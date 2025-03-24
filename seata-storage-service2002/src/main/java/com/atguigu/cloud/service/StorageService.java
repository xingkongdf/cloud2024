package com.atguigu.cloud.service;

public interface StorageService {
    //扣减库存
    public void decrease(Long productId, Integer count);
}
