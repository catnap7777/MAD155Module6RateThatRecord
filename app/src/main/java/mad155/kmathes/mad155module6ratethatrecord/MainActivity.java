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

    boolean restartFlag = false;


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

        final int spin0Color = 0xffAEBAC4;
        final int spin1Color = 0xff8933F9;
        final int spin2Color = 0xffBA8AFF;
        final int spin3Color = 0xff3174FF;
        final int spin4Color = 0xff60DCFF;
        final int spin5Color = 0xff96FFFF;

        final Button btn1 = (Button) findViewById(R.id.btnNext);

        final TextView txtResults = (TextView) findViewById(R.id.textView1);
        txtResults.setMovementMethod(new ScrollingMovementMethod());

        //final String txtResultFiftyWays = getString(R.string.txtFiftyWays);
        //final String txtResultSabotage = getString(R.string.txtSabotage);
        //final String txtResultScreamandShout = getString(R.string.txtScreamAndShout);
        //final String txtResultSpreadTooThin = getString(R.string.txtSpreadTooThin);
        //final String txtResultTroublemaker = getString(R.string.txtTroubleMaker);

        //final String txtErrorSelectSpnr = getString(R.string.txtErrorSelectSpnr);
        final String txtErrorDuplicateSpnr1 = getString(R.string.txtErrorDuplicateSpnr1);
        final String txtErrorDuplicateSpnr2 = getString(R.string.txtErrorDuplicateSpnr2);


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
                //} else {
                    //tv.setTextColor(Color.BLACK);
                    //..light blue
                    //tv.setBackgroundColor(0xff8FDDFF);
                } else if (position == 1){
                    tv.setTextColor(Color.BLACK);
                    //..light blue
                    //tv.setBackgroundColor(0xff9CB1FF);
                    tv.setBackgroundColor(spin1Color);
                } else if (position == 2){
                    tv.setTextColor(Color.BLACK);
                    //..light blue
                    //tv.setBackgroundColor(0xffAAD0FF);
                    tv.setBackgroundColor(spin2Color);
                } else if (position == 3){
                    tv.setTextColor(Color.BLACK);
                    //..light blue
                    //tv.setBackgroundColor(0xff8FDDFF);
                    tv.setBackgroundColor(spin3Color);
                } else if (position == 4){
                    tv.setTextColor(Color.BLACK);
                    //..light blue
                    //tv.setBackgroundColor(0xff9CF9FF);
                    tv.setBackgroundColor(spin4Color);
                } else if (position == 5){
                    tv.setTextColor(Color.BLACK);
                    //..light blue
                    //tv.setBackgroundColor(0xffC1FFFF);
                    tv.setBackgroundColor(spin5Color);
                } else {
                    tv.setTextColor(Color.WHITE);
                    tv.setBackgroundColor(Color.BLACK);
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

                //System.out.println("SELECTEDITEMTEXTA = " + srelectedItemTextA);

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
                //boolean restartFlag = false;

                //public static <T> boolean hasDuplicate(Iterable<T> all) {
                //    Set<T> set = new HashSet<T>();
                Set<String> setList = new HashSet<>();

                //System.out.println("MADE IT TO THE BUTTON CLICK");
                //check to see if there is a "value" for the spinner field. if not, make user select one before
                // proceeding... set up another nexted if
                // if(selectItemTextA equals "rank your song" ie. position 0....message user and dont continue until they select
                //********

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

                    if(spin1.getSelectedItemPosition() == 0) {
                        txtResults.setBackgroundColor(Color.BLACK);
                        txtResults.setTextColor(Color.RED);
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        //txtResults.setText(txtErrorSelectSpnr);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
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
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        //txtResults.setText(txtErrorSelectSpnr);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
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
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        //txtResults.setText(txtErrorSelectSpnr);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
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
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        //txtResults.setText(txtErrorSelectSpnr);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
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
                        txtResults.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
                        //txtResults.setText("Please select a rank for each song");
                        //txtResults.setText(txtErrorSelectSpnr);
                        txtResults.setText(getText(R.string.txtErrorSelectSpnr));
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
                    txtResults.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL);
                    //txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.TOP);
                    //txtResults.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    //txtResults.setText("Duplicate ranking found: " + duplicateValue +
                    //        "\nPlease correct and resubmit by clicking button");
                    //txtResults.setText( txtErrorDuplicateSpnr1 + " " + duplicateValue + txtErrorDuplicateSpnr2);
                    txtResults.setText(getText(R.string.txtErrorDuplicateSpnr1) + " " + duplicateValue +
                            getText(R.string.txtErrorDuplicateSpnr2));
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
                    //txtResults.setText("\nSummary... Thank you for your submission!" +
                    //        "\n\n" + txtResultFiftyWays + ": \t\tRank = " + spin1.getSelectedItem().toString() +
                    //        "\n" + txtResultSabotage + ": \t\tRank = " + spin2.getSelectedItem().toString() +
                    //        "\n" + txtResultScreamandShout + ": \t\tRank = " + spin3.getSelectedItem().toString() +
                    //        "\n" + txtResultSpreadTooThin + ": \t\tRank = " + spin4.getSelectedItem().toString() +
                    //        "\n" + txtResultTroublemaker + ": \t\tRank = " + spin5.getSelectedItem().toString());

                    //String holdSpin1 = spin1.getSelectedItem().toString();
                    String holdSpin1 = spin1.getSelectedItem().toString();
                    String holdSpin2 = spin2.getSelectedItem().toString();
                    String holdSpin3 = spin3.getSelectedItem().toString();
                    String holdSpin4 = spin4.getSelectedItem().toString();
                    String holdSpin5 = spin5.getSelectedItem().toString();

                   //String[] array1 = new String[] {" ", " ", " ", " ", " "};

                    List<String> resultsList = new ArrayList<>();

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

                    txtResults.setText("\nSummary... Thank you for your submission!" +
                            "\n\n" + resultsList.get(0) +
                            "\n" + resultsList.get(1) +
                            "\n" + resultsList.get(2) +
                            "\n" + resultsList.get(3) +
                            "\n" + resultsList.get(4));


                    //.. old way to print out ... not in order though
                    //txtResults.setText("\nSummary... Thank you for your submission!" +
                    //      "\n\n" + spin1.getSelectedItem().toString() + ". " + getText(R.string.resultsFiftyWays) +
                    //        "\n" + spin2.getSelectedItem().toString() + ". " + getText(R.string.resultsSabotage) +
                    //        "\n" + spin3.getSelectedItem().toString() + ". " + getText(R.string.resultsScreamAndShout) +
                    //        "\n" + spin4.getSelectedItem().toString()+ ". " + getText(R.string.resultsSpreadTooThin) +
                    //        "\n" + spin5.getSelectedItem().toString()+ ". " + getText(R.string.resultsTroubleMaker));

                    //..set spinners to disabled and light red background courtesy of
                    //..  ..https://color.adobe.com/create/color-wheel
                    int finishedBackgroundColor = 0xffEFE0FF;
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

                    restartFlag = true;
                    btn1.setText("Click to Rerank");





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

