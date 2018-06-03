package com.example.strahinja.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FragmentContact extends Fragment {

    private View view;
    private RecyclerView myRecyclerView;
    private List<Contact> listContacts;
    private RecyclerViewAdapter recyclerViewAdapter;
    private FloatingActionButton fab;
    private boolean flag = false;

    public FragmentContact() { }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.contact_fragment, container, false);

        myRecyclerView = (RecyclerView) view.findViewById(R.id.contact_recyclerview);
        recyclerViewAdapter = new RecyclerViewAdapter(getContext(), listContacts);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerViewAdapter);

        if(flag) {
            showMessage("Oops nothing found :( ","No contacts in database");
        }

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getActivity(), AddNewContactActivity.class);
                startActivity(myIntent);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listContacts = new ArrayList<Contact>();

        listContacts = MainActivity.getThemAll();

        if (listContacts.isEmpty()) {
            flag = true;
        }
        else {
            flag = false;
        }

    }

    public void showMessage(String title, String Message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
