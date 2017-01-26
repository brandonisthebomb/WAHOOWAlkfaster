package liu.brandon.wahoowalkfaster.Data;

import android.graphics.Color;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by brandon on 12/31/16.
 */

public class VehicleDeserializer implements JsonDeserializer<VehicleResponse> {

    private final static String TAG = "VehicleDeserializer";

    @Override
    public VehicleResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final JsonElement jsonRateLimit = jsonObject.getAsJsonPrimitive("rate_limit");
        final int rateLimit = jsonRateLimit.getAsInt();

        final JsonElement jsonExpiresIn = jsonObject.getAsJsonPrimitive("expires_in");
        final int expiresIn = jsonExpiresIn.getAsInt();

        final JsonElement jsonApiLatestVersion = jsonObject.getAsJsonPrimitive("api_latest_version");
        final String apiLatestVersion = jsonApiLatestVersion.getAsString();

        final JsonElement jsonGeneratedOn = jsonObject.getAsJsonPrimitive("generated_on");
        final String generatedOn = jsonGeneratedOn.getAsString();

        final JsonElement jsonApiVersion = jsonObject.getAsJsonPrimitive("api_version");
        final String apiVersion = jsonApiVersion.getAsString();

        final JsonObject jsonData = jsonObject.getAsJsonObject("data");
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        // Agency
        for (Map.Entry<String, JsonElement> entry : jsonData.entrySet()) {

            //Log.d(TAG, entry.toString()); Print out each agency in a string

            // Vehicle
            for (JsonElement element : entry.getValue().getAsJsonArray()) {
                //Log.d(TAG, element.toString());
                JsonObject jsonVehicle = element.getAsJsonObject();
                //Log.d(TAG, jsonVehicle.toString()); Print out each vehicle in a string

                // vehicleID
                String vehicleID = jsonVehicle.getAsJsonPrimitive("vehicle_id").getAsString();

                // routeID
                String routeID = jsonVehicle.getAsJsonPrimitive("route_id").getAsString();

                // segmentID
                JsonElement segmentElement = jsonVehicle.get("segment_id");
                String segmentID = "null";
                if (!(segmentElement instanceof JsonNull))
                       segmentID = segmentElement.getAsString();
                //Log.d(TAG, vehicleID + " " + routeID + " " + segmentID);


                // Arrival estimates
                JsonArray jsonArrivals = jsonVehicle.getAsJsonArray("arrival_estimates");
                ArrayList<ArrivalEstimate> estimates = new ArrayList<>();
                for (JsonElement arrivalElement : jsonArrivals) {
                    JsonObject arrivalObject = arrivalElement.getAsJsonObject();
                    //Log.d(TAG, "arrival");
                    //Log.d(TAG, arrivalObject.toString());
                    estimates.add(new ArrivalEstimate(
                            arrivalObject.getAsJsonPrimitive("route_id").getAsString(),
                            arrivalObject.getAsJsonPrimitive("arrival_at").getAsString(),
                            arrivalObject.getAsJsonPrimitive("stop_id").getAsString()));
                }

                // tracking status
                String trackingStatus = jsonVehicle.getAsJsonPrimitive("tracking_status").getAsString();

                // location
                JsonObject jsonLocation = jsonVehicle.getAsJsonObject("location");
                //Log.d(TAG, jsonLocation.toString());
                Location location = new Location(
                        jsonLocation.getAsJsonPrimitive("lat").getAsDouble(),
                        jsonLocation.getAsJsonPrimitive("lng").getAsDouble());
                //Log.d(TAG, location.toString());


                // speed
                int speed = jsonVehicle.getAsJsonPrimitive("speed").getAsInt();
                //Log.d(TAG, speed + "");

                // heading
                int heading = jsonVehicle.getAsJsonPrimitive("heading").getAsInt();
                //Log.d(TAG, heading + "");

                // last updated on
                String lastUpdatedOn = jsonVehicle.getAsJsonPrimitive("last_updated_on").getAsString();
                //Log.d(TAG, lastUpdatedOn);

                //Log.d(TAG, vehicleID);
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleID(vehicleID);
                vehicle.setRouteID(routeID);
                vehicle.setSegmentID(segmentID);
                vehicle.setArrivals(estimates);
                vehicle.setLocation(location);
                vehicle.setSpeed(speed);
                vehicle.setHeading(heading);
                vehicle.setLastUpdatedOn(lastUpdatedOn);
                vehicle.setTrackingStatus(trackingStatus);
                vehicles.add(vehicle);
                Log.d(TAG, vehicle.toString());

            }
        }

        // Build the final route response
        final VehicleResponse response = new VehicleResponse();
        response.setRateLimit(rateLimit);
        response.setExpiresIn(expiresIn);
        response.setApiLatestVersion(apiLatestVersion);
        response.setGeneratedOn(generatedOn);
        response.setApiVersion(apiVersion);
        response.setVehicleList(vehicles);

        return response;
    }
}
