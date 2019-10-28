package com.zhangtory.wayhome.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: ZhangYaoYu
 * @Date: 10/25 17:14
 * @Description:
 */
@Data
@Entity
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private String mobile;
    @OneToMany
    @JoinColumn(name = "id", referencedColumnName = "user_id")
    private List<UserKey> userKeyList;

}
