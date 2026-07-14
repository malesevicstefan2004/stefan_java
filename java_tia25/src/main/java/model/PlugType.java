package model;

import lombok.Getter;
import lombok.ToString;

/**
 * Plug Type
 *
 * <p>Enumerates common electrical plug standards used in different regions.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
@Getter
@ToString
public enum PlugType {

   /** Schuko plug (CEE 7/4), used in Europe. */
   TYPE_F("Type F", "CEE7/4", "Europe"),
   /** Australian/New Zealand plug (AS/NZS 3112). */
   TYPE_I("Type I", "AS/NZS 3112", "ANZ");

   /** Human-readable name of the plug type. */
   private final String description;
   /** Norm or standard identifier of the plug type. */
   private final String norm;
   /** Geographic region where this plug type is commonly used. */
   private final String region;

   /**
    * Creates a plug type constant with the given properties.
    *
    * @param description human-readable name
    * @param norm        norm or standard identifier
    * @param region      geographic region of use
    */
   PlugType(String description, String norm, String region) {
      this.description = description;
      this.norm = norm;
      this.region = region;
   }

}
