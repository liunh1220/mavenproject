package com.ssm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.log4j.Logger;


/**
 * 配置文件帮助类.
 * 
 */
public final class ConfigurationHelper {
    /**
     * logger.
     */
    private static final Logger logger = Logger
                            .getLogger(ConfigurationHelper.class);
    /**
     * xml文件后缀名.
     */
    private static final String XML = ".xml";
    /**
     * properties文件后缀名.
     */
    private static final String PROPERTIES = ".properties";
    /**
     * HTTP协议前缀.
     */
    private static final String HTTP = "HTTP://";

    /**
     * 开发配置文件根目录.
     */
    private static String basePath = null;
    
    /**
     * 部署配置文件根目录.
     */
    private static String deployBasePath = null;

    /**
     * 私有构造涵数，防止被构造.
     */
    private ConfigurationHelper() {

    }

    /**
     * 设置开发配置文件根目录.
     * @param basePath 根目录.
     */
    public static void setBasePath(String basePath) {
        ConfigurationHelper.basePath = basePath;                
    }
    
    /**
     * 得到开发配置文件根目录.
     * @return 根目录.
     */
    public static String getBasePath() {
    	if(basePath == null){
    		return System.getProperty("user.dir");
    	}
        return basePath;                
    }

    /**
     * 根据配置文件名得到配置文件流.
     * 
     * @param configurationFileName
     *            配置文件名
     * @return 配置文件流
     */
    public static InputStream readConfiguration(String configurationFileName) {
        if (configurationFileName == null) {
            return null;
        }
        // 得到配置文件全路径名（如果设置了basePath，fileName=basePath+configurationFileName）
        String fileName = getFullFileName(configurationFileName);
     
        // 检查配置文件是否URL文件
        boolean isUrl = isUrlFile(fileName);
        
        if (isUrl) {
            URL url;
            try {
                url = new URL(fileName);
                return url.openStream();
            } catch (MalformedURLException e) {
                logger.error("打开URL配置文件失败，URL=" + fileName);
            } catch (IOException e) {
                logger.error("打开URL配置文件失败，URL=" + fileName);
            }
            return null;
        } else {
            try {
                File file = new File(fileName);
                FileInputStream fileInputStream = new FileInputStream(file);
                return fileInputStream;
            } catch (FileNotFoundException e) {
                logger.error("打开配置文件失败，filename=" + fileName);
            }
            return null;
        }
    }
    
    /**
     * 根据开发配置文件名得到开发配置对象.
     * 
     * @param configurationFileName
     *            配置文件名
     * @return 配置对象
     */
    public static Configuration getConfiguration(String configurationFileName) {
        return getConfiguration(configurationFileName, 0);
    }

