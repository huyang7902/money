package com.huyang.service;

import com.huyang.criteria.MyMoneyLogCriteriaTO;
import com.huyang.dao.MyMoneyLogMapper;
import com.huyang.lib.to.MyMoneyLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/13 9:42
 */
@Service
public class MyMoneyLogServiceImpl implements MyMoneyLogService{

    @Autowired
    private MyMoneyLogMapper myMoneyLogMapper;

    @Override
    public int addMyMoneyLog(MyMoneyLog myMoneyLog) {
        int rows = myMoneyLogMapper.insertSelective(myMoneyLog);
        return rows;
    }

    @Override
    public List<MyMoneyLog> getMyMoneyLogByYearAndMonth(Integer uid,Integer year, Integer month) {
        return myMoneyLogMapper.getMyMoneyLogByYearAndMonth(uid, year, month);
    }


    @Override
    public List<MyMoneyLog> getMyMoneyLog(MyMoneyLogCriteriaTO criteria) {
        return myMoneyLogMapper.getMyMoneyLog(criteria);
    }

    @Override
    public BigDecimal getMyMoneyLogByYearAndMonthTotle(Integer uid, Integer year, Integer month) {
        return myMoneyLogMapper.getMyMoneyLogByYearAndMonthTotle(uid,year,month);
    }

}
