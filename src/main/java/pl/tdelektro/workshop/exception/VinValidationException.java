package pl.tdelektro.workshop.exception;

public class VinValidationException extends RuntimeException{

    public VinValidationException(String vinNumber){
        super("Vin " + vinNumber + " universal crc validation error. " +
                "Vin number not correct or manufacturing company has its own crc algorithm");
    }

}
