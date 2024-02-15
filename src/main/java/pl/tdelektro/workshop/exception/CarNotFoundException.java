package pl.tdelektro.workshop.exception;

public class CarNotFoundException extends Throwable {
    public CarNotFoundException(String vinNumber){
        super("Car with vin : " + vinNumber + " is not available  in our repository");
    }
}
