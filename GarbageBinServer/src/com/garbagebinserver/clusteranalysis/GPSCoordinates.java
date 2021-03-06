package com.garbagebinserver.clusteranalysis;

public class GPSCoordinates implements Coordinates {
  
  protected int m_clusterID;
  protected double m_latitude;
  protected double m_longitude;
  
  public GPSCoordinates( double latitude, double longitude ) {
    if( latitude < -90 || latitude > 90 ) {
      throw new IllegalArgumentException( "Latitude argument is outside of valid range [-90, 90]!" );
    }
    
    if( longitude < -180 || latitude > 180 ) {
      throw new IllegalArgumentException( "Longitude argument is outside of valid range [-180, 180]!" );
    }
    
    m_clusterID = -1;
    m_latitude = latitude;
    m_longitude = longitude;
  }
  
  public double getLatitude() {
    return m_latitude;
  }
  
  public void setLatitude( double latitude ) {
    m_latitude = latitude;
  }
  
  public double getLongitude() {
    return m_longitude;
  }
  
  public void setLongitude( double longitude ) {
    m_longitude = longitude;
  }

  @Override
  public double getDistance( Coordinates coordinates ) {
    if( !( coordinates instanceof GPSCoordinates ) ) {
      throw new IllegalArgumentException( "Cannot compute GPS distance between coordinates of different classes!" );
    }
    
    final double earthRadius = 6371;
    GPSCoordinates gpsCoordinates = ( GPSCoordinates )coordinates;
    
    double latitudeRadians1 = m_latitude * Math.PI / 180;
    double latitudeRadians2 = gpsCoordinates.getLatitude() * Math.PI / 180;
    
    double deltaLatitudeRadians = ( gpsCoordinates.getLatitude() - m_latitude ) * Math.PI / 180;
    double deltaLongitudeRadians = ( gpsCoordinates.getLongitude() - m_longitude ) * Math.PI / 180;
    
    double x = Math.sin( deltaLatitudeRadians / 2 );
    double xSquared = x * x;
    
    double y = Math.cos( latitudeRadians1 ) * Math.cos( latitudeRadians2 );
    
    double z = Math.sin( deltaLongitudeRadians / 2 );
    double zSquared = z * z;
    
    double a = xSquared + y * zSquared;
    double c = 2 * Math.atan2( Math.sqrt( a ), Math.sqrt( 1 - a ) );
    double gpsDistance = earthRadius * c;
    
    return gpsDistance;
  }
  
  public CartesianCoordinates convertToCartesianCoordinates() {
    double latitudeRadians = m_latitude * Math.PI / 180;
    double longitudeRadians = m_longitude * Math.PI / 180;
    
    double xCoordinate = Math.cos( latitudeRadians ) * Math.cos( longitudeRadians );
    double yCoordinate = Math.cos( latitudeRadians ) * Math.sin( longitudeRadians );
    double zCoordinate = Math.sin( latitudeRadians );
    
    return new CartesianCoordinates( xCoordinate, yCoordinate, zCoordinate );
  }
  
  public int getClusterID() {
    return m_clusterID;
  }
  
  public void setClusterID( final int clusterID ) {
    m_clusterID = clusterID;
  }
}
