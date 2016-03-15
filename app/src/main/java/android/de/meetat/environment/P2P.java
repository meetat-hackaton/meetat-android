package android.de.meetat.environment;

import android.content.Context;
import android.de.meetat.API.ServiceCallback;
import android.de.meetat.API.ServiceFactor;
import android.de.meetat.Session;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import ch.uepaa.p2pkit.P2PKitClient;
import ch.uepaa.p2pkit.P2PKitStatusCallback;
import ch.uepaa.p2pkit.StatusResult;
import ch.uepaa.p2pkit.StatusResultHandling;
import ch.uepaa.p2pkit.discovery.InfoTooLongException;
import ch.uepaa.p2pkit.discovery.P2PListener;
import ch.uepaa.p2pkit.discovery.entity.Peer;

/**
 * Created by mahieke on 13.03.16.
 */
public class P2P {
    private final String APP_KEY = "eyJzaWduYXR1cmUiOiJWbDk4NkY0cjMyWVRrYUl0UWhxdkRrdjJ3V3I2ZXJDMTRpVjZFSmI2UnVFT1luZXhFclhEc1k4Zi9PSkRCVi8yZUdqMXp5Vi9CRmEwWS8xdWtwUVFCaGFWcWlZR3FFU1dlTnBnOHUrNE1xa2VWSjFlNGdES3dQbXJ4TkJxTVNUbURLdUdiZlZFQzhsc01lTHk0R1REd2djM0pDcjlSQ2FOS0JyTVBHc3JEVlk9IiwiYXBwSWQiOjE0ODMsInZhbGlkVW50aWwiOjE2OTYyLCJhcHBVVVVJRCI6IjQxMDEyMjRELURDOEEtNDMyRC05NkQyLUY1NENCMUMxMjVGQyJ9";
    private final String TAG = "MESSAGE";
    private static P2P p2pkit = null;
    private Context context;
    Map<UUID, String> peers = new ConcurrentHashMap<UUID, String>();

    private P2P(Context context) {
        this.context = context;
        final StatusResult result = P2PKitClient.isP2PServicesAvailable(context);
        if (result.getStatusCode() == StatusResult.SUCCESS) {
            P2PKitClient client = P2PKitClient.getInstance(context);
            client.enableP2PKit(mStatusCallback, APP_KEY);

            P2PKitClient.getInstance(context).getDiscoveryServices().addP2pListener(mP2PDiscoveryListener);

            try {
                P2PKitClient.getInstance(context).getDiscoveryServices()
                        .setP2pDiscoveryInfo(Session.getSessionReminder().getNickname().getBytes());
            } catch (InfoTooLongException e) {
                Log.d(TAG, "P2PListener | The discovery info is too long");
            }

        } else {
            StatusResultHandling.showAlertDialogForStatusError(context, result);
        }
    }

    public static P2P getSessionReminder(Context context) {
        if(p2pkit == null){
            p2pkit = new P2P(context);
        }
        p2pkit.context = context;
        return p2pkit;
    }

    private final P2PKitStatusCallback mStatusCallback = new P2PKitStatusCallback() {
        @Override
        public void onEnabled() {
            // ready to start discovery

        }

        @Override
        public void onSuspended() {
            // p2pkit is now suspended
        }

        @Override
        public void onError(StatusResult statusResult) {
            // enabling failed, handle statusResult
        }
    };

    private final P2PListener mP2PDiscoveryListener = new P2PListener() {
        @Override
        public void onP2PStateChanged(int state) {
            Log.d(TAG, "P2PListener | State changed: " + state);
        }

        @Override
        public void onPeerDiscovered(final Peer peer) {
            final UUID discoveredUUID = peer.getNodeId();
            final String discoveredInfo = new String(peer.getDiscoveryInfo());
            Log.d(TAG, "P2PListener | Peer discovered: " + discoveredUUID + " with info: " + discoveredInfo);
            // search for nickname in database
            ServiceFactor.getQueryService(context).checkNickname(discoveredInfo, new ServiceCallback<JSONObject>() {
                @Override
                public void onDataReceived(JSONObject data) {
                    super.onDataReceived(data);
                    try {
                        Boolean wasfound = (Boolean) data.get("success");

                        if (wasfound) {
                            if (!peers.containsKey(discoveredUUID)) {
                                peers.put(discoveredUUID, discoveredInfo);
                            } else {
                                // Just update:
                                peers.remove(discoveredUUID);
                                peers.put(discoveredUUID, discoveredInfo);
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onPeerLost(Peer peer) {
            final UUID discoveredUUID = peer.getNodeId();
            Log.d(TAG, "P2PListener | Peer lost: " + discoveredUUID);
            peers.remove(discoveredUUID);
        }

        @Override
        public void onPeerUpdatedDiscoveryInfo(final Peer peer) {

            final UUID discoveredUUID = peer.getNodeId();
            final String discoveredInfo = new String(peer.getDiscoveryInfo());
            Log.d(TAG, "P2PListener | Peer updated: " + discoveredUUID + " with new info: " + discoveredInfo);
            // search for nickname in database
            ServiceFactor.getQueryService(context).checkNickname(discoveredInfo, new ServiceCallback<JSONObject>() {
                @Override
                public void onDataReceived(JSONObject data) {
                    super.onDataReceived(data);
                    try {
                        Boolean wasfound = (Boolean) data.get("success");

                        if (wasfound) {
                            if (!peers.containsKey(discoveredUUID)) {
                                // THIS SHOULD NOT HAPPEN,
                                // TODO: ERROR HANDLING NEEDED
                                // BUT FOR NOW JUST ADD AN ENTRY
                                peers.put(discoveredUUID, discoveredInfo + "withERROR");
                            } else {
                                peers.remove(discoveredUUID);
                                peers.put(discoveredUUID, discoveredInfo);
                            }
                        }
                        else {
                            // Delete entry, becaus discoveredInfo was not found in model
                            peers.remove(discoveredUUID);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}
