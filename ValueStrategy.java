/**
 * Compares the latest market price with the estimated company value.
 */
public class ValueStrategy implements Strategy {
    /** Creates a value-based strategy. */
    public ValueStrategy() {
    }

    /**
     * Evaluates whether the stock appears undervalued or overvalued.
     *
     * @param stock stock to evaluate
     * @return {@code 1} when the latest price is above company value,
     *         {@code -1} when below, or {@code 0} when equal or unavailable
     */
    @Override 
    public int decideAction(Stock stock){
    if (stock.prices.isEmpty()) {
        return 0;
    }

    if (stock.prices.get(stock.prices.size() - 1) > stock.companyPrice) {
        return 1;
    }
    if (stock.prices.get(stock.prices.size() - 1) < stock.companyPrice) {
        return -1;
    }

    return 0;
    }
}