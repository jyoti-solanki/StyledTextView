package com.js.styledtextview.demo.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;

import com.js.styledtextview.R;
import com.js.styledtextview.demo.DatabaseHandler;
import com.js.styledtextview.demo.HoriontalListAdapter;
import com.js.styledtextview.demo.LibContants;
import com.js.styledtextview.demo.StickerInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.HashMap;

public class ContainerHost extends RelativeLayout {
    private String actAdapter = "";
    private HashMap<String, HoriontalListAdapter> adapterHashMap = new HashMap<>();
    private Context ctx;
    private HorizontalListView horizontalListView;
    private OnItemClickListener itemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(String str);
    }

    public void setItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public ContainerHost(Context context) {
        super(context);
        initContainerHost(context);
    }

    public ContainerHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        initContainerHost(context);
    }

    public ContainerHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initContainerHost(context);
    }

    public void initContainerHost(Context context) {
        this.ctx = context;
        this.horizontalListView = new HorizontalListView(context);
        this.horizontalListView.setLayoutParams(new LayoutParams(-1, -1));
        addView(this.horizontalListView);
        this.horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /* class com.js.styledtextview.demo.view.ContainerHost.AnonymousClass1 */

            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (ContainerHost.this.itemClickListener != null) {
                    Uri uri = Uri.parse(((StickerInfo) ContainerHost.this.getActiveAdapter().getItem(position)).getIMAGE_PATH());
                    if ("android.resource".equals(uri.getScheme())) {
                        ContainerHost.this.itemClickListener.onItemClick(((StickerInfo) ContainerHost.this.getActiveAdapter().getItem(position)).getIMAGE_PATH());
                    } else if (new File(uri.getPath()).exists()) {
                        ContainerHost.this.itemClickListener.onItemClick(((StickerInfo) ContainerHost.this.getActiveAdapter().getItem(position)).getIMAGE_PATH());
                    } else {
                        Log.i("testing", "Starting AsyncTask");
                        new SaveAndLoadAsync(position, (StickerInfo) ContainerHost.this.getActiveAdapter().getItem(position)).execute(new Object[0]);
                    }
                }
            }
        });
    }

    public void addAdapter(String name, HoriontalListAdapter horiontalListAdapter) {
        this.adapterHashMap.put(name, horiontalListAdapter);
    }

    public void removeAdapter(String name) {
        if (this.adapterHashMap.containsKey(name)) {
            this.adapterHashMap.remove(name);
        }
        if (!this.adapterHashMap.containsKey(this.actAdapter)) {
            this.actAdapter = "";
        }
    }

    @SuppressLint("WrongConstant")
    public void changeAdapter(String name) {
        if (this.adapterHashMap.containsKey(name)) {
            this.horizontalListView.setVisibility(0);
            this.horizontalListView.setAdapter((ListAdapter) this.adapterHashMap.get(name));
        } else {
            this.horizontalListView.setVisibility(8);
        }
        if (this.adapterHashMap.containsKey(name)) {
            this.actAdapter = name;
        } else {
            this.actAdapter = "";
        }
    }

    public HoriontalListAdapter getActiveAdapter() {
        if (this.adapterHashMap.containsKey(this.actAdapter)) {
            return this.adapterHashMap.get(this.actAdapter);
        }
        return null;
    }

    public HoriontalListAdapter getAdapter(String tabName) {
        if (this.adapterHashMap.containsKey(tabName)) {
            return this.adapterHashMap.get(tabName);
        }
        return null;
    }

    private class SaveAndLoadAsync extends AsyncTask<Object, Void, Boolean> {
        private ProgressDialog pdia;
        private int position;
        private StickerInfo stickerInfo;

        public SaveAndLoadAsync(int pos, StickerInfo info) {
            this.position = pos;
            this.stickerInfo = info;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
            this.pdia = new ProgressDialog(ContainerHost.this.ctx);
            this.pdia.setMessage(ContainerHost.this.ctx.getResources().getString(R.string.downloadin_file));
            this.pdia.setCancelable(false);
            this.pdia.show();
        }

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Object... params) {
            try {
                String imagePath = saveBitmapObject(BitmapFactory.decodeStream(new URL(this.stickerInfo.getIMAGE_SERVER_PATH()).openStream()), this.stickerInfo.getSTICKER_NAME());
                DatabaseHandler dbHanlder = DatabaseHandler.getDbHandler(ContainerHost.this.ctx);
                dbHanlder.updateStickerImagePath(this.stickerInfo.getSTICKER_ID(), imagePath, true);
                dbHanlder.close();
                this.stickerInfo.setIMAGE_PATH(imagePath);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                return false;
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean isDownloaded) {
            super.onPostExecute((Boolean) isDownloaded);
            this.pdia.dismiss();
            if (isDownloaded.booleanValue()) {
                Log.i("testing", "Sticker Saved to : " + this.stickerInfo.getIMAGE_PATH());
                ((StickerInfo) ContainerHost.this.getActiveAdapter().getItem(this.position)).setIMAGE_PATH(this.stickerInfo.getIMAGE_PATH());
                ContainerHost.this.itemClickListener.onItemClick(this.stickerInfo.getIMAGE_PATH());
                return;
            }
            new AlertDialog.Builder(ContainerHost.this.ctx).setTitle(ContainerHost.this.ctx.getResources().getString(R.string.no_internet)).setMessage(ContainerHost.this.ctx.getResources().getString(R.string.file_not_downloaded)).setCancelable(false).setPositiveButton(ContainerHost.this.ctx.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                /* class com.js.styledtextview.demo.view.ContainerHost.SaveAndLoadAsync.AnonymousClass1 */

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        }

        private String saveBitmapObject(Bitmap bitmap, String fname) {
            File myDir = LibContants.getSaveFileLocation();
            myDir.mkdirs();
            File file = new File(myDir, fname + ".png");
            if (file.exists()) {
                file.delete();
            }
            try {
                FileOutputStream ostream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                ostream.close();
                return file.getPath();
            } catch (Exception e) {
                e.printStackTrace();
                Log.i("testing", "Exception" + e.getMessage());
                return null;
            }
        }
    }
}
