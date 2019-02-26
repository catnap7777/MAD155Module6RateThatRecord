package mad155.kmathes.mad155module6ratethatrecord;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    MediaPlayer mpFiftyWays, mpSabotage, mpScream, mpSpread, mpTrouble;
    int playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //.. set the title on the action bar for this activity and override title in manifest
        setTitle(R.string.txt_play_music);

        button1 = (Button) findViewById(R.id.buttonFiftyWays);
        button2 = (Button) findViewById(R.id.buttonSabotage);
        button3 = (Button) findViewById(R.id.buttonScream);
        button4 = (Button) findViewById(R.id.buttonSpread);
        button5 = (Button) findViewById(R.id.buttonTrouble);

        button1.setOnClickListener(bFiftyWays);
        button2.setOnClickListener(bSabotage);
        button3.setOnClickListener(bScream);
        button4.setOnClickListener(bSpread);
        button5.setOnClickListener(bTrouble);

        mpFiftyWays = new MediaPlayer();
        mpFiftyWays = MediaPlayer.create(this, R.raw.fifty_ways_to_say_goodbye);

        mpSabotage = new MediaPlayer();
        mpSabotage = MediaPlayer.create(this, R.raw.sabotage);

        mpScream = new MediaPlayer();
        mpScream = MediaPlayer.create(this, R.raw.scream_and_shout);

        mpSpread = new MediaPlayer();
        mpSpread = MediaPlayer.create(this, R.raw.spread_too_thin);

        mpTrouble = new MediaPlayer();
        mpTrouble = MediaPlayer.create(this, R.raw.troublemaker);

        playing = 0;

        final CheckBox chkFiftyWays = (CheckBox) findViewById(R.id.chkFiftyWays);

        chkFiftyWays.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            chkFiftyWays.setText("Hi Karen");

            }
        });

    }

    //..button1
    Button.OnClickListener bFiftyWays = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (playing) {
                case 0:
                    mpFiftyWays.start();
                    playing = 1;
                    button1.setText("Pause");
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpFiftyWays.pause();
                    playing = 0;
                    button1.setText("Play");
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    button5.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    //..button2
    Button.OnClickListener bSabotage = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (playing) {
                case 0:
                    mpSabotage.start();
                    playing = 1;
                    button2.setText("Pause");
                    button1.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpSabotage.pause();
                    playing = 0;
                    button2.setText("Play");
                    button1.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    button5.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    //..button3
    Button.OnClickListener bScream = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (playing) {
                case 0:
                    mpScream.start();
                    playing = 1;
                    button3.setText("Pause");
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpScream.pause();
                    playing = 0;
                    button3.setText("Play");
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    button5.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    //..button4
    Button.OnClickListener bSpread = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (playing) {
                case 0:
                    mpSpread.start();
                    playing = 1;
                    button4.setText("Pause");
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpSpread.pause();
                    playing = 0;
                    button4.setText("Play");
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    button5.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    //..button5
    Button.OnClickListener bTrouble = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (playing) {
                case 0:
                    mpTrouble.start();
                    playing = 1;
                    button5.setText("Pause");
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpTrouble.pause();
                    playing = 0;
                    button5.setText("Play");
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };



}

