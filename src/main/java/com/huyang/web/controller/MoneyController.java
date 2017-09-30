package com.huyang.web.controller;


import com.huyang.common.type.MoneyLogType;
import com.huyang.common.utils.MathUtil;
import com.huyang.common.utils.RequestUtil;
import com.huyang.common.utils.ResponseResult;
import com.huyang.lib.to.MoneyLog;
import com.huyang.lib.to.MoneyResult;
import com.huyang.lib.to.User;
import com.huyang.lib.to.UserTotle;
import com.huyang.service.MoneyLogService;
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
import java.util.*;

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

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = {"/index.html", "/listMoney.html"})
    public String index(HttpServletRequest request) {
        User loginUser = getLoginUser(request);
        request.setAttribute("loginUser",loginUser);
        return "money/home";
    }

    /**
     * 本周数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/weekMoneyLog.html")
    public String weekMoneyLog(HttpServletRequest request, HttpServletResponse response) {
        List<MoneyLog> moneyLogList = moneyLogService.getweekMoneyLog();
        request.setAttribute("moneyLogList", moneyLogList);
        return "money/weekMoneyLog";
    }

    @RequestMapping("/calculate.html")
    @ResponseBody
    public MoneyResult calculate() {
        List<MoneyLog> moneyLogList = moneyLogService.getweekMoneyLog();
        List<UserTotle> moneyByUserNameList = moneyLogService.getMoneyByUserName();
        MoneyResult moneyResult = new MoneyResult();
        double sum = 0;
        for (UserTotle userTotle : moneyByUserNameList) {
            sum += MathUtil.add(sum, userTotle.getSumMoney());
            switch (userTotle.getUserName()) {
                case "胡洋":
                    moneyResult.setHy(userTotle.getSumMoney());
                    break;
                case "何晓波":
                    moneyResult.setHxb(userTotle.getSumMoney());
                    break;
                case "李丹全":
                    moneyResult.setLdq(userTotle.getSumMoney());
                    break;
                default:
                    break;
            }
        }
        moneyResult.setTotle(new BigDecimal(sum));
        moneyResult.setAvg(new BigDecimal(MathUtil.defaultDiv(3, sum)));

        return moneyResult;

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
        Map<Byte, ArrayList<MoneyLog>> map = new LinkedHashMap<>();
        ArrayList<MoneyLog> list = new ArrayList<>();
        Set<Byte> set = new HashSet<>();
        int num = 0;
        for (MoneyLog moneyLog : moneyLogByYearAndMonth) {
            byte weeks = moneyLog.getWeeks();
            if (set.add(weeks)) {
                if (list.size() != 0) {
                    map.put(list.get(0).getWeeks(),list);
                }
                list = new ArrayList<>(); // 用clear会导致map.put的list置空
                list.add(moneyLog);
            } else {
                list.add(moneyLog);
            }
            num++;
            // 最后一个元素
            if(num == moneyLogByYearAndMonth.size()) {
                map.put(list.get(0).getWeeks(),list);
            }
        }

        request.setAttribute("map", map);

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

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int weeks = calendar.get(Calendar.WEEK_OF_MONTH);
        moneyLog.setWeeks((byte)weeks);

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


}
