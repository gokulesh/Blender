package org.blender.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

public class Address {

    @NotEmpty
    private String street;

    @Valid
    private Phone home = new Phone();

    @Valid
    private Phone mobile = new Phone();

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Phone getHome() {
        return home;
    }

    public void setHome(Phone home) {
        this.home = home;
    }

    public Phone getMobile() {
        return mobile;
    }

    public void setMobile(Phone mobile) {
        this.mobile = mobile;
    }
}
