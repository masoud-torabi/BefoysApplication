package com.befoys.core.modules;


import com.befoys.core.enums.Enum_Api;
import com.befoys.core.enums.Enum_RequestType;
import com.befoys.core.models.WarehouseHandlingNoItem;
import com.befoys.core.viewmodel.ViewWarehouseHandlingPositionItem;
import com.befoys.core.webservice.base.ApiParameter;
import com.befoys.core.webservice.base.ApiResultListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WarehouseHandlingNoItemModule extends BaseModule<WarehouseHandlingNoItem> {
    @Provides
    @Singleton
    WarehouseHandlingNoItemModule getWarehouseHandlingNoItemModuleContext() { return new WarehouseHandlingNoItemModule();  }

    @Inject
    public void search(int noId, String product, String barcode, Integer shelfId, ApiResultListener result) {
        List<ApiParameter> params = new ArrayList();
        params.add(new ApiParameter("id", noId));
        if (product != null && product != "" && product.equals("") == false) {
            params.add(new ApiParameter("product", product));
        }
        if (barcode != null && barcode != "" && barcode.equals("") == false) {
            params.add(new ApiParameter("barcode", barcode));
        }
        if (shelfId != null) {
            params.add(new ApiParameter("shelfId", shelfId));
        }
        params.add(new ApiParameter("hasCount", false));
        createApiRequest(Enum_RequestType.GET, Enum_Api.WAREHOUSE_HANDLING_NO_ITEM, result, params);
    }

    @Inject
    public void doCount(WarehouseHandlingNoItem item, ApiResultListener result) {
        createApiRequest(Enum_RequestType.POST, Enum_Api.WAREHOUSE_HANDLING_NO_ITEM, result, null, item);
    }
}
