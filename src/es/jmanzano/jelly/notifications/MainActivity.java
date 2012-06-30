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
import android.view.View;

public class MainActivity extends Activity {

	private NotificationManager notificationManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		notificationManager = getNotificationManager();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private NotificationManager getNotificationManager() {
		return (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}

	/**
	 * Push a simple notification (old way)
	 */
	private void pushSimpleNotification() {
		Notification notification = new Notification(
				android.R.drawable.ic_menu_camera, "Hello camera!",
				System.currentTimeMillis());
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		notification.number += 1;

		Intent intent = new Intent(this, MainActivity.class);
		PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 0);
		notification.setLatestEventInfo(this, "title", "content", activity);
		notificationManager.notify(0, notification);
	}

	/**
	 * Big picture notification!
	 */
	public void pushBigPictureNotifications() {
		Builder build = new Notification.Builder(this)
				.setContentTitle("New mail from me")
				.setContentText("subject")
				.setTicker("New email with photo")
				.setSmallIcon(R.drawable.ic_action_search)
				.addAction(
						android.R.drawable.ic_btn_speak_now,
						"Speak!",
						PendingIntent.getActivity(getApplicationContext(), 0,
								getIntent(), 0, null))
				.addAction(
						android.R.drawable.ic_dialog_map,
						"Maps",
						PendingIntent.getActivity(getApplicationContext(), 0,
								getIntent(), 0, null))
				.addAction(
						android.R.drawable.ic_dialog_info,
						"Info",
						PendingIntent.getActivity(getApplicationContext(), 0,
								getIntent(), 0, null));

		Notification notification = new Notification.BigPictureStyle(build)
				.bigPicture(
						BitmapFactory.decodeResource(getResources(),
								R.drawable.jellybean)).build();

		notificationManager.notify(0, notification);
	}

	/**
	 * Big notifications
	 */
	public void pushBigTextNotification() {
		Builder builder = new Notification.Builder(this)
				.setContentTitle("New mail from me").setContentText("subject")
				.setSmallIcon(android.R.drawable.ic_menu_agenda);
		Notification notification = new Notification.BigTextStyle(builder)
				.bigText("Very long stringgggggg!!!! It will fill the space of more than 1 line! It's amazing how Jelly Bean notifications work!")
				.build();
		notificationManager.notify(0, notification);
	}

	/**
	 * Inbox notifications
	 */
	private void pushInboxNotifications() {
		Builder builder = new Notification.Builder(this)
				.setContentTitle("5 New mails from me")
				.setContentText("subject")
				.setSmallIcon(android.R.drawable.ic_menu_agenda);

		Notification notification = new Notification.InboxStyle(builder)
				.addLine("line1")
				.addLine("line2")
				.addLine("line3")
				.setSummaryText("+3 more")
				.build();
		notificationManager.notify(0, notification);
	}

	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.button1:
			pushSimpleNotification();
			break;
		case R.id.button2:
			pushBigPictureNotifications();
			break;
		case R.id.button3:
			pushBigTextNotification();
			break;
		case R.id.button4:
			pushInboxNotifications();
			break;
		}

	}

}
