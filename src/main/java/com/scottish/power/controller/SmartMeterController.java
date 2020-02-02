package com.scottish.power.controller;

import com.scottish.power.exception.AccountNotFoundException;
import com.scottish.power.model.SmartMeterDetail;
import com.scottish.power.service.SmartMeterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class SmartMeterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SmartMeterController.class);

    private static final String BAD_REQ_ERR_MSG = "Bad input received from client - ";

    private SmartMeterService smartMeterService;

    @Autowired
    public SmartMeterController(final SmartMeterService smartMeterService) {
        this.smartMeterService = smartMeterService;
    }

    /**
     * Endpoint to return both gas and electricity meter readings for a given account number.
     *
     * @param accountNo account number received from client.
     * @return response entity containing the meter readings.
     */
    @GetMapping(path = "/smart/reads/{accountNo}", produces = "application/json")
    public ResponseEntity<SmartMeterDetail> getMeterReadingsByAccountNo(@PathVariable final long accountNo) {
        LOGGER.info("--- Request for meter readings received for account: {}", accountNo);

        final SmartMeterDetail smartMeterDetail = smartMeterService.findReadingByAccountNo(accountNo);
        return new ResponseEntity<>(smartMeterDetail, HttpStatus.OK);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleAccountNotFoundException(final AccountNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NullPointerException.class, SQLException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity handleInternalServiceExceptions(final Exception ex) {
        LOGGER.info("--- Request for meter readings received for account: {}", ex);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity handleBadClientRequestException(final NumberFormatException ex) {
        final String error_msg = BAD_REQ_ERR_MSG.concat(ex.getMessage());
        LOGGER.info("--- {} ---", error_msg);

        return new ResponseEntity<>(error_msg, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SmartMeterController{");
        sb.append("smartMeterService=").append(smartMeterService);
        sb.append('}');
        return sb.toString();
    }
}