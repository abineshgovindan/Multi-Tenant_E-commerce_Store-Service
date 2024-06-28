package com.app.Store.Controller;

import com.app.Store.Dto.Request.StoreRequest;
import com.app.Store.Dto.Response.StoreResponse;
import com.app.Store.Entity.Store;
import com.app.Store.Service.StoreServices.StoreServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreServices storeService;


    @PostMapping()
    public ResponseEntity<StoreResponse> createStore(@RequestBody @Valid StoreRequest request) {
        StoreResponse response = storeService.createStore(request);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<StoreResponse> getStoreById(@PathVariable UUID storeId) {
        StoreResponse response = storeService.getStoreById(storeId);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PutMapping("/{adminId}/{storeId}")
    public ResponseEntity<StoreResponse> updateStore(@PathVariable UUID storeId, @RequestBody @Valid StoreRequest request) {
        StoreResponse response = storeService.updateStore(storeId, request);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{adminId}/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable UUID storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("{adminId}/{storeId}/archive")
    public ResponseEntity<StoreResponse> archiveStore(@PathVariable UUID adminId,@PathVariable UUID storeId) {
        StoreResponse response = storeService.archiveStore(storeId, adminId);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{storeId}/alive")
    public ResponseEntity<StoreResponse> makeStoreAlive(@PathVariable UUID storeId) {
        StoreResponse response = storeService.makeStoreAlive(storeId);
        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAllStore/{adminId}")
    public ResponseEntity<List<StoreResponse>> getAllStoreForAdminId(@PathVariable UUID adminId){
        return ResponseEntity.ok(storeService.getAllStoreForAdminId(adminId));
    }



}
