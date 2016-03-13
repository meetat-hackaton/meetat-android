package android.de.meetat.API;

import android.content.Context;
import android.de.meetat.API.imp.AuthenticationServiceImp;
import android.de.meetat.API.imp.QueryServiceImp;

/**
 * Created by mahieke on 12.03.16.
 */
public final class Service {
    private Service() {
        throw new RuntimeException("Donot create an instance of class Service!");
    }

    public static AuthenticationService getAuthenticationService(Context context) {
        return new AuthenticationServiceImp(context);
    }

    public static QueryService getQueryService(Context context) {
        return new QueryServiceImp(context);
    }
}
