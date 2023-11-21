package com.befoys.inventory.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.befoys.R;
import com.befoys.core.dagger.EntityComponent;
import com.befoys.core.enums.Enum_ApiResultStatus;
import com.befoys.core.models.WarehouseHandlingNoItem;
import com.befoys.core.modules.BaseModule;
import com.befoys.core.viewmodel.ViewWarehouseHandlingPositionItem;
import com.befoys.core.webservice.base.ApiResult;
import com.befoys.core.webservice.base.ApiResultListener;

import java.util.ArrayList;
import java.util.List;

public class WarehouseHandlingPositionAdapter extends RecyclerView.Adapter<WarehouseHandlingPositionAdapter.ItemViewHolder> {
    private static List<ViewWarehouseHandlingPositionItem> dataList;
    private LayoutInflater mInflater;
    private Context context;
    private OnRowListener rowListener;
    private WarehouseHandlingPositionAdapter thisClass;
    EntityComponent _component;
    String _barcode;
    Integer _noId;

    public interface OnRowListener {
        void onItemClick(View itemView, WarehouseHandlingNoItem selectedItem);
    }

    public void setOnRowItemListener(OnRowListener listener) {
        this.rowListener = listener;
    }

    public WarehouseHandlingPositionAdapter(Context ctx, EntityComponent component, String barcode, Integer noId, List<ViewWarehouseHandlingPositionItem> data) {
        context = ctx;
        dataList = data;
        mInflater = LayoutInflater.from(context);
        thisClass = this;
        _component = component;
        _barcode = barcode;
        _noId = noId;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView crdOrderItem;
        private TextView lblPositionName;
        private RecyclerView recyclerInner;

        public ItemViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            crdOrderItem = itemView.findViewById(R.id.crdOrderItem);
            lblPositionName = itemView.findViewById(R.id.lblPositionName);
            recyclerInner = itemView.findViewById(R.id.recyclerInner);
        }

        @Override
        public void onClick(View view) {
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.partial_warehouse_position_item, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        ViewWarehouseHandlingPositionItem entity = dataList.get(position);
        holder.lblPositionName.setText(entity.getPositionName());

        if (_barcode == null ||
            _barcode == "" ||
            _barcode.equals("")) {
            holder.lblPositionName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    _component.getWarehouseHandlingNoItemModule().search(_noId, null, null, entity.getShelfId(), new ApiResultListener() {
                        @Override
                        public void onSuccess(ApiResult result) {
                            ArrayList<WarehouseHandlingNoItem> list = BaseModule.castObjectList((ArrayList)result.getValue(), WarehouseHandlingNoItem.class);
                            entity.setItems(list);
                            bindSubAdapter(holder, entity);
                        }

                        @Override
                        public void onFailure(Enum_ApiResultStatus status, String message) {

                        }
                    });
                }
            });
        } else {
            bindSubAdapter(holder, entity);
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

    private void bindSubAdapter(ItemViewHolder holder, ViewWarehouseHandlingPositionItem entity) {
        WarehouseHandlingNoItemAdapter adapter = new WarehouseHandlingNoItemAdapter(context, entity.getItems());

        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyclerInner.setLayoutManager(layoutManager);
        holder.recyclerInner.setNestedScrollingEnabled(false);
        holder.recyclerInner.setHasFixedSize(false);
        holder.recyclerInner.setAdapter(adapter);

        adapter.setOnRowItemListener(new WarehouseHandlingNoItemAdapter.OnRowItemListener() {
            @Override
            public void onItemClick(View itemView, WarehouseHandlingNoItem selectedItem) {
                thisClass.rowListener.onItemClick(itemView, selectedItem);
            }
        });
    }
}
