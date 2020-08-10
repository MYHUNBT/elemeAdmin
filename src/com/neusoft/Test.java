package com.neusoft;

import com.alibaba.druid.sql.visitor.SQLASTOutputVisitorUtils;
import com.neusoft.dao.AdminDao;
import com.neusoft.dao.impl.AdminDaoImpl;
import com.neusoft.domain.Admin;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * @author liuboting
 * @date 2020/8/7 10:02
 */

public class Test {
    public static void main(String[] args) {
        int a = 2;
        int b= 3;
        int c = 4;
        int d = (a>b? a:b)>c? (a>b? a:b):c;
        System.out.println(d);
    }
}
