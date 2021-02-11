package com.app.nbbo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    @EmbeddedId
    private StockPK id;

    @Column(name = "description")
    private String description;

    @Column(name = "bidPrice")
    @NotNull(message = "BidPrice is mandatory")
    private Double bidPrice;

    @Column(name = "offerPrice")
    @NotNull(message = "OfferPrice is mandatory")
    private Double offerPrice;
}
