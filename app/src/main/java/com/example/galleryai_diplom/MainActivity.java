package com.example.galleryai_diplom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import org.opencv.android.OpenCVLoader;
//import org.opencv.core.Mat;
//import org.opencv.imgcodecs.Imgcodecs;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int REQUEST_PERMISSIONS = 101;
    private RecyclerView recyclerView;
    private ImageAdapter adapter;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (!OpenCVLoader.initDebug()) {
//            Log.e("OpenCV", "Ошибка инициализации OpenCV");
//        } else {
//            Log.d("OpenCV", "OpenCV успешно загружен");
//        }

        //инициализация toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigation_view);
        recyclerView = findViewById(R.id.recyclerView);

        // Настройка меню
        navigationView.setNavigationItemSelectedListener(this);

        // Добавляем кнопку открытия меню
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.open_drawer, R.string.close_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            checkAndRequestPermissions();
        } else {
            setupGallery();
        }
    }

//    private void testOpenCV() {
//        Mat img = Imgcodecs.imread("/storage/emulated/0/DCIM/test.jpg");
//        if (img.empty()) {
//            Log.e("OpenCV", "Ошибка загрузки изображения");
//        } else {
//            Log.d("OpenCV", "Изображение загружено успешно");
//        }
//    }

    private void checkAndRequestPermissions() {
        String[] permissions = {
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
        };

        boolean allGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (!allGranted) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_PERMISSIONS);
        } else {
            setupGallery();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }
            if (allGranted) {
                setupGallery();
            } else {
                Toast.makeText(this, "Необходимо разрешение для работы с медиафайлами", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void setupGallery() {
        List<String> images = ImageUtils.loadImages(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ImageAdapter(this, images);
        recyclerView.setAdapter(adapter);

        Toast.makeText(this, "Все разрешения предоставлены", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_gallery) {
            Toast.makeText(this, "Галерея выбрана", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_settings) {
            Toast.makeText(this, "Настройки пока недоступны", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_about) {
            Toast.makeText(this, "О приложении", Toast.LENGTH_SHORT).show();
        }
//        else if (id == R.id.nav_ai_search) {
//            Intent intent = new Intent(this, FaceSearchActivity.class);
//            startActivity(intent);
//        }

        drawerLayout.closeDrawers();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.open();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}