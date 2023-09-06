package com.lisboaworks.algafood.api.assembler;

import com.lisboaworks.algafood.api.AlgaLinks;
import com.lisboaworks.algafood.api.controller.PaymentMethodController;
import com.lisboaworks.algafood.api.model.PaymentMethodModel;
import com.lisboaworks.algafood.api.model.RestaurantModel;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import com.lisboaworks.algafood.domain.model.Restaurant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class PaymentMethodModelAssembler extends RepresentationModelAssemblerSupport<PaymentMethod, PaymentMethodModel> {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AlgaLinks algaLinks;

    public PaymentMethodModelAssembler() {
        super(PaymentMethodController.class, PaymentMethodModel.class);
    }

    public PaymentMethodModel toModel(PaymentMethod paymentMethod) {
        PaymentMethodModel paymentMethodModel = this.createModelWithId(paymentMethod.getId(), paymentMethod);

        modelMapper.map(paymentMethod, paymentMethodModel);

        paymentMethodModel.add(algaLinks.linkToPaymentMethods("paymentMethods"));

        return paymentMethodModel;
    }

    public CollectionModel<PaymentMethodModel> toCollectionModel(Iterable<? extends PaymentMethod> paymentMethods) {
        return super.toCollectionModel(paymentMethods)
                .add(algaLinks.linkToPaymentMethods());
    }

}
