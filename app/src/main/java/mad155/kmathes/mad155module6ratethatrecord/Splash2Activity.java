package mad155.kmathes.mad155module6ratethatrecord;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash2);


        new CountDownTimer(5000, 1500) {
            int i = 0;
            public void onTick(long millisUntilFinished) {
                ImageView imageview = (ImageView) findViewById(R.id.imageView);

                i++;
                if(i == 1){
                    imageview.setImageResource(R.drawable.just_dance1);
                }
                else if(i == 2){
                    imageview.setImageResource(R.drawable.just_dance2);
                }
                else if(i == 3){
                    imageview.setImageResource(R.drawable.just_dance3);
                }
                //and so on..........................

            }

            public void onFinish() {
                //finish your splash screen activity
                Splash2Activity.this.finish();
                startActivity(new Intent(Splash2Activity.this, MainActivity.class));

            }

        }

        .start();



        /*TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                finish();
                //startActivity(new Intent(SplashActivity.this, MainActivity.class));
                startActivity(new Intent(Splash2Activity.this, MainActivity.class));
            }
        };

        Timer opening1 = new Timer();

        opening1.schedule(task1, 5000);  //1000 milliseconds = approx 1 second in real time
       */
    }
}
