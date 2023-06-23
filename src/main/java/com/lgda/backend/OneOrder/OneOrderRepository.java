package com.lgda.backend.OneOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OneOrderRepository extends JpaRepository<OneOrder, Long> {
}
