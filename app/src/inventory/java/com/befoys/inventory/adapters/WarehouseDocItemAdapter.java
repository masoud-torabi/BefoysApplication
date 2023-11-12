package com.befoys.inventory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.befoys.R;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.models.WarehouseDoc;
import com.befoys.core.models.WarehouseDocItem;
import com.befoys.core.utils.BaseConvert;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WarehouseDocItemAdapter extends RecyclerView.Adapter<WarehouseDocItemAdapter.ItemViewHolder> {
    private static List<WarehouseDocItem> dataList;
    private LayoutInflater mInflater;
    private Context context;

    public WarehouseDocItemAdapter(Context ctx, List<WarehouseDocItem> data) {
        context = ctx;
        dataList = data;
        mInflater = LayoutInflater.from(context);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView crdOrderItem;
        private TextView lblOrderProductName, lblOrderCount;
        private ImageView imgOrderItem;

        public ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            crdOrderItem = itemView.findViewById(R.id.crdOrderItem);
            lblOrderProductName = itemView.findViewById(R.id.lblOrderProductName);
            lblOrderCount = itemView.findViewById(R.id.lblOrderCount);
            imgOrderItem = itemView.findViewById(R.id.imgOrderItem);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_product_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        WarehouseDocItem entity = dataList.get(position);

        holder.lblOrderProductName.setText(entity.getProduct().getId() + " - " + entity.getProduct().getName());
        holder.lblOrderCount.setText(
                context.getResources().getString(R.string.label_count) +
                BaseConvert.toPersian(entity.getCount()) + " " +
                entity.getProduct().getUnitName());

        if (entity.getProduct().getPicture() != null)
        {
            Picasso.get()
                    .load(entity.getProduct().getPicture().getUrl())
                    .into(holder.imgOrderItem);
        }
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
