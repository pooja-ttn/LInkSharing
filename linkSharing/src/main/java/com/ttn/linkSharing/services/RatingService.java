package com.ttn.linkSharing.services;

import com.ttn.linkSharing.repositories.RatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    @Autowired
    RatingRepository ratingRepository;

    Logger logger = LoggerFactory.getLogger(RatingService.class);

}
