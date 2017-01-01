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
}
