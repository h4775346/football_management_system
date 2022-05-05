
package football.managment.system;



import static football.managment.system.FootballManagmentSystem.scn;
import static football.managment.system.Start.ANSI_GREEN;
import static football.managment.system.Start.ANSI_RED;
import static football.managment.system.Start.ANSI_RESET;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public final class Team  {
  String[] Type = {"forward", "midfielder", "defender", "goalkeeper"};//Array Of Players Types
static int Id;
    private String Name;
    private String Captain;
    private String Goalkeeper;
    private int GoalReceivedGoals;
    private int Players,Matches,id2,PlayersAverageAge;
    private final ArrayList<Player> playerArr;//Array That Contans Players In The Team
   private final HashMap  Teams_We_Played_With_Map ;//HashMap To Check How Many Times We Playes Against Other Teams
   static int i=0;
   
   Scanner scnLines = new Scanner(System.in);
   //=-----------------------------------------------------------
   
    private int WinMatchesCount;
    private int DrawMatchesCount;
    private int LostMatchesCount;
    private boolean WeHaveGoalkeeper;
    private int scoredGoalsCount;
    private int receivedGoalsCount;
    private int points;
    private int PlayerLoopNumber=1;
   
      //=-----------------------------------------------------------
Scanner scn = new Scanner(System.in);
    public Team(boolean auto,String TeamArrData[]){
  Teams_We_Played_With_Map = new HashMap<Team, Integer>();
      playerArr = new ArrayList<>();
    
      int PlayersNumbers ;
                
      
        if (auto) {
            setWeHaveGoalkeeper(true);//Because We In Auto Add Mode Then We Will Add GoalKeeper Anyway
            setGoalkeeper("player " + 3);
                setName(TeamArrData[i]);
                i++;
                
          for (int i = 1; i <= 11; i++) {
               Player p = new Player();
               
               if (i>=0 && i<=3) {
                   
                   p.setType(Type[i]);
               
              }else{
                   
                    p.setType(Type[2]);
                   
               }
              
              
               p.setName("player " + i);
                p.setNumber(i+i);
                 p.setAge(18 + i);
                 
                 p.setTeam(getName());
               playerArr.add(p);
               
              PlayerLoopNumber=i+1;
          }
            
            
            setCaptain("player " + i);
            
           Id++;
            setId2(Id);
         
            PlayersAverageAgeChanger();
            return;
        }
      
      
      
      
      
      
      
      
      
      
                  System.out.print("Enter Team (Name) :");
        
        String TeamName =GetFixedString();
        
      
        
        while (CheckTeamExistance(TeamName)) {  
             System.out.println(ANSI_RED +"The Team Name Is Existed"+ ANSI_RESET);
            System.out.print("Enter Team (Name) Again :");
           TeamName =GetFixedString();
        }
        
        
        
        setName(TeamName);
        
        
         System.out.print("How Much Players In The Team (Should be at Least 11 Players )  : ");
         
        
          PlayersNumbers = GetFixedInt();
          while (PlayersNumbers<11) {
            System.out.println(ANSI_RED +"The Team (Should be at Least 11 Players )"+ ANSI_RESET);
            System.out.print("How Much Players In The Team (Should be at Least 11 Players )  : ");
            PlayersNumbers = GetFixedInt();
        }
          
        PlayerLoopNumber=1;
         
         int Choice=ChooseTheWay();
         while (Choice>2 || Choice<1) {            
          
             System.out.println(ANSI_RED +"Wrong Choice"+ ANSI_RESET);
             Choice=ChooseTheWay();
             
        
        }
         if (Choice==1) {
             for (int i = 1; i <= PlayersNumbers; i++) {
           
            Manual_addPlayer();
           
            
        }
         System.out.println(ANSI_GREEN + "Players has Been Successfully Added ^-^" + ANSI_RESET);     
        }else if(Choice==2){
            
             for (int i = 1; i <= PlayersNumbers; i++) {
           
            Fast_addPlayer();
           
            
        }
            
        System.out.println(ANSI_GREEN + "Players has Been Successfully Added ^-^" + ANSI_RESET);     
            
        }
       
        
       ChooseCaptain();
    
            Id++;
            setId2(Id);
           System.out.println(ANSI_GREEN + "Team has Been Successfully Added ^-^" + ANSI_RESET);     
         
    
}

    public int getPlayersAverageAge() {
        return PlayersAverageAge;
    }

    public void setPlayersAverageAge(int PlayersAverageAge) {
        this.PlayersAverageAge = PlayersAverageAge;
    }
    
    public void PlayersAverageAgeChanger() {
        int TotalAge=0;
        int Number_Of_Players_We_Have=0;
         for (Player Player : playerArr) {
             Number_Of_Players_We_Have++;
            TotalAge+=Player.getAge();
            
         }
         setPlayersAverageAge(TotalAge/Number_Of_Players_We_Have);
        
    }
    public  boolean CheckTeamExistance(String TeamName){
    
    
     for (Team team : Start.teamArr) {
                        
                    if(team.getName().equals(TeamName)){
                        
                       return true;
                                            
                       
                    }
           
           
           
    
       
       }
               return false; 
           }
    
    
    


    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    Team() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        
        return Name;
    }
    
    

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getCaptain() {
        return Captain;
    }

    public void setCaptain(String Captain) {
        this.Captain = Captain;
    }
