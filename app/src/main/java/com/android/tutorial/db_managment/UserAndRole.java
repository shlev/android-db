package com.android.tutorial.db_managment;

import androidx.room.Embedded;
import androidx.room.Relation;

public class UserAndRole {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "roleID",
            entityColumn = "id"
    )
    public Role role;

    public User getUser() {
        return user;
    }

    public Role getRole() {
        return role;
    }
}
