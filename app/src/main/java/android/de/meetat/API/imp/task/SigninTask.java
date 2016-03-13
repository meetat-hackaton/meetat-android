package android.de.meetat.API.imp.task;

import android.content.Context;
import android.de.meetat.API.ServiceCallback;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by philipp on 13.03.16.
 */
public class SigninTask extends BackendTask<Void, Void, JSONObject> {

    private String email;
    private String password;

    public SigninTask(String email, String password, Context context, ServiceCallback<JSONObject> callback) {
        super(context, callback);
        this.email = email;
        this.password = password;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        List<NameValuePair> parameters = new ArrayList<>();
        parameters.add(new BasicNameValuePair("email", email));
        parameters.add(new BasicNameValuePair("password", password));
        return post("/users/auth", parameters);
    }
}
