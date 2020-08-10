package com.neusoft.dao.impl;

import com.neusoft.dao.BusinessDao;
import com.neusoft.domain.Business;
import com.neusoft.domain.Food;
import com.neusoft.utils.JDBCUtils;

import java.nio.Buffer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuboting
 * @date 2020/8/7 11:08
 */

public class BusinessDaoImpl implements BusinessDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public List<Business> findAll(String businessName,String businessAddress) {
            StringBuffer sql = new StringBuffer("select * from business where 1=1");
            if(businessName != null && !businessName.equals("")){
                // 传入商家名
                sql.append(" and businessName like '%" ).append(businessName).append("%'");
                System.out.println(sql);
            }
            if(businessAddress != null && !businessAddress.equals("")){
                // 传入商家名
                sql.append(" and businessAddress like '%" ).append(businessAddress).append("%'");
                System.out.println(sql);
            }

        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql.toString());
            resultSet = preparedStatement.executeQuery();
            ArrayList<Business> businesses = new ArrayList<>();
            while (resultSet.next()) {
                businesses.add(new Business(
                        resultSet.getInt("businessId"),
                        resultSet.getString("password"),
                        resultSet.getString("businessName"),
                        resultSet.getString("businessAddress"),
                        resultSet.getString("businessExplain"),
                        resultSet.getBigDecimal("starPrice"),
                        resultSet.getBigDecimal("deliveryPrice")
                ));
            }
            return businesses;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, resultSet);
        }
        return null;
    }

    @Override
    public int save(String businessName) {
        int businessId = 0;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into business(businessName,password) values(?,'123')";
            preparedStatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,businessName);

            int count = preparedStatement.executeUpdate();

            resultSet= preparedStatement.getGeneratedKeys();
            resultSet.next();
            businessId = resultSet.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection,resultSet);
        }
        return businessId;
    }

    @Override
    public Integer update(Business business) {
        try {
            connection = JDBCUtils.getConnection();
//            String sql = "update business set password = ?,businessName = ?,businessAddress = ?,businessExplain =?,starPrice = ?,deliveryPrice = ? where businessId = ?";
            StringBuffer sql = new StringBuffer("update business set ");
            if(business.getPassword() != null){
                sql.append("password = ").append(business.getPassword());
            }
            if(business.getBusinessName() != null){
                sql.append("businessName = ").append(business.getBusinessName()).append(",");
            }
            if(business.getBusinessAddress() != null){
                sql.append("businessAddress = ").append(business.getBusinessAddress()).append(",");
            }
            if(business.getBusinessExplain() != null){
                sql.append("businessExplain = ").append(business.getBusinessExplain()).append(",");
            }
            if(business.getStarPrice() != null){
                sql.append("starPrice = ").append(business.getDeliveryPrice()).append(",");
            }
            if(business.getDeliveryPrice() != null){
                sql.append("deliveryPrice = ").append(business.getDeliveryPrice());
            }
            sql.append(" where businessId = ").append(business.getBusinessId());
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql.toString());
            int count = preparedStatement.executeUpdate();
            return count;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, null);
        }
        return 0;
    }

    @Override
    public Integer delete(Integer businessId) {
        int count = 0;
        try {
            connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);
            String sql = "delete from business where businessId = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,businessId);
            count = preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException e) {
            // 进入了异常的代码区要给count置为0
            count = 0;
            try{
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
        } finally {
            JDBCUtils.close(preparedStatement, connection, null);
        }
        return count;
    }

    @Override
    public Business getBusinessByNameByPassword(Integer businessId, String password) {
        Business business = null;
        try{
            connection = JDBCUtils.getConnection();
            String sql = "select * from business where businessId = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,businessId);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            business = new Business(
                    resultSet.getInt("businessId"),
                    resultSet.getString("password"),
                    resultSet.getString("businessName"),
                    resultSet.getString("businessAddress"),
                    resultSet.getString("businessExplain"),
                    resultSet.getBigDecimal("starPrice"),
                    resultSet.getBigDecimal("deliveryPrice")
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(preparedStatement,connection,resultSet);
        }
        return business;
    }
}
