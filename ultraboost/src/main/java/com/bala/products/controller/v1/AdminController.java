package com.bala.products.controller.v1;

import com.bala.products.config.ApplicationConfig;
import com.bala.products.dto.MetaData;
import com.bala.products.dto.PricingInformation;
import com.bala.products.dto.Product;
import com.bala.products.dto.ProductDescription;
import com.bala.products.service.KafkaProducer;
import com.bala.products.service.PersistenceService;
import com.bala.products.service.ProductService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
public class AdminController {
    /***********************************
     *  ADMIN endpoints
     * *********************************/

    @Autowired
    private ProductService productService;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private PersistenceService persistenceService;

    @Autowired
    private ApplicationConfig config;

    @RequestMapping(value = "/_ping", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> pong() {
        return new ResponseEntity<>("pong", HttpStatus.OK);
    }

    /**
     * CAUTION: Use this endpoint sparingly - only when exhaustive
     * health checks are needed as it puts the thread to sleep.
     * */
    @RequestMapping(value = "/_health", method = RequestMethod.GET)
    @ResponseBody
    public  ResponseEntity<String> health() {

        /*
         * Setup a sample product with random id. It should appear
         * for query in "50 ms" (is this number right? - review)
         * for the application to be healthy
         * */

        String productId = new RandomString(6).make().toUpperCase();
        String modelNumber = "HEALTHCHCK";

        Product product = AdminController.getSampleProduct(productId, modelNumber);

        kafkaProducer.sendMessage(product);
        try {
            Thread.sleep(config.persistence_delay);
        } catch (InterruptedException ex) {
            return new ResponseEntity<>("internal error", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Product product1 = productService.findByProductId(productId);
        if (null == product1) {
            return new ResponseEntity<>("persistence error or bad performance", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!persistenceService.deleteByProductId(productId)) {
            return new ResponseEntity<>("persistence error - delete product issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("healthy", HttpStatus.OK);
    }

    // TODO: should be admin only endpoint.
    @RequestMapping(value = "/_sample", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> setupSampleProducts() {

        List<Integer> range = IntStream.range(10, 91).boxed().collect(Collectors.toList());
        for (int i : range) {
            String productId = String.format("CG70%d", i);
            String modelNumber = String.format("BT0%d", i);
            Product product = AdminController.getSampleProduct(productId, modelNumber);
            kafkaProducer.sendMessage(product);
        }

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    private static Product getSampleProduct(String productId, String modelNumber) {
        MetaData md = new MetaData()
                .setCanonical("//www.adidas.co.uk/nite-jogger-shoes/product.html")
                .setDescription("Shop for Nite Jogger Shoes - Black at adidas.co.uk! See all the styles " +
                        "and colours of Nite Jogger Shoes - Black at the official adidas UK online store.")
                .setKeywords("Nite Jogger Shoes")
                .setSiteName("adidas United Kingdom")
                .setPageTitle("adidas Nite Jogger Shoes - Black | adidas UK");

        PricingInformation pi = new PricingInformation()
                .setCurrentPrice(119.95)
                .setStandardPrice(199.95)
                .setStandardPriceNoVat(119.95);

        ProductDescription pd = new ProductDescription()
                .setSubtitle("Modern cushioning updates this flashy'80s standout.")
                .setTitle("Nite Jogger Shoes")
                .setText("Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and " +
                        " reflective details. The mesh and nylon ripstop upper is detailed with suede overlays. An " +
                        " updated Boost midsole adds responsive cushioning for all-day comfort.");

        return new Product()
                .setId(productId)
                .setModelNumber(modelNumber)
                .setName("Nite Jogger Lite")
                .setProductType("inline")
                .setMetaData(md)
                .setPricingInformation(pi)
                .setProductDescription(pd);
    }
}
