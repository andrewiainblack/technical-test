package com.scottish.power.repository;

import com.scottish.power.model.SmartMeterDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartMeterRepository extends CrudRepository<SmartMeterDetail, Long> {
}