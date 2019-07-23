package com.bala.products.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Product {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("model_number")
    @Expose
    public String modelNumber;
    @SerializedName("product_type")
    @Expose
    public String productType;
    @SerializedName("meta_data")
    @Expose
    public MetaData metaData;
    @SerializedName("pricing_information")
    @Expose
    public PricingInformation pricingInformation;
    @SerializedName("product_description")
    @Expose
    public ProductDescription productDescription;

}
