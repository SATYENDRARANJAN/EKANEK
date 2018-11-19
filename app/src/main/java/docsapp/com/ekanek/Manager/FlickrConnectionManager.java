package docsapp.com.ekanek.Manager;

import android.content.Context;

import docsapp.com.ekanek.dto.FlickrImageResponseDTO;
import docsapp.com.ekanek.network.APICallback;
import docsapp.com.ekanek.network.APIServiceUtil;
import retrofit2.Call;

public class FlickrConnectionManager {

    public static void getFlickrImage(Context context,String query, int page, APICallback<FlickrImageResponseDTO> apiCallback){
        Call<FlickrImageResponseDTO> callObj =APIServiceUtil.getsInstance(context).getAPIInterface().getImagesData(query,page);
        callObj.enqueue(apiCallback);
    }
}
