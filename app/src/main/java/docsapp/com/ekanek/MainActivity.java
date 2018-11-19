package docsapp.com.ekanek;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;

import docsapp.com.ekanek.Presenter.ImagePresenter;
import docsapp.com.ekanek.dbRoom.dao.ImageDao;
import docsapp.com.ekanek.dbRoom.db.AppDataBase;
import docsapp.com.ekanek.dbRoom.model.ImageData;
import docsapp.com.ekanek.dto.FlickrImageData;
import docsapp.com.ekanek.dto.FlickrImageResponseDTO;
import docsapp.com.ekanek.view.FlickrImageView;
import utils.Utils;

public class MainActivity extends AppCompatActivity implements FlickrImageView, View.OnClickListener {

    private RecyclerView recyclerView;
    private GridLayoutManager recyclerViewLayoutManagerTwo, recyclerViewLayoutManagerThree, recyclerViewLayoutManagerFour;
    private ImagePresenter imagePresenter;
    private ArrayList<FlickrImageData> imageList;
    private EditText etSearch;
    private Button btn_done;
    private ImageAdapter imageAdapter;
    FrameLayout ll_loading;
    ProgressBar progress_Loading;
    private boolean isLoading = false;
    private SharedPreferencesManager sharedPreferencesManager;
    String searchText=null;
    Context mContext ;
    static int currentPage=1;

    //Room
    ImageDao imageDao;
    ImageData IMAGE_DATA ;

    //public Integer[] mIds ={R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a,R.drawable.a};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferencesManager = SharedPreferencesManager.getInstance(this);
        imageList = new ArrayList<FlickrImageData>();
        btn_done = findViewById(R.id.bt_search);
        etSearch = findViewById(R.id.et_search);
        mContext = this;
        //Room
        imageDao = Room.databaseBuilder(this,AppDataBase.class,"FlickrTable")
                .build()
                .getImageDao();
        //progress bar bottom
        progress_Loading = findViewById(R.id.progress_bar);
        imagePresenter = new ImagePresenter(this);
        imageAdapter = new ImageAdapter(this, imageList);
        recyclerView = findViewById(R.id.recycler_view1);
        recyclerViewLayoutManagerTwo = new GridLayoutManager(this, 2);
        recyclerViewLayoutManagerThree = new GridLayoutManager(this, 3);
        recyclerViewLayoutManagerFour = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(recyclerViewLayoutManagerTwo);
        sharedPreferencesManager.setMenuItemChecked(Constants.VALUE_2);
        //set adapter in recyclerview
        recyclerView.setAdapter(imageAdapter);
        btn_done.setOnClickListener(this);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                //find the topmost element and elements visible on screen .
                //if sum > total elements ->
                Log.d("satya",imageList.size()+"");
                if (sharedPreferencesManager.getMenuItemChecked() == Constants.VALUE_2) {
                    int visibleCount = recyclerViewLayoutManagerTwo.getChildCount();
                    int totalItem = recyclerViewLayoutManagerTwo.getItemCount();
                    int firstVisible = recyclerViewLayoutManagerTwo.findFirstVisibleItemPosition();
                    if (visibleCount + firstVisible >= totalItem) {
                        progress_Loading.setVisibility(View.VISIBLE);

                        if (!isLoading) {
                            currentPage = sharedPreferencesManager.getPage();
                            imagePresenter.getFlickerImage(mContext, searchText,++currentPage);
                            Log.d("satya",imageList.size()+" "+currentPage);
                            sharedPreferencesManager.setPage(currentPage);
                        }
                        isLoading =true;

                    }
                }else if(sharedPreferencesManager.getMenuItemChecked() == Constants.VALUE_3) {
                    int visibleCount = recyclerViewLayoutManagerThree.getChildCount();
                    int totalItem = recyclerViewLayoutManagerThree.getItemCount();
                    int firstVisible = recyclerViewLayoutManagerThree.findFirstVisibleItemPosition();
                    if (visibleCount + firstVisible >= totalItem) {
                        progress_Loading.setVisibility(View.VISIBLE);

                        if (!isLoading) {
                            currentPage = sharedPreferencesManager.getPage();
                            imagePresenter.getFlickerImage(mContext, searchText, ++currentPage);
                            sharedPreferencesManager.setPage(currentPage);
                        }
                        isLoading =true;
                    }
                }else if(sharedPreferencesManager.getMenuItemChecked() == Constants.VALUE_4){
                    int visibleCount = recyclerViewLayoutManagerFour.getChildCount();
                    int totalItem = recyclerViewLayoutManagerFour.getItemCount();
                    int firstVisible = recyclerViewLayoutManagerFour.findFirstVisibleItemPosition();
                    if (visibleCount + firstVisible >= totalItem) {
                        progress_Loading.setVisibility(View.VISIBLE);


                        if (!isLoading){
                            currentPage = sharedPreferencesManager.getPage();
                            imagePresenter.getFlickerImage(mContext, searchText,++currentPage);
                            sharedPreferencesManager.setPage(currentPage);
                        }

                        isLoading =true;
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.two:
                if (!item.isChecked()) {
                    item.setChecked(true);
                } else {
                    item.setChecked(false);
                }
                recyclerView.setLayoutManager(recyclerViewLayoutManagerTwo);
                sharedPreferencesManager.setMenuItemChecked(Constants.VALUE_2);
                break;
            case R.id.three:
                if (!item.isChecked()) {
                    item.setChecked(true);
                } else {
                    item.setChecked(false);
                }
                recyclerView.setLayoutManager(recyclerViewLayoutManagerThree);
                sharedPreferencesManager.setMenuItemChecked(Constants.VALUE_3);
                break;
            case R.id.four:
                if (!item.isChecked()) {
                    item.setChecked(true);
                } else {
                    item.setChecked(false);
                }
                recyclerView.setLayoutManager(recyclerViewLayoutManagerFour);
                sharedPreferencesManager.setMenuItemChecked(Constants.VALUE_4);
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResponseSuccess(FlickrImageResponseDTO flickrImageResponseDTO) {



        if (flickrImageResponseDTO.photos.photo != null)
            imageList.addAll(flickrImageResponseDTO.photos.photo);
        progress_Loading.setVisibility(View.GONE);
        isLoading=false;
        imageAdapter.notifyDataSetChanged();

        saveInInternalDbAndSavePathInRoom();
    }

    private void saveInInternalDbAndSavePathInRoom() {
        IMAGE_DATA = new ImageData();

    }

    @Override
    public void onResponseFailure(String message) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_search:
                searchText = etSearch.getText().toString();
                if (!TextUtils.isEmpty(searchText) ) {
                    imageList.clear();
                    sharedPreferencesManager.setPage(1);
                    currentPage =1;
                    imagePresenter.getFlickerImage(this, searchText,1);
                    progress_Loading.setVisibility(View.VISIBLE);
                }
                    break;
                }
        }
    }

