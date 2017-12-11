package com.yang.springboot.auth2.repository;

import com.yang.springboot.auth2.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface AccountRepository extends MongoRepository<Account, String> {
    /**
     * 参照spring data jpa 可以根据方法名称查询出数据无需实现
     * @param name
     * @return
     */
    Account findByName(String name);
}
