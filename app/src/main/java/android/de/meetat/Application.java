package android.de.meetat;

/**
 * Created by raina on 12.03.2016.
 */
public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "Raleway-Regular.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "Raleway-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "Raleway-Regular.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "Raleway-Regular.ttf");
    }
}
