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
    private String id;
    @NotNull
    @SerializedName("name")
    @Expose
    private String name;
    @NotNull
    @SerializedName("model_number")
    @Expose
    private String modelNumber;
    @NotNull
    @SerializedName("product_type")
    @Expose
    private String productType;
    @NotNull
    @SerializedName("meta_data")
    @Expose
    private MetaData metaData;
    @NotNull
    @SerializedName("pricing_information")
    @Expose
    private PricingInformation pricingInformation;
    @NotNull
    @SerializedName("product_description")
    @Expose
    private ProductDescription productDescription;

}
