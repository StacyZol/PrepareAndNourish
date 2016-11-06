package com.stacyzolnikov.prepareandnourish;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

import com.stacyzolnikov.prepareandnourish.unsigned_users.HomeUnsignedActivity;

public class MainActivity extends AppCompatActivity {
    Button mGmailButton, mEmailButton, mSkipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGmailButton = (Button) findViewById(R.id.gmailButton);
        mEmailButton = (Button) findViewById(R.id.emailButton);
        mSkipButton = (Button) findViewById(R.id.skipCustomizationButton);
        //DisplayNotification("PrepareAndNourish","OnCreate");
       // sleepForAMinute();

        //NOTES:
        //Create views, initialize variables, bind static data to lists in this method
        //This method takes a Bundle parameter, which is a dictionary for storing and passing state informatation and objects between activities. If the bundle is not null, this indicates the activity is restarting and it should restore its state from the previous instance. To retrieve values from a bundle:


    }


    @Override
    protected void onStart() {
        super.onStart();


        //DisplayNotification("PrepareAndNourish","OnStart");
        //sleepForAMinute();

        //NOTES
        //This method is always called by the system after OnCreate is finished. Activities may override this method if they need to perform any specific tasks right before an activity becomes visible such as refreshing current values of views within the activity.

    }

    @Override
    protected void onResume() {
        super.onResume();

        //The system calls this method when the Activity is ready to start interacting with the user. Activities should override this method to perform tasks such as:
        // - Ramping up frame rates (a common task in game building)
        // - Starting animations
        // - Listening for GPS updates
        // - Display any relevant alerts or dialogs
        // - Wire up external event handlers
        // - Initializing the camera

        // !!!IMPORTANT because any operation that is done in OnPause should be un-done in OnResume, since it's the only lifecycle method that is guaranteed to execute after OnPause when bringing the activity back to life

        //Button to bring the user to the home page if they decide to not sign in. Essentially a free version
        mSkipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeUnsignedActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        //This method is called when the system is about to put the activity into the background or when the activity becomes partially obscured. Activities should override this method if they need to:
        // - Commit unsaved changes to persistent data
        // - Destroy or clean up other objects consuming resources
        // - Ramp down frame rates and pausing animations
        // - Unregister external event handlers or notification handlers (i.e. those that are tied to a service). This must be done to prevent Activity memory leaks
        // - Likewise, if the Activity has displayed any dialogs or alerts, they must be cleaned up with the .dismiss() method

        //There are two possible lifecycle methods that could be called after this...OnResume() if the activity is returned to the foreground and OnStop() if the activity is being placed in the backgrou nd

    }

    @Override
    protected void onStop() {
        super.onStop();
        //This is when the activity is no longer visible to the user. It happens when one of the following occurs:
        // - A new activity is being started and is covering up this activity
        // - An existing activity is being brought to the foreground
        // - The activity is being destroyed

        //It may not always be called in low-memory situations, such as when Android is starved for resources and cannot properly backgrond the Activity. For this reason, it is best not to rely on OnStop getting called when preparing an Activity for destruction. The next lifecycle will be OnDestroy() if the activity is going away or OnRestart() if the Activity is coming back to interact with the user
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DisplayNotification("PrepareAndNourish","OnDestroy");
        sleepForAMinute();

        //The final method what will completely remove from memory
        //Most activities will not implement this method because most clean up and shut down has been done in the OnPause and OnStop methods
        //This method is usually overridden to clean up long running resources that might leak resources. An example of this might be background threads that were started in OnCreate();
    }

       
    //OnRestart() method:
    //This method is called after your activity has been stopped, prior to it being started again. A good example of this would be when the user presses the home button while on an activity in the application. When this happens OnPause and then OnStop methods are called and the Activity is moved to the background but is not destroyed.
    //If the user were then to restore the application by using the task manager or a similar application, Android will call the OnRestart method of the activity..it's usually not used because Android will call OnStart()

    protected void DisplayNotification(String title, String message){

       PendingIntent intent = PendingIntent.getActivity(MainActivity.this,0,new Intent(MainActivity.this,MainActivity.class), 0 );
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_announcement_black_24dp)
                .setContentTitle(title)
                .setContentText(message);
        builder.setContentIntent(intent);
        builder.setAutoCancel(true);
        NotificationManager notifManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notifManager.notify(1,builder.build());

    }
    protected void sleepForAMinute(){
        try{
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




















}
