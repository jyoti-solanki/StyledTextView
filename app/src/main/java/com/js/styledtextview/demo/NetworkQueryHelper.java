package com.js.styledtextview.demo;

import android.content.Context;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class NetworkQueryHelper {
    private static NetworkQueryHelper networkQueryHelper = null;
    private int categoriesCount = 0;
    private Context context = null;
    private int dbVersion = 0;
    private boolean isVersionRecieved = false;

    public interface ResponseListener {
        void onResponse(JSONObject jSONObject);
    }

    public static NetworkQueryHelper getInstance() {
        if (networkQueryHelper == null) {
            networkQueryHelper = new NetworkQueryHelper();
        }
        return networkQueryHelper;
    }

    public void getServerDbVersion(final ResponseListener listener) {
        if (this.isVersionRecieved) {
            try {
                JSONObject verObj = new JSONObject();
                verObj.put("dbVersion", this.dbVersion);
                verObj.put("categoryCount", this.categoriesCount);
                listener.onResponse(verObj);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            new HttpRequestTask(new HttpRequestTask.RequestListener() {
                /* class com.js.styledtextview.demo.NetworkQueryHelper.AnonymousClass1 */

                @Override // com.js.styledtextview.demo.HttpRequestTask.RequestListener
                public void onResponse(String response) {
                    try {
                        JSONObject dataObj = (JSONObject) new JSONObject(response).getJSONArray("data").get(0);
                        NetworkQueryHelper.this.dbVersion = dataObj.getInt("Version");
                        NetworkQueryHelper.this.categoriesCount = dataObj.getInt("Category_Count");
                        JSONObject verObj = new JSONObject();
                        verObj.put("dbVersion", NetworkQueryHelper.this.dbVersion);
                        verObj.put("categoryCount", NetworkQueryHelper.this.categoriesCount);
                        listener.onResponse(verObj);
                        NetworkQueryHelper.this.isVersionRecieved = true;
                    } catch (JSONException e) {
                        e.printStackTrace();
                        listener.onResponse(null);
                    }
                }

                @Override // com.js.styledtextview.demo.HttpRequestTask.RequestListener
                public void onError() {
                    listener.onResponse(null);
                }
            }).execute(getDataVersionURL());
        }
    }

    public void getCategoriesData(int categoriesCount2, final ResponseListener listener) {
        new HttpRequestTask(new HttpRequestTask.RequestListener() {
            /* class com.js.styledtextview.demo.NetworkQueryHelper.AnonymousClass2 */

            @Override // com.js.styledtextview.demo.HttpRequestTask.RequestListener
            public void onResponse(String response) {
                try {
                    listener.onResponse(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onResponse(null);
                }
            }

            @Override // com.js.styledtextview.demo.HttpRequestTask.RequestListener
            public void onError() {
                listener.onResponse(null);
            }
        }).execute(getFetchCategoryURL(categoriesCount2));
    }

    public void getStickersData(String categoryName, int sequenceNum, int pageSize, final ResponseListener listener) {
        new HttpRequestTask(new HttpRequestTask.RequestListener() {
            /* class com.js.styledtextview.demo.NetworkQueryHelper.AnonymousClass3 */

            @Override // com.js.styledtextview.demo.HttpRequestTask.RequestListener
            public void onResponse(String response) {
                try {
                    listener.onResponse(new JSONObject(response));
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onResponse(null);
                }
            }

            @Override // com.js.styledtextview.demo.HttpRequestTask.RequestListener
            public void onError() {
                listener.onResponse(null);
            }
        }).execute(getFetchStickerDataURL(categoryName, sequenceNum, pageSize));
    }

    private String getFetchCategoryURL(int pageSize) {
        return "https://apasdi.backfsdfsdfsdfendless.com/v1/data/Category_Master?pageSize=abc" + String.valueOf(pageSize) + "&sortBy=Sequence%20asc&where=Package_Name%3D%27" + LibContants.getPackageId() + "%27";
    }

    private String getDataVersionURL() {
        return "https://adasdapi.backwerwerweerwsdadsdaerwerwerndless.com/v1/data/Data_Version?where=Padasckage_Name%3D%27" + LibContants.getPackageId() + "%27";
    }

    private String getFetchStickerDataURL(String categoryName, int sequenceNum, int pageSize) {
        String str = "https://apasdi.backeasdasdasdasndless.com/v1/data/Stickers?pageSize=abc" + String.valueOf(pageSize) + "&sortBy=Sequence%20asc&where=Main_Category%3D%27" + categoryName + "%27%20and%20Sequence%3E" + String.valueOf(sequenceNum);
        Log.i("testing", " Server URL " + str);
        return str;
    }
}
