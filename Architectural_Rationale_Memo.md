# Architectural Rationale Memo

## System Overview

This project models a small stock-trading system. A `Stock` stores the recent price history and estimated company value. A `Trader` receives stock updates and delegates its decision to a replaceable `Strategy`. The available strategies are `MomentumStrategy`, `MeanReservationStrategy`, and `ValueStrategy`.

The architecture separates market data from decision-making and notification. `Main` assembles the objects and demonstrates the system with several rolling price updates.

## Pattern Choices

### Strategy Pattern

The `Strategy` interface defines one operation:

```java
int decideAction(Stock stock)
```

Each concrete strategy implements a different trading rule:

- `MomentumStrategy` buys when prices are strictly increasing and sells when they are strictly decreasing.
- `MeanReservationStrategy` compares the latest price with the historical average.
- `ValueStrategy` compares the latest market price with the estimated company value.

`Trader` depends on the `Strategy` interface rather than a concrete strategy. This allows a trader to be configured with a different decision rule without changing the `Trader` class. It also keeps each algorithm focused and makes new strategies easy to add.

The main trade-off is that the system has several small classes and uses indirection for a simple decision. That is worthwhile here because the trading rules are expected to vary. For a very small program with one permanent rule, a direct conditional might be shorter, but it would become harder to extend and test as more rules were added.

### Observer Pattern

`Stock` acts as the subject and maintains a collection of `Trader` observers. When its price history changes, `notifyObservers()` calls `update(stock)` on every registered trader. The traders then evaluate the current state using their configured strategies.

This avoids coupling `Stock` to specific trading algorithms. The stock only knows that its observers are `Trader` objects; it does not need to know whether a trader uses momentum, mean reversion, or company-value analysis. Additional traders can be registered without modifying the stock update logic.

The trade-off is that notification is an explicit two-step operation: the caller updates the stock and then calls `notifyObservers()`. A more automatic design could make `addPrice` notify observers immediately, but keeping those operations separate makes batch updates and controlled notifications possible. It also makes the demonstration behavior clear.

## Responsibilities and Coupling

- `Stock` owns market state, price-history updates, and observer notification.
- `Trader` translates a strategy result into a user-visible action.
- `Strategy` defines the decision contract.
- Concrete strategy classes contain only their own calculation rules.
- `Main` is the composition root and demonstration entry point.

The design uses composition instead of inheritance for trader behavior. A `Trader` has a `Strategy`, so behavior can be selected at runtime. Concrete strategies implement the same interface and remain substitutable wherever a `Strategy` is required.

## Data and Update Trade-offs

The stock keeps a fixed-size rolling history. When a new price is added, older entries shift left and the newest price replaces the final entry. This keeps the amount of data used by the strategies bounded and makes the recent trend easy to inspect.

The current implementation uses public fields on `Stock` for straightforward classroom visibility. In production code, private fields with accessors or carefully scoped update methods would better protect invariants, such as preventing an invalid company value or an unintended replacement of the observer list.

The implementation returns simple integer action codes: `1` for buy, `-1` for sell, and `0` for hold. This is compact and easy to demonstrate, but an enum such as `Action.BUY`, `Action.SELL`, and `Action.HOLD` would be more self-documenting and would reduce the risk of passing an invalid number.

## Conclusion

The combination of Strategy and Observer keeps the two changing concerns independent: trading algorithms can change independently of stock notifications, and the set of interested traders can change independently of those algorithms. The design adds a small amount of structure compared with a single procedural implementation, but the separation improves extensibility, readability, and testability for the current multi-strategy example.