public boolean isWeHaveGoalkeeper() {
        return WeHaveGoalkeeper;
    }

    public void setWeHaveGoalkeeper(boolean WeHaveGoalkeeper) {
        this.WeHaveGoalkeeper = WeHaveGoalkeeper;
    }

    public String getGoalkeeper() {
        return Goalkeeper;
    }

    public void setGoalkeeper(String Goalkeeper) {
        this.Goalkeeper = Goalkeeper;
    }
    
    public void setType(String[] Type) {
        this.Type = Type;
    }

    public String[] getType() {
        return Type;
    }

    public HashMap getTeams_We_Played_With_Map() {
        return Teams_We_Played_With_Map;
    }

     public void ChooseCaptain() {
         prinPlayers();
          
         while (true) {            
             System.out.print("\nTeam Captain (Name) : ");
         
         String myCaption = GetFixedString();
         
         System.out.print("\nTeam Captain T-Shirt (Number) : ");
         
         int CaptinNumber = GetFixedInt();
             if (PlayerIsExisted(myCaption,CaptinNumber)) {
                 setCaptain(myCaption);
            break;
             }

             System.out.println(ANSI_RED +"The Player Is Not Existed Please Enter a Correct Name " + ANSI_RESET);
         
        }
    }
     
     public void prinPlayers(){
         
          int x=0;
         for (Player Player : playerArr) {
             x++;
      System.out.println(x + "- " + Player.getName() + " T-Shirt " + Player.getNumber() + "  " + Player.getType() );
      
         }
         
        
         
         
         
         
     }

   public Object getPlayer(String PlayerName,int PlayerNumber) {
        
      
           for (Player Player : playerArr) {
            
            if (Player.getName().equals(PlayerName)&& PlayerNumber==Player.getNumber()) {
                
                return Player;
                
            }
            
      
         }
      
               
        
        return null;
       
         
    }
   
   
    void addToGoalKeeperReceivedGoals(int NewGoals){
     
        for (Player Player : playerArr) {
            
            if (Player.getType().equals("goalkeeper")) {
                
             Player.setReceivedGoals(Player.getReceivedGoals()+NewGoals);
                
            }
            
      
         }              
           
       }
   
   
   public void ChangePlayerData(Player OldPlayer,Player NewPlayer){
       
       
       
       for (Player Player : playerArr) {
            
            if (Player.equals(OldPlayer)) {
                
               Player=NewPlayer;
                
            }
            
      
         }
       
       
       
       
       
   }
   
   
   
   public boolean PlayerIsExisted(String PlayerName,int PlayerNumber) {
        for (Player Player : playerArr) {
            
            if (Player.getName().equals(PlayerName)&& Player.getNumber()== PlayerNumber) {
                
                return true;
                
            }
            
      
         }
        
        return false;
         
    }
   
   
   

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getPlayers() {
        return Players;
    }

    public void setPlayers(int Players) {
        this.Players = Players;
    }

   

    public ArrayList<Player> getPlayerArr() {
        return playerArr;
    }

   

    public int getMatches() {
        return Matches;
    }

    public void setMatches(int Matches) {
        this.Matches = Matches;
    }

   
    
    
    
    
    public int getWinMatchesCount(){
        return WinMatchesCount;
    }
    
    public int getDrawMatchesCount() {
        return DrawMatchesCount;
    }
    
    public int getLostMatchesCount(){
        return LostMatchesCount;
    }
    
    public int getScoredGoalsCount() {
        return scoredGoalsCount;
    }
    
    public int getReceivedGoalsCount() {
        return receivedGoalsCount;
    }
    
    public int getPoints() {
        return points;
    }

    public int getGoalReceivedGoals() {
        return GoalReceivedGoals;
    }

    public void setGoalReceivedGoals(int GoalReceivedGoals) {
        this.GoalReceivedGoals = GoalReceivedGoals;
    }
   
    
    public void setWinMatchesCount(int i) {
        WinMatchesCount=i;
    }
    
    public void setDrawMatchesCount(int i){
        DrawMatchesCount = i;
    }
    
    public void setLostMatchesCount(int i) {
        LostMatchesCount=i;
    }
    
    public void setScoredGoalsCount(int i){
        scoredGoalsCount = i;
    }
    
     public void setRecievedGoalsCount(int i){
        receivedGoalsCount = i;
    }
     
     public void setPoints(int i){
        points = i;
    }
    
     public boolean CheckPlayer(String Name){
         for (Player Player : playerArr) {
            
        if (Player.getName().equals(Name)) {
            
            return true;
        }
      
         }
         return false;
    }
    
    
 public int ChooseTheWay(){
      
      System.out.println("\nManual Add For Players (Click 1) (Add All Data For Every Player Like Name number Age ....ect)");
        
         System.out.println("Fast Add For Players  (Click 2) (Name And Type)");
         
         System.out.print("\nYour Choice : ");
                   
      int number=GetFixedInt();
      return number;
      
  }
    
     public void Manual_addPlayer(){
          Player p = new Player();
            
            System.out.println("Enter The (Type) Of Player Number " + PlayerLoopNumber);
            
            PrintPlayerTypes();
            
            System.out.print("Your Answer Number : ");
            
           int choice = GetFixedInt();
           
            while (WrongTypePick(choice) || (isWeHaveGoalkeeper()&&choice==3)) {   
                if (isWeHaveGoalkeeper()&&choice==3) {
                    
                    System.out.println(ANSI_RED +"Sorry But You Allredy Have Goalkeeper !! "+ ANSI_RESET);
                     System.out.print("Your Answer Number : ");
           }else{
                    System.out.println(ANSI_RED +"Wrong Input Please Try Again !! "+ ANSI_RESET);
                System.out.print("Your Answer Number : ");
                }
                
                choice = GetFixedInt();
            }
             
            
            p.setType(Type[choice]);
            
            
            System.out.print("Enter The (Name) Of Player Number " + PlayerLoopNumber + " : ");
              
            String pName=GetFixedString();
            p.setName(pName);
             if (choice==3) {
                setWeHaveGoalkeeper(true);
                setGoalkeeper(pName);
        }
            while (true) {            
            System.out.print("Enter The (Shirt) Number Of Player Number " + PlayerLoopNumber + " : ");
            int TshirtNumber=GetFixedInt();
           
           if (NumberIsOk(TshirtNumber)){
                 p.setNumber(TshirtNumber);
                 break;
               
           }
            System.out.println(ANSI_RED +"Shirt Number Is Existed Please Change It"+ ANSI_RESET);
        }
            
           
            
            
            System.out.print("Enter The (Age) Of Player Number " + PlayerLoopNumber + " : ");
           choice=GetFixedInt();
            
             while (WrongAge(choice)) {   
                
                System.out.println(ANSI_RED +"Age Can Not be Acceptd !! "+ ANSI_RESET);
                System.out.print("Enter Player Age Again Please :   ");
                choice = GetFixedInt();
            }
            p.setAge(choice);
             p.setTeam(getName());
            playerArr.add(p);
           PlayersAverageAgeChanger();
        
                   PlayerLoopNumber++;
   

    }
     
     
     
     public void Fast_addPlayer(){
         
         
         
         
          Player p = new Player();
            
            System.out.println("Enter The (Type) Of Player Number " + PlayerLoopNumber);
            
            PrintPlayerTypes();
            
            System.out.print("Your Answer Number : ");
            
           int choice = GetFixedInt();
           
            while (WrongTypePick(choice) || (isWeHaveGoalkeeper()&&choice==3)) {   
                if (isWeHaveGoalkeeper()&&choice==3) {
                    
                    System.out.println(ANSI_RED +"Sorry But You Allredy Have Goalkeeper !! "+ ANSI_RESET);
                     System.out.print("Your Answer Number : ");
                     
                }else{
                    System.out.println(ANSI_RED +"Wrong Input Please Try Again !! "+ ANSI_RESET);
                System.out.print("Your Answer Number : ");
                }
                choice = GetFixedInt();
            }
             
            
            p.setType(Type[choice]);
            
            
            System.out.print("Enter The (Name) Of Player Number " + PlayerLoopNumber + " : ");
              
            String pName=GetFixedString();
            p.setName(pName);
             if (choice==3) {
                setWeHaveGoalkeeper(true);
                setGoalkeeper(pName);
        }
             int tShirtNumber =1;
            while (!NumberIsOk(tShirtNumber)) {            
           tShirtNumber++;
        }
            
            p.setNumber(tShirtNumber);
            
            Random rand = new Random(); 
             int value = rand.nextInt(50); 
           choice= rand.nextInt((70 - 20) + 1) + 20;
             while (WrongAge(choice)) {   
                
                choice= rand.nextInt((70 - 20) + 1) + 20;
            }
            p.setAge(choice);
             p.setTeam(getName());
            playerArr.add(p);
           PlayersAverageAgeChanger();
        PlayerLoopNumber++;
        
     }
    
    
    //Function To Add GoalKeeper Befor The Match
   public void addGoalKeeper(){
        
        
         Player p = new Player();
            
            
            p.setType("goalkeeper");
            
            
            System.out.print("Enter The (Name) Of The GoalKeeper : ");
              
            String pName=GetFixedString();
            p.setName(pName);
             
                setWeHaveGoalkeeper(true);
                setGoalkeeper(pName);
      
            while (true) {            
            System.out.print("Enter The (Shirt) Number Of The GoalKeeper : ");
            int TshirtNumber=GetFixedInt();
           
           if (NumberIsOk(TshirtNumber)){
                 p.setNumber(TshirtNumber);
                 break;
               
           }
            System.out.println(ANSI_RED +"Shirt Number Is Existed Please Change It"+ ANSI_RESET);
        }
            
           
            
            
            System.out.print("Enter The (Age) Of The GoalKeeper : ");
          int  choice=GetFixedInt();
            
             while (WrongAge(choice)) {   
                
                System.out.println(ANSI_RED +"Age Can Not be Acceptd !! "+ ANSI_RESET);
                System.out.print("Enter Player Age Again Please :   ");
                choice = GetFixedInt();
            }
            p.setAge(choice);
             p.setTeam(getName());
            playerArr.add(p);
           PlayersAverageAgeChanger();
   System.out.println(ANSI_GREEN + "GoalKeeper has Been Successfully Added ^-^" + ANSI_RESET);      
        
        
    }
    //Function To Chec If the T-Shirt Number Is Existed In The Team players
    public boolean NumberIsOk(int Number){
       
         for (Player Player : playerArr) {
            
        if (Player.getNumber() ==(Number)) {
            
            return false;
        }
      
         }
         return true;
    }
    
    //Check For Accepted Age In The Team
    public boolean WrongAge(int Age){
        if (Age <16  || Age > 70) {
            
             return true;
        }
        return false;
        
        
    }
    
    ///Check If Pick Wrong Index Of The Array
    public boolean WrongTypePick(int TypeIndex){
        if (TypeIndex > Type.length-1 || TypeIndex < 0) {
            
             return true;
        }
        return false;
        
        
    }
    
    
    //Add Tem That We Played Against To The Hash Map 
              
          public  void teamWePlayedAgainst(Object theTeam){
              
              if (Teams_We_Played_With_Map.containsKey(theTeam)) {
                  
               Object lastTime = Teams_We_Played_With_Map.get(theTeam);
                  
                  if (lastTime.equals(1)) {
                      Teams_We_Played_With_Map.replace(theTeam, lastTime, 2);
                      
                      return;
                      
                  }
              
              }else{
                 Teams_We_Played_With_Map.put(theTeam, 1);
              
             
              
              
                  
              }
              
             
              
              
          }
              
              public void PrintPlayerTypes(){
                  
                  for (int j = 0; j < Type.length; j++) {
                
                System.out.println( j + " " + Type[j] );
                
            }
                  
                  
                  
                  
              }
              //Check If We Played Against That Team Twice Or Not
              public  boolean MatchOk(Object theTeam){
             //Check if we played the match twice
              
              if (Teams_We_Played_With_Map.containsKey(theTeam)) {
                  
               Object lastTime = Teams_We_Played_With_Map.get(theTeam);
               
               
                  if (lastTime.equals(2)) {
                     return false;
                      
                      
                  }
             
              }
              
            return true;
              
              
          }
          
              
    
    public int GetFixedInt(){// Function to get Only Entegers
       
       int number;
        while (true){
             String line = scn.next();
                  if (line.toLowerCase().equals("back")){
                Start.NotAutoAdded=false;
                 Start b =new Start();
             }
            try {
                number = Integer.parseInt(line);
               return number;
            } catch (Exception e) {
                System.out.println(ANSI_RED +"We Accept Only Integer Numbers !! "+ ANSI_RESET);
                 System.out.print("Enter Again : ");
        }
            
        }
       
       
   }
    
    public String GetFixedString(){ // Function to get String Line and Change it To LowerCase
       
             String line = scnLines.nextLine();
             if (line.toLowerCase().equals("back")){
                 Start.NotAutoAdded=false;
                 Start b =new Start();
             }
                 
              while(line.equals("")) {
                 System.out.print("Empty Line Enter Again : ");
            line = scnLines.nextLine();
             }
              
             return line.toLowerCase();
                 
   }
    
}
