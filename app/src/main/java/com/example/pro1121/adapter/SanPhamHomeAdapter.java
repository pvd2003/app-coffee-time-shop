package com.example.pro1121.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.ItemClick;
import com.example.pro1121.model.Sanpham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamHomeAdapter extends RecyclerView.Adapter<SanPhamHomeAdapter.Viewhoder> implements Filterable {
    private ItemClick itemClick;


    private List<Sanpham> mlist;
    private List<Sanpham> mlistold;

    public SanPhamHomeAdapter(List<Sanpham> mlist, ItemClick itemClick ) {
        this.mlist = mlist;
        this.itemClick = itemClick;
        this.mlistold = mlist;
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

     //Thực hiện click vào 1 sản phẩm trong trang home
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
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String search = constraint.toString();
                if(search.isEmpty()){
                    mlist= mlistold;
                }else {
                    List<Sanpham> list = new ArrayList<>();
                    for(Sanpham sanpham: mlistold) {
                        if (sanpham.getTenSP().toLowerCase().contains(search.toLowerCase())) {
                            list.add(sanpham);
                        }
                    }
                    mlist = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mlist;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mlist = (List<Sanpham>) results.values;
                    notifyDataSetChanged();

            }
        };
    }
}
