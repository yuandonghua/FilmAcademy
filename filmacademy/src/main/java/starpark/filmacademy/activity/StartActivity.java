package starpark.filmacademy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;


import starpark.filmacademy.R;

import starpark.filmacademy.utils.ManageUserDataUtil;
import starpark.filmacademy.utils.SharedPreferencesUtil;

/**
 * @description:app开始界面
 * @author:袁东华 created at 2016/8/29 0029 上午 10:51
 */
@ContentView(R.layout.activity_start)
public class StartActivity extends BaseActivity {
    @ViewInject(R.id.container)
    private ViewPager container;
    public int count = 3;


    @Override
    public void initData() {
        if (ManageUserDataUtil.getInstance().isFirst(activity)) {
            //第一次进入app
            count = 3;


        } else {
            count = 1;
            handler.postDelayed(runnable, 2000);

        }
        StartPagerAdapter startPagerAdapter = new StartPagerAdapter(getSupportFragmentManager(), activity);
        container.setAdapter(startPagerAdapter);
    }

    public class StartPagerAdapter extends FragmentPagerAdapter {
        private Activity activity;

        public StartPagerAdapter(FragmentManager fm, Activity activity) {
            super(fm);
            this.activity = activity;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return StartPageFragment.newInstance(position + 1, count);
        }

        @Override
        public int getCount() {
            return count;
        }

    }

    public static class StartPageFragment extends Fragment {
        private int position;
        private static int count;
        private static final String ARG_SECTION_NUMBER = "section_number";


        public static StartPageFragment newInstance(int sectionNumber, int c) {
            count = c;
            StartPageFragment fragment = new StartPageFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public StartPageFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            position = getArguments().getInt(ARG_SECTION_NUMBER);
            View rootView = null;
            switch (position) {
                case 1:
                    if (count == 1) {
                        rootView = inflater.inflate(R.layout.fragment_start_page, container, false);
                        rootView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(), MainActivity.class));
                                getActivity().finish();
                            }
                        });

                    } else {
                        rootView = inflater.inflate(R.layout.fragment_first_page, container, false);
                    }
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_second_page, container, false);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_third_page, container, false);
                    rootView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SharedPreferencesUtil.getInstance().setIsFirst(getActivity(), false);
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                        }
                    });
                    break;
            }


            return rootView;
        }


    }
public Handler handler=new Handler();
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(activity, MainActivity.class));
            finish();
        }
    };

    @Override
    public void finish() {
        handler.removeCallbacks(runnable);
        super.finish();

    }
}