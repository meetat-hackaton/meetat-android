package android.de.meetat.API;

import org.json.JSONObject;

/**
 * Created by mahieke on 12.03.16.
 */
public interface QueryService {
    void checkNickname(String nickname, ServiceCallback<JSONObject> callback);
}
