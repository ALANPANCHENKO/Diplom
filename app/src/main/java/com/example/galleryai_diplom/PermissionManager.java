package com.example.galleryai_diplom;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionManager {
    private static final int REQUEST_PERMISSIONS = 101;
    private final Activity activity;
    private final GalleryManager galleryManager;

    public PermissionManager(Activity activity, GalleryManager galleryManager) {
        this.activity = activity;
        this.galleryManager = galleryManager;
    }

    public void checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            galleryManager.loadImages();
            return;
        }

        String[] permissions = {
                Manifest.permission.READ_MEDIA_IMAGES,
                Manifest.permission.READ_MEDIA_VIDEO,
                Manifest.permission.READ_MEDIA_AUDIO
        };

        boolean allGranted = true;
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                allGranted = false;
                break;
            }
        }

        if (!allGranted) {
            ActivityCompat.requestPermissions(activity, permissions, REQUEST_PERMISSIONS);
        } else {
            galleryManager.loadImages();
        }
    }

    public void handlePermissionsResult(int requestCode, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS) {
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(activity, "Разрешения необходимы для работы", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
            galleryManager.loadImages();
        }
    }
}
