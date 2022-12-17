package com.example.pro1121.adapter;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.example.pro1121.fragments.FragmentQLSanPham;
import com.example.pro1121.model.ItemClick;
import com.example.pro1121.model.Sanpham;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.DialogPlusBuilder;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuanLySanPhamAdapter extends RecyclerView.Adapter<QuanLySanPhamAdapter.Viewhoder> {


    private List<Sanpham> mlist;
    private FragmentQLSanPham mfragmentQLSanPham;

    public QuanLySanPhamAdapter(List<Sanpham> mlist) {
        this.mlist = mlist;
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
     holder.tvGiaTien.setText("Giá tiền: " + sp.getGiatien() );

     //Sửa sản phẩm đã chọn trong quản lý sản phẩm
     holder.imgUpdate.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             DialogPlus dialogPlus = DialogPlus.newDialog(holder.tvTenSP.getContext())
                     .setContentHolder(new ViewHolder(R.layout.dialog_update_san_pham))
                     .setExpanded(true,600)
                     .create();

             View mView = dialogPlus.getHolderView();
             EditText edtTenSPDialog = mView.findViewById(R.id.edtTenSPDialog);
             EditText edtGiaSPDialog = mView.findViewById(R.id.edtGiaSPDialog);
             Button btnUpdateSP = mView.findViewById(R.id.btnUpdateSP);

             edtTenSPDialog.setText(sp.getTenSP());
             edtGiaSPDialog.setText(sp.getGiatien());

             dialogPlus.show();

             btnUpdateSP.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Map<String, Object> map = new HashMap<>();
                     map.put("tensanpham",edtTenSPDialog.getText().toString());
                     map.put("giasanpham",edtGiaSPDialog.getText().toString());
                     FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                     final CollectionReference reference = firebaseFirestore.collection("sanpham");
                     reference.document(sp.getIdsanpham())
                             .update(map)
                             .addOnSuccessListener(new OnSuccessListener<Void>() {
                                 @Override
                                 public void onSuccess(Void unused) {
                                    dialogPlus.dismiss();
                                 }
                             }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {
                                     dialogPlus.dismiss();
                                 }
                             });
                 }
             });
         }
     });

     //Xóa sản phẩm đã chọn trong quản lý sản phẩm
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
                             notifyDataSetChanged();
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                                 @Override
                                 public void onFailure(@NonNull Exception e) {

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
      ImageView imgDelete, imgUpdate;

        public Viewhoder(@NonNull View view) {
            super(view);
            tvTenSP= view.findViewById(R.id.tvTenSP);
            tvGiaTien= view.findViewById(R.id.tvGiaTien);
            imgDelete = view.findViewById(R.id.imgDelete);
            imgUpdate = view.findViewById(R.id.imgUpdate);
        }
    }

}
