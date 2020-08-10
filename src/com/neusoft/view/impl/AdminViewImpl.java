package com.neusoft.view.impl;

import com.neusoft.dao.AdminDao;
import com.neusoft.dao.impl.AdminDaoImpl;
import com.neusoft.domain.Admin;
import com.neusoft.view.AdminView;

import java.util.Scanner;

/**
 * @author liuboting
 * @date 2020/8/7 11:35
 */

public class AdminViewImpl implements AdminView {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public Admin login() {
        System.out.println("请输入管理员的用户名：");
        String adminName = scanner.nextLine();
        System.out.println("请输入管理员的密码：");
        String password = scanner.nextLine();
        AdminDao dao = new AdminDaoImpl();
        return dao.getAdminByNameByPassword(adminName,password);
    }
}
