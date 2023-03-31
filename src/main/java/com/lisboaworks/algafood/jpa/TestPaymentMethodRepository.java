package com.lisboaworks.algafood.jpa;

import com.lisboaworks.algafood.AlgafoodApiApplication;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.model.Restaurant;
import com.lisboaworks.algafood.domain.repository.PaymentMethodRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class TestPaymentMethodRepository {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        PaymentMethodRepository paymentMethodRepository = applicationContext.getBean(PaymentMethodRepository.class);

        // FIND ALL
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();
        for (PaymentMethod paymentMethod : paymentMethods) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Id: " + paymentMethod.getId());
            System.out.println("Description: " + paymentMethod.getDescription());
            System.out.println("Restaurant: " + paymentMethod.getRestaurant().getName());
            System.out.println();
        }

        // FIND BY ID
        PaymentMethod paymentMethod1 = paymentMethodRepository.findById(1L);
        System.out.println("Id: " + paymentMethod1.getId());
        System.out.println("Description: " + paymentMethod1.getDescription());
        System.out.println("Restaurant: " + paymentMethod1.getRestaurant().getName());

        // ADD
        Restaurant restaurant1 = new Restaurant();
        Restaurant restaurant2 = new Restaurant();

        restaurant1.setId(1L);
        restaurant2.setId(2L);

        PaymentMethod paymentMethod7 = new PaymentMethod();
        paymentMethod7.setDescription("QR Code");
        paymentMethod7.setRestaurant(restaurant1);

        PaymentMethod paymentMethod8 = new PaymentMethod();
        paymentMethod8.setDescription("QR Code");
        paymentMethod8.setRestaurant(restaurant2);

        paymentMethod7 = paymentMethodRepository.save(paymentMethod7);
        paymentMethod8 = paymentMethodRepository.save(paymentMethod8);

        // UPDATE
        paymentMethod8.setId(8L);
        paymentMethod8.setDescription("QR Code+");
        
        paymentMethodRepository.save(paymentMethod8);

        // DELETE
        paymentMethodRepository.delete(paymentMethod7);
    }
}
