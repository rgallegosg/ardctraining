package com.ardctraining.core.feedback.dao;

import com.ardctraining.core.model.CustomerFeedbackModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public interface CustomerFeedbackDao {

    List<CustomerFeedbackModel> findByCustomer (CustomerModel customer);

    CustomerFeedbackModel save (CustomerFeedbackModel customerFeedbackModel);
}
