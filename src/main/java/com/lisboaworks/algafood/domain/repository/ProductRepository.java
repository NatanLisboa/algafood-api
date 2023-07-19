package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.Product;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import com.lisboaworks.algafood.domain.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryQuery {

    @Query("from Product where restaurant.id = :restaurantId and id = :productId")
    Optional<Product> findById(Long restaurantId, Long productId);
    @Query("from Product p where p.restaurant = :restaurant and p.active = true")
    List<Product> findActiveProductsByRestaurant(Restaurant restaurant);

    @Query("select pp from ProductPhoto pp join pp.product p where p.restaurant.id = :restaurantId and p.id = :productId")
    Optional<ProductPhoto> findPhotoById(Long restaurantId, Long productId);
}
