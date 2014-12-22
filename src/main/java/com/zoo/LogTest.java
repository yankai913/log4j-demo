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
public class LogTest {
    static final Log log = LogFactory.getLog(LogTest.class);


    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("log4j.rootLogger", "DEBUG,stdout");
        properties.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
        properties.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
        properties.setProperty("log4j.appender.stdout.layout.ConversionPattern",
            "%d [%t] %-5p %C{6} (%F:%L) - %m%n");
        // log4j的继承关系是"."
        // log --> com.zoo.LogTest
        // log2(父log) --> com.zoo
        // log3(父log2) --> com
        // 以此类推
        // 子logger的继承关系表现是，把父logger的appender加入子logger中。
        // 以下这句话定义了上述的log2(即父log)，导致日志打印2次。
        properties.setProperty("log4j.logger.com.zoo", "INFO, stdout");
        // 以下这句话定义com.zoo包的logger(即log2)的appender不被继承，日志打印只有一条。
        properties.setProperty("log4j.additivity.com.zoo", "false");
        PropertyConfigurator.configure(properties);
        System.out.println(log);
        log.info("Hello World!");
        System.out.println("end");
    }
}
