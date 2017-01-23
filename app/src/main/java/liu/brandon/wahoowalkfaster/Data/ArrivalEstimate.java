package liu.brandon.wahoowalkfaster.Data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by brandon on 12/31/16.
 */

public class ArrivalEstimate {

    @SerializedName("route_id")
    private String routeID;
    @SerializedName("arrival_at")
    private String arrivalTime;
    @SerializedName("stop_id")
    private String stopID;

    public ArrivalEstimate (String routeID, String arrivalTime, String stopID) {
        this.routeID = routeID;
        this.arrivalTime = arrivalTime;
        this.stopID = stopID;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getStopID() {
        return stopID;
    }

    public void setStopID(String stopID) {
        this.stopID = stopID;
    }

    @Override
    public String toString() {
        return "Arrival Estimate route: " + routeID + ", arrival time: " + arrivalTime + ", stop: " + stopID;
    }
}
