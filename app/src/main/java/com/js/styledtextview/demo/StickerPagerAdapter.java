package com.js.styledtextview.demo;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class StickerPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private ArrayList<CategoryRowInfo> dataList = null;
    private HashMap<String, StickerGridFragment> hashMap = new HashMap<>();

    public StickerPagerAdapter(Context context2, FragmentManager fm, ArrayList<CategoryRowInfo> list) {
        super(fm);
        this.context = context2;
        this.dataList = list;
    }

    @Override // android.support.v13.app.FragmentPagerAdapter
    public Fragment getItem(int position) {
        String categoryName = this.dataList.get(position).getCATEGORY_NAME();
        StickerGridFragment stickerGridFragment = new StickerGridFragment();
        Bundle bundle = new Bundle();
        bundle.putString("categoryName", categoryName);
        bundle.putInt("totalItems", this.dataList.get(position).getTOTAL_ITEMS());
        stickerGridFragment.setArguments(bundle);
        this.hashMap.put(categoryName, stickerGridFragment);
        Log.i("testing", "Not Contain : " + categoryName);
        return stickerGridFragment;
    }

    @Override // android.support.v4.view.PagerAdapter
    public CharSequence getPageTitle(int position) {
        return this.dataList.get(position).getCATEGORY_NAME();
    }

    @Override // android.support.v4.view.PagerAdapter
    public int getCount() {
        return this.dataList.size();
    }
}
