/**
 * Defines the decision rule used by a trader.
 */
public interface Strategy {
    /**
     * Chooses an action for the supplied stock.
     *
     * @param stock stock data to evaluate
     * @return {@code 1} to buy, {@code -1} to sell, or {@code 0} to hold
     */
    int decideAction(Stock stock);
}