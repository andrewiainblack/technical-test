package com.scottish.power;

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

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SmartMeterServiceTest {

    @Mock
    private SmartMeterRepository smartMeterRepositoryMock;

    private SmartMeterService smartMeterService;

    private SmartMeterDetail dummySmartMeterDetail;

    @Before
    public void setup() {
        smartMeterService = new SmartMeterService(smartMeterRepositoryMock);

        dummySmartMeterDetail = new SmartMeterDetail(
                9223372036L, BigDecimal.valueOf(902.09), BigDecimal.valueOf(7348.09));
    }

    @Test
    public void testFindReadingByAccountNo_accountFound() {
        Mockito.when(smartMeterRepositoryMock
                .findById(dummySmartMeterDetail.getAccountNumber())).
                thenReturn(Optional.of(dummySmartMeterDetail));

        final SmartMeterDetail actualResult = smartMeterService.findReadingByAccountNo(dummySmartMeterDetail
                .getAccountNumber());

        Assert.assertEquals(dummySmartMeterDetail, actualResult);

        Mockito.verify(smartMeterRepositoryMock, Mockito.timeout(100).times(1))
                .findById(dummySmartMeterDetail.getAccountNumber());
        Mockito.verify(smartMeterRepositoryMock, Mockito.timeout(100).times(1))
                .findById(any());
    }

    @Test(expected = AccountNotFoundException.class)
    public void testFindReadingByAccountNo_accountNotFound() {
        Mockito.when(smartMeterRepositoryMock
                .findById(dummySmartMeterDetail.getAccountNumber())).
                thenReturn(Optional.empty());

       smartMeterService.findReadingByAccountNo(dummySmartMeterDetail
                .getAccountNumber());
    }
}
