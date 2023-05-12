package com.lisboaworks.algafood.api.controller;


import com.lisboaworks.algafood.domain.exception.BusinessRuleException;
import com.lisboaworks.algafood.domain.exception.EntityAlreadyInUseException;
import com.lisboaworks.algafood.domain.exception.EntityNotFoundException;
import com.lisboaworks.algafood.domain.model.City;
import com.lisboaworks.algafood.domain.repository.CityRepository;
import com.lisboaworks.algafood.domain.service.CityRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegisterService;

    @GetMapping
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @GetMapping("/{id}")
    public City findById(@PathVariable Long id) {
        return cityRegisterService.findOrThrowException(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City add(@RequestBody City city) {
        try {
            return cityRegisterService.save(city);
        } catch (BusinessRuleException e) {
            throw new BusinessRuleException(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public City update(@PathVariable Long id,
                       @RequestBody City newCity) {
        City city = cityRegisterService.findOrThrowException(id);
        BeanUtils.copyProperties(newCity, city, "id");
        try {
            return cityRegisterService.save(city);
        } catch (EntityNotFoundException e) {
            throw new BusinessRuleException(e.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        cityRegisterService.delete(id);
    }

}
