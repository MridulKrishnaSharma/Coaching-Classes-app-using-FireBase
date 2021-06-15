package com.knowledge.coachingclasses.classRoomFragment.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.knowledge.coachingclasses.classRoomFragment.classChatFragment;
import com.knowledge.coachingclasses.classRoomFragment.studentsFragmet;

import org.jetbrains.annotations.NotNull;

public class classFragmentAdapter extends FragmentStateAdapter {

    public classFragmentAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){

//            case 0:
//                return new classChatFragment();

            case 1:
                return new studentsFragmet();

        }
        return new classChatFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
