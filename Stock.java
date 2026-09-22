import java.util.ArrayList;
import java.util.List;

/**
 * Represents a stock and the traders observing it.
 */
public class Stock { 
    /** Stock ticker or company name. */
    public String name;
    /** Current estimated company value. */
    public Double companyPrice;
    /** Recent market prices, ordered from oldest to newest. */
    public List<Double> prices;
    /** Traders that receive stock updates. */
    public List<Trader> observers = new ArrayList<>();

    /**
     * Creates a stock with its current market history and estimated value.
     *
     * @param name stock ticker or company name
     * @param stockPrice current stock price
     * @param prices recent market prices
     * @param companyPrice estimated company value
     */
    public Stock(String name, double stockPrice, List<Double> prices, Double companyPrice) {
        this.name = name;
        this.prices = prices;
        this.companyPrice = companyPrice;
    }

    /**
     * Adds a new price while keeping the existing history size.
     *
     * @param newPrice newest price
     */
    public void addPrice(double newPrice) {
        if (prices.isEmpty()) {
            prices.add(newPrice);
            return;
        }

        for (int i = 0; i < prices.size() - 1; i++) {
            prices.set(i, prices.get(i + 1));
        }

        prices.set(prices.size() - 1, newPrice);
    }

    /** Notifies every registered trader about the current stock state. */
    public void notifyObservers(){
        for (Trader observer : observers) {
            observer.update(this);
        }
    }

}
