package model;

import java.util.Objects;

/**
 * Light Bulb
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class LightBulb {

   /** The color of this light bulb. */
   private final String color;

   /**
    * Creates a new light bulb with the given color.
    *
    * @param color the color of the light bulb
    */
   public LightBulb(String color) {
      this.color = color;
   }

   /**
    * Returns the color of this light bulb.
    *
    * @return the color
    */
   public String getColor() {
      return color;
   }

   /**
    * Checks whether this light bulb has the same color as another one.
    *
    * @param otherLightBulb the light bulb to compare with
    * @return {@code true} if both bulbs share the same color
    */
   public boolean equals(LightBulb otherLightBulb) {
      return color.equals(otherLightBulb.color);
   }

   @Override
   public String toString() {
      return "LightBulb [color=" + color + "]";
   }

   @Override
   public int hashCode() {
      return Objects.hash(color);
   }

}
