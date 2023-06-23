package com.lgda.backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " not found"));
    }

    public User update(User user, Long id) {
        User foundUser = getById(id);

        foundUser.setFirstname(user.getFirstname());
        foundUser.setLastname(user.getLastname());
        foundUser.setEmail(user.getEmail());
        foundUser.setBillingAddress(user.getBillingAddress());
        foundUser.setDeliveryAddress(user.getDeliveryAddress());
        foundUser.setOrderList(user.getOrderList());

        return repository.save(foundUser);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
