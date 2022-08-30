package com.siddharth.massengerapp.Fragments;

import static com.siddharth.massengerapp.Utils.ConnectionManager.isNetworkAvailable;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.siddharth.massengerapp.Activities.LogInActivity;
import com.siddharth.massengerapp.Adapters.UsersAdapter;
import com.siddharth.massengerapp.Models.Users;
import com.siddharth.massengerapp.R;
import com.siddharth.massengerapp.databinding.FragmentChatBinding;

import java.util.ArrayList;


public class ChatFragment extends Fragment {
    public ChatFragment() {
        // Required empty public constructor
    }


    FragmentChatBinding binding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase database;
    UsersAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentChatBinding.inflate(inflater, container, false);
        database = FirebaseDatabase.getInstance();


        /*Check if the internet is present or not*/
        if (isNetworkAvailable(getContext())) {

            database.getReference().child("Users").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    list.clear();

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Users users = dataSnapshot.getValue(Users.class);
                        users.setUserId(dataSnapshot.getKey());
//                        Toast.makeText(getContext(),users.getPassword(),Toast.LENGTH_SHORT).show();
                        list.add(users);
                    }
                    adapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


            adapter = new UsersAdapter(list, getContext());
            binding.chatRecyclerview.setAdapter(adapter);


            LinearLayoutManager layoutManger = new LinearLayoutManager(getContext());
            binding.chatRecyclerview.setLayoutManager(layoutManger);

        }
        else
        {
//        AlertDialog builder = AlertDialog.Builder
//        builder.setTitle("Error")
//        builder.setMessage("No Internet Connection found. Please connect to the internet and re-open the app.")
//        builder.setCancelable(false)
//        builder.setPositiveButton("Ok") { _, _ ->
//                ActivityCompat.finishAffinity(activity as Activity)
//        }
//        builder.create()
//        builder.show()

//*********************************************

//            AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
//            builder1.setTitle("Error");
//            builder1.setMessage("No Internet Connection found. Please connect to the internet and re-open the app.");
//            builder1.setCancelable(false);
//            builder1.setPositiveButton(
//                    "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            ActivityCompat.finishAffinity(getActivity());
//                        }
//                    });
//            AlertDialog alert11 = builder1.create();
//            alert11.show();

//            Toast.makeText(getContext(),"No network availible",Toast.LENGTH_SHORT).show();
        }

        // Inflate the layout for this fragment
        return binding.getRoot();
    }

    }
