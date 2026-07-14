package apps.goal;

import lombok.Getter;

/** Singleton model that holds the state of both teams throughout the app. */
@Getter
public class Model {

   private final Team teamA;
   private final Team teamB;

   private static final Model instance = new Model();

   /** Creates a new model with two empty teams. */
   private Model() {
      teamA = new Team();
      teamB = new Team();
   }

   /** Returns the single shared {@code Model} instance. */
   public static Model getInstance() {
      return instance;
   }

   /**
    * Increments the goal count of the given team by one.
    *
    * @param team the team whose goal count is incremented
    */
   public void increaseGoals(Team team) {
      team.setGoals(team.getGoals() + 1);
   }

}
