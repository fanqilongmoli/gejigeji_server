package com.gj.gejigeji.repository;

import com.gj.gejigeji.model.AuctionChart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AuctionChartRepository extends MongoRepository<AuctionChart,String> {


    Optional<AuctionChart> findFirstByFeedIdOrderByDateDesc(String feedId);
}
