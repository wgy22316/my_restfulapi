package com.my.restfulapi.service.impl;
import com.my.restfulapi.mapper.dao.UserOrderDao;
import com.my.restfulapi.model.UserOrder;
import com.my.restfulapi.service.UserOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @Author: 小师傅
 * @Date: 2020-07-20 23:35
 */
@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Resource
    private UserOrderDao userOrderDao;

    @Override
    @Transactional(rollbackFor =Exception.class, propagation = Propagation.REQUIRED)
    public void addUserOrderService() {
        UserOrder userOrder = new UserOrder();
        userOrder.setOrderId(1L);
        userOrder.setPrice(new BigDecimal("2"));
        userOrderDao.save(userOrder);
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor =Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void addUserOrderService2() {
        UserOrder userOrder = new UserOrder();
        userOrder.setOrderId(1L);
        userOrder.setPrice(new BigDecimal("2"));
        userOrderDao.save(userOrder);
        throw new RuntimeException();
    }
}
