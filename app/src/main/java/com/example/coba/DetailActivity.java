package com.example.coba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    TextView detailName, detailCode, detailType, detailPrice, detailStock, detailDate;
    ImageView detailImage;
    FloatingActionButton deleteButton, editButton;
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailName = findViewById(R.id.detailName);
        detailCode = findViewById(R.id.detailCode);
        detailType = findViewById(R.id.detailType);
        detailPrice = findViewById(R.id.detailPrice);
        detailStock = findViewById(R.id.detailStock);
        detailDate = findViewById(R.id.detailDate);
        detailImage = findViewById(R.id.detailImage);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        // Gw Nambahin ini
        String Name = bundle.getString("Name");
        imageUrl = bundle.getString("image");
        if (bundle != null) {
            detailName.setText(bundle.getString("Name"));
            detailCode.setText(bundle.getString("Item Code"));
            detailType.setText(bundle.getString("Item Type"));
            detailPrice.setText(bundle.getString("Price"));
            detailStock.setText(bundle.getString("Stock"));
            detailDate.setText(bundle.getString("Date"));
            Glide.with(this).load(bundle.getString("image")).into(detailImage);
        }
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("My Inventory");
                FirebaseStorage storage = FirebaseStorage.getInstance();
                reference.child(Name).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(DetailActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }
                });
            }
        });
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("Name", detailName.getText().toString())
                        .putExtra("Item Code", detailCode.getText().toString())
                        .putExtra("Item Type", detailType.getText().toString())
                        .putExtra("Price", detailPrice.getText().toString())
                        .putExtra("Stock", detailStock.getText().toString())
                        .putExtra("Date", detailDate.getText().toString())
                        .putExtra("Image", imageUrl);
                startActivity(intent);
            }
        });
    }
}