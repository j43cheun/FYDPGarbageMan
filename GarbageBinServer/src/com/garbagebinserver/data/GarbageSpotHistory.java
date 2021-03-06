package com.garbagebinserver.data;

import java.sql.Date;

public class GarbageSpotHistory {
  
  private int m_garbageSpotID;
  private double m_volume;
  private Date m_date;
  
  public GarbageSpotHistory( final int garbageSpotID, final double volume, final Date date ) {
    m_garbageSpotID = garbageSpotID;
    m_volume = volume;
    m_date = date;
  }
  
  public int getGarbageSpotID() {
    return m_garbageSpotID;
  }
  
  public double getVolume() {
    return m_volume;
  }
  
  public Date getDate() {
    return m_date;
  }
}
