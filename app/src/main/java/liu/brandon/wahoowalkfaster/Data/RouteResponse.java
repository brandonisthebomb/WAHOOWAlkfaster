package liu.brandon.wahoowalkfaster.Data;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Brandon on 8/14/16.
 */

public class RouteResponse {

    @SerializedName("data")
    private List<Route> routes;
    @SerializedName("rate_limit")
    private int rateLimit;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("api_latest_version")
    private String apiLatestVersion;
    @SerializedName("generated_on")
    private String generatedOn;
    @SerializedName("api_version")
    private String apiVersion;

    public List<Route> getRouteList() {
        return this.routes;
    }

    public void setRouteList(List<Route> routes) {
        this.routes = routes;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
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

    public Map<String, Route> getRouteMap() {
        Map<String, Route> map = new HashMap<>();
        List<Route> routeList = this.getRouteList();
        for (int i = 0; i < routeList.size(); i++) {
            Route route = routeList.get(i);
            String id = route.getId();
            map.put(id, route);
        }
        return map;
    }

}
