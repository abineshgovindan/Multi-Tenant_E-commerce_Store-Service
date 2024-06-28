package com.app.Store.Dto.Mapper;

import com.app.Store.Dto.Response.StoreResponse;
import com.app.Store.Entity.Store;
import org.springframework.stereotype.Component;

@Component
public class StoreMapper {

    public static StoreResponse fromStore(Store store){
        return StoreResponse.builder()
                .storeId(store.getStoreId())
                .storeName(store.getStoreName())
                .storeLogo(store.getStoreLogo())
                .description(store.getDescription())
                .archive(store.isArchive())
                .live(store.isLive())
                .build();
    }
}
