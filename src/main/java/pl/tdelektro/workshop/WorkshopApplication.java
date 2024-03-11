package pl.tdelektro.workshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.TaskRepository;
import pl.tdelektro.workshop.repository.UserRepository;
import pl.tdelektro.workshop.security.Admin;

import java.io.FileReader;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
@ComponentScan(basePackages = "pl.tdelektro")
public class WorkshopApplication implements CommandLineRunner {


    private UserRepository userRepository;
    private CarRepository carRepository;
    private TaskRepository taskRepository;
    private PasswordEncoder passwordEncoder;
    private Admin adminPassword;

    public static void main(String[] args) {
        SpringApplication.run(WorkshopApplication.class, args);
    }


    //Users for test
    @Override
    public void run(String... args) throws Exception {

        //User class initial data from JSON file
        FileReader fileReader = new FileReader("src/main/resources/users.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<User> userList = objectMapper.readValue(fileReader, new TypeReference<List<User>>() {});
        for(User user : userList){
            User userToSave = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()));
            userRepository.save(userToSave);
        }
        userRepository.save(new User("workshop@tdelektro.pl", adminPassword.getPassword(), "ADMIN"));


        //Task class initial data from XML
        FileReader xmlFile = new FileReader("src/main/resources/tasks.xml");
        XmlMapper xmlMapper = new XmlMapper();

        List<Task> taskList = xmlMapper.readValue(xmlFile, List.class);
        System.out.println(taskList.get(0));


        taskRepository.save(taskList.get(0));

        //Car class initial data from CSV file
        fileReader = new FileReader("src/main/resources/cars.csv");
        CSVReader csvReader = new CSVReader(fileReader);

        //Header omit
        csvReader.readNext();
        String[] stringLine;

        while ((stringLine = csvReader.readNext()) != null) {
            Car car = new Car(stringLine[0], stringLine[1]);
            carRepository.save(car);
        }


    }
}
