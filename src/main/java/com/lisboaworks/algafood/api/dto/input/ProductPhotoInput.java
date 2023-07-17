package com.lisboaworks.algafood.api.dto.input;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductPhotoInput {

    private MultipartFile file;
    private String description;

}
