package uk.tw.energy.controller.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RecommendPricePlansOutput {
    
    private List<Map.Entry<String, BigDecimal>> recommendations;
    
    public RecommendPricePlansOutput(Map<String, BigDecimal> recommendations, Optional<Integer> limit) {
        this.recommendations = new ArrayList<>(recommendations.entrySet());
        sortRecommendationsByPriceInAscendingOrder();

        if (limit.isPresent() && limit.get() < recommendations.size()) {
            this.recommendations = this.recommendations.subList(0, limit.get());
        }
    }

    public void sortRecommendationsByPriceInAscendingOrder() {
        this.recommendations.sort(Comparator.comparing(Map.Entry::getValue));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((recommendations == null) ? 0 : recommendations.hashCode());
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
        RecommendPricePlansOutput other = (RecommendPricePlansOutput) obj;
        if (recommendations == null) {
            if (other.recommendations != null)
                return false;
        } else if (!recommendations.stream().allMatch(recomentation -> other.recommendations.contains(recomentation)))
            return false;
        return true;
    }

    
}
