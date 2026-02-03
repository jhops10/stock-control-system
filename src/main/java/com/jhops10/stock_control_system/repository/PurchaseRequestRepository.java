package com.jhops10.stock_control_system.repository;

import com.jhops10.stock_control_system.entity.PurchaseRequestEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRequestRepository extends MongoRepository<PurchaseRequestEntity, String> {
}
