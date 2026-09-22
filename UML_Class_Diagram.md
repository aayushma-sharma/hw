# UML Class Diagram

The following class diagram represents all interfaces, classes, fields, methods, and associations currently in the `hw` project.

```mermaid
classDiagram
    direction LR

    class Strategy {
        <<interface>>
        +decideAction(stock: Stock) int
    }

    class Stock {
        +name: String
        +companyPrice: Double
        +prices: List~Double~
        +observers: List~Trader~
        +Stock(name: String, stockPrice: double, prices: List~Double~, companyPrice: Double)
        +addPrice(newPrice: double) void
        +notifyObservers() void
    }

    class Trader {
        -strategy: Strategy
        +Trader(strategy: Strategy)
        +update(stock: Stock) void
    }

    class MomentumStrategy {
        +MomentumStrategy()
        +decideAction(stock: Stock) int
    }

    class MeanReservationStrategy {
        +MeanReservationStrategy()
        +decideAction(stock: Stock) int
    }

    class ValueStrategy {
        +ValueStrategy()
        +decideAction(stock: Stock) int
    }

    class Main {
        +Main()
        +main(args: String[]) void
    }

    Strategy <|.. MomentumStrategy : implements
    Strategy <|.. MeanReservationStrategy : implements
    Strategy <|.. ValueStrategy : implements

    Trader --> Strategy : delegates decisions to
    Trader --> Stock : receives updates
    Stock "1" o-- "0..*" Trader : observers
    Stock ..> List : uses
    Main ..> Stock : creates
    Main ..> Trader : creates
    Main ..> MomentumStrategy : configures
    Main ..> MeanReservationStrategy : configures
    Main ..> ValueStrategy : configures
```

## Relationship Notes

- `Strategy` is the common interface for all trading algorithms.
- Each concrete strategy implements `decideAction(Stock)`.
- `Trader` has one `Strategy` and delegates its decision to that strategy.
- `Stock` maintains zero or more `Trader` observers and calls `update(Stock)` when notified.
- `Main` creates and connects the objects, then adds new prices and publishes notifications.
- The `Stock` to `Trader` association is shown as one stock to many observers.
