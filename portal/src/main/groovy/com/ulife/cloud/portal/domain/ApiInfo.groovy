package com.ulife.cloud.portal.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by Rocky on 8/24/16.
 */
@Document(collection = "api_info")
class ApiInfo {

    @Id
    String id
    Namespace namespace
    String name
    String description
    int type
    String className
    String endpoint
    String owner
}
