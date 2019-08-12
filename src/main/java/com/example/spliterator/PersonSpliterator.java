package com.example.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

public class PersonSpliterator implements Spliterator<Person> {

    private final Spliterator<String> lineSpliterator;
    private String name;
    private int age;
    private String city;

    PersonSpliterator(Spliterator<String> lineSpliterator) {
        this.lineSpliterator = lineSpliterator;
    }

    /**
     * For our try advance, we have to make sure there are 3 lines to
     * read for creating a Person object
     */
    @Override
    public boolean tryAdvance(Consumer<? super Person> action) {
        if (this.lineSpliterator.tryAdvance(line -> this.name = line) &&
        this.lineSpliterator.tryAdvance(line -> this.age = Integer.parseInt(line)) &&
        this.lineSpliterator.tryAdvance(line -> this.city = line)) {
            Person p = new Person(name, age, city);
            action.accept(p);
            return true;
        }
        return false;
    }

    @Override
    public Spliterator<Person> trySplit() {
        // not supporting parallel stream
        return null;
    }

    @Override
    public long estimateSize() {
        // For us the size will be divided by 3 because 3 lines in our
        // file is one Person Object
        return this.lineSpliterator.estimateSize() / 3;
    }

    @Override
    public int characteristics() {
        // Sending the characteristics of underlying Spliterator
        return this.lineSpliterator.characteristics();
    }
}
