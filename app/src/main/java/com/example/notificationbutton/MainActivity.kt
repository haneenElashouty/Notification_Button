package com.example.notificationbutton

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.resources.Compatibility.Api18Impl.setAutoCancel
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationbutton.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    // your important id
    private  val CHANNEL_ID ="my channel"
    private val notificatioId=101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        // add the channel creation
        CreateNotificationChannel()
            // first type button
        binding.buttonOne.setOnClickListener {
            SendNotificationOne()
        }
        // 2nd type button
        binding.buttontwo.setOnClickListener {
            SendNotificationTwo()
        }
        // 3rd type button
        binding.buttonthree.setOnClickListener {
            SendNotificationThree()
        }

    }

    // to make notification work on all API
    private fun CreateNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = " test channel"
            val descriptionText ="this app is for explaining how to make notification on your app"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel =NotificationChannel(CHANNEL_ID,name,importance).apply { description =descriptionText }
            // register channel with system
            val notificationManager :NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }

    // make the notification one basic
    private fun SendNotificationOne (){
        //add action when click on notification
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // set content
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle("first notification")
            .setContentText("this is a basic notification")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        // show notification if user accept
        with(NotificationManagerCompat.from(this)){
            notify(notificatioId,builder.build())
        }
    }

    // make the notification two image
    private fun SendNotificationTwo (){
        //add action when click on notification
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        //add your image as bitmap
        val bitmap =BitmapFactory.decodeResource(applicationContext.resources,R.drawable.android)

        // set content
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle("second notification")
            .setContentText("this is a notification with large image")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        // if user reject permission
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        // show notification if user accept
        with(NotificationManagerCompat.from(this)){
            notify(notificatioId,builder.build())
        }
    }

    // make the notification three large text
    private fun SendNotificationThree(){
        //add action when click on notification
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        // set content
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(androidx.core.R.drawable.notification_bg)
            .setContentTitle("third notification")
            .setContentText("this is a notification with long text")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setStyle(NotificationCompat.BigTextStyle().bigText("this is a long text that cannot fit in one line so id add it as long text"))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        // if user reject permission
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        // show notification if user accept
        with(NotificationManagerCompat.from(this)){
            notify(notificatioId,builder.build())
        }
    }


}