package com.hjb.dao.repository;

import com.hjb.dao.entity.GgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClassName: GgUserRepository
 * Description:
 * Created by hjb on 2019/3/29 17:45
 */
@Repository
public interface GgUserRepository extends JpaRepository<GgUser, String> {
    GgUser findByPhone(String phone);
}
