package com.befoys.drivers.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.befoys.R;
import com.befoys.core.models.Travel;
import com.befoys.drivers.activities.TravelActivity;

import java.util.List;

public class TravelAdapter extends RecyclerView.Adapter<TravelAdapter.ItemViewHolder> {
    private static List<Travel> dataList;
    private LayoutInflater mInflater;
    private Context context;

    public TravelAdapter(Context ctx, List<Travel> data) {
        context = ctx;
        dataList = data;
        mInflater = LayoutInflater.from(context);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView crdOrder;
        private TextView lblOrderName, lblOrderDatetime, lblOrderStatus, lblOrderCount, lblOrderAddress;

        public ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            crdOrder = itemView.findViewById(R.id.crdOrder);
            lblOrderName = itemView.findViewById(R.id.lblOrderName);
            lblOrderDatetime = itemView.findViewById(R.id.lblOrderDatetime);
            lblOrderStatus = itemView.findViewById(R.id.lblOrderStatus);
            lblOrderCount = itemView.findViewById(R.id.lblOrderCount);
            lblOrderAddress = itemView.findViewById(R.id.lblOrderAddress);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_travel_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        Travel travel = dataList.get(position);

        holder.lblOrderName.setText(travel.getId() + " - " +  travel.getName());
        holder.lblOrderDatetime.setText(travel.getStartDatetime());
        holder.lblOrderStatus.setText(context.getString(R.string.label_travel_status) + travel.getStatus().getName());
        holder.lblOrderCount.setText(context.getString(R.string.label_travel_product_count) + travel.getProductCount().toString());

        StringBuilder addressValue = new StringBuilder();
        for (int i = 0; i < travel.getTravelSteps().size(); i++) {
            addressValue.append((i + 1) + "- " + travel.getTravelSteps().get(i).getAddressValue());
            addressValue.append(System.getProperty("line.separator"));
        }
        holder.lblOrderAddress.setText(addressValue);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TravelActivity.class);
                intent.putExtra("travelId", travel.getId());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }

    private void onItemDismiss(int position) {
        dataList.remove(position);
        notifyItemRemoved(position);
    }

}