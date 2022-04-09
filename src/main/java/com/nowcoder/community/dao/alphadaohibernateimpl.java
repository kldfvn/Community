package com.nowcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
//访问数据库注解
@Repository
//优先注解
@Primary
public class alphadaohibernateimpl implements alphdao {
    @Override
    public String select() {
        return "Aaaaa";
    }
}
