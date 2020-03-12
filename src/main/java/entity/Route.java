package entity;

import org.json.JSONObject;
import utility.LatLng;

import java.util.ArrayList;
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

    public void setRoutes(List<RouteInfo> r) { this.routes = r; }

    public List<RouteInfo> getRoutes() { return this.routes; }

    public void setRouteID(Integer i, String id) {
        this.routes.get(i).setRouteID(id);
    }

    public Route (RouteBuilder builder) {
        this.fromLoc = builder.fromLoc;
        this.toLoc = builder.toLoc;
        this.price = builder.price;
        this.timeLength = builder.timeLength;
        this.total_weight = builder.total_weight;
        this.routes = builder.routes;
    }

    public static class RouteBuilder {
        private Location fromLoc;
        private Location toLoc;
        private double total_weight;
        private double price;
        private double timeLength;
        private List<RouteInfo> routes;

        public RouteBuilder setFromLoc(Location fromLoc) {
            this.fromLoc = new Location(fromLoc);
            return this;
        }

        public RouteBuilder setToLoc(Location toLoc) {
            this.toLoc = new Location(toLoc);
            return this;
        }

        public RouteBuilder setTotalWeight(double tw) {
            this.total_weight = tw;
            return this;
        }

        public RouteBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public RouteBuilder setTimeLength(double tl) {
            this.timeLength = tl;
            return this;
        }

        public RouteBuilder setRoutes(List<RouteInfo> rs) {
            this.routes = new ArrayList<RouteInfo>(rs);
            return this;
        }

        public Route build() {
            return new Route(this);
        }

    }

}
