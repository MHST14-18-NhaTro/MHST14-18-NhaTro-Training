package mhst.project.locationservicedemo;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements 
GooglePlayServicesClient.ConnectionCallbacks,
GooglePlayServicesClient.OnConnectionFailedListener {

	LocationClient mLocationClient;
	private TextView addressLabel;
	private TextView locationLabel;
	private Button getLocationBtn;
	private Button disconnectBtn;
	private Button connectBtn;
	
	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_main);
		locationLabel = (TextView)findViewById(R.id.locationLabel);
		addressLabel = (TextView)findViewById(R.id.addressLabel);
		getLocationBtn = (Button)findViewById(R.id.getLocation);
		mLocationClient = new LocationClient(this, this, this);
		
		getLocationBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				displayCurrentLocation();
			}
		});
		disconnectBtn = (Button)findViewById(R.id.disconnect);
		disconnectBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mLocationClient.disconnect();
				locationLabel.setText("Got disconnected...");
			}
		});
		connectBtn = (Button)findViewById(R.id.connect);
		connectBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mLocationClient.connect();
				locationLabel.setText("Got connected...");
			}
		});
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onStart()
	 */
	@Override
	protected void onStart() {
		super.onStart();
		mLocationClient.connect();
		locationLabel.setText("Got connected...");
	}

	/* (non-Javadoc)
	 * @see android.support.v4.app.FragmentActivity#onStop()
	 */
	@Override
	protected void onStop() {
		mLocationClient.disconnect();		
		super.onStop();
		locationLabel.setText("Got disconnected...");
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		Toast.makeText(this, "Connection Failure : " + result, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onConnected(Bundle connectionHint) {
		Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDisconnected() {
		Toast.makeText(this, "Disconnected. Please re-connect", Toast.LENGTH_SHORT).show();
	}

	public void displayCurrentLocation() {
		Location currentLocation = mLocationClient.getLastLocation();
		String msg = "Current Location: " +
		        Double.toString(currentLocation.getLatitude()) + ", " +
				Double.toString(currentLocation.getLongitude());
		locationLabel.setText(msg);
		(new GetAddressTask(this)).execute(currentLocation);
	}
	
	private class GetAddressTask extends AsyncTask<Location, Void, String> {

		Context mContext;
		public GetAddressTask(Context context) {
			super();
			mContext = context;
		}
		
		/* (non-Javadoc)
		 * @see android.os.AsyncTask#onPostExecute(java.lang.Object)
		 */
		@Override
		protected void onPostExecute(String address) {
			addressLabel.setText(address);
		}

		@Override
		protected String doInBackground(Location... params) {
			Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
			Location loc = params[0];
			List<Address> addresses = null;
			try {
				addresses = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
			} catch (IOException e1) {
				Log.e("LocationSampleActivity", "IO Exception in getFromLocation()");
				e1.printStackTrace();
				return "IO Exception trying to get address";
			} catch (IllegalArgumentException e2) {
				String errorString = "Illegal arguments " +
				        Double.toString(loc.getLatitude()) + ", " +
						Double.toString(loc.getLongitude()) +
						" passed to address service";
				Log.e("LocationSampleActivity", errorString);
				e2.printStackTrace();
				return errorString;
			}
			if (addresses != null && addresses.size() > 0) {
				Address address = addresses.get(0);
				String addressText = String.format("%s %s %s", 
						address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
						address.getLocality(),
						address.getCountryName());
				return addressText;
			} else {
				return "No address found";
			}
		}
		
	}
}
