package android.de.meetat.API.imp.task;

import android.content.Context;
import android.de.meetat.API.ServiceCallback;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author Philipp Daniels
 */
public abstract class BackendTask<Params, Progress, Result> extends AsyncServiceTask<Params, Progress, Result> {

    private final static String API_HOME = "http://172.27.6.113:8080";
    private final static String LOG_TAG = "rest-backend";

    protected BackendTask(Context context, ServiceCallback<Result> callback) {
        super(context, callback);
    }

    protected JSONObject post(String uri, List<NameValuePair> parameters) {
        StringBuffer result = new StringBuffer();
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(API_HOME + uri);
            post.setEntity(new UrlEncodedFormEntity(parameters));
            HttpResponse response = client.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return new JSONObject(result.toString());
        } catch (UnsupportedEncodingException e) {
            callback.onFailure(context, e, null);
        } catch (JSONException e) {
            callback.onFailure(context, e, null);
        } catch (ClientProtocolException e) {
            callback.onFailure(context, e, null);
        } catch (IOException e) {
            callback.onFailure(context, e, null);
        }
        return null;
    }

    protected JSONObject get(String uri, List<NameValuePair> parameters) {
        StringBuffer result = new StringBuffer();
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(API_HOME + uri + "?"+ URLEncodedUtils.format(parameters, "utf-8"));
            HttpResponse response = client.execute(get);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return new JSONObject(result.toString());
        } catch (JSONException e) {
            callback.onFailure(context, e, null);
        } catch (ClientProtocolException e) {
            callback.onFailure(context, e, null);
        } catch (UnsupportedEncodingException e) {
            callback.onFailure(context, e, null);
        } catch (IOException e) {
            callback.onFailure(context, e, null);
        }
        return null;
    }

    protected JSONObject put(String uri, List<NameValuePair> parameters) {
        StringBuffer result = new StringBuffer();
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPut put = new HttpPut(API_HOME + uri);
            put.setEntity(new UrlEncodedFormEntity(parameters));
            HttpResponse response = client.execute(put);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            return new JSONObject(result.toString());
        } catch (JSONException e) {
            callback.onFailure(context, e, null);
        } catch (ClientProtocolException e) {
            callback.onFailure(context, e, null);
        } catch (UnsupportedEncodingException e) {
            callback.onFailure(context, e, null);
        } catch (IOException e) {
            callback.onFailure(context, e, null);
        }
        return null;
    }
}
