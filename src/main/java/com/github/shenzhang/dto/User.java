package com.github.shenzhang.dto;

/**
 * Created by Zhang Shen on 5/23/16.
 */
public class User {
    public User() {}
    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    private long id;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
