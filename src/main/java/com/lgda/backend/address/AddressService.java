package com.lgda.backend.address;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAll() {
        return addressRepository.findAll();
    }

    public Address getById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " not found"));
    }

    public Address add(Address address) {
        return addressRepository.save(address);
    }

    public Address update(Address address, Long id) {
        Address foundAddress = getById(id);

        foundAddress.setNumber(address.getNumber());
        foundAddress.setStreetName(address.getStreetName());
        foundAddress.setZipcode(address.getZipcode());
        foundAddress.setCity(address.getCity());

        return addressRepository.save(foundAddress);
    }

    public void delete(Long id) {
        addressRepository.deleteById(id);
    }
}
