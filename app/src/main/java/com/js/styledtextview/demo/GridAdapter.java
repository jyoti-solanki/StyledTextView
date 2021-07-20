package com.js.styledtextview.demo;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.js.styledtextview.R;
import com.js.styledtextview.demo.view.SquareFrameLayoutGrid;
import com.js.styledtextview.demo.view.SquareImageViewGrid;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.List;

public class GridAdapter extends ArrayAdapter<StickerInfo> {
    public static int num = 0;
    Context context;

    public GridAdapter(Context context2, List<StickerInfo> images) {
        super(context2, 0, images);
        this.context = context2;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        StickerInfo stickerInfo = (StickerInfo) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.gridview_item, (ViewGroup) null);
            holder = new ViewHolder(convertView);
            holder.setStickerInfo(position);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.setStickerInfo(position);
        }
        holder.uri = Uri.parse(((StickerInfo) getItem(position)).getTHUMB_PATH());
        holder.refreshImage();
        return convertView;
    }

    class ViewHolder {
        ImageView download;

        SquareImageViewGrid mThumbnail;
        int position;
        SquareFrameLayoutGrid root;
        private StickerInfo stickerInfo;
        Uri uri;

        public ViewHolder(View view) {
            this.root = (SquareFrameLayoutGrid) view.findViewById(R.id.root);
            this.mThumbnail = (SquareImageViewGrid) view.findViewById(R.id.thumbnail_image);
            this.download = (ImageView) view.findViewById(R.id.download);
        }

        public void refreshImage() {
                Glide.with(GridAdapter.this.context).load(this.stickerInfo.getTHUMB_SERVER_PATH()).thumbnail(0.5f).centerCrop().placeholder(R.drawable.sticker_loading).error(R.drawable.sticker_no_image).into(this.mThumbnail);
            if (this.stickerInfo.isIS_DOWNLOADING()) {
                Glide.with(GridAdapter.this.context).load(Integer.valueOf(R.drawable.sticker_downloading)).asGif().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(this.download);
                this.download.setOnClickListener(null);
            } else if (this.stickerInfo.IS_DOWNLOADED().equals("false")) {
                Glide.with(GridAdapter.this.context).load(Integer.valueOf(R.drawable.sticker_downloading)).asBitmap().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(this.download);
                this.download.setOnClickListener(new View.OnClickListener() {
                    /* class com.js.styledtextview.demo.GridAdapter.ViewHolder.AnonymousClass1 */

                    public void onClick(View v) {
                        Log.i("testing", "convertView one " + ViewHolder.this.uri.getLastPathSegment());
                        ViewHolder.this.download.setBackgroundResource(0);
                        Glide.with(GridAdapter.this.context).load(Integer.valueOf(R.drawable.sticker_downloading)).asGif().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(ViewHolder.this.download);
                        ViewHolder.this.stickerInfo.setIS_DOWNLOADING(true);
                        ViewHolder.this.download.setOnClickListener(null);
                        new SaveAndLoadAsync(ViewHolder.this.position).execute(new Object[0]);
                    }
                });
            } else {
                Glide.with(GridAdapter.this.context).load(Integer.valueOf(R.drawable.sticker_delete)).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(this.download);
                this.download.setOnClickListener(new View.OnClickListener() {
                    /* class com.js.styledtextview.demo.GridAdapter.ViewHolder.AnonymousClass2 */

                    @SuppressLint("WrongConstant")
                    public void onClick(View v) {
                        Log.i("testing", "Downloaded " + ViewHolder.this.stickerInfo.getIMAGE_PATH());
                        Toast.makeText(GridAdapter.this.context, GridAdapter.this.context.getResources().getString(R.string.deleted), 0).show();
                        DatabaseHandler dbHanlder = DatabaseHandler.getDbHandler(GridAdapter.this.context);
                        dbHanlder.updateStickerImagePath(ViewHolder.this.stickerInfo.getSTICKER_ID(), "", false);
                        dbHanlder.close();
                        File file = new File(ViewHolder.this.stickerInfo.getIMAGE_PATH());
                        if (!file.exists()) {
                            ((StickerInfo) GridAdapter.this.getItem(ViewHolder.this.position)).setIS_DOWNLOADED("false");
                            GridAdapter.this.notifyDataSetChanged();
                        } else if (file.delete()) {
                            ((StickerInfo) GridAdapter.this.getItem(ViewHolder.this.position)).setIS_DOWNLOADED("false");
                            GridAdapter.this.notifyDataSetChanged();
                        }
                    }
                });
            }
        }

        public void setStickerInfo(int pos) {
            this.position = pos;
            this.stickerInfo = (StickerInfo) GridAdapter.this.getItem(pos);
        }
    }

    private class SaveAndLoadAsync extends AsyncTask<Object, Void, Boolean> {
        private int position;
        private StickerInfo stickerInfo;

        public SaveAndLoadAsync(int pos) {
            this.position = pos;
            this.stickerInfo = (StickerInfo) GridAdapter.this.getItem(pos);
        }

        /* access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Object... params) {
            try {
                String imagePath = saveBitmapObject(BitmapFactory.decodeStream(new URL(this.stickerInfo.getIMAGE_SERVER_PATH()).openStream()), this.stickerInfo.getSTICKER_NAME());
                DatabaseHandler dbHanlder = DatabaseHandler.getDbHandler(GridAdapter.this.context);
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
            int style;
            super.onPostExecute((Boolean) isDownloaded);
            if (isDownloaded.booleanValue()) {
                Log.i("testing", "Sticker Saved to : " + this.stickerInfo.getIMAGE_PATH());
                ((StickerInfo) GridAdapter.this.getItem(this.position)).setIS_DOWNLOADED("true");
            } else if (StickerGridActivity.isRunning) {
                if (Build.VERSION.SDK_INT >= 14) {
                    style = 16974126;
                } else {
                    style = 16973835;
                }
                new AlertDialog.Builder(StickerGridActivity.mContext, style).setTitle(StickerGridActivity.mContext.getResources().getString(R.string.no_internet)).setMessage(StickerGridActivity.mContext.getResources().getString(R.string.file_not_downloaded)).setCancelable(false).setPositiveButton(StickerGridActivity.mContext.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                    /* class com.js.styledtextview.demo.GridAdapter.SaveAndLoadAsync.AnonymousClass1 */

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
            }
            ((StickerInfo) GridAdapter.this.getItem(this.position)).setIS_DOWNLOADING(false);
            GridAdapter.this.notifyDataSetChanged();
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
