package com.hjb.dao.entity;

import com.hjb.common.RoleType;
import com.hjb.common.UserState;
import com.hjb.utils.RoleTypeListConvert;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: GgUser
 * Description:
 * Created by hjb on 2019/3/29 17:44
 */
@Data
@Entity(name = "gg_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class GgUser {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String nickName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String headPicPath;
    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private UserState userState;
    @CreatedDate
    private Timestamp createTime;
    @LastModifiedDate
    private Timestamp updateTime;
    @Column(nullable = false)
    @Convert(converter = RoleTypeListConvert.class)
    private List<RoleType> roles;

}
