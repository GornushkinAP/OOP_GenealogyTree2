package familytree;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public interface NamedEntity <T> extends Comparable<T>, Serializable {

    void addChildren(T child);

    String getName();

    void setName(String name);

    T getPerson(String name);

    Gender getGender();

    LocalDate getBirthYear();

    LocalDate getDeathYear();

    String getRelationship();

    void setRelationship(String relationship);

    void setPeople(List<T> people);

    void sortByName();

    void sortByBirthYear();

    

    
    
    

    

    

    
}
