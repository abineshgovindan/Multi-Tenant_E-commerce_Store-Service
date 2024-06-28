package com.app.Store.Dto.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor(staticName = "build")
@NoArgsConstructor
@Builder
public class StoreRequest {

    @NotNull(message = "Enter a valid Employee Id")
    private UUID adminId;

    @NotBlank(message = "Enter the valid storeName .")
    private String storeName;

    @NotBlank(message = "Enter the valid storeLogo .")
    private String storeLogo;



    @Size(min = 10, max = 200, message = "Description should have a length between 10 and 100 characters." )
    @NotBlank(message = "Enter the valid  description .")
    private String description;
}
