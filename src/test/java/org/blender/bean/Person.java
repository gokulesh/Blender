package org.blender.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class Person {

    @NotNull
    private String name;

    @NotNull
    @Min(30)
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
