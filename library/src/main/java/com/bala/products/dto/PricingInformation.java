package com.bala.products.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class PricingInformation {

    @SerializedName("standard_price")
    @Expose
    private Double standardPrice;
    @SerializedName("standard_price_no_vat")
    @Expose
    private Double standardPriceNoVat;
    @SerializedName("currentPrice")
    @Expose
    private Double currentPrice;

}
