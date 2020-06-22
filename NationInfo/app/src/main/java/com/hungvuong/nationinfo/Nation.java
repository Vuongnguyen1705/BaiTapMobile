package com.hungvuong.nationinfo;

import java.io.Serializable;

public class Nation implements Serializable {
//  private String continent;
//  private String capital;
//  private String languages;
//  private  int geonameId;
//  private double south;
//  private String isoAlpha3;
//  private double north;
//  private String fipsCode;
  private int population;
//    private double east;
//  private String isoNumeric;
  private String areaInSqKm;
  private String countryCode;
//    private double west;
  private String countryName;
//  private String continentName;
//  private String currencyCode;
  private String flag;
  
  
  public int getPopulation() {
    return population;
  }
  
  public void setPopulation(int population) {
    this.population = population;
  }
  
  public String getAreaInSqKm() {
    return areaInSqKm;
  }
  
  public void setAreaInSqKm(String areaInSqKm) {
    this.areaInSqKm = areaInSqKm;
  }
  
  public String getCountryCode() {
    return countryCode;
  }
  
  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }
  
  public String getCountryName() {
    return countryName;
  }
  
  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }
  
  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }
  
  public Nation(int population, String areaInSqKm, String countryCode, String countryName, String flag) {
    this.population = population;
    this.areaInSqKm = areaInSqKm;
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.flag = flag;
  }
}

