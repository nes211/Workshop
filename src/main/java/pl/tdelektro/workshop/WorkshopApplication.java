package pl.tdelektro.workshop;

import com.opencsv.CSVReader;
import com.opencsv.ICSVParser;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.TaskRepository;
import pl.tdelektro.workshop.repository.UserRepository;
import pl.tdelektro.workshop.security.Admin;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
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





        List<User> userList = Arrays.asList(
                new User("workshop@tdelektro.pl", adminPassword.getPassword(), "ADMIN"),
                new User("tom@test.com", passwordEncoder.encode("password1")),
                new User("dawid@test.com", passwordEncoder.encode("password2")),
                new User("adalbert@test.com", passwordEncoder.encode("password3"))
        );

        for (User user : userList) {
            userRepository.save(user);
        }

        //Tasks for test
        List<Task> taskList = Arrays.asList(
                new Task("Car inspection"),
                new Task("Oil and filter change"),
                new Task("Tyre change"),
                new Task("Check coolant"),
                new Task("Fix coolant leak"),
                new Task("Check breakes"),
                new Task("Fix breakes")
        );

        for (Task task : taskList) {
            taskRepository.save(task);
        }

        FileReader fileReader = new FileReader("src/main/resources/cars.csv");
        CSVReader csvReader = new CSVReader(fileReader);

        //Header omit
        csvReader.readNext();
        String [] stringLine;

        while((stringLine = csvReader.readNext())!= null){
            Car car = new Car(stringLine[0], stringLine[1]);
            carRepository.save(car);
        }




    }
}
