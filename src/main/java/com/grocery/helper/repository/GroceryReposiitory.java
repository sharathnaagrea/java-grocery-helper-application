package com.grocery.helper.repository;

import com.grocery.helper.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryReposiitory extends JpaRepository<Grocery, Long> {

}
