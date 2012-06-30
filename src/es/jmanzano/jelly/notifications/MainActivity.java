package es.jmanzano.jelly.notifications;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import es.jmanzano.jelly.notifications.R;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        String ns = Context.NOTIFICATION_SERVICE;
        NotificationManager notificationManager = (NotificationManager) getSystemService(ns);
        
        int icon = android.R.drawable.ic_menu_camera;
        CharSequence tickerText = "Hello";
        long when = System.currentTimeMillis();
        //Notification notification = new Notification(icon, tickerText, when);

        Builder build = new Notification.Builder(this)
	        .setContentTitle("New mail from me")
	        .setContentText("subject")
	        .setTicker(tickerText)
	        .setSmallIcon(R.drawable.ic_action_search)
	        .addAction(
	        			android.R.drawable.ic_btn_speak_now,
	        			"Speak!",
	        			PendingIntent.getActivity(getApplicationContext(), 0, getIntent(), 0, null)
	        			)
	        .addAction(
	        			android.R.drawable.ic_dialog_map,
	        			"Maps",
	        			PendingIntent.getActivity(getApplicationContext(), 0, getIntent(), 0, null)
	        			)
	        .addAction(
    					android.R.drawable.ic_dialog_info,
    					"Info",
    					PendingIntent.getActivity(getApplicationContext(), 0, getIntent(), 0, null)
    					)
	        ;

        Notification notification = new Notification
        	.BigPictureStyle(build)
        	.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.jellybean))
        	.build();
        
        Intent notificationIntent = new Intent(this, MainActivity.class);
        
        CharSequence contentTitle = "Notification";
        CharSequence contentText = "Hello world!";
        PendingIntent activity = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        notificationManager.notify(0, notification);
        

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
}
