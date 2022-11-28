package com.example.pro1121.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pro1121.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class FragmentQLLoaiSanPham extends Fragment {
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    private FirebaseFirestore firebaseFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ql_loai_san_pham, container, false);
      
        floatingActionButton = view.findViewById(R.id.floatAdd);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             opendialog();
            }
        });
        return view;


    }

    private void opendialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater= this.getLayoutInflater();
        firebaseFirestore = FirebaseFirestore.getInstance();
        final CollectionReference reference = firebaseFirestore.collection("loaisanpham");
        View view =inflater.inflate(R.layout.dialog_addloaisp,null);
        EditText edtmaloai = view.findViewById(R.id.edtmaloai);
        EditText edttenloai = view.findViewById(R.id.edttenloai);
        Button btnadd = view.findViewById(R.id.btnthemmoi);
        Button btnhuy = view.findViewById(R.id.btnhuy);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> loaisanpham = new HashMap<>();
                loaisanpham.put("maloai",edtmaloai.getText().toString());
                loaisanpham.put("tenloai",edttenloai.getText().toString());
                reference.add(loaisanpham)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getContext(), "add thành công!", Toast.LENGTH_SHORT).show();
                        edttenloai.setText("");
                        edtmaloai.setText("");

                    }
                })       .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), "add thất bại!", Toast.LENGTH_SHORT).show();
                                
                            }
                        });

            }
        });
    }
}
