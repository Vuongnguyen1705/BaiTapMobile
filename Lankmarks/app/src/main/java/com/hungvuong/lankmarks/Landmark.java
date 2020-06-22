package com.hungvuong.lankmarks;

public class Landmark {
  private double Latitude;
  private double Longitude;
  private String linkWeb;
  private String tenKhuVuc;
  
  public Landmark(double latitude, double longitude, String linkWeb, String tenKhuVuc) {
    Latitude = latitude;
    Longitude = longitude;
    this.linkWeb = linkWeb;
    this.tenKhuVuc = tenKhuVuc;
  }
  
  public double getLatitude() {
    return Latitude;
  }
  
  public void setLatitude(double latitude) {
    Latitude = latitude;
  }
  
  public double getLongitude() {
    return Longitude;
  }
  
  public void setLongitude(double longitude) {
    Longitude = longitude;
  }
  
  public String getLinkWeb() {
    return linkWeb;
  }
  
  public void setLinkWeb(String linkWeb) {
    this.linkWeb = linkWeb;
  }
  
  public String getTenKhuVuc() {
    return tenKhuVuc;
  }
  
  public void setTenKhuVuc(String tenKhuVuc) {
    this.tenKhuVuc = tenKhuVuc;
  }
}
