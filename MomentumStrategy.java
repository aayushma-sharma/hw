/**
 * Buys during a strictly increasing price trend and sells during a strictly
 * decreasing price trend.
 */
public class MomentumStrategy implements Strategy {
    /** Creates a momentum strategy. */
    public MomentumStrategy() {
    }

    /**
     * Evaluates whether every consecutive price movement has the same direction.
     *
     * @param stock stock to evaluate
     * @return {@code 1} for increasing momentum, {@code -1} for decreasing
     *         momentum, or {@code 0} for a mixed or insufficient trend
     */
    @Override 
    public int decideAction(Stock stock){
    boolean increasing = true;
    boolean decreasing = true;

    for (int i = 1; i < stock.prices.size(); i++) {
        if (stock.prices.get(i) <= stock.prices.get(i - 1)) {
            increasing = false;
        }

        if (stock.prices.get(i) >= stock.prices.get(i - 1)) {
            decreasing = false;
        }
    }

    if (increasing) {
        return 1;
    }

    if (decreasing) {
        return -1;
    }

    return 0;
    }
}
