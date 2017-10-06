package com.huyang.web.controller;


import com.huyang.common.cache.EchcacheManager;
import com.huyang.common.type.EhCacheType;
import com.huyang.common.type.MoneyLogType;
import com.huyang.common.utils.CookieUtils;
import com.huyang.common.utils.RequestUtil;
import com.huyang.common.utils.ResponseResult;
import com.huyang.criteria.MoneyLogCriteriaTO;
import com.huyang.dao.MoneyLogMapper;
import com.huyang.dao.SettlementHistoryMapper;
import com.huyang.lib.to.MoneyLog;
import com.huyang.lib.to.MoneyResult;
import com.huyang.lib.to.SettlementHistory;
import com.huyang.lib.to.User;
import com.huyang.service.MoneyLogService;
import com.huyang.service.UserService;
import com.huyang.web.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 记账系统controller
 *
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/9/11 14:14
 */
@Controller
@RequestMapping("/money")
public class MoneyController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MoneyController.class);

    @Autowired
    private MoneyLogService moneyLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private MoneyLogMapper moneyLogMapper;

    @Autowired
    private SettlementHistoryMapper settlementHistoryMapper;

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = {"/index.html"})
    public String index(HttpServletRequest request) {
        //User loginUser = getLoginUser(request);
        String token = CookieUtils.getCookieValue(request, Constants.USER_TOKEN);
        //获取缓存中的登录时间
        String key = String.format(EhCacheType.USER_LOGIN.getKey(), token);
        User loginUser = (User) EchcacheManager.getCacheByKeyAndName(EhCacheType.USER_LOGIN.getName(), key);
        request.setAttribute("loginUser",loginUser);
        return "money/home";
    }

    /**
     * 未结算数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/ListUnBalanceMoney.html")
    public String weekMoneyLog(HttpServletRequest request, HttpServletResponse response) {
        MoneyLogCriteriaTO criteriaTO = new MoneyLogCriteriaTO();
        criteriaTO.setStatus((byte) 1);
        List<MoneyLog> moneyLogList = moneyLogService.getMoneyLog(criteriaTO);
        request.setAttribute("moneyLogList", moneyLogList);
        return "money/listUnBalanceMoney";
    }

    /**
     * 计算未结算账单
     * @return
     */
    @RequestMapping("/calculate.html")
    @ResponseBody
    public MoneyResult calculate() {
        List<MoneyLog> moneyLogList = new ArrayList<>();
        // 查询所有用户
        List<User> allUser = userService.getAllUser();
        for (User user : allUser) {
            MoneyLogCriteriaTO criteriaTO = new MoneyLogCriteriaTO();
            criteriaTO.setStatus((byte) 1);
            criteriaTO.setUid(user.getId());
            MoneyLog usermoneyLog = moneyLogService.getUnBalanceSumMoneyLog(user.getId());
            if (usermoneyLog == null ) {
                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUid(user.getId());
                moneyLog.setUserName(user.getName());
                moneyLog.setTotleMoney(new BigDecimal(0));
                moneyLogList.add(moneyLog);
            } else {
                moneyLogList.add(usermoneyLog);
            }
        }

        BigDecimal sum = new BigDecimal(0);

        for (MoneyLog moneyLog : moneyLogList) {
            sum = sum.add(moneyLog.getTotleMoney());
        }
        BigDecimal avg = sum.divide(BigDecimal.valueOf(allUser.size()), BigDecimal.ROUND_CEILING);
        //计算个人账单
        for (MoneyLog moneyLog : moneyLogList) {
            moneyLog.setMoney(moneyLog.getTotleMoney().subtract(avg));
        }
        //返回结果
        MoneyResult moneyResult = new MoneyResult();
        moneyResult.setTotle(sum);
        moneyResult.setAvg(avg);
        moneyResult.setMoneyLogList(moneyLogList);
        return moneyResult;
    }

    /**
     * 结算
     * @return
     */
    @RequestMapping("/balance.html")
    @ResponseBody
    public ResponseResult balance(HttpServletRequest request) {


        // 判断是否有未结算账单
        MoneyLogCriteriaTO criteria = new MoneyLogCriteriaTO();
        criteria.setStatus(MoneyLogType.ENABLE.getCode());
        List<MoneyLog> unBalancemoneyLogList = moneyLogService.getMoneyLog(criteria);
        if (unBalancemoneyLogList == null || unBalancemoneyLogList.size() == 0) {
            return ResponseResult.build(400, "当前没有未结算的账单！");
        }

        List<MoneyLog> moneyLogList = new ArrayList<>();
        // 查询所有用户
        List<User> allUser = userService.getAllUser();
        for (User user : allUser) {
            MoneyLogCriteriaTO criteriaTO = new MoneyLogCriteriaTO();
            criteriaTO.setStatus((byte) 1);
            criteriaTO.setUid(user.getId());
            MoneyLog usermoneyLog = moneyLogService.getUnBalanceSumMoneyLog(user.getId());
            if (usermoneyLog == null ) {
                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUid(user.getId());
                moneyLog.setUserName(user.getName());
                moneyLog.setTotleMoney(new BigDecimal(0));
                moneyLogList.add(moneyLog);
            } else {
                moneyLogList.add(usermoneyLog);
            }
        }

        BigDecimal sum = new BigDecimal(0);

        for (MoneyLog moneyLog : moneyLogList) {
            sum = sum.add(moneyLog.getTotleMoney());
        }
        BigDecimal avg = sum.divide(BigDecimal.valueOf(allUser.size()),BigDecimal.ROUND_CEILING);
        //计算个人账单
        for (MoneyLog moneyLog : moneyLogList) {
            moneyLog.setMoney(moneyLog.getTotleMoney().subtract(avg));
        }

        //把状态从未结算置为结算
        moneyLogService.balance();

        // 添加结算记录
        SettlementHistory settlementHistory = new SettlementHistory();
        settlementHistory.setTotleMoney(sum);
        settlementHistory.setAvgMoney(avg);
        settlementHistory.setCreateDate(new Date());
        settlementHistory.setCreateUser(getLoginUser(request).getName());
        // 详细
        StringBuilder sb = new StringBuilder();
        MoneyLog moneyLog = null;
        for (int i = 0; i <moneyLogList.size() ; i++) {
             moneyLog = moneyLogList.get(i);
            sb.append(moneyLog.getUserName()).append("：").append(moneyLog.getTotleMoney()).append("元(").append(moneyLog.getMoney()).append("元)");
             if (i < moneyLogList.size()-1) {
                 sb.append("；");
             }
        }

        settlementHistory.setDetail(sb.toString());
        settlementHistoryMapper.insert(settlementHistory);

        //TODO 发送邮件
        return new ResponseResult("结算成功！");

    }

    /**
     * 历史记账记录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/listMoneyLog.html")
    public String listMoneyLog(HttpServletRequest request, HttpServletResponse response) {
        Integer year = RequestUtil.getInteger(request, "year");
        Integer month = RequestUtil.getInteger(request, "month");

        Calendar calendar = Calendar.getInstance();
        year = (year == null || year <= 1990) ? calendar.get(Calendar.YEAR) : year;
        month = (month == null || month <1 || month > 12) ? calendar.get(Calendar.MONTH)+1 : month;

        List<MoneyLog> moneyLogByYearAndMonth = moneyLogService.getMoneyLogByYearAndMonth(year, month);
        BigDecimal moneyLogByYearAndMonthTotle = moneyLogMapper.getMoneyLogByYearAndMonthTotle(year, month);

        //个人统计
        List<MoneyLog> sumMoneyLogList = new ArrayList<>();
        for (User user : userService.getAllUser()) {
            MoneyLog sumMoneyLogByUid = moneyLogMapper.getSumMoneyLog(user.getId());
            if (sumMoneyLogByUid == null) {
                MoneyLog moneyLog = new MoneyLog();
                moneyLog.setUid(user.getId());
                moneyLog.setUserName(user.getName());
                moneyLog.setTotleMoney(new BigDecimal(0));
                sumMoneyLogList.add(moneyLog);
            } else {
                sumMoneyLogList.add(sumMoneyLogByUid);
            }
        }


        List<Integer> yearList = new ArrayList<>();
        List<Integer> monthList = new ArrayList<>();
        for (int i = 2017; i < 2020; i++) {
            yearList.add(i);
        }
        for (int i = 1; i < 13; i++) {
            monthList.add(i);
        }

        //request.setAttribute("map", map);
        request.setAttribute("list", moneyLogByYearAndMonth);
        request.setAttribute("moneyLogTotle", moneyLogByYearAndMonthTotle);
        request.setAttribute("sumMoneyLogList", sumMoneyLogList);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("yearList", yearList);
        request.setAttribute("monthList", monthList);

        return "money/listMoneyLog";
    }


    /**
     * 跳转添加记账页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/goAddMoneyLog.html")
    public String goAddMoneyLog(HttpServletRequest request, HttpServletResponse response) {
        return "money/addMoneyLog";
    }

    /**
     * 添加记账记录
     *
     * @param request
     * @param response
     * @param money
     * @param useFor
     * @return
     */
    @RequestMapping("/addMoneyLog.html")
    @ResponseBody
    public ResponseResult addMoneyLog(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam("money") BigDecimal money,
                                      @RequestParam("useFor") String useFor) {
        User loginUser = getLoginUser(request);
        MoneyLog moneyLog = new MoneyLog();
        moneyLog.setMoney(money);
        moneyLog.setUsefor(useFor);
        moneyLog.setUid(loginUser.getId());
        moneyLog.setUserName(loginUser.getName());
        moneyLog.setStatus(MoneyLogType.ENABLE.getCode());
        moneyLog.setCreateTime(new Date());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String weeks = "";
        switch (week-1) {
            case 1:
                weeks = "一";
                break;
            case 2:
                weeks = "二";
                break;
            case 3:
                weeks = "三";
                break;
            case 4:
                weeks = "四";
                break;
            case 5:
                weeks = "五";
                break;
            case 6:
                weeks = "六";
                break;
            case 7:
                weeks = "日";
            default:
                break;

        }
        moneyLog.setWeeks(weeks);

        try {
            int  rows = moneyLogService.addMoneyLog(moneyLog);
            if (rows != 1) {
                return ResponseResult.build(400, "添加失败！");
            }
            return ResponseResult.ok("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("user:" + loginUser.getName() + ",time:" +
                    new SimpleDateFormat("yyyy-MM-dd HH-mm").format(new Date()) + "add moneyLog faild", e);
            return ResponseResult.build(400, "添加失败！", e.toString());
        }
    }

    @RequestMapping("/deleteMoneyLog.html")
    @ResponseBody
    public ResponseResult deleteMoneyLog(HttpServletRequest request) {
        Integer id = RequestUtil.getInteger(request, "id");
        if (id == null) {
            return ResponseResult.build(400, "该条账单不存在！");
        }
        MoneyLog moneyLog = moneyLogMapper.selectByPrimaryKey(id);

        if (moneyLog == null) {
            return ResponseResult.build(400, "该条账单不存在！");
        }

        User loginUser = getLoginUser(request);

        if (moneyLog.getUid() != loginUser.getId()) {
            return ResponseResult.build(400, "只能删除自己的账单！");
        }

        int i = moneyLogMapper.deleteByPrimaryKey(id);

        if (i != 1) {
            return ResponseResult.build(400, "删除失败！");
        }

        return ResponseResult.ok("删除成功！");
    }

    /**
     * 编辑账单
     * @param request
     * @return
     */
    @RequestMapping("editMoneyLog.html")
    @ResponseBody
    public ResponseResult editMoneyLog(HttpServletRequest request) {
        Integer id = RequestUtil.getInteger(request, "id");
        Integer uid = RequestUtil.getInteger(request, "uid");
        String usefor = RequestUtil.getString(request, "usefor");
        String money = RequestUtil.getString(request, "money");

        if (id == null || uid == null || StringUtils.isBlank(usefor) || StringUtils.isBlank(money)) {
            return ResponseResult.build(400, "数据不完整！");
        }

        MoneyLog moneyLog = moneyLogMapper.selectByPrimaryKey(id);

        if (moneyLog == null) {
            return ResponseResult.build(400, "该账单不存在！");
        }

        moneyLog.setUsefor(usefor);
        moneyLog.setMoney(new BigDecimal(money));

        int i = moneyLogMapper.updateByPrimaryKeySelective(moneyLog);

        if (i != 1) {
            return ResponseResult.build(400, "更新失败！");
        }

        return ResponseResult.ok("更新成功！");
    }

    /**
     * 历史结算信息
     * @param request
     * @return
     */
    @RequestMapping("/settlementHistory.html")
    public String settlementHistory(HttpServletRequest request) {
        List<SettlementHistory> settlementHistoryList = settlementHistoryMapper.getAllHistory();
        request.setAttribute("settlementHistoryList",settlementHistoryList);
        return "money/settlementHistory";
    }



}
