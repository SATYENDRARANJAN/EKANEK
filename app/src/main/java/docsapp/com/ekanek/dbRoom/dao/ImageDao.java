package docsapp.com.ekanek.dbRoom.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import docsapp.com.ekanek.Constants;
import docsapp.com.ekanek.dbRoom.model.ImageData;

@Dao
public interface ImageDao {

    @Insert
    public void insert(ImageData... imageData);

    @Update
    public void update(ImageData... imageData);

    @Delete
    public void delete(ImageData... imageData);

    @Query("SELECT * FROM FlickrTable")
    public List<ImageData> getImageData();

    @Query("SELECT * FROM FlickrTable WHERE  id =:n")
    public List<ImageData> getImageDataWithId(String n);

}

