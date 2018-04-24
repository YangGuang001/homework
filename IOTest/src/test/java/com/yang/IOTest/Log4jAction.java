package com.yang.IOTest;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by yz on 2018/4/21.
 */
public class Log4jAction {
    private static final String SUCCESS = "success";

    Log4jConfig config = new Log4jConfig();
    //日志对象列表
    private List<Logger> loggers;

    //日志对象名称
    private String name;
    //日志对象级别
    private String level;

    /**
     * 日志列表*/
    public String getLogList()
    {
        //日志列表
        loggers = new ArrayList<Logger>();
        //获取根日志
        Logger rootLogger = Logger.getRootLogger();
        loggers.add(rootLogger);

        //获取当前所有的日志对象
        Enumeration<Logger> enumer = (Enumeration<Logger>)rootLogger.getLoggerRepository().getCurrentLoggers();
        while(enumer.hasMoreElements())
        {
            Logger logger = enumer.nextElement();
            if((null != level) && (!"".equals(level)) && (!"ALL".equals(level))
                    && (null != logger.getLevel()) && (!logger.getLevel().toString().equals(level)))
            {
                continue;
            }

            if((null != name) && (!"".equals(name)) && (!logger.getName().contains(name)))
            {
                continue;
            }
            loggers.add(logger);
        }

        return SUCCESS;
    }

    /**
     * 更新日志级别
     * @return
     */
    public String editLoggerLevel()
    {
        if(Level.INFO.toString().equals(level))
        {
            config.enableInfo(name);
        }

        else if(Level.ERROR.toString().equals(level))
        {
            config.enableError(name);
        }
        else if(Level.DEBUG.toString().equals(level))
        {
            config.enableDebug(name);
        }
        else if(Level.WARN.toString().equals(level))
        {
            config.enableWarn(name);
        }
        else if(Level.OFF.toString().equals(level))
        {
            config.enableOff(name);
        }

        return SUCCESS;
    }

    /**
     * 新增日志对象
     * @return
     * @throws IOException
     */
    public String addNewLogger() throws IOException
    {
        Logger log = ("".equals(name) ?
                Logger.getRootLogger() : Logger.getLogger(name));
        log.setLevel(Level.toLevel(level, Level.DEBUG));

        return null;
    }

    /**
     * @return loggers
     */
    public List<Logger> getLoggers() {
        return loggers;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }
}
