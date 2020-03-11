package entity;

import org.json.JSONObject;
import utility.LatLng;

import java.util.List;

public class Route {
    private Location fromLoc;
    private Location toLoc;
    private double total_weight;
    private double price;
    private double timeLength;
    private List<RouteInfo> routes;

    public Location getFromLoc() {
        return fromLoc;
    }

    public void setFromLoc(Location fromLoc) {
        this.fromLoc = fromLoc;
    }

    public Location getToLoc() {
        return toLoc;
    }

    public void setToLoc(Location toLoc) {
        this.toLoc = toLoc;
    }

    public double getTotal_weight() {
        return total_weight;
    }

    public void setTotal_weight(double total_weight) {
        this.total_weight = total_weight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTimeLength() {
        return this.timeLength;
    }

}
