package com.neusoft.dao.impl;

import com.neusoft.dao.AdminDao;
import com.neusoft.domain.Admin;
import com.neusoft.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuboting
 * @date 2020/8/7 9:48
 */

public class AdminDaoImpl implements AdminDao {
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public Admin getAdminByNameByPassword(String adminName, String password) {
        if (adminName == null || password == null) {
            return null;
        }
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from admin where adminName = ? and password = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, adminName);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            Admin admin = null;
            while (resultSet.next()) {
                admin = new Admin(
                        resultSet.getInt("adminId"),
                        resultSet.getString("adminName"),
                        resultSet.getString("password")
                );
            }
            return admin;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, resultSet);
        }
        return null;
    }

    @Override
    public List<Admin> findAll() {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select * from admin;";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            ArrayList<Admin> admins = new ArrayList<>();
            while (resultSet.next()) {
                admins.add(new Admin(
                        resultSet.getInt("adminId"),
                        resultSet.getString("adminName"),
                        resultSet.getString("password")
                ));
            }
            return admins;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, resultSet);
        }
        return null;
    }

    @Override
    public void save(Admin admin) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "insert into admin values(null,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,admin.getAdminName());
            preparedStatement.setString(2,admin.getPassword());
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("保存成功");
            } else {
                System.out.println("保存失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, null);
        }
    }

    @Override
    public void update(Admin admin) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update admin set password = ? where adminName = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getPassword());
            preparedStatement.setString(2, admin.getAdminName());
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
    public void delete(Integer adminId) {
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete from admin where adminId = ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, adminId);
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(preparedStatement, connection, null);
        }
    }
}

