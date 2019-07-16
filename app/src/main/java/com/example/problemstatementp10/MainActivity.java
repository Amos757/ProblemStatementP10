package com.example.problemstatementp10;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    MyFragmentPagerAdapter adapter;
    ViewPager vPager;
    Button ButtonLater;
    AlarmManager am;
    int requestCode = 123;
    int notificationID = 888;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = findViewById(R.id.viewpager1);
        ButtonLater = findViewById(R.id.buttonLater);

        FragmentManager fm = getSupportFragmentManager();
        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());
        adapter = new MyFragmentPagerAdapter(fm, al);
        vPager.setAdapter(adapter);

        ButtonLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new
                            NotificationChannel("default", "Default Channel",
                            NotificationManager.IMPORTANCE_DEFAULT);

                    channel.setDescription("This is for default notification");
                    notificationManager.createNotificationChannel(channel);
                }

                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity
                        ( MainActivity.this, requestCode, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                // Build notification
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);
                am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, requestCode , intent, PendingIntent.FLAG_CANCEL_CURRENT);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
                finish();

                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("Remember you have some useless fact's to find out!");
                builder.setContentText("Subject");
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);

                Notification n = builder.build();

                // An integer good to have, for you to programmatically cancel it
                notificationManager.notify(notificationID, n);
                finish();


            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return super.onCreateOptionsMenu(menu);



    }
}
