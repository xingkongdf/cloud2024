package com.atguigu.cloud.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface AccountService {

    /**
     *
     * @param userId
     * @param money
     */
    void decrease( Long userId, Long money);
}
