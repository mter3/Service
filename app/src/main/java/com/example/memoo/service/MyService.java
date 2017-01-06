package com.example.memoo.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class MyService extends Service {
    Handler handler=new Handler();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        Toast.makeText(getApplicationContext(),"Servis basladı",Toast.LENGTH_LONG).show();
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Servis basladı 10 sec ",Toast.LENGTH_LONG).show();
                NotificationManager notificationManager= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                Intent reteating_intent1=new Intent(getApplicationContext(),MainActivity.class);
                reteating_intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                PendingIntent pendingIntent=PendingIntent.getActivity(getApplicationContext(),100,reteating_intent1,PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder=new NotificationCompat.Builder(getApplicationContext())
                        .setContentIntent(pendingIntent).setSmallIcon(R.drawable.sharebutton1)
                        .setContentTitle("title")
                        .setContentText("text").setAutoCancel(true);
                notificationManager.notify(100,builder.build());
                handler.postDelayed(this,20000);

            }
        };
        handler.post(runnable);

        super.onCreate();
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        Toast.makeText(getApplicationContext(),"Servis Durdu ",Toast.LENGTH_LONG).show();

    }
}
