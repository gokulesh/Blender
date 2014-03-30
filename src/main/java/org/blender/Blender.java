package org.blender;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.configuration.Configuration;
import org.blender.exception.BlenderException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Iterator;
import java.util.Set;

public class Blender {

    private final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
    private final Configuration configuration;

    public Blender(BlenderConfig blenderConfig) {
        configuration = configurationBuilder.createConfiguration(blenderConfig);
    }

    public String valueOf(String property) {
        return configuration.getString(property);
    }

    public <T> void fill(T bean) {
        Iterator<String> keys = configuration.getKeys();
        while (keys.hasNext()) {
            String key = keys.next();
            String value = configuration.getString(key);
            fillBean(bean, key, value);
        }
        validate(bean);
    }

    public void dumpProperties(){
        Iterator<String> keys = configuration.getKeys();
        while(keys.hasNext()){
            String key = keys.next();
            System.out.println(key + ":" + configuration.getString(key) );
        }
    }

    private <T> void fillBean(T bean, String key, String value) {
        try {
            BeanUtils.setProperty(bean, key, value);
        } catch (ReflectiveOperationException e) {
            throw new BlenderException(e);
        }
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
            msg.append(violation.getPropertyPath().toString() + ": " + violation.getMessage());
        }
        throw new BlenderException(msg.toString());
    }

}
