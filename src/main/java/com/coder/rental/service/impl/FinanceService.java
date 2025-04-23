package com.coder.rental.service.impl;

import com.coder.rental.mapper.FinanceMapper;
import com.coder.rental.service.IFinanceService;
import com.coder.rental.vo.FinanceCostVo;
import com.coder.rental.vo.FinanceNumDayVo;
import com.coder.rental.vo.FinanceNumMonthVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceService implements IFinanceService {
    @Resource
    private FinanceMapper financeMapper;
    @Override
    public List<FinanceNumDayVo> countDayRental() {
        return financeMapper.countDayRental();
    }

    @Override
    public List<FinanceNumDayVo> countDayReturn() {
        return financeMapper.countDayReturn();
    }

    @Override
    public List<FinanceNumMonthVo> countMonthRental() {
        return financeMapper.countMonthRental();
    }

    @Override
    public List<FinanceNumMonthVo> countMonthReturn() {
        return financeMapper.countMonthReturn();
    }

    @Override
    public FinanceCostVo sumRentPay() {

        return financeMapper.sumRentPay();
    }

    @Override
    public Integer sumDeposit() {
        return financeMapper.sumDeposit();
    }

    @Override
    public FinanceCostVo sumRentPayMonth() {
        return financeMapper.sumRentPayMonth();
    }

    @Override
    public Integer sumDepositMonth() {
        return financeMapper.sumDepositMonth();
    }
}
