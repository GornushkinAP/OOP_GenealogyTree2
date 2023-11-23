package familytree;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


// import java.io.Serializable;
// import familytree.NamedEntity;


public class Person implements NamedEntity<Person>{
    private String name;
    private Gender gender;
    private LocalDate birthYear;
    private LocalDate deathYear;
    private String relationship;
    
    private List<Person> children;
   
    public Person(String name, Gender gender, LocalDate birthYear, LocalDate deathYear, String relationship) {
        this.name = name;
        this.gender = gender;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.relationship = relationship;
        
        
        this.children = new ArrayList<>();
    }


    public void addChildren(Person child) {
        children.add(child);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDate getBirthYear() {
        return birthYear;
    }

    public LocalDate getDeathYear() {
        return deathYear;
    }

    public String getRelationship() {
        return relationship;
    }

    public List<Person> getChildren() {
        return children;
    }
    
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Override
    public int compareTo(Person other){
        return this.getName().compareTo(other.getName());
    }

    public void setPeople(List<Person> people) {
        
    }

    @Override
    public void sortByBirthYear(){

    }

    @Override
    public void sortByName(){

    }

    @Override
    public Person getPerson(String name){
        return null;

    }



}
