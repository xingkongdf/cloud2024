package com.atguigu.cloud.entities;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 表名：t_order
*/
@Table(name = "t_order")
@ToString
public class Order implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     *  用户 id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     *  产品 id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     *  数量 
     */
    private Integer count;

    /**
     *  金额 
     */
    private Long money;

    /**
     *  订单状态: 0: 创建中；1: 已完结 
     */
    private Integer status;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取 用户 id
     *
     * @return userId -  用户 id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置 用户 id
     *
     * @param userId  用户 id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取 产品 id
     *
     * @return productId -  产品 id
     */
    public Long getProductId() {
        return productId;
    }

    /**
     * 设置 产品 id
     *
     * @param productId  产品 id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取 数量 
     *
     * @return count -  数量 
     */
    public Integer getCount() {
        return count;
    }

    /**
     * 设置 数量 
     *
     * @param count  数量 
     */
    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 获取 金额 
     *
     * @return money -  金额 
     */
    public Long getMoney() {
        return money;
    }

    /**
     * 设置 金额 
     *
     * @param money  金额 
     */
    public void setMoney(Long money) {
        this.money = money;
    }

    /**
     * 获取 订单状态: 0: 创建中；1: 已完结 
     *
     * @return status -  订单状态: 0: 创建中；1: 已完结 
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置 订单状态: 0: 创建中；1: 已完结 
     *
     * @param status  订单状态: 0: 创建中；1: 已完结 
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}