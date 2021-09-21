package com.android.tutorial.db_managment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    
    private UserRepository repository;
    private LiveData<List<UserAndRole>> allUsers;
    
    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = new UserRepository(application);
        allUsers = repository.getAllUsers();
    }

    public void insert(User user) {
        repository.insert(user);
    }

    public void update(User user) {
        repository.update(user);
    }

    public void delete(User user) {
        repository.delete(user);
    }

    public void delete(UserAndRole userAndRole) {
        repository.delete(userAndRole.getUser());
    }

    public void deleteAllUsers() {
        repository.deleteAllUsers();
    }

    public LiveData<List<UserAndRole>> getAllUsers() {
        return allUsers;
    }
}
