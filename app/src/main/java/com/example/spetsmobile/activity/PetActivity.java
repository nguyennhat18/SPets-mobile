package com.example.spetsmobile.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.model.response.PetResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.squareup.picasso.Picasso;

public class PetActivity extends AppCompatActivity {

    private TextView tvName;
    private ImageView imgAvatar;
    private TextView tvHealth;
    private AppCompatButton btnVaccine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Thiết lập nút "Back" ở tiêu đề
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvName = findViewById(R.id.tvName);
        imgAvatar = findViewById(R.id.imgAvatar);
        tvHealth = findViewById(R.id.tvHealth);
        btnVaccine = findViewById(R.id.btnVaccine);

        PetResponse response = ConstantUtil.getPetResponse();
        if (response == null) {
            response = new PetResponse();
        }

        tvName.setText(response.getName());
        tvHealth.setText(response.getHealth());

        String imageURL = APIClient.HOST_URL + "/resources/file" + response.getAvatar();
        Picasso.get().load(imageURL).placeholder(R.drawable.book1).into(imgAvatar);

        btnVaccine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PetActivity.this, VaccineActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_custom, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            // xử lý sự kiện khi nút "Back" được nhấn
            finish(); // đóng hoạt động và quay về hoạt động trước đó
            return true;
        }

        if (id == R.id.action_logout) {
            Intent intent = new Intent(PetActivity.this, LoginActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}