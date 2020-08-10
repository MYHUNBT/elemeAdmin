package com.neusoft.view.impl;

import com.neusoft.dao.FoodDao;
import com.neusoft.dao.impl.FoodDaoImpl;
import com.neusoft.domain.Food;
import com.neusoft.view.FoodView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * @author liuboting
 * @date 2020/8/7 13:31
 */

public class FoodViewImpl implements FoodView{
    private Scanner scanner = new Scanner(System.in);
    private FoodDao foodDao = new FoodDaoImpl();
    @Override
    public void showFoodList() {
        List<Food> foods = foodDao.findAll(null,0,0);
        System.out.println("食物编号\t食物名字\t食物详情\t食物价格");
        for(Food food:foods){
            System.out.println(food.getFoodId() + "\t" + food.getFoodName() + "\t" + food.getFoodExplain() + "\t" + food.getFoodPrice());
        }
    }

    public void showFoodSearch(){
        String foodName = null;
        int lowPrice = 0;
        int highPrice = 0;
        System.out.println("是否要输入食物名称关键字(Y/N):");
        String a = scanner.next();
        if(a.equals("Y")){
            System.out.println("请输入食物名称关键字：");
            foodName = scanner.next();
        }
        System.out.println("是否要输入食物价格范围(最小值)(Y/N):");
        String b = scanner.next();
        if(b.equals("Y")){
            System.out.println("请输入食物价格最低值:");
            lowPrice = scanner.nextInt();
        }
        System.out.println("是否要输入食物价格范围(最大值)(Y/N):");
        String c = scanner.next();
        if(c.equals("Y")){
            System.out.println("请输入食物价格最大值:");
            highPrice = scanner.nextInt();
        }

        List<Food> foods = foodDao.findAll(foodName, lowPrice, highPrice);

        System.out.println("食物编号\t食物名字\t食物详情\t食物价格");
        for(Food food:foods){
            System.out.println(food.getFoodId() + "\t" + food.getFoodName() + "\t" + food.getFoodExplain() + "\t" + food.getFoodPrice());
        }

    }

    public void saveFood(){
        System.out.println("请输入食物的名字：");
        String foodName = scanner.next();
        Integer foodId = foodDao.save(foodName);
        if(foodId > 0){
            System.out.println("食物添加成功,食物的编号:" + foodId);
        }else {
            System.out.println("食物添加失败");
        }
    }

    public void deleteFood(){
        System.out.println("请输入删除的食物编号:");
        int foodId = scanner.nextInt();
        if(foodDao.delete(foodId) > 0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
}
