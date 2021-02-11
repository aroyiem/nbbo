package com.app.nbbo.controller;

import com.app.nbbo.model.TickerRate;
import com.app.nbbo.service.StockProcessingService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nbbo")
public class IndexController {

    private final Log logger = LogFactory.getLog(this.getClass());

    private StockProcessingService stockProcessingService;

    @Autowired
    public IndexController(StockProcessingService stockProcessingService) {
        this.stockProcessingService = stockProcessingService;
    }

    @RequestMapping(value = "/ticker/{ticker}", method = RequestMethod.GET)
    public TickerRate getPriceListForTicker(@PathVariable("ticker") String ticker) {
        logger.info("Fetching price list for ticker " + ticker);
        return stockProcessingService.getRatesForTicker(ticker);
    }

    @RequestMapping(value = "/tickers", method = RequestMethod.GET)
    public List<TickerRate> getPriceList() {
        logger.info("Fetching price list for all tickers");
        return stockProcessingService.getRates();
    }
}
