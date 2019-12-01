package com.example.realestateagencys.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.realestateagencys.R;
import com.example.realestateagencys.ui.model.HouseType;
import com.example.realestateagencys.ui.view.HouseTypes;

import java.util.List;

public class HouseTypeAdapter extends RecyclerView.Adapter<HouseTypeAdapter.HouseTypeViewHolder> {

    private Context context;
    private List<HouseType> housetypes;
    private Activity activity;

    public HouseTypeAdapter(Context context, List<HouseType> housetypes, Activity activity) {
        this.context = context;
        this.housetypes = housetypes;
        this.activity = activity;
    }

    @Override
    public HouseTypeAdapter.HouseTypeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate( R.layout.activity_house_type_item, viewGroup, false);
        return new HouseTypeAdapter.HouseTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HouseTypeAdapter.HouseTypeViewHolder houseTypeViewHolder, int i) {
        final HouseType houseType = housetypes.get(i);
        houseTypeViewHolder.house_type_name.setText(houseType.getName());
        houseTypeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCarFilterActivity(houseType);
            }
        });
    }

    private void openCarFilterActivity(HouseType houseType) {
        Intent returnToCarFilter = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("housetype", houseType);
        returnToCarFilter.putExtras(bundle);
        activity.setResult(Activity.RESULT_OK, returnToCarFilter);
        activity.finish();
    }

    @Override
    public int getItemCount() {
        return housetypes.size();
    }

    public class HouseTypeViewHolder extends RecyclerView.ViewHolder {

        public TextView house_type_name;

        public HouseTypeViewHolder(View itemView) {
            super(itemView);
            house_type_name = itemView.findViewById(R.id.house_type_name);
        }
    }

}
