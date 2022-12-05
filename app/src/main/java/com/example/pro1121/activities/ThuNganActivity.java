package com.example.pro1121.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.pro1121.R;
import com.example.pro1121.fragments.FragmentDoanhThu;
import com.example.pro1121.fragments.FragmentHome;
import com.example.pro1121.fragments.FragmentQLDonHang;
import com.example.pro1121.fragments.FragmentQLLoaiSanPham;
import com.example.pro1121.fragments.FragmentQLSanPham;
import com.example.pro1121.fragments.FragmentQLTaiKhoan;
import com.example.pro1121.fragments.FragmentTop10;
import com.google.android.material.navigation.NavigationView;

public class ThuNganActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_ngan);

        toolBar = findViewById(R.id.toolBar);
        frameLayout = findViewById(R.id.frameLayout);
        navigationView = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawerLayout);

        FragmentHome fragmentHome = new FragmentHome();
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,fragmentHome).commit();


        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Home");
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.mnHome:
                        fragment = new FragmentHome();
                        break;

                    case R.id.mQLSanPham:
                        fragment = new FragmentQLSanPham();
                        break;

                    case R.id.mQLLoaiSanPham:
                        fragment = new FragmentQLLoaiSanPham();
                        break;

                    case R.id.mQLDonHang:
                        fragment = new FragmentQLDonHang();
                        break;

                    case R.id.mQLTaiKhoan:
                        fragment = new FragmentQLTaiKhoan();
                        break;

                    case R.id.mTop10:
                        fragment = new FragmentTop10();
                        break;

                    case R.id.mDoanhThu:
                        fragment = new FragmentDoanhThu();
                        break;

                    case R.id.mDoiMatKhau:
                        // Dialog đổi mật khẩu
                        showDialogDoiMatKhau();
                        break;

                    case R.id.mThoat:
                        startActivity(new Intent(ThuNganActivity.this, LoginActivity.class));
                        finish();
                        break;

                    default:
                        fragment = new FragmentHome();
                        break;
                }

                if (fragment != null) {
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction().replace(R.id.frameLayout, fragment).commit();
                    toolBar.setTitle(item.getTitle());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    //
    public void showDialogDoiMatKhau(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setNegativeButton("OK", null)
                .setPositiveButton("Close",null);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doi_mat_khau,null);
        EditText etOldPass = view.findViewById(R.id.etOldPass);
        EditText etNewPass = view.findViewById(R.id.etNewPass);
        EditText etRePass = view.findViewById(R.id.etRePass);

        builder.setView(view);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThuNganActivity.this, "Successful", Toast.LENGTH_SHORT).show();
            }
        });

    }
}