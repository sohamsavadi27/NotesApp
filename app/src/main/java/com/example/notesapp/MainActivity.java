package com.example.notesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etContent;
    Button btnSave, btnView;
    LinearLayout layoutNotes;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);
        btnView = findViewById(R.id.btnView);
        layoutNotes = findViewById(R.id.layoutNotes);

        databaseReference = FirebaseDatabase.getInstance().getReference("notes");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString();

                if (title.isBlank()) {
                    Toast.makeText(MainActivity.this, "title is blank", Toast.LENGTH_SHORT).show();
                    etTitle.requestFocus();
                    return;
                }

                String content = etContent.getText().toString();

                if (content.isBlank()) {
                    Toast.makeText(MainActivity.this, "content is blank", Toast.LENGTH_SHORT).show();
                    etContent.requestFocus();
                    return;
                }

                String id = databaseReference.push().getKey();

                databaseReference.child(id).child("title").setValue(title);
                databaseReference.child(id).child("content").setValue(content)
                        .addOnSuccessListener(a -> {
                            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                            etTitle.setText("");
                            etContent.setText("");
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        });
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        layoutNotes.removeAllViews();

                        for (DataSnapshot ds : snapshot.getChildren()) {

                            String id = ds.getKey();
                            String title = ds.child("title").getValue(String.class);
                            String content = ds.child("content").getValue(String.class);

                            TextView tv = new TextView(MainActivity.this);
                            tv.setText("Title: " + title + "\nContent: " + content);
                            tv.setTextSize(16);
                            tv.setPadding(10, 10, 10, 10);

                            Button btnDelete = new Button(MainActivity.this);
                            btnDelete.setText("Delete");

                            btnDelete.setOnClickListener(v1 -> {
                                databaseReference.child(id).removeValue()
                                        .addOnSuccessListener(a -> {
                                            Toast.makeText(MainActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                        });
                            });

                            LinearLayout itemLayout = new LinearLayout(MainActivity.this);
                            itemLayout.setOrientation(LinearLayout.VERTICAL);
                            itemLayout.setPadding(20, 20, 20, 20);
                            itemLayout.setElevation(5);

                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.WRAP_CONTENT
                            );

                            params.setMargins(0, 0, 0, 20);
                            itemLayout.setLayoutParams(params);

                            itemLayout.addView(tv);
                            itemLayout.addView(btnDelete);

                            layoutNotes.addView(itemLayout);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this, "issue " + error, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}