package pl.tdelektro.workshop;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class WorkshopApplication implements CommandLineRunner {

    private UserRepository userRepository;
    private CarRepository carRepository;

    public static void main(String[] args) {
        SpringApplication.run(WorkshopApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        List<User> userList = Arrays.asList(
                new User("user1@test.pl", "password1"),
                new User("user2@test.pl", "password2"),
                new User("user3@test.pl", "password3")
        );

        for (User user : userList) {
            userRepository.save(user);
        }


        List<Car> carList = Arrays.asList(
                new Car("SUPB01CEHTW858479", "Polonez"),
                new Car("WAUZZZ8K6AA103083", "Audi"),
                new Car("SBM11SAD4GW675962", "McLaren"),
                new Car("WDB2030421A598116", "Mercedes-Benz"),
                new Car("VF1FDCCL525890440", "Renault"),
                new Car("KMHSU81XCDU147558", "Hyundai")
        );

        for (Car car : carList) {
            carRepository.save(car);
        }

    }
}
