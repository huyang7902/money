package com.huyang.service;

import com.huyang.criteria.MyMoneyLogCriteriaTO;
import com.huyang.lib.to.MyMoneyLog;

import java.math.BigDecimal;
import java.util.List;

/**
 * money服务实现类
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/13 9:39
 */
public interface MyMoneyLogService {
    /**
     * 添加记账记录
     * @param myMoneyLog 记录实体类
     * @return 影响行数
     */
    int addMyMoneyLog(MyMoneyLog myMoneyLog);

    List<MyMoneyLog> getMyMoneyLogByYearAndMonth(Integer uid, Integer year, Integer month);

    /**
     * 根据条件查找账单
     * @return
     */
    List<MyMoneyLog> getMyMoneyLog(MyMoneyLogCriteriaTO criteria);

    /**
     * 计算当月账单总和
     * @param uid
     * @param year
     * @param month
     * @return
     */
    BigDecimal getMyMoneyLogByYearAndMonthTotle(Integer uid, Integer year, Integer month);
}
