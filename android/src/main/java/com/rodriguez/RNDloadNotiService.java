
package com.rodriguez;

import android.app.Notification;
import android.app.Service;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.IBinder;
import com.rodriguez.R;

public class RNDloadNotiService extends Service {

  private NotificationManager notiManager;

  @Override
  public IBinder onBind(Intent intent) {
      return null;
  }

  @Override
  public void onCreate() {
    notiManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
  }

  @Override
  public void onTaskRemoved(Intent rootIntent) {
    super.onTaskRemoved(rootIntent);
    stopInstance();
  }

  private void showPendingNotification() {
    Notification notification = new Notification.Builder(this)
      .setSmallIcon(R.mipmap.rndn_icon)
      .setContentTitle("Downloads Favoritos")
      .setContentText("Os downloads pendentes serão concluídos em plano de fundo! Se quiser pará-los, abra o aplicativo!")
      .setContentIntent(null)
      .setStyle(new Notification.BigTextStyle()
         .bigText("Os downloads pendentes serão concluídos em plano de fundo! Se quiser pará-los, abra o aplicativo!"))
      .build();
    notiManager.notify(666, notification);
  }

  private void stopInstance() {
    notiManager.cancelAll();
    showPendingNotification();
    stopSelf();
  }
}
