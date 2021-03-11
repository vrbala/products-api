package com.bala.products.mapper;

import com.bala.products.dto.MetaData;
import com.bala.products.dto.PricingInformation;
import com.bala.products.dto.Product;
import com.bala.products.dto.ProductDescription;
import com.bala.products.model.ProductModel;

public interface ProductMapper {
    static Product productModelToDto(ProductModel pm) {
        MetaData md = new MetaData()
                .setCanonical(pm.getCanonical())
                .setDescription(pm.getDescription())
                .setKeywords(pm.getKeywords())
                .setSiteName(pm.getSiteName())
                .setPageTitle(pm.getPageTitle());

        PricingInformation pi = new PricingInformation()
                .setCurrentPrice(pm.getCurrentPrice())
                .setStandardPrice(pm.getStandardPrice())
                .setStandardPriceNoVat(pm.getStandardPriceNoVat());

        ProductDescription pd = new ProductDescription()
                .setSubtitle(pm.getSubtitle())
                .setText(pm.getText())
                .setTitle(pm.getTitle());

        return new Product()
                .setId(pm.getProductId())
                .setName(pm.getName())
                .setProductType(pm.getProductType())
                .setModelNumber(pm.getModelNumber())
                .setProductDescription(pd)
                .setPricingInformation(pi)
                .setMetaData(md);
    }

    static ProductModel productDtoToModel(Product pd) {
        ProductModel pm = new ProductModel();
        ProductMapper.productDtoIntoModel(pd, pm);
        return pm;
    }

    static void productDtoIntoModel(Product pd, ProductModel pm) {
        pm.setCanonical(pd.getMetaData().getCanonical())
                .setDescription(pd.getMetaData().getDescription())
                .setKeywords(pd.getMetaData().getKeywords())
                .setSiteName(pd.getMetaData().getSiteName())
                .setPageTitle(pd.getMetaData().getPageTitle())
                .setCurrentPrice(pd.getPricingInformation().getCurrentPrice())
                .setStandardPrice(pd.getPricingInformation().getStandardPrice())
                .setStandardPriceNoVat(pd.getPricingInformation().getStandardPriceNoVat())
                .setSubtitle(pd.getProductDescription().getSubtitle())
                .setText(pd.getProductDescription().getText())
                .setTitle(pd.getProductDescription().getTitle())
                .setProductId(pd.getId())
                .setName(pd.getName())
                .setProductType(pd.getProductType())
                .setModelNumber(pd.getModelNumber());
    }
}
