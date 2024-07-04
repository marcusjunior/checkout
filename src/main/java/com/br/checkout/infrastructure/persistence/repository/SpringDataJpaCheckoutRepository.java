package com.br.checkout.infrastructure.persistence.repository;

import com.br.checkout.infrastructure.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SpringDataJpaCheckoutRepository extends JpaRepository<OrderEntity, Long> {
}
