package fr.ipac.multigame.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import fr.ipac.multigame.R;
import fr.ipac.multigame.model.Player;
import fr.ipac.multigame.utils.ActivityUtils;

public class CreatePlayerActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1000;
    private static final int REQUEST_LOCATION = 2000;

    private String imageUrl;
    private String userLocalisation;

    private EditText name;
    private EditText first_name;
    private EditText age;
    private EditText localization;
    private ImageView localization_btn;
    private Button players;
    private Button validate;
    private ImageView image;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_player_activity);

        // Get ids in view
        name = findViewById(R.id.name);
        first_name = findViewById(R.id.first_name);
        age = findViewById(R.id.age);
        localization = findViewById(R.id.localization);
        validate = findViewById(R.id.validate);
        players = findViewById(R.id.players);
        image = findViewById(R.id.image);
        localization_btn = findViewById(R.id.create_player_localistion_bt);


        players.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreatePlayerActivity.this, ShowPlayerActivity.class);
                startActivity(intent);
                ActivityUtils.launchActivity(CreatePlayerActivity.this, ShowPlayerActivity.class, ActivityUtils.TYPE_SLIDE);
                finish();
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (userLocalisation == null) {
                    userLocalisation = localization.getText().toString();
                }

                if (!name.getText().toString().isEmpty() && !first_name.getText().toString().isEmpty() && !age.getText().toString().isEmpty() && imageUrl != null) {
                    Player player = new Player(name.getText().toString(), first_name.getText().toString(), age.getText().toString(), imageUrl, userLocalisation);

                    Intent intent = new Intent(CreatePlayerActivity.this, MainActivity.class);
                    startActivity(intent);
                    ActivityUtils.launchActivity(CreatePlayerActivity.this, MainActivity.class, ActivityUtils.TYPE_SLIDE);
                    finish();
                } else {
                    Toast.makeText(CreatePlayerActivity.this, "Informations manquantes", Toast.LENGTH_SHORT).show();

                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(Intent.createChooser(intent, "Choix de la photo"), PICK_IMAGE);
            }
        });

        localization_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CreatePlayerActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
                } else {
                    getUserLocation();
                }
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && data != null) {
            Picasso.get().load(data.getData()).into(image);
            imageUrl = data.getDataString();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(CreatePlayerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(CreatePlayerActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            getUserLocation();
        }
    }

    @SuppressLint("MissingPermission")
    private void getUserLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);
        locationManager.requestSingleUpdate(new Criteria(), new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                userLocalisation = location.getLatitude() + " : " + location.getLongitude();
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
        }, null);
    }

}

