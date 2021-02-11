package com.app.nbbo.dao;

import com.app.nbbo.entity.Stock;
import com.app.nbbo.entity.StockPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockDao extends JpaRepository<Stock, StockPK> {

    @Query("select s from Stock s where s.id.symbol = :symbol")
    List<Stock> findByIdSymbol(@Param("symbol") String symbol);
}
