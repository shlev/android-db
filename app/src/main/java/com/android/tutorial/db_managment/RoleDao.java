package com.android.tutorial.db_managment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RoleDao {

    @Insert
    long insert(Role role);

    @Update
    void update(Role role);

    @Delete
    void delete(Role role);

    @Query("SELECT * FROM role_table ORDER BY priority")
    LiveData<List<Role>> getAllRoles();


}
