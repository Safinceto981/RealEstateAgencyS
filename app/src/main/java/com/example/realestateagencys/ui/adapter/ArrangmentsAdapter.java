package com.example.realestateagencys.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.model.HouseArrangment;
import com.example.realestateagencys.ui.view.MyArrangmentsDetails;

import java.util.List;

public class ArrangmentsAdapter extends RecyclerView.Adapter<ArrangmentsAdapter.ArrangmentsViewHolder> {

    private Context context;
    private List<HouseArrangment> arrangments;
    private Activity activity;

    public ArrangmentsAdapter(Context context, List<HouseArrangment> arrangments, Activity activity) {
        this.arrangments = arrangments;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public ArrangmentsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.booking_date, viewGroup, false);
        return new ArrangmentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArrangmentsViewHolder arrangmentsViewHolder, int i) {
        final HouseArrangment arrangment = arrangments.get(i);
        arrangmentsViewHolder.arrangment_house.setText(arrangment.getHouse().getName());
        arrangmentsViewHolder.arrangment_date.setText(arrangment.getArrangmentDate());
        arrangmentsViewHolder.arrangment_status.setImageResource(getStatus(arrangment.isComplete()));
        arrangmentsViewHolder.arrangment_area.setText(arrangment.getArea().getAddress());
        arrangmentsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTripRefunds(arrangment);
            }
        });
    }

    private int getStatus(boolean isComplete) {
        if (isComplete) {
            return R.drawable.ic_check_black_24dp;
        }
        return R.drawable.ic_hourglass_empty_black_24dp;
    }

    private void showTripRefunds(HouseArrangment arrangment) {
        Intent intentToBookingDetails = new Intent(activity, MyArrangmentsDetails.class);
        intentToBookingDetails.putExtra("arrangment", arrangment);
        activity.startActivity(intentToBookingDetails);
    }

    @Override
    public int getItemCount() {
        return arrangments.size();
    }

    public class ArrangmentsViewHolder extends RecyclerView.ViewHolder {

        public TextView arrangment_date;
        public TextView arrangment_house;
        public ImageView arrangment_status;
        public TextView arrangment_area;

        public ArrangmentsViewHolder(View itemView) {
            super(itemView);
            arrangment_date = itemView.findViewById(R.id.arrangment_date);
            arrangment_house = itemView.findViewById(R.id.arrangment_house);
            arrangment_house = itemView.findViewById(R.id.arrangment_status);
            arrangment_area = itemView.findViewById(R.id.arrangment_area);
        }
    }

}