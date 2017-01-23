package liu.brandon.wahoowalkfaster.Data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Brandon on 1/10/17.
 */

public class ArrivalResponse {
    @SerializedName("data")
    private List<Vehicle> vehicles;
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

    public List<Vehicle> getArrivalList() {
        return this.vehicles;
    }

    public void setVehicleList(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
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
}
