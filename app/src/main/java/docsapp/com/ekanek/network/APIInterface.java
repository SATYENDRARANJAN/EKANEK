package docsapp.com.ekanek.network;

import java.util.Map;

import docsapp.com.ekanek.dto.FlickrImageResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface APIInterface {


    @GET("?method=flickr.photos.getRecent&api_key=3e7cc266ae2b0e0d78e279ce8e361736&%20format=json&nojsoncallback=1&safe_search=1")
    Call<FlickrImageResponseDTO> getImagesData(@Query("text") String textSearch, @Query("page") int page);

}
