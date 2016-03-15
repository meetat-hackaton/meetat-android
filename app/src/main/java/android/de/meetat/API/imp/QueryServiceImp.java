package android.de.meetat.API.imp;

import android.content.Context;
import android.de.meetat.API.QueryService;
import android.de.meetat.API.ServiceCallback;
import android.de.meetat.API.imp.task.CheckNicknameTask;
import android.de.meetat.API.imp.task.SignupTask;

import org.json.JSONObject;

/**
 * Created by mahieke on 12.03.16.
 */
public class QueryServiceImp implements QueryService {
    Context context;

    public QueryServiceImp(Context context) {
        this.context = context;
    }

    @Override
    public void checkNickname(String nickname, ServiceCallback<JSONObject> callback) {
        new CheckNicknameTask(nickname, context, callback).execute();
    }

}
