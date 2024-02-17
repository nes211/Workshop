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




    //Users for test
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

        //Cars for test
        List<Car> carList = Arrays.asList(

                new Car("1M8GDM9AXKP042788", "checksumTest"),
                new Car("1HGCM563X3A157921", "Honda"),
                new Car("1G1BL52P0TR136787", "Chevrolet"),
                new Car("5TDZT34A34S190929", "Toyota"),
                new Car("1FAFP34NX6W229751", "Ford")

        );

        for (Car car : carList) {
            carRepository.save(car);
        }

    }
}
