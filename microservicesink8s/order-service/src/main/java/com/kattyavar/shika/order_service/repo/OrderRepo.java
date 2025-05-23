package com.kattyavar.shika.order_service.repo;

import com.kattyavar.shika.order_service.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<OrderEntity, Integer> {

  List<OrderEntity> findByUserId(String userId);
}
