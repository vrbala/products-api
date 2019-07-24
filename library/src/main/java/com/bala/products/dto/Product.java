package com.bala.products.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class Product {

    @NotNull
    @SerializedName("id")
    @Expose
    public String id;
    @NotNull
    @SerializedName("name")
    @Expose
    public String name;
    @NotNull
    @SerializedName("model_number")
    @Expose
    public String modelNumber;
    @NotNull
    @SerializedName("product_type")
    @Expose
    public String productType;
    @NotNull
    @SerializedName("meta_data")
    @Expose
    public MetaData metaData;
    @NotNull
    @SerializedName("pricing_information")
    @Expose
    public PricingInformation pricingInformation;
    @NotNull
    @SerializedName("product_description")
    @Expose
    public ProductDescription productDescription;

}
