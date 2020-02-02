package com.scottish.power;

import com.scottish.power.controller.SmartMeterController;
import com.scottish.power.exception.AccountNotFoundException;
import com.scottish.power.model.SmartMeterDetail;
import com.scottish.power.repository.SmartMeterRepository;
import com.scottish.power.service.SmartMeterService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@RunWith(MockitoJUnitRunner.class)
public class SmartMeterControllerTest {

    private SmartMeterController smartMeterController;

    @Mock
    private SmartMeterRepository smartMeterRepositoryMock;

    private SmartMeterService smartMeterServiceSpy;

    private SmartMeterDetail dummySmartMeterDetail;

    @Before
    public void setup() {
        smartMeterServiceSpy = Mockito.spy(new SmartMeterService(smartMeterRepositoryMock));
        smartMeterController = new SmartMeterController(smartMeterServiceSpy);

        dummySmartMeterDetail = new SmartMeterDetail(
                9223372036L, BigDecimal.valueOf(902.09), BigDecimal.valueOf(7348.09));
    }

    @Test
    public void testFindAllByAccount() {
        Mockito.when(smartMeterRepositoryMock
                .findById(dummySmartMeterDetail.getAccountNumber())).
                thenReturn(Optional.of(dummySmartMeterDetail));

        final ResponseEntity actualResult = smartMeterController.getMeterReadingsByAccountNo(dummySmartMeterDetail
                .getAccountNumber());

        Assert.assertEquals(HttpStatus.OK, actualResult.getStatusCode());
        Assert.assertEquals(200, actualResult.getStatusCodeValue());
        Assert.assertEquals(dummySmartMeterDetail, actualResult.getBody());

        Mockito.verify(smartMeterRepositoryMock, Mockito.timeout(100).times(1))
                .findById(dummySmartMeterDetail.getAccountNumber());
        Mockito.verify(smartMeterRepositoryMock, Mockito.timeout(100).times(1))
                .findById(any());
        Mockito.verify(smartMeterServiceSpy, Mockito.timeout(100).times(1))
                .findReadingByAccountNo(dummySmartMeterDetail.getAccountNumber());
        Mockito.verify(smartMeterServiceSpy, Mockito.timeout(100).times(1))
                .findReadingByAccountNo(anyLong());
    }

    @Test(expected = AccountNotFoundException.class)
    public void testFindAllByAccount_AccountNotFound() {
        final long accountNoNotInDatabase = 837472903L;
        smartMeterController.getMeterReadingsByAccountNo(accountNoNotInDatabase);
    }

    @Test(expected = NullPointerException.class)
    public void testFindAllByAccount_NullPointer() {
        Mockito.when(smartMeterRepositoryMock.findById(dummySmartMeterDetail.getAccountNumber())).
                thenThrow(new NullPointerException());

        smartMeterController.getMeterReadingsByAccountNo(dummySmartMeterDetail.getAccountNumber());
    }
}
