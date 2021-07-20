package com.js.styledtextview.demo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.js.styledtextview.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StickerGridActivity extends AppCompatActivity {
    private static final String MyPREFERENCES = "MyPrefs";
    protected static boolean isRunning = false;
    protected static Context mContext = null;
    private StickerPagerAdapter adapter = null;
    private int categoriesCount = 0;
    private int curVer;
    private int dbVer;
    private TextView headerText;
    private ViewPager pager;
    private ProgressDialog ringProgressDialog;
    SharedPreferences sharedpreferences;
    private PagerSlidingTabStrip tabHost;
    private Typeface ttf;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_list);
        mContext = this;
        isRunning = true;
        this.sharedpreferences = getSharedPreferences(MyPREFERENCES, 0);
        initUI();
        this.ringProgressDialog = ProgressDialog.show(this, "", getResources().getString(R.string.please_wait) + "...", true);
        this.ringProgressDialog.setCancelable(false);
        if (isNetworkAvailable()) {
            checkDataVersionChanges();
        } else {
            ArrayList<CategoryRowInfo> categoriesList = DatabaseHandler.getDbHandler(this).getCategoriesList();
            if (categoriesList.size() == 0) {
                showNetworkErrorDialog();
            } else {
                intiCategoryList(categoriesList);
            }
        }
        this.ttf = Typeface.createFromAsset(getAssets(), "Roboto-Medium.ttf");
        this.headerText.setTypeface(this.ttf);
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
    }

    private void initUI() {
        this.headerText = (TextView) findViewById(R.id.headertext);
        this.tabHost = (PagerSlidingTabStrip) findViewById(R.id.tabHost);
        this.pager = (ViewPager) findViewById(R.id.pager);
        this.tabHost.setTextColor(-1);
        this.tabHost.setDividerColor(Color.parseColor("#620b80"));
//        this.tabHost.setDividerWidth(dpToPx(this, 1));
        this.tabHost.setIndicatorColor(Color.parseColor("#620b80"));
        this.tabHost.setIndicatorHeight(dpToPx(this, 5));
        this.tabHost.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /* class com.js.styledtextview.demo.StickerGridActivity.AnonymousClass1 */

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int position) {
                if (StickerGridActivity.this.adapter != null) {
                    StickerGridActivity.this.headerText.setText(StickerGridActivity.this.adapter.getPageTitle(position));
                }
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int state) {
            }
        });
        findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
            /* class com.js.styledtextview.demo.StickerGridActivity.AnonymousClass2 */

            public void onClick(View v) {
                StickerGridActivity.this.finish();
                StickerGridActivity.this.overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out);
            }
        });
    }

    private void checkDataVersionChanges() {
        this.curVer = this.sharedpreferences.getInt("DataVersion", 0);
        NetworkQueryHelper.getInstance().getServerDbVersion(new NetworkQueryHelper.ResponseListener() {
            /* class com.js.styledtextview.demo.StickerGridActivity.AnonymousClass3 */

            @Override // com.js.styledtextview.demo.NetworkQueryHelper.ResponseListener
            public void onResponse(JSONObject response) {
                if (response != null) {
                    try {
                        StickerGridActivity.this.dbVer = response.getInt("dbVersion");
                        StickerGridActivity.this.categoriesCount = response.getInt("categoryCount");
                        if (StickerGridActivity.this.curVer != StickerGridActivity.this.dbVer) {
                            StickerGridActivity.this.fetchAndInsertCategory();
                            return;
                        }
                        ArrayList<CategoryRowInfo> categoriesList = DatabaseHandler.getDbHandler(StickerGridActivity.this).getCategoriesList();
                        StickerGridActivity.this.intiCategoryList(categoriesList);
                        Log.i("testing", categoriesList.size() + "  " + categoriesList.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        StickerGridActivity.this.showNetworkErrorDialog();
                    }
                } else {
                    StickerGridActivity.this.showNetworkErrorDialog();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void fetchAndInsertCategory() {
        NetworkQueryHelper.getInstance().getCategoriesData(this.categoriesCount, new NetworkQueryHelper.ResponseListener() {
            /* class com.js.styledtextview.demo.StickerGridActivity.AnonymousClass4 */

            @Override // com.js.styledtextview.demo.NetworkQueryHelper.ResponseListener
            public void onResponse(JSONObject responseObj) {
                if (responseObj != null) {
                    try {
                        JSONArray dataArr = responseObj.getJSONArray("data");
                        Log.i("testing", dataArr + " Server Categories Data " + dataArr.toString());
                        DatabaseHandler dh = DatabaseHandler.getDbHandler(StickerGridActivity.this);
                        dh.disableAllRow();
                        ArrayList<CategoryRowInfo> categoriesList = dh.getCategoriesList();
                        Log.i("testing", categoriesList + " Local Categories Data " + categoriesList.toString());
                        Iterator<CategoryRowInfo> it2 = StickerGridActivity.this.getNewCategoryList(dataArr, categoriesList, dh).iterator();
                        while (it2.hasNext()) {
                            dh.insertCategoryMasterRow(it2.next());
                        }
                        SharedPreferences.Editor edit = StickerGridActivity.this.sharedpreferences.edit();
                        edit.putInt("DataVersion", StickerGridActivity.this.dbVer);
                        edit.commit();
                        ArrayList<CategoryRowInfo> categoriesList2 = dh.getCategoriesList();
                        StickerGridActivity.this.intiCategoryList(categoriesList2);
                        Log.i("testing", categoriesList2.size() + " and " + categoriesList2.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                        StickerGridActivity.this.showNetworkErrorDialog();
                    }
                } else {
                    StickerGridActivity.this.showNetworkErrorDialog();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void intiCategoryList(ArrayList<CategoryRowInfo> categoriesList) {
        Collections.sort(categoriesList, new Comparator<CategoryRowInfo>() {
            /* class com.js.styledtextview.demo.StickerGridActivity.AnonymousClass5 */

            public int compare(CategoryRowInfo o1, CategoryRowInfo o2) {
                if (o1.getSEQUENCE() == o2.getSEQUENCE()) {
                    return 0;
                }
                if (o1.getSEQUENCE() > o2.getSEQUENCE()) {
                    return 1;
                }
                return -1;
            }
        });
        this.adapter = new StickerPagerAdapter(StickerGridActivity.this, getSupportFragmentManager(), categoriesList);
        this.pager.setAdapter(this.adapter);
        this.tabHost.setViewPager(this.pager);
        if (categoriesList.size() > 0) {
            this.headerText.setText(categoriesList.get(0).getCATEGORY_NAME());
            new SelectionAsync(categoriesList).execute(new Object[0]);
        }
        if (this.ringProgressDialog.isShowing()) {
            this.ringProgressDialog.dismiss();
        }
    }

    /* access modifiers changed from: private */
    public class SelectionAsync extends AsyncTask<Object, Object, Integer> {
        ArrayList<CategoryRowInfo> categoriesList;
        String tabName = "";

        public SelectionAsync(ArrayList<CategoryRowInfo> categoriesLis) {
            this.categoriesList = categoriesLis;
        }

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Integer doInBackground(Object... params) {
            try {
                this.tabName = StickerGridActivity.this.getIntent().getStringExtra("tabName");
                Log.i("testing", "" + this.tabName);
                if (this.tabName.trim().length() > 0) {
                    for (int i = 0; i < this.categoriesList.size(); i++) {
                        if (this.categoriesList.get(i).getCATEGORY_NAME().equals(this.tabName)) {
                            return Integer.valueOf(i);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Integer integer) {
            super.onPostExecute((Integer) integer);
            if (integer.intValue() >= 0) {
                StickerGridActivity.this.headerText.setText(this.categoriesList.get(0).getCATEGORY_NAME());
                StickerGridActivity.this.pager.setCurrentItem(integer.intValue());
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private ArrayList<CategoryRowInfo> getNewCategoryList(JSONArray jsonArr, ArrayList<CategoryRowInfo> categoriesList, DatabaseHandler dh) {
        ArrayList<CategoryRowInfo> newCategoryList = new ArrayList<>();
        ArrayList<String> oldCategoryList = new ArrayList<>();
        Iterator<CategoryRowInfo> it2 = categoriesList.iterator();
        while (it2.hasNext()) {
            oldCategoryList.add(it2.next().getCATEGORY_NAME());
        }
        for (int i = 0; i < jsonArr.length(); i++) {
            try {
                JSONObject obj = (JSONObject) jsonArr.get(i);
                String catName = obj.getString("Category_Name");
                if (!oldCategoryList.contains(catName)) {
                    newCategoryList.add(new CategoryRowInfo(catName, obj.getInt("Sequence"), obj.getInt("Total_Items")));
                } else {
                    dh.updateCategoryRow(catName, obj.getInt("Sequence"), obj.getInt("Total_Items"));
                    oldCategoryList.remove(catName);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Iterator<String> it3 = oldCategoryList.iterator();
        while (it3.hasNext()) {
            dh.deleteCategoryMasterRow(it3.next());
        }
        return newCategoryList;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void showNetworkErrorDialog() {
        int style;
        if (this.ringProgressDialog.isShowing()) {
            this.ringProgressDialog.dismiss();
        }
        if (isRunning) {
            if (Build.VERSION.SDK_INT >= 14) {
                style = 16974126;
            } else {
                style = 16973835;
            }
            new AlertDialog.Builder(mContext, style).setTitle(mContext.getResources().getString(R.string.error)).setMessage(mContext.getResources().getString(R.string.something_wrong)).setCancelable(false).setPositiveButton(mContext.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                /* class com.js.styledtextview.demo.StickerGridActivity.AnonymousClass6 */

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    finish();
                }
            }).create().show();
        }
    }

    public boolean isNetworkAvailable() {
        @SuppressLint("WrongConstant") NetworkInfo netInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        isRunning = false;
        super.onDestroy();
    }

    private int dpToPx(Context c, int dp) {
        c.getResources();
        return (int) (((float) dp) * Resources.getSystem().getDisplayMetrics().density);
    }
}
