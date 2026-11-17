# 🏢 Elevator System Design — Low-Level Design (LLD)

A multi-elevator scheduling system built in Java that simulates real-world elevator behaviour using classic Gang-of-Four (GoF) design patterns. Each elevator runs on its own thread and processes requests concurrently via dual priority queues (SCAN / Elevator algorithm).

---

## 📁 Project Structure

```
elevatorSystemDesign/
├── src/
│   ├── elevatorSystemDemo.java          # Entry point / driver
│   ├── entities/
│   │   ├── elevator.java                # Core elevator entity (Runnable)
│   │   └── request.java                 # Request value object
│   ├── enums/
│   │   ├── direction.java               # UP | DOWN | IDLE
│   │   └── requestType.java             # INTERNAL | EXTERNAL
│   ├── observer/
│   │   ├── elevatorObserver.java        # Observer interface
│   │   └── elevatorDisplay.java         # Concrete observer (display)
│   ├── service/
│   │   └── elevatorSystem.java          # Façade / system controller
│   ├── states/
│   │   ├── elevatorState.java           # State interface
│   │   ├── idleState.java               # Concrete: Idle
│   │   ├── movingUp.java                # Concrete: Moving Up
│   │   └── movingDown.java              # Concrete: Moving Down
│   └── strategy/
│       ├── elevatorStrategy.java        # Strategy interface
│       └── nearestElevator.java         # Concrete: Nearest-elevator selector
└── out/                                 # Compiled .class files
```

---

## 🧠 System Overview — What is happening?

### The Big Picture

```
User calls requestElevator(floor, direction)
         │
         ▼
 elevatorSystem (Façade)
         │  uses Strategy
         ▼
 nearestElevator.getElevator(...)      ← picks the best elevator
         │
         ▼
 elevator.addRequest(request)
         │  delegates to current State
         ▼
 idleState / movingUp / movingDown
 adds floor to upQueue or downQueue
         │
         ▼
 elevator.run() loop (every 500 ms)
   calls state.move(this)
         │  polls from priority queue
         ▼
 elevator.setFloor(nextFloor)
         │  notifies Observers
         ▼
 elevatorDisplay prints: "Elevator X is at floor Y"
```

---

## ⚙️ How it Works — Step by Step

### 1. Bootstrap (`elevatorSystemDemo`)

```java
elevatorSystem elevatorSystem = new elevatorSystem(2);  // 2 elevators
elevatorSystem.start();                                  // kick off threads
```

- Creates 2 `elevator` instances, each attached to a shared `elevatorDisplay` observer.
- Submits each elevator to a fixed-thread `ExecutorService` — every elevator is a `Runnable` ticking every **500 ms**.

---

### 2. Receiving a Request

**External Request** (from outside the elevator — a hall button press):
```java
elevatorSystem.requestElevator(3, direction.UP);
// → creates request(floor=3, direction=UP, type=EXTERNAL)
// → passed to nearestElevator strategy to pick which elevator handles it
```

**Internal Request** (from inside the elevator — cabin floor button):
```java
elevatorSystem.selectFloor("elevator 1", 5);
// → creates request(floor=5, direction=IDLE, type=INTERNAL)
// → sent directly to the named elevator, bypassing strategy
```

---

### 3. Dispatching via Strategy

`nearestElevator.getElevator()` runs only for EXTERNAL requests:

