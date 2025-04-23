package com.coder.rental.vo;

import lombok.Data;

@Data
public class FinanceNumDayVo {
    //按天的小时数
    private Integer hours;
    //出租的数量
    private Integer rentalNum;
    //归还的数量
    private Integer returnNum;
}
