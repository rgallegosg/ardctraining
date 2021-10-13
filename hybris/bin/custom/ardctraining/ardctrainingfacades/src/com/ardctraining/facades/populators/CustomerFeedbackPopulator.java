package com.ardctraining.facades.populators;

import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.facades.customer.data.CustomerFeedbackData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

public class CustomerFeedbackPopulator implements Populator<CustomerFeedbackModel, CustomerFeedbackData> {

    @Override
    public void populate(CustomerFeedbackModel feedbackModel, CustomerFeedbackData customerFeedbackData) throws ConversionException {

        customerFeedbackData.setSubject(feedbackModel.getSubject());
        customerFeedbackData.setMessage(feedbackModel.getMessage());
        customerFeedbackData.setStatus(feedbackModel.getStatus());
        customerFeedbackData.setSubmittedDate(feedbackModel.getSubmittedDate());

    }
}
