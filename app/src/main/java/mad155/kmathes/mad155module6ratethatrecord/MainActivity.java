package mad155.kmathes.mad155module6ratethatrecord;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    MediaPlayer mpFiftyWays, mpSabotage, mpScream, mpSpread, mpTrouble;

    int playing;

    String[] spnArrayNbrs = new String[] {"Click to Rank", "1","2","3","4","5"};
    List<String> spnListNbrs = new ArrayList<>(Arrays.asList(spnArrayNbrs));

    String selectedItemTextA = " ";
    String selectedItemTextB = " ";
    String selectedItemTextC = " ";
    String selectedItemTextD = " ";
    String selectedItemTextE = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //.. set the title on the action bar for this activity and override title in manifest
        //setTitle(R.string.txt_play_music);

        //.. to get rid of action bar for this activity
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}

        setContentView(R.layout.activity_main);


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

        final Spinner spin1 = (Spinner) findViewById(R.id.spn1);
        final Spinner spin2 = (Spinner) findViewById(R.id.spn2);
        final Spinner spin3 = (Spinner) findViewById(R.id.spn3);
        final Spinner spin4 = (Spinner) findViewById(R.id.spn4);
        final Spinner spin5 = (Spinner) findViewById(R.id.spn5);

        Button btn1 = (Button) findViewById(R.id.btnNext);

        final TextView txtResults = (TextView) findViewById(R.id.textView1);

        final String txtResultFiftyWays = getString(R.string.txtFiftyWays);
        final String txtResultSabotage = getString(R.string.txtSabotage);
        final String txtResultScreamandShout = getString(R.string.txtScreamAndShout);
        final String txtResultSpreadTooThin = getString(R.string.txtSpreadTooThin);
        final String txtResultTroublemaker = getString(R.string.txtTroubleMaker);

        final String txtErrorSelectSpnr = getString(R.string.txtErrorSelectSpnr);
        final String txtErrorDuplicateSpnr1 = getString(R.string.txtErrorDuplicateSpnr1);
        final String txtErrorDuplicateSpnr2 = getString(R.string.txtErrorDuplicateSpnr2);


        ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this,
                R.layout.spinner_item, spnListNbrs)
        {
            @Override
            public boolean isEnabled(int position){
                if(position == 0) {
                    return false;
                } else {
                    return true;
                }
            }

            // Used to make this a dropdown list instead of more traditional spinner_item
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;

                if(position == 0) {
                    tv.setTextColor(Color.WHITE);
                    tv.setBackgroundColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                    //..light blue
                    tv.setBackgroundColor(0xff8FDDFF);
                }
                return view;
            }
        };

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item);

        //..Fifty Ways
        spin1.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //String selectedItemText = (String) parent.getItemAtPosition(position);

                selectedItemTextA = (String) parent.getItemAtPosition(position);

                //System.out.println("SELECTEDITEMTEXTA = " + selectedItemTextA);

                if(position > 0){
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemTextA, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        //..Sabotage
        spin2.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //String selectedItemText = (String) parent.getItemAtPosition(position);

                selectedItemTextB = (String) parent.getItemAtPosition(position);

                //System.out.println("SELECTEDITEMTEXTA = " + selectedItemTextB);

                if(position > 0){
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemTextB, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        //..Scream and Shout
        spin3.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //String selectedItemText = (String) parent.getItemAtPosition(position);

                selectedItemTextC = (String) parent.getItemAtPosition(position);

                //System.out.println("SELECTEDITEMTEXTA = " + selectedItemTextC);

                if(position > 0){
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemTextC, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        //..Spread Too Thin
        spin4.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //String selectedItemText = (String) parent.getItemAtPosition(position);

                selectedItemTextD = (String) parent.getItemAtPosition(position);

                //System.out.println("SELECTEDITEMTEXTA = " + selectedItemTextD);

                if(position > 0){
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemTextD, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        //..Troublemaker
        spin5.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //String selectedItemText = (String) parent.getItemAtPosition(position);

                selectedItemTextE = (String) parent.getItemAtPosition(position);

                //System.out.println("SELECTEDITEMTEXTA = " + selectedItemTextE);

                if(position > 0){
                    Toast.makeText
                            (getApplicationContext(), "Selected : " + selectedItemTextE, Toast.LENGTH_SHORT)
                            .show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean proceedFlag = true;
                boolean duplicateFlag = false;
                String duplicateValue = "";

                //public static <T> boolean hasDuplicate(Iterable<T> all) {
                //    Set<T> set = new HashSet<T>();
                Set<String> setList = new HashSet<>();

                //System.out.println("MADE IT TO THE BUTTON CLICK");
                //check to see if there is a "value" for the spinner field. if not, make user select one before
                // proceeding... set up another nexted if
                // if(selectItemTextA equals "rank your song" ie. position 0....message user and dont continue until they select
                //********


                if (selectedItemTextA.isEmpty() || selectedItemTextA == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {

                    if(spin1.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        txtResults.setText(txtErrorSelectSpnr);
                        proceedFlag = false;
                    }

                    if(spin1.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextA)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextA;
                            //System.out.println("HELLO, I AM IN HERE1");
                        }
                    }
                }

                if (selectedItemTextB.isEmpty() || selectedItemTextB == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {

                    if(spin2.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        txtResults.setText(txtErrorSelectSpnr);
                        proceedFlag = false;
                    }

                    if(spin2.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextB)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextB;
                            //System.out.println("HELLO, I AM IN HERE2");
                        }
                    }
                }

                if (selectedItemTextC.isEmpty() || selectedItemTextC == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {

                    if(spin3.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        txtResults.setText(txtErrorSelectSpnr);
                        proceedFlag = false;
                    }

                    if(spin3.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextC)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextC;
                            //System.out.println("HELLO, I AM IN HERE3");
                        }
                    }
                }

                if (selectedItemTextD.isEmpty() || selectedItemTextD == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {

                    if(spin4.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        txtResults.setText(txtErrorSelectSpnr);
                        proceedFlag = false;
                    }

                    if(spin4.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextD)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextD;
                            //System.out.println("HELLO, I AM IN HERE4");
                        }
                    }
                }

                if (selectedItemTextE.isEmpty() || selectedItemTextE == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {

                    if(spin5.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        txtResults.setText(txtErrorSelectSpnr);
                        proceedFlag = false;
                    }

                    if(spin5.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextE)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextE;
                            //System.out.println("HELLO, I AM IN HERE5");
                        }
                    }
                }

                System.out.println("Values : \n" + selectedItemTextA + "\n" +
                        selectedItemTextB + "\n" + selectedItemTextC + "\n" + selectedItemTextD + "\n"
                        + selectedItemTextE + "\n");

                System.out.println("Duplicate Values: " + duplicateValue);

                if(duplicateFlag) {
                    //duplicateFlag = false;
                    Toast.makeText
                            (getApplicationContext(), "Duplicate rankings detected", Toast.LENGTH_SHORT)
                            .show();
                    txtResults.setBackgroundColor(Color.BLACK);
                    txtResults.setTextColor(Color.RED);
                    txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    //txtResults.setText("Duplicate ranking found: " + duplicateValue +
                    //        "\nPlease correct and resubmit by clicking button");
                    txtResults.setText( txtErrorDuplicateSpnr1 + " " + duplicateValue + txtErrorDuplicateSpnr2);
                }

                if (proceedFlag && !duplicateFlag){
                    // if this works, disable spinners and thank user for input (possible new screen)

                    //System.out.println("SUMMARY TIME!");
                    txtResults.setBackgroundColor(Color.WHITE);
                    txtResults.setTextColor(Color.BLUE);
                    txtResults.setGravity(Gravity.START | Gravity.TOP);
                    //txtResults.setText("\nSummary... Thank you for your submission!" +
                    //        "\nspinner1 rank: " + spin1.getSelectedItem().toString() +
                    //        "\nspinner2 rank: " + spin2.getSelectedItem().toString() +
                    //        "\nspinner3 rank: " + spin3.getSelectedItem().toString() +
                    //        "\nspinner4 rank: " + spin4.getSelectedItem().toString() +
                    //        "\nspinner5 rank: " + spin5.getSelectedItem().toString());
                    txtResults.setText("\nSummary... Thank you for your submission!" +
                            "\n\n" + txtResultFiftyWays + ": \t\tRank = " + spin1.getSelectedItem().toString() +
                            "\n" + txtResultSabotage + ": \t\tRank = " + spin2.getSelectedItem().toString() +
                            "\n" + txtResultScreamandShout + ": \t\tRank = " + spin3.getSelectedItem().toString() +
                            "\n" + txtResultSpreadTooThin + ": \t\tRank = " + spin4.getSelectedItem().toString() +
                            "\n" + txtResultTroublemaker + ": \t\tRank = " + spin5.getSelectedItem().toString());

                    //..set spinners to disabled and light red background courtesy of
                    //..  ..https://color.adobe.com/create/color-wheel
                    spin1.setBackgroundColor(0xffFFE2E6);
                    spin2.setBackgroundColor(0xffFFE2E6);
                    spin3.setBackgroundColor(0xffFFE2E6);
                    spin4.setBackgroundColor(0xffFFE2E6);
                    spin5.setBackgroundColor(0xffFFE2E6);

                    spin1.setEnabled(false);
                    spin2.setEnabled(false);
                    spin3.setEnabled(false);
                    spin4.setEnabled(false);
                    spin5.setEnabled(false);


                }

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
                    button1.setTextColor(Color.RED);
                    button1.setText(getString(R.string.txtPause));
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpFiftyWays.pause();
                    playing = 0;
                    button1.setTextColor(Color.GREEN);
                    button1.setText(getString(R.string.txtPlay));
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
                    button2.setTextColor(Color.RED);
                    button2.setText(getString(R.string.txtPause));
                    button1.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpSabotage.pause();
                    playing = 0;
                    button2.setTextColor(Color.GREEN);
                    button2.setText(getString(R.string.txtPlay));
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
                    button3.setTextColor(Color.RED);
                    button3.setText(getString(R.string.txtPause));
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpScream.pause();
                    playing = 0;
                    button3.setTextColor(Color.GREEN);
                    button3.setText(getString(R.string.txtPlay));
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
                    button4.setTextColor(Color.RED);
                    button4.setText(getString(R.string.txtPause));
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button5.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpSpread.pause();
                    playing = 0;
                    button4.setTextColor(Color.GREEN);
                    button4.setText(getString(R.string.txtPlay));
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
                    button5.setTextColor(Color.RED);
                    button5.setText(getString(R.string.txtPause));
                    button1.setVisibility(View.INVISIBLE);
                    button2.setVisibility(View.INVISIBLE);
                    button3.setVisibility(View.INVISIBLE);
                    button4.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    mpTrouble.pause();
                    playing = 0;
                    button5.setTextColor(Color.GREEN);
                    button5.setText(getString(R.string.txtPlay));
                    button1.setVisibility(View.VISIBLE);
                    button2.setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);
                    button4.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };



}

