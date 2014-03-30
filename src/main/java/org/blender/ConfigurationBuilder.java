package org.blender;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.blender.exception.BlendingException;

public class ConfigurationBuilder {

    private final BlenderConfig blenderConfig;

    public ConfigurationBuilder(){
        blenderConfig = new BlenderConfig();
    }

    public ConfigurationBuilder withPropertyFile(String file) {
        blenderConfig.addPropertyFiles(file);
        return this;
    }

    public ConfigurationBuilder useSystemProperties(boolean useSystemProperties){
        blenderConfig.setUseSystemProperties(useSystemProperties);
        return this;
    }

    public ConfigurationBuilder withPrefix(String prefix){
        blenderConfig.setPrefix(prefix);
        return this;
    }

    public Configuration build() {
        CompositeConfiguration cc = new CompositeConfiguration();
        if(blenderConfig.isUseSystemProperties()){
            cc.addConfiguration(new SystemConfiguration());
        }

        for(String file: blenderConfig.getPropertyFiles()){
            PropertiesConfiguration config = createPropertiesConfiguration(file);
            cc.addConfiguration(config);
        }
        return cc;
    }

    private PropertiesConfiguration createPropertiesConfiguration(String file) {
        PropertiesConfiguration config = null;
        try {
            config = new PropertiesConfiguration(file);
        } catch (ConfigurationException e) {
            throw new BlendingException(e);
        }
        return config;
    }

}
