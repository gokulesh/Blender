package org.blender;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlenderConfigTest {


    @Test
    public void shouldSetPrefix(){
        BlenderConfig config = new BlenderConfig.Builder().withPrefix("p").build();
        assertThat(config.prefix()).isEqualTo("p");
    }

    @Test
    public void shouldSetUseSystemproperties(){
        BlenderConfig config = new BlenderConfig.Builder().useSystemProperties(true).build();
        assertThat(config.isUseSystemProperties()).isTrue();
    }

    @Test
    public void shouldAddPropertyFiles(){
        BlenderConfig config = new BlenderConfig.Builder().withPropertyFiles("simple.properties").build();
        assertThat(config.getPropertyFiles()).contains("simple.properties");

    }
}
