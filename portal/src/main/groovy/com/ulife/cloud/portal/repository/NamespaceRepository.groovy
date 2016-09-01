package com.ulife.cloud.portal.repository

import com.ulife.cloud.portal.domain.Namespace
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by Rocky on 8/24/16.
 */
interface NamespaceRepository extends MongoRepository<Namespace,String>{

}