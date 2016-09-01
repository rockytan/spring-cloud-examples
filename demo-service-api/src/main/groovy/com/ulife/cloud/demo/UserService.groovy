package com.ulife.cloud.demo

import com.ulife.cloud.annotation.Api
import com.ulife.cloud.annotation.Parameter

/**
 * Created by Rocky on 8/23/16.
 */
interface UserService {

    @Api(name = "user.info",description = "获取用户信息",owner = "Rocky")
    public String getUser(@Parameter(name = "id",description = "用户ID") String id);

    @Api(name = "user.list",description = "获取用户列表",owner = "Rocky")
    public List<String> getUsers();
}