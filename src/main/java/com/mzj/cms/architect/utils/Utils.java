package com.mzj.cms.architect.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


/*
 * @Author hzj
 * @ClassName Utils
 * @Description 工具类
 * @Date 14:29 2019/1/14
 * @Param 
 * @return 
 **/

public class Utils {
  
  private static Log log = LogFactory.getLog(Utils.class);
  
  /**
   * 字符串相关
   */
  public static class Str {

    /**
     * 判断字符串是否为空，包括null、空字符串和空格
     * 
     * @param str 字符串
     * @return 是或否
     */
    public static boolean isEmpty(String str) {
      return str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null");
    }

  }



  /**
   * 日期时间工具类
   */
  public static class DT {
    /**
     * 获取到现在这个时间
     * 
     * @return
     */
    public static String getNowDate() {

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      try {
        return sdf.format(new Date());
      } catch (Exception e) {
        log.error(e.getMessage());
      }

      return null;
    }


    /**
     * 计算日期相隔几天的日期
     * 
     * @param startTime
     * @param days
     * @return
     */
    public static Date dateAfterDays(Date startTime, int days) {
      // 计算结束时间
      Calendar cl = Calendar.getInstance();
      cl.setTime(startTime);
      cl.add(Calendar.DATE, days);
      Date endTime = cl.getTime();
      return endTime;
    }

    /**
     * 获取到现在这个时间
     * 
     * @return
     */
    public static String getNowDate2() {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try {
        return sdf.format(new Date());
      } catch (Exception e) {
        log.error(e.getMessage());
      }
      return null;
    }
    
    /**
     * 将一个日期转换为时间字符串
     * 
     * @param d
     * @return
     */
    public static String formatDate(Date d) {
      if (d == null) {
        return null;
      }

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try {
        return sdf.format(d);
      } catch (Exception e) {
        log.error(e.getMessage());
      }

      return null;
    }
    
    /**
     * 将一个时间月份转换为字符串
     * @param d
     * @return
     */
    public static String formatMonth(Date d) {
      if (d == null) {
        return null;
      }

      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
      try {
        return sdf.format(d);
      } catch (Exception e) {
        log.error(e.getMessage());
      }

      return null;
    }
    
    /**
     * 获取当前时间时间搓
     * @return
     */
    public static String getTimeMillis() {
      return new Date().getTime()+"";
    }


  }
  
  
  
  
  /**
   * double计算
   * 
   * @author
   * 
   */
  public static class DoubleUtil {
    
    //加法
    public static Double add(Double v1, Double v2) {  
      BigDecimal b1 = BigDecimal.ZERO;  
      BigDecimal b2 = BigDecimal.ZERO;  
      if(v1 != null){
        b1 = new BigDecimal(v1.toString());  
      }
      if(v2 != null){
        b2 = new BigDecimal(v2.toString());  
      }
        return new Double(b1.add(b2).doubleValue());  
     } 
    
    //减法
    public static Double sub(Double v1, Double v2) {  
      BigDecimal b1 = BigDecimal.ZERO;  
      BigDecimal b2 = BigDecimal.ZERO;  
      if(v1 != null){
        b1 = new BigDecimal(v1.toString());  
      }
      if(v2 != null){
        b2 = new BigDecimal(v2.toString());  
      }
        return new Double(b1.subtract(b2).doubleValue());  
     }
    
    //乘法 --scale精确位数
    public static Double mul(Double v1, Double v2,int scale) {  
      BigDecimal b1 = BigDecimal.ZERO;  
      BigDecimal b2 = BigDecimal.ZERO;  
      if(v1 != null){
        b1 = new BigDecimal(v1.toString());  
      }
      if(v2 != null){
        b2 = new BigDecimal(v2.toString());  
      }
      return new Double(b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue());  
     }
    
    //除法 --scale精确位数
    public static Double div(Double v1, Double v2,int scale) {  
      BigDecimal b1 = BigDecimal.ZERO;  
      BigDecimal b2 = BigDecimal.ZERO; 
      if(v1 != null){
        b1 = new BigDecimal(v1.toString());  
      }
      if(v2 != null){
        b2 = new BigDecimal(v2.toString()); 
      }
      if(b2.compareTo(BigDecimal.ZERO) != 0){
        return new Double(b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue());  
      }else{
        return new Double(0);
      }
    }
    
  }
  
  
  public static class ShortUrlUtil {

    public static String shortUrl(String url) {
      // 可以自定义生成 MD5 加密字符传前的混合 KEY
      String key = "test";
      // 要使用生成 URL 的字符
      String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
          "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
          "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
          "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
          "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
          "U", "V", "W", "X", "Y", "Z"

      };
      // 对传入网址进行 MD5 加密
      String hex = md5ByHex(key + url);

      String[] resUrl = new String[4];
      for (int i = 0; i < 4; i++) {

        // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
        String sTempSubString = hex.substring(i * 8, i * 8 + 8);

        // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用long ，则会越界
        long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
        String outChars = "";
        for (int j = 0; j < 6; j++) {
          // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
          long index = 0x0000003D & lHexLong;
          // 把取得的字符相加
          outChars += chars[(int) index];
          // 每次循环按位右移 5 位
          lHexLong = lHexLong >> 5;
        }
        // 把字符串存入对应索引的输出数组
        resUrl[i] = outChars;
      }


      Random random=new Random();
      int j=random.nextInt(4);//产成4以内随机数
      return resUrl[j];
    }
    /**
     * MD5加密(32位大写)    
     * @param src
     * @return
     */
    public static String md5ByHex(String src) {
      try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = src.getBytes();
        md.reset();
        md.update(b);
        byte[] hash = md.digest();
        String hs = "";
        String stmp = "";
        for (int i = 0; i < hash.length; i++) {
          stmp = Integer.toHexString(hash[i] & 0xFF);
          if (stmp.length() == 1)
            hs = hs + "0" + stmp;
          else {
            hs = hs + stmp;
          }
        }
        return hs.toUpperCase();
      } catch (Exception e) {
        return "";
      }
    }


  }
  
  
  
  
  
  

}
