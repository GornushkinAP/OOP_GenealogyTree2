import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import familytree.FamilyTree;
import familytree.Gender;
import familytree.NamedEntity;
import familytree.Person;
import filemanager.GenealogyFileManager;


public class Main {
    public static void main(String[] args) {
        FamilyTree<Person> familyTree = new FamilyTree<>();
        Scanner scanner = new Scanner(System.in);
        GenealogyFileManager fileManager = new GenealogyFileManager();

         List<Person> loadedPeople = new ArrayList<>();
        try {
            loadedPeople = fileManager.readFromFile("genealogy.dat");
            System.out.println("Загруженные данные из файла:");
            for (Person people : loadedPeople) {
                System.out.println("ФИО: " + people.getName());
                if (people instanceof Person) {
                    Person person = (Person) people;
                    System.out.println("Пол: " + person.getGender());
                    System.out.println("Дата рождения: " + person.getBirthYear());
                    System.out.println("Степень родства: " + person.getRelationship());
                    System.out.println("Дети: " + person.getChildren());
            } 
    }
}
        catch (IOException e) {
            System.err.println("Ошибка при чтении данных из файла: " + e.getMessage());
        }
        familyTree.setPeople(loadedPeople);

        boolean continueSorting = true;
        while (continueSorting) {
            System.out.println("Выберите опцию:");
            System.out.println("1. Добавить информацию о человеке");
            System.out.println("2. Поиск информации о человеке");
            System.out.println("3. Выберите способ сортировки");
            System.out.println("4. Выйти");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addPersonInteractive(familyTree, scanner, fileManager);
                    break;
                case 2:
                    searchPersonInteractive(familyTree, scanner);
                    break;
                case 3:
                    chooseSortingMethod(familyTree, scanner);
                    break;
                case 4:
                    continueSorting = false;
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
            }
        }

        scanner.close();
    }

    private static void addPersonInteractive(FamilyTree<Person> familyTree, Scanner scanner, GenealogyFileManager fileManager) {
        while (true) {
            System.out.println("Введите информацию о человеке (или 'стоп' для выхода):");
            System.out.print("Фамилия, имя, отчество: ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("стоп")) {
                break;
            }
            
            System.out.print("Пол (Мужской/Женский): ");
            String genderStr = scanner.next();
            Gender gender = Gender.valueOf(genderStr);
            System.out.print("Год рождения(в формате: гггг-мм-дд): ");
            String birthYearStr = scanner.next();
            LocalDate birthYear = LocalDate.parse(birthYearStr);
            scanner.nextLine(); 

            System.out.print("Степень родства: ");
            String relationship = scanner.nextLine();

            Person person = new Person(name, gender, birthYear, birthYear, relationship);

        
            System.out.print("Сколько детей у этого человека? ");
            int numChildren = scanner.nextInt();
            scanner.nextLine(); 

            for (int i = 0; i < numChildren; i++) {
                System.out.println("Введите информацию о ребенке " + (i + 1) + ":");
                System.out.print("Фамилию, имя, отчество: ");
                String childName = scanner.nextLine();
                System.out.print("Пол (Мужской/Женский): ");
                String childGenderStr = scanner.nextLine();
                Gender childGender = Gender.valueOf(childGenderStr);
                System.out.print("Дату рождения(в формате: гггг-мм-дд): ");
                String childBirtYearStr = scanner.nextLine();
                LocalDate childBirthYear = LocalDate.parse(childBirtYearStr);
                            

                Person child = new Person(childName, childGender, birthYear, childBirthYear, relationship);
                person.addChildren(child);
            }

            familyTree.addPerson(person);
            System.out.println("Информация добавлена.");
        }
            System.out.println("Сохранить данные в файл (да/нет)? ");
            String saveData = scanner.nextLine().toLowerCase();
            if (saveData.equals("да")) {
                try {
                    fileManager.writeToFile("genealogy.dat", familyTree.getPeople());
                } catch (IOException e) {
                    System.err.println("Ошибка при сохранении данных в файл: " + e.getMessage());
                }
            }
            scanner.close();
        }
    

    private static void searchPersonInteractive(FamilyTree<Person> familyTree, Scanner scanner) {
        System.out.println("Введите фамилию, имя и отчество, чтобы получить информацию о нем:");
        String searchName = scanner.nextLine();
        Person person = familyTree.getPerson(searchName);

        if (person != null) {
            System.out.println("Информация о человеке:");
            System.out.println("Фамилия, имя, отчество: " + person.getName());
            System.out.println("Пол: " + person.getGender());
            System.out.println("Дата рождения: " + person.getBirthYear());
            System.out.println("Степень родства: " + person.getRelationship());
            System.out.println("Дети: ");
            for (Person child : person.getChildren()) {
                System.out.println(child.getName());
            }
        } else {
            System.out.println("Человек не найден.");
        }
    }


    private static void chooseSortingMethod(FamilyTree<Person> familyTree, Scanner scanner){
        System.out.println("Выберите способ сортировки:");
        System.out.println("1. По имени");
        System.out.println("2. По дате рождения");

        int choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
                familyTree.sortByName();
                printSortedData(familyTree);
                break;
            case 2:
                familyTree.sortByBirthYear();
                printSortedData(familyTree);
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте еще раз.");
        }
    }


    private static void printSortedData(FamilyTree<Person> familyTree) {
        for (Person person : familyTree) {
            System.out.println("ФИО: " + person.getName());
            System.out.println("Пол: " + person.getGender());
            System.out.println("Дата рождения: " + person.getBirthYear());
            System.out.println("Степень родства: " + person.getRelationship());
            System.out.println("Дети: ");
            for (Person child : person.getChildren()) {
                System.out.println(child.getName());
            }
        }   
           
        
        familyTree.sortByName(); 
        for (Person person : familyTree) {
            
        }
        
        familyTree.sortByBirthYear(); 
        for (Person person : familyTree) {
    
        }

       
    }
}

