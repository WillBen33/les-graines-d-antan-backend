package com.lgda.backend.OneOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OneOrderController {

    private final OneOrderService oneOrderService;

    @GetMapping
    public List<OneOrder> getAll() {
        return oneOrderService.getAll();
    }

    @GetMapping("/{id}")
    public OneOrder getById(@PathVariable("id") Long id) {
        return oneOrderService.getById(id);
    }

    @PostMapping
    public OneOrder add(@RequestBody OneOrder oneOrder) {
        return oneOrderService.add(oneOrder);
    }

    @PutMapping("/{id}")
    public OneOrder update(@RequestBody OneOrder oneOrder, @PathVariable("id") Long id) {
        return oneOrderService.update(oneOrder, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        oneOrderService.delete(id);
    }

}
