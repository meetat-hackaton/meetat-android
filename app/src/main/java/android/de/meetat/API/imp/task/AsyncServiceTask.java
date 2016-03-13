package android.de.meetat.API.imp.task;

import android.content.Context;
import android.de.meetat.API.NoConnectionException;
import android.de.meetat.API.ServiceCallback;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

/**
 * @author Philipp Daniels
 */
public abstract  class AsyncServiceTask<Params, Progress, Result> extends
        AsyncTask<Params, Progress, Result> {

    protected Context context;

    protected Exception exception;

    protected ServiceCallback<Result> callback;

    protected AsyncServiceTask(Context context, ServiceCallback<Result> callback) {
        this.context = context;
        this.callback = callback;
    }

    public void checkConnection() throws NoConnectionException {
        if (!hasNetworkConnection())
            throw new NoConnectionException();
    }

    /**
     * Checks whether the device currently has a network connection
     *
     * @return true if is online otherwise false
     * @throws NoConnectionException
     */
    protected boolean hasNetworkConnection()
    {
        boolean result = false;
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            result = true;
        }
        return result;
    }

    /**
     * Gets a unique key of the current android device.
     *
     * @return
     */
    protected String getDevice()
    {
        // FIXME: // TODO: add some real device information.
        return "android-1";
    }

    /**
     * Checks the connection.
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        try {
            callback.onPreExecute();
            checkConnection();
        } catch (NoConnectionException e) {
            cancelWithException(e);
        }
    }

    @Override
    protected void onPostExecute(Result result) {
        super.onPostExecute(result);
        callback.onDataReceived(result);
    }

    @Override
    protected void onCancelled(Result result) {
        if (exception != null) {
            callback.onFailure(context, exception, result);
        }
    }

    protected void cancelWithException(Exception ex) {
        exception = ex;
        cancel(true);
    }

}

