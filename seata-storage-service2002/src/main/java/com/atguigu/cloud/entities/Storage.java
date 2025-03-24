package com.atguigu.cloud.entities;

import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 表名：t_storage
*/
@Table(name = "t_storage")
@ToString
public class Storage implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     *  产品 id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     *  总库存 
     */
    private Integer total;

    /**
     *  已用库存 
     */
    private Integer used;

    /**
     *  剩余库存 
     */
    private Integer residue;

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
     * 获取 总库存 
     *
     * @return total -  总库存 
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置 总库存 
     *
     * @param total  总库存 
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * 获取 已用库存 
     *
     * @return used -  已用库存 
     */
    public Integer getUsed() {
        return used;
    }

    /**
     * 设置 已用库存 
     *
     * @param used  已用库存 
     */
    public void setUsed(Integer used) {
        this.used = used;
    }

    /**
     * 获取 剩余库存 
     *
     * @return residue -  剩余库存 
     */
    public Integer getResidue() {
        return residue;
    }

    /**
     * 设置 剩余库存 
     *
     * @param residue  剩余库存 
     */
    public void setResidue(Integer residue) {
        this.residue = residue;
    }
}