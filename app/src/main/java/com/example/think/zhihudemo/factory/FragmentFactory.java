package com.example.think.zhihudemo.factory;


import android.support.v4.app.Fragment;

import com.example.think.zhihudemo.fragment.Tab0Fragment;
import com.example.think.zhihudemo.fragment.Tab1Fragment;

/**
 *
 */
public class FragmentFactory {
    private static Fragment sConversationFragment;
    private static Fragment sTabTopFragment;

    private static Fragment getTabHomeFragment() {
        if (sConversationFragment == null) {
            sConversationFragment = new Tab0Fragment();
        }
        return sConversationFragment;
    }

    private static Fragment getTabTopFragment() {
        if (sTabTopFragment == null) {
            sTabTopFragment = new Tab1Fragment();
        }
        return sTabTopFragment;
    }


    public static Fragment getFragment(int index) {
        switch (index) {
            case 0:
                return getTabHomeFragment();
            case 1:
                return getTabTopFragment();
        }
        return null;
    }


}
