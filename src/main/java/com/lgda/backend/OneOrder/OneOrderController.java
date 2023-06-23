package com.lgda.backend.OneOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
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

    @GetMapping("/top-product-quantities")
    public List<Object> getTopProductQuantitiesByMonth() {
        return oneOrderService.findTopProductQuantitiesByMonth();
    }

    @GetMapping("/worse-product-quantities")
    public List<Object> getWorseProductQuantitiesByMonth() {
        return oneOrderService.findWorseProductQuantitiesByMonth();
    }

    @GetMapping("/total-sales-month")
    public Integer findTotalCostSumByCurrentMonth() {
        return oneOrderService.findTotalCostSumByCurrentMonth();
    }

    @GetMapping("/total-orders-month")
    public long findCurrentMonthOrderCount() {
        return oneOrderService.findCurrentMonthOrderCount();
    }

    @GetMapping("/total-orders-week")
    public long findCurrentWeekOrderCount() {
        return oneOrderService.findCurrentWeekOrderCount();
    }

}
