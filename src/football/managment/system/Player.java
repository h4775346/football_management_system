
package football.managment.system;

import java.util.HashMap;


public class Player {
      private final HashMap  PlayerScores = new HashMap<Match, Integer>();
    private String Name;
    private String Team;
    private String type;
    private int Number,Age,Score,Rank,receivedGoals;
    
    
    public Player() {
        
      
        
        
        
        
    }

    
    
    public String getType() {
        return type;
    }

    public int getReceivedGoals() {
        return receivedGoals;
    }

    public void setReceivedGoals(int receivedGoals) {
        this.receivedGoals = receivedGoals;
    }

    public HashMap getPlayerScores() {
        return PlayerScores;
    }
    
      public void ChangePlayerScores(Match match ,int addedScore ) {
       
          
          if (PlayerScores.containsKey(match)) {
                        int oldScore= (int) PlayerScores.get(match);
                        int newScore=oldScore+addedScore;
                         PlayerScores.put(match,newScore);
                         
                        
                     }else{
                         
                          PlayerScores.put(match,addedScore);
                          
                         
                     }
          
          
          
    }
      
       public void GetPlayerScoredMatches() {
           
    for ( Object key : PlayerScores.keySet() ) {
    Match match = (Match)key;
    System.out.println("AT Match Id (" + match.getId2() +  ") + Date (" + match.getDate() + ")");
    System.out.println( getName()+ " Scored : " + PlayerScores.get(key)+ " Goals\n");
      }
          
        
          
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) 
     {
        this.Name = Name;
    }

    public String getTeam() {
        return Team;
    }

    public void setTeam(String Team) {
        this.Team = Team;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        
        this.Number = Number;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int Rank) {
        this.Rank = Rank;
    }
    
    

    
    
    
}
