package com.neusoft.dao.impl;

import com.neusoft.dao.FoodDao;
import com.neusoft.domain.Admin;
import com.neusoft.domain.Food;
import com.neusoft.utils.JDBCUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuboting
 * @date 2020/8/7 11:07
 */

public class FoodDaoImpl implements FoodDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;



    @Override
    public List<Food> findAll(Integer businessId,String foodName, int lowPrice, int highPrice) {

        StringBuffer sql = new StringBuffer("select * from food where businessId = " + businessId);
        if(foodName != null && !foodName.equals("")){
            sql.append(" and foodName like '%").append(foodName).append("%'");
        }
        if(lowPrice != 0){
            sql.append(" and foodPrice >= ").append(lowPrice);
        }
        if(highPrice != 0){
            sql.append(" and foodPrice <= ").append(highPrice);
        }
        System.out.println(sql);
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = preparedStatement.executeQuery();
            ArrayList<Food> foods = new ArrayList<>();
            while (resultSet.next()) {
                foods.add(new Food(
                        resultSet.getInt("foodId"),
                        resultSet.getString("foodName"),
                        resultSet.getString("foodExplain"),
                        resultSet.getBigDecimal("foodPrice"),
                        resultSet.getInt("businessId")
                ));
            }
            return foods;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, resultSet);
        }
        return null;
    }

    @Override
    public Integer save(String foodName,Integer businessId) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into food(foodName,foodPrice,businessId) values(?,0,?)";
            preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,foodName);
//            preparedStatement.setString(2,food.getFoodExplain());
//            preparedStatement.setBigDecimal(3,food.getFoodPrice());
            preparedStatement.setInt(2,businessId);
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, resultSet);
        }
        return 0;
    }

    @Override
    public void update(Food food) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update admin set foodName = ?,foodExplain = ?,foodPrice = ?,set business = ? where foodId = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, food.getFoodName());
            preparedStatement.setString(2, food.getFoodExplain());
            preparedStatement.setBigDecimal(3,food.getFoodPrice());
            preparedStatement.setInt(4,food.getBusinessId());
            preparedStatement.setInt(5,food.getFoodId());
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("修改成功");
            } else {
                System.out.println("修改失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, null);
        }
    }

    @Override
    public Integer delete(Integer foodId) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from food where foodId = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, foodId);
            int count = preparedStatement.executeUpdate();
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, null);
        }
        return 0;
    }
}
