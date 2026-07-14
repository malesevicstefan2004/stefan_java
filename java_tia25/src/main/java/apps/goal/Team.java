package apps.goal;

import lombok.Data;
import lombok.NoArgsConstructor;

/** Represents a football team with a name and a goal count. */
@Data
@NoArgsConstructor
public class Team {

   /** The team's name. */
   private String name;
   /** The number of goals scored by this team. */
   private int goals;

}
