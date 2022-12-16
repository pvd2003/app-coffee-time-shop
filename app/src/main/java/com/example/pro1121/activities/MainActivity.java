package com.example.pro1121.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.pro1121.R;
import com.example.pro1121.fragments.FragmentCaNhan;
import com.example.pro1121.fragments.FragmentHome;
import com.example.pro1121.fragments.FragmentLichSu;
import com.example.pro1121.fragments.FragmentQLGioHang;
import com.example.pro1121.fragments.FragmentQLSanPham;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolBar;
    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                    case R.id.mQLGioHang:
                        fragment = new FragmentQLGioHang();
                        break;

                    case R.id.mLichSu:
                        fragment = new FragmentLichSu();
                        break;

                    case R.id.mCaNhan:
                        fragment = new FragmentCaNhan();
                        break;

                    case R.id.mQLSanPham:
                        fragment = new FragmentQLSanPham();
                        break;

                    case R.id.mThoat:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
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

        closeFunction();

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    //Ẩn chức năng khi không phải là nhân viên
    private void closeFunction(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(!user.getDisplayName().equals("Admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.mQLSanPham).setVisible(false);

        }
    }
}