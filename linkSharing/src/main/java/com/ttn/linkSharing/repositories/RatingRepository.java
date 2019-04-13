package com.ttn.linkSharing.repositories;

import com.ttn.linkSharing.entities.Rating;
import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating,Integer> {
}
