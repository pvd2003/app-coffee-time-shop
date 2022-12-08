package com.example.pro1121.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.fragments.FragmentQLSanPham;
import com.example.pro1121.model.ItemClick;
import com.example.pro1121.model.Sanpham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuanLySanPhamAdapter extends RecyclerView.Adapter<QuanLySanPhamAdapter.Viewhoder> {


    private List<Sanpham> mlist;
    private ItemClick itemClick;
    private FragmentQLSanPham mfragmentQLSanPham;

    public QuanLySanPhamAdapter(List<Sanpham> mlist, ItemClick itemClick) {
        this.mlist = mlist;
        this.itemClick = itemClick;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_qly_san_pham,parent,false);
       return new Viewhoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        Sanpham sp = mlist.get(position);
     holder.tvTenSP.setText("Tên sản phẩm: " + sp.getTenSP());
     holder.tvGiaTien.setText("Giá tiền: " + sp.getGiatien());

     holder.linearSanPham.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Sanpham sanpham = mlist.get(holder.getAdapterPosition());
             itemClick.onClickSanPham(sanpham);
         }
     });

//     holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//             AlertDialog.Builder builder = new AlertDialog.Builder(mfragmentQLSanPham.getContext())
//                     .setNegativeButton("OK", null)
//                     .setPositiveButton("Close",null);
//             LayoutInflater inflater = holder.;
//             View view = inflater.inflate(R.layout.dialog_update_san_pham,null);
//         }
//     });

     holder.imgDelete.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             AlertDialog.Builder builder = new AlertDialog.Builder(holder.tvTenSP.getContext());
             builder.setTitle("Bạn có chắc không?");

             builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                     final CollectionReference reference = firebaseFirestore.collection("sanpham");
                     reference.document(sp.getIdsanpham())
                             .delete()
                             .addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void unused) {
                             Toast.makeText(holder.tvTenSP.getContext(), "xóa thành công!", Toast.LENGTH_SHORT).show();
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     Toast.makeText(holder.tvTenSP.getContext(), "xóa thất bại!", Toast.LENGTH_SHORT).show();
                                 }
                             });
                 }
             });

             builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {

                 }
             });
             builder.show();
         }
     });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
      TextView tvTenSP, tvGiaTien;
      LinearLayout linearSanPham;
      ImageView imgDelete, imgUpdate;

        public Viewhoder(@NonNull View view) {
            super(view);
            tvTenSP= view.findViewById(R.id.tvTenSP);
            tvGiaTien= view.findViewById(R.id.tvGiaTien);
            imgDelete = view.findViewById(R.id.imgDelete);
            imgUpdate = view.findViewById(R.id.imgUpdate);
            linearSanPham = view.findViewById(R.id.linearSanPham);

        }
        //
    }

//    public void readData(){
//        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
//        final CollectionReference reference = firebaseFirestore.collection("sanpham")
//                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        mlist = new ArrayList<>();
//                        if (task.isSuccessful()) {
//                            QuerySnapshot querySnapshot = task.getResult();
//                            for (QueryDocumentSnapshot doc : querySnapshot) {
//                                Map<String, Object> sanpham = new HashMap<>();
//                                sanpham.put("tensanpham", tvTenSP.getText().toString());
//                                sanpham.put("giasanpham", tvGiaTien.getText().toString());
//                                Sanpham sanpham1 = new Sanpham()
//
//                                sanphamList.add(sanpham);
//
//                            }
//                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
//                            recyclerView.setLayoutManager(linearLayoutManager);
//                            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
//                                    , DividerItemDecoration.VERTICAL);
//                            recyclerView.addItemDecoration(dividerItemDecoration);
//                            quanLySanPhamAdapter = new QuanLySanPhamAdapter(sanphamList, new ItemClick() {
//                                @Override
//                                public void onClickSanPham(Sanpham sanpham) {
//                                    edtTenSP.setText(sanpham.getTenSP());
//                                    edtGiaSP.setText(sanpham.getGiatien());
//                                }
//                            });
//                            recyclerView.setAdapter(quanLySanPhamAdapter);
//                        }
//                    }
//                })
//
//    }
}
