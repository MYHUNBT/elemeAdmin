package com.neusoft.dao;

import com.neusoft.domain.Business;
import com.neusoft.domain.Food;

import java.util.List;

/**
 * @author liuboting
 * @date 2020/8/7 10:56
 */

public interface BusinessDao {
    public List<Business> findAll(String businessName,String businessAddress);
    public int save(String businessName);
    public Integer update(Business business);
    public Integer delete(Integer businessId);
    public Business getBusinessByNameByPassword(Integer businessId,String password);
}
