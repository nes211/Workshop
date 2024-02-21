package pl.tdelektro.workshop.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.repository.CarRepository;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private JavaMailSender javaMailSender;
    private CarRepository carRepository;

    @Override
    public void sendEmail(Long carId) throws MessagingException {

        Car car = carRepository.findById(carId).get();
        String email = car.getUser().getEmail();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("workshop@tdelektro.pl");
        helper.setTo(email);
        helper.setSubject("The car " + car.getModel() + " has been repaired");
        helper.setText("Hello, <br><br>" +
                "your car: " + car.getModel() + " , vin : " + car.getVinNumber() + "has been repaired. " +
                "<br>Please call us at: <b>500100100</b> to schedule a pickup appointment." +
                "<br><br>Workshop Team", true);
        javaMailSender.send(message);
    }


}
