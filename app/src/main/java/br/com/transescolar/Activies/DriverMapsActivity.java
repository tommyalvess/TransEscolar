package br.com.transescolar.Activies;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import br.com.transescolar.Conexao.SessionManager;
import br.com.transescolar.R;

public class DriverMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    FusedLocationProviderClient mFusedLocationClient;

    private LatLng pickupLocation;
    private Button mLogout;

    SessionManager sessionManager;
    String getId;
    String getCpf;

    private DatabaseReference databaseR;

    private String customerId = "";

    private SupportMapFragment mapFragment;

    Boolean isLogginOut = false;

    LocationManager locationManager;

    private LocationListener mLocationListener;

    private Switch mWorkingSwitch;

    LocationCallback mLocationCallback;

    public static final String SWITCH_PREFS = "Switch";
    public static final String SWITCH_CHECKED = "isChecked";
    public static final String SWITCH_NOT_CHECKED = "isNotChecked";
    public static final String SWITCH_STATUS = "Status";
    private SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        //Criando o tollbar para custumizar
        getActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getActionBar().setHomeButtonEnabled(true);      //Ativar o botão
        getActionBar().setTitle("Mapa");

        //iniciando a sessão
        sessionManager = new SessionManager(this);

        //pegando dados da sessão
        HashMap<String, String> user = sessionManager.getUserDetail();
        getId = user.get(sessionManager.ID);
        getCpf = user.get(sessionManager.CPF);

        mWorkingSwitch = findViewById(R.id.workingSwitch);

        final SharedPreferences sharedpreferences = getSharedPreferences("MY PREFS",   Context.MODE_PRIVATE);
        mWorkingSwitch.setChecked(sharedpreferences.getBoolean(SWITCH_STATUS,false));
        mWorkingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    myLocationCallback();
                    checkLocationPermission();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(SWITCH_STATUS, true);
                    editor.commit();
                    Log.d("Chamada if", "isChecked");

                }else{
                    disconectarDriver();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putBoolean(SWITCH_STATUS, false);
                    editor.commit();
                    Log.d("Chamada if", "isNotChecked");
                }
            }
        });


    } //Oncreate

    private void myPermissionLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                        new AlertDialog.Builder(this)
                                .setTitle("De permissão para localização!")
                                .setMessage("Para funcionmento precisa ter permição")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ActivityCompat.requestPermissions(DriverMapsActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                                    }
                                })
                                .create()
                                .show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            }
        }
    }

    private void checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                    myAlertDialog();
            } else {
                ActivityCompat.requestPermissions(DriverMapsActivity.this,
                        new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
    }

    private void myAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Localização");
        builder.setMessage("Para funcionmento precisa ter permição")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ActivityCompat.requestPermissions(DriverMapsActivity.this,
                                new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void myLocationCallback() {
            mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                for (Location location : locationResult.getLocations()) {
                    if (getApplicationContext() != null) {
                        mLastLocation = location;

                        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference refAvailable = database.getReference("driversAvailable");
                        DatabaseReference refWorking = database.getReference("driversWorking");
                        GeoFire geoFireAvailable = new GeoFire(refAvailable);
                        GeoFire geoFireWorking = new GeoFire(refWorking);

                        geoFireAvailable.setLocation(getId, new GeoLocation(location.getLatitude(), location.getLongitude()), new GeoFire.CompletionListener() {
                            @Override
                            public void onComplete(String key, DatabaseError error) {
                                if (error != null) {
                                    Log.d("Sucesso", "Localização Salva ");
                                }
                            }
                        });

                    }

                    Log.i("DriverMaps", "Location: " + location.getLatitude() + " " + location.getLongitude());
                }

            }

        };

    } // myLocationCallback


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            } else {
                checkLocationPermission();
            }

        }


    }// onMapReady

//    LocationCallback mLocationCallback = new LocationCallback() {
//        @Override
//        public void onLocationResult(LocationResult locationResult) {
//
//            for (Location location : locationResult.getLocations()) {
//            if (getApplicationContext() != null) {
//                mLastLocation = location;
//
//                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//                //mMap.animateCamera(CameraUpdateFactory.zoomTo(+11));
//
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference refAvailable = database.getReference("driversAvailable");
//                DatabaseReference refWorking = database.getReference("driversWorking");
//                GeoFire geoFireAvailable = new GeoFire(refAvailable);
//                GeoFire geoFireWorking = new GeoFire(refWorking);
//
//                geoFireAvailable.setLocation(getId, new GeoLocation(location.getLatitude(), location.getLongitude()), new GeoFire.CompletionListener() {
//                    @Override
//                    public void onComplete(String key, DatabaseError error) {
//                        if (error != null) {
//                            Log.d("Sucesso", "Localização Salva ");
//                        }
//                    }
//                });
//
//
//
//
//                }
//
//        }
//
//        Log.d("Chamada", "onLocationResult");
//
//        }
//
//    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
                        mMap.setMyLocationEnabled(true);
                    }
                    mapFragment.getMapAsync(this);
                } else {
                    Toast.makeText(this, "Precisa permitir o acesso a localização!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
        Log.d("Call", "onRequestPermissionsResult");

    }


    private void startLocationUpdates() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        }
        Log.d("Chamada", "Location update started ..............: ");
        requestLocationUpdates();
    }

    public void requestLocationUpdates() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(50000);
        mLocationRequest.setFastestInterval(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        Log.d("Chamada", "requestLocationUpdates");
    }

    protected void stopLocationUpdates() {
        if (mFusedLocationClient != null) {
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }
        Log.d("Chamada", "Location update stopped .......................");
    }

    private void connectDriver(){
        checkLocationPermission();
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
        mMap.setMyLocationEnabled(true);
        Log.d("Chamada", "connectDriver");
    }

    private void disconectarDriver() {

        if(mFusedLocationClient != null){
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
        }

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("driversAvailable");

        GeoFire geoFire = new GeoFire(ref);
        geoFire.removeLocation(getId, new GeoFire.CompletionListener() {
            @Override
            public void onComplete(String key, DatabaseError error) {
                mFusedLocationClient.removeLocationUpdates(mLocationCallback);
                Log.d("Chamada", "disconectarDriver");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mWorkingSwitch.isChecked()){
            myLocationCallback();
            checkLocationPermission();
            Log.d("Chamada mWorkingSwitch", "onCreate isChecked");
        }else {
            Toast.makeText(this, "Voce está offline!", Toast.LENGTH_SHORT).show();
            Log.d("Chamada mWorkingSwitch", "onCreate isNotChecked");

        }

        Log.d("Chamada", "onStart..............");
    }

    @Override
    public void onResume() {
        super.onResume();


        Log.d("Chamada", "onResume.....................");
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mWorkingSwitch.isChecked()){
            myLocationCallback();
            checkLocationPermission();
            Log.d("Chamada mWorkingSwitch", "onCreate isChecked");
        }else {
            Toast.makeText(this, "Voce está offline!", Toast.LENGTH_SHORT).show();
            Log.d("Chamada mWorkingSwitch", "onCreate isNotChecked");
        }

        Log.d("Chamada", "onPause.....................");

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mWorkingSwitch.isChecked()){
            myLocationCallback();
            checkLocationPermission();
            Log.d("Chamada mWorkingSwitch", "onCreate isChecked");
        }else {
            Log.d("Chamada mWorkingSwitch", "onCreate isNotChecked");

        }

        Log.d("Chamada", "onStop.....................");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }


}// Map Activity