    /**
     * 根据开发配置文件名得到开发配置对象.
     * 
     * @param configurationFileName
     *            配置文件名
     * @param refreshDelay
     *            刷新时间(微秒单位)
     * @return 配置对象
     */
    public static Configuration getConfiguration(String configurationFileName,
            long refreshDelay) {
        if (configurationFileName == null) {
            return null;
        }
        
        // 得到配置文件全路径名（如果设置了basePath，fileName=basePath+configurationFileName）
        String fileName = getFullFileName(configurationFileName);
        
        // 检查配置文件类型
        boolean isXmlFile = false;
        if (configurationFileName.lastIndexOf(XML) > 0) {
            isXmlFile = true;
        } else if (configurationFileName.lastIndexOf(PROPERTIES) > 0) {
            isXmlFile = false;
        } else {
            return null;
        }

        // 检查配置文件是否URL文件
        boolean isUrl = isUrlFile(fileName);

        if (isXmlFile) {
            XMLConfiguration xmlConfiguration = null;
            if (isUrl) {
                try {
                    xmlConfiguration = new XMLConfiguration(new URL(fileName));
                } catch (ConfigurationException e) {
                    logger.error("打开URL配置文件失败，URL=" + fileName);
                } catch (MalformedURLException e) {
                    logger.error("打开URL配置文件失败，URL=" + fileName);
                }
            } else {
                try {
                    xmlConfiguration = new XMLConfiguration(fileName);
                } catch (ConfigurationException e) {
                    logger.error("打开配置文件失败，filename=" + fileName);
                }
            }

            // 如果需要定时刷新,设置刷新策略
            if (refreshDelay > 0) {
                FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
                fileChangedReloadingStrategy.setConfiguration(xmlConfiguration);
                fileChangedReloadingStrategy.setRefreshDelay(refreshDelay);
                xmlConfiguration.setReloadingStrategy(fileChangedReloadingStrategy);
            }
            return xmlConfiguration;
        } else {
            PropertiesConfiguration propertiesConfiguration = null;
            if (isUrl) {
                try {
                    propertiesConfiguration = new PropertiesConfiguration(new URL(fileName));
                } catch (ConfigurationException e) {
                    logger.error("打开URL配置文件失败，URL=" + fileName);
                } catch (MalformedURLException e) {
                    logger.error("打开URL配置文件失败，URL=" + fileName);
                }
            } else {
                try {
                    propertiesConfiguration = new PropertiesConfiguration(
                            fileName);
                } catch (ConfigurationException e) {
                    logger.error("打开配置文件失败，filename=" + fileName);
                }
            }

            // 如果需要定时刷新,设置刷新策略
            if (refreshDelay > 0) {
                FileChangedReloadingStrategy reloadingStrategy = null;
               
                reloadingStrategy = new FileChangedReloadingStrategy();
                reloadingStrategy.setConfiguration(propertiesConfiguration);
                reloadingStrategy.setRefreshDelay(refreshDelay);
                propertiesConfiguration.setReloadingStrategy(reloadingStrategy);

            }
            return propertiesConfiguration;
        }

    }
    
    /**
     * 设置部署配置文件根目录.
     * @param basePath 根目录.
     */
    public static void setDeployBasePath(String deployBasePath) {
    	ConfigurationHelper.deployBasePath = deployBasePath;                
    }
    
    /**
     * 得到部署配置文件根目录.
     * @return 根目录.
     */
    public static String getDeployBasePath() {
    	if(deployBasePath == null){
    		return System.getProperty("user.dir");
    	}
        return deployBasePath;
    }
    
    /**
     * 根据部署配置文件名得到配置文件流.
     * 
     * @param configurationFileName
     *            配置文件名
     * @return 配置文件流
     */
    public static InputStream readDeployConfiguration(String configurationFileName) {
        if (configurationFileName == null) {
            return null;
        }
        // 得到配置文件全路径名（如果设置了deployBasePath，fileName=deployBasePath+configurationFileName）
        String fileName = getDeployFullFileName(configurationFileName);
     
        // 检查配置文件是否URL文件
        boolean isUrl = isUrlFile(fileName);
        
        if (isUrl) {
            URL url;
            try {
                url = new URL(fileName);
                return url.openStream();
            } catch (MalformedURLException e) {
                logger.error("打开URL配置文件失败，URL=" + fileName);
            } catch (IOException e) {
                logger.error("打开URL配置文件失败，URL=" + fileName);
            }
            return null;
        } else {
            try {
                File file = new File(fileName);
                FileInputStream fileInputStream = new FileInputStream(file);
                return fileInputStream;
            } catch (FileNotFoundException e) {
                logger.error("打开配置文件失败，filename=" + fileName);
            }
            return null;
        }
    }

    /**
     * 根据部署配置文件名得到配置对象.
     * 
     * @param configurationFileName
     *            配置文件名
     * @return 配置对象
     */
    public static Configuration getDeployConfiguration(String configurationFileName) {
    	return getDeployConfiguration(configurationFileName, 0);
    }
    
