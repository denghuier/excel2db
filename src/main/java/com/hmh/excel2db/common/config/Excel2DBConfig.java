package com.hmh.excel2db.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取项目相关配置
 * 
 * @author ruoyi
 */
@Component
@ConfigurationProperties(prefix = "excel2db")
public class Excel2DBConfig
{
    /** 项目名称 */
    private String name;

    /** 版本 */
    private String version;

    /** 版权年份 */
    private String copyrightYear;

    /** 上传路径 */
    private static String filePath;





    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    public String getCopyrightYear()
    {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear)
    {
        this.copyrightYear = copyrightYear;
    }

    public static String getFilePath() {
        return filePath;
    }

    public  void setFilePath(String filePath) {
        Excel2DBConfig.filePath = filePath;
    }



    /**
     * 获取导入上传路径
     */
    public static String getImportPath()
    {
        return getFilePath() + "/import";
    }

    /**
     * 获取头像上传路径
     */
    public static String getAvatarPath()
    {
        return getFilePath() + "/avatar";
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getFilePath() + "/download/";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getFilePath() + "/upload";
    }
}
