# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the lecture repository for the course _Programmieren mit Java_ (TIA25) — a Java programming course taught in German. It contains runnable demo programs that accompany each lecture session. The course documentation lives at [jappuccini.github.io/java-docs](https://jappuccini.github.io/java-docs/production/).

## Build and Run

This is a Maven project targeting Java 21.

```bash
# Compile
mvn compile

# Run a specific demo (example)
mvn exec:java -Dexec.mainClass="main.D01_JavaBasics"
mvn exec:java -Dexec.mainClass="main.D02_ObjectOrientedProgramming"

# Run the Goals App
mvn exec:java -Dexec.mainClass="apps.goal.MainClass"

# Run tests
mvn test

# Run a single test class
mvn test -Dtest=SomeTestClass

# Package
mvn package
```

## Code Architecture

### Package Structure

- **`main`** — Demo entry points, one per lecture (`D01_JavaBasics`, `D02_ObjectOrientedProgramming`, …). Each `main` method is a self-contained walkthrough of the lecture topic, executed top to bottom.
- **`model`** — Domain classes used by the demos. Currently uses plain public fields (no encapsulation enforced yet) for pedagogical simplicity; later demos introduce getters/setters and access modifiers.
- **`utility`** — Static helper classes (e.g., `StringArrayHelper`) used to contrast procedural-style helpers against OOP-style instance methods before the course moves fully to OOP.
- **`apps`** — Larger, multi-class application examples. Currently contains the `goal` sub-package (Goals App), which demonstrates MVC-style separation with `MainClass`, `Model`, `InputController`, `OutputController`, `GoalApp`, and `Team`.

### Lecture–Demo Mapping

Each lecture in `skript/lectureNN.md` links directly to the demo class it covers. New demos are added as `D0N_<TopicName>.java` in `main`, with supporting model/utility classes alongside.

### Pedagogical Progression

The demos deliberately evolve Java idioms across lectures:

1. **D01** — imperative basics: primitives, `Scanner`, `if`/`switch`, loops, arrays (all public fields and procedural style).
2. **D02** — OOP introduction: classes with public fields (`FlightConnection`, `Flight`), static utility helpers (`StringArrayHelper`) vs. instance methods (`StringArray`), then encapsulation via getters/setters (`LightBulb`, `TableLight`).
3. **D03** — Java API: file I/O (`File`, `Scanner`), date/time (`LocalDate`), records (`PersonRecord`), Lombok (`PersonLombok`).
4. **D04** — Inheritance: abstract classes, interfaces (`Light`, `LightBulb`, `FlashLight`, `TableLight`), exceptions (`AlreadyPluggedInException`).
5. **D05** — Sorting: `Comparable`, `Comparator` (`Movie`, `MoviesByRatingDescendingComparator`), `Collections.sort`.
6. **D06** — Maps: `HashMap`, iterating entries (`Student`, `Exam`).
7. **D07** — Optionals: `Optional` API, avoiding null checks.

Later demos will introduce JUnit 5 tests, Mockito, and JavaFX (all already on the classpath via `pom.xml`).
