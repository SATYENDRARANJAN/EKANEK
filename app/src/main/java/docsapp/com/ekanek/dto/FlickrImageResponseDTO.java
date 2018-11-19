package docsapp.com.ekanek.dto;

import android.support.v7.graphics.drawable.DrawerArrowDrawable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FlickrImageResponseDTO {
    @SerializedName("photos")
    public FlickrPhotoData photos;

    @SerializedName("stat")
    public String stat;
}
