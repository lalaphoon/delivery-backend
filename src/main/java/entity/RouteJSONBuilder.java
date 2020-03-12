package entity;

import org.json.JSONArray;
import org.json.JSONObject;
import utility.TimeStamp;

import java.util.List;

public class RouteJSONBuilder {

    public static double getPriceByDistanceAndType(double distance, String type) {
        if (type.equals("DRONE")){ //TODO: use enumerate instead of String
            return distance * 0.85;
        } else {
            return distance * 1.3;
        }
    }

    public static JSONArray getRouteOptionsResponse(final Route route){
        //Get A single route
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        List<RouteInfo> routes = route.getRoutes();
        for(RouteInfo i : routes) {
            JSONObject tmp = new JSONObject();
            tmp.put("route",i.getRouteDataJSONArray());
            tmp.put("distance", i.getDistance());
            tmp.put("deliver_type", i.getDeliverType());
            tmp.put("available_time", TimeStamp.getCurrentTimestamp()); //TODO: in future, timstamp should be given from data modal
            tmp.put("route_id", i.getRouteID());
            tmp.put("usage_time", "1 min"); //TODO: change the usage time
            tmp.put("price", i.getPrice());

            array.put(tmp);

        }
        return array;
    }
}
