package com.hmkcode.android;
 
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
 
public class MainActivity extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks, 
GooglePlayServicesClient.OnConnectionFailedListener, com.google.android.gms.location.LocationListener {

	LocationClient myLocationClient;
	GoogleMap myMap;
	private static final LocationRequest REQUEST = LocationRequest.create()
			.setInterval(5000)
			.setFastestInterval(16)
			.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		myMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		if (myMap != null) {
			myMap.setMyLocationEnabled(true);
		}
		myLocationClient = new LocationClient(getApplicationContext(), this, this);
		if (myLocationClient != null) {
			myLocationClient.connect();	
		}
	}

	@Override
	public void onLocationChanged(Location arg0) {
		myMap.moveCamera(CameraUpdateFactory.newCameraPosition(
				new CameraPosition.Builder().target(new LatLng(12, 15))
				.zoom(15.5f)
				.bearing(0)
				.tilt(25)
				.build()));
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		Log.d("bug", "onConnectionFailed");
	}

	@Override
	public void onConnected(Bundle arg0) {
		myLocationClient.requestLocationUpdates(REQUEST, this);
	}

	@Override
	public void onDisconnected() {
		Log.d("bug", "onDisconnected");
	}

}
