package pl.tdelektro.workshop.exception;

public class CarNotFoundException extends Throwable {
    public CarNotFoundException(Long carId){
        super("Car with id : " + carId + " is not available in repository");
    }
}
