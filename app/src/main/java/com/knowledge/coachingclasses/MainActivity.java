package com.knowledge.coachingclasses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.knowledge.coachingclasses.modelClasses.user;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    //navigation drawer tools
    private AppBarConfiguration mAppBarConfiguration;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private View nav_header_view;

    private NavController navController ;

    //Bottom Navigation tools
    BottomNavigationView bottomNavigationView;

    //Modifying Header
    private ImageView header_image;
    private TextView header_name;
    private TextView header_class;

    //current user
    private user current_user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialization & findViewbyid
        initializaion();

//

        //tool BAR
        setSupportActionBar(toolbar);
//        tabLayoutSetting();
        navigationDrawerSetting();

        //Bottom Navigation setting
        BottomNavigationSetting();

    }

    private void BottomNavigationSetting() {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }


    private void navigationDrawerSetting() {
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,R.id.noticeBoardFragment ,R.id.nav_teachersDetail)
                .setDrawerLayout(drawer)
                .build();

        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //getting navigation header view

    }

    private user getUserDetailsFromSharedPreference(){
        SharedPreferences sp = getSharedPreferences(getString(R.string.user_Details_sp), MODE_PRIVATE);

        // Basic details of user
         String userId = sp.getString("userId","");
         String displayName = sp.getString("displayName","");
         String imageUrl = sp.getString("imageUrl","");
         String phoneNumber = sp.getString("phoneNumber","");
         String classes = sp.getString("classes","");
        int points = sp.getInt("points", 0);
        ArrayList<String> classCodes = new ArrayList<>();

        // Cources code details
        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sp.getString("classCodes", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<String>>() {}.getType();

        // in below line we are getting data from gson
        // and saving it to our array list
        classCodes = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (classCodes == null) {
            // if the array list is empty
            // creating a new array list.
            classCodes = new ArrayList<>();
        }

        user currentuser  = new user(userId,displayName,imageUrl,phoneNumber,classes,classCodes,points);
        return currentuser;
    }

    private void initializaion() {

        //getting current user from shared preference
        current_user = getUserDetailsFromSharedPreference();

        //tool BAR
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Drawable layout binding layout
        drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        //Bottom Navigation tools
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        //getting navigation header view
//        nav_header_view = navigationView.inflateHeaderView(R.layout.nav_header_main);
        nav_header_view = navigationView.getHeaderView(0);

        //setting header tools

        header_image = (ImageView) nav_header_view.findViewById(R.id.header_image);
//using glide for getting image from url
        Glide.with(header_image.getContext()).load(current_user.getImageUrl()).circleCrop().into(header_image);
//setting name
        header_name = (TextView) nav_header_view.findViewById(R.id.header_name);
        header_name.setText(current_user.getDisplayName());

//setting class
        header_class = (TextView) nav_header_view.findViewById(R.id.header_class);
        header_class.setText(current_user.getClasses());

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        //helps in back stag navigation
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {

        //help in close the drawer when back pressed
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();

        }
    }
}
