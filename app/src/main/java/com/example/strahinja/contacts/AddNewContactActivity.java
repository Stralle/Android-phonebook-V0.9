package com.example.strahinja.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by Strahinja on 4/9/2018.
 */

public class AddNewContactActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText addFirstName, addLastName, addPhone;
    Button createContact;
    ImageView addImage;
    CheckBox favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        myDb = new DBHelper(this);

        addFirstName = findViewById(R.id.add_first_name);
        addLastName = findViewById(R.id.add_last_name);
        addPhone = findViewById(R.id.add_phone);
        addImage = findViewById(R.id.add_image);
        favorite = findViewById(R.id.add_favorite);

        createContact = findViewById(R.id.create_contact);


        //Image integer? or string?
        createContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(addFirstName.getText().toString(),
                        addLastName.getText().toString(),
                        addPhone.getText().toString(),
                        favorite.isChecked()?1:0);
                if(isInserted == true) {
                    Toast.makeText(AddNewContactActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
//                    Intent myIntent = new Intent(AddNewContactActivity.this, MainActivity.class);
//                    AddNewContactActivity.this.startActivity(myIntent);

                    finish();
                }
                else
                    Toast.makeText(AddNewContactActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
            }
        });



    }
}
