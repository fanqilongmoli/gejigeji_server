package com.gj.gejigeji.service;

import com.gj.gejigeji.model.Feed;
import com.gj.gejigeji.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedService {

    @Autowired
    FeedRepository feedRepository;

    public List<Feed> getAll() {
        return feedRepository.findAll();
    }
}
