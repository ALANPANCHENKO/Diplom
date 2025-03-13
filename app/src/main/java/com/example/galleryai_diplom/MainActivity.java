package com.example.galleryai_diplom;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements  ImageAdapter.OnImageClickListener {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private RecyclerView recyclerView;
    private GalleryManager galleryManager;
    private PermissionManager permissionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        recyclerView = findViewById(R.id.recyclerView);

//        navigationView.setNavigationItemSelectedListener(this);

        galleryManager = new GalleryManager(this, recyclerView, this);
        permissionManager = new PermissionManager(this, galleryManager);

        permissionManager.checkAndRequestPermissions();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Передаем результат в PermissionManager
        permissionManager.handlePermissionsResult(requestCode, grantResults);
    }

    @Override
    public void onImageClick(String imagePath) {
        galleryManager.openFullScreenImage(imagePath);
    }

//
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        drawerLayout.closeDrawers();
//        return true;
//    }
}
