/**
 * Buys or sells based on the latest price compared with its historical mean.
 */
public class MeanReservationStrategy implements Strategy {
    /** Creates a mean-reversion strategy. */
    public MeanReservationStrategy() {
    }

    /**
     * Compares the latest price with the average price in the history.
     *
     * @param stock stock to evaluate
     * @return {@code 1} when above average, {@code -1} when below average,
     *         or {@code 0} when equal or unavailable
     */
    @Override 
    public int decideAction(Stock stock){
    if (stock.prices.isEmpty()) {
        return 0;
    }

    double sum = stock.prices.get(0);

    for (int i = 1; i < stock.prices.size(); i++) {
        sum += stock.prices.get(i);
        }
        double average = sum / stock.prices.size();
        double latestPrice = stock.prices.get(stock.prices.size() - 1);

    if (latestPrice > average) {
        return 1;
    }
    if (latestPrice < average) {
        return -1;
    }

    return 0;
    }
}