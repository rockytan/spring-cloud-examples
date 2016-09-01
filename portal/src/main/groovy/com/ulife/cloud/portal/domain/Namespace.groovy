package com.ulife.cloud.portal.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by Rocky on 8/24/16.
 */
@Document(collection = "api_namespace")
class Namespace {

    @Id
    String id
    String name
    String description
    String path
}
