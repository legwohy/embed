package com.cobra.util.log;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * logback日志输出脱敏
 */
public class FilterLogSensitive extends MessageConverter {

    /**
     * 日志脱敏开关
     */
    private static String converterCanRun = "true";
    /**
     * 日志脱敏关键字
     */
    private static String sensitiveDataKeys = "idCardNum,mobile,name,bankCardNo,cardNo,mobileNo,personName,address,operName,idNumber,telephone,linkMan,linkPhone";
    //正则匹配
    private static Pattern pattern = Pattern.compile("[0-9a-zA-Z]");
    @Override
    public String convert(ILoggingEvent event) {
        // 获取原始日志
        String oriLogMsg = event.getFormattedMessage();
        // 获取脱敏后的日志
        String afterLogMsg = filterSensitiveInfo(oriLogMsg);
        return afterLogMsg;
    }

    /**
     * 对原始日志进行脱敏
     * @param oriMsg 原始日志
     * @return 脱敏后的日志
     */
    public String filterSensitiveInfo(String oriMsg) {
        //手机号
        String tempMsg = oriMsg;
        if("true".equals(converterCanRun)){
            // 处理字符串
            if(sensitiveDataKeys != null && sensitiveDataKeys.length() > 0){
                String[] keysArray = sensitiveDataKeys.split(",");
                for(String key: keysArray){
                    int index= -1;
                    do{
                        index = tempMsg.indexOf(key, index+1);
                        if(index != -1){
                            // 判断key是否为单词字符
                            if(isWordChar(tempMsg, key, index)){
                                continue;
                            }
                            // 寻找值的开始位置
                            int valueStart = getValueStartIndex(tempMsg, index + key.length());

                            // 查找值的结束位置（逗号，分号）........................
                            int valueEnd = getValuEndEIndex(tempMsg, valueStart);

                            // 对获取的值进行脱敏
                            String subStr = tempMsg.substring(valueStart, valueEnd);
                            subStr = filterSensitive(subStr, key);
                            tempMsg = tempMsg.substring(0,valueStart) + subStr + tempMsg.substring(valueEnd);
                        }
                    }while(index != -1);
                }
            }
        }
        return tempMsg;

        }
    private boolean isWordChar(String msg, String key, int index) {
        if (index != 0) { // 判断key前面一个字符
            char preCh = msg.charAt(index - 1);
            Matcher match = pattern.matcher(preCh + "");
            if (match.matches()) {
                return true;
            }
        }
        // 判断key后面一个字符
        char nextCh = msg.charAt(index + key.length());
        Matcher match = pattern.matcher(nextCh + "");
        if (match.matches()) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        String tempMsg = "{sign=f88898b2677e62f1ad54b9e330c0a27e, idcard=130333198901192762, realname=%E5%BE%90%E5%BD%A6%E5%A8%9C, key=c5d34d4c3c71cc45c88f32b4f13da887, mobile=13210141605, bankcard=6226430106137525}";
        String tempMsg1 = "{\"reason\":\"成功 \",\"result\":{\"jobid\":\"JH2131171027170837443588J6\",\"realname\":\"李哪娜\",\"bankcard\":\"6226430106137525\",\"idcard\":\"130333198901192762\",\"mobile\":\"13210141605\",\"res\":\"1\",\"message\":\"验证成功\"},\"error_code\":0}";
        String temMsg2="[CoreTop(credit=CoreCredit\n" +
                "(header=CoreHeader (version=0100, testFlag=0, activityCode=1005, actionCode=0, reqSys=xingyexiaojin001, reqChannel=0, \n" +
                "reqTransID=68A142B6FC474F7B9E10ADA98717C6DB, reqDate=Wed Apr 17 00:00:00 CST 2019,\n" +
                " reqDateTime=Wed Apr 17 09:06:55 CST 2019, rcvSys=null, rcvTransID=null, rcvDate=null, rcvDateTime=null,\n" +
                " rspCode=null, rspDesc=null, authorizationCode=10201904172019041709065520190417), \n" +
                " body={name=杨波, idCardNum=511524199202270317, mobile=15198278016}, mac=ce3ef343765012a22bec01771c5ac349),\n" +
                " clientIp=220.248.31.50, traceLogId=10190417090655000001148106366799, \n" +
                " serviceCode=threeElementsCheckAllDetail, rcvTransID=10190417090655000002148106753298,\n" +
                " protocol=http, frontHostName=app-148-106)]";
        String str11="[Response [success=true, result={detailByName=[DetailByName(eopProductSeq=10190417100132000007165152785183, orgId=null, cisId=null, remark=null, collectTime=Wed Apr 17 10:01:32 CST 2019, keyWord=东莞市凤岗荣鑫手机配件店, name=东莞市凤岗荣鑫手机配件店, econKind=个体户, registCapi=, address=东莞市凤岗镇官井头村中兴路综合楼一楼B1, regNo=, scope=零售：手机配件、电脑配件；商务代理服务。(依法须经批准的项目，经相关部门批准后方可开展经营活动)〓, termStart=, termEnd=, belongOrg=东莞市工商行政管理局, operName=洪如妹, checkDate=2017-10-26, startDate=2015-09-30, endDate=null, status=存续, partners=[], employees=[], branches=[], changerecords=[ChangeRecords(changeItem=经营范围, changeDate=2016-12-09, beforeContent=零售：手机配件、电脑配件。, afterContent=零售：手机配件、电脑配件；商务代理服务。)], abnormalItems=[], reserved1=null, reserved2=null, reserved3=null)]}";
        String str="[{\"credit\":{\"header\":{\"version\":\"0100\",\"testFlag\":\"5\",\"activityCode\":\"1005\",\"businessType\":\"\",\"actionCode\":\"0\",\"reqSys\":\"no-security-sys-code\",\"reqChannel\":\"\",\"reqTransID\":\"20190411003\",\"reqDate\":\"20150320\",\"reqDateTime\":\"20150320112600\",\"rcvSys\":\"000000000001\",\"rcvTransID\":\"20180320001\",\"rcvDate\":\"20150320\",\"rcvDateTime\":\"20150320112600\",\"authorizationCode\":\"10151112140001000001542155961605\"},\"body\":{\"mobile\":\"15256076070\",\"name\":\"季学明\",\"idCardNum\":\"342623198709308533\"},\"mac\":\"adjfhuefheiwfh2iu3ehi1iu2y312i3\"}}]";
        FilterLogSensitive sc = new FilterLogSensitive();
//        System.out.println(sc.filterSensitiveInfo(tempMsg));
//        System.out.println(sc.filterSensitiveInfo(tempMsg1));
//        System.out.println(sc.filterSensitiveInfo(temMsg2));
//        System.out.println(sc.filterSensitiveInfo(str11));
        System.out.println(sc.filterSensitiveInfo(str));
    }
    /**
     * 获取value值的开始位置
     * @param msg 要查找的字符串
     * @param valueStart 查找的开始位置
     * @return
     */
    private int getValueStartIndex(String msg, int valueStart ){
        // 寻找值的开始位置.................................
        do{
            char ch = msg.charAt(valueStart);
            if(ch == ':' || ch == '='){ // key与 value的分隔符
                valueStart ++;
                ch = msg.charAt(valueStart);
                if(ch == '"'){
                    valueStart ++;
                }
                break;    // 找到值的开始位置
            }else{
                valueStart ++;
            }
        }while(true);
        return valueStart;
    }

