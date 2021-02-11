package com.app.nbbo.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class StockPK implements Serializable {

    private String symbol;

    private String exchange;
}
