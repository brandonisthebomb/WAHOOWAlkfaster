package liu.brandon.wahoowalkfaster;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Brandon on 7/12/16.
 */
public class Stop {

/*
    JSON response:
    {"code": "002", "description": "", "url": "", "parent_station_id": null, "agency_ids": ["347"],
        "station_id": null, "location_type": "stop", "location": {"lat": 38.037573, "lng": -78.497789},
        "stop_id": "4123738", "routes": ["4003290", "4003478"], "name": "14th Street NW @ Virginia Ave"},
    (in a list)
*/

    @SerializedName("name")
    private String name;
    @SerializedName("stop_id")
    private String stopId;
    @SerializedName("code")
    private String code;

    @SerializedName("routes")
    private List<String> routes;
    @SerializedName("location")
    private Location location;
    @SerializedName("agency_ids")
    private String[] agencyIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(List<String> routes) {
        this.routes = routes;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String[] getAgencyIds() {
        return agencyIds;
    }

    public void setAgencyIds(String[] agencyIds) {
        this.agencyIds = agencyIds;
    }

    @Override
    public String toString() {
        return name;
    }
}
