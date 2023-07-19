package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.assembler.ProductPhotoDTOAssembler;
import com.lisboaworks.algafood.api.dto.ProductPhotoDTO;
import com.lisboaworks.algafood.api.dto.input.ProductPhotoInput;
import com.lisboaworks.algafood.domain.model.Product;
import com.lisboaworks.algafood.domain.model.ProductPhoto;
import com.lisboaworks.algafood.domain.service.ProductPhotoCatalogService;
import com.lisboaworks.algafood.domain.service.ProductRegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
@AllArgsConstructor
public class RestaurantProductPhotoController {

    private final ProductRegisterService productRegisterService;
    private final ProductPhotoCatalogService productPhotoCatalogService;
    private final ProductPhotoDTOAssembler productPhotoDTOAssembler;

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProductPhotoDTO updatePhoto(@PathVariable Long restaurantId,
                                       @PathVariable Long productId, @Valid ProductPhotoInput productPhotoInput) throws IOException {
        Product product = productRegisterService.findOrThrowException(restaurantId, productId);

        MultipartFile file = productPhotoInput.getFile();

        ProductPhoto productPhoto = new ProductPhoto();
        productPhoto.setProduct(product);
        productPhoto.setDescription(productPhotoInput.getDescription());
        productPhoto.setContentType(file.getContentType());
        productPhoto.setSize(file.getSize());
        productPhoto.setFilename(file.getOriginalFilename());

        ProductPhoto savedPhoto = productPhotoCatalogService.save(productPhoto, file.getInputStream());

        return productPhotoDTOAssembler.toDTO(savedPhoto);
    }

}