1. Iterates over all elevators.
2. Skips any elevator whose **direction conflicts** with the requested direction (won't pick a DOWN-moving elevator for an UP request).
3. Among eligible elevators (same direction OR idle), picks the one with **minimum absolute floor distance** to the target floor.

This is the classic *nearest-car* heuristic used in real elevator firmware.

---

### 4. Queueing via State (SCAN Algorithm)

Once the right elevator is chosen, `elevator.addRequest(request)` is called, which **delegates immediately to the current state**:

| Current State | Target > Current Floor | Target < Current Floor |
|---|---|---|
| `idleState` | → `upQueue` | → `downQueue` |
| `movingUp` | → `upQueue` (keep going up) | → `downQueue` (handle on way back) |
| `movingDown` | → `downQueue` (keep going down) | → `upQueue` (handle on way back) |

- **`upQueue`** — `PriorityQueue<Integer>` (min-heap): serves floors in ascending order.
- **`downQueue`** — `PriorityQueue<Integer>(Comparator.reverseOrder())` (max-heap): serves floors in descending order.

This replicates the **SCAN / Elevator algorithm** — the elevator sweeps in one direction, serving all queued floors, then reverses.

---

### 5. The Movement Loop

Each elevator runs in its own thread:

```java
public void run() {
    while (isRunning) {
        move();               // delegate to current state
        Thread.sleep(500);    // simulate travel time
    }
}
```

**`idleState.move()`** — checks if any queue has work, transitions to `movingDown` or `movingUp`.

**`movingUp.move()`** — polls the next floor from `upQueue` via `setFloor()`. When `upQueue` empties → transitions back to `idleState`.

**`movingDown.move()`** — polls the next floor from `downQueue` via `setFloor()`. When `downQueue` empties → transitions back to `idleState`.

---

### 6. Notifications via Observer

Every time `setFloor()` is called, `notifyObservers()` fires:

```java
public void setFloor(int floor) {
    this.currentFloor = floor;
    notifyObservers();   // push to all registered observers
}
```

`elevatorDisplay.update(elevator)` then prints:
```
Elevator: elevator 1 is at floor 5
```

New displays (audio announcements, digital boards, analytics loggers) can be added without touching `elevator.java` — just implement `elevatorObserver` and register it.

---

## 🎨 Design Patterns Used

### 1. 🔁 State Pattern
**Files:** `elevatorState.java`, `idleState.java`, `movingUp.java`, `movingDown.java`

**What:** Encapsulates each elevator state (Idle, MovingUp, MovingDown) as its own class. The elevator delegates `move()` and `addRequest()` to whatever state object it currently holds.

**Why:**
- Eliminates sprawling `if/else` or `switch` blocks checking current direction inside `elevator.java`.
- State transitions are **self-contained** — `idleState` knows when to become `movingUp`, not the elevator itself.
- Adding a new state (e.g., `maintenanceState`, `doorOpenState`) requires zero changes to `elevator.java`.

```
elevator ──holds──▶ elevatorState (interface)
                        ├── idleState
                        ├── movingUp
                        └── movingDown
```

---

### 2. 👁️ Observer Pattern
**Files:** `elevatorObserver.java`, `elevatorDisplay.java`, `elevator.java`

**What:** `elevator` maintains a list of `elevatorObserver` listeners and notifies all of them whenever its floor changes.

**Why:**
- Decouples the elevator's movement logic from any UI/display/logging concern.
- Multiple independent displays (floor indicator, audio, logging system) can observe a single elevator.
- Adding a new notification channel never touches `elevator.java`.

```
elevator ──notifies──▶ elevatorObserver (interface)
                            └── elevatorDisplay
                            └── (future: audioAnnouncer, analyticsLogger, ...)
```

---

### 3. 🧩 Strategy Pattern
**Files:** `elevatorStrategy.java`, `nearestElevator.java`, `elevatorSystem.java`

**What:** The selection algorithm for "which elevator should handle this external request?" is hidden behind an interface. `elevatorSystem` holds a reference to `elevatorStrategy` and calls `getElevator()`.

**Why:**
- The dispatching algorithm is a first-class plug-in — swap `nearestElevator` for `roundRobin`, `leastLoaded`, or `zoneBasedStrategy` with **one line change** in `elevatorSystem`.
- Satisfies the Open/Closed Principle: open for extension, closed for modification.
- Makes unit-testing each algorithm trivially easy.

```
elevatorSystem ──uses──▶ elevatorStrategy (interface)
                              └── nearestElevator
                              └── (future: roundRobin, zoneStrategy, ...)
```

---

### 4. 🏛️ Façade Pattern
**File:** `elevatorSystem.java`

**What:** `elevatorSystem` is the single entry-point for the outside world. The caller never touches individual elevators, strategy objects, or thread pools directly.

**Why:**
- Hides all internal wiring (thread management, strategy invocation, observer registration).
- The API surface is intentionally tiny: `start()`, `requestElevator()`, `selectFloor()`.
- Clients (like `elevatorSystemDemo`) are shielded from internal changes.

---

### 5. 🧵 Active Object / Thread-per-Elevator
**Files:** `elevator.java` (implements `Runnable`), `elevatorSystem.java` (uses `ExecutorService`)

**What:** Each elevator is submitted to a `FixedThreadPool` and runs its own continuous `while(isRunning)` loop, polling its queues every 500 ms.

**Why:**
- Elevators are **physically independent** — they should not block each other.
- The `ExecutorService` manages thread lifecycle cleanly (no raw `Thread` management).
- `addRequest()` is `synchronized` to protect shared queue state from concurrent writes by both the user thread and the elevator's own thread.

---

## 🔄 Data Structures — Why These Queues?

| Queue | Type | Order | Purpose |
|---|---|---|---|
| `upQueue` | `PriorityQueue<Integer>` (min-heap) | Ascending | Serves floors going UP in order (lowest first) |
| `downQueue` | `PriorityQueue<Integer>(reverseOrder)` (max-heap) | Descending | Serves floors going DOWN in order (highest first) |

Using priority queues instead of plain lists ensures:
- **O(log n) insertion** of new floor requests.
- **O(1) peek / O(log n) removal** of the next floor to serve.
- The elevator never backtracks mid-sweep — it always moves optimally in one direction before reversing.

This is the textbook **SCAN (Elevator) disk-scheduling algorithm** applied to vertical transport.

---

## 🔀 Request Types

| Type | Who sends | Direction field | Dispatched via |
|---|---|---|---|
| `EXTERNAL` | Hall button (outside elevator) | UP or DOWN | Strategy (nearestElevator selects) |
| `INTERNAL` | Cabin button (inside elevator) | IDLE | Directly to named elevator |

INTERNAL requests bypass strategy because the user is already inside a specific elevator.

---

## 📊 Class Relationship Diagram

```
elevatorSystemDemo
        │
        ▼
  elevatorSystem (Façade)
     │         │
     │         └──── elevatorStrategy ◄── nearestElevator
     │
     ├── elevator (Runnable) ──has──► elevatorState
     │       │                           ├── idleState
     │       │                           ├── movingUp
     │       │                           └── movingDown
     │       │
     │       ├── upQueue   (PriorityQueue min-heap)
     │       ├── downQueue (PriorityQueue max-heap)
     │       │
     │       └──notifies──► elevatorObserver ◄── elevatorDisplay
     │
     └── request
           ├── targetFloor : int
           ├── direction   : UP | DOWN | IDLE
           └── requestType : INTERNAL | EXTERNAL
```

---

## 🚀 Running the Demo

Compile from the `src/` directory:

```bash
cd src
javac -d ../out elevatorSystemDemo.java entities/*.java enums/*.java observer/*.java service/*.java states/*.java strategy/*.java
cd ../out
java elevatorSystemDemo
```

**Demo Scenario (`elevatorSystemDemo.java`):**

```
1. requestElevator(3, UP)       — External: someone on floor 3 wants to go up
2. selectFloor("elevator 1", 5) — Internal: elevator 1 passenger selects floor 5
3. requestElevator(2, DOWN)     — External: someone on floor 2 wants to go down
4. selectFloor("elevator 2", 1) — Internal: elevator 2 passenger selects floor 1
```

---

## 💡 Design Decisions & Trade-offs

| Decision | Rationale |
|---|---|
| **State pattern over flags** | `if (direction == UP && state == MOVING)` chains become unmaintainable fast; states make each behaviour self-documenting. |
| **Two separate priority queues** | Allows servicing both directions independently without sorting on every access. |
| **Strategy for dispatching** | The "best elevator" problem is an active research area; keeping it pluggable means you never need to refactor core code to try a new algorithm. |
| **Observer for display** | Floor announcements are a cross-cutting concern; coupling them to elevator movement logic would violate SRP. |
| **Façade for the system** | The demo/client code should not understand threads, strategy, or observer wiring — that's the system's internal concern. |
| **`synchronized` on `addRequest`** | The elevator's movement thread and the main thread both touch the queues; synchronization prevents race conditions without a full lock on the entire elevator. |

---

## 🛠️ Extension Points

- **New dispatch algorithm** → implement `elevatorStrategy`, inject into `elevatorSystem`. 
- **New elevator state** (e.g., `doorOpenState`, `maintenanceState`) → implement `elevatorState`, transition from an existing state.  
- **New display/notification** → implement `elevatorObserver`, call `elevator.addObserver(...)`.  
- **More elevators** → pass a higher number to `new elevatorSystem(N)`.

