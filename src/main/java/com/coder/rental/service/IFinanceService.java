package com.coder.rental.service;

import com.coder.rental.vo.FinanceCostVo;
import com.coder.rental.vo.FinanceNumDayVo;
import com.coder.rental.vo.FinanceNumMonthVo;

import java.util.List;

public interface IFinanceService {
    List<FinanceNumDayVo> countDayRental();
    List<FinanceNumDayVo> countDayReturn();
    List<FinanceNumMonthVo> countMonthRental();
    List<FinanceNumMonthVo> countMonthReturn();

    FinanceCostVo sumRentPay();

    Integer sumDeposit();
    FinanceCostVo sumRentPayMonth();

    Integer sumDepositMonth();
}
