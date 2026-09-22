/**
 * Applies a strategy to stock updates and reports the resulting action.
 */
public class Trader {
    /** Decision strategy used by this trader. */
    private Strategy strategy;

    /**
     * Creates a trader using the supplied decision strategy.
     *
     * @param strategy strategy used to make trading decisions
     */
    public Trader(Strategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Responds to a stock update by printing the strategy's decision.
     *
     * @param stock stock that changed
     */
    public void update(Stock stock) {
        int action = strategy.decideAction(stock);
        if (action == 1) {
            System.out.println("Price Changed of " + stock.name + " Trader: Buying " + stock.name);
        } else if (action == -1) {
            System.out.println("Price Changed of " + stock.name + " Trader: Selling " + stock.name);
        } else {
            System.out.println("Price Not Changed of " + stock.name + " Trader: Holding " + stock.name);
        }
    }

}
