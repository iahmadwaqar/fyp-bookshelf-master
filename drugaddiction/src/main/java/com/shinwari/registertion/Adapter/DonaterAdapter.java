package com.shinwari.registertion.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.shinwari.registertion.Model.DonatorInfo;
import com.shinwari.registertion.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class DonaterAdapter extends RecyclerView.Adapter<DonaterAdapter.ViewHolder> {
    FirebaseAuth mAuth;
    private AppCompatActivity mcontex;
    private List<DonatorInfo> mdata;

    public DonaterAdapter(AppCompatActivity mcontex, List<DonatorInfo> mdata) {
        this.mcontex = mcontex;
        this.mdata = mdata;
    }
    @NonNull
    @Override
    public DonaterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mcontex);
        view = layoutInflater.inflate(R.layout.user_doctor_design,parent,false);
        mAuth = FirebaseAuth.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonaterAdapter.ViewHolder holder, int i) {
        final DonatorInfo donatorInfo = mdata.get(i);
         holder.name.setText(donatorInfo.getDonatorName());
         holder.location.setText(donatorInfo.getDonatorLocation());
        Glide.with(mcontex).load(donatorInfo.getDonatorImage()).into(holder.userImage);
        if (donatorInfo.getDonatorDiseases() != null )
        holder.type.setText("Patient");
        else
            holder.type.setText("Doctor");
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,location,type;
        ImageView userImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name =(TextView)itemView.findViewById(R.id.CustomerName);
            location = (TextView)itemView.findViewById(R.id.CustomerLocation);
            userImage = (ImageView)itemView.findViewById(R.id.CustomerImage);
           type = (TextView)itemView.findViewById(R.id.CustomerMoreBid);
        }
    }
}
