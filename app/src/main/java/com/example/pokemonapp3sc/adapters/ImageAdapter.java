package com.example.pokemonapp3sc.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

public class ImageAdapter extends BaseAdapter { //sets images dynamically based on id
    private List<Integer> thumbnailIds;
    private Context context;

    public ImageAdapter(List<Integer> thumbnailIds, Context mContext) {
        this.thumbnailIds = thumbnailIds;
        this.context = mContext;
    }

    @Override
    public int getCount() {
        return thumbnailIds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return thumbnailIds.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;

        if(imageView == null){
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(350,450));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        imageView.setImageResource(thumbnailIds.get(position));

        return imageView;
    }
}
