package utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

import docsapp.com.ekanek.SharedPreferencesManager;

public class Utils {
    static SharedPreferencesManager sharedPreferencesManager;

    public static boolean isNotSearched(Context context, String searchText) {
       // Get the current list.
        sharedPreferencesManager = SharedPreferencesManager.getInstance(context);
        return sharedPreferencesManager.checkString(context, searchText);
    }

    public static void addStringToSearchedList(Context context, String searchText) {
        // Get the current list.
        sharedPreferencesManager = SharedPreferencesManager.getInstance(context);
        sharedPreferencesManager.addStringToSearchedList(searchText,context);
    }


    /*SharedPreferences.Editor editor = settings.edit();
    Set<String> myStrings = settings.getStringSet("myStrings", new HashSet<String>());

// Add the new value.
        myStrings.add("Another string");

// Save the list.
        editor.putStringSet("myStrings", myStrings);
        editor.commit();

        return ;*/
}
