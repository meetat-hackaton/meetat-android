package android.de.meetat.API.imp;

import android.content.Context;
import android.de.meetat.API.AuthenticationService;
import android.de.meetat.API.ServiceCallback;
import android.de.meetat.API.imp.task.SigninTask;
import android.de.meetat.API.imp.task.SignupTask;

import org.json.JSONObject;

/**
 * Created by mahieke on 12.03.16.
 */
public class AuthenticationServiceImp implements AuthenticationService {

    private Context context;

    public AuthenticationServiceImp (Context context) {
        this.context = context;
    }

    @Override
    public void signup(String nickname, String email, String password, ServiceCallback<JSONObject> callback) {
        new SignupTask(nickname, email, password, context, callback).execute();
    }

    @Override
    public void signin(String email, String password, ServiceCallback<JSONObject> callback) {
        new SigninTask(email, password, context, callback).execute();
    }
}
