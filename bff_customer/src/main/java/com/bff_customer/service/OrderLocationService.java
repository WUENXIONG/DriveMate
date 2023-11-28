package com.bff_customer.service;

import com.bff_customer.controller.form.SearchOrderLocationCacheForm;

import java.util.HashMap;

public interface OrderLocationService {

    public HashMap searchOrderLocationCache(SearchOrderLocationCacheForm form);
}
