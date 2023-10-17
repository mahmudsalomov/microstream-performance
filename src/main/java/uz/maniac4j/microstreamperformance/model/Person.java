package uz.maniac4j.microstreamperformance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(Long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    klk
}
