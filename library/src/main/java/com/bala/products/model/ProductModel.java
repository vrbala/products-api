package com.bala.products.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
@Accessors(chain = true)
public class ProductModel {

    @Id
    @Column(name = "_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    private String name;

    @Column(name = "model_number")
    private String modelNumber;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "page_title")
    private String pageTitle;

    @Column(name = "site_name")
    private String siteName;

    private String description;

    private String keywords;

    private String canonical;

    @Column(name = "standard_price")
    private Double standardPrice;

    @Column(name = "standard_price_no_vat")
    private Double standardPriceNoVat;

    @Column(name = "current_price")
    private Double currentPrice;

    private String title;

    private String subtitle;

    private String text;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_dttm")
    private Date createdDateTime;
}
