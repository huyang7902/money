package com.huyang.dao;

import com.huyang.lib.to.MoneyLog;
import com.huyang.lib.to.UserTotle;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoneyLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MoneyLog record);

    int insertSelective(MoneyLog record);

    MoneyLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MoneyLog record);

    int updateByPrimaryKey(MoneyLog record);

    List<MoneyLog> getMoneyLogByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

    List<MoneyLog> getweekMoneyLog();

    List<UserTotle> getMoneyByUserName();
}