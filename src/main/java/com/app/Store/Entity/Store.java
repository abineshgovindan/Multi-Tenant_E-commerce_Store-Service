package com.app.Store.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
@Table(name = "Store")
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Builder
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "storeId", nullable = false, updatable = false, unique = true)
    private UUID storeId;

    @Column(name = "adminId", nullable = false, unique = false)
    private UUID adminId;

    @Column(name = "storeName")
    private String storeName;

    @Column(name = "storeLogo")
    private String storeLogo;
    @Column(name = "archive")
    private boolean archive;
    @Column(name = "live")
    private boolean live;

    @Column(name = "description")
    private String description;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;


    @PrePersist
    public void onPrePersist() {
        this.setCreateDate(LocalDateTime.now());
        this.setUpdateDate(LocalDateTime.now());
    }

    @PreUpdate
    public void onPreUpdate() {
        this.setUpdateDate(LocalDateTime.now());
    }






}

