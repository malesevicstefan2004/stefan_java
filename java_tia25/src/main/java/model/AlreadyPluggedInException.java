package model;

/**
 * Already Plugged In Exception
 *
 * <p>Thrown when an attempt is made to plug in a {@link TableLight} that is already connected to
 * the power supply.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class AlreadyPluggedInException extends Exception {

   private static final long serialVersionUID = 2459368959202015973L;

   /**
    * Creates a new exception with the given detail message.
    *
    * @param message a description of the error
    */
   public AlreadyPluggedInException(String message) {
      super(message);
   }

}
