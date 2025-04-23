package com.coder.rental.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coder.rental.vo.FinanceCostVo;
import com.coder.rental.vo.FinanceNumDayVo;
import com.coder.rental.vo.FinanceNumMonthVo;
import org.apache.poi.ss.formula.functions.Finance;

import java.util.List;

public interface FinanceMapper extends BaseMapper<Finance> {
    List<FinanceNumDayVo> countDayRental();

    List<FinanceNumDayVo> countDayReturn();

    List<FinanceNumMonthVo> countMonthRental();

    List<FinanceNumMonthVo> countMonthReturn();

    FinanceCostVo sumRentPay();

    Integer sumDeposit();
    FinanceCostVo sumRentPayMonth();

    Integer sumDepositMonth();
}
