package com.example.uoftlife.util;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.uoftlife.R;
import com.example.uoftlife.data.DataFacade;
import com.example.uoftlife.data.GameConstants;

// Singleton class
// act as builder

public class GameMessenger {
    private static final GameMessenger gameMessenger = new GameMessenger();

    private NotificationManager mNotificationManager = null;
    private NotificationCompat.Builder mBuilder = null;
    private int id = 0;

    private GameMessenger() {
    }

    public static GameMessenger getMessenger() {
        return gameMessenger;
    }


    // return true if is successful and can continue use this class
    public boolean initialize() {
        if (DataFacade.getContext() == null) {
            return false;
        }
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) DataFacade.getContext()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(GameConstants.CHANNEL_ID,
                    GameConstants.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(notificationChannel);
        }

        mBuilder = new NotificationCompat.Builder(DataFacade.getContext(), GameConstants.CHANNEL_ID);
        // initialize the builder default value
        mBuilder.setAutoCancel(true).setContentTitle("").setContentText("")
                .setSmallIcon(R.mipmap.ic_launcher).setPriority(NotificationCompat.PRIORITY_DEFAULT);
        return true;
    }

    public GameMessenger setIntent(Intent intent) {
        Context context = DataFacade.getContext();
        if (mBuilder != null && intent != null && context != null) {
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
            mBuilder.setFullScreenIntent(pendingIntent, true);
        }
        return this;
    }

    public GameMessenger setIcon(int iconResId) {
        if (mBuilder != null) {
            mBuilder.setSmallIcon(iconResId);
        }
        return this;
    }

    public GameMessenger setTitle(String title) {
        if (mBuilder != null) {
            mBuilder.setContentTitle(title);
        }
        return this;
    }

    public GameMessenger setText(String text) {
        if (mBuilder != null) {
            mBuilder.setContentText(text);
        }
        return this;
    }

    public GameMessenger setSubText(String text) {
        if (mBuilder != null) {
            mBuilder.setSubText(text);
        }
        return this;
    }

    public GameMessenger setProgressBar(int max, int progress, boolean indeterminate) {
        if (mBuilder != null) {
            mBuilder.setProgress(max, progress, indeterminate);
        }
        return this;
    }


    public void sendNewMessage() {
        if (mNotificationManager != null) {
            mNotificationManager.notify(++id, mBuilder.build());
        }
    }

    public void updateCurrentMessage() {
        if (mNotificationManager != null) {
            mNotificationManager.notify(id, mBuilder.build());
        }
    }

    public void clearAll() {
        if (mNotificationManager != null) {
            mNotificationManager.cancelAll();
            id = 0;
        }
    }

    public void toastMessage(String text) {
        Context context = DataFacade.getContext();
        if (context != null) {
            Toast.makeText(context, text, Toast.LENGTH_LONG).show();
        }
    }
}
