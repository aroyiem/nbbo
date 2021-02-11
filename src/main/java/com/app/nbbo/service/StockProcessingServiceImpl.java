package com.app.nbbo.service;

import com.app.nbbo.dao.StockDao;
import com.app.nbbo.entity.Stock;
import com.app.nbbo.model.TickerRate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockProcessingServiceImpl implements StockProcessingService {

    private final Log logger = LogFactory.getLog(this.getClass());

    private StockDao stockDao;
    private FormatService formatService;

    @Autowired
    public StockProcessingServiceImpl(StockDao stockDao, FormatService formatService) {
        this.stockDao = stockDao;
        this.formatService = formatService;
    }

    @Override
    public TickerRate getRatesForTicker(String ticker) {
        logger.info("Fetching rates for ticker " + ticker);
        List<Stock> list = stockDao.findByIdSymbol(ticker);
        if(list.isEmpty()) {
            throw new RuntimeException("Exchange rates not found for ticker "+ ticker);
        }
        return formatService.formatPayload(stockDao.findByIdSymbol(ticker), ticker);
    }

    @Override
    public List<TickerRate> getRates() {
        logger.info("Fetching rates for all tickers ");
        return formatService.formatPayload(stockDao.findAll());
    }
}
