package com.befoys.drivers.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static android.Manifest.permission.CALL_PHONE;

import com.befoys.R;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.models.DriverFavorite;

import java.util.List;

public class DriverFavoriteAdapter extends RecyclerView.Adapter<DriverFavoriteAdapter.ItemViewHolder> {
    private static List<DriverFavorite> dataList;
    private LayoutInflater mInflater;
    private Context context;
    EntityComponent _component;
    private OnCallListener callListener;

    public interface OnCallListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnCallButtonListener(OnCallListener listener) {
        this.callListener = listener;
    }

    public DriverFavoriteAdapter(Context ctx, EntityComponent component, List<DriverFavorite> data) {
        context = ctx;
        _component = component;
        dataList = data;
        mInflater = LayoutInflater.from(context);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView crdFavorite;
        private TextView lblName, lblPhone, lblAddressValue;
        private ImageView btnPoint, btnFavorite, btnCall;
        private Boolean IsFavorite = true;

        public ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            crdFavorite = itemView.findViewById(R.id.crdFavorite);
            lblName = itemView.findViewById(R.id.lblName);
            lblPhone = itemView.findViewById(R.id.lblPhone);
            lblAddressValue = itemView.findViewById(R.id.lblAddressValue);
            btnPoint = itemView.findViewById(R.id.btnPoint);
            btnFavorite = itemView.findViewById(R.id.btnFavorite);
            btnCall = itemView.findViewById(R.id.btnCall);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_favorite_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        DriverFavorite entity = dataList.get(position);

        holder.lblName.setText(entity.getName());
        holder.lblPhone.setText(context.getString(R.string.phone) + entity.getPhone());
        holder.lblAddressValue.setText(context.getString(R.string.address) + entity.getAddressValue());

        holder.btnPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String directionLink = "https://www.google.com/maps/dir//";
                directionLink += entity.getLatitude().toString() + ",";
                directionLink += entity.getLongitude().toString() + "/";

                Intent browser = new Intent(Intent.ACTION_VIEW);
                browser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                browser.setData(Uri.parse(directionLink));
                context.startActivity(browser);
            }
        });

        holder.btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        callListener.onItemClick(holder.btnCall, position);
                    }
                }
            }
        });

        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _component.getDriverFavoriteModule().toggleDriverFavorite(entity, null);

                if (holder.IsFavorite == true) {
                    holder.btnFavorite.setBackgroundResource(R.drawable.icon_star_empty);
                    holder.IsFavorite = false;
                } else {
                    holder.btnFavorite.setBackgroundResource(R.drawable.icon_star_completed);
                    holder.IsFavorite = true;
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
