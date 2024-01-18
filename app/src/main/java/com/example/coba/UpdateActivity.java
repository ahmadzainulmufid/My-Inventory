package com.example.coba;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class UpdateActivity extends AppCompatActivity {

    ImageView updateImage;
    Button updateButton;
    EditText updateName, updateCode, updatePrice, updateStock, updateDate;
    String name, code, type, price, stock, date, originalName, UpdateName;
    String imageUrl;
    String key, oldImageURL;
    Uri uri;
    AutoCompleteTextView updateType;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    private int tahun, bulan, tanggal;
    ArrayAdapter<String> adapterItems;
    String[] item = {"raw goods", "semi-finished goods", "finished goods", "supply of goods", "Merchandise"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton = findViewById(R.id.updateButton);
        updateName = findViewById(R.id.updateName);
        updateCode = findViewById(R.id.updateCode);
        updatePrice = findViewById(R.id.updatePrice);
        updateStock = findViewById(R.id.updateStock);
        updateDate = findViewById(R.id.updateDate);
        updateImage = findViewById(R.id.updateImage);
        Bundle bundle = getIntent().getExtras();

        updateType = findViewById(R.id.updateType);
        adapterItems = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, item);
        originalName = bundle.getString("Name");
        if (bundle != null) {
            Glide.with(UpdateActivity.this).load(bundle.getString("Image")).into(updateImage);
            updateName.setText(bundle.getString("Name"));
            updateCode.setText(bundle.getString("Item Code"));
            updateType.setText(bundle.getString("Item Type"));
            updatePrice.setText(bundle.getString("Price"));
            updateStock.setText(bundle.getString("Stock"));
            updateDate.setText(bundle.getString("Date"));
            key = bundle.getString("Key");
            oldImageURL = bundle.getString("Image");

        }

        if (bundle != null) {
            Glide.with(UpdateActivity.this).load(bundle.getString("Image")).into(updateImage);
            updateName.setText(bundle.getString("Name"));
            updateCode.setText(bundle.getString("Item Code"));
            updateType.setText(bundle.getString("Item Type"));
            updatePrice.setText(bundle.getString("Price"));
            updateStock.setText(bundle.getString("Stock"));
            updateDate.setText(bundle.getString("Date"));
            Log.d("TRYKEY",bundle.getString("Name"));
                    key = bundle.getString("id");
            oldImageURL = bundle.getString("Image");
        }
        UpdateName = bundle.getString("Name");
        databaseReference = FirebaseDatabase.getInstance().getReference("My Inventory").child(UpdateName);
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = updateName.getText().toString();
                code = updateCode.getText().toString();
                type = updateType.getText().toString();
                price = updatePrice.getText().toString();
                stock = updateStock.getText().toString();
                date = updateDate.getText().toString();
                key = key;
                imageUrl = bundle.getString("Image");
                DatabaseReference newReference = databaseReference.getParent().child(name);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        newReference.setValue(snapshot.getValue(), new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                if(error != null){
                                    Toast.makeText(UpdateActivity.this, "Failed to rename the node", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                databaseReference.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        DataClass newInventory = new DataClass ( name, code, type, price, stock, date, imageUrl);
                                        newReference.setValue(newInventory);
                                        Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e("Firebase", "Error removing the original node", e);
                                    }
                                });
                            }
                        });

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Firebase", "Error renaming the node", error.toException());
                    }
                });



            }
    });
}

}
