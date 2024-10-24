package com.vk.bucket.repository;

import com.vk.bucket.entity.BucketItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketItemsRepository extends JpaRepository<BucketItems, Integer> {
    //BucketItems findByItem(String item);
}
