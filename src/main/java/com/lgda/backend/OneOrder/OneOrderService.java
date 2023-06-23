package com.lgda.backend.OneOrder;

import com.lgda.backend.OrderProduct.OrderProductRepository;
import com.lgda.backend.address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OneOrderService {

    private final OneOrderRepository oneOrderRepository;
    private final AddressRepository addressRepository;
    private final OrderProductRepository orderProductRepository;

    public  List<OneOrder> getAll() {
        return oneOrderRepository.findAll();
    }


    public OneOrder getById(Long id) {
        return oneOrderRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + "not found"));
    }


    public OneOrder add(OneOrder oneOrder) {
       return oneOrderRepository.save(oneOrder);
    }

    public OneOrder update(OneOrder oneOrder, Long id) {
        OneOrder foundOneOrder = getById(id);
        foundOneOrder.setOrderStatus(oneOrder.getOrderStatus());
        foundOneOrder.setOrderProductList(oneOrder.getOrderProductList());
        foundOneOrder.setCreatedAt(oneOrder.getCreatedAt());
        foundOneOrder.setTotalCost(oneOrder.getTotalCost());


        return oneOrderRepository.save(foundOneOrder);
    }

    public List<Object> findTopProductQuantitiesByMonth() {
        return oneOrderRepository.findTopProductQuantitiesByMonth();
    }

    public List<Object> findWorseProductQuantitiesByMonth() {
        return oneOrderRepository.findWorseProductQuantitiesByMonth();
    }

    public Integer findTotalCostSumByCurrentMonth() {
        return oneOrderRepository.findTotalCostSumByCurrentMonth();
    }

    public long findCurrentMonthOrderCount() {
        return oneOrderRepository.findCurrentMonthOrderCount();
    }

    public long findCurrentWeekOrderCount() {
        return oneOrderRepository.findCurrentWeekOrderCount();
    }

    public void delete(Long id) {
        oneOrderRepository.deleteById(id);
    }
}
