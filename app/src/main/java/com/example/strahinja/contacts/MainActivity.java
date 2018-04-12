package com.example.strahinja.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper myDb;
    FloatingActionButton fab;
    ArrayList<Contact> allContacts = new ArrayList<>();
    ListView myListView;

    ArrayAdapter<Contact> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DBHelper(this);


//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, AddNewContactActivity.class);
                startActivity(myIntent);
//                finish();
//                Snackbar.make(view,"Adding new contact", Snackbar.LENGTH_LONG)
//               we
//         .setAction("Action", null).show();
            }
        });

        myListView = (ListView) findViewById(R.id.myListView);

//        myDb.deleteAll();

//        myDb.insertData("Petar", "Petrovic", "060666555");
//        myDb.insertData("Nikola", "Jevrosimovic", "060666555");
//        myDb.insertData("Miodrag", "Zivoradic", "060666555");

        arrayAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, allContacts);

//        getThemAll();

        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent myIntent = new Intent(MainActivity.this, EditContactActivity.class);

                Log.i("FNAME", allContacts.get(i).getFirstName());
                Log.i("LNAME", allContacts.get(i).getLastName());
                Log.i("PHONE", allContacts.get(i).getPhone());
                myIntent.putExtra("fname", String.valueOf(allContacts.get(i).getFirstName()));
                myIntent.putExtra("lname", String.valueOf(allContacts.get(i).getLastName()));
                myIntent.putExtra("phone", String.valueOf(allContacts.get(i).getPhone()));
                myIntent.putExtra("id", String.valueOf(allContacts.get(i).getId()));
                startActivity(myIntent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Resuming", "back to my first activity");

        arrayAdapter.notifyDataSetChanged();
        getThemAll();

    }

    private void getThemAll() {

        allContacts.removeAll(allContacts);

        myListView = (ListView) findViewById(R.id.myListView);

        Cursor res = myDb.getAllData();

        if(res.getCount() == 0) {
            // show message
            showMessage("Oops nothing found :( ","No contacts in database");
            return;
        }

        while (res.moveToNext()) {
            Contact c = new Contact(res.getString(0), res.getString(1), res.getString(2), res.getString(3));
            allContacts.add(c);
        }

        myListView.setAdapter(arrayAdapter);

    }
}
