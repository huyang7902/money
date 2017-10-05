package com.huyang.web.controller;

import com.huyang.common.utils.RequestUtil;
import com.huyang.common.utils.ResponseResult;
import com.huyang.dao.MyMoneyLogMapper;
import com.huyang.lib.to.MyMoneyLog;
import com.huyang.lib.to.User;
import com.huyang.service.MyMoneyLogService;
import com.huyang.service.UserService;
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
 * @Author <a href="yang.hu@downjoy.com">胡洋</a>
 * @Date 2017/10/3 16:02
 */
@Controller
@RequestMapping("/my")
public class MyMoneyController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MoneyController.class);

    @Autowired
    private MyMoneyLogService myMoneyLogService;
    @Autowired
    private MyMoneyLogMapper myMoneyLogMapper;

    @Autowired
    private UserService userService;

    /**
     * 跳转添加记账页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/goAddMyMoneyLog.html")
    public String goAddMyMoneyLog(HttpServletRequest request, HttpServletResponse response) {
        return "my/addMyMoneyLog";
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
    @RequestMapping("/addMyMoneyLog.html")
    @ResponseBody
    public ResponseResult addMyMoneyLog(HttpServletRequest request,
                                      HttpServletResponse response,
                                      @RequestParam("money") BigDecimal money,
                                      @RequestParam("useFor") String useFor) {
        User loginUser = getLoginUser(request);
        MyMoneyLog myMoneyLog = new MyMoneyLog();
        myMoneyLog.setMoney(money);
        myMoneyLog.setUsefor(useFor);
        myMoneyLog.setUid(loginUser.getId());
        myMoneyLog.setUserName(loginUser.getName());
        myMoneyLog.setCreateTime(new Date());

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
        myMoneyLog.setWeeks(weeks);

        try {
            int  rows = myMoneyLogService.addMyMoneyLog(myMoneyLog);
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

    /**
     * 历史记账记录
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/listMyMoneyLog.html")
    public String listMyMoneyLog(HttpServletRequest request, HttpServletResponse response) {
        Integer year = RequestUtil.getInteger(request, "year");
        Integer month = RequestUtil.getInteger(request, "month");

        Calendar calendar = Calendar.getInstance();
        year = (year == null || year <= 1990) ? calendar.get(Calendar.YEAR) : year;
        month = (month == null || month <1 || month > 12) ? calendar.get(Calendar.MONTH)+1 : month;
        User loginUser = getLoginUser(request);

        List<MyMoneyLog> myMoneyLogByYearAndMonth = myMoneyLogService.getMyMoneyLogByYearAndMonth(loginUser.getId(),year, month);

        BigDecimal myMoneyLogTotle = myMoneyLogService.getMyMoneyLogByYearAndMonthTotle(loginUser.getId(),year, month);

        List<Integer> yearList = new ArrayList<>();
        List<Integer> monthList = new ArrayList<>();
        for (int i = 2017; i < 2020; i++) {
            yearList.add(i);
        }
        for (int i = 1; i < 13; i++) {
            monthList.add(i);
        }

        //request.setAttribute("map", map);
        request.setAttribute("list", myMoneyLogByYearAndMonth);
        request.setAttribute("myMoneyLogTotle", myMoneyLogTotle);
        request.setAttribute("year", year);
        request.setAttribute("month", month);
        request.setAttribute("yearList", yearList);
        request.setAttribute("monthList", monthList);

        return "my/listMyMoneyLog";
    }

    @RequestMapping("/deleteMyMoneyLog.html")
    @ResponseBody
    public ResponseResult deleteMyMoneyLog(HttpServletRequest request) {
        Integer id = RequestUtil.getInteger(request, "id");
        if (id == null) {
            return ResponseResult.build(400, "该条账单不存在！");
        }
        MyMoneyLog myMoneyLog = myMoneyLogMapper.selectByPrimaryKey(id);

        if (myMoneyLog == null) {
            return ResponseResult.build(400, "该条账单不存在！");
        }

        User loginUser = getLoginUser(request);

        if (myMoneyLog.getUid() != loginUser.getId()) {
            return ResponseResult.build(400, "只能删除自己的账单！");
        }

        int i = myMoneyLogMapper.deleteByPrimaryKey(id);

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
    @RequestMapping("editMyMoneyLog.html")
    @ResponseBody
    public ResponseResult editMyMoneyLog(HttpServletRequest request) {
        Integer id = RequestUtil.getInteger(request, "id");
        Integer uid = RequestUtil.getInteger(request, "uid");
        String usefor = RequestUtil.getString(request, "usefor");
        String money = RequestUtil.getString(request, "money");

        if (id == null || uid == null || StringUtils.isBlank(usefor) || StringUtils.isBlank(money)) {
            return ResponseResult.build(400, "数据不完整！");
        }

        MyMoneyLog myMoneyLog = myMoneyLogMapper.selectByPrimaryKey(id);

        if (myMoneyLog == null) {
            return ResponseResult.build(400, "该账单不存在！");
        }

        myMoneyLog.setUsefor(usefor);
        myMoneyLog.setMoney(new BigDecimal(money));

        int i = myMoneyLogMapper.updateByPrimaryKeySelective(myMoneyLog);

        if (i != 1) {
            return ResponseResult.build(400, "更新失败！");
        }

        return ResponseResult.ok("更新成功！");
    }

}
