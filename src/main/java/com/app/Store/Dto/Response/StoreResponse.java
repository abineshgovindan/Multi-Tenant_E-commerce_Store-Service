package com.app.Store.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class StoreResponse {
    private UUID storeId;
    private UUID adminId;
    private String storeName;
    private String storeLogo;
    private boolean archive;
    private boolean live;
    private String description;
}
