package com.bala.products.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
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
    private float standardPrice;

    @Column(name = "standard_price_no_vat")
    private float standardPriceNoVat;

    @Column(name = "current_price")
    private float currentPrice;

    private String title;

    private String subtitle;

    private String text;
}
