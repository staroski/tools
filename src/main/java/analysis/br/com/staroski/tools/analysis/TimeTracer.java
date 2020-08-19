package br.com.staroski.tools.analysis;

import java.util.Objects;

public final class TimeTracer {

    public static TimeTracer start(String name) {
        return new TimeTracer(name);
    }

    private final String name;

    private long started;

    public TimeTracer(String name) {
        this.name = Objects.requireNonNull(name);
        reset();
    }

    public long elapsed() {
        return System.currentTimeMillis() - started;
    }

    public void reset() {
        started = System.currentTimeMillis();
    }

    public void trace(String operation) {
        System.err.println(name + " " + operation + " done in " + elapsed() + " ms");
        reset();
    }
}
