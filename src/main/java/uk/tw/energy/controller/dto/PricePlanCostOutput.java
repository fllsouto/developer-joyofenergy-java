package uk.tw.energy.controller.dto;

import java.math.BigDecimal;
import java.util.Map;

public class PricePlanCostOutput {
    // Map<String, Object>

    private String pricePlanId;
    private Map<String, BigDecimal> consumptionsForPricePlans;

    public PricePlanCostOutput(String pricePlanId, Map<String, BigDecimal> consumptionsForPricePlans) {
        this.pricePlanId = pricePlanId;
        this.consumptionsForPricePlans = consumptionsForPricePlans;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pricePlanId == null) ? 0 : pricePlanId.hashCode());
        result = prime * result + ((consumptionsForPricePlans == null) ? 0 : consumptionsForPricePlans.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PricePlanCostOutput other = (PricePlanCostOutput) obj;
        if (pricePlanId == null) {
            if (other.pricePlanId != null)
                return false;
        } else if (!pricePlanId.equals(other.pricePlanId))
            return false;
        if (consumptionsForPricePlans == null) {
            if (other.consumptionsForPricePlans != null)
                return false;
        } else if (!consumptionsForPricePlans.equals(other.consumptionsForPricePlans))
            return false;
        return true;
    }

    

}
