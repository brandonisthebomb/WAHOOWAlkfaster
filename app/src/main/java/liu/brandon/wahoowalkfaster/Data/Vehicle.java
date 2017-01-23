package liu.brandon.wahoowalkfaster.Data;

import java.util.ArrayList;

/**
 * Created by brandon on 12/30/16.
 */

public class Vehicle {
    private String vehicleID;
    private String segmentID;
    private String routeID;
    private ArrayList<ArrivalEstimate> arrivals;
    private String lastUpdatedOn;
    private double speed;
    private String trackingStatus;
    private Location location;
    private int heading;

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getSegmentID() {
        return segmentID;
    }

    public void setSegmentID(String segmentID) {
        this.segmentID = segmentID;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public ArrayList<ArrivalEstimate> getArrivals() {
        return arrivals;
    }

    public void setArrivals(ArrayList<ArrivalEstimate> arrivals) {
        this.arrivals = arrivals;
    }

    public String getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(String lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String getTrackingStatus() {
        return trackingStatus;
    }

    public void setTrackingStatus(String trackingStatus) {
        this.trackingStatus = trackingStatus;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID='" + vehicleID + '\'' +
                ", segmentID='" + segmentID + '\'' +
                ", routeID='" + routeID + '\'' +
                ", arrivals=" + arrivals +
                ", lastUpdatedOn='" + lastUpdatedOn + '\'' +
                ", speed=" + speed +
                ", trackingStatus='" + trackingStatus + '\'' +
                ", location=" + location +
                ", heading=" + heading +
                '}';
    }
}
