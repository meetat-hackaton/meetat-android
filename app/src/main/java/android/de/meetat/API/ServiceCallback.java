package android.de.meetat.API;

import android.content.Context;
import android.de.meetat.R;
import android.view.View;
import android.widget.Toast;

/**
 * @author Philipp Daniels
 */
public class ServiceCallback<T> {

    private View view;

    public ServiceCallback(View view)
    {
        this.view = view;
    }

    public ServiceCallback()
    {
    }

    /**
     * Default Exception Handling. <br>
     * But you can also overwrite this.:-)
     *
     * @param context
     * @param ex
     * @param data
     */
    public void onFailure(Context context, Exception ex, T data) {
        setLoading(false);
        // default exception handling here.
        // but you can also overwrite and do an individual exception handling
        // TODO: Externalize Strings etc.
        if (ex instanceof NoConnectionException) {
            Toast.makeText(context, context.getResources().getString(R.string.error_network_no_connection),
                    Toast.LENGTH_LONG).show();

        } else if (ex != null) {
            // TODO: This is for development use only
            Toast.makeText(context, "Something went wrong: " + ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void onPreExecute() {
        setLoading(true);
    }

    public void onDataReceived(T data) {
        setLoading(false);
    }

    protected void setLoading(boolean loading) {
        if (view != null) {
            if (loading) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }
}
