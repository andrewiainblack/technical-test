package com.scottish.power.cucumber;

import com.scottish.power.controller.SmartMeterController;
import com.scottish.power.exception.AccountNotFoundException;
import com.scottish.power.model.SmartMeterDetail;
import com.scottish.power.repository.SmartMeterRepository;
import com.scottish.power.service.SmartMeterService;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

public class Definitions {

    private final RestTemplate restTemplate = new RestTemplate();

    @Given("^the application has started up correctly")
    public void theApplicationHasStartedUpCorrectly() {
        final String healthUrl = "http://localhost:8080/actuator/health";
        final ResponseEntity result = restTemplate.getForEntity(healthUrl, String.class);

        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(200, result.getStatusCodeValue());
    }

    @Then("^the client can successfully request an account in the database")
    public void theClientCanSuccessfullyRequestAnAccountInTheDatabase() {
        final String requestUrl = "http://localhost:8080/api/smart/reads/9223372036";
        final ResponseEntity result = restTemplate.getForEntity(requestUrl, String.class);

        final String expectedResponseMessage = "{\"electricityRead\":326.038,\"gasRead\":93.038}";

        Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assert.assertEquals(200, result.getStatusCodeValue());
        Assert.assertEquals(expectedResponseMessage, result.getBody());
    }

    @Then("^the client gets a bad request exception for bad account input")
    public void theClientGetsBadRequestExceptionForMalformedAccountInput() {
        final String requestUrl = "http://localhost:8080/api/smart/reads/74gfsfgh";
        final String expectedResponseMessage = "Bad input received from client - For input string: \"74gfsfgh\"";
        try {
            restTemplate.getForEntity(requestUrl, String.class);
        } catch (HttpClientErrorException.BadRequest ex) {
            Assert.assertNotNull(ex);
            Assert.assertEquals(HttpStatus.BAD_REQUEST, ex.getStatusCode());
            Assert.assertEquals(400, ex.getRawStatusCode());
            Assert.assertEquals(expectedResponseMessage, ex.getResponseBodyAsString());
        }
    }

    @Then("^the client gets a not found exception for an account not in the database")
    public void theClientGetsNotFoundExceptionForAccountNotInTheDatabase() {
        final String requestUrl = "http://localhost:8080/api/smart/reads/7364849394";
        final String expectedResponseMessage = "Can't find account 7364849394 in database";
        try {
            restTemplate.getForEntity(requestUrl, String.class);
        } catch (final HttpClientErrorException.NotFound ex) {
            Assert.assertNotNull(ex);
            Assert.assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
            Assert.assertEquals(404, ex.getRawStatusCode());
            Assert.assertEquals(expectedResponseMessage, ex.getResponseBodyAsString());
        }
    }
}