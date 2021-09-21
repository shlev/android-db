package com.android.tutorial.brave_db_managment;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database( entities = { User.class, Role.class}, version = 1)
public abstract class BraveDatabase extends RoomDatabase {
    private static BraveDatabase instance;
    public abstract UserDao userDao();
    public abstract RoleDao roleDao();


    public static synchronized BraveDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BraveDatabase.class, "brave_database")
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

        private PopulateDbAsyncTask(BraveDatabase braveDatabase) {
            this.noteDao = braveDatabase.userDao();
            this.roleDao = braveDatabase.roleDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            long adminID = roleDao.insert(new Role(30, "ADMIN"));
            long heroID = roleDao.insert(new Role(20, "HERO"));
            long volID = roleDao.insert(new Role(10, "VOLUNTEER"));

            noteDao.insert(new User("Super", "super@brave.com","1234512345", adminID));
            noteDao.insert(new User("David", "brave@brave.com","1234512345" , heroID));
            noteDao.insert(new User("Yury", "voly@brave.com","1234512345", volID ));
            return null;
        }
    }


}
