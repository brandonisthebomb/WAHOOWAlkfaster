package liu.brandon.wahoowalkfaster.Data;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Brandon on 8/14/16.
 */

public class StopResponse {
    @SerializedName("data")
    private List<Stop> stops;
    @SerializedName("rate_limit")
    private int rateLimit;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("api_latest_version")
    private String apiLatestVersion;
    @SerializedName("generated_on")
    private String generatedOn;

    public List<Stop> getStopList() {
        return this.stops;
    }

    public void setStopList(List<Stop> stops) {
        this.stops = stops;
    }

    public int getRateLimit() {
        return rateLimit;
    }

    public void setRateLimit(int rateLimit) {
        this.rateLimit = rateLimit;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getApiLatestVersion() {
        return apiLatestVersion;
    }

    public void setApiLatestVersion(String apiLatestVersion) {
        this.apiLatestVersion = apiLatestVersion;
    }

    public String getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(String generatedOn) {
        this.generatedOn = generatedOn;
    }

    public Map<String, Stop> getStopMap() {
        Map<String, Stop> map = new HashMap<>();
        List<Stop> stopList = this.getStopList();
        for (int i = 0; i < stopList.size(); i++) {
            Stop stop = stopList.get(i);
            String id = stop.getId();
            map.put(id, stop);
        }
        return map;
    }
}
