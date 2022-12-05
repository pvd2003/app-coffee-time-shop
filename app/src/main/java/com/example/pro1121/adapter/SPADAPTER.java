package com.example.pro1121.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.model.Sanpham;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class SPADAPTER extends RecyclerView.Adapter<SPADAPTER.Viewhoder> {


    private List<Sanpham> mlist;

    public SPADAPTER(List<Sanpham> mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sanpham,parent,false);
       return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Sanpham sp = mlist.get(position);
     holder.txttensp.setText(sp.getTenloai());
     holder.imupdate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             ondelit();
         }
     });


    }

    private void ondelit() {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final   CollectionReference reference = firebaseFirestore.collection("sanpham");


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView txttensp;
      ImageView imsp, imdelit,imupdate;

        public Viewhoder(@NonNull View view) {
            super(view);
            txttensp= view.findViewById(R.id.tvtensp);
            imsp= view.findViewById(R.id.sanpham);
            imdelit = view.findViewById(R.id.ivDeletesp);
            imupdate = view.findViewById(R.id.ivEditsp);



        }
        //
    }
}
