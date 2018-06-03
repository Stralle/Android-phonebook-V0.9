package com.example.strahinja.contacts;

import android.app.TabActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentFavorite extends Fragment {

    View view;

    public FragmentFavorite() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
        initRecyclerView(view);
        return view;
    }

    private void initRecyclerView(View view){
        RecyclerView recycler = view.findViewById(R.id.recycler_view2);
        RecycleCardAdapter adapter = new RecycleCardAdapter(MainActivity.allContacts, getContext());
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

}
