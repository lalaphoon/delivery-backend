package entity;

import org.json.JSONObject;

public class Location {
    private double lat;
    private double lng;
    private String address;

    Location(double lat, double lng, String address) {
        this.lat = lat;
        this.lng = lng;
        this.address = address;
    }

    Location(JSONObject obj) {
        if (obj.has("lat")){
            this.lat = obj.getDouble("lat");
        }
        if (obj.has("lng")) {
            this.lng = obj.getDouble("lng");
        }
        if (obj.has("address")) {
            this.address = obj.getString("address");
        }
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lng;
    }

    public void setLon(double lon) {
        this.lng = lon;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String stringfy() {
        JSONObject obj = this.getJson();
        return obj.toString();
    }
    public JSONObject getJson() {
        JSONObject obj = new JSONObject();
        obj.put("lat", this.lat);
        obj.put("lng", this.lng);
        return obj;
    }
}
