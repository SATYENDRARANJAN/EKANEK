package docsapp.com.ekanek.Presenter;

import android.content.Context;

import docsapp.com.ekanek.Manager.FlickrConnectionManager;
import docsapp.com.ekanek.dto.FlickrImageResponseDTO;
import docsapp.com.ekanek.network.APICallback;
import docsapp.com.ekanek.view.FlickrImageView;

public class ImagePresenter {

    FlickrImageView flickrImageView;
    public ImagePresenter(FlickrImageView flickrImageView) {
        this.flickrImageView = flickrImageView;
    }

    public void getFlickerImage(Context context ,String textSearch,int page){
        FlickrConnectionManager.getFlickrImage( context,textSearch,page, new APICallback<FlickrImageResponseDTO>(context){
            @Override
            public void onResponseSuccess(FlickrImageResponseDTO responseDTO) {
                if(responseDTO==null || responseDTO.photos==null)
                    return;
                FlickrImageView view = (FlickrImageView)flickrImageView;
                if(view==null)
                    return;
                view.onResponseSuccess(responseDTO);
            }

            @Override
            public void onResponseFailure(String message) {
                FlickrImageView view = flickrImageView;
                if(view== null)
                    return;
                view.onResponseFailure("Error occured!");
            }

            @Override
            public void onResponseError(FlickrImageResponseDTO responseDTO) {
                FlickrImageView view = flickrImageView;
                if(view== null)
                    return;
                view.onResponseFailure("Error occured!");
            }
        });

    }


}
