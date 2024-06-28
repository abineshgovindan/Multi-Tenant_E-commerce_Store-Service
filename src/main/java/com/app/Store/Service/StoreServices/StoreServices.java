package com.app.Store.Service.StoreServices;

import com.app.Store.Dto.Request.StoreRequest;
import com.app.Store.Dto.Response.StoreResponse;
import com.app.Store.Entity.Store;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface StoreServices {

    public StoreResponse createStore(StoreRequest request);
    public StoreResponse getStoreById(UUID storeId);
    public StoreResponse updateStore(UUID storeId, StoreRequest request);
    public void deleteStore(UUID storeId);
    public StoreResponse archiveStore(UUID storeId, UUID adminId);
    public StoreResponse makeStoreAlive(UUID storeId);

    public List<StoreResponse> getAllStoreForAdminId(UUID adminId);

}
