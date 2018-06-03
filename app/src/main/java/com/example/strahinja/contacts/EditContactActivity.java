package com.example.strahinja.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

/**
 * Created by Strahinja on 4/11/2018.
 */

public class EditContactActivity extends AppCompatActivity {

    DBHelper myDb;
    EditText editFirstName, editLastName, editPhone;
    String id;
    CheckBox favorite;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        myDb = new DBHelper(this);

        editFirstName = findViewById(R.id.edit_first_name);
        editLastName = findViewById(R.id.edit_last_name);
        editPhone = findViewById(R.id.edit_phone);
        favorite = findViewById(R.id.edit_favorite);

        String fname = getIntent().getStringExtra("fname");
        String lname= getIntent().getStringExtra("lname");
        String phone = getIntent().getStringExtra("phone");
        id = getIntent().getStringExtra("id");

        int fav = 0;

        Log.i("ALL CONTACTS", id);
        for(Contact c:MainActivity.allContacts) {
            if(c.getId().equals(id)) {
                fav = c.getFavorite();
                Log.i("FAVORITE", String.valueOf(fav));
            }
        }

        if(fav == 1) {
            favorite.setChecked(true);
        }
        else {
            favorite.setChecked(false);
        }


        Log.i("FNAME", fname);
        Log.i("LNAME", lname);
        Log.i("WTF", phone);

        editFirstName.setText(fname);
        editLastName.setText(lname);
        editPhone.setText(phone);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.edit_save:
                myDb.updateData(id, editFirstName.getText().toString(), editLastName.getText().toString(), editPhone.getText().toString(), favorite.isChecked()?1:0);
                Intent i = new Intent(EditContactActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                return true;
            case R.id.edit_delete:
                myDb.deleteData(id);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
