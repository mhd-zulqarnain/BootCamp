package com.example.zulup.onswipdelete.model;

/**
 * Created by Zul Qarnain on 1/31/2018.
 */

public class Student {
    String name;
    int id;

    public Student(int id,String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
