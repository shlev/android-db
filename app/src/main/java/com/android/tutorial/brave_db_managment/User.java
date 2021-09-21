package com.android.tutorial.brave_db_managment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String email;
    private String phone;

    private long roleID;


    public User(String name, String email, String phone, long roleID) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roleID = roleID;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public long getRoleID() { return roleID;}
}
