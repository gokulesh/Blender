package org.blender;

import static java.util.Collections.addAll;

import java.util.ArrayList;
import java.util.List;

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


    public static class Builder {
        private BlenderConfig config = new BlenderConfig();

        public Builder withPropertyFiles(String... files) {
            addAll(config.propertyFiles, files);
            return this;
        }

        public Builder useSystemProperties(){
            config.useSystemProperties = true;
            return this;
        }

        public Builder ignoreSystemProperties(){
            config.useSystemProperties = false;
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
