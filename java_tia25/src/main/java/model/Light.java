package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Light
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
@Getter
@ToString
@EqualsAndHashCode
public abstract class Light {

   /** Whether this light is currently switched on. */
   private boolean isOn;

   /** Switches this light on. */
   public void switchOn() {
      isOn = true;
   }

   /** Switches this light off. */
   public final void switchOff() {
      isOn = false;
   }

   /**
    * Returns whether this light is currently emitting light.
    *
    * @return {@code true} if the light is shining
    */
   public abstract boolean isShining();

}
