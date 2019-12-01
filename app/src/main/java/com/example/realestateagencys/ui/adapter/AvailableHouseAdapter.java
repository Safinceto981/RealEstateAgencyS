package com.example.realestateagencys.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.housearrangment.HouseArrangmentDashboard;
import com.example.realestateagencys.ui.model.Areah;
import com.example.realestateagencys.ui.model.House;

import java.util.List;

public class AvailableHouseAdapter extends RecyclerView.Adapter<AvailableHouseAdapter.AvailableHouseViewHolder> {


    private Areah selectedArea;
    private String selectedDate;

    private Context context;
    private List<House> availableHouses;
    private Activity activity;

    public AvailableHouseAdapter(Context context, List<House> availableHouses, Activity activity, Areah selectedArea, String selectedDate
                             ) {
        this.availableHouses = availableHouses;
        this.context = context;
        this.selectedArea = selectedArea;
        this.selectedDate = selectedDate;
        this.activity = activity;

    }

    @Override
    public AvailableHouseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_house_item, viewGroup, false);
        return new AvailableHouseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AvailableHouseViewHolder availableHouseViewHolder, int i) {
        final House availableHouse = availableHouses.get(i);
        availableHouseViewHolder.house_name.setText(availableHouse.getName());


        availableHouseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showArrangmentDashboard(availableHouse);
            }
        });
    }

    private void showArrangmentDashboard(House availableHouse) {
        Intent intentToHouseArangment = new Intent(activity, HouseArrangmentDashboard.class);
        intentToHouseArangment.putExtra("selectedArea", selectedArea);
        intentToHouseArangment.putExtra("selectedHouse",availableHouse);

        intentToHouseArangment.putExtra("selectedDate", selectedDate);

        activity.startActivity(intentToHouseArangment);
    }

    @Override
    public int getItemCount() {
        return availableHouses.size();
    }

    public class AvailableHouseViewHolder extends RecyclerView.ViewHolder {

        public TextView house_name;
        public TextView house_type;


        public AvailableHouseViewHolder(View itemView) {
            super(itemView);
            house_name = itemView.findViewById(R.id.house_name);
            house_type = itemView.findViewById(R.id.house_type);
        }
    }

}
