package com.rengu.sugar.sugaruserapi.feignclient;

import com.rengu.sugar.sugaruserapi.entity.DepartmentEntity;
import com.rengu.sugar.sugaruserapi.entity.RoleEntity;
import com.rengu.sugar.sugaruserapi.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Author: XYmar
 * Date: 2019/1/10 16:48
 */
@Component
public class UserRemoteHystrix  implements UserFeignClient{
    @Override
    public boolean hasUserByEmail(String email) {
        return false;
    }

    @Override
    public void saveUser(UserEntity userEntity) {

    }

    @Override
    public List<UserEntity> getUsers() {
        return null;
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserEntity getUserById(String userId) {
        return null;
    }

    @Override
    public UserEntity updateUserById(String userId, UserEntity userEntity) {
        return null;
    }

    @Override
    public UserEntity updatePasswordById(String userId, String password) {
        return null;
    }

    @Override
    public UserEntity updateRoleById(String userId, String roleId) {
        return null;
    }

    @Override
    public UserEntity deleteUserById(String userId) {
        return null;
    }

    @Override
    public void updateEmailStateById(String userId) {

    }

    @Override
    public boolean getUserByEmailAndCode(String email, String activeCode) {
        return false;
    }

    @Override
    public RoleEntity saveRole(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public List<RoleEntity> getRoles() {
        return null;
    }

    @Override
    public RoleEntity getRoleById(String id) {
        return null;
    }

    @Override
    public RoleEntity updateRoleById(String id, RoleEntity roleEntity) {
        return null;
    }

    @Override
    public RoleEntity deleteRoleById(String id) {
        return null;
    }

    @Override
    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity) {
        return null;
    }

    @Override
    public List<DepartmentEntity> getDepartments() {
        return null;
    }

    @Override
    public DepartmentEntity updateDepartmentById(String departmentId, DepartmentEntity departmentEntity) {
        return null;
    }

    @Override
    public DepartmentEntity deleteDepartmentById(String departmentId) {
        return null;
    }

    @Override
    public DepartmentEntity saveMemberById(String departmentId, String[] ids) {
        return null;
    }

    @Override
    public DepartmentEntity deleteDepartmentMemberById(String departmentId, String userId) {
        return null;
    }
}
