package org.blender;

import org.blender.bean.SimpleBean;
import org.blender.bean.ValidatorBean;
import org.blender.exception.BlendingException;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlenderTest {

    private static final String SIMPLE_PROPERTIES_FILE = "simple.properties";

    private Blender blender;

    @Before
    public void setUp() {
        blender = new Blender();
    }

    @Test
    public void shouldReturnValidProperty() {
        blender.read(SIMPLE_PROPERTIES_FILE);
        assertThat(blender.valueOf("name")).isEqualTo("name");
        assertThat(blender.valueOf("value")).isEqualTo("20");
    }

    @Test
    public void shouldSerializeSimplePropertiesToBean() {
        SimpleBean simpleBean = new SimpleBean();
        blender.mix(simpleBean, SIMPLE_PROPERTIES_FILE);
        assertThat(simpleBean.getName()).isEqualTo("name");
        assertThat(simpleBean.getValue()).isEqualTo("20");
    }

    @Test
    public void shouldValidateBeanCorrectly() {
        ValidatorBean validatorBean = new ValidatorBean();
        blender.mix(validatorBean, SIMPLE_PROPERTIES_FILE);
        assertThat(validatorBean.getValue()).isEqualTo(20);
    }

    @Test(expected = BlendingException.class)
    public void shouldThrowExceptionIfValidationFails() {
        ValidatorBean validatorBean = new ValidatorBean();
        blender.mix(validatorBean, "invalid.properties");
    }

    @Test
    public void shouldRemovePrefixBeforeSerializing() {
//        ValidatorBean validatorBean = new ValidatorBean();
//        blender.mix(validatorBean, "common.prefix", SIMPLE_PROPERTIES_FILE);
//        assertThat(validatorBean.getValue()).isEqualTo(20);
    }

    @Test
    public void shouldCLearValuesIfitReadsPropertyFilesMultipleTimes(){

    }

    @Test
    public void shouldSerializeNestedPropertiesToBean() {

    }

    @Test
    public void shouldReadFromIncludedFiles() {

    }

    @Test
    public void shouldReadFromSystemProperties() {

    }

    @Test
    public void shouldOverrideBasedOnFileOrder() {

    }

    @Test
    public void shouldOverrideBasedOnSystemProperties() {

    }

}
