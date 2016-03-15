package android.de.meetat;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by mahieke on 12.03.16.
 */
public class Session {
    private static final String  MY_PREFS_NAME = "de.meetat";
    private static Session sessionReminder = null;
    private String NICKNAME = null;

    private Session() {

    }

    public static Session getSessionReminder(){
        if(sessionReminder == null){
            sessionReminder = new Session();
        }
        return sessionReminder;
    }

    public void saveLogin(String key, String nickname, Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(NICKNAME, nickname);
        editor.commit();
    }

    public String getNickname() {
        return NICKNAME;
    }

    public void dropLogin(Context context) {
        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.remove(NICKNAME);
        editor.commit();
    }

    public boolean isLogged(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREFS_NAME,Context.MODE_PRIVATE);
        String result = sharedPreferences.getString(NICKNAME,null);
        return result != null;
    }
}