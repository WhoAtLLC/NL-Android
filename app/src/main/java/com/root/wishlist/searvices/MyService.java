package com.root.wishlist.searvices;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;

public class MyService extends Service {
    public MyService() {
    }

    boolean isbackrunning = false;
    Handler handler;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        isbackrunning = true;
        handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                new ContactFetchSearvice(getApplicationContext()).execute();
            }
        });

        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isbackrunning = false;
        //new ContactFetchSearvice(getApplicationContext()).execute();
    }

    private boolean isServiceRunning(Class<?> backgroundLocationServiceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (backgroundLocationServiceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    public void onTaskRemoved(Intent rootIntent) {
        //org.ninehertz.india.imoncampus.utils.Log.log("onTaSKRemoved", "onTaSKRemoved");

        boolean is_back_running = isServiceRunning(MyService.class);
        if (is_back_running == true) {
            stopService(new Intent(this, MyService.class));
            startService(new Intent(this, MyService.class));
        }
        super.onTaskRemoved(rootIntent);
    }
}
