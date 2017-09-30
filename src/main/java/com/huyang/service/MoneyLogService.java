package com.huyang.service;

import com.huyang.lib.to.MoneyLog;
import com.huyang.lib.to.UserTotle;

import java.util.List;

/**
 * money服务实现类
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/13 9:39
 */
public interface MoneyLogService {
    /**
     * 添加记账记录
     * @param moneyLog 记录实体类
     * @return 影响行数
     */
    int addMoneyLog(MoneyLog moneyLog);

    List<MoneyLog> getMoneyLogByYearAndMonth(Integer year, Integer month);

    /**
     * 本周记录
     * @return
     */
    List<MoneyLog> getweekMoneyLog();

    List<UserTotle> getMoneyByUserName();
}
