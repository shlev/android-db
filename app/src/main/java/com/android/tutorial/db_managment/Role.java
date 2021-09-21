package com.android.tutorial.db_managment;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "role_table")
public class Role {

    @PrimaryKey(autoGenerate = true)
    public long id;

    public int priority;

    public String name;

    public Role(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }
}
