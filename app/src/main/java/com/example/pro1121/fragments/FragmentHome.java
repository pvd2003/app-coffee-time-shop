package com.example.pro1121.fragments;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.pro1121.R;
import com.example.pro1121.activities.OrderActivity;
import com.example.pro1121.adapter.PhotoAdapter;
import com.example.pro1121.adapter.SanPhamHomeAdapter;
import com.example.pro1121.model.ItemClick;
import com.example.pro1121.model.Photo;
import com.example.pro1121.model.Sanpham;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class FragmentHome extends Fragment {
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    private PhotoAdapter adapter;
    private EditText edtSearch;
    private SanPhamHomeAdapter msanphamaapter;
    private RecyclerView recyclerView;
    private MenuItem menuItem;
    private SearchView searchView;

    List<Sanpham> mlistsp;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    final CollectionReference reference = firebaseFirestore.collection("sanpham");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rcysp);
        viewPager = view.findViewById(R.id.viewpager);
        circleIndicator = view.findViewById(R.id.circleIndicator);

        getlistdatafirebasestore();

        loadSlideShow();

        return view;
    }

    //Lấy dữ liệu từ Firebase
    private void getlistdatafirebasestore(){
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                mlistsp= new ArrayList<>();
                if (task.isSuccessful()) {
                    QuerySnapshot querySnapshot = task.getResult();
                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        Sanpham sanpham = new Sanpham();
                        sanpham.setTenSP(doc.get("tensanpham").toString());
                        sanpham.setGiatien(doc.get("giasanpham").toString());
                        mlistsp.add(sanpham);
                    }
                    //chạy lại dữ liệu
                    loadData();
                }
            }
        });
    }

    //Lấy thêm ảnh vào slideshow
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.hsv_1));
        list.add(new Photo(R.drawable.hsv_2));
        list.add(new Photo(R.drawable.hsv_3));
        return list;
    }

    //Hiện tất cả sản phẩm lên màn hình
    private void loadData(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext()
                , DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        msanphamaapter = new SanPhamHomeAdapter(mlistsp, new ItemClick() {
            @Override
            public void onClickSanPham(Sanpham sanpham) {
                Intent intent = new Intent(getContext(),OrderActivity.class);
                intent.putExtra("chitietsanppham",sanpham);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(msanphamaapter);
    }

    //Hiển thị slideshow
    private void loadSlideShow(){
        adapter = new PhotoAdapter(getContext(),getListPhoto());
        viewPager.setAdapter(adapter);

        circleIndicator.setViewPager(viewPager);
        adapter.registerDataSetObserver(circleIndicator.getDataSetObserver());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
          inflater.inflate(R.menu.menusearch,menu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.find).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                msanphamaapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                msanphamaapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


}




