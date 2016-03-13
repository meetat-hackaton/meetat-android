package android.de.meetat.API.imp;

import android.content.Context;
import android.de.meetat.API.AuthenticationService;
import android.de.meetat.ServiceCallback;

/**
 * Created by mahieke on 12.03.16.
 */
public class AuthenticationServiceImp implements AuthenticationService {
    Context context;

    public AuthenticationServiceImp (Context context) {
        this.context = context;
    }

    @Override
    public boolean signup(String nickname, String email, String password, ServiceCallback<>) {
        // "curl -XPOST http://localhost:8080/users -d 'nickname=philipp&email=someone%40somewhere.com&phone_number=1234&password=lol1234'"
        return true;
    }
}
