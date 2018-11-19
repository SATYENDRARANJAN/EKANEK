package docsapp.com.ekanek.dbRoom.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "FlickrTable")
public class ImageData {

    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull
    public  String id ;

    @ColumnInfo(name = "ImageUrl")
    public String img_url;

    @ColumnInfo(name="pathInternal")
    public String pathInternal;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getPathInternal() {
        return pathInternal;
    }

    public void setPathInternal(String pathInternal) {
        this.pathInternal = pathInternal;
    }
}
