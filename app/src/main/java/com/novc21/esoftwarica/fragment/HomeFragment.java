package com.novc21.esoftwarica.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.novc21.esoftwarica.MainActivity;
import com.novc21.esoftwarica.R;
import com.novc21.esoftwarica.adapter.StudentsAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    SwipeRefreshLayout Swipe;
    RecyclerView recyclerView;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        Swipe = view.findViewById(R.id.Swipe);
        load();
        Swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Swipe.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        return view;
    }

    private void load() {
        if (MainActivity.studentsList.isEmpty()) {
            Toast.makeText(getContext(), "empty", Toast.LENGTH_SHORT).show();
        } else {
            StudentsAdapter studentsAdapter = new StudentsAdapter(getContext(), MainActivity.studentsList);
            recyclerView.setAdapter(studentsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }

}
