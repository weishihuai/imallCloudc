package com.imall.iportal.core.elasticsearch.entity;

/**
 * Created by lxh on 2017/8/15.
 */
public class WeShopEntity {
    public static final String ID = "id";
    public static final String SHOP_NM = "shopNm";
    public static final String SHOP_ZONE = "shopZone";
    public static final String SHOP_LAT = "shopLat";
    public static final String SHOP_LNG = "shopLng";
    public static final String DELIVERY_RANGE = "deliveryRange";
    public static final String CONTACT_TEL = "contactTel";
    public static final String IS_NORMAL_SALES = "isNormalSales";
    public static final String LOCATION = "location";

    private Long id;
    private String shopNm;
    private Long shopZone;
    private Double shopLat;
    private Double shopLng;
    private Long deliveryRange;
    private String contactTel;
    private String isNormalSales;
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShopNm() {
        return shopNm;
    }

    public void setShopNm(String shopNm) {
        this.shopNm = shopNm;
    }

    public Long getShopZone() {
        return shopZone;
    }

    public void setShopZone(Long shopZone) {
        this.shopZone = shopZone;
    }

    public Double getShopLat() {
        return shopLat;
    }

    public void setShopLat(Double shopLat) {
        this.shopLat = shopLat;
    }

    public Double getShopLng() {
        return shopLng;
    }

    public void setShopLng(Double shopLng) {
        this.shopLng = shopLng;
    }

    public Long getDeliveryRange() {
        return deliveryRange;
    }

    public void setDeliveryRange(Long deliveryRange) {
        this.deliveryRange = deliveryRange;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getIsNormalSales() {
        return isNormalSales;
    }

    public void setIsNormalSales(String isNormalSales) {
        this.isNormalSales = isNormalSales;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public static class Location{
        private Double lat;
        private Double lon;

        public Location(Double lat, Double lon){
            this.lat = lat;
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }
    }
}
