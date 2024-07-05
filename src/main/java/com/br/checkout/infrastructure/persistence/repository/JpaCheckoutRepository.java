package com.br.checkout.infrastructure.persistence.repository;

import com.br.checkout.domain.model.Order;
import com.br.checkout.domain.model.OrderStatus;
import com.br.checkout.domain.port.CheckoutRepository;
import com.br.checkout.infrastructure.persistence.entity.OrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaCheckoutRepository implements CheckoutRepository {

    private final SpringDataJpaCheckoutRepository repository;

    @Autowired
    public JpaCheckoutRepository(SpringDataJpaCheckoutRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setAmount(order.getAmount());
        entity.setStatus(order.getStatus());
        entity = repository.save(entity);
        order.setId(entity.getId());
        return order;
    }

    @Override
    public List<Order> findAll() {
        return repository.findAll().stream().map(entity -> {
            Order order = new Order();
            order.setId(entity.getId());
            order.setAmount(entity.getAmount());
            order.setStatus(entity.getStatus());
            return order;
        }).collect(Collectors.toList());
    }

    @Override
    public void updateOrderStatus(Long id, String status){
        Optional<OrderEntity> order = repository.findById(id);
        if(order.isPresent()) {
            order.get().setStatus(OrderStatus.valueOf(status));
            repository.save(order.get());
        }
    }

}