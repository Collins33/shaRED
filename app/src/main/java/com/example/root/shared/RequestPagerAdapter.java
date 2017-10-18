package com.example.root.shared;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by root on 10/18/17.
 */

public class RequestPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Request> mRequest;

    public RequestPagerAdapter(FragmentManager fm,ArrayList<Request>request){
        super(fm);
        mRequest=request;
    }

    @Override
    public Fragment getItem(int position) {
        return DonationDetailFragment.newInstance(mRequest.get(position));
    }

    @Override
    public int getCount() {
       return mRequest.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRequest.get(position).getHospital();
    }
}
