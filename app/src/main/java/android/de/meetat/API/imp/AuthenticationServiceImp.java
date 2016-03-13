package android.de.meetat.API.imp;

import android.content.Context;
import android.de.meetat.API.AuthenticationService;
import android.de.meetat.API.ServiceCallback;

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
        // "curl -XPOST http://localhost:8080/users -d 'nickname=philipp&email=someone%40somewhere.com&phone_number=1234&password=lol1234'"
    }

    @Override
    public void signin(String email, String password, ServiceCallback<Boolean> callback) {

    }
}
