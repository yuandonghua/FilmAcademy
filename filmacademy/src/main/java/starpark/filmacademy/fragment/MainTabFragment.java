package starpark.filmacademy.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import starpark.filmacademy.R;
import starpark.filmacademy.page.DownloadPage;
import starpark.filmacademy.page.FilmListPage;
import starpark.filmacademy.page.FilmCategoryPage;
import starpark.filmacademy.page.MePage;

/**
 *@description:主页tab的fragment
 *@author:袁东华
 *created at 2016/8/22 0022 下午 2:30
 */
public class MainTabFragment extends Fragment {
private int position;

    private static final String ARG_SECTION_NUMBER = "section_number";


    public static MainTabFragment newInstance(int sectionNumber) {
        MainTabFragment fragment = new MainTabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MainTabFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        position = getArguments().getInt(ARG_SECTION_NUMBER);
        View rootView = null;
        switch (position) {
            case 1:
                rootView = FilmListPage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_filmlist, container, false);
                     FilmListPage.getInstance().start(getActivity(), rootView);
                }

                break;
            case 2:
                rootView = FilmCategoryPage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_filmcategory, container, false);
                    FilmCategoryPage.getInstance().start(getActivity(), rootView);
                }
                break;
            case 3:
                rootView = DownloadPage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_filmcategory, container, false);
                    DownloadPage.getInstance().start(getActivity(), rootView);
                }
                break;
            case 4:
                rootView = MePage.getInstance().getView();
                if (rootView == null) {
                    rootView = inflater.inflate(R.layout.fragment_filmcategory, container, false);
                    MePage.getInstance().start(getActivity(), rootView);
                }
                break;
        }


        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();

    }


}
