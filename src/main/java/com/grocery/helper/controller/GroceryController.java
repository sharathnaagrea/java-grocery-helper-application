package com.grocery.helper.controller;

import com.grocery.helper.exception.ResourceNotFound;
import com.grocery.helper.model.Grocery;
import com.grocery.helper.repository.GroceryReposiitory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class GroceryController {

    @Autowired
    GroceryReposiitory groceryReposiitory;

    // get all groceries
    @GetMapping("/groceries")
    public List<Grocery> getAllGroceries() {
        return groceryReposiitory.findAll();
    }

    // create grocery api
    @PostMapping("/add-grocery")
    public Grocery createGrocery(@RequestBody Grocery grocery) {
        return groceryReposiitory.save(grocery);
    }

    // get grocery by id
    @GetMapping("/grocery/{id}")
    public ResponseEntity<Grocery> getGrocery(@PathVariable Long id) {
        Grocery grocery = groceryReposiitory.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Grocery not found with an Id: " + id));

        return ResponseEntity.ok(grocery);
    }

    // update grocery by id
    @PutMapping("/grocery/{id}")
    public ResponseEntity<Grocery> updateGrocery(@PathVariable Long id, @RequestBody Grocery groceryDetails) {
        Grocery grocery = groceryReposiitory.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Grocery not found with an Id: " + id));

        grocery.setItemName(groceryDetails.getItemName());
        grocery.setPrice(groceryDetails.getPrice());
        grocery.setCategory(groceryDetails.getCategory());

        Grocery updateEmployee = groceryReposiitory.save(grocery);
        return ResponseEntity.ok(updateEmployee);
    }

    // delete employee by id
    @DeleteMapping("/grocery/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteGrocery(@PathVariable Long id) {
        Grocery grocery = groceryReposiitory.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Grocery not found with an Id: " + id));

        groceryReposiitory.delete(grocery);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Successfully", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
