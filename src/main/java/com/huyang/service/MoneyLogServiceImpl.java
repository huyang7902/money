package com.huyang.service;

import com.huyang.dao.MoneyLogMapper;
import com.huyang.lib.to.MoneyLog;
import com.huyang.lib.to.MoneyResult;
import com.huyang.lib.to.UserTotle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/13 9:42
 */
@Service
public class MoneyLogServiceImpl implements MoneyLogService{

    @Autowired
    private MoneyLogMapper moneyLogMapper;

    @Override
    public int addMoneyLog(MoneyLog moneyLog) {
        int rows = moneyLogMapper.insertSelective(moneyLog);
        return rows;
    }

    @Override
    public List<MoneyLog> getMoneyLogByYearAndMonth(Integer year, Integer month) {
        return moneyLogMapper.getMoneyLogByYearAndMonth(year, month);
    }

    @Override
    public List<MoneyLog> getweekMoneyLog() {
        return moneyLogMapper.getweekMoneyLog();
    }

    @Override
    public List<UserTotle> getMoneyByUserName() {
        return moneyLogMapper.getMoneyByUserName();
    }
}
