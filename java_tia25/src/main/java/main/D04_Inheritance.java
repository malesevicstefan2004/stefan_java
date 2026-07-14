package main;

import java.util.ArrayList;
import java.util.List;

import model.AlreadyPluggedInException;
import model.FlashLight;
import model.Light;
import model.LightBulb;
import model.TableLight;

/**
 * Demo 4: Inheritance
 *
 * <p>Demonstrates inheritance with an abstract base class, method overriding, upcasting,
 * downcasting, and dynamic polymorphism using {@link model.Light}, {@link model.TableLight}, and
 * {@link model.FlashLight}.
 *
 * @author Daniel Appenmaier
 * @version 1.0
 *
 */
public class D04_Inheritance {

   /**
    * Entry point — runs all inheritance demos sequentially.
    *
    * @param args command-line arguments (not used)
    */
   public static void main(String[] args) throws AlreadyPluggedInException {
      /* Ansatz ohne Vererbung */
      List<TableLight> tableLights = new ArrayList<>();
      tableLights.add(new TableLight());
      tableLights.add(new TableLight());
      tableLights.add(new TableLight());

      List<FlashLight> flashLights = new ArrayList<>();
      flashLights.add(new FlashLight());
      flashLights.add(new FlashLight());
      flashLights.add(new FlashLight());

      for (TableLight t : tableLights) {
         t.switchOn();
         t.plugIn();
         t.changeLightBulb(new LightBulb("white"));
         System.out.println(t.toString());
         System.out.println(t.isShining());
      }

      for (FlashLight f : flashLights) {
         f.switchOn();
         System.out.println(f.toString());
         System.out.println(f.isShining());
      }

      System.out.println();

      /* Ansatz mit Vererbung */
      ArrayList<Light> lights = new ArrayList<>();
      lights.add(new TableLight()); // Upcast
      lights.add(new TableLight()); // Upcast
      lights.add(new TableLight()); // Upcast
      lights.add(new FlashLight()); // Upcast
      lights.add(new FlashLight()); // Upcast
      lights.add(new FlashLight()); // Upcast

      for (Light l : lights) {
         l.switchOn(); // Dynamische Polymorphie

         if (l instanceof TableLight) {
            TableLight t = (TableLight) l; // Downcast
            t.plugIn();
            t.changeLightBulb(new LightBulb("white"));
         }

         if (l instanceof FlashLight f) { // Downcast
            f.recharge();
         }

         System.out.println(l.toString()); // Statische und Dynamische Polymorphie
         System.out.println(l.isShining()); // Statische und Dynamische Polymorphie
      }
   }

}
