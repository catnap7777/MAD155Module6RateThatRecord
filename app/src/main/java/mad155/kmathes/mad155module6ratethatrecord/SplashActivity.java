package mad155.kmathes.mad155module6ratethatrecord;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                finish();
                //startActivity(new Intent(SplashActivity.this, MainActivity.class));
                startActivity(new Intent(SplashActivity.this, Splash2Activity.class));
            }
        };

        Timer opening1 = new Timer();

        opening1.schedule(task1, 5000);  //1000 milliseconds = approx 1 second in real time

    }
}
