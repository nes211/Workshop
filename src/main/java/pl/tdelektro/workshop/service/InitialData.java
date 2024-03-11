package pl.tdelektro.workshop.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.TaskRepository;
import pl.tdelektro.workshop.repository.UserRepository;
import pl.tdelektro.workshop.security.Admin;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


@Service
@AllArgsConstructor
public class InitialData {

    private UserRepository userRepository;
    private CarRepository carRepository;
    private TaskRepository taskRepository;
    private PasswordEncoder passwordEncoder;
    private Admin adminPassword;


    //Add initial data as an import files:
    //JSON for User class
    //XML for Task class
    //CSV for Car class
    public void importData(String filePath) throws IOException, CsvValidationException {

        switch (filePath) {

            case "src/main/resources/users.json": {

                FileReader fileReader = new FileReader(filePath);
                ObjectMapper objectMapper = new ObjectMapper();
                List<User> userList = objectMapper.readValue(fileReader, new TypeReference<List<User>>() {
                });
                for (User user : userList) {
                    User userToSave = new User(user.getUsername(), passwordEncoder.encode(user.getPassword()));
                    userRepository.save(userToSave);
                }
                userRepository.save(new User("workshop@tdelektro.pl", adminPassword.getPassword(), "ADMIN"));
                break;
            }

            case "src/main/resources/tasks.xml": {

                FileReader xmlFile = new FileReader("src/main/resources/tasks.xml");
                XmlMapper xmlMapper = new XmlMapper();

                List<Task> taskList = xmlMapper.readValue(xmlFile, new TypeReference<List<Task>>() {
                });

                for (Task task : taskList) {
                    taskRepository.save(task);
                }
                break;
            }
            case "src/main/resources/cars.csv": {

                FileReader fileReader = new FileReader("src/main/resources/cars.csv");
                CSVReader csvReader = new CSVReader(fileReader);

                //Header omit
                csvReader.readNext();
                String[] stringLine;

                while ((stringLine = csvReader.readNext()) != null) {
                    Car car = new Car(stringLine[0], stringLine[1]);
                    carRepository.save(car);
                }
                break;
            }

        }
    }
}
