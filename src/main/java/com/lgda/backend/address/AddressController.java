package com.lgda.backend.address;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @GetMapping
    public List<Address> getAll() {
        return addressService.getAll();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable("id") Long id) {
        return addressService.getById(id);
    }

    @PostMapping
    public Address add(@RequestBody Address address) {
        return addressService.add(address);
    }

    @PutMapping("/{id}")
    public Address update(@RequestBody Address address, @PathVariable("id") Long id) {
        return addressService.update(address, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        addressService.delete(id);
    }

}
