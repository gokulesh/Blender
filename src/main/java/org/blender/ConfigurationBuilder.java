package org.blender;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.apache.commons.lang.StringUtils;
import org.blender.exception.BlenderException;

public class ConfigurationBuilder {

    public Configuration createConfiguration(BlenderConfig blenderConfig) {
        CompositeConfiguration cc = new CompositeConfiguration();
        addSystemConfiguration(blenderConfig, cc);
        addAllPropertiesConfiguration(blenderConfig, cc);
        return createSubsetConfigurationBasedOnPrefix(blenderConfig, cc);

    }

    private Configuration createSubsetConfigurationBasedOnPrefix(BlenderConfig blenderConfig, CompositeConfiguration compositeConfiguration) {
        if(StringUtils.isNotBlank(blenderConfig.prefix())){
            return compositeConfiguration.subset(blenderConfig.prefix());
        }
        return compositeConfiguration;
    }

    private void addSystemConfiguration(BlenderConfig blenderConfig, CompositeConfiguration cc) {
        if(blenderConfig.isUseSystemProperties()){
            cc.addConfiguration(new SystemConfiguration());
        }
    }

    private void addAllPropertiesConfiguration(BlenderConfig blenderConfig, CompositeConfiguration cc) {
        for(String file: blenderConfig.getPropertyFiles()){
            PropertiesConfiguration config = createPropertiesConfiguration(file);
            cc.addConfiguration(config);
        }
    }

    private PropertiesConfiguration createPropertiesConfiguration(String file) {
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(file);
        } catch (ConfigurationException e) {
            throw new BlenderException(e);
        }
        return config;
    }
}
