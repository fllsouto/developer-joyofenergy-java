package uk.tw.energy.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uk.tw.energy.controller.dto.PricePlanCostOutput;
import uk.tw.energy.controller.dto.RecommendPricePlansOutput;
import uk.tw.energy.service.AccountService;
import uk.tw.energy.service.PricePlanService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/price-plans")
public class PricePlanComparatorController {

    public final static String PRICE_PLAN_ID_KEY = "pricePlanId";
    public final static String PRICE_PLAN_COMPARISONS_KEY = "pricePlanComparisons";
    private final PricePlanService pricePlanService;
    private final AccountService accountService;

    public PricePlanComparatorController(PricePlanService pricePlanService, AccountService accountService) {
        this.pricePlanService = pricePlanService;
        this.accountService = accountService;
    }

    @GetMapping("/compare-all/{smartMeterId}")
    public ResponseEntity<PricePlanCostOutput> calculatedCostForEachPricePlan(@PathVariable String smartMeterId) {
        String pricePlanId = accountService.getPricePlanIdForSmartMeterId(smartMeterId);
        Optional<Map<String, BigDecimal>> consumptionsForPricePlans =
                pricePlanService.getConsumptionCostOfElectricityReadingsForEachPricePlan(smartMeterId);

        if (!consumptionsForPricePlans.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var output = new PricePlanCostOutput(pricePlanId, consumptionsForPricePlans.get());

        return ResponseEntity.ok(output);
    }

    @GetMapping("/recommend/{smartMeterId}")
    public ResponseEntity<RecommendPricePlansOutput> recommendCheapestPricePlans(@PathVariable String smartMeterId,
                                                                                           @RequestParam(value = "limit", required = false) Optional<Integer> limit) {
        Optional<Map<String, BigDecimal>> consumptionsForPricePlans =
                pricePlanService.getConsumptionCostOfElectricityReadingsForEachPricePlan(smartMeterId);

        if (!consumptionsForPricePlans.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var output = new RecommendPricePlansOutput(consumptionsForPricePlans.get(), limit);

        return ResponseEntity.ok(output);
    }
}
