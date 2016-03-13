package android.de.meetat.API.imp.task;

import android.content.Context;
import android.de.meetat.API.ServiceCallback;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * @author Philipp Daniels
 */
public abstract class BackendTask<Params, Progress, Result> extends AsyncServiceTask<Params, Progress, Result> {

    private final static String API_HOME = "http://172.27.6.113:8080";
    private final static String LOG_TAG = "rest-backend";

    protected BackendTask(Context context, ServiceCallback<Result> callback) {
        super(context, callback);
    }

    protected String post(String uri, ArrayList<NameValuePair> parameters) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(API_HOME + uri);
            post.setEntity(new UrlEncodedFormEntity(parameters));
            HttpResponse response = client.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            JSONObject o = new JSONObject(result.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
