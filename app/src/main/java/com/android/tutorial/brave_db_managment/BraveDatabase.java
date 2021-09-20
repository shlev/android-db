package com.android.tutorial.brave_db_managment;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database( entities = { User.class}, version = 2)
public abstract class BraveDatabase extends RoomDatabase {
    private static BraveDatabase instance;
    public abstract UserDao userDao();

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

        private PopulateDbAsyncTask(BraveDatabase noteDatabase) {
            this.noteDao = noteDatabase.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new User("Super", "super@brave.com","1234512345", "SuperUser" ));
            noteDao.insert(new User("David", "brave@brave.com","1234512345", "Hero" ));
//            noteDao.insert(new User("Yury", "voly@brave.com","1234512345", "Volunteer" ));
            return null;
        }
    }


}
