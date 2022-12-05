package com.example.pro1121.activities;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
=======
>>>>>>> bdee6ba6297f18f6e3f7188dea12233f52d1a4c6
import android.widget.TextView;

import com.example.pro1121.R;

public class OrderActivity extends AppCompatActivity {
   TextView txtbuynow ;
   ImageView ivprnev;
   TextView txtten,txtgia,txtcong,txttru,txtnumber;
   int number=1;
   double tongtien;
    int soluong;
    int madonhang = 1;

<<<<<<< HEAD
    @SuppressLint("MissingInflatedId")
=======
    TextView tenSanPham, giaSanPham;


>>>>>>> bdee6ba6297f18f6e3f7188dea12233f52d1a4c6
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
<<<<<<< HEAD
        txtten= findViewById(R.id.ten);
        txtgia= findViewById(R.id.gia);
        txtcong= findViewById(R.id.cong);
        txttru = findViewById(R.id.tru);
        txtnumber= findViewById(R.id.number);
        ivprnev= findViewById(R.id.icon_prev);
        txtbuynow = findViewById(R.id.txtbuynow);
         soluong = Integer.parseInt(txtnumber.getText().toString());
         tongtien=(soluong * 3.50);
         String ten = txtten.getText().toString();


        txtcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              number=number+1;
              txtnumber.setText(String.valueOf(number));
            }
        });
        txttru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number>1){
                    number=number-1;
                }
                txtnumber.setText(toString().valueOf(number));
            }
        });
        ivprnev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtbuynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), TTDonhangActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("ten",ten);
                bundle.putInt("soluong",soluong);
                bundle.putDouble("tien",tongtien);
                bundle.putInt("madonhang",madonhang);
                intent.putExtra("dulieu",bundle);
                startActivity(intent);

            }
        });
    }

    }
=======
        tenSanPham = findViewById(R.id.titleSP);
        giaSanPham = findViewById(R.id.priceSP);
    }

}
>>>>>>> bdee6ba6297f18f6e3f7188dea12233f52d1a4c6
