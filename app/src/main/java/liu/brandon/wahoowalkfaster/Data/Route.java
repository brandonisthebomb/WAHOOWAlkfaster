package liu.brandon.wahoowalkfaster.Data;

import java.util.Map;

/**
 * Created by Brandon on 8/16/16.
 */

public class Route {

    private String id;
    private Map<String, String> segments;
    private String[] stops;
    private boolean isActive;
    private int agencyId;
    private String longName;
    private String type;
    private int color;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getSegments() {
        return segments;
    }

    public void setSegments(Map<String, String> segments) {
        this.segments = segments;
    }

    public String[] getStops() {
        return stops;
    }

    public void setStops(String[] stops) {
        this.stops = stops;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
