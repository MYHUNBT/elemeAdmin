package com.neusoft.view.impl;

import com.neusoft.dao.impl.BusinessDaoImpl;
import com.neusoft.domain.Business;
import com.neusoft.view.BusinessView;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

/**
 * @author liuboting
 * @date 2020/8/7 15:18
 */

public class BusinessViewImpl implements BusinessView {
    private Scanner scanner = new Scanner(System.in);
    private BusinessDaoImpl businessDao = new BusinessDaoImpl();
    @Override
    public void listBusinessAll() {
        List<Business> businesses = businessDao.findAll(null, null);
        System.out.println("商家编号\t商家名称\t商家地址\t起运费\t配送费");
        for(Business business:businesses){
            System.out.println(business.getBusinessId() + "\t" + business.getBusinessName() + "\t" + business.getBusinessAddress() + "\t" + business.getStarPrice() + "\t" + business.getDeliveryPrice());
        }
    }

    @Override
    public void listBusinessSort() {
        String businessName = null;
        String businessAddress = null;
        System.out.println("是否需要输入商家名称关键词:(Y/N)");
        String b1 = scanner.nextLine();
        if(b1.equals("Y")){
            System.out.println("请输入商家名称关键词:");
            businessName = scanner.nextLine();
        }
        System.out.println("是否需要输入商家地址关键词:(Y/N)");
        String b2 = scanner.nextLine();
        if(b2.equals("Y")){
            System.out.println("请输入商家地址关键词:");
            businessAddress = scanner.nextLine();
        }
        List<Business> businesses = businessDao.findAll(businessName, businessAddress);
        System.out.println("商家编号\t商家名称\t商家地址\t起运费\t配送费");
        for(Business business:businesses){
            System.out.println(business.getBusinessId() + "\t" + business.getBusinessName() + "\t" + business.getBusinessAddress() + "\t" + business.getStarPrice() + "\t" + business.getDeliveryPrice());
        }
    }

    @Override
    public Business login() {
        System.out.println("请输入商家编号：");
        int businessId = scanner.nextInt();
        System.out.println("请输入密码：");
        String password = scanner.next();
        Business business = businessDao.getBusinessByNameByPassword(businessId, password);
        return business;
    }

    public void select(Business business){
        System.out.println("商家编号:\t" + business.getBusinessId());
        System.out.println("商家名称:\t" + business.getBusinessName());
        System.out.println("商家地址:\t" + business.getBusinessAddress());
        System.out.println("商家介绍:\t" + business.getBusinessExplain());
        System.out.println("起运费:\t" + business.getStarPrice());
        System.out.println("配送费:\t" + business.getDeliveryPrice());
    }

    public void saveBusiness(){
        System.out.println("新建商家");
        System.out.println("请输入商家名称:");
        String businessName = scanner.nextLine();
        int businessId = businessDao.save(businessName);
        if(businessId>0){
            System.out.println("新建商家成功，商家编号:" + businessId);
        }else{
            System.out.println("新建失败");
        }
    }

    @Override
    public void updateBusiness(Business business){
        String next = null;
        String businessName = null;
        String businessAddress = null;
        String businessExplain = null;
        BigDecimal businessStarPrice = null;
        BigDecimal businessDeliveryPrice = null;

        new BusinessViewImpl().select(business);
        System.out.println("是否修改商家名称:(Y/N)");
        next = scanner.next();
        if(next.equals("Y")){
            System.out.println("请输入商家名称:");
            businessName = scanner.next();
            business.setBusinessName(businessName);
        }
        System.out.println("是否修改商家地址:(Y/N)");
        next = scanner.next();
        if(next.equals("Y")){
            System.out.println("请输入商家地址:");
            businessAddress = scanner.next();
            business.setBusinessAddress(businessAddress);
        }
        System.out.println("是否修改商家介绍:(Y/N)");
        next = scanner.next();
        if(next.equals("Y")){
            System.out.println("请输入商家介绍:");
            businessExplain = scanner.next();
            business.setBusinessExplain(businessExplain);
        }
        System.out.println("是否修改商家起运费:(Y/N)");
        next = scanner.next();
        if(next.equals("Y")){
            System.out.println("请输入商家起运费:");
            businessStarPrice = scanner.nextBigDecimal();
            business.setStarPrice(businessStarPrice);
        }
        System.out.println("是否修改商家配送费:(Y/N)");
        next = scanner.next();
        if(next.equals("Y")){
            System.out.println("请输入商家配送费:");
            businessDeliveryPrice = scanner.nextBigDecimal();
            business.setDeliveryPrice(businessDeliveryPrice);
        }

        Integer count = businessDao.update(new Business(business.getBusinessId(),null,businessName,businessAddress,businessExplain,businessStarPrice,businessDeliveryPrice));
        if(count>0){
            System.out.println("修改商家信息成功");
            new BusinessViewImpl().select(business);
        }else {
            System.out.println("修改商家信息失败");
        }

    }

    @Override
    public void updateBusinessPassword(Business business){
        String next = null;
        System.out.println("请输入旧密码:");
        next = scanner.next();
        if(next.equals(business.getPassword())){
            System.out.println("请输入新密码:");
            String password = scanner.next();
            if(password != null && password != ""){
                System.out.println("请再次输入新密码:");
                String password2 = scanner.next();
                if(password.equals(password2)){
                    Integer count = businessDao.update(new Business(business.getBusinessId(), password, null, null, null, null, null));
                    if(count>0){
                        System.out.println("修改成功");
                    }else {
                        System.out.println("修改失败");
                    }
                }else {
                    System.out.println("两次输入密码不一致！");
                }
            }
        }else {
            System.out.println("密码错误");
        }
    }

    public void deleteBusiness(){
        System.out.println("删除商家");
        System.out.println("请输入商家id");
        Integer businessId = scanner.nextInt();
        System.out.println("确定要删除吗？(Y/N)");
        String s = scanner.next();
        if(s.equals("Y")){
            if(businessDao.delete(businessId) > 0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("取消删除");
        }
    }
}
