package org.blender;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.addAll;

public class BlenderConfig
{
    private List<String> propertyFiles = new ArrayList<String>();
    private boolean useSystemProperties ;
    private String prefix;


    public List<String> getPropertyFiles() {
        return propertyFiles;
    }

    public void setPropertyFiles(List<String> propertyFiles) {
        this.propertyFiles = propertyFiles;
    }

    public void addPropertyFiles(String... propertyFiles) {
        addAll(this.propertyFiles, propertyFiles);
    }

    public boolean isUseSystemProperties() {
        return useSystemProperties;
    }

    public void setUseSystemProperties(boolean useSystemProperties) {
        this.useSystemProperties = useSystemProperties;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
