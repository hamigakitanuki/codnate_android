package com.example.codnate3.object;

import java.io.Serializable;

public class Account_data implements Serializable {
    private String sex;
    private String name;
    private String age;
    private String type;

    public void setSex(String sex){
        this.sex = sex;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAge(String age){
        this.age = age;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getSex(){
        return sex;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return Integer.parseInt(age);
    }

    public String getType(){
        return type;
    }
}
