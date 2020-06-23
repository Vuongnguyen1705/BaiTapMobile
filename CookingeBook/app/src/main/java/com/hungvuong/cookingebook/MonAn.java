package com.hungvuong.cookingebook;

public class MonAn {
  private String Ten;
  private int Avatar;
  private int IconHighRes;
  private String LinkDetail;
  private String LinkHighRes;
  
  public MonAn(String ten, int avatar, int iconHighRes, String linkDetail, String linkHighRes) {
    Ten = ten;
    Avatar = avatar;
    IconHighRes = iconHighRes;
    LinkDetail = linkDetail;
    LinkHighRes = linkHighRes;
  }
  
  public String getLinkDetail() {
    return LinkDetail;
  }
  
  public void setLinkDetail(String linkDetail) {
    LinkDetail = linkDetail;
  }
  
  public String getLinkHighRes() {
    return LinkHighRes;
  }
  
  public void setLinkHighRes(String linkHighRes) {
    LinkHighRes = linkHighRes;
  }
  
  public String getTen() {
    return Ten;
  }
  
  public void setTen(String ten) {
    Ten = ten;
  }
  
  public int getAvatar() {
    return Avatar;
  }
  
  public void setAvatar(int avatar) {
    Avatar = avatar;
  }
  
  public int getIconHighRes() {
    return IconHighRes;
  }
  
  public void setIconHighRes(int iconHighRes) {
    IconHighRes = iconHighRes;
  }
  
}
