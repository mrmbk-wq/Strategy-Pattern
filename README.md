# Strategy Pattern — Navigation / Route Planning

Assignment #5, ShP-2216 Software Design Patterns (Astana IT University).

A `Navigator` (Context) plans a route between two points using an interchangeable
`RouteStrategy`: car, walking, bicycle or public transport. The strategy can be
switched at runtime, and the Navigator contains no `if`/`switch` on transport type.

## Project structure

```
src/kz/aitu/strategy/
├── Main.java                          # Client: registers strategies, switches them at runtime
├── context/
│   └── Navigator.java                 # Context: holds a RouteStrategy and delegates to it
├── model/
│   ├── Trip.java                      # Input: origin, destination, distance
│   └── Route.java                     # Output: mode, distance, duration, cost
└── strategy/
    ├── RouteStrategy.java             # Strategy interface
    ├── CarStrategy.java               # Concrete strategy
    ├── WalkStrategy.java              # Concrete strategy
    ├── CycleStrategy.java             # Concrete strategy
    └── PublicTransportStrategy.java   # Concrete strategy added later (extensibility demo)
```

## Running

**IntelliJ IDEA:** File → Open → select this folder. Set the Project SDK to JDK 17
(File → Project Structure → Project), then run `kz.aitu.strategy.Main`.

**Command line (JDK 17+):**

```bash
javac -d out $(find src -name "*.java")
java -cp out kz.aitu.strategy.Main
```

The program first prints the same trip planned with every strategy, then lets you
type `car`, `walk`, `cycle` or `bus` to switch the Navigator's strategy live
(`exit` to quit).

## Sample output

```
=== Runtime strategy switching demo ===
Car               AITU (EXPO) -> Baiterek |    8.1 km |   13 min |     182 KZT
Walk              AITU (EXPO) -> Baiterek |    6.8 km |   82 min |       0 KZT
Bicycle           AITU (EXPO) -> Baiterek |    7.5 km |   30 min |     300 KZT
Public transport  AITU (EXPO) -> Baiterek |    8.5 km |   29 min |     100 KZT

Choose transport [car, walk, cycle, bus] or 'exit':
walk
Navigator now uses: Walk
Walk              AITU (EXPO) -> Baiterek |    6.8 km |   82 min |       0 KZT
```

See [REPORT.md](REPORT.md) for the UML diagram and the Clean Code analysis.
