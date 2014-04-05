package com.hackgood.nvolveu.app;

import java.io.IOException;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GpsActivity extends FragmentActivity implements LocationListener {
	
	GoogleMap googleMap;
	MarkerOptions markerOptions;
	LatLng latLng;
	
	Double latitud;
	Double longitud;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gpsactivity);		
		
		SupportMapFragment supportMapFragment = (SupportMapFragment) 
				getSupportFragmentManager().findFragmentById(R.id.map);
		
		
		// Getting a reference to the map
		googleMap = supportMapFragment.getMap();
		
		//Seteamos el tipo de mapa 
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		//Activamos la capa o layer MyLocation
		googleMap.setMyLocationEnabled(true);
		
		// Getting LocationManager object from System Service LOCATION_SERVICE
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        
        // Creating a criteria object to retrieve provider
        Criteria criteria = new Criteria();

        // Getting the name of the best provider
        String provider = locationManager.getBestProvider(criteria, true);

        // Getting Current Location
        Location location = locationManager.getLastKnownLocation(provider);

        if(location!=null){
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(provider, 10000, 0, this);
        
        locationManager.removeUpdates(this);
        
        Toast.makeText(getApplicationContext(), "Seleciona la zona de interes", Toast.LENGTH_LONG).show();
		
		// Setting a click event handler for the map
		googleMap.setOnMapClickListener(new OnMapClickListener() {
			
			int count = 0;
			@Override
			public void onMapClick(LatLng arg0) {	
				

				// Getting the Latitude and Longitude of the touched location
				latLng = arg0;
				
				// Clears the previously touched position
				googleMap.clear();
				
				// Animating to the touched position                				
				googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));				
				
				// Creating a marker
				markerOptions = new MarkerOptions();
				
				// Setting the position for the marker
				markerOptions.position(latLng);		
				
				// Placing a marker on the touched position
				googleMap.addMarker(markerOptions);
				Toast.makeText(getApplicationContext(), "Confirma la ubicación presionando de nuevo", Toast.LENGTH_SHORT).show();
				
				// Adding Marker on the touched location with address
		    	new ReverseGeocodingTask(getBaseContext()).execute(latLng);	
		    	
			}
		});
	}
	
    @Override
    public void onLocationChanged(Location location) {
 
        // Getting latitude of the current location
        double latitude = location.getLatitude();
 
        // Getting longitude of the current location
        double longitude = location.getLongitude();
 
        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
 
        // Showing the current location in Google Map
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
 
        // Zoom in the Google Map
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
 
    }
 
    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }
 
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
	
	private class ReverseGeocodingTask extends AsyncTask<LatLng, Void, String>{
		Context mContext;
		
		public ReverseGeocodingTask(Context context){
			super();
			mContext = context;
		}

		// Finding address using reverse geocoding
		@Override
		protected String doInBackground(LatLng... params) {
			Geocoder geocoder = new Geocoder(mContext);
			double latitude = params[0].latitude;
			double longitude = params[0].longitude;
			
			longitud = longitude;
			latitud = latitude;
			
			List<Address> addresses = null;
			String addressText="";
			
			try {
				addresses = geocoder.getFromLocation(latitude, longitude,1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			if(addresses != null && addresses.size() > 0 ){
				Address address = addresses.get(0);
				
				addressText = String.format("%s, %s, %s",
	                    address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
	                    address.getLocality(),	                    
	                    address.getCountryName());				
			}
			
			return addressText;
		}		
		
		@Override
		protected void onPostExecute(String addressText) {
			// Setting the title for the marker. 
			// This will be displayed on taping the marker
			markerOptions.title(addressText);
			markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			
			// Placing a marker on the touched position
			googleMap.addMarker(markerOptions);
			ejecutar_inter();
			}
					
		}	
	
    public void ejecutar_inter() {
        Intent i = new Intent(this, intermediate.class);
        Bundle b1 = new Bundle();
        b1.putDouble("latitud", latitud);
        i.putExtras(b1);
        Bundle b2 = new Bundle();
        b2.putDouble("longitud", longitud);
        i.putExtras(b2);
        startActivity(i);
    }

    
	/*NotificationCompat.Builder mBuilder =
    new NotificationCompat.Builder(MainActivity.this)
        .setSmallIcon(android.R.drawable.stat_sys_warning)
        .setLargeIcon((((BitmapDrawable)getResources()
        .getDrawable(R.drawable.ic_launcher)).getBitmap()))
        .setContentTitle("Mensaje de Alerta")
        .setContentText("Ejemplo de notificación.")
        .setContentInfo("1")
        .setTicker("Alerta!");

Intent notIntent =
    new Intent(MainActivity.this, MainActivity.class);

PendingIntent contIntent =
    PendingIntent.getActivity(
        MainActivity.this, 0, notIntent, 0);

mBuilder.setContentIntent(contIntent);

NotificationManager mNotificationManager =
	    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

	mNotificationManager.notify(2, mBuilder.build());*/
}