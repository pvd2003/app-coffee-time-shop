package com.example.pro1121.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.ItemClick;
import com.example.pro1121.model.Sanpham;

import java.util.List;

public class SanPhamHomeAdapter extends RecyclerView.Adapter<SanPhamHomeAdapter.Viewhoder> {
    private ItemClick itemClick;


    private List<Sanpham> mlist;

    public SanPhamHomeAdapter(List<Sanpham> mlist, ItemClick itemClick ) {
        this.mlist = mlist;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham_home,parent,false);
       return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Sanpham sp = mlist.get(position);
     holder.tvTenSanPham.setText(sp.getTenSP());



     holder.imgSanPham.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Sanpham sanpham = mlist.get(holder.getAdapterPosition());
             itemClick.onClickSanPham(sanpham);
         }
     });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView tvTenSanPham;
      ImageView imgSanPham;

        public Viewhoder(@NonNull View view) {
            super(view);
            tvTenSanPham= view.findViewById(R.id.tvTenSanPham);
            imgSanPham= view.findViewById(R.id.imgSanPham);



        }
        //
    }
}
