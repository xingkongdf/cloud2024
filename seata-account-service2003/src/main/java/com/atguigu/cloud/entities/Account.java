package com.atguigu.cloud.entities;

import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 表名：t_account
*/
@Table(name = "t_account")
@ToString
public class Account implements Serializable {
    /**
     * id
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     *  用户 id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     *  总额度 
     */
    private Long total;

    /**
     *  已用账户余额 
     */
    private Long used;

    /**
     *  剩余可用额度 
     */
    private Long residue;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
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
     * 获取 总额度 
     *
     * @return total -  总额度 
     */
    public Long getTotal() {
        return total;
    }

    /**
     * 设置 总额度 
     *
     * @param total  总额度 
     */
    public void setTotal(Long total) {
        this.total = total;
    }

    /**
     * 获取 已用账户余额 
     *
     * @return used -  已用账户余额 
     */
    public Long getUsed() {
        return used;
    }

    /**
     * 设置 已用账户余额 
     *
     * @param used  已用账户余额 
     */
    public void setUsed(Long used) {
        this.used = used;
    }

    /**
     * 获取 剩余可用额度 
     *
     * @return residue -  剩余可用额度 
     */
    public Long getResidue() {
        return residue;
    }

    /**
     * 设置 剩余可用额度 
     *
     * @param residue  剩余可用额度 
     */
    public void setResidue(Long residue) {
        this.residue = residue;
    }
}