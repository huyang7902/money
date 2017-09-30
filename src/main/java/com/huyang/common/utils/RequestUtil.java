package com.huyang.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * RequestUtil工具类
 *
 * @Project ids
 * @package com.huyang.common.utils
 * @author HuYang 790247179@qq.com
 * @date 2017年5月19日 下午8:18:21
 * @version V1.0 Copyright (c) 2017
 */
public class RequestUtil {

    /**
     * 判断是否为AJAX请求
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")) || request.getParameter("ajax") != null;
    }

    /**
     * 取参数的Long 值
     * @param request
     * @param paraName
     * @return
     */
    public static Long getLong(HttpServletRequest request, String paraName) {
        String tempStr=request.getParameter(paraName);
        if(StringUtils.isBlank(tempStr)) {
            return null;
        }
        try {
            return Long.parseLong(tempStr);
        } catch(Exception ex) {
        }
        return null;

    }

    /**
     * 取参数的Long 值数组
     * @param request
     * @param paraName
     * @return
     */
    public static List<Long> getLongs(HttpServletRequest request, String paraName) {
        String tempStrArray[]=request.getParameterValues(paraName);
        List<Long> valueArray=new ArrayList<Long>();
        if(null != tempStrArray) {
            for(String tempStr: tempStrArray) {
                if(StringUtils.isNotBlank(tempStr)) {
                    try {
                        valueArray.add(Long.parseLong(tempStr.trim()));
                    } catch(Exception ex) {
                    }
                }
            }
        }
        return valueArray;
    }

    /**
     * 取参数值
     * @param request
     * @param paraName
     * @return
     */
    public static String getString(HttpServletRequest request, String paraName) {
        String tempStr=request.getParameter(paraName);
        if(null == tempStr) {
            return null;
        }
        return tempStr;
    }
    
