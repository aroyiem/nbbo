package com.app.nbbo.service;

import com.app.nbbo.model.TickerRate;

import java.util.List;

public interface StockProcessingService {
    TickerRate getRatesForTicker(String ticker);

    List<TickerRate> getRates();
}
