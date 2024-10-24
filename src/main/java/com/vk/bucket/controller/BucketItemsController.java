package com.vk.bucket.controller;

import com.vk.bucket.entity.BucketItems;
import com.vk.bucket.repository.BucketItemsRepository;
import com.vk.bucket.service.BucketItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/")
public class BucketItemsController {
    private final BucketItemsService bs;
    private final BucketItemsRepository br;

    @Autowired
    public BucketItemsController(BucketItemsService bs, BucketItemsRepository br) {
        this.bs = bs;
        this.br = br;
    }

    @GetMapping("/fetch")
    public List<BucketItems> fetchItems(){
        return br.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody BucketItems item){
        if (item == null || item.getItem()==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Input: Item name is missing");
        }

        bs.save(item);
        return ResponseEntity.status(HttpStatus.OK).body("Item added successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        boolean isRemoved = bs.deleteById(id);

        if (!isRemoved) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully");
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> updateItem(@PathVariable int id, @RequestBody BucketItems updatedItem){
        if (updatedItem == null || updatedItem.getItem() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid Input: Item name is missing");
        }

        // Call the service to update the item
        boolean isUpdated = bs.updateItem(id, updatedItem.getItem());

        // Return appropriate response based on the update result
        if (!isUpdated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Item not found");
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body("Item updated successfully");
    }


}
