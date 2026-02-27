package com.betul.oms.infrastructure.persistence.postgres.repository;

import com.betul.oms.domain.model.Order;
import com.betul.oms.domain.repository.OrderRepository;
import com.betul.oms.infrastructure.persistence.postgres.mapper.OrderMapper;
import com.betul.oms.infrastructure.persistence.postgres.entity.OrderEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Primary
@Slf4j
@Repository
@RequiredArgsConstructor
public class PostgresOrderRepository implements OrderRepository {
    private final SpringDataOrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        log.info("Saving order using postgres repository: {}", order);
        OrderEntity entity = OrderMapper.toOrderEntity(order);
        orderJpaRepository.save(entity);
        return order;
    }

    @Override
    public Optional<Order> findById(UUID id) {
        log.info("Finding order using postgres repository: {}", id);
        return orderJpaRepository.findById(id)
                .map(OrderMapper::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return List.of();
    }
}
