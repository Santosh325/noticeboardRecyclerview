package com.example.noticeboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sticknotes extends AppCompatActivity {
    private DatabaseReference mDatabase;
    Button mButton;
    EditText title,description;
    String titlesend,descsend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticknotes);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        mButton = findViewById(R.id.ok);
    }
    public void addNotes(View view) {
        titlesend = title.getText().toString();
        descsend = description.getText().toString();

        if(TextUtils.isEmpty(titlesend) || TextUtils.isEmpty(descsend)) {
            return;
        }
        AddNotesInDatabase(titlesend,descsend);
    }
    private void AddNotesInDatabase(String titlesend,String descsend) {
        String id = mDatabase.push().getKey();
        Notes notes = new Notes(id,titlesend,descsend);
        mDatabase.child("Notes").child(id).setValue(notes).
                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(sticknotes.this, "Notes Added", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(sticknotes.this,"Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
