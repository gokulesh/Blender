package org.blender;

import org.blender.bean.Person;
import org.blender.bean.SimpleBean;
import org.blender.bean.ValidatorBean;
import org.blender.exception.BlenderException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BlenderTest {

    private static final String SIMPLE_PROPERTIES_FILE = "simple.properties";
    private static final String SYSTEM_KEY = "system-name";
    private static final String SYSTEM_VALUE = "system-value";

    private Blender blender;

    @Before
    public void setUp() {
        BlenderConfig simplePropertiesConfig = new BlenderConfig.Builder().withPropertyFiles(SIMPLE_PROPERTIES_FILE).build();
        blender = new Blender(simplePropertiesConfig);
        System.setProperty(SYSTEM_KEY, SYSTEM_VALUE);
    }


    @After
    public void tearDown() {
        System.clearProperty(SYSTEM_KEY);
    }

    @Test
    public void shouldReturnValidProperty() {
        assertThat(blender.valueOf("name")).isEqualTo("name");
        assertThat(blender.valueOf("value")).isEqualTo("20");
    }

    @Test
    public void shouldSerializeSimplePropertiesToBean() {
        SimpleBean simpleBean = new SimpleBean();
        blender.fill(simpleBean);

        assertThat(simpleBean.getName()).isEqualTo("name");
        assertThat(simpleBean.getValue()).isEqualTo("20");
    }

    @Test
    public void shouldValidateBeanCorrectly() {
        ValidatorBean validatorBean = new ValidatorBean();
        blender.fill(validatorBean);

        assertThat(validatorBean.getValue()).isEqualTo(20);
    }

    @Test(expected = BlenderException.class)
    public void shouldThrowExceptionIfValidationFails() {
        BlenderConfig simplePropertiesConfig = new BlenderConfig.Builder().withPropertyFiles("invalid.properties").build();
        blender = new Blender(simplePropertiesConfig);
        ValidatorBean validatorBean = new ValidatorBean();

        blender.fill(validatorBean);
    }

    @Test
    public void shouldRemovePrefixBeforeSerializing() {
        BlenderConfig prefixPropertiesConfig = new BlenderConfig.Builder().withPropertyFiles("prefix.properties").
                withPrefix("org.blender").build();
        blender = new Blender(prefixPropertiesConfig);
        ValidatorBean validatorBean = new ValidatorBean();
        blender.fill(validatorBean);

        assertThat(validatorBean.getName()).isEqualTo("name");
        assertThat(validatorBean.getValue()).isEqualTo(20);
    }

    @Test
    public void shouldReadFromSystemProperties() {
        BlenderConfig systemPropertiesConfig = new BlenderConfig.Builder().useSystemProperties().build();
        blender = new Blender(systemPropertiesConfig);
        assertThat(blender.valueOf(SYSTEM_KEY)).isEqualTo(SYSTEM_VALUE);
    }

    @Test
    public void shouldSerializeNestedPropertiesToBean() {
        BlenderConfig prefixPropertiesConfig = new BlenderConfig.Builder().withPropertyFiles("nested.properties").
                withPrefix("org.blender").build();
        blender = new Blender(prefixPropertiesConfig);
        Person person = new Person();
        blender.fill(person);

        assertThat(person.getName()).isEqualTo("gokul");
        assertThat(person.getAddress().getStreet()).isEqualTo("local street");
        assertThat(person.getAddress().getHome().getNumber()).isEqualTo(12345);
    }

    @Test
    public void shouldOverrideBasedOnFileOrder() {
        BlenderConfig overridePropertiesConfig = new BlenderConfig.Builder().withPropertyFiles("override.properties", "simple.properties").build();
        blender = new Blender(overridePropertiesConfig);
        ValidatorBean validatorBean = new ValidatorBean();
        blender.fill(validatorBean);

        assertThat(validatorBean.getName()).isEqualTo("new name");
        assertThat(validatorBean.getValue()).isEqualTo(23);
    }

    @Test
    public void shouldOverrideBasedOnSystemProperties() {
        System.setProperty("name",SYSTEM_VALUE);
        BlenderConfig overridePropertiesConfig = new BlenderConfig.Builder().useSystemProperties().
                withPropertyFiles("override.properties", "simple.properties").build();
        blender = new Blender(overridePropertiesConfig);

        ValidatorBean validatorBean = new ValidatorBean();
        blender.fill(validatorBean);

        assertThat(validatorBean.getName()).isEqualTo(SYSTEM_VALUE);
        System.clearProperty("name");
    }

    @Test
    public void shouldDumpPropertiesToConsole() {
        BlenderConfig overridePropertiesConfig = new BlenderConfig.Builder().ignoreSystemProperties().
                withPropertyFiles("override.properties", "simple.properties").build();
        blender = new Blender(overridePropertiesConfig);
        blender.dumpProperties();
    }

}
