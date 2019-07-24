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
public class ProductDescription {

    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("subtitle")
    @Expose
    public String subtitle;
    @SerializedName("text")
    @Expose
    public String text;

}