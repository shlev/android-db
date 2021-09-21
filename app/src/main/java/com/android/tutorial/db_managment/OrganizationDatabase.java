package com.android.tutorial.db_managment;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database( entities = { User.class, Role.class}, version = 1)
public abstract class OrganizationDatabase extends RoomDatabase {
    private static OrganizationDatabase instance;
    public abstract UserDao userDao();
    public abstract RoleDao roleDao();


    public static synchronized OrganizationDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OrganizationDatabase.class, "org_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao noteDao;
        private RoleDao roleDao;

        private PopulateDbAsyncTask(OrganizationDatabase braveDatabase) {
            this.noteDao = braveDatabase.userDao();
            this.roleDao = braveDatabase.roleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            long adminID = roleDao.insert(new Role(30, "ADMIN"));
            long workerID = roleDao.insert(new Role(20, "WORKER"));
            long manID = roleDao.insert(new Role(10, "MANAGER"));

            noteDao.insert(new User("Super", "super@myorg.com","1234512345", adminID));
            noteDao.insert(new User("David", "david@myorg.com","1234512345" , workerID));
            noteDao.insert(new User("Yury", "voly@myorg.com","1234512345", manID ));
            return null;
        }
    }


}
