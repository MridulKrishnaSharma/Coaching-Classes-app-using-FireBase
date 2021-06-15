package com.knowledge.coachingclasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabLayout;
import com.knowledge.coachingclasses.classRoomFragment.adapter.classFragmentAdapter;

import org.jetbrains.annotations.NotNull;

public class ClassRoom extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private classFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_room);

        //initialization
        tabLayout = (TabLayout) findViewById(R.id.classroom_tabL);
        viewPager2 = (ViewPager2) findViewById(R.id.classroom_ViewPager);

        //settimg fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        // initialization of fragment adapter class
        adapter = new classFragmentAdapter(fragmentManager,getLifecycle());
        // setting addapter to viewpager2
        viewPager2.setAdapter(adapter);

        //using tab selected listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //now we are calling function if we swipe respective tab wil also get selected
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//         PageTransformer to making slide fun
        this.viewPager2.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull @NotNull View page, float position) {
                page.setCameraDistance(20000f);

                //if position of page is less then -1
                if(position <-1){
                    //page will be invisible
                    page.setAlpha(0f);
                } else if(position <=0 ){ //if position of page will be less then or equal to zero
//                  page will bw completely visible
                    page.setAlpha(1f);
                    // set pivotx = page width
                    page.setPivotX(page.getWidth());
                    //set rotationY =  -90*Maths.abstract(position)
                    page.setRotationY(-90*Math.abs(position));
                } else if(position <=1 ){ //if position of page will be less then or equal to 1
//                  page will bw completely visible
                    page.setAlpha(1f);
                    // set pivotx = page width
                    page.setPivotX(0f);
                    //set rotationY =  -90*Maths.abstract(position)
                    page.setRotationY(90*Math.abs(position));
                } else {
                    // page will be invisible
                    page.setAlpha(0f);
                }
            }
        });
    }
}