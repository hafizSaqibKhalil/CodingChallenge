package com.example.challange.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.challange.R;
import com.example.challange.databinding.HeaderItemBinding;
import com.example.challange.databinding.ItemPhotoBinding;
import com.example.challange.network.response.ResponsePhotos;
import com.example.challange.util.ImageLoader;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Saqib Khalil on 03/28/2019.
 */
public class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements StickyRecyclerHeadersAdapter {

    public static final String TAG = PhotosAdapter.class.getSimpleName();

    private List<ResponsePhotos> photosList = new ArrayList<>(0);

    private LayoutInflater inflater;


    // constructor
    public PhotosAdapter(Context context) {

        inflater = LayoutInflater.from(context);

    }

    public void setList(List<ResponsePhotos> list) {
        this.photosList.clear();
        this.photosList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemPhotoBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.item_photo, parent, false);
        holder = new MyViewHolderChild(binding.getRoot());
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ResponsePhotos photo = photosList.get(position);
        MyViewHolderChild holder = (MyViewHolderChild) viewHolder;
        holder.mBinding.setPhoto(photo);
    }




    @Override
    public int getItemCount() {
        return photosList.size();
    }

    class MyViewHolderChild extends RecyclerView.ViewHolder {

       /* TextView tvchild;
        TextView tvHeaderIds;*/
       ItemPhotoBinding mBinding;

        public MyViewHolderChild(View itemView) {
            super(itemView);

          /*  tvchild = (TextView) itemView.findViewById(R.id.tvTitle);
            tvHeaderIds = (TextView) itemView.findViewById(R.id.tvHeaderIds);*/
            mBinding = DataBindingUtil.bind(itemView);
        }

    }



//---------------------------Header-----------------------------------------------




    @Override
    public long getHeaderId(int position) {
        return photosList.get(position).getAlbumId();
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        RecyclerView.ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        HeaderItemBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.header_item, parent, false);
        holder = new MyViewHolderHeader(binding.getRoot());
        return holder;
    }

    @Override
    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        ResponsePhotos photos = photosList.get(position);
        MyViewHolderHeader holder1 = (MyViewHolderHeader) holder;
        holder1.headerBinding.tvHeader.setText("Album Id is "+photos.getAlbumId());
    }
    class MyViewHolderHeader extends RecyclerView.ViewHolder {
        HeaderItemBinding headerBinding;
        TextView tvHeader;

        public MyViewHolderHeader(View itemView) {
            super(itemView);

        //    tvHeader = (TextView) itemView.findViewById(R.id.tvHeader);
            headerBinding = DataBindingUtil.bind(itemView);
        }

    }

//-----------------------end----------------------------------------------------------


}
