package com.yang.springboot.hibernate.repository;

import com.yang.springboot.hibernate.entity.User;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

@Repository
@Table(name = "user")
@Qualifier("userRepository")
public interface UserRepository extends CrudRepository<User, Long> {

    public User findOne(Long id);

    public User save(User u);

    //@Query("select t from User t where t.userName=:name")
    public User findUserByName(@Param("name") String name);
}
