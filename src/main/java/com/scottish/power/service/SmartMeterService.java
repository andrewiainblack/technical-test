package com.scottish.power.service;

import com.scottish.power.exception.AccountNotFoundException;
import com.scottish.power.model.SmartMeterDetail;
import com.scottish.power.repository.SmartMeterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SmartMeterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartMeterService.class);

    private SmartMeterRepository smartMeterRepository;

    @Autowired
    public SmartMeterService(final SmartMeterRepository smartMeterRepository) {
        this.smartMeterRepository = smartMeterRepository;
    }

    public SmartMeterDetail findReadingByAccountNo(final long accountNo) {
        final Optional<SmartMeterDetail> smartMeterDetail = smartMeterRepository.findById(accountNo);
        if (smartMeterDetail.isPresent()) {
            LOGGER.info("--- Account retrieved from database ---");
            return smartMeterDetail.get();
        } else {
            LOGGER.error("--- Account not found in database ---");
            throw new AccountNotFoundException("Can't find account " + String.valueOf(accountNo) + " in database");
        }
    }

    public SmartMeterRepository getSmartMeterRepository() {
        return smartMeterRepository;
    }

    public void setSmartMeterRepository(final SmartMeterRepository smartMeterRepository) {
        this.smartMeterRepository = smartMeterRepository;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SmartMeterService{");
        sb.append("smartMeterRepository=").append(smartMeterRepository);
        sb.append('}');
        return sb.toString();
    }
}