    /**
     * 根据部署配置文件名得到配置对象.
     * 
     * @param configurationFileName
     *            配置文件名
     * @param refreshDelay
     *            刷新时间(微秒单位)
     * @return 配置对象
     */
    public static Configuration getDeployConfiguration(String configurationFileName,
    		long refreshDelay) {
    	if (configurationFileName == null) {
            return null;
        }
        
        // 得到配置文件全路径名（如果设置了deployBasePath，fileName=deployBasePath+configurationFileName）
        String fileName = getDeployFullFileName(configurationFileName);
        
        // 检查配置文件类型
        boolean isXmlFile = false;
        if (configurationFileName.lastIndexOf(XML) > 0) {
            isXmlFile = true;
        } else if (configurationFileName.lastIndexOf(PROPERTIES) > 0) {
            isXmlFile = false;
        } else {
            return null;
        }

        // 检查配置文件是否URL文件
        boolean isUrl = isUrlFile(fileName);

        if (isXmlFile) {
            XMLConfiguration xmlConfiguration = null;
            if (isUrl) {
                try {
                    xmlConfiguration = new XMLConfiguration(new URL(fileName));
                } catch (ConfigurationException e) {
                    logger.error("打开URL配置文件失败，URL=" + fileName);
                } catch (MalformedURLException e) {
                    logger.error("打开URL配置文件失败，URL=" + fileName);
                }
            } else {
                try {
                    xmlConfiguration = new XMLConfiguration(fileName);
                } catch (ConfigurationException e) {
                    logger.error("打开配置文件失败，filename=" + fileName);
                }
            }

            // 如果需要定时刷新,设置刷新策略
            if (refreshDelay > 0) {
                FileChangedReloadingStrategy fileChangedReloadingStrategy = new FileChangedReloadingStrategy();
                fileChangedReloadingStrategy.setConfiguration(xmlConfiguration);
                fileChangedReloadingStrategy.setRefreshDelay(refreshDelay);
                xmlConfiguration.setReloadingStrategy(fileChangedReloadingStrategy);
            }
            return xmlConfiguration;
        } else {
            PropertiesConfiguration propertiesConfiguration = null;
            if (isUrl) {
                try {
                    propertiesConfiguration = new PropertiesConfiguration(new URL(fileName));
                } catch (ConfigurationException e) {
                    logger.error("打开URL配置文件失败，URL=" + fileName);
                } catch (MalformedURLException e) {
                    logger.error("打开URL配置文件失败，URL=" + fileName);
                }
            } else {
                try {
                    propertiesConfiguration = new PropertiesConfiguration(
                            fileName);
                } catch (ConfigurationException e) {
                    logger.error("打开配置文件失败，filename=" + fileName);
                }
            }

            // 如果需要定时刷新,设置刷新策略
            if (refreshDelay > 0) {
                FileChangedReloadingStrategy reloadingStrategy = null;
           
                reloadingStrategy = new FileChangedReloadingStrategy();
                reloadingStrategy.setConfiguration(propertiesConfiguration);
                reloadingStrategy.setRefreshDelay(refreshDelay);
                propertiesConfiguration.setReloadingStrategy(reloadingStrategy);

            }
            return propertiesConfiguration;
        }

    }

    /**
     * 得到开发配置文件全路径名.
     * @param fileName 配置文件名
     * @return 全路径名
     */
    public static String getFullFileName(String fileName) {
        if (basePath != null) {
            return basePath + File.separator + fileName;
        }
        return fileName;
    }
    
    /**
     * 得到部署配置文件全路径名.
     * @param fileName 配置文件名
     * @return 全路径名
     */
    public static String getDeployFullFileName(String fileName) {
        if (deployBasePath != null) {
            return deployBasePath + File.separator + fileName;
        }
        return fileName;
    }
    
    /**
     * 是否URL配置文件.
     * @param fileName 配置文件名
     * @return 全路径名
     */
    private static boolean isUrlFile(String fileName) {
        if (fileName.toUpperCase().startsWith(HTTP)) {
            return true;
        }
        return false;
    }
}
