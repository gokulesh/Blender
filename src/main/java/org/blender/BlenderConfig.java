package org.blender;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class BlenderConfig
{
    private List<String> propertyFiles = new ArrayList<String>();
    private boolean useSystemProperties = false;
    private String prefix = "";

    public List<String> getPropertyFiles() {
        return propertyFiles;
    }

    public boolean isUseSystemProperties() {
        return useSystemProperties;
    }

    public String prefix() {
        return prefix;
    }


    static class Builder {
        private BlenderConfig config = new BlenderConfig();

        public Builder withPropertyFiles(String... files) {
            addAll(config.propertyFiles, files);
            return this;
        }

        public Builder useSystemProperties(boolean useSystemProperties){
            config.useSystemProperties = useSystemProperties;
            return this;
        }

        public Builder withPrefix(String prefix){
            config.prefix = prefix;
            return this;
        }

        public BlenderConfig build(){
            return config;
        }
    }
}
