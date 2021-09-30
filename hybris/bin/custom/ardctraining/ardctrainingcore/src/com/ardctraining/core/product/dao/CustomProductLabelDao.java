package com.ardctraining.core.product.dao;

import com.ardctraining.core.model.CustomProductLabelModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.List;

public interface CustomProductLabelDao {

    List<CustomProductLabelModel> findByCustomerAndProduct (CustomerModel customer, ProductModel product);

}
