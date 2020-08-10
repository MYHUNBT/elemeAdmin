package com.neusoft.dao;

import com.neusoft.domain.Admin;
import com.neusoft.domain.Food;

import java.math.BigDecimal;
import java.util.List;

public interface FoodDao {
    public List<Food> findAll(String foodName, int lowPrice, int highPrice);
    public Integer save(String foodName);
    public void update(Food food);
    public Integer delete(Integer foodId);
}
