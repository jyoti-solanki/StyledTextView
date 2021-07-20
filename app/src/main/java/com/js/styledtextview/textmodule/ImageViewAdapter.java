package com.js.styledtextview.textmodule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.js.styledtextview.R;

public class ImageViewAdapter extends BaseAdapter {
    private final int[] Imageid;
    private Context mContext;

    public ImageViewAdapter(Context c, int[] Imageid2) {
        this.mContext = c;
        this.Imageid = Imageid2;
    }

    public int getCount() {
        return this.Imageid.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        ImageView imageView;

        public ViewHolder() {
        }
    }

    @SuppressLint("WrongConstant")
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.libtext_btxt_lst_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.grid_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource(this.Imageid[position]);
        return convertView;
    }
}
