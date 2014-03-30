package org.blender;

import org.junit.Test;

public class BlenderConfigTest {


    @Test
    public void shouldLoadConfigWithSinglePropertyFile(){
        new BlenderConfig().withPropertyFile("simple.properties").build();
    }
}
