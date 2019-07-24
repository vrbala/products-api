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
public class KafkaPayload {

    @Expose
    @SerializedName("product")
    public Product product;

    /*
    * Kafka Consumer currently supports "CREATE" and "UPDATE"
    *
    * */
    @Expose
    @SerializedName("action")
    public String action;
}
