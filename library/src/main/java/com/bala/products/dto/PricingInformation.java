package com.bala.products.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PricingInformation {

    @SerializedName("standard_price")
    @Expose
    public Double standardPrice;
    @SerializedName("standard_price_no_vat")
    @Expose
    public Double standardPriceNoVat;
    @SerializedName("currentPrice")
    @Expose
    public Double currentPrice;

}
