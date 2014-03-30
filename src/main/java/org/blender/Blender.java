package org.blender;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.blender.exception.BlendingException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

public class Blender {

    private PropertiesConfiguration configuration;

    public Blender(PropertiesConfiguration configuration) {
        this.configuration = configuration;
    }

    public Blender() {
        this.configuration = new PropertiesConfiguration();
    }

    public void read(String file) {
        loadPropertiesFile(file);
    }

    public <T> void mix(T bean, String file) {
        loadPropertiesFile(file);
        Iterator<String> keys = configuration.getKeys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = configuration.getString(key);
            fillBean(bean, key, value);
        }
        validate(bean);
    }

    public String valueOf(String property) {
        return configuration.getString(property);
    }

    private <T> void validate(T bean) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (violations.isEmpty()) {
            return;
        }
        throwExceptionWithViolations(violations);

    }

    private <T> void throwExceptionWithViolations(Set<ConstraintViolation<T>> violations) {
        StringBuilder msg = new StringBuilder("");
        for (ConstraintViolation<T> violation : violations) {
            msg.append(violation.getPropertyPath().toString() + "-" + violation.getMessage());
        }

        throw new BlendingException(msg.toString());
    }

    private void loadPropertiesFile(String file) {
        try {
            configuration.load(file);
        } catch (ConfigurationException e) {
            throw new BlendingException(e);
        }
    }

    private <T> void fillBean(T bean, String key, String value) {
        try {
            BeanUtils.setProperty(bean, key, value);
        } catch (ReflectiveOperationException e) {
            throw new BlendingException(e);
        }
    }
}
