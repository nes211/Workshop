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
		new User("smok@test.pl", "password1"),
				new User("smok@test.pl", "password2"),
				new User("smok@test.pl", "password3")
		);

		for(User user : userList){
			userRepository.save(user);
		}


		List<Car> carList = Arrays.asList(
				new Car(),
				new Car(),
				new Car()
		);

		for(Car car : carList){
			carRepository.save(car);
		}

	}
}
