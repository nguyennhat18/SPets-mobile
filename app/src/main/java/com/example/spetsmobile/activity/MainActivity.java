package com.example.spetsmobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.model.response.AuthResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.spetsmobile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private NavigationView navigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fragment = getCurrentFragmentName();
                Snackbar.make(view, "Replace with your own action >> " + fragment, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                if (fragment != null) {
                    fragment = fragment.replace("com.example.spetsmobile.ui.", "");
                    Intent intent = null;

                    switch (fragment) {
                        case "pet.PetFragment":
                            ConstantUtil.setPetResponse(null);
                            intent = new Intent(MainActivity.this, PetFormActivity.class);
                            break;
//                        case "vaccine.VaccineFragment":
//                            intent = new Intent(MainActivity.this, VaccineFormActivity.class);
//                            break;
                        default:
                            break;
                    }

                    if (intent != null) {
                        startActivity(intent);
                    }
                }
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        navigationView = binding.navView;

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_pet, R.id.nav_vaccine)
                .setOpenableLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setProfile();
    }

    private void setProfile() {
        AuthResponse account = ConstantUtil.getAuth();
        if (account != null) {
            View headerView = navigationView.getHeaderView(0);

            // set user
            TextView tvUser = (TextView) headerView.findViewById(R.id.tvUser);
            tvUser.setText(account.getFullname());

            // set role
            TextView tvRole = (TextView) headerView.findViewById(R.id.tvRole);
            tvRole.setText(account.getRole());
        }
    }

    // sẽ trả về tên của fragment đang hiển thị
    public String getCurrentFragmentName() {
        NavDestination currentDestination = navController.getCurrentDestination();
        // Lấy tên lớp của fragment từ tên thành phần của địa chỉ đích
        String className = ((FragmentNavigator.Destination) currentDestination).getClassName();
        return className;
    }

}