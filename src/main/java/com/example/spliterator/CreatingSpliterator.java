package com.example.spliterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Spliterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Creating Person Spliterator for reading person object from a
 * file containing Strings
 *
 */
public class CreatingSpliterator {

    public static void main(String[] args) {

        Path path = Paths.get("D:\\Spallya\\Bit Bucket\\streams-collectors-optionals\\src\\main\\resources\\files\\people.txt");

        try (Stream<String> lines = Files.lines(path)) {
            Spliterator<String> lineSpliterator = lines.spliterator();
            Spliterator<Person> peopleSpliterator = new PersonSpliterator(lineSpliterator);
            Stream<Person> people = StreamSupport.stream(peopleSpliterator, false); // false: no parallel support
            people.forEach(System.out::println);
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }
}
