package liu.brandon.wahoowalkfaster.Data;

import android.graphics.Color;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by brandon on 12/31/16.
 */

public class ArrivalDeserializer implements JsonDeserializer<ArrivalResponse> {

    private final static String TAG = "VehicleDeserializer";

    @Override
    public ArrivalResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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
        List<Vehicle> vehicles = new ArrayList<>();
        // Agency
        for (Map.Entry<String, JsonElement> entry : jsonData.entrySet()) {
            Log.d(TAG, entry.toString());

            // Vehicle
            for (JsonElement element : entry.getValue().getAsJsonArray()) {
                JsonObject jsonVehicle = element.getAsJsonObject();

                // vehicleID
                String vehicleID = jsonVehicle.getAsJsonPrimitive("vehicle_id").getAsString();
                // routeID
                String routeID = jsonVehicle.getAsJsonPrimitive("route_id").getAsString();
                // segmentID
                String segmentID = jsonVehicle.getAsJsonPrimitive("segment_id").getAsString();

                // Arrival estimates
                JsonArray jsonArrivals = jsonVehicle.getAsJsonArray("arrival_estimates");
                ArrayList<ArrivalEstimate> estimates = new ArrayList<>();
                for (JsonElement arrivalElement : jsonArrivals) {
                    JsonObject arrivalObject = arrivalElement.getAsJsonObject();
                    estimates.add(new ArrivalEstimate(
                            arrivalObject.getAsJsonPrimitive("route_id").getAsString(),
                            arrivalObject.getAsJsonPrimitive("arrival_at").getAsString(),
                            arrivalObject.getAsJsonPrimitive("stop_id").getAsString()));
                }

                // location
                JsonObject jsonLocation = jsonVehicle.getAsJsonObject("location");
                Location location = new Location(
                        jsonLocation.getAsJsonPrimitive("lat").getAsDouble(),
                        jsonLocation.getAsJsonPrimitive("lng").getAsDouble());

                // speed
                int speed = jsonVehicle.getAsJsonPrimitive("speed").getAsInt();

                // heading
                int heading = jsonVehicle.getAsJsonPrimitive("heading").getAsInt();

                // last updated on
                String lastUpdatedOn = jsonVehicle.getAsJsonPrimitive("last_updated_on").getAsString();


                Log.d(TAG, vehicleID);
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleID(vehicleID);
                vehicle.setRouteID(routeID);
                vehicle.setSegmentID(segmentID);
                vehicle.setArrivals(estimates);
                vehicle.setLocation(location);
                vehicle.setSpeed(speed);
                vehicle.setHeading(heading);
                vehicle.setLastUpdatedOn(lastUpdatedOn);
                vehicles.add(vehicle);
            }
        }

        // Build the final route response
        final ArrivalResponse response = new ArrivalResponse();
        response.setRateLimit(rateLimit);
        response.setExpiresIn(expiresIn);
        response.setApiLatestVersion(apiLatestVersion);
        response.setGeneratedOn(generatedOn);
        response.setApiVersion(apiVersion);
        response.setVehicleList(vehicles);
        return response;
    }
}
