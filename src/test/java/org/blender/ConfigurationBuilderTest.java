package org.blender;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.SubsetConfiguration;
import org.apache.commons.configuration.SystemConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ConfigurationBuilderTest {

    private ConfigurationBuilder builder;

    @Before
    public void setUp(){
        builder = new ConfigurationBuilder();
    }

    @Test
    public void shouldAddSystemConfiguration(){
        BlenderConfig config = new BlenderConfig.Builder().useSystemProperties().build();
        CompositeConfiguration compositeConfig= (CompositeConfiguration)builder.createConfiguration(config);
        assertThat(compositeConfig.getConfiguration(0)).isInstanceOf(SystemConfiguration.class);
    }

    @Test
    public void shouldAddPropertiesConfiguration(){
        BlenderConfig config = new BlenderConfig.Builder().withPropertyFiles("simple.properties", "invalid.properties").build();
        CompositeConfiguration compositeConfig= (CompositeConfiguration)builder.createConfiguration(config);
        assertThat(compositeConfig.getConfiguration(0)).isInstanceOf(PropertiesConfiguration.class);
    }

    @Test
    public void shouldAddSubsetConfigurationBasedOnPrefix(){
        BlenderConfig blenderConfig = new BlenderConfig.Builder().withPrefix("prefix").build();
        SubsetConfiguration configuration= (SubsetConfiguration)builder.createConfiguration(blenderConfig);
        assertThat(configuration).isInstanceOf(SubsetConfiguration.class);
    }

}
