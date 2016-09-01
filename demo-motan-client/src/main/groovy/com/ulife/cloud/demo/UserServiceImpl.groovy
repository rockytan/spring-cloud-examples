package com.ulife.cloud.demo

import com.google.common.collect.Lists
import com.ulife.cloud.annotation.Parameter

/**
 * Created by Rocky on 8/24/16.
 */
class UserServiceImpl implements UserService{

    @Override
    String getUser(@Parameter(name = "id", description = "用户ID") String id) {
        "Rocky"
    }

    @Override
    List<String> getUsers() {
        Lists.newArrayList("Rocky","Merlin")
    }
}
