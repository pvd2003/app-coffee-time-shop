package com.example.pro1121.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.pro1121.R;
import com.example.pro1121.model.Photo;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {

    private Context context;
    private List<Photo> list;

    public PhotoAdapter(Context context, List<Photo> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slideshow,container,false);
        ImageView imgPhoto = view.findViewById(R.id.imgPhoto);

        Photo photo = list.get(position);
        if(photo != null){
            Glide.with(context).load(photo.getIdPhoto()).into(imgPhoto);
        }
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
