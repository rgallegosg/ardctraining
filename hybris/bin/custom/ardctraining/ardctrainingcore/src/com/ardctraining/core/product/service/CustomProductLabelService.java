package com.ardctraining.core.product.service;

import com.ardctraining.core.model.CustomProductLabelModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface CustomProductLabelService {

    List<CustomProductLabelModel> findByCustomerAndProduct (CustomerModel customer, ProductModel product);

}
