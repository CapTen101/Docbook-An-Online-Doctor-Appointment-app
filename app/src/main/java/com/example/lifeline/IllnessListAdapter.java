package com.example.lifeline;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class IllnessListAdapter extends RecyclerView.Adapter<IllnessListAdapter.myViewHolder> {

    private ArrayList<String> millnessName;
    private ArrayList<String> mDepartmentName;
    private Context mContext;

    public IllnessListAdapter(Context context, ArrayList<String> illnessName) {
        millnessName = illnessName;
        mContext = context;
    }

    @NonNull
    @Override
    public IllnessListAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.illness_list_item, parent, false);
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull IllnessListAdapter.myViewHolder holder, final int position) {
        Log.e("hey!", "OnBindViewHolder is called, instanciated");
        holder.thisillnessName.setText(millnessName.get(position));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("working","fine");
                Intent goToDoctorListPage = new Intent(mContext, ListOfDoctorsActivity.class);
                goToDoctorListPage.putExtra("Department",millnessName.get(position));
                mContext.startActivity(goToDoctorListPage);
            }
        });
    }

    @Override
    public int getItemCount() {
        return millnessName.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {

        TextView thisillnessName;
        RelativeLayout relativeLayout;

        public myViewHolder(@NonNull View itemView1) {
            super(itemView1);

            thisillnessName = itemView.findViewById(R.id.illness_list_item);
            relativeLayout = itemView.findViewById(R.id.illness_list_layout);
        }
    }
}