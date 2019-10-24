package example.athirapaul.photoconnect;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static example.athirapaul.photoconnect.R.id.ic_magnify;
import static example.athirapaul.photoconnect.R.id.input_search;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    DatabaseHelper db;
    private GoogleMap map;
    ImageButton ic_magnify;
    EditText addressField;
    LocationManager locationManager;
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    Button locate;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        EditText addressField = (EditText) findViewById(input_search);
        ImageButton ic_magnify = (ImageButton) findViewById(R.id.ic_magnify);
//locate = (Button)findViewById(R.id.locate) ;
        db = new DatabaseHelper(this);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;


        //  Address address = addressList.get(0);
        // LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
        //  map.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        //  map.animateCamera(CameraUpdateFactory.newLatLng(latLng));


        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT < 23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    Activity#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location lastKnownLocation = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                map.clear();
                LatLng canada = new LatLng(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());
                //   map.addMarker(new MarkerOptions().position(canada).title());
                // map.moveCamera(CameraUpdateFactory.newLatLng(canada));
                //    Double LatitudeValue = lastKnownLocation.getLatitude();
                //    Double LongitudeValue = lastKnownLocation.getLongitude();

                map.moveCamera(CameraUpdateFactory.newLatLng(canada));


                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                try {
                    List<Address> listAddresses = geocoder.getFromLocation(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 1);
                    if (listAddresses != null && listAddresses.size() > 0) {
                        Address LocationOnly = listAddresses.get(0);
//LocationOnly.getFeatureName();
                        Log.i("Locality Name", String.valueOf(LocationOnly.getThoroughfare()));
                        double Latitude = Log.i("Latitude", String.valueOf(LocationOnly.getLatitude()));
                        double Longitude = Log.i("Longitude", String.valueOf(LocationOnly.getLongitude()));


                        map.addMarker(new MarkerOptions().position(canada).title(String.valueOf(LocationOnly.getThoroughfare())));
                        map.moveCamera(CameraUpdateFactory.newLatLng(canada));

                        map.addMarker(new MarkerOptions()
                                .position(new LatLng(43.775838, -79.257890))
                                .title("New Market Photography Store"));
                        //map.moveCamera(CameraUpdateFactory.newLatLng(canada));

                        map.addMarker(new MarkerOptions()
                                .position(new LatLng(43.775517, -79.280591))
                                .title("Henry's Photography"));
                       // map.moveCamera(CameraUpdateFactory.newLatLng(canada));

                        map.addMarker(new MarkerOptions()
                                .position(new LatLng(43.828299, -79.305119))
                                .title("Aieden camera"));
                        //map.moveCamera(CameraUpdateFactory.newLatLng(canada));

                        map.addMarker(new MarkerOptions()
                                .position(new LatLng(43.714574, -79.399556))
                                .title("F-stop Photo"));
                        //  boolean b;

//                       if( db.addMapTable(Latitude, Longitude) == true) {
//                           Toast.makeText(MapsActivity.this, "Data", Toast.LENGTH_SHORT).show();
//
//                       }
                       // else b = false;
                       // if(b = true ){
                      db.addMapTable(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude());

                      Log.i("value","saving");




                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
