package com.app.nbbo.service;

import com.app.nbbo.entity.Stock;
import com.app.nbbo.model.ExchangePrice;
import com.app.nbbo.model.TickerRate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FormatService {

    public TickerRate formatPayload(final List<Stock> priceList, final String ticker) {
        List<ExchangePrice> exchangePriceList =
                priceList.stream().collect(
                        Collectors.mapping(p->
                                new ExchangePrice(p.getId().getExchange(),
                                        p.getBidPrice(), p.getOfferPrice()), Collectors.toList())
                );

        return constructResponse(exchangePriceList, ticker);
    }

    public List<TickerRate> formatPayload(final List<Stock> priceList) {
        Map<String, List<Stock>> requestMap = new HashMap<>();
        List<TickerRate> response = new ArrayList<>();

        priceList.forEach(p-> {
            String ticker = p.getId().getSymbol();
            if(null != requestMap.get(ticker)) {
                List<Stock> list = requestMap.get(ticker);
                list.add(p);
                requestMap.put(ticker, list);
            } else {
                requestMap.put(ticker, new ArrayList<Stock>(){{add(p);}});
            }
        });

        requestMap.forEach((key, value)-> response.add(formatPayload(value, key)));

        return response;
    }

    private TickerRate constructResponse(final List<ExchangePrice> exchangePriceList, final String ticker) {
        TickerRate rate = new TickerRate();
        rate.setTime(getTimeStamp());
        rate.setTicker(ticker);
        rate.setPriceList(exchangePriceList);

        final Double bestBid = getBestBid(exchangePriceList);
        final Double bestOffer = getBestOffer(exchangePriceList);

        rate.setBestBid(bestBid);
        rate.setBestOffer(bestOffer);
        rate.setNbbo(bestBid + "@" + bestOffer);
        return rate;
    }


    private Double getBestBid(final List<ExchangePrice> exchangePriceList) {
        return exchangePriceList.stream().max(Comparator.comparing(ExchangePrice::getBidPrice)).get().getBidPrice();
    }

    private Double getBestOffer(final List<ExchangePrice> exchangePriceList) {
        return exchangePriceList.stream().min(Comparator.comparing(ExchangePrice::getOfferPrice)).get().getOfferPrice();
    }

    private String getTimeStamp() {
        return new SimpleDateFormat("yyyy.MM.dd hh.mm.ss aa").format(new Date());
    }

}
