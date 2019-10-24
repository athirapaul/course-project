package example.athirapaul.photoconnect;




import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class PhotographyStyleActivity extends AppCompatActivity {
    DatabaseHelper db;
    Spinner mySpinner;
    Button save;
     int ss;
//int checkedId;
String itemvalue;
   RadioGroup radioGroup;
   RadioGroup radioGroup2;
//RadioButton radioButton,radioButton2,radioButton3,radioButton4,radioButton5,radioButton6,radioButton7,radioButton8,radioButton9,radioButton10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photography_style);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("PHOTOGRAPHY STYLE ACTIVITY");
        final Spinner mySpinner = (Spinner) findViewById(R.id.spinneR);


        save = (Button) findViewById(R.id.save);
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        final RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.radioGroup2);

        final ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(PhotographyStyleActivity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        db = new DatabaseHelper(this);

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) mySpinner.getSelectedItem();

                if (!item.equals("")) {
               //     Toast.makeText(PhotographyStyleActivity.this, "Data added", Toast.LENGTH_LONG).show();
                    Log.i("CAMERANAME", item);
                  //  db.addPhotographytable(item,String.valueOf(checkedId));

                }
itemvalue = item;
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }

        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                if (checkedId == (R.id.radioButton)) {
                   // String s= String.valueOf(R.id.radioGroup);
                   // R.id.radioGroup.getText();
                    /*RadioGroup radioGroup=(RadioGroup)findViewById(checkedId);
                    CharSequence param = radioGroup.getText();
                    String p=String.valueOf(param);
                    Log.i("Art",p);*/

                   // Log.i("Art","art");
                 // String s1 = s.getText();
                   // String mEdit = ((EditText) findViewById(R.id.inText)).getText().toString();

                   // db.addPhotographytable(item,p);
                  Toast.makeText(PhotographyStyleActivity.this, "Data added", Toast.LENGTH_LONG).show();
                }
                    else if (checkedId == R.id.radioButton2) {

                    int s= R.id.radioButton2;
                  //  String s1 = s.getText();
                    Log.i("Wedding", "WEDDING");
                  //  String s1 = button.getText();
                    // db.addPhotographytable(item,"WEDDING");

                }

                 else if (checkedId == R.id.radioButton3) {
                    Log.i("Product", "PRODUCT");
                  //  db.addPhotographytable(item,"PRODUCT");

                } else if (checkedId == R.id.radioButton4) {
                    Log.i("Sports", "SPORTS");
                  //  db.addPhotographytable(item,"SPORTS");

                } else if (checkedId == R.id.radioButton5) {
                    Log.i("Lomography", "LOMOGRAPHY");
                 //   db.addPhotographytable(item,"LOMOGRAPHY");

                }
ss=checkedId;
            }

        });



        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup2, int checkedId2) {

          if (checkedId2 == R.id.radioButton6) {
                    Log.i("Portrait", "PORTRAIT");
                    //  db.addPhotographytable(item,"PORTRAIT");

                }

          else if (checkedId2 == R.id.radioButton7) {

                    Log.i("Landscape", "LANDSCAPE");
                    //   db.addPhotographytable(item,"LANDSCAPE");

                } else if (checkedId2 == R.id.radioButton8) {
                    Log.i("Industrial", "INDUSTRIAL");
                    //  db.addPhotographytable(item,"INDUSTRIAL");

                } else if (checkedId2 == R.id.radioButton9) {
                    Log.i("Action", "ACTION");
                    //  db.addPhotographytable(item,"ACTION");

                } else {
                    Log.i("Film/Retro", "FILM/RETRO");
                    //  db.addPhotographytable(item,"FILM/RETRO");

                }
                // return checkedId;
                ss=checkedId2;

            }

        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//db.addPhotographytable(item,"ART");
                RadioButton radioButton=(RadioButton)findViewById(ss);


                CharSequence param = radioButton.getText();

                    String p=String.valueOf(param);
                    db.addPhotographytable( itemvalue,p);
                    Log.i("cameraname",itemvalue);
                    Log.i("photographystyle",p);
                    Toast.makeText(PhotographyStyleActivity.this, "Data added", Toast.LENGTH_LONG).show();







            }

        });


    }
   /* public int abc(int s){

        return s;
    }*/
}