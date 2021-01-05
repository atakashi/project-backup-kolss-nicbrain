/**
 * 
 */
package br.com.kolss.nicbrainmobile.util.gps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

/**
 * @author LuisCM
 *
 */
public class GPS implements LocationListener {

	private Context context = null;
	private LocationManager locationManager = null;

	public GPS() {
	}

	public GPS(final Context context) {
		this.context = context;
	}
	
	public void init() {
		// Get GPS location service LocationManager object
		locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
		
		/*
		  Parameters :
		     First(provider)    :  the name of the provider with which to register 
		     Second(minTime)    :  the minimum time interval for notifications, in milliseconds. This field is only used as a hint to conserve power, and actual time between location updates may be greater or lesser than this value. 
		     Third(minDistance) :  the minimum distance interval for notifications, in meters 
		     Fourth(listener)   :  a {#link LocationListener} whose onLocationChanged(Location) method will be called for each location update 
        */
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				3000,   // 3 sec
				10, this);
		
		// After registration onLocationChanged method called periodically after each 3 sec
	}
	
	/**
	 * close
	 */
	public void close() {
		locationManager.removeGpsStatusListener(null);
		locationManager.removeUpdates(this);
		locationManager = null;
	}
	
	/**
	 * Called after each 3 sec
	 * 
	 * @param location Location
	 */
	@Override
	public void onLocationChanged(final Location location) {
		final String str = "Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude();
		Toast.makeText(context, str, Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderDisabled(final String provider) {
		// Called when User off GPS
		Toast.makeText(context, "Gps desligado", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onProviderEnabled(final String provider) {
		// Called when User on GPS
		Toast.makeText(context, "Gps ligado", Toast.LENGTH_LONG).show();
	}

	@Override
	public void onStatusChanged(final String provider, final int status, final Bundle extras) {
		// TODO Auto-generated method stub
	}
	
}
