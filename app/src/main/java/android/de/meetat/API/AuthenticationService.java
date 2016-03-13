package android.de.meetat.API;

/**
 * Created by mahieke on 12.03.16.
 */
public interface AuthenticationService {

    /**
     * @param nickname String
     * @param email String
     * @param password String
     */
    void signup(String nickname, String email, String password, ServiceCallback<Boolean> callback);

    /**
     * @param email String
     * @param password String
     */
    void signin(String email, String password, ServiceCallback<Boolean> callback);
}
