package com.lgda.backend.OneOrder;

import com.lgda.backend.OrderProduct.OrderProduct;
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

  /*  public OneOrder add(OneOrder oneOrder) {
        return this.createOneOrder(oneOrder);
    }*/


    public OneOrder add(OneOrder oneOrder) {
        OneOrder newOneOrder = oneOrderRepository.save(oneOrder);
                return newOneOrder;
    }

   /* public OneOrder createOneOrder(OneOrder oneOrder) {
        System.out.println("-----------create one order -----------");
        System.out.println(oneOrder.getId());



        // Save the OneOrder entity to the database

        List<OrderProduct> orderProducts = oneOrder.getOrderProducts();

        System.out.println("-----------create one order -----------");

        OneOrder savedOrder = oneOrderRepository.save(oneOrder);
        System.out.println(savedOrder.getId());
        for (OrderProduct orderProduct : orderProducts) {
            System.out.println(orderProduct.getQuantity());
            orderProduct.setOneOrder(savedOrder);
            orderProductRepository.save(orderProduct);
        }

        return savedOrder;
    }*/

    public OneOrder update(OneOrder oneOrder, Long id) {
        OneOrder foundOneOrder = getById(id);

        foundOneOrder.setOrderStatus(oneOrder.getOrderStatus());
        foundOneOrder.setOrderProducts(oneOrder.getOrderProducts());
        foundOneOrder.setCreatedAt(oneOrder.getCreatedAt());
        //foundOneOrder.setUser(oneOrder.getUser());
        foundOneOrder.setTotalCost(oneOrder.getTotalCost());

        return oneOrderRepository.save(foundOneOrder);
    }

    public void delete(Long id) {
        oneOrderRepository.deleteById(id);
    }
}
