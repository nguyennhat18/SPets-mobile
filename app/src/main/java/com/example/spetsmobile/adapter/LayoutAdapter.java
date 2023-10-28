package com.example.spetsmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spetsmobile.R;
import com.example.spetsmobile.activity.VaccineFormActivity;
import com.example.spetsmobile.api.APIClient;
import com.example.spetsmobile.model.response.VaccineResponse;
import com.example.spetsmobile.util.ConstantUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LayoutAdapter extends RecyclerView.Adapter<LayoutAdapter.PetViewHolder> {

    private Context context;
    private List responseList;

    public LayoutAdapter(Context context, List petList) {
        this.context = context;
        this.responseList = responseList;
    }

    @NonNull
    @Override
    public PetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new PetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetViewHolder holder, int position) {
        VaccineResponse response = (VaccineResponse) responseList.get(position);

        // Hiển thị thông tin
        holder.tvTitle.setText(response.getName());
        holder.tvDesc.setText(response.getDate());

//        holder.btnLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ConstantUtil.setVaccineResponse((VaccineResponse) responseList.get(position));
//
//                Intent intent = new Intent(context, VaccineFormActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        if (responseList == null) {
            return 0;
        }

        return responseList.size();
    }

    // Phương thức để cập nhật danh sách thú cưng
    public void setDataList(List responseList) {
        this.responseList = responseList;
        notifyDataSetChanged(); // Thông báo cho Adapter cập nhật giao diện
    }

    public class PetViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDesc;

        public PetViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }

    }

}
