package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Flash Light
 *
 * <p>A portable light that runs on an internal energy supply. Switching it on drains the energy
 * level; it can be recharged to full capacity.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public final class FlashLight extends Light {

   // private boolean isOn;
   /** The remaining energy level, in the range {@code [0.0, 1.0]}. */
   private double energyLevel;

   /** Creates a fully charged flash light. */
   public FlashLight() {
      energyLevel = 1.0;
   }

   /**
    * Switches this flash light on and drains {@code 0.1} energy units if available.
    * Delegates to the superclass to set the on-state.
    */
   @Override
   public void switchOn() {
      if (energyLevel >= 0.1) {
         energyLevel -= 0.1;
      }
      super.switchOn();
   }

   /**
    * Returns whether this flash light is currently emitting light.
    *
    * @return {@code true} if switched on and energy level is above zero
    */
   @Override
   public boolean isShining() {
      return isOn() && energyLevel > 0;
   }

   /** Recharges the flash light to its full energy level of {@code 1.0}. */
   public void recharge() {
      energyLevel = 1.0;
   }

}
