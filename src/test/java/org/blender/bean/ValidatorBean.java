package org.blender.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class ValidatorBean {

    @NotEmpty
    private String name;

    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(15)
    @Max(25)
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
