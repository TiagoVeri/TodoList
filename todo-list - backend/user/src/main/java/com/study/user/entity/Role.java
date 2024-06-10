package com.study.user.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public enum Roles {

        ADMIN(1L),
        USER(2L);

        long roleId;

        Roles(Long roleId){
            this.roleId = roleId;
        }

        public Long getRole(){
            return this.roleId;
        }
    }

}
