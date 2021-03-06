package android.de.meetat.API.imp.task;

import android.content.Context;
import android.de.meetat.API.ServiceCallback;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Philipp Daniels
 */
public class SignupTask extends BackendTask<Void, Void, JSONObject> {

    private String nickname;
    private String email;
    private String password;

    public SignupTask(String nickname, String email, String password, Context context, ServiceCallback<JSONObject> callback) {
        super(context, callback);
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("nickname", nickname));
        parameters.add(new BasicNameValuePair("email", email));
        parameters.add(new BasicNameValuePair("password", password));
        parameters.add(new BasicNameValuePair("phone_number", "1234"));
        return post("/users", parameters);
    }
}
