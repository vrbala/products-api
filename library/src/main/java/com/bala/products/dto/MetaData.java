package com.bala.products.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetaData {

    @SerializedName("page_title")
    @Expose
    public String pageTitle;
    @SerializedName("site_name")
    @Expose
    public String siteName;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("keywords")
    @Expose
    public String keywords;
    @SerializedName("canonical")
    @Expose
    public String canonical;

}