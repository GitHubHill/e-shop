package com.hill.eshop.util;

import com.hill.eshop.constant.IGConstant;
import com.hill.eshop.constant.PropName;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class PropUtils {

    private Map<String, Configuration> configMap = new HashMap<>();

    public PropUtils() throws ConfigurationException {
        for (PropName propName : PropName.values()) {
            Parameters params = new Parameters();
            FileBasedConfigurationBuilder<FileBasedConfiguration> builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class).configure(params.properties()
                    .setFileName(propName.getFileName()).setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
            configMap.put(propName.getFileName(), builder.getConfiguration());
        }
    }

    /**
     * 取数据回传空字符串
     * 
     * @param propName
     * @param key
     * @return
     */
    public String getProp(PropName propName, String key) {
        return getConfig(propName).getString(key, IGConstant.EMPTY);
    }

    /**
     * 取数据时回传0
     * 
     * @param propName
     * @param key
     * @return
     */
    public Integer getIntProp(PropName propName, String key) {
        return Integer.valueOf(getConfig(propName).getString(key, "0"));
    }

    /**
     * 取数据回传0
     * 
     * @param propName
     * @param key
     * @return
     */
    public Long getLongProp(PropName propName, String key) {
        return Long.valueOf(getConfig(propName).getString(key, "0"));
    }

    /**
     * 取数据回传false
     * 
     * @param propName
     * @param key
     * @return
     */
    public Boolean getBoolProp(PropName propName, String key) {
        return Boolean.valueOf(getConfig(propName).getString(key, "false"));
    }

    public String[] getProps(PropName propName, String key) {
        return getConfig(propName).getStringArray(key);
    }

    public Configuration getConfig(PropName propName) {
        return configMap.get(propName.getFileName());
    }

    public Map<String, Configuration> getConfigMap() {
        return configMap;
    }
}
