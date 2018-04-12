package com.example.strahinja.contacts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Strahinja on 4/9/2018.
 */

public class AddNewContactActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText addFirstName, addLastName, addPhone;
    Button createContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        myDb = new DBHelper(this);

        addFirstName = findViewById(R.id.add_first_name);
        addLastName = findViewById(R.id.add_last_name);
        addPhone = findViewById(R.id.add_phone);

        createContact = findViewById(R.id.create_contact);

        createContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(addFirstName.getText().toString(),
                        addLastName.getText().toString(),
                        addPhone.getText().toString() );
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
