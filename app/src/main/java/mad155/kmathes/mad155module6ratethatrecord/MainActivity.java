package mad155.kmathes.mad155module6ratethatrecord;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
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
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    //.. buttons for playing music
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    Button button5;

    MediaPlayer mpFiftyWays, mpSabotage, mpScream, mpSpread, mpTrouble;

    //.. used for pause/play
    int playing;

    //.. setup for spinners list
    String[] spnArrayNbrs = new String[] {"Click to Rank", "1","2","3","4","5"};
    List<String> spnListNbrs = new ArrayList<>(Arrays.asList(spnArrayNbrs));

    //.. used to see what spinner item rank was selected
    String selectedItemTextA = " ";
    String selectedItemTextB = " ";
    String selectedItemTextC = " ";
    String selectedItemTextD = " ";
    String selectedItemTextE = " ";

    //.. used if user wants to rerank items again
    boolean restartFlag = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //.. set the title on the action bar for this activity and override title in manifest
        //setTitle(R.string.txt_play_music);

        //.. to get rid of action bar for this activity (because I needed more room on screen
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

        //.. used to set the color for each spinner - in the drop down and after selected (makes it easier to see choice)
        final int spin0Color = 0xffAEBAC4;
        final int spin1Color = 0xff8933F9;
        final int spin2Color = 0xffBA8AFF;
        final int spin3Color = 0xff3174FF;
        final int spin4Color = 0xff60DCFF;
        final int spin5Color = 0xff96FFFF;

        final Button btn1 = (Button) findViewById(R.id.btnNext);

        final TextView txtResults = (TextView) findViewById(R.id.textView1);
        //.. used so TextView for results can be scrollable if needed
        txtResults.setMovementMethod(new ScrollingMovementMethod());

        final String txtErrorDuplicateSpnr1 = getString(R.string.txtErrorDuplicateSpnr1);
        final String txtErrorDuplicateSpnr2 = getString(R.string.txtErrorDuplicateSpnr2);


        //.. array adapter - only one needed for all 5 spinners
        final ArrayAdapter<String> spinnerArrayAdapter1 = new ArrayAdapter<String>(this,
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
                    //tv.setBackgroundColor(spin0Color);
                    tv.setBackgroundColor(Color.GRAY);
                } else if (position == 1){
                    tv.setTextColor(Color.BLACK);
                    tv.setBackgroundColor(spin1Color);
                } else if (position == 2){
                    tv.setTextColor(Color.BLACK);
                    tv.setBackgroundColor(spin2Color);
                } else if (position == 3){
                    tv.setTextColor(Color.BLACK);
                    tv.setBackgroundColor(spin3Color);
                } else if (position == 4){
                    tv.setTextColor(Color.BLACK);
                    tv.setBackgroundColor(spin4Color);
                } else if (position == 5){
                    tv.setTextColor(Color.BLACK);
                    tv.setBackgroundColor(spin5Color);
                } else {
                    tv.setTextColor(Color.WHITE);
                    tv.setBackgroundColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter1.setDropDownViewResource(R.layout.spinner_item);

        //..for Fifty Ways
        spin1.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedItemTextA = (String) parent.getItemAtPosition(position);

                //.. set spinner background color to that of item selected
                switch(position) {
                    case 0:
                        //spin1.setBackgroundColor(spin0Color);
                        break;
                    case 1:
                        spin1.setBackgroundColor(spin1Color);
                        break;
                    case 2:
                        spin1.setBackgroundColor(spin2Color);
                        break;
                    case 3:
                        spin1.setBackgroundColor(spin3Color);
                        break;
                    case 4:
                        spin1.setBackgroundColor(spin4Color);
                        break;
                    case 5:
                        spin1.setBackgroundColor(spin5Color);
                        break;
                    default:
                        spin1.setBackgroundColor(Color.RED);
                        break;
                }

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

        //.. for Sabotage
        spin2.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedItemTextB = (String) parent.getItemAtPosition(position);

                //.. set spinner background color to that of item selected
                switch(position) {
                    case 0:
                        //spin2.setBackgroundColor(spin0Color);
                        break;
                    case 1:
                        spin2.setBackgroundColor(spin1Color);
                        break;
                    case 2:
                        spin2.setBackgroundColor(spin2Color);
                        break;
                    case 3:
                        spin2.setBackgroundColor(spin3Color);
                        break;
                    case 4:
                        spin2.setBackgroundColor(spin4Color);
                        break;
                    case 5:
                        spin2.setBackgroundColor(spin5Color);
                        break;
                    default:
                        spin2.setBackgroundColor(Color.RED);
                        break;
                }

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

        //..for Scream and Shout
        spin3.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               selectedItemTextC = (String) parent.getItemAtPosition(position);

                //.. set spinner background color to that of item selected
                switch(position) {
                    case 0:
                        //spin3.setBackgroundColor(spin0Color);
                        break;
                    case 1:
                        spin3.setBackgroundColor(spin1Color);
                        break;
                    case 2:
                        spin3.setBackgroundColor(spin2Color);
                        break;
                    case 3:
                        spin3.setBackgroundColor(spin3Color);
                        break;
                    case 4:
                        spin3.setBackgroundColor(spin4Color);
                        break;
                    case 5:
                        spin3.setBackgroundColor(spin5Color);
                        break;
                    default:
                        spin3.setBackgroundColor(Color.RED);
                        break;
                }

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

        //..for Spread Too Thin
        spin4.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedItemTextD = (String) parent.getItemAtPosition(position);

                //.. set spinner background color to that of item selected
                switch(position) {
                    case 0:
                        //spin4.setBackgroundColor(spin0Color);
                        break;
                    case 1:
                        spin4.setBackgroundColor(spin1Color);
                        break;
                    case 2:
                        spin4.setBackgroundColor(spin2Color);
                        break;
                    case 3:
                        spin4.setBackgroundColor(spin3Color);
                        break;
                    case 4:
                        spin4.setBackgroundColor(spin4Color);
                        break;
                    case 5:
                        spin4.setBackgroundColor(spin5Color);
                        break;
                    default:
                        spin4.setBackgroundColor(Color.RED);
                        break;
                }

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

        //..for Troublemaker
        spin5.setAdapter(spinnerArrayAdapter1);
        // When an item is selected...
        spin5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectedItemTextE = (String) parent.getItemAtPosition(position);

                //.. set spinner background color to that of item selected
                switch(position) {
                    case 0:
                        //spin5.setBackgroundColor(spin0Color);
                        break;
                    case 1:
                        spin5.setBackgroundColor(spin1Color);
                        break;
                    case 2:
                        spin5.setBackgroundColor(spin2Color);
                        break;
                    case 3:
                        spin5.setBackgroundColor(spin3Color);
                        break;
                    case 4:
                        spin5.setBackgroundColor(spin4Color);
                        break;
                    case 5:
                        spin5.setBackgroundColor(spin5Color);
                        break;
                    default:
                        spin5.setBackgroundColor(Color.RED);
                        break;
                }

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

        //.. button to rank choices and setText summary in TextView
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //.. check to see if all choices for spinner selected
                boolean proceedFlag = true;
                //.. check to see if user picked duplicates
                boolean duplicateFlag = false;
                //.. to save value of duplicates
                String duplicateValue = "";

                //.. list used to assist with checking for duplicates - duplicates cannot be add to
                //..   a list that's "Set<String>" rather than "List<String>"
                Set<String> setList = new HashSet<>();

               //.. "reset" the screen if user wants to do ranking again
                if(restartFlag){
                   spin1.setEnabled(true);
                   spin1.setAdapter(spinnerArrayAdapter1);
                   spin2.setEnabled(true);
                   spin2.setAdapter(spinnerArrayAdapter1);
                   spin3.setEnabled(true);
                   spin3.setAdapter(spinnerArrayAdapter1);
                   spin4.setEnabled(true);
                   spin4.setAdapter(spinnerArrayAdapter1);
                   spin5.setEnabled(true);
                   spin5.setAdapter(spinnerArrayAdapter1);
                   txtResults.setText(" ");
                   btn1.setText(getString(R.string.btnNext));
                   System.out.println("I AM TRYING TO RESET");
                   restartFlag = false;
               }


                if (selectedItemTextA.isEmpty() || selectedItemTextA == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {
                    //.. position 0 is the text that says "Click to Rank" in spnArrayNbrs and spnListNbrs -
                    //   It's in the beginning of the code and are the items that show up in the drop down spinner list.
                    //   If "Click to Rank" is showing, it means that user did not choose a rank and needs to do so
                    //   before proceeding. A message is formatted and sent to txtResults to indicate this
                    if(spin1.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL);
                        //txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
                        proceedFlag = false;
                    }
                    //..try to add selection to "Set" list to see if it works to check for duplicates
                    if(spin1.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextA)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextA;
                        }
                    }
                }

                if (selectedItemTextB.isEmpty() || selectedItemTextB == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {
                    //.. position 0 is the text that says "Click to Rank" in spnArrayNbrs and spnListNbrs -
                    //   It's in the beginning of the code and are the items that show up in the drop down spinner list.
                    //   If "Click to Rank" is showing, it means that user did not choose a rank and needs to do so
                    //   before proceeding. A message is formatted and sent to txtResults to indicate this
                    if(spin2.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
                        proceedFlag = false;
                    }
                    //..try to add selection to "Set" list to see if it works to check for duplicates
                    if(spin2.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextB)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextB;
                        }
                    }
                }

                if (selectedItemTextC.isEmpty() || selectedItemTextC == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {
                    //.. position 0 is the text that says "Click to Rank" in spnArrayNbrs and spnListNbrs -
                    //   It's in the beginning of the code and are the items that show up in the drop down spinner list.
                    //   If "Click to Rank" is showing, it means that user did not choose a rank and needs to do so
                    //   before proceeding. A message is formatted and sent to txtResults to indicate this
                    if(spin3.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
                        proceedFlag = false;
                    }
                    //..try to add selection to "Set" list to see if it works to check for duplicates
                    if(spin3.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextC)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextC;
                        }
                    }
                }

                if (selectedItemTextD.isEmpty() || selectedItemTextD == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {
                    //.. position 0 is the text that says "Click to Rank" in spnArrayNbrs and spnListNbrs -
                    //   It's in the beginning of the code and are the items that show up in the drop down spinner list.
                    //   If "Click to Rank" is showing, it means that user did not choose a rank and needs to do so
                    //   before proceeding. A message is formatted and sent to txtResults to indicate this
                    if(spin4.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
                        proceedFlag = false;
                    }
                    //..try to add selection to "Set" list to see if it works to check for duplicates
                    if(spin4.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextD)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextD;
                        }
                    }
                }

                if (selectedItemTextE.isEmpty() || selectedItemTextE == null ) {
                    System.out.println("SELECTED PROJECT IS EMPTY OR NULL");
                } else {
                    //.. position 0 is the text that says "Click to Rank" in spnArrayNbrs and spnListNbrs -
                    //   It's in the beginning of the code and are the items that show up in the drop down spinner list.
                    //   If "Click to Rank" is showing, it means that user did not choose a rank and needs to do so
                    //   before proceeding. A message is formatted and sent to txtResults to indicate this
                    if(spin5.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
                        proceedFlag = false;
                    }
                    //..try to add selection to "Set" list to see if it works to check for duplicates
                    if(spin5.getSelectedItemPosition() != 0) {
                        if (!setList.add(selectedItemTextE)) {
                            duplicateFlag = true;
                            duplicateValue = selectedItemTextE;
                        }
                    }
                }

                //.. used for debugging
                //System.out.println("Values : \n" + selectedItemTextA + "\n" +
                //       selectedItemTextB + "\n" + selectedItemTextC + "\n" + selectedItemTextD + "\n"
                //        + selectedItemTextE + "\n");
                //System.out.println("Duplicate Values: " + duplicateValue);

                //.. if duplicates ranks were entered by user
                if(duplicateFlag) {

                    Toast.makeText
                            (getApplicationContext(), "Duplicate rankings detected", Toast.LENGTH_SHORT)
                            .show();
                    txtResults.setBackgroundColor(Color.BLACK);
                    txtResults.setTextColor(Color.RED);
                    txtResults.setGravity(Gravity.CENTER_HORIZONTAL);
                    txtResults.setText(getText(R.string.txtErrorDuplicateSpnr1) + " " + duplicateValue +
                            getText(R.string.txtErrorDuplicateSpnr2));
                }

                //.. if no duplicates and user entered a rank for each song
                if (proceedFlag && !duplicateFlag){

                    txtResults.setBackgroundColor(Color.WHITE);
                    txtResults.setTextColor(Color.BLUE);
                    txtResults.setGravity(Gravity.START | Gravity.TOP);

                    //.. get rankings from each spinner
                    String holdSpin1 = spin1.getSelectedItem().toString();
                    String holdSpin2 = spin2.getSelectedItem().toString();
                    String holdSpin3 = spin3.getSelectedItem().toString();
                    String holdSpin4 = spin4.getSelectedItem().toString();
                    String holdSpin5 = spin5.getSelectedItem().toString();

                    //.. set up new list so that rankings and songs/artists can be "sorted"
                    //   in order for creating setText for txtResults TextView widget
                    List<String> resultsList = new ArrayList<>();

                    //.. loop through 5 times (for all 5 songs) and build rank/song/artist string and
                    //   store it in the corresponding List element. ie. if a song ranked 3rd, store it
                    //   (the results string to be output) in the 3rd element of the list
                    for(int k=0; k<6; k++) {
                        if(holdSpin1.equalsIgnoreCase(String.valueOf(k+1))) {
                            //results1 = holdSpin1 + ". " + getText(R.string.resultsFiftyWays);
                            resultsList.add(k, holdSpin1 + ". " + getText(R.string.resultsFiftyWays));
                        }
                        if(holdSpin2.equalsIgnoreCase(String.valueOf(k+1))) {
                            //results1 = holdSpin1 + ". " + getText(R.string.resultsFiftyWays);
                            resultsList.add(k, holdSpin2 + ". " + getText(R.string.resultsSabotage));
                        }
                        if(holdSpin3.equalsIgnoreCase(String.valueOf(k+1))) {
                            //results1 = holdSpin1 + ". " + getText(R.string.resultsFiftyWays);
                            resultsList.add(k, holdSpin3 + ". " + getText(R.string.resultsScreamAndShout));
                        }
                        if(holdSpin4.equalsIgnoreCase(String.valueOf(k+1))) {
                            //results1 = holdSpin1 + ". " + getText(R.string.resultsFiftyWays);
                            resultsList.add(k, holdSpin4 + ". " + getText(R.string.resultsSpreadTooThin));
                        }
                        if(holdSpin5.equalsIgnoreCase(String.valueOf(k+1))) {
                            //results1 = holdSpin1 + ". " + getText(R.string.resultsFiftyWays);
                            resultsList.add(k, holdSpin5 + ". " + getText(R.string.resultsTroubleMaker));
                        }
                    }

                    //.. "read" from the List built above and format/setText the results output
                    txtResults.setText("\nSummary... Thank you for your submission!" +
                            "\n\n" + resultsList.get(0) +
                            "\n" + resultsList.get(1) +
                            "\n" + resultsList.get(2) +
                            "\n" + resultsList.get(3) +
                            "\n" + resultsList.get(4));


                    //.. old way to print out ... this doesn't come out strict number order though
                    //txtResults.setText("\nSummary... Thank you for your submission!" +
                    //      "\n\n" + spin1.getSelectedItem().toString() + ". " + getText(R.string.resultsFiftyWays) +
                    //        "\n" + spin2.getSelectedItem().toString() + ". " + getText(R.string.resultsSabotage) +
                    //        "\n" + spin3.getSelectedItem().toString() + ". " + getText(R.string.resultsScreamAndShout) +
                    //        "\n" + spin4.getSelectedItem().toString()+ ". " + getText(R.string.resultsSpreadTooThin) +
                    //        "\n" + spin5.getSelectedItem().toString()+ ". " + getText(R.string.resultsTroubleMaker));

                    //.. also set spinners to be disabled and light gray background courtesy of
                    //   https://color.adobe.com/create/color-wheel
                    int finishedBackgroundColor = 0xffEFE0FF;
                    //.. for light red
                    //int finishedBackgroundColor = 0xffFFE2E6;
                    spin1.setBackgroundColor(finishedBackgroundColor);
                    spin2.setBackgroundColor(finishedBackgroundColor);
                    spin3.setBackgroundColor(finishedBackgroundColor);
                    spin4.setBackgroundColor(finishedBackgroundColor);
                    spin5.setBackgroundColor(finishedBackgroundColor);

                    spin1.setEnabled(false);
                    spin2.setEnabled(false);
                    spin3.setEnabled(false);
                    spin4.setEnabled(false);
                    spin5.setEnabled(false);

                    //.. set flag in case user wants to rerank all
                    restartFlag = true;
                    //.. change button text
                    btn1.setText("Click to Rerank");
                }

            }

        });


    }

    //..button1 listener to play/pause Fifty Ways
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
    //..button2 listener to play/pause Sabotage
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
    //..button3 listener to play/pause Scream and Shout
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
    //..button4 listener to play/pause Spread Too Thin
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
    //..button5 listener to play/pause Troublemaker
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

