package com.app.Store.Service;

import com.app.Store.Dto.Mapper.StoreMapper;
import com.app.Store.Dto.Request.StoreRequest;
import com.app.Store.Dto.Response.StoreResponse;
import com.app.Store.Entity.Store;
import com.app.Store.Expection.StoreNotFoundException;
import com.app.Store.Expection.UnauthorizedException;
import com.app.Store.FeignClient.AdminServiceClient;
import com.app.Store.Repository.StoreRepository;
import com.app.Store.Service.StoreServices.StoreServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreServicesImp implements StoreServices {
    private final StoreRepository storeRepository;
    private final AdminServiceClient adminServiceClient;
    private final StoreMapper storeMapper;

    @Override
    public StoreResponse createStore(StoreRequest request) {

        if(!adminServiceClient.canAdminCreateStore(request.getAdminId())){
            throw new UnauthorizedException("Admin does not have permission to manage stores.");
        }
        Store store = Store.builder()
                .adminId(request.getAdminId())
                .storeName(request.getStoreName())
                .storeLogo(request.getStoreLogo())
                .description(request.getDescription())
                .archive(false)
                .live(false)
                .build();
        Store savedStore = storeRepository.save(store);
        if(savedStore == null){
            return null;
        }
        StoreResponse storeResponse = StoreResponse.builder()
                .storeId(savedStore.getStoreId())
                .storeName(savedStore.getStoreName())
                .storeLogo(savedStore.getStoreLogo())
                .archive(savedStore.isArchive())
                .live(savedStore.isLive())
                .description(savedStore.getDescription())
                .adminId(savedStore.getAdminId())
                .build();

        return storeResponse;


    }


    @Override
    public StoreResponse getStoreById(UUID storeId) {
        Optional<Store> optionalStore = storeRepository.findById(storeId);
        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            return StoreResponse.builder()
                    .storeId(store.getStoreId())
                    .adminId(store.getAdminId())
                    .storeName(store.getStoreName())
                    .storeLogo(store.getStoreLogo())
                    .archive(store.isArchive())
                    .live(store.isLive())
                    .description(store.getDescription())
                    .build();
        } else {
            return null; // or throw exception indicating store not found
        }
    }

    @Override
    public StoreResponse updateStore(UUID storeId, StoreRequest request) {
        Optional<Store> optionalStore = storeRepository.findById(storeId);
        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            // Update store details based on request
            store.setStoreName(request.getStoreName());
            store.setStoreLogo(request.getStoreLogo());
            store.setDescription(request.getDescription());
            // Save the updated store
            Store updatedStore = storeRepository.save(store);
            // Return the updated store response
            return StoreResponse.builder()
                    .storeId(updatedStore.getStoreId())
                    .adminId(updatedStore.getAdminId())
                    .storeName(updatedStore.getStoreName())
                    .storeLogo(updatedStore.getStoreLogo())
                    .archive(updatedStore.isArchive())
                    .live(updatedStore.isLive())
                    .description(updatedStore.getDescription())
                    .build();
        } else {
            return null; // or throw exception indicating store not found
        }
    }
    @Override
    public void deleteStore(UUID storeId) {
        storeRepository.deleteById(storeId);
    }

    @Override
    public StoreResponse archiveStore(UUID storeId, UUID adminId) {
        Optional<Store> optionalStore = storeRepository.findById(storeId);

        if (optionalStore.isPresent()) {
            Store store = optionalStore.get();
            if (store.getAdminId() != adminId){
                throw new UnauthorizedException("Your not authorized");
            };
            store.setArchive(true);
            store.setLive(false);
            System.out.println("Stat --------"+ store.toString());
            Store archivedStore = storeRepository.save(store);
            return StoreResponse.builder()
                    .storeId(archivedStore.getStoreId())
                    .adminId(archivedStore.getAdminId())
                    .storeName(archivedStore.getStoreName())
                    .storeLogo(archivedStore.getStoreLogo())
                    .archive(archivedStore.isArchive())
                    .live(archivedStore.isLive())
                    .description(archivedStore.getDescription())
                    .build();
        } else {
            return null; // or throw exception indicating store not found
        }
    }

    @Override
    public StoreResponse makeStoreAlive(UUID storeId) {
            Optional<Store> optionalStoreData = storeRepository.findById(storeId);
            if (optionalStoreData.isPresent()) {
                Store store = optionalStoreData.get();
                store.setArchive(false);
                store.setLive(true);
                Store liveStore = storeRepository.save(store);
                return StoreResponse.builder()
                        .storeId(liveStore.getStoreId())
                        .adminId(liveStore.getAdminId())
                        .storeName(liveStore.getStoreName())
                        .storeLogo(liveStore.getStoreLogo())
                        .archive(liveStore.isArchive())
                        .live(liveStore.isLive())
                        .description(liveStore.getDescription())
                        .build();
            } else {
                return null; // or throw exception indicating store not found
            }
        }

    @Override
    public List<StoreResponse> getAllStoreForAdminId(UUID adminId) {
       List<StoreResponse> storeResponseList =  storeRepository.findByAdminId(adminId).stream()
                .map(StoreMapper::fromStore)
                .collect(Collectors.toList());

        if (storeResponseList.isEmpty()){
            throw new StoreNotFoundException("No Store is Found For the Admin ID");
        }
        return storeResponseList;
    }


}




