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
public class CheckNicknameTask extends BackendTask<Void, Void, JSONObject> {

    private String nickname;

    public CheckNicknameTask(String nickname, Context context, ServiceCallback<JSONObject> callback) {
        super(context, callback);
        this.nickname = nickname;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {
        List<NameValuePair> parameters = new ArrayList<>(); // TODO FOR API 23, RESOLVE DEPRICATED
        parameters.add(new BasicNameValuePair("nickname", nickname));
        return get("/users/", parameters);
    }
}
