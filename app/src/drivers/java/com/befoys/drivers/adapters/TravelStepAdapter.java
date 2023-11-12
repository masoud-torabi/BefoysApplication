package com.befoys.drivers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.befoys.R;
import com.befoys.core.enums.Enum_TravelStatus;
import com.befoys.core.models.TravelStep;

import java.util.List;

public class TravelStepAdapter extends RecyclerView.Adapter<TravelStepAdapter.ItemViewHolder> {
    private static List<TravelStep> dataList;
    private LayoutInflater mInflater;
    private Context context;

    private OnStartButtonListener startListener;
    private OnEndButtonListener endListener;
    private OnDirectionButtonListener directionListener;
    private OnFavoriteButtonListener favoriteListener;
    private OnPointButtonListener pointListener;
    private OnCallButtonListener callListener;

    public interface OnStartButtonListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnEndButtonListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnDirectionButtonListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnFavoriteButtonListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnPointButtonListener {
        void onItemClick(View itemView, int position);
    }

    public interface OnCallButtonListener {
        void onItemClick(View itemView, int position);
    }

    public void setOnStartClickListener(OnStartButtonListener listener) {
        this.startListener = listener;
    }

    public void setOnEndClickListener(OnEndButtonListener listener) {
        this.endListener = listener;
    }

    public void setOnDirectionClickListener(OnDirectionButtonListener listener) {
        this.directionListener = listener;
    }

    public void setOnFavoriteButtonListener(OnFavoriteButtonListener listener) {
        this.favoriteListener = listener;
    }

    public void setOnPointButtonListener(OnPointButtonListener listener) {
        this.pointListener = listener;
    }

    public void setOnCallButtonListener(OnCallButtonListener listener) {
        this.callListener = listener;
    }

    public TravelStepAdapter(Context ctx, List<TravelStep> data) {
        context = ctx;
        dataList = data;
        mInflater = LayoutInflater.from(context);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView crdOrderStep;
        private TextView lblOrderStepName, lblOrderSourceName, lblOrderSourceAddress;
        private Button btnDirection, btnStart, btnEnd;
        private ImageView btnPointSource, btnFavoriteSource, btnCallSource;
        private Boolean isSourceFavorite = false;
        private Boolean isDestinationFavorite = false;

        public ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            crdOrderStep = itemView.findViewById(R.id.crdOrderStep);
            lblOrderStepName = itemView.findViewById(R.id.lblOrderStepName);
            lblOrderSourceName = itemView.findViewById(R.id.lblOrderSourceName);
            lblOrderSourceAddress = itemView.findViewById(R.id.lblOrderSourceAddress);
            btnDirection = itemView.findViewById(R.id.btnDirection);
            btnStart = itemView.findViewById(R.id.btnStart);
            btnEnd = itemView.findViewById(R.id.btnEnd);

            btnPointSource = itemView.findViewById(R.id.btnPointSource);
            btnFavoriteSource = itemView.findViewById(R.id.btnFavoriteSource);
            btnCallSource = itemView.findViewById(R.id.btnCallSource);
        }

        @Override
        public void onClick(View view) {

        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_travel_step_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        TravelStep entity = dataList.get(position);

        holder.lblOrderStepName.setText("سفارش شماره #" + entity.getResellerOrderId());

        holder.lblOrderSourceName.setText(holder.lblOrderSourceName.getText() + entity.getName());
        holder.lblOrderSourceAddress.setText(entity.getAddressValue());

        if (entity.getFavorite() == true) {
            holder.btnFavoriteSource.setBackgroundResource(R.drawable.icon_star_completed);
            holder.isSourceFavorite = true;
        }

        if (position == 0) {
            if (entity.getStatus().getLabel().equals(Enum_TravelStatus.TRAVEL_STATUS_INSERTED.toString())) {
                holder.btnStart.setVisibility(View.VISIBLE);
                holder.btnEnd.setVisibility(View.GONE);
            } else if (entity.getStatus().getLabel().equals(Enum_TravelStatus.TRAVEL_STATUS_STARTED.toString())) {
                holder.btnStart.setVisibility(View.GONE);
                holder.btnEnd.setVisibility(View.VISIBLE);
            } else if (entity.getStatus().getLabel().equals(Enum_TravelStatus.TRAVEL_STATUS_DONE.toString()) ||
                       entity.getStatus().getLabel().equals(Enum_TravelStatus.TRAVEL_STATUS_CANCELED.toString())) {
                holder.btnStart.setVisibility(View.GONE);
                holder.btnEnd.setVisibility(View.GONE);
            }
        } else {
            if (entity.getStatus().getLabel().equals(Enum_TravelStatus.TRAVEL_STATUS_INSERTED.toString())) {
                holder.btnStart.setVisibility(View.GONE);
                holder.btnEnd.setVisibility(View.GONE);
            } else if (entity.getStatus().getLabel().equals(Enum_TravelStatus.TRAVEL_STATUS_STARTED.toString())) {
                holder.btnStart.setVisibility(View.GONE);
                holder.btnEnd.setVisibility(View.VISIBLE);
            } else if (entity.getStatus().getLabel().equals(Enum_TravelStatus.TRAVEL_STATUS_DONE.toString()) ||
                    entity.getStatus().getLabel().equals(Enum_TravelStatus.TRAVEL_STATUS_CANCELED.toString())) {
                holder.btnStart.setVisibility(View.GONE);
                holder.btnEnd.setVisibility(View.GONE);
            }
        }

        holder.btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (directionListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        directionListener.onItemClick(holder.btnDirection, position);
                    }
                }
            }
        });
        holder.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (startListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        startListener.onItemClick(holder.btnStart, position);
                    }
                }
            }
        });
        holder.btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (endListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        endListener.onItemClick(holder.btnEnd, position);
                    }
                }
            }
        });
        holder.btnPointSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pointListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        pointListener.onItemClick(holder.btnPointSource, position);
                    }
                }
            }
        });
        holder.btnCallSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        callListener.onItemClick(holder.btnCallSource, position);
                    }
                }
            }
        });
        holder.btnFavoriteSource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (favoriteListener != null) {
                    if (position != RecyclerView.NO_POSITION) {
                        favoriteListener.onItemClick(holder.btnFavoriteSource, position);

                        if (holder.isSourceFavorite == true) {
                            holder.btnFavoriteSource.setBackgroundResource(R.drawable.icon_star_empty);
                            holder.isSourceFavorite = false;
                        } else {
                            holder.btnFavoriteSource.setBackgroundResource(R.drawable.icon_star_completed);
                            holder.isSourceFavorite = true;
                        }
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