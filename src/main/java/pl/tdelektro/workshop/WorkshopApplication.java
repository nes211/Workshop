package pl.tdelektro.workshop;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pl.tdelektro.workshop.service.InitialData;

@SpringBootApplication
@AllArgsConstructor
@ComponentScan(basePackages = "pl.tdelektro")
public class WorkshopApplication implements CommandLineRunner {

    private InitialData initialData;

    public static void main(String[] args) {
        SpringApplication.run(WorkshopApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        initialData.importData("src/main/resources/users.json");
        initialData.importData("src/main/resources/tasks.xml");
        initialData.importData("src/main/resources/cars.csv");

    }
}
