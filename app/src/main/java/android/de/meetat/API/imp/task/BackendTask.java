package android.de.meetat.API.imp.task;

import android.content.Context;
import android.de.meetat.API.ServiceCallback;

import java.net.URI;

/**
 * @author Philipp Daniels
 */
public abstract class BackendTask<Params, Progress, Result> extends AsyncServiceTask<Params, Progress, Result> {

    private final static String API_HOME = "http://192.168.178.54:8090";
    private final static String LOG_TAG = "rest-backend";

    protected BackendTask(Context context, ServiceCallback<Result> callback) {
        super(context, callback);
    }

    protected String request(String uri) {
        return null;
    }
}
