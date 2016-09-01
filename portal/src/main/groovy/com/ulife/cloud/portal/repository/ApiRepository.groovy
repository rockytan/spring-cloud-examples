package com.ulife.cloud.portal.repository

import com.ulife.cloud.portal.domain.ApiInfo
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by Rocky on 8/24/16.
 */
interface ApiRepository extends MongoRepository<ApiInfo,String>{



}