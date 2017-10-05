package com.huyang.dao;

import com.huyang.criteria.MyMoneyLogCriteriaTO;
import com.huyang.lib.to.MyMoneyLog;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MyMoneyLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MyMoneyLog record);

    int insertSelective(MyMoneyLog record);

    MyMoneyLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MyMoneyLog record);

    int updateByPrimaryKey(MyMoneyLog record);

    List<MyMoneyLog> getMyMoneyLogByYearAndMonth(@Param("uid") Integer uid, @Param("year") Integer year, @Param("month") Integer month);

    List<MyMoneyLog> getMyMoneyLog(MyMoneyLogCriteriaTO criteria);

    BigDecimal getMyMoneyLogByYearAndMonthTotle(@Param("uid") Integer id, @Param("year") Integer year, @Param("month") Integer month);

}