package filemanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import familytree.Person;

public class GenealogyFileManager implements FileIO {
    @Override
    public void writeToFile(String fileName, List<Person> people) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(people);
            System.out.println("Данные успешно записаны в файл " + fileName);
        }
    }

    @Override
    public List<Person> readFromFile(String fileName) throws IOException {
        List<Person> people = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            people = (List<Person>) ois.readObject();
            System.out.println("Данные успешно загружены из файла " + fileName);
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка при чтении данных из файла: " + e.getMessage());
        }
        return people;
    }
}
