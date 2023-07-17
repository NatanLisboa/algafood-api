package com.lisboaworks.algafood.api.controller;

import com.lisboaworks.algafood.api.dto.input.ProductPhotoInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping("/restaurants/{restaurantId}/products/{productId}/photo")
public class RestaurantProductPhotoController {

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updatePhoto(@PathVariable Long restaurantId,
                            @PathVariable Long productId, @Valid ProductPhotoInput productPhotoInput) {
        String filename = UUID.randomUUID().toString()
                + "_" + productPhotoInput.getFile().getOriginalFilename();

        Path photoFilePath = Path.of(".\\src\\test\\resources\\image", filename);

        System.out.println(productPhotoInput.getDescription());
        System.out.println(photoFilePath);
        System.out.println(productPhotoInput.getFile().getContentType());

        try {
            productPhotoInput.getFile().transferTo(photoFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
