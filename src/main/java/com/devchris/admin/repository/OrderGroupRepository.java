package com.devchris.admin.repository;

import com.devchris.admin.model.entity.OrderGroup;
import com.devchris.admin.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderGroupRepository extends JpaRepository<OrderGroup, Long> {
}
