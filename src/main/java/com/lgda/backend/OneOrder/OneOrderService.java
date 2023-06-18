package com.lgda.backend.OneOrder;

import com.lgda.backend.address.Address;
import com.lgda.backend.address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OneOrderService {

    private final OneOrderRepository oneOrderRepository;
    private final AddressRepository addressRepository;

    public  List<OneOrder> getAll() {
        return oneOrderRepository.findAll();
    }


    public OneOrder getById(Long id) {
        return oneOrderRepository.findById(id).orElseThrow(() ->
                new RuntimeException(id + "not found"));
    }

    public OneOrder add(OneOrder oneOrder) {
        return this.createOneOrder(oneOrder);
    }


    public OneOrder createOneOrder(OneOrder oneOrder) {

        if (oneOrder.getDeliveryAddressId() == null) {
            Address newDeliveryAddress = oneOrder.getDeliveryAddress();
            Address createdAddress = addressRepository.save(newDeliveryAddress);
            oneOrder.setDeliveryAddress(createdAddress);
        }
        if (oneOrder.getBillingAddressId() == null) {
            Address newBillingAddress = oneOrder.getBillingAddress();
            Address createdAddress = addressRepository.save(newBillingAddress);
            oneOrder.setBillingAddress(createdAddress);
        }

        // Save the OneOrder entity to the database
        return oneOrderRepository.save(oneOrder);
    }






    public OneOrder update(OneOrder oneOrder, Long id) {
        OneOrder foundOneOrder = getById(id);

        foundOneOrder.setId(oneOrder.getId());
        foundOneOrder.setOrderStatus(oneOrder.getOrderStatus());
        foundOneOrder.setOrderProducts(oneOrder.getOrderProducts());
        foundOneOrder.setCreatedAt(oneOrder.getCreatedAt());
        foundOneOrder.setUser(oneOrder.getUser());
        foundOneOrder.setTotalCost(oneOrder.getTotalCost());
        foundOneOrder.setBillingAddress(oneOrder.getBillingAddress());
        foundOneOrder.setDeliveryAddress(oneOrder.getDeliveryAddress());

        return oneOrderRepository.save(foundOneOrder);
    }

    public void delete(Long id) {
        oneOrderRepository.deleteById(id);
    }
}
