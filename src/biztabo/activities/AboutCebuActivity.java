package biztabo.activities;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.biztabo.R;
import com.google.android.gms.maps.model.LatLng;

public class AboutCebuActivity extends Activity {
	Location currentLocation;
	LocationListener locationListener;
    LocationManager locationManager;
	int isChanged;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_cebu);
		isChanged = 0;
		currentLocation = null;
		initialize();
    }
	
	 public void initialize() {
        locationListener = new myLocationListener();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(
            //LocationManager.GPS_PROVIDER, 6, 4, locationListener);
			LocationManager.NETWORK_PROVIDER, 6, 4, locationListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public void setUI()
	{
	if(isChanged==1)
	{
		TextView textData = (TextView)findViewById(R.id.aboutCebuText);
		try{
			if(currentLocation!=null)
			{
			 switch(staticDataComperator())
			 {
				case 0: textData.setText("Ayala");
				isChanged = 0;
				break;
				case 1: textData.setText("ITPark");
				isChanged = 0;
				break;
				case 2: textData.setText("SM");
				isChanged = 0;
				break;
			 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			Log.d("Location Error","error");
		}
	}
	}
	
	public int staticDataComperator(){
		int ret = 0;
		float x,y,z;
		LatLng temp = new LatLng( (currentLocation.getLatitude()*1E6),(currentLocation.getLongitude()*1E6));
		x= distanceBetweenPoints(temp, new LatLng((37.066684 * 1E6),(-95.676867 * 1E6)));
		y= distanceBetweenPoints(temp, new LatLng( (10.317611 * 1E6), (123.907659 * 1E6)));
		z= distanceBetweenPoints(temp, new LatLng( (10.311985* 1E6), (123.91843* 1E6)));
		if(y<z&&y<x)
		 ret = 1;
		if(z<y&&z<x)
		 ret = 2;
		
		return ret;
	}
	
	public float distanceBetweenPoints(LatLng curpoint, LatLng distpoint) {

			double lat1, lat2, long1, long2;
			float[] dist = new float[1];

			lat1 = curpoint.latitude / 1E6;
			long1 = curpoint.longitude / 1E6;
			lat2 = distpoint.latitude / 1E6;
			long2 = distpoint.longitude / 1E6;

			Location.distanceBetween(lat1, long1, lat2, long2, dist);
			return dist[0];
		}

	class StaticData{
	Location location;
	String text;
	public StaticData(Location location, String text)
	{
		this.text = text;
		this.location = location;
	}
	}
	class myLocationListener implements LocationListener {

         public void onLocationChanged(Location location) {
                 // TODO Auto-generated method stub

                 if (location != null) {
                        
                         Log.d("Location Listener",
                                         Double.toString(location.getLongitude()));
						if(currentLocation == null||(location.getLongitude() != currentLocation.getLongitude()
						&&location.getLatitude() != currentLocation.getLatitude())
						)
						{
						isChanged = 1;
							currentLocation = location;
							setUI();
						}
						
                 }

         }

         public void onProviderDisabled(String provider) {
                 // TODO Auto-generated method stub

         }

         public void onProviderEnabled(String provider) {
                 // TODO Auto-generated method stub

         }

         public void onStatusChanged(String provider, int status, Bundle extras) {
                 // TODO Auto-generated method stub

         }
 }
}
