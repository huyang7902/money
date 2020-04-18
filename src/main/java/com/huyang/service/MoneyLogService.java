package com.huyang.service;

import com.huyang.criteria.MoneyLogCriteriaTO;
import com.huyang.lib.to.MoneyLog;

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
     * 根据条件查找账单
     * @return
     */
    List<MoneyLog> getMoneyLog(MoneyLogCriteriaTO criteria);

    /**
     * 查询所有未结算账单金额和
     * @param uid
     * @return
     */
    MoneyLog getUnBalanceSumMoneyLog(Integer uid);

    /**
     * 结算账单
     * @return
     */
    int balance(Integer settlementId);
}
