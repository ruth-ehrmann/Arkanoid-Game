# Arkanoid Game – Java Project

This project is a recreation of the classic **Arkanoid** game, developed as part of a university course assignment in Java.

## 🎮 Game Features

- Paddle controlled by keyboard input
- Ball movement with collision physics
- Bricks that disappear when hit
- Score tracking and life counter
- Multiple listeners for handling events (e.g. block removal, ball loss)
- Object-oriented design with clear separation of responsibilities

## 🧱 Architecture Overview

- `collisionDetection` – Handles collision logic and game environment
- `geometryPrimitive` – Classes for geometric shapes: `Point`, `Line`, `Rectangle`
- `hitListener` / `hitNotifier` – Event system for reacting to hits
- `game` – Core game loop and counters
- `Ass5Game.java` – Entry point and game initialization

## 🛠 Technologies Used

- Java
- OOP principles
- Event-driven architecture
- Simple physics and game loop

## 🚀 Getting Started

To run the game:

1. Compile the project using your favorite Java IDE or the provided `build.xml` file.
2. Run the main class:
   ```
   Ass5Game.java
   ```

Make sure all source files are in the correct package structure as provided under `src/`.

---

*This game was developed as part of an academic exercise and reflects principles of basic game development using Java.*
