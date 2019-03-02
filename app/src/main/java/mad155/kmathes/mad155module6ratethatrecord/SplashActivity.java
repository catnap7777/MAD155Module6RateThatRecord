package mad155.kmathes.mad155module6ratethatrecord;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /* Old countdown timer like in Aloha assignment
        //.. so this was basically putting up splash xml for 5 seconds and then continuing
        opening1.schedule(task1, 5000);  //1000 milliseconds = approx 1 second in real time

        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                finish();
                //startActivity(new Intent(SplashActivity.this, MainActivity.class));
                //startActivity(new Intent(SplashActivity.this, Splash2Activity.class));
            }
        };
        Timer opening1 = new Timer();
        */

        //.. using CountDownTimer to put up multiple images on splash screen
        new CountDownTimer(8000, 2000) {
            int i = 0;
            public void onTick(long millisUntilFinished) {
                ImageView imageview = (ImageView) findViewById(R.id.imageViewSplash);

                i++;
                if(i == 1){
                    imageview.setImageResource(R.drawable.records);
                }
                else if(i == 2){
                    imageview.setImageResource(R.drawable.just_dance1);
                }
                else if(i == 3){
                    imageview.setImageResource(R.drawable.just_dance2);
                }
                else if(i == 4){
                    imageview.setImageResource(R.drawable.just_dance3);
                }
                //and so on..........................

            }

            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }

        }

        .start();

    }
}
