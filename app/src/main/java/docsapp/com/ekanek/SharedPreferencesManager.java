package docsapp.com.ekanek;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Satyendra.Ranjan on 14-08-2018.
 */

public class SharedPreferencesManager {


    public interface  KEYS{
        public static String KEY_PAGE ="PAGE";
        public static String KEY_MENU_ITEM_CHECKED ="KEY_MENU_ITEM_CHECKED";
        public static final String SEARCH_LIST= "SEARCH_LIST";

    }

    private static SharedPreferencesManager sInstance;
    private SharedPreferences mPrefs;
    private  SharedPreferences.Editor mEditor;


    private SharedPreferencesManager(Context context){
        mPrefs = context.getApplicationContext().getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        mEditor = mPrefs.edit();
    }

    public static SharedPreferencesManager getInstance(Context context){
        if(sInstance ==null){
            sInstance =  new SharedPreferencesManager(context);
        }
        return sInstance;
    }

    public  int getPage(){
        return mPrefs.getInt(KEYS.KEY_PAGE,1);
    }

    public  void setPage(int page){
        mEditor.putInt(KEYS.KEY_PAGE,page).commit();
    }


    public int getMenuItemChecked(){
        return  mPrefs.getInt(KEYS.KEY_MENU_ITEM_CHECKED,10);
    }

    public void  setMenuItemChecked(int limit ){
        mEditor.putInt(KEYS.KEY_MENU_ITEM_CHECKED,limit).commit();
    }


    public  boolean checkString(Context context,String searchText) {
        Set<String> list = mPrefs.getStringSet(KEYS.SEARCH_LIST , new HashSet<String>());
        if(!list.contains(searchText))
            return true;
        return false;
    }

    public void addStringToSearchedList(String s , Context context){
        Set<String> list = mPrefs.getStringSet(KEYS.SEARCH_LIST , new HashSet<String>());
        Log.d("satyaHash",list.toString());
        list.add(s);
        mEditor.putStringSet(KEYS.SEARCH_LIST,list);
        mEditor.commit();
        Log.d("satyaHashA",list.toString());
    }
}
