package com.example.lifeline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Token_Adapter extends RecyclerView.Adapter<Token_Adapter.MyViewHolder> {


    private tokenStatusFragment context;
    private ArrayList<Token> Doctors_Status;

    public Token_Adapter(tokenStatusFragment c, ArrayList<Token> u) {
        context = c;
        Doctors_Status = u;
    }

    @NonNull
    @Override
    public Token_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_token_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Token_Adapter.MyViewHolder holder, int position) {

        holder.Doctor_name.setText(String.valueOf(Doctors_Status.get(position).getDoctor_name()));
        holder.token_no.setText(String.valueOf(Doctors_Status.get(position).getToken_No()));
    }

    @Override
    public int getItemCount() {
        return Doctors_Status.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Doctor_name, token_no;

        public MyViewHolder(View itemView) {
            super(itemView);
            Doctor_name = itemView.findViewById(R.id.Doctor_name);
            token_no = itemView.findViewById(R.id.token);
        }

    }
}
