package com.neusoft.view;

import com.neusoft.domain.Business;

public interface BusinessView {
    public void listBusinessAll();

    public void listBusinessSort();

    public Business login();

    public void updateBusiness(Business business);

    public void updateBusinessPassword(Business business);
}
