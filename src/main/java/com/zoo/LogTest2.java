package com.zoo;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;


/**
 * 
 * @author yankai913@gmail.com
 * @date 2014年12月22日
 */
public class LogTest2 {
    // 定义公共的logerName，按module分
    static final String Module1_LogName = "Module1";
    static final String Module2_LogName = "Module2";


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("log4j.rootLogger", "DEBUG,file");
        properties.setProperty("log4j.appender.file", "org.apache.log4j.FileAppender");
        properties.setProperty("log4j.appender.file.layout", "org.apache.log4j.PatternLayout");
        properties.setProperty("log4j.appender.file.layout.ConversionPattern",
            "%d [%t] %-5p %c{1} %C{6} (%F:%L) - %m%n");
        properties.setProperty("log4j.appender.file.append", "true");
        properties.setProperty("log4j.appender.file.file", "logtest2.log");
        PropertyConfigurator.configure(properties);
        // 比较同名称的log，结果为true，所以log作为入参不需要传入引用，直接get即可。
        System.out.println(Module1.log.equals(ServiceImpl_1.log));
        System.out.println(Module2.log.equals(ServiceImpl_2.log));
        // 按模块搜索查看日志，关键词就是模块名称，即上面的Module1，Module2。
        // 解决包名类名完全一样，但是在不同模块的日志查询。
        Module1.println();
        Module2.println();
        ServiceImpl_1.println();
        ServiceImpl_2.println();
        System.out.println("end");
    }

    public static class Module1 {
        public static Log log = LogFactory.getLog(Module1_LogName);


        public static void println() {
            log.info("this is module1");
        }
    }

    public static class Module2 {
        public static Log log = LogFactory.getLog(Module2_LogName);


        public static void println() {
            log.info("this is module2");
        }
    }

    public static class ServiceImpl_1 {
        public static Log log = LogFactory.getLog(Module1_LogName);


        public static void println() {
            log.info("this is service");
        }
    }

    public static class ServiceImpl_2 {
        public static Log log = LogFactory.getLog(Module2_LogName);


        public static void println() {
            log.info("this is service");
        }
    }
}
