package docsapp.com.ekanek.dbRoom.db;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import docsapp.com.ekanek.dbRoom.dao.ImageDao;
import docsapp.com.ekanek.dbRoom.model.ImageData;

@Database(entities = {ImageData.class},version=1)

public abstract class AppDataBase extends RoomDatabase {
    public abstract ImageDao getImageDao();

}
