package com.app.nbbo.model;

import lombok.Getter;

import java.io.Serializable;

@Getter
public class ExchangePrice implements Serializable {

    private String exchange;

    private Double bidPrice;

    private Double offerPrice;

    public ExchangePrice(String exchange, Double bidPrice, Double offerPrice) {
        this.exchange = exchange;
        this.bidPrice = bidPrice;
        this.offerPrice = offerPrice;
    }
}