    /**
     * 获取value值的结束位置
     * @return
     */
    private int getValuEndEIndex(String msg,int valueEnd){
        do{
            if(valueEnd == msg.length()){
                break;
            }
            char ch = msg.charAt(valueEnd);

            if(ch == '"'){ // 引号时，判断下一个值是结束，分号还是逗号决定是否为值的结束
                if(valueEnd+1 == msg.length()){
                    break;
                }
                char nextCh = msg.charAt(valueEnd+1);
                if(nextCh ==';' || nextCh == ','){
                    // 去掉前面的 \  处理这种形式的数据
                    while(valueEnd>0 ){
                        char preCh = msg.charAt(valueEnd-1);
                        if(preCh != '\\'){
                            break;
                        }
                        valueEnd--;
                    }
                    break;
                }else{
                    valueEnd ++;
                }
            }else if (ch ==';' || ch == ',' || ch == '}'){
                break;
            }else{
                valueEnd ++;
            }

        }while(true);
        return valueEnd;
    }

    private String filterSensitive(String submsg, String key){
        // idcard：身份证号, realname：姓名, bankcard：银行卡号, mobile：手机号
        if("idCardNum".equals(key)||"idNumber".equals(key)){
            return SensitiveInfoUtils.idCardNum(submsg);
        }
        if("name".equals(key)||"personName".equals(key)||"linkMan".equals(key)){
            return SensitiveInfoUtils.chineseName(submsg);
        }
        if("mobile".equals(key)||"mobileNo".equals(key)||"telephone".equals(key)||"linkPhone".equals(key)){
            return SensitiveInfoUtils.mobilePhone(submsg);
        }
        if ("bankCardNo".equals(key)||"cardNo".equals(key)){
            return SensitiveInfoUtils.bankCard(submsg);
        }
        if ("address".equals(key)){
            return SensitiveInfoUtils.addressInfo(submsg);
        }
        if ("operName".equals(key)){
            return SensitiveInfoUtils.chineseName(submsg);
        }
        return "";
    }
}

