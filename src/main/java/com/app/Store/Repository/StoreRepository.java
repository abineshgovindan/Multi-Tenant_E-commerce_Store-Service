package com.app.Store.Repository;


import com.app.Store.Dto.Response.StoreResponse;
import com.app.Store.Entity.Store;
import org.bouncycastle.LICENSE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StoreRepository extends JpaRepository<Store, UUID> {
    List<Store> findByAdminId(UUID adminId);
}
