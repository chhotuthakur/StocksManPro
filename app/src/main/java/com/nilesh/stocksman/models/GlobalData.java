package com.nilesh.stocksman.models;

public class GlobalData {

    String  market_type, region, local_open, local_close, current_status;

    public GlobalData() {
    }

    public GlobalData(String market_type, String region, String local_open, String local_close, String current_status) {
        this.market_type = market_type;
        this.region = region;
        this.local_open = local_open;
        this.local_close = local_close;
        this.current_status = current_status;
    }

    public String getMarket_type() {
        return market_type;
    }

    public void setMarket_type(String market_type) {
        this.market_type = market_type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLocal_open() {
        return local_open;
    }

    public void setLocal_open(String local_open) {
        this.local_open = local_open;
    }

    public String getLocal_close() {
        return local_close;
    }

    public void setLocal_close(String local_close) {
        this.local_close = local_close;
    }

    public String getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(String current_status) {
        this.current_status = current_status;
    }
}
