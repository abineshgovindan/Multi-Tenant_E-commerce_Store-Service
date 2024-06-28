package com.app.Store.FeignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@FeignClient(name = "ADMIN-SERVICE")
public interface AdminServiceClient {

    @GetMapping("api/v1/admin/canCreateStore/{adminId}")
    boolean canAdminCreateStore(@PathVariable("adminId") UUID adminId);
}
