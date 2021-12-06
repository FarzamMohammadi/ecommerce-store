package com.farzammohammadi_imanetahri_comp303_assignment04.productservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // USING QUERY:
    // @Query("SELECT p FROM Product p where p.name = ?1")
    // USING OPTIONAL:
    Optional<Product> findProductByName(String name);


}