    /** 获取UTF-8的string  get参数
     * String
     */
    public static String getStringForUTF8(HttpServletRequest request, String paraName) {
        String tempStr=request.getParameter(paraName);
        if(null == tempStr) {
            return null;
        }
        try {
			tempStr = new String(tempStr.getBytes("iso8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			//
			tempStr = null;
		}
        return tempStr;
    }

    /**
     * 取参数的Integer值
     * @param request
     * @param paraName
     * @return
     */
    public static Integer getInteger(HttpServletRequest request, String paraName) {
        String tempStr=request.getParameter(paraName);
        if(StringUtils.isBlank(tempStr)) {
            return null;
        }
        try {
            return Integer.parseInt(tempStr);
        } catch(Exception ex) {
        }
        return null;
    }

    /**
     * 取参数的Double值
     * @param request
     * @param paraName
     * @return
     */
    public static Double getDouble(HttpServletRequest request, String paraName) {
        String tempStr=request.getParameter(paraName);
        if(StringUtils.isBlank(tempStr)) {
            return null;
        }
        try {
            return Double.parseDouble(tempStr);
        } catch(Exception ex) {
        }
        return null;
    }

    /**
     * 取参数的Float值
     * @param request
     * @param paraName
     * @return
     */
    public static Float getFloat(HttpServletRequest request, String paraName) {
        String tempStr=request.getParameter(paraName);
        if(StringUtils.isBlank(tempStr)) {
            return null;
        }
        try {
            return Float.parseFloat(tempStr);
        } catch(Exception ex) {
        }
        return null;
    }

    /**
     * 取参数的Date值
     * @param request
     * @param paraName
     * @param format
     * @return
     */
    public static Date getDate(HttpServletRequest request, String paraName, String format) {
        String tempStr=request.getParameter(paraName);
        if(StringUtils.isBlank(tempStr)) {
            return null;
        }
        try {
        	Date date = new SimpleDateFormat(format).parse(tempStr);
            return date;
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return null;
    }

    /**
     * 取参数的Calendar值
     * @param request
     * @param paraName
     * @param format
     * @return
     */
    public static Calendar getCalendar(HttpServletRequest request, String paraName, String format) {
        Date date=getDate(request, paraName, format);
        Calendar cal=Calendar.getInstance();
        if(date != null) {
            cal.setTime(date);
        } else {
            cal=null;
        }
        return cal;
    }

    /**
     * 取参数的boolean值
     * @param request
     * @param paraName
     * @return
     */
    public static boolean getBool(HttpServletRequest request, String paraName) {
        String tempStr=request.getParameter(paraName);
        if(tempStr == null) {
            return false;
        }
        tempStr=tempStr.trim();
        if("true".equalsIgnoreCase(tempStr) || "1".equals(tempStr)) {
            return true;
        }
        return false;
    }

    /**
     * 取参数的Boolean值
     * @param request
     * @param paraName
     * @return
     */
    public static Boolean getBoolean(HttpServletRequest request, String paraName) {
        String tempStr=request.getParameter(paraName);
        if(StringUtils.isBlank(tempStr)) {
            return null;
        }
        tempStr=tempStr.trim();
        if("true".equalsIgnoreCase(tempStr) || "1".equals(tempStr)) {
            return true;
        }
        return false;
    }

    /**
     * 取参数的Integer值列表
     * @param request
     * @param paraName
     * @return
     */
    public static List<Integer> getIntegers(HttpServletRequest request, String paraName) {
        String tempStrArray[]=request.getParameterValues(paraName);
        List<Integer> valueArray=new ArrayList<Integer>();
        if(null != tempStrArray) {
            for(String tempStr: tempStrArray) {
                if(StringUtils.isNotBlank(tempStr)) {
                    try {
                        valueArray.add(Integer.parseInt(tempStr.trim()));
                    } catch(Exception ex) {
                    }
                }
            }
        }
        return valueArray;
    }

    /**
     * 取参数的Double值列表
     * @param request
     * @param paraName
     * @return
     */
    public static List<Double> getDoubles(HttpServletRequest request, String paraName) {
        String tempStrArray[]=request.getParameterValues(paraName);
        List<Double> valueArray=new ArrayList<Double>();
        if(null != tempStrArray) {
            for(String tempStr: tempStrArray) {
                if(StringUtils.isNotBlank(tempStr)) {
                    try {
                        valueArray.add(Double.parseDouble(tempStr));
                    } catch(Exception ex) {
                    }
                }
            }
        }
        return valueArray;
    }

    /**
     * 取参数的Float值列表
     * @param request
     * @param paraName
     * @return
     */
    public static List<Float> getFloats(HttpServletRequest request, String paraName) {
        String tempStrArray[]=request.getParameterValues(paraName);
        List<Float> valueArray=new ArrayList<Float>();
        if(null != tempStrArray) {
            for(String tempStr: tempStrArray) {
                if(StringUtils.isNotBlank(tempStr)) {
                    try {
                        valueArray.add(Float.parseFloat(tempStr));
                    } catch(Exception ex) {
                    }
                }
            }
        }
        return valueArray;
    }

    /**
     * 取参数的值
     * @param request
     * @param paraName
     * @return
     */
    public static String[] getStringArray(HttpServletRequest request, String paraName) {
        return request.getParameterValues(paraName);
    }

    /**
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getAllRequestParameter(HttpServletRequest request) {
        Enumeration en=request.getParameterNames();
        String parameterName=null;
        StringBuilder buf=new StringBuilder();
        String valueArray[]=null;
        while(en.hasMoreElements()) {
            parameterName=(String)en.nextElement();
            valueArray=request.getParameterValues(parameterName);
            for(String value: valueArray) {
                buf.append("&").append(parameterName).append("=").append(value);
            }
        }
        return buf.toString();
    }

    public static String getSubIpAddr(HttpServletRequest request) {
        String ip=request.getHeader("X-Forwarded-For");
        if(null != ip && ip.length() > 0) {
            return ip;
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getRemoteAddr();
        }
        return ip;
    }

    public static String getUserIpAddr(HttpServletRequest request) {
        String ip=request.getHeader("X-Forwarded-For");
        if(null != ip && ip.length() > 0) {
            String tmpIps[]=ip.split(",");
            for(String tmpIp: tmpIps) {
                tmpIp=tmpIp.trim();
                if(null != tmpIp && tmpIp.length() > 0 && !"unknown".equalsIgnoreCase(tmpIp)) {
                    ip=tmpIp;
                    break;
                }
            }
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip=request.getRemoteAddr();
        }
        return ip;
    }

    @SuppressWarnings("unchecked")
    public static void printAllHeaders(HttpServletRequest request) {
        Enumeration en=request.getHeaderNames();
        String headerName=null;
        System.out.println("<------------------print header begin----------------------->");
        Enumeration valueArray=null;
        String value=null;
        while(en.hasMoreElements()) {
            headerName=(String)en.nextElement();
            valueArray=request.getHeaders(headerName);
            while(valueArray.hasMoreElements()) {
                value=(String)valueArray.nextElement();
                System.out.println(headerName + "=" + value);
            }
        }
        System.out.println("<------------------print header end------------------------->");
    }

    public static String getIpAddr(HttpServletRequest request) {
        String unknown = "unknown";
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if(ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if(null != ip && ip.length() != 0) {
            String[] tmpIps = ip.split(",");
            String[] arr$ = tmpIps;
            int len$ = tmpIps.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String tmpIp = arr$[i$];
                if(null != tmpIp) {
                    tmpIp = tmpIp.trim();
                    if(tmpIp.length() != 0 && !unknown.equalsIgnoreCase(tmpIp) && tmpIp.indexOf("127.0.0.1") == -1 && !tmpIp.startsWith("192.168.") && !tmpIp.startsWith("10.") && !tmpIp.startsWith("118.144.66") && !tmpIp.startsWith("118.144.65") && !tmpIp.startsWith("211.147.5")) {
                        return tmpIp;
                    }
                }
            }
        }

        return request.getRemoteAddr();
    }
}
