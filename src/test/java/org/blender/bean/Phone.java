package org.blender.bean;

import javax.validation.constraints.Digits;

public class Phone {

    @Digits(integer = 5,fraction = 0)
    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
