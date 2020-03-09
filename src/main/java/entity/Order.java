package entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Order {
    //order_id, user_id, from_location, to_location, drone_id, time_start, time_end, total_weight, price
    private String orderId;
    private String userId;
    private Location fromLoc;
    private Location toLoc;
    private String droneId;
    private String timeStart;
    private String timeEnd;
    private double totalWeight;
    private double price;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromLocString() {
        return fromLoc.stringfy();
    }

    public void setFromLoc(Location fromLoc) {
        this.fromLoc = fromLoc;
    }

    public String getToLocString() {
        return toLoc.stringfy();
    }

    public void setToLoc(Location toLoc) {
        this.toLoc = toLoc;
    }

    public String getDroneId() {
        return droneId;
    }

    public void setDroneId(String droneId) {
        this.droneId = droneId;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("order_id", orderId);
            obj.put("user_id", userId);
            obj.put("location_from", fromLoc);
            obj.put("location_to", toLoc);
            obj.put("drone_id", droneId);
            obj.put("time_start", timeStart);
            obj.put("time_end", timeEnd);
            obj.put("total_weight", totalWeight);
            obj.put("price", price);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private Order(OrderBuilder builder) {
        this.orderId = builder.orderId;
        this.userId = builder.userId;
        this.fromLoc = builder.fromLoc;
        this.toLoc = builder.toLoc;
        this.droneId =  builder.droneId;
        this.timeStart =  builder.timeStart;
        this.timeEnd = builder.timeEnd;
        this.totalWeight = builder.totalWeight;
        this.price = builder.price;
    }

    public static class OrderBuilder {
        private String orderId;
        private String userId;
        private Location fromLoc;
        private Location toLoc;
        private String droneId;
        private String timeStart;
        private String timeEnd;
        private double totalWeight;
        private double price;

        public OrderBuilder setOrderId(String orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public OrderBuilder setFromLoc(String fromLoc) {
            JSONObject json = new JSONObject(fromLoc);
            this.fromLoc = new Location(json);
            return this;
        }

        public OrderBuilder setToLoc(String toLoc){
            JSONObject json = new JSONObject(toLoc);
            this.toLoc = new Location(json);
            return this;
        }

        public OrderBuilder setFromLoc(JSONObject fromLoc) {
            this.fromLoc = new Location(fromLoc);
            return this;
        }

        public OrderBuilder setToLoc(JSONObject toLoc) {
            this.toLoc = new Location(toLoc);
            return this;
        }

        public OrderBuilder setDroneId(String droneId) {
            this.droneId = droneId;
            return this;
        }

        public OrderBuilder setTimeStart(String timeStart) {
            this.timeStart = timeStart;
            return this;
        }

        public OrderBuilder setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
            return this;
        }

        public OrderBuilder setTotalWeight(double totalWeight) {
            this.totalWeight = totalWeight;
            return this;
        }

        public OrderBuilder setPrice(double price) {
            this.price = price;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
