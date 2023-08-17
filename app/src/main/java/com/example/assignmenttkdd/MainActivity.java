package com.example.assignmenttkdd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignmenttkdd.GioiThieu.GioiThieuFragment;
import com.example.assignmenttkdd.KhoanChi.KhoanChiFragment;
import com.example.assignmenttkdd.KhoangThu.KhoangThuFragment;
import com.example.assignmenttkdd.ThongKe.ThongKeFragment;
import com.google.android.material.navigation.NavigationView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private boolean chk = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.id_drawerLayout);
        toolbar = findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, 0, 0);
        toggle.syncState();
        navigationView = findViewById(R.id.layout_navigation);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.khoanthu) {
            replaceFragment(KhoangThuFragment.newInstance());
        } else if (i == R.id.khoanchi) {
            replaceFragment(KhoanChiFragment.newInstance());
        } else if (i == R.id.thongke) {
            replaceFragment(ThongKeFragment.newInstance());
        } else if (i == R.id.gioithieu) {
            replaceFragment(GioiThieuFragment.newInstance());
        } else if (i == R.id.thoat) {
            AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thoát");
            builder.setMessage("Bạn có chắc muốn thoát ứng dụng không?");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    Toast.makeText(getBaseContext(), "Cancel Exit", Toast.LENGTH_SHORT).show();
                }
            });
            builder.create();
            builder.show();
        }
        drawerLayout.closeDrawer(navigationView);
        return true;
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout_content, fragment);
        transaction.commit();
    }
}