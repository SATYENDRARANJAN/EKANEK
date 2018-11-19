package docsapp.com.ekanek.dto;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class FlickrPhotoData {
    @SerializedName("page")
    public int page;
    @SerializedName("pages")
    public int pages;
    @SerializedName("perpage")
    public int perpage;
    @SerializedName("total")
    public int total;
    @SerializedName("photo")
    public List<FlickrImageData> photo;
}
