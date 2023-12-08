package com.lisboaworks.algafood.api.v1.model.input;

import com.lisboaworks.algafood.core.validation.FileContentType;
import com.lisboaworks.algafood.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductPhotoInput {


    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;


    @NotBlank
    private String description;

}
