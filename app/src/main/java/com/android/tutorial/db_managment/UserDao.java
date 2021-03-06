package com.android.tutorial.db_managment;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void deleteAllUsers();

//    @Query("SELECT * FROM user_table ORDER BY name DESC")
//    LiveData<List<User>> getAllUsers();

    @Transaction
    @Query("SELECT * FROM user_table")
    LiveData<List<UserAndRole>> getAllUsersAndRoles();
}
