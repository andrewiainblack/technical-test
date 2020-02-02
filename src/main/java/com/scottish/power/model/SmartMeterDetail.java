package com.scottish.power.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "SMART_METER_READINGS")
public class SmartMeterDetail {

    @Id
    @Column(name = "ACCOUNT_NUMBER")
    @JsonIgnore
    private long accountNumber;

    @Column(name = "ELEC_SMART_READ")
    private BigDecimal electricityRead;

    @Column(name = "GAS_SMART_READ")
    private BigDecimal gasRead;

    public SmartMeterDetail(final long accountNumber,
                            final BigDecimal electricityRead,
                            final BigDecimal gasMeterReading) {
        this.accountNumber = accountNumber;
        this.electricityRead = electricityRead;
        this.electricityRead = gasMeterReading;
    }

    public SmartMeterDetail() {
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(final long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getElectricityRead() {
        return electricityRead;
    }

    public void setElectricityRead(final BigDecimal electricityRead) {
        this.electricityRead = electricityRead;
    }

    public BigDecimal getGasRead() {
        return gasRead;
    }

    public void setGasRead(final BigDecimal gasRead) {
        this.gasRead = gasRead;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SmartMeterDetail{");
        sb.append("accountNumber=").append(accountNumber);
        sb.append(", electricityRead=").append(electricityRead);
        sb.append(", gasRead=").append(gasRead);
        sb.append('}');
        return sb.toString();
    }
}
