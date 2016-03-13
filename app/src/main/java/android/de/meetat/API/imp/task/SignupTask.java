package android.de.meetat.API.imp.task;

import android.content.Context;
import android.de.meetat.API.ServiceCallback;

/**
 * @author Philipp Daniels
 */
public class SignupTask extends BackendTask {

    private String nickname;
    private String email;
    private String password;

    public SignupTask(String nickname, String email, String password, Context context, ServiceCallback callback) {
        super(context, callback);
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        // "curl -XPOST http://localhost:8080/users -d 'nickname=philipp&email=someone%40somewhere.com&phone_number=1234&password=lol1234'"
        return null;
    }
}
