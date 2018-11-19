package docsapp.com.ekanek.network;

import android.content.Context;

import docsapp.com.ekanek.dto.FlickrImageResponseDTO;
import retrofit2.Call;
import retrofit2.Response;

public abstract  class APICallback<T> implements  retrofit2.Callback {

    Context context;

    public APICallback(){
    }

    public APICallback(Context context){
        this.context = context;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if(response.isSuccessful()){
            FlickrImageResponseDTO responseDTO = (FlickrImageResponseDTO) response.body();
            if(responseDTO != null)
                onResponseSuccess(responseDTO);
            else
                onResponseError(responseDTO);
        }else{
            onResponseFailure(response.message());
        }
    }

    public abstract void onResponseFailure(String message);

    public abstract void onResponseError(FlickrImageResponseDTO responseDTO);

    public abstract void onResponseSuccess(FlickrImageResponseDTO responseDTO);

    @Override
    public void onFailure(Call call, Throwable t) {
        onResponseFailure("OOPS . There is a Problem ! "+t.toString());

    }
}
