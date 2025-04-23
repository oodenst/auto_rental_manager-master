package com.coder.rental.vo;

import lombok.Data;

@Data
public class FinanceNumMonthVo {
    //按月的天数
    private Integer days;
    //出租的数量
    private Integer rentalNum;
    //归还的数量
    private Integer returnNum;
}
