package pl.tdelektro.workshop;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.TaskRepository;
import pl.tdelektro.workshop.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class WorkshopApplication implements CommandLineRunner {


    private UserRepository userRepository;
    private CarRepository carRepository;
    private TaskRepository taskRepository;
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(WorkshopApplication.class, args);
    }




    //Users for test
    @Override
    public void run(String... args) throws Exception {
        List<User> userList = Arrays.asList(
                new User("workshop@tdelektro.pl", passwordEncoder.encode("password"), "ADMIN"),
                new User("tomek@test.pl", passwordEncoder.encode("password1")),
                new User("dawid@test.pl", passwordEncoder.encode("password2")),
                new User("michal@test.pl",passwordEncoder.encode( "password3"))
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

        for(Task task: taskList) {
            taskRepository.save(task);
        }

        //Cars for test
        List<Car> carList = Arrays.asList(

                new Car("1M8GDM9AXKP042788", "Pagani Zonda"),
                new Car("1HGCM563X3A157921", "Honda Accord"),
                new Car("1G1BL52P0TR136787", "Chevrolet Camaro"),
                new Car("5TDZT34A34S190929", "Toyota Corolla"),
                new Car("1FAFP34NX6W229751", "Ford F150")

        );

        for (Car car : carList) {
            carRepository.save(car);
        }
    }
}
