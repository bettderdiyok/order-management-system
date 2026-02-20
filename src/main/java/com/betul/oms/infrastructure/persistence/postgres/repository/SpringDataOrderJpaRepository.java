package com.betul.oms.infrastructure.persistence.postgres.repository;

import com.betul.oms.infrastructure.persistence.postgres.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface SpringDataOrderJpaRepository extends JpaRepository<OrderEntity, UUID> {


}
