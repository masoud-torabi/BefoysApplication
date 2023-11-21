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
import com.befoys.core.models.WarehouseDocItem;
import com.befoys.core.models.WarehouseHandlingNoItem;
import com.befoys.core.utils.BaseConvert;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WarehouseHandlingNoItemAdapter extends RecyclerView.Adapter<WarehouseHandlingNoItemAdapter.ItemViewHolder> {
    private static List<WarehouseHandlingNoItem> dataList;
    private LayoutInflater mInflater;
    private Context context;
    private OnRowItemListener rowListener;

    public interface OnRowItemListener {
        void onItemClick(View itemView, WarehouseHandlingNoItem selectedItem);
    }

    public void setOnRowItemListener(OnRowItemListener listener) {
        this.rowListener = listener;
    }

    public WarehouseHandlingNoItemAdapter(Context ctx, List<WarehouseHandlingNoItem> data) {
        context = ctx;
        dataList = data;
        mInflater = LayoutInflater.from(context);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView crdOrderItem;
        private TextView lblProductName, lblUnitName, lblCountInUnit;
        private ImageView imgOrderItem;

        public ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            crdOrderItem = itemView.findViewById(R.id.crdOrderItem);
            lblProductName = itemView.findViewById(R.id.lblProductName);
            lblUnitName = itemView.findViewById(R.id.lblUnitName);
            lblCountInUnit = itemView.findViewById(R.id.lblCountInUnit);
            imgOrderItem= itemView.findViewById(R.id.imgOrderItem);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public WarehouseHandlingNoItemAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_warehouse_item, parent, false);
        WarehouseHandlingNoItemAdapter.ItemViewHolder itemViewHolder = new WarehouseHandlingNoItemAdapter.ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final WarehouseHandlingNoItemAdapter.ItemViewHolder holder, final int position) {
        WarehouseHandlingNoItem entity = dataList.get(position);

        if (entity.getProduct() != null) {
            holder.lblProductName.setText(entity.getProduct().getId() + " - " + entity.getProduct().getName());
            holder.lblUnitName.setText(entity.getProduct().getUnitName());
            if (entity.getProduct().getPicture() != null)
            {
                Picasso.get()
                        .load(entity.getProduct().getPicture().getUrl())
                        .into(holder.imgOrderItem);
            }
        }
        //holder.lblCountInUnit.setText(entity.getProduct().get);

        holder.crdOrderItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rowListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        rowListener.onItemClick(holder.crdOrderItem, entity);
                    }
                }
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
