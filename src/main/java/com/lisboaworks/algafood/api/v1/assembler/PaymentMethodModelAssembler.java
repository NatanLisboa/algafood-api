package com.lisboaworks.algafood.api.v1.assembler;

import com.lisboaworks.algafood.api.v1.AlgaLinks;
import com.lisboaworks.algafood.api.v1.controller.PaymentMethodController;
import com.lisboaworks.algafood.api.v1.model.PaymentMethodModel;
import com.lisboaworks.algafood.core.security.SecurityHelper;
import com.lisboaworks.algafood.domain.model.PaymentMethod;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodModelAssembler extends RepresentationModelAssemblerSupport<PaymentMethod, PaymentMethodModel> {

    private final ModelMapper modelMapper;
    private final AlgaLinks algaLinks;
    private final SecurityHelper securityHelper;

    @Autowired
    public PaymentMethodModelAssembler(ModelMapper modelMapper, AlgaLinks algaLinks, SecurityHelper securityHelper) {
        super(PaymentMethodController.class, PaymentMethodModel.class);
        this.modelMapper = modelMapper;
        this.algaLinks = algaLinks;
        this.securityHelper = securityHelper;
    }

    public PaymentMethodModel toModel(PaymentMethod paymentMethod) {
        PaymentMethodModel paymentMethodModel = this.createModelWithId(paymentMethod.getId(), paymentMethod);

        modelMapper.map(paymentMethod, paymentMethodModel);

        if (securityHelper.canGetPaymentMethods()) {
            paymentMethodModel.add(algaLinks.linkToPaymentMethods("paymentMethods"));
        }

        return paymentMethodModel;
    }

    public CollectionModel<PaymentMethodModel> toCollectionModel(Iterable<? extends PaymentMethod> paymentMethods) {
        return super.toCollectionModel(paymentMethods)
                .add(algaLinks.linkToPaymentMethods());
    }

}
