package liu.brandon.wahoowalkfaster.Data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brandon on 12/30/16.
 */

public class VehicleResponse {
    @SerializedName("data")
    private ArrayList<Vehicle> vehicles;
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

    public ArrayList<Vehicle> getVehicleList() {
        return this.vehicles;
    }

    public void setVehicleList(ArrayList<Vehicle> vehicles) {
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
