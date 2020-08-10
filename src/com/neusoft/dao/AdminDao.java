package com.neusoft.dao;

import com.neusoft.domain.Admin;

import java.util.List;

/**
 * @author liuboting
 * @date 2020/8/7 9:48
 */

public interface AdminDao {
    public Admin getAdminByNameByPassword(String adminName,String password);
    public List<Admin> findAll();
    public void save(Admin admin);
    public void update(Admin admin);
    public void delete(Integer adminId);
}
