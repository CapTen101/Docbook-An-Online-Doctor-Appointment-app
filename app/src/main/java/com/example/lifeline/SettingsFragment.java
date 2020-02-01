package com.example.lifeline;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsFragment extends AppCompatActivity {

    Button button;
    TextView name, mail, branch, en_no;
    FirebaseAuth fAuth;
    FirebaseUser current_user;
    DatabaseReference mDatabase;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        name = findViewById(R.id.profile_name);
        mail = findViewById(R.id.pro_mail);
        branch = findViewById(R.id.pro_branch);
        en_no = findViewById(R.id.pro_no_);
        progressBar = findViewById(R.id.progressBar2);
        fAuth = FirebaseAuth.getInstance();
        current_user = fAuth.getCurrentUser();
        final String CURRENT = current_user.getEmail();
        Log.e("currenty", "currenty" + CURRENT);

        Toolbar toolbar = findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user.getUid());
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e("0000", "0000" + (dataSnapshot.getValue(User.class).getName()));
                String NAME = dataSnapshot.getValue(User.class).getName().toString();
                String BRANCH = dataSnapshot.getValue(User.class).getBranch().toString();
                Log.e("0001", "0001" + (dataSnapshot.getValue(User.class).getBranch().toString()));
                String MIAL = dataSnapshot.getValue(User.class).getEmail().toString();
                Log.e("0002", "0002" + (dataSnapshot.getValue(User.class).getEmail()));
                String EN_NO = dataSnapshot.getValue(User.class).getEnrollment().toString();
                Log.e("0003", "0003" + (dataSnapshot.getValue(User.class).getEnrollment()));

                name.setText(NAME);
                Log.e("0003", "0003" + name);
                branch.setText(BRANCH);
                Log.e("0003", "0003" + branch);
                mail.setText(MIAL);
                Log.e("0003", "0003" + mail);
                en_no.setText(EN_NO);
                Log.e("0003", "0003" + en_no);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}


