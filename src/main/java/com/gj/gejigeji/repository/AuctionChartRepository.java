package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.AuctionChart;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuctionChartRepository extends MongoRepository<AuctionChart,String> {
}
