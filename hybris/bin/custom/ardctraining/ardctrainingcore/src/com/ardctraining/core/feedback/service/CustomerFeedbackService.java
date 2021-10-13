package com.ardctraining.core.feedback.service;

import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface CustomerFeedbackService {

    List<CustomerFeedbackModel> findByCustomer (CustomerModel customer);

    void save (CustomerFeedbackModel feedbackModel);
}
