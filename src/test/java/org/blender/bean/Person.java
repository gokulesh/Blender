package org.blender.bean;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

public class Person {

    @NotEmpty
    private String name;

    @Valid
    private Address address = new Address();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
