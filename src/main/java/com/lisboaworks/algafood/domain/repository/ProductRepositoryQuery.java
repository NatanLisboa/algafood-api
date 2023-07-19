package com.lisboaworks.algafood.domain.repository;

import com.lisboaworks.algafood.domain.model.ProductPhoto;

public interface ProductRepositoryQuery {

    ProductPhoto save(ProductPhoto productPhoto);

    void delete(ProductPhoto productPhoto);

}
