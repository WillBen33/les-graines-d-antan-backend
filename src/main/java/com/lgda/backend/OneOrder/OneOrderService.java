package com.lgda.backend.OneOrder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OneOrderService {

    private final OneOrderRepository oneOrderRepository;

    public  List<OneOrder> getAll() {
        return oneOrderRepository.findAll();
    }

    public OneOrder getById(Long id) {
        return oneOrderRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + "not found"));
    }

    public OneOrder add(OneOrder oneOrder) {
        OneOrder newOneOrder = oneOrderRepository.save(oneOrder);
                return newOneOrder;
    }

    public OneOrder update(OneOrder oneOrder, Long id) {
        OneOrder foundOneOrder = getById(id);

        foundOneOrder.setOrderStatus(oneOrder.getOrderStatus());
        foundOneOrder.setOrderProductList(oneOrder.getOrderProductList());
        foundOneOrder.setCreatedAt(oneOrder.getCreatedAt());
        foundOneOrder.setTotalCost(oneOrder.getTotalCost());

        return oneOrderRepository.save(foundOneOrder);
    }

    public void delete(Long id) {
        oneOrderRepository.deleteById(id);
    }
}
