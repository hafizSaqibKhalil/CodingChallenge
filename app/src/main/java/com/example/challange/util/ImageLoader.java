package com.example.challange.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.challange.R;

import java.io.File;



/**
 * Created by Saqib Khalil
 *
 */
public class ImageLoader {

    public static final int DEFAULT_PLACE_HOlDER_IMAGE = R.drawable.ic_launcher_background;

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView iv,String url) {


        Glide.with(iv.getContext())
                .load(url).placeholder(DEFAULT_PLACE_HOlDER_IMAGE).error(DEFAULT_PLACE_HOlDER_IMAGE)
                .skipMemoryCache(false)
                // .fitCenter()

                .into(iv);
    }





}
