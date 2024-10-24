package com.vk.bucket.service;

import com.vk.bucket.entity.BucketItems;
import com.vk.bucket.repository.BucketItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BucketItemsService {
    private final BucketItemsRepository br;

    @Autowired
    public BucketItemsService(BucketItemsRepository br) {
        this.br = br;
    }

    //method to save an item
    public void save(BucketItems item) {
        br.save(item);
    }

    //method to delete by ID
    public boolean deleteById(int id) {
        BucketItems item = br.findById(id).orElse(null);
        if(item != null) {
            br.delete(item);
            return true;
        }
        return false;
    }

    // method to edit an item
    public boolean updateItem(int id, String newName){
        BucketItems existingItem = br.findById(id).orElse(null);
        if(existingItem != null) {
            existingItem.setItem(newName);
            br.save(existingItem);
            return true;
        }
        return false;
    }




}
