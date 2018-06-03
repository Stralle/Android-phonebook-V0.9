package com.example.strahinja.contacts;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static DBHelper myDb;
    public static ArrayList<Contact> allContacts = new ArrayList<>();

    private TabLayout tabLayout;
    private ViewPagerAdapter adapter;
    private ViewPager viewpager;
    private ArrayAdapter<Contact> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tablayout_id);
        viewpager = (ViewPager) findViewById(R.id.viewpager_id);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        //Adding fragments
//        adapter.addFragment(new FragmentCall(), "");
        adapter.addFragment(new FragmentContact(), "");
        adapter.addFragment(new FragmentFavorite(), "");

        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);

//        tabLayout.getTabAt(0).setIcon(R.drawable.ic_call);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_group);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_star);


        myDb = new DBHelper(this);
        arrayAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, allContacts);

        getThemAll();

//        allContacts.add(new Contact("1", "Pera", "Peric", "123456789"));
//        allContacts.add(new Contact("2", "Zika", "Peric", "123456789"));
//        allContacts.add(new Contact("3", "Laza", "Peric", "123456789"));
//        allContacts.add(new Contact("4", "Mika", "Peric", "123456789"));
//        allContacts.add(new Contact("5", "Kica", "Peric", "123456789"));

//        myListView = (ListView) findViewById(R.id.myListView);
//
//        arrayAdapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, allContacts);
//
//        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                Intent myIntent = new Intent(MainActivity.this, EditContactActivity.class);
//
//                Log.i("FNAME", allContacts.get(i).getFirstName());
//                Log.i("LNAME", allContacts.get(i).getLastName());
//                Log.i("PHONE", allContacts.get(i).getPhone());
//                myIntent.putExtra("fname", String.valueOf(allContacts.get(i).getFirstName()));
//                myIntent.putExtra("lname", String.valueOf(allContacts.get(i).getLastName()));
//                myIntent.putExtra("phone", String.valueOf(allContacts.get(i).getPhone()));
//                myIntent.putExtra("id", String.valueOf(allContacts.get(i).getId()));
//                startActivity(myIntent);
//
//            }
//        });
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


//    public static void showMessage(String title, String Message)
//    {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(Message);
//        builder.show();
//    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Resuming", "back to my first activity");

//        arrayAdapter.notifyDataSetChanged();

        getThemAll();

    }

    public static ArrayList<Contact> getThemAll() {

        Cursor res = myDb.getAllData();

        allContacts.removeAll(allContacts);

//        if(res.getCount() == 0) {
//            // show message
//            showMessage("Oops nothing found :( ","No contacts in database");
//            return null;
//        }

        while (res.moveToNext()) {
            Contact c = new Contact(res.getString(0), res.getString(1), res.getString(2), res.getString(3), Integer.parseInt(res.getString(4)));
            Log.d("TAG", "getThemAll: " + c.toString() + c.getFavorite());
            allContacts.add(c);
        }

        return allContacts;

    }
}
