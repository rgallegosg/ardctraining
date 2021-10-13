package com.ardctraining.facades.feedback.impl;

import com.ardctraining.core.feedback.service.CustomerFeedbackService;
import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.facades.customer.data.CustomerFeedbackData;
import com.ardctraining.facades.feedback.CustomerFeedbackFacade;
import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.List;

public class DefaultCustomerFeedbackFacade extends DefaultCustomerFacade implements CustomerFeedbackFacade {

    private CustomerFeedbackService customerFeedbackService;
    private Converter<CustomerFeedbackModel, CustomerFeedbackData> customerFeedbackConverter;


    @Override
    public List<CustomerFeedbackData> findByCustomer() {
        final CustomerModel currentCustomer = (CustomerModel) getUserService().getCurrentUser();
        return customerFeedbackConverter.convertAll(customerFeedbackService.findByCustomer(currentCustomer));

    }

    @Override
    public void save(String subject, String message) {
        CustomerFeedbackModel customerFeedbackModel = new CustomerFeedbackModel();
        customerFeedbackModel.setSubject(subject);
        customerFeedbackModel.setMessage(message);
        customerFeedbackService.save(customerFeedbackModel);
    }

    public CustomerFeedbackService getCustomerFeedbackService() {
        return customerFeedbackService;
    }

    public void setCustomerFeedbackService(CustomerFeedbackService customerFeedbackService) {
        this.customerFeedbackService = customerFeedbackService;
    }

    public Converter<CustomerFeedbackModel, CustomerFeedbackData> getCustomerFeedbackDataConverter() {
        return customerFeedbackConverter;
    }

    public void setCustomerFeedbackDataConverter(Converter<CustomerFeedbackModel, CustomerFeedbackData> customerFeedbackDataConverter) {
        this.customerFeedbackConverter = customerFeedbackDataConverter;
    }
}
