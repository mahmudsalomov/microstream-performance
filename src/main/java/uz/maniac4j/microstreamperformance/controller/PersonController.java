package uz.maniac4j.microstreamperformance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.maniac4j.microstreamperformance.model.Person;
import uz.maniac4j.microstreamperformance.service.PersonService;

import java.util.List;

@RestController
@RequestMapping("api")
public class PersonController {
    @Autowired
    private PersonService personService;

    @GetMapping("/all")
    public List<Person> all(){
        return personService.findAll();
    }

    @GetMapping("/one/{id}")
    public Person one(@PathVariable long id){
        return personService.findById(id);
    }

    @GetMapping("/add/{name}/{age}")
    public List<Person> add(@PathVariable String name, @PathVariable int age){
        personService.add(new Person(name,age));
//        personService.storeAll();
        return personService.findAll();
    }

    @GetMapping("/add")
    public List<Person> add(){
        personService.add();
//        personService.storeAll();
        return personService.findAll();
    }


    @GetMapping("/delete")
    public void delete(){
        personService.deleteAll();
    }

    @GetMapping("/count")
    public long count(){
        return personService.count();
    }


    @GetMapping("/add/{count}")
    public long adder(@PathVariable long count){
        for (long i = 0; i < count; i++) {
            personService.add();
        }
        return count();
    }

    @GetMapping("/store")
    public List<Person> store(){
        personService.storeAll();
        return personService.findAll();
    }

    @GetMapping("/average")
    public long average(){
        return personService.averageAge();
    }
}
