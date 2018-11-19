package docsapp.com.ekanek;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.ArrayList;

import docsapp.com.ekanek.dto.FlickrImageData;
import docsapp.com.ekanek.dto.FlickrImageResponseDTO;

class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    ArrayList<FlickrImageData> arrayList;

    public ImageAdapter(Context context , ArrayList<FlickrImageData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (i < getItemCount()) {
            viewHolder.bindView(arrayList.get(i));
        }

        ViewHolder vh = viewHolder;
        Glide.with(vh.imageView.getContext())
                .load(arrayList.get(i).getImageUrl())
                .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                .into(vh.imageView);

       /* Glide.with(mContext)
                .load("YOUR_URL")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>(100,100) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation)  {
                        saveImage(resource);
                    }
                });*/


        /*Glide.with(mContext)
         .load("YOUR_URL")
         .asBitmap()
         .into(new SimpleTarget<Bitmap>(100,100) {
             @Override
             public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation)  {
                   saveImage(resource);
             }
         });*/
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public  ImageView imageView;
        @Nullable
        private AsyncTask<Object, Void, Bitmap> mDownloadImageTask;
        @Nullable
        private FlickrImageData mFlickrImagedata;

        public ViewHolder(View v) {
            super(v);
           imageView = v.findViewById(R.id.img_search);
        }

        void bindView(FlickrImageData flickrImageData){
            this.mFlickrImagedata = flickrImageData;
            //nullify previous asynctasks
            if(mDownloadImageTask !=null)
            {
                mDownloadImageTask.cancel(true);
            }
            loadImages();
        }

        private void loadImages() {
            if(mFlickrImagedata.getImageUrl()!=null){

            }
        }
    }
}
