package docsapp.com.ekanek.dto;

import com.google.gson.annotations.SerializedName;

public class FlickrImageData {
    @SerializedName("id")
    public long id ;

    @SerializedName("owner")
    public String owner ;

    @SerializedName("secret")
    public String secret;

    @SerializedName("server")
    public String server;

    @SerializedName("farm")
    public int farm;

    @SerializedName("title")
    public String title;

    @SerializedName("ispublic")
    public int ispublic;

    @SerializedName("isfriend")
    public int isfriend;

    @SerializedName("isfamily")
    public int isfamily;

    public String getImageUrl() {
        return String.format("http://farm%s.static.flickr.com/%s/%s_%s.jpg", farm, server, id, secret);
    }

}
/*"id": "45946302971",
				"owner": "152010848@N02",
				"secret": "16a8f7b2d8",
				"server": "4842",
				"farm": 5,
				"title": "Uniform",
				"ispublic": 1,
				"isfriend": 0,
				"isfamily": 0*/