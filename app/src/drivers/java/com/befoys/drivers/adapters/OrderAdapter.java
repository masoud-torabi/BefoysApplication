package com.befoys.drivers.adapters;

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
import com.befoys.core.models.DriverOrderItem;
import com.befoys.core.utils.BaseConvert;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ItemViewHolder> implements View.OnLongClickListener {
    private static List<DriverOrderItem> dataList;
    private LayoutInflater mInflater;
    private Context context;
    private EntityComponent _component;
    private OnCallListener callListener;
    private OnLongClickListener longClickListener;

    @Override
    public boolean onLongClick(View view) {
        return true;
    }

    public interface OnLongClickListener {
        boolean onLongClick(View itemView, int position);
    }

    public interface OnCallListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnCallButtonListener(OnCallListener listener) {
        this.callListener = listener;
    }

    public void setOnLongClickListener(OnLongClickListener listener) {
        this.longClickListener = listener;
    }

    public OrderAdapter(Context ctx, EntityComponent component, ArrayList<DriverOrderItem> data) {
        context = ctx;
        _component = component;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_order_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        DriverOrderItem entity = dataList.get(position);

        holder.lblOrderProductName.setText(entity.getProduct().getName());
        holder.lblOrderCount.setText(BaseConvert.toPersian(entity.getCount()));

        if (entity.getProduct().getPicture() != null)
        {
            Picasso.get()
                    .load(entity.getProduct().getPicture().getUrl())
                    .into(holder.imgOrderItem);
        }

        holder.crdOrderItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (longClickListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        longClickListener.onLongClick(holder.crdOrderItem, position);
                    }
                }
                return false;
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
