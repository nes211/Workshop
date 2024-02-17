package pl.tdelektro.workshop.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(Long carId){
        super("Car with id : " + carId + " is not available in repository");
    }
}
