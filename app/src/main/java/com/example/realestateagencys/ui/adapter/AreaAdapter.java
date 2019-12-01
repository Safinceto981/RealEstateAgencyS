package com.example.realestateagencys.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.realestateagencys.ui.housearrangment.HouseOptionsFilter;
import com.example.realestateagencys.ui.model.Areah;

import androidx.recyclerview.widget.RecyclerView;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.model.Areah;

import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.AreaViewHolder> {

    private static final String SEPARATOR = ", ";
    private Context context;
    private List<Areah> areas;

    public AreaAdapter(Context context, List<Areah> areas) {
        this.context = context;
        this.areas = areas;
    }

    @Override
    public AreaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_area_item, viewGroup, false);
        return new AreaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final AreaViewHolder areaViewHolder, int i) {
        final Areah area = areas.get(i);
        areaViewHolder.area_address.setText(area.getAddress());
        areaViewHolder.area_city_and_state.setText(area.getCity().concat(SEPARATOR).concat(area.getState()));
        areaViewHolder.area_distance.setText(area.getDistance() + "");
        areaViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSelectedAreaDetails(area);
            }
        });
    }

    private void openSelectedAreaDetails(Areah area) {
        Intent intent = new Intent(context, HouseOptionsFilter.class);
        intent.putExtra("selectedHouseDetails", area);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return areas.size();
    }

    public class AreaViewHolder extends RecyclerView.ViewHolder {

        public TextView area_address;
        public TextView area_city_and_state;
        public TextView area_distance;

        public AreaViewHolder(View itemView) {
            super(itemView);
            area_address = itemView.findViewById(R.id.area_address);
            area_city_and_state = itemView.findViewById(R.id.area_city_and_state);
            area_distance = itemView.findViewById(R.id.area_distance);
        }
    }

}