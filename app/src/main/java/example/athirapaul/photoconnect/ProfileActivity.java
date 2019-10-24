package example.athirapaul.photoconnect;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;


public class ProfileActivity extends AppCompatActivity {
    String receive11;
    private int STORAGE_PERMISSION_CODE = 23;

    DatabaseHelper db;
    Button save;
    EditText EditFirstName ;
    Intent intent;
    EditText EditLastName;
    Spinner mySpinner;
    RadioButton radioButton;
    ImageView imageView;
    Uri currImageURI;
byte[] ImageValue ;
    private  static final  int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        // Button button3 = (Button) findViewById(R.id.button3);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},STORAGE_PERMISSION_CODE);

        imageView = (ImageView)findViewById(R.id.imageView);
        EditFirstName = (EditText)findViewById(R.id.EditFirstName);
        EditLastName = (EditText)findViewById(R.id.EditLastName) ;
        save = (Button) findViewById(R.id.save);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
                // Intent intent2 = new Intent(Intent.ACTION_PICK,Uri.parse("contents://media/internal/images/media"));
                // startActivityForResult(intent2,PICK_IMAGE);
            }
        });
        db = new DatabaseHelper(this);





        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //     receive11.valueOf(getIntent().getStringExtra("Cameraname"));

                //String ID =  EditFirstName.getText().toString();
                String FIRSTNAME = EditFirstName.getText().toString();

                String LASTNAME = EditLastName.getText().toString();

                //  String CAMERANAME = receive11;
//String PHOTOGRAPHYSTYLE = radioButton.toString();
                // intent = new Intent(getApplicationContext(),PhotographyStyleActivity.class);
                //  intent.putExtra("EdiTtEXTvALUE1", FIRSTNAME);
                //  intent.putExtra("EdiTtEXTvALUE2", LASTNAME);

                //   startActivity(intent);

                if (!FIRSTNAME.equals("") && db.addUserTable(FIRSTNAME, LASTNAME,ImageValue) && !LASTNAME.equals("") ) {
                    Toast.makeText(ProfileActivity.this, "Data", Toast.LENGTH_SHORT).show();
                    EditFirstName.setText("");
                    EditLastName.setText("");

                } else {
                    Toast.makeText(ProfileActivity.this, "Data not added", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        ImageValue = byteArray ;
      //  bitmap.recycle();
//        if(db.addImageTable(byteArray) != false){
//            Toast.makeText(ProfileActivity.this, "Data added", Toast.LENGTH_SHORT).show();
//
//        }
//        else
//            Toast.makeText(ProfileActivity.this, "Data not added", Toast.LENGTH_SHORT).show();
//

    }


}