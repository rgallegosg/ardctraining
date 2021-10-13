package com.ardctraining.facades.feedback;

import com.ardctraining.core.model.CustomerFeedbackModel;
import com.ardctraining.facades.customer.data.CustomerFeedbackData;
import de.hybris.platform.commercefacades.customer.CustomerFacade;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;


public interface CustomerFeedbackFacade extends CustomerFacade {

    List<CustomerFeedbackData> findByCustomer ();

    void save (String subject, String message);

}
