package uz.maniac4j.microstreamperformance.service;

import one.microstream.reflect.ClassLoaderProvider;
import one.microstream.storage.embedded.types.EmbeddedStorage;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.maniac4j.microstreamperformance.model.Person;
import uz.maniac4j.microstreamperformance.repository.PersonRepository;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PersonService implements PersonRepository {
    private final List<Person> people=new ArrayList<>();
//    private final EmbeddedStorageManager storage=EmbeddedStorage.start(
//            this.people,
//            Paths.get("/home/kali/Documents/microstream-performance/microstream_data"));

    private final EmbeddedStorageManager storage = EmbeddedStorage.Foundation(Paths.get("/home/kali/Documents/microstream-performance/microstream_data"))
            .onConnectionFoundation(cf -> cf.setClassLoaderProvider(ClassLoaderProvider.New(
                    Thread.currentThread().getContextClassLoader())))
            .start(this.people);

    @Value("${microstream.store.location}")
    String location;



    public String generateRandomPassword(int len) {
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
                +"lmnopqrstuvwxyz!@#$%&";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        return sb.toString();
    }
    private final Random random=new Random();



//    public PersonService(@Value("${microstream.store.location}") String location)
//    {
//        super();
//
//        this.people = new ArrayList<>();
//
//        this.storage   = EmbeddedStorage.start(
//                this.people,
//                Paths.get(location)
//        );
//    }
    @Override
    public void add(Person person) {
        person.setId((long) (people.size() + 1));
        this.people.add(person);
        this.storeAll();
    }

    public void add(){
        Person person1=new Person();
        person1.setId((long) (people.size() + 1));
        person1.setAge(random.nextInt(100));
        person1.setName(generateRandomPassword(random.nextInt(14)));
        this.people.add(person1);
        this.storeAll();
    }

    @Override
    public List<Person> findAll() {
        return this.people;
    }

    @Override
    public List<Person> findByName(String name) {
        return this.people.stream()
                .filter(c -> c.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public Person findById(Long id) {
        System.out.println(people.size());
        return this.people.stream()
                .filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void deleteAll() {
        this.people.clear();
        this.storeAll();
    }

    @Override
    public void storeAll() {
        this.storage.store(this.people);
    }

    public long count(){
        System.out.println(people.size());
//        System.out.println(people.get(2));
//        Person person=people.get(2);
//        System.out.println(person);
//        System.out.println(new Person(131231L,"AA",12));
        return people.size();
    }

    public long averageAge(){
        long sum=0;
        for (Person person : people) {
            sum+=person.getAge();
        }
        return sum/people.size();
    }
}
