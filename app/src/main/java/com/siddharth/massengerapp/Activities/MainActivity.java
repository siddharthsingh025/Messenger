package com.siddharth.massengerapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.siddharth.massengerapp.Adapters.FragmentsAdapter;
import com.siddharth.massengerapp.R;
import com.siddharth.massengerapp.databinding.ActivityLogInBinding;
import com.siddharth.massengerapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.viewPager.setAdapter(new FragmentsAdapter(getSupportFragmentManager()));
        binding.tabLayout.setupWithViewPager(binding.viewPager);


    }


    ///create menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater(); // first we defined variable for MenuInflater with activity as context
        inflater.inflate(R.menu.menu,menu) ; // inflate our variable with our menu layout

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.settings:
                Toast.makeText(MainActivity.this , " click on Settings", Toast.LENGTH_SHORT).show();
                 break;
            case R.id.groupChat:
                Toast.makeText(MainActivity.this , "click on Group Chat", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:

                auth.signOut();
                Intent intent = new Intent(MainActivity.this, LogInActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}