package starpark.filmacademy.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import starpark.filmacademy.fragment.MainTabFragment;
/**
 *@description:主页ViewPager的适配器
 *@author:袁东华
 *created at 2016/8/22 0022 下午 2:29
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private Activity activity;
    public MainPagerAdapter(FragmentManager fm, Activity activity) {
        super(fm);
        this.activity=activity;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return MainTabFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "";
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "";
        }
        return null;
    }
}
