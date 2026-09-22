import java.util.ArrayList;
import java.util.Arrays;

/**
 * Runs examples of traders responding to changing stock prices.
 */
public class Main {
    /** Creates the demonstration entry point. */
    public Main() {
    }

    /**
     * Creates a stock, registers all strategies, and publishes several updates.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        /** Stock used for the strategy demonstration. */
        Stock stock = new Stock(
            "ACME",
            105.0,
            new ArrayList<>(Arrays.asList(98.0, 100.0, 103.0, 108.0)),
            105.0
        );

        /** Register one trader for each available strategy. */
        stock.observers.add(new Trader(new MomentumStrategy()));
        stock.observers.add(new Trader(new MeanReservationStrategy()));
        stock.observers.add(new Trader(new ValueStrategy()));

        /** Publish the initial price history before any new prices arrive. */
        System.out.println("Initial prices: " + stock.prices);
        stock.notifyObservers();

        /** New prices applied one at a time to demonstrate rolling updates. */
        double[] updatedPrices = {101.0, 95.0, 90.0, 110.0, 105.0};
        for (double newPrice : updatedPrices) {
            stock.addPrice(newPrice);
            System.out.println("\nAdded price: " + newPrice);
            System.out.println("Current prices: " + stock.prices);
            stock.notifyObservers();
        }
    }
}
