package com.huyang.dao;

import com.huyang.lib.to.SettlementHistory;

import java.util.List;

public interface SettlementHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SettlementHistory record);

    int insertSelective(SettlementHistory record);

    SettlementHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SettlementHistory record);

    int updateByPrimaryKey(SettlementHistory record);

    List<SettlementHistory> getAllHistory();
}