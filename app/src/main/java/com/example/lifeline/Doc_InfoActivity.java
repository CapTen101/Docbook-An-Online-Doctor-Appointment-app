package com.example.lifeline;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Doc_InfoActivity extends AppCompatActivity {
    ImageView Image;
    TextView name, graduate, dpt, doc_info;
    String Doctorname, Doctorgraduate, Doctordpt, Docimage, Docinfo;
    Button button;
    private DatabaseReference mDatabase;
    private DatabaseReference fDatabase;
    private DatabaseReference tDatabase;
    private DatabaseReference pDatabase;
    SharedPreferenceConfig preferenceConfig;
    FirebaseAuth dAuth;
    FirebaseUser Current_User;
    int tok;
    int TOKEN;
    int Status_token;
    String User_Doctor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doc_info);

        doc_info = findViewById(R.id.doc_info);
        Image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        graduate = findViewById(R.id.graduate);
        dpt = findViewById(R.id.dpt);
        Doctorname = getIntent().getStringExtra("name");
        Doctorgraduate = getIntent().getStringExtra("graduate");
        Doctordpt = getIntent().getStringExtra("dpt");
        Docinfo = getIntent().getStringExtra("doc_info");
        Docimage = getIntent().getStringExtra("image");
        name.setText(Doctorname);
        Log.e("jhfklsdajflk;", "dsfnhkdjsh" + Doctorname);
        graduate.setText(Doctorgraduate);
        dpt.setText(Doctordpt);

        Picasso.get().load(Docimage).into(Image);
        dAuth = FirebaseAuth.getInstance();
        Current_User = dAuth.getCurrentUser();
        final String current_name = Current_User.getEmail();
        final ProgressDialog dialog = new ProgressDialog(this);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors_List");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                    String value = dataSnapshot2.child("doc_info").getValue().toString();
                    doc_info.setText(value);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_User.getUid());
        tDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User_Doctor = dataSnapshot.getValue(User.class).getMy_doctor();

                TOKEN = dataSnapshot.getValue(User.class).getMy_token();
                Log.e("123", "asdfg" + TOKEN);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        button = findViewById(R.id.bookbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.setMessage("Booking Appointment, Please wait.");
                dialog.show();
                if (TOKEN == 0) {

                    {
                        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors_List");
                        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Log.i("Function", "Called");
                                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    if (Doctorname.equals(dataSnapshot1.getValue(Doctor.class).getDoc_name())) {
                                        Log.e("doc_name", "ahkfjhkj" + dataSnapshot1.getValue(Doctor.class).getDoc_name());
                                        Log.e("doc_token", "ahkfjhkj" + dataSnapshot1.getValue(Doctor.class).getDoc_token());
                                        String current_name = dataSnapshot1.getValue(Doctor.class).getDoc_name();
                                        tok = dataSnapshot1.getValue(Doctor.class).getDoc_token() + 1;
                                        String key = dataSnapshot1.getKey();

                                        mDatabase.child(key).child("doc_token").setValue(tok);
                                    }
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                    {
                        fDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(Current_User.getUid());
                        fDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                Log.e("name", "name" + dataSnapshot.getValue(User.class).getName());
                                Log.e("current", "current" + current_name);
                                String data = dataSnapshot.getKey();
                                fDatabase.child("my_doctor").setValue(Doctorname);
                                fDatabase.child("my_token").setValue(tok).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(getApplicationContext(), tokenStatusFragment.class));
                                            dialog.dismiss();
                                        }
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                } else {
                    Toast.makeText(Doc_InfoActivity.this, "You can't Book twice", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }

        });
    }
}