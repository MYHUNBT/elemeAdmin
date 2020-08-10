package com.neusoft.view;

import com.neusoft.domain.Food;

import java.util.List;

/**
 * @author liuboting
 * @date 2020/8/7 13:30
 */

public interface FoodView {
    public void showFoodList(Integer businessId);

    public void showFoodSearch(Integer businessId);

    public void saveFood(Integer businessId);

    public void deleteFood();
}
