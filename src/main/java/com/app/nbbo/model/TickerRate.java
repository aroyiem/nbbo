package com.app.nbbo.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class TickerRate implements Serializable {

    private String time;

    private String ticker;

    private List<ExchangePrice> priceList = new ArrayList<>();

    private Double bestBid;

    private Double bestOffer;

    private String nbbo;


}
