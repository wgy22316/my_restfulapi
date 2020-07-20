package com.my.restfulapi.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "user_order")
public class UserOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "order_id")
    private Long orderId;

    private BigDecimal price;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}