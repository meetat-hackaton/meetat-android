package android.de.meetat.API.imp;

import android.content.Context;
import android.de.meetat.API.AuthenticationService;
import android.de.meetat.API.ServiceCallback;
import android.de.meetat.API.imp.task.SignupTask;

/**
 * Created by mahieke on 12.03.16.
 */
public class AuthenticationServiceImp implements AuthenticationService {

    private Context context;

    public AuthenticationServiceImp (Context context) {
        this.context = context;
    }

    @Override
    public void signup(String nickname, String email, String password, ServiceCallback<Boolean> callback) {
        new SignupTask(nickname, email, password, context, callback);
    }

    @Override
    public void signin(String email, String password, ServiceCallback<Boolean> callback) {

    }
}
