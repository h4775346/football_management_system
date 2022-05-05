
package football.managment.system;


import static football.managment.system.FootballManagmentSystem.scn;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start {
    public static final String ANSI_BLUE = "\u001B[34m";//Change TextColor To Blue
    public static final String ANSI_RED = "\u001B[31m";//Change TextColor To Red
    public static final String ANSI_RESET = "\u001B[0m";//Rest Text Color To Black
    public static final String ANSI_GREEN = "\u001B[32m";//Change TextColor To Green
    public  static ArrayList<Team> teamArr = new ArrayList<>(); // Array List Of Teams Objects
    
    public static ArrayList<Player> P = new ArrayList<>(); //Array List to include players in It; 
  
     public static ArrayList<Match> matches = new ArrayList<>(); // Arra List For Matches;
     
  
     public static boolean NotAutoAdded = true; // Check If We Allready Added Teams Att The Constructor
   
    Scanner scnLines = new Scanner(System.in);
    
    public Start(){
        
        if (NotAutoAdded) {
            autoComplete();//Function to Auto add teams for Speed Test
            displayMenu();
            NotAutoAdded = false;
            
            

        }else{
            
             displayMenu();// Main Menue
        }
  
     
    
}
    
  //----------------- Sorting Arrays Functions ----------------------------------------------  
    
    // Sort by Using Points-Sort by Using ScoredGoals-Sort Using Average Age-Sorts by Recived Goals
    void SortTeamArr(boolean UsingPoints,boolean UsingScoredGoals,boolean UsingTeamsAverageAge,boolean RecivedGoals){
  
Comparator<Team> compareByPoints;
        compareByPoints = (Team o1, Team o2) -> {
            if (UsingPoints) {
                return Integer.valueOf(o1.getPoints()) .compareTo( o2.getPoints() );
            }else if(UsingScoredGoals){
                return Integer.valueOf(o1.getScoredGoalsCount()) .compareTo( o2.getScoredGoalsCount() );
            }else if(UsingTeamsAverageAge){
                return Integer.valueOf(o1.getPlayersAverageAge()) .compareTo( o2.getPlayersAverageAge() );
                
                
            }else{
                return Integer.valueOf(o1.getGoalReceivedGoals()) .compareTo( o2.getGoalReceivedGoals() );
                
            }
};
        if (RecivedGoals) {
            Collections.sort(teamArr, compareByPoints);//We Use Reserved Sort To Make The Big First
        }else{
            Collections.sort(teamArr, compareByPoints.reversed());//We Use Reserved Sort To Make The Big First

        }
        
        
        
    }
    
    
   void SortPlayersArr(){
    
    P.clear();//Remve Old Players In Array To Add New Players With New Informations
    teamArr.forEach((team) -> {
        //Add Every Player In Every Team
        P.addAll(team.getPlayerArr());
        });
    
    //Sort Using Players Scores
      Comparator<Player> compareByPoints;
        compareByPoints = (Player o1, Player o2) -> Integer.valueOf(o1.getScore()) .compareTo( o2.getScore() );
 
Collections.sort(P, compareByPoints.reversed());//We Use Reserved Sort To Make The Big First
        
}
    //----------------- ----------------------------- ---------------------------------------------- 
    
   //------------------------------------------------------------------------------------------------------
   
   
   int GetFixedInt(){ // Function to get String Line and check it if its a number then returnback the number value of the line
       
       int number;
       
        while (true){
             String line = scn.next();
             
            line= line.toLowerCase();
             if (line.equals("back")) {
                displayMenu();
             
            }
            try {
                number = Integer.parseInt(line);
               return number;
            } catch (NumberFormatException e) {
                System.out.println(ANSI_RED +"We Accept Only Integer Numbers !! "+ANSI_RESET);
                 System.out.print("Enter Again : ");
                

        }
            
        }
       
       
   }
   
  String GetFixedString(){ // Function to get String Line and Change it To LowerCase
       
             String line = scnLines.nextLine();
             while(line.equals("")) {
                 System.out.print(ANSI_RED +"Empty Line Enter Again : "+ANSI_RESET);
            line = scnLines.nextLine();
             }
             line= line.toLowerCase();
             if (line.equals("back")) {
                displayMenu();
             
            }
                  return line.toLowerCase();
    
                 
   }
   
      //------------------------------------------------------------------------------------------------------

   
   
   //-----------------------  Displaying Menues ---------------------------------------------- 
     void displayMenu(){
       //Main Menue
      
        System.out.println("\n\n--------------------------------------\n\n");
        System.out.println(ANSI_BLUE +"Anytime You Click \"Back\" I Will Send You Back To This Menue" +ANSI_RESET + "\n");
        System.out.println("Players-----------(Click 1)");
        System.out.println("Teams-------------(Click 2) ");
        System.out.println("Matches-----------(Click 3) ");
        System.out.println("Play Match Enter--(Click 4)");
        System.out.println("More Options------(Click 5)");
        System.out.print("\nYour Choice : ");
        
        
      int command = GetFixedInt();
        
            
            switch(command) {
                case 1 :
                   displayPlayerMenue();
                 
            break;
                case 2 :
                     displayTeamMenu();
                    break;
                case 3 :
                  MatchesMenue();
                  break;
               case 4 :
                  addPlayedMatch();
                  break;
                  case 5 :
                  GeneralFunctionsMenue();
                  break;
            default:
            System.out.println(ANSI_RED +"Wrong  Input"+ANSI_RESET);
            break;
        }
        
        
            displayMenu(); // Will Display The Main Menue Again And Again
            
        }
        
    
    
    
    
    
      void displayPlayerMenue(){
        
        
        System.out.println("\n\n--------------------------------------\n\n");
        System.out.println("Add Player----------------(Click 1)");
        System.out.println("Display Player Informatin-(Click 2)");
        System.out.println("Update Player Information-(Click 3)");
        System.out.println("Back to Main Menue--------(Click 0)");
        System.out.println("");
        System.out.print("Your Choice : ");
        int command = GetFixedInt();
       
            
       
            
            switch(command) {
                case 1 :
                
                 System.out.print("\n The Team You Want To Add The Player To (Team Name) : ");
                 String teamName = GetFixedString();       
     
                   addPlayer(teamName);//Function To Add Player
                
            break;
                case 2 :
                     
                         System.out.print("\n The Player (Team) : ");
                    teamName = GetFixedString();
                              // team name - team Id - Search by Id
                    if (!CheckExistTeam(teamName, 0, false)) {
                     //Functin to check the Existance of the them
                        System.out.print(ANSI_RED +"Sorry But Team Isnt Existed!!"+ANSI_RESET);
                        break;
                    }
                    DisplayTeamPlayers(teamName);
                        System.out.print("Player (Name) : ");
                        String MyPlayerName = GetFixedString();
                           System.out.print("Player T-Shirt (Number) : ");
                         int PlayerNumber = GetFixedInt();
                 
                 //Function to print player information
                   getPlayerInformation(teamName,MyPlayerName,PlayerNumber);
                    break;
                case 3 :
                     
                   System.out.print("\n The Team The Player (Team) : ");
                   teamName = GetFixedString();  //scan for lowerCase String
                   if (!CheckExistTeam(teamName, 0, false)) {
                     //Functin to check the Existance of the them
                        System.out.print(ANSI_RED +"Sorry But Team Isnt Existed!!"+ANSI_RESET);
                        break;
                    }
                   DisplayTeamPlayers(teamName);
                     System.out.print("Player (Name) : ");
                         MyPlayerName = GetFixedString();//scan for lowerCase String
                           System.out.print("Player T-Shirt (Number) : ");
                          PlayerNumber = GetFixedInt();//scan for Only Numbers
                 
                 
                  UpdatePlayer(teamName,MyPlayerName,PlayerNumber);//Send Player Data To Serch And Update Him
                 
                 
                  break;
                  case 0 :
                 
                  return;
                  
               
            default:
            System.out.println(ANSI_RED +"Wrong Input"+ANSI_RESET);
            break;
        }
        
        
            displayPlayerMenue();
            
        }
    
    
    
    
       
           
      void displayTeamMenu(){
        
        
        System.out.println("\n\n--------------------------------------\n\n");
        System.out.println("Add Team-------------------------------(Click 1)");
        System.out.println("Display All Teams----------------------(Click 2)");
        System.out.println("Display Team Informatin----------------(Click 3)");
        System.out.println("Update Team Information----------------(Click 4)");
        System.out.println("Searching For a Team-------------------(Click 5)");
        System.out.println("Display Team Players-------------------(Click 6)");
        System.out.println("Display Team Matches With All Details--(Click 7)");
        System.out.println("Back to Main Menue---------------------(Click 0)");
        System.out.println("\n");
       
        System.out.print("Your Choice : ");
        
        
        int command = GetFixedInt();
       
            
            switch(command) {
                case 1 :
                    
                    addTeam();
                    break;
                     case 2 :
       //Function to Show Players With Points or ShowScores or ShowAverageAges or RecivedGoals if All False Then Will Display Only Teams Names
                      displayAllTeams(false,false,false,false);
                    break;
                case 3 :
                         System.out.print("\nPlease Enter The Team Name : ");
                    String teamName = GetFixedString();
                    //Send Team Data To The Function To Display His Information
                    displayTeamInformation(teamName,0,false);
                    break;
                  case 4 :
                      //Function to update team
                      System.out.print("Enter The TeamName You Want To Update : ");
           
           String EnterdTeamName = GetFixedString();
          
           
          if (CheckExistTeam(EnterdTeamName,0,false)){
              
              UpdateTeam(EnterdTeamName);
              
              
          }else{
              
              System.out.println(ANSI_RED +"\nSorry But This Team Is Not Existed\n"+ANSI_RESET);
              
          }
                  
                  break;
                  case 5 :
                //Function to search for a team then display his information
                  SearchTeam();
                  break;
                  case 6 :
                     
                    System.out.print("\nPlease Enter The Team Name : ");
                    
                    teamName = GetFixedString();
                    if (CheckExistTeam(teamName, 0, false)) {
                Team team = (Team) GetTeam(teamName); 
                team.prinPlayers();//Funtion in team Class That Display All Players In The team
                      
                     
                 
                  
                      System.out.println("");
                        
                          }else{
                           
                           System.out.println(ANSI_RED +"Team Is Not Existed"+ANSI_RESET);
                           
                       }
                  
                  
                  break;
                   case 7 :
                   
                    System.out.print("\nPlease Enter The Team Name : ");
                    teamName = GetFixedString();
                       if (CheckExistTeam(teamName, 0, false)) {
                           Team team = (Team) GetTeam(teamName); 
               System.out.println("\n\n--------------------------------------\n\n");
               System.out.println("Team Matches :       "+ team.getMatches());
               System.out.println("Won Matches  :       "+ team.getWinMatchesCount());
               System.out.println("Lost Matches :       "+ team.getLostMatchesCount());
               System.out.println("draw Matches :       "+ team.getDrawMatchesCount());
               System.out.println("Team scored Goals :  "+ team.getScoredGoalsCount());
               System.out.println("Team Recived Goals : "+ team.getReceivedGoalsCount());
                        displayTeamMatches(teamName);

                       }else{
                           
                           System.out.println(ANSI_RED +"Team Is Not Existed"+ANSI_RESET);
                           
                       }
                    
               
                      
                  break;
                  
                 
                  
                  case 0 :
                 
                  return;
                  
               
            default:
            System.out.println(ANSI_RED +"Wrong Input"+ANSI_RESET);
            break;
        }
        
        
            displayTeamMenu();
            
        }
    
        void MatchesMenue(){
            
        System.out.println("\n\n--------------------------------------\n\n");
        System.out.println("Search For Match Using Date--(Click 1)");
        System.out.println("Disply All held Matches------(Click 2)");
        System.out.println("Update Match Data Using Id---(Click 3)");
        System.out.println("Back to Main Menue-----------(Click 0)");
        System.out.println("");
        System.out.print("Your Choice : ");
        
       int command = GetFixedInt();
       
            switch(command) {
                case 1 :
                    
                    
                    GetMatch();
                
            break;
                case 2 :
                    GetAllMatches();
                    break;
                case 3 :
                    System.out.print("\nMatch Id : ");
                    int MatchId=GetFixedInt();
                   //function to check if Match Is Existed using Id
                    if (CheckMatchExistance(MatchId)) {
                         UpdateMatch(MatchId);//Function to update match using gis Id
                    }else{
                        System.out.println(ANSI_RED +"Sorry But There is No Match With That Id !!"+ANSI_RESET);
                        
                    }
                    
                  break;
                 
                  case 0 :
                 
                  return;
                  
               
            default:
            System.out.println(ANSI_RED +"Wrong Input"+ANSI_RESET);
            break;
        }
        
        
            MatchesMenue();
            
            
            
            
        }
       
     void GeneralFunctionsMenue(){
           
       System.out.println("\n\n--------------------------------------\n\n");
       System.out.println("Display Teams Orderd by Their Points (Click 1)");
       System.out.println("Display Teams Orderd by Scored Goals (Click 2)");
       System.out.println("Display Teams Orderd by Ther Average Ages Of Its Players (Click 3)");
       System.out.println("Display Top 3 Players Who Scores The Greatest Number Of Goals (Click 4)");
       System.out.println("Display Top 3 Goalkeepers Who have The fewest Number Of Goals (Click 5)");
       System.out.println("Display Top 3 Players Orderd by Their Ranks (Click 6)");

        System.out.println("Back to Main Menue  ==> (0)");
        System.out.println("");
        System.out.print("Your Choice : ");
        
        
       int command = GetFixedInt();
        
            
            
            switch(command) {
                case 1 :
    // Sort by Using Points-Sort by Using ScoredGoals-Sort Using Average Age-Sorts by Recived Goals

                    SortTeamArr(true,false,false,false);
                      displayAllTeams(true,false,false,false);
            break;
                case 2 :
        // Sort by Using Points-Sort by Using ScoredGoals-Sort Using Average Age-Sorts by Recived Goals
                
                    SortTeamArr(false,true,false,false);
                    displayAllTeams(false,true,false,false);
                    break;
                case 3 :
           // Sort by Using Points-Sort by Using ScoredGoals-Sort Using Average Age-Sorts by Recived Goals
             
                     SortTeamArr(false,false,true,false);
                     displayAllTeams(false,false,true,false);
                    
                  break;
                case 4 :
                    
                     SortPlayersArr();
                     //Display by Ranks if true Else Display By Scored Goals
                    displayAllPlayers(false);
                  break;
                 case 5 :
                 // Sort by Using Points-Sort by Using ScoredGoals-Sort Using Average Age-Sorts by Recived Goals

                    SortTeamArr(false,false,false,true);
                     displayAllTeams(false,false,false,true);
                  break;
                   case 6 :
                    SortPlayersArr();
                     displayAllPlayers(true);
                  break;
                  case 0 :
                 
                  return;
                  
               
            default:
            System.out.println(ANSI_RED +"Wrong Input"+ANSI_RESET);
            break;
        }
        
        
          GeneralFunctionsMenue();
            
            
           
       }
      
      
     //------------------------------------------------------------------------------------------
      
      
      
      
      
      
      
      
      
      
      //--------------------------- Teams -------------------------------------------------
   
       
       //Create Teams On Openning The Applicatons To Speed Test
       void autoComplete(){
           //Array Of Teams Names
            String[] Teams = {"team1", "team2", "team3", "team4","team5","team6","team7","team8","team9","team10"};
           
           for (int i = 0; i < 10; i++) {
               
              Team team = new Team(true,Teams);
               
              
             
               teamArr.add(team);
               
              
            
               
               
               
           }
           
          
           
           
           
           
       }
       
       
       void displayAllTeams(boolean ShowPoints,boolean ShowScores,boolean ShowAverageAges,boolean RecivedGoals){
           int x=1;
           System.out.println("");
           //For Every Opject In The ArraList Do Something
             for (Team team : teamArr) {
                 //Print Showing Teams Points
                        if (ShowPoints) {
                            
                     System.out.println("Team Name " + team.getName() + " Total Points " + team.getPoints()) ;
               //Print Showing Teams Scores
                        }else if(ShowScores){
                     
                    System.out.println("Team Name " + team.getName() + " Total Score " + team.getScoredGoalsCount()) ;
  
                       //Print Showing Teams Average Ages
                 }else if(ShowAverageAges){
                     
                  System.out.println("Team Name " + team.getName() + " Avrage Age " + team.getPlayersAverageAge()) ;
   
                     
                     //  //Print Showing Teams RecivedGoals With The GoalKeeper Name
                 }else if(RecivedGoals){
                            if (team.isWeHaveGoalkeeper()==false) {
                              
                               continue;
                            }
          System.out.println("");
         
       
            System.out.println("    " + x);
            System.out.println("GoalKeeper Name : " + team.getGoalkeeper());
            System.out.println("Team            : " + team.getName());
            System.out.println("Recived Goals   : " + team.getGoalReceivedGoals());
            
            x++;
            if (x==4) {
                break;
            }else{
            System.out.println("----------------------------------------------");
            }
            
        
                 }else{
                   
                    System.out.println("");
                    System.out.println("  " + x +" - " + team.getName());                    
                    System.out.println("\n     Players \n");
                    team.prinPlayers();
                            
                            x++;
                System.out.println("----------------------------------------------");
                 }
                      
                    }
           
            System.out.println("");
       }
       
       
       
       void addTeam(){
           
           //Array That Contains Every Team NameIn ArrayList
           String[] TeamsNames=new String[teamArr.size()];
                    
  
     //Create New Team Opject Sending The TeamsNames In The Constructor And Without autoComplete So We Made It False
                    Team T = new Team(false,TeamsNames);
                   
                   
                   //Add The Team To The ArrayList
                    teamArr.add(T);
                
           
           
           
       }
       
       //Function To Search For Team Using Name Or Id And Display His Information 
       void displayTeamInformation(String teamName,int Id,boolean SearchById){
           
           
          
                  
                   
                   if (SearchById) {
                       if (CheckExistTeam(teamName,Id,true)) {
                        
                        for (Team team : teamArr) {
                       
                    if(team.getId2()==(Id)){
                        System.out.println("\n\n--------------------------------------\n\n");
                        System.out.println("Team Name    :       "+team.getName());
                        System.out.println("Team Id      :       "+ team.getId2());
                        System.out.println("Team Points  :       "+team.getPoints());
                        System.out.println("Team Captain :       "+team.getCaptain());
                        System.out.println("Team Matches :       "+ team.getMatches());
                        System.out.println("Won Matches  :       "+ team.getWinMatchesCount());
                        System.out.println("Lost Matches :       "+ team.getLostMatchesCount());
                        System.out.println("draw Matches :       "+ team.getDrawMatchesCount());
                        System.out.println("Team scored Goals :  "+ team.getScoredGoalsCount());
                        System.out.println("Team Recived Goals : "+ team.getReceivedGoalsCount());
                        System.out.println("\n  Team Players\n");
                        team.prinPlayers();
                         System.out.println("\n");
                                            
                        return;
                    }
                        
                    }
               
           }else{
                        
                        System.out.println(ANSI_RED +"\n" + Id + " Is Not Existed"+ANSI_RESET); 
                        
                      
                        
                    }
                       
               
           }else{
                    if (CheckExistTeam(teamName,0,false)) {
                        
                        for (Team team : teamArr) {
                       
                    if(team.getName().equals(teamName)){
             System.out.println("\n\n--------------------------------------\n\n");
                        System.out.println("Team Name    :       "+team.getName());
                        System.out.println("Team Id      :       "+ team.getId2());
                        System.out.println("Team Points  :       "+team.getPoints());
                        System.out.println("Team Captain :       "+team.getCaptain());
                        System.out.println("Team Matches :       "+ team.getMatches());
                        System.out.println("Won Matches  :       "+ team.getWinMatchesCount());
                        System.out.println("Lost Matches :       "+ team.getLostMatchesCount());
                        System.out.println("draw Matches :       "+ team.getDrawMatchesCount());
                        System.out.println("Team scored Goals :  "+ team.getScoredGoalsCount());
                        System.out.println("Team Recived Goals : "+ team.getReceivedGoalsCount());
                        System.out.println("\n  Team Players\n");
                        team.prinPlayers();
                        System.out.println("\n");
                           return;                      
                    }
                        
                    }
               
           }else{
                        
                        System.out.println(ANSI_RED +"\n" + teamName + " Is Not Existed"+ANSI_RESET); 
                        
                      
                        
                    }   
                   }
                    
                    
                  
                    
                    
                    
           
           
           
           
           
           
       }
       
       //Function To Update Team
       void UpdateTeam(String EnterdTeamName){
           
         
           
          
          
           
          
              
                for (Team team : teamArr) {
                        
                    if(team.getName().equals(EnterdTeamName)){
                        System.out.println("\n\n--------------------------------------\n\n");
                        System.out.println("Update Team Name-----Click (1) ");                       
                        System.out.println("Update Points--------Click (2)");
                        System.out.println("Update Captain-------Click (3) "); 
                        System.out.println("Back to Main Menue---Click (0)" );
                     //   System.out.println("Update scored Goals : Click (5) ");
                      //  System.out.println("Update Recived Goals : Click (6) ");
                        
                        
                        System.out.print("\n Your Choice : ");
                        
                        int command = GetFixedInt();
       
            switch(command) {
                case 1 :
                  
                     System.out.print("\n The New Name : ");
                    String newName=GetFixedString();
                    if(CheckExistTeam(newName,0,false)){
                        
                        System.out.println(ANSI_RED +"Sorry But This Name Is Existed"+ANSI_RESET);
                        
                    }else{
                        team.setName(newName);
                 displayTeamInformation(newName,0,false);
                 System.out.println(ANSI_GREEN +"Team Name Has been Changed Successfully ^-^"+ANSI_RESET); 
                 UpdateTeam(newName);
                 
                 
                    }
                    
                    
                    break;
                  case 2 :
                      
                   System.out.print("\n The New Points : ");
                    int newPoints=GetFixedInt();
                     team.setPoints(newPoints);
                    displayTeamInformation(EnterdTeamName,0,false); 
                   System.out.println(ANSI_GREEN +"Team Points Has been Changed Successfully ^-^"+ANSI_RESET);   
                    UpdateTeam(EnterdTeamName);   
                   
                    break;
                  case 3 :
                     team.ChooseCaptain();//Function In Team Class That Alows Me To Change Captin
                   
                  displayTeamInformation(EnterdTeamName,0,false); 
                   System.out.println(ANSI_GREEN +"Team Captain Has been Changed Successfully ^-^"+ANSI_RESET);   
                   
                   UpdateTeam(EnterdTeamName);
                  break;
                  
                  //Sorry But I Cant Change Teams Scores Without Changes The Players Whose Scored It
                 /* case 5 :
                   int newScoredGoals=GetFixedInt();
                   
                        team.setScoredGoalsCount(newScoredGoals);
                  break;
                   case 6 :
                   int newRecivedGoals=GetFixedInt();
                   
                     team.setRecievedGoalsCount(newRecivedGoals);
                  break;*/
                  
                  case 0 :
                 
                  return;
                  
               
            default:
            System.out.println("Wrong Input");
            UpdateTeam(EnterdTeamName);
            break;
        }
                                            
                       
                       
                    }
           
                }
              
              
              
              
         
           
           
           
           
           
           
           
           
           
           
       }
       
       //Check If The Team Existed Using His Name Or Id
       boolean CheckExistTeam(String TeamName,int TeamId,boolean SearchById){
           
           if (SearchById) {
               
               
               for (Team team : teamArr) {
                        
                    if(team.getId2()== (TeamId)){
                        
                       return true;
                                            
                       
                    }
               
               
               
               
           }
            return false;
           }else{
               
           
            for (Team team : teamArr) {
                        
                    if(team.getName().equals(TeamName)){
                        
                       return true;
                                            
                       
                    }
           
           
           
    
       
       }
               return false; 
           }
           
          
     
   
}
       
       
       //Menue To Search For A Team
       void SearchTeam(){
           
           
           
           System.out.println("\n\n--------------------------------------\n\n");
           System.out.println("\nSearch by Name--(Click 1)");
            System.out.println("\nSearch by Id---(Click 2)");
            System.out.println("\nMain Menue-----(Click 0)");
           
           
           System.out.print("\nyour Choice : ");
           int command = GetFixedInt();
        
            switch(command) {
                case 1 :
                    System.out.print("Enter Team Name : ");
                   
                   String TeamName=GetFixedString();
                   
                    displayTeamInformation(TeamName,0,false);
                    break;
                  case 2 :
                        System.out.print("Enter Team Id : ");
                   int Id=GetFixedInt();
                    
                    displayTeamInformation("",Id,true);
                    break;
                 
                  case 0 :
                 
                  return;
                  
               
            default:
            System.out.println("\nWrong Command\n");
            break;
        }
        
           
           
           SearchTeam();
       }
       
       
       
       
       
       
       //Search For Team Using His Name And Return Object
      Object GetTeam(String teamName){
          
           for (Team team : teamArr) {
                       
                    if(team.getName().equals(teamName)){
                        
                       
                                            
                        return team;
                    }
                        
                    }
          
          
          
          return null;
          
          
      }
       
 
         
     
       
      
       
       
       
       
       //----------------------------------------------------------------------------------------
       
       
       
       
       
       
       
       
       
       
       
   
      
      //----------------------------- Players --------------------------------------------------
//Display Team Player Showing Their Ranks Or Their Scored Goals
    void   displayAllPlayers(boolean Display_By_Ranks){
        
         System.out.println("");
         int x=1;
         String folows;
        for (Player player : P) {
             switch (x) {
                 case 1:
                     folows="th";
                     break;
                 case 2:
                     folows="nd";
                     break;
                 default:
                     folows="rd";
                     break;
             }
            System.out.println("    " + x);
            System.out.println("Name : " + player.getName());
            System.out.println("Team : " + player.getTeam());
            if (Display_By_Ranks) {
                System.out.println("Rank : " + x + folows);
            }else{
                System.out.println("Score : " + player.getScore());
            }
            
            
            x++;
            if (x==4) {
                break;
            }else{
            System.out.println("----------------------------------------------");
            }
            
        }
        
        
        
        
        
        
        
        
        
        
        
        
        
    }
       
       
       
       
       
       //Add New Player 
void addPlayer(String teamName){
    
    
                   if(CheckExistTeam(teamName, 0, false)){           
                         for (Team team : teamArr) {
                    if(teamName.equals(team.getName())){ 
                      team.Manual_addPlayer();   //Function In Team Class That Alows Me To Add New Player Into The ArrayList                
                    System.out.println(ANSI_GREEN +"Player has been Successfully Added ^-^"+ANSI_RESET);   
                    }
                      }
                        
                      }else{
                        
                        
                          System.out.println(ANSI_RED +"Team Is Not Existed"+ANSI_RESET);
                        
                        
                    }
    
    
}

//Get Payer Information and Scored Gols in Ech Match
void getPlayerInformation(String teamName,String MyPlayerName,int PlayerNumber){
    
                   if(CheckExistTeam(teamName, 0, false)){           
                         for (Team team : teamArr) {
                    if(teamName.equals(team.getName())){
                       
                  if (team.PlayerIsExisted(MyPlayerName,PlayerNumber)) {
           Player player =  (Player) team.getPlayer(MyPlayerName,PlayerNumber);
           System.out.println("\n\n--------------------------------------\n\n");
            System.out.println("\nPlayer Name : " + player.getName());
              System.out.println("Player Age : " + player.getAge());
              System.out.println("Player T-Shirt Number : " + player.getNumber());
              System.out.println("Player Type (Role) : " + player.getType());
              displayPlayoutoleDescription(player.getType());
              System.out.println("Player Score : " + player.getScore());
              
              
         player.GetPlayerScoredMatches();//Function In Player Class That Prints Every Scored Goals That The Played Made In His Life
                System.out.println("");
       }else{
           
           System.out.println(ANSI_RED +"This Player Isnt Existed !! "+ANSI_RESET);
           return;
         
           
          
       }
                   
                       
         }
           }
                        
           }else{
                        
                        
           System.out.println(ANSI_RED +"Team Is Not Existed"+ANSI_RESET);
                        
                        
          }
    
    
    
    
    
}


       
        //Just Function To Display Players Roles Descriptions
       
      void displayPlayoutoleDescription(String Playoutole){
           
           
            if (Playoutole.equals("Center")) {
              System.out.println("(Role description)=> The player who snaps the ball to the quarterback. He handles the ball on every play.");
                      }else if(Playoutole.equals("midfielder")){
                          
               System.out.println("(Role description)=> is a player whose usual position is in the central area of the playing field between the two goals.");
            
                          
                      }else if(Playoutole.equals("defender")){
                          
          System.out.println("(Role description)=> The defender role is preventing the attacker from either passing forward, dribbling toward the goal or shooting.");
             
                          
                      }else{
                          
                          
                 System.out.println("(Role description)=> the player who stands in the team's goal to try to stop the other team from scoring");
                   
                          
                          
                          
                      }
           
           
           
           
           
           
       }
       
      void DisplayTeamPlayers(String teamName){
    
     for (Team team : teamArr) {
                       
                    if(team.getName().equals(teamName)){
                       
                        team.prinPlayers();        
                        System.out.println("");
                        return;
                    }
                        
                    }
    
    
    
}
    
    
    //Update Player Data Usig His TeamName And The Player Name And His Number
    
  void UpdatePlayer(String teamName,String MyPlayerName,int PlayerNumber){
        
        
        
       
        
                   if(CheckExistTeam(teamName, 0, false)){           
                         for (Team team : teamArr) {
                    if(teamName.equals(team.getName())){
                      
                  if (team.PlayerIsExisted(MyPlayerName,PlayerNumber)) {
                      Player Newplayer =null; //Make Empty Object Of Player
           Player Oldplayer =  (Player) team.getPlayer(MyPlayerName,PlayerNumber);//Get The Player Object Using Function In Team Using His Name And Number
           Newplayer = Oldplayer;//Get Data From Old Player To the New To Change Data In The New And Replace It With The Old One
           System.out.println("\n\n--------------------------------------\n\n");
              System.out.println("\nChange Player Name-----------Click (1) ");
              System.out.println("Change Player Age------------Click (2) " );
              System.out.println("Change Player T-Shirt Number-Click (3) " );
              System.out.println("Change Player Type-----------Click (4) " );
              System.out.println("Back to Main Menue-----------Click (0) " );
               System.out.println("");
               
               
                System.out.print("Your Choice : ");
               
        int command = GetFixedInt();
        

            switch(command) {
                
           case 1 :
                 System.out.print("New (Name) : "); 
                 String NewName=GetFixedString();
                 Newplayer.setName(NewName);
                 team.ChangePlayerData(Oldplayer,Newplayer);//Function In Team Class That Replace Old Player With Another New Player 
                 getPlayerInformation(teamName,NewName,PlayerNumber);//Show Player Information After Update
                 System.out.println(ANSI_GREEN +"Name has been Changed Successfully ^-^"+ANSI_RESET);   
                 UpdatePlayer( teamName, NewName,PlayerNumber);//Back to The Same Player To Give The Apility to Jupdate Him Again
               
            break;
         case 2 :
       System.out.print("New Age : "); 
                 int NewAge=GetFixedInt();
                 while (team.WrongAge(NewAge))//Function In Team Class To Check If The Age Is Accepted Or No
                 {   
                
                System.out.println(ANSI_RED +"Age Can Not be Acceptd !! "+ANSI_RESET);
                System.out.print("Enter Player Age Again Please :   ");
                NewAge = GetFixedInt();
                
            }
           
                 Newplayer.setAge(NewAge);
                 team.ChangePlayerData(Oldplayer,Newplayer);
                 team.PlayersAverageAgeChanger();
                  getPlayerInformation(teamName,MyPlayerName,PlayerNumber);
                  System.out.println(ANSI_GREEN +"Age has been Changed Successfully ^-^"+ANSI_RESET);   
                  UpdatePlayer( teamName, MyPlayerName,PlayerNumber);
                  
          break;
          case 3 :
                     System.out.print("New T-Shirt (Number) : "); 
                 int NewNumber=GetFixedInt();
                 while (!team.NumberIsOk(NewNumber))//Function In Team Class To Check If The T-Shirt Number Existed Or No
                 {   
                
                System.out.println(ANSI_RED +"Sorry But T-Shirt Number Is Existed !! "+ANSI_RESET);
                System.out.print("Enter T-Shirt (Number) Again Please :   ");
                NewNumber = GetFixedInt();
                
            }
           
                 Newplayer.setNumber(NewNumber);
                 team.ChangePlayerData(Oldplayer,Newplayer);
                 
                  getPlayerInformation(teamName,MyPlayerName,NewNumber);
                  System.out.println(ANSI_GREEN +"T-Shirt has been Changed Successfully ^-^"+ANSI_RESET);   
                  UpdatePlayer( teamName, MyPlayerName,NewNumber);
         break;
                 case 4 :
                     team.PrintPlayerTypes();
                System.out.print("Choose The New Type (Number) : "); 
                 int NewType=GetFixedInt();
                  //While Choosing type Check If You Have GoalKeeper Or No and If You Have Then You Cant Have Another One unless You Changed the GoalKeeper Role Then You Can Have Another GoalKeeper Insteed Of Him  
                //team.WrongTypePick Is A Function In TeamClass To Check If His Pick Index In the array Or Not
                 while (team.WrongTypePick(NewType) ||(Oldplayer.getType().equals("goalkeeper")&& NewType!=3&&!team.WrongTypePick(NewType)) || (Oldplayer.getType().equals("goalkeeper")&& NewType==3&&!team.WrongTypePick(NewType)) || (!Oldplayer.getType().equals("goalkeeper")&& NewType==3&&!team.WrongTypePick(NewType)&&team.isWeHaveGoalkeeper()) || (!Oldplayer.getType().equals("goalkeeper")&& NewType==3&&!team.WrongTypePick(NewType)&&!team.isWeHaveGoalkeeper())) {   
                if (Oldplayer.getType().equals("goalkeeper")&& NewType!=3&&!team.WrongTypePick(NewType)) {
                         team.setGoalkeeper(null);//Delete The GoalKeeper
                         team.setWeHaveGoalkeeper(false);//Make GoalKeeper Position Avilable
                         team.setGoalReceivedGoals(0);
                        
                         break;
                     }else if(Oldplayer.getType().equals("goalkeeper")&& NewType==3&&!team.WrongTypePick(NewType)){
                     
                     System.out.println(ANSI_RED +"the Player is Allredy a Goalkeeper"+ANSI_RESET);
                      System.out.print("Your Answer (Number) : ");
                       NewType = GetFixedInt();
                    
                            }else if(!Oldplayer.getType().equals("goalkeeper")&& NewType==3&&!team.WrongTypePick(NewType)&&team.isWeHaveGoalkeeper()){
                               
                         System.out.println(ANSI_RED +"Sorry But You Allredy Have Goalkeeper (Change The Goalkeeper Position First)"+ANSI_RESET);
                         System.out.print("Your Answer (Number) : ");
                NewType = GetFixedInt();
                     }else if(!Oldplayer.getType().equals("goalkeeper")&& NewType==3&&!team.WrongTypePick(NewType)&&!team.isWeHaveGoalkeeper()){
                         
                         team.setGoalkeeper(Oldplayer.getName());//Add Player Name As AgoalKeeper
                         team.setWeHaveGoalkeeper(true);//Make GoalKeeper Position Isnt Avilable for AnyOne else
                         
                        Player Newplayer2 =null; //Make Empty Object Of Player
            Oldplayer =  (Player) team.getPlayer(MyPlayerName,PlayerNumber);//Get The Player Object Using Function In Team Using His Name And Number
            team.setGoalReceivedGoals(Oldplayer.getReceivedGoals());

                       
                         break;
                         
                     }else{
                         System.out.println(ANSI_RED +"Wrong Input Please Try Again !! "+ANSI_RESET);
                System.out.print("Your Answer (Number) : ");
                NewType = GetFixedInt();
                     }
               
            }
           
                 Newplayer.setType(team.Type[NewType]);
                 team.ChangePlayerData(Oldplayer,Newplayer);//Change Old Player With The Updated One
                 
                  getPlayerInformation(teamName,MyPlayerName,PlayerNumber);//And Display His Information
                  System.out.println(ANSI_GREEN +"Type has been Changed Successfully ^-^"+ANSI_RESET); 
                   UpdatePlayer( teamName, MyPlayerName,PlayerNumber);//And Back To Update The Same Player
          break; 
          case 0:
              break;
           default:
          System.out.println("Wrong Input");
             UpdatePlayer( teamName, MyPlayerName,PlayerNumber);
            return;
        }
            
               
       }else{
           
           System.out.println("This Player Isnt Existed !! ");
           return;
         
       }
                   
                       
         }
           }
                        
           }else{
                        
                        
           System.out.println("Team Is Not Existed");
                        
                        
          }
    
        
    }
    
    //--------------------------------------------------------------------------------------------------
    
    
    
    
    
    
    
    
    
    
         //-------------------------------------- Matches -------------------------------------------  
      
     void addPlayedMatch(){
     
         
         String teamA,teamB;
         System.out.println("\n\n--------------------------------------\n\n");
        System.out.println("Enter date (format dd-MM-yyyy): ");
        String line = GetFixedString();
        
       
         
         while(!isThisDateCorrect(line,"dd-MM-yyyy")){
               System.out.println(ANSI_RED+"You have to enter date in format dd-MM-yyyy"+ANSI_RESET);
                System.out.println("Enter date (format dd-MM-yyyy): ");
                 line = GetFixedString();
              
          }
       
              Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(line);
        } catch (ParseException ex) {
            Logger.getLogger(Start.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
           
       
       
         System.out.print("Enter Stadium (Name) :  ");
         String Stadium = GetFixedString();
        
        System.out.print("Enter Team A (Name): ");
        line = GetFixedString();
        teamA=line;
        Team home = null;//Make Empty Team To Add The New Data And Replace it With The Old One
          for(Team team : teamArr){
              if(team.getName().equals(line))
                  home = team;
             
          }  
          if (home == null) {
              System.out.println(ANSI_RED+"Team Isnt Existed"+ANSI_RESET);
              return;
          }else{
              //Check If The Team Have A GoalKeeper Or Not (If Not Then You Will Have To Add New One)
               while (!home.isWeHaveGoalkeeper()) {
                  System.out.println(ANSI_RED +"You Dont Have Goalkeeper Please Add one !!"+ANSI_RESET);
                  home.addGoalKeeper();//Function In Team Class To Add New GoalKeeper To The Team
              }
          }
          System.out.print("Enter Team B (Name) : ");
          line = GetFixedString();
          teamB=line;
          //If Te Team Played Against HimSelf
          while (teamB.equals(teamA)) {
              System.out.println(ANSI_RED +"Sorry But The Team Cant Play Agains himSelf !!"+ANSI_RESET);
              System.out.print("Enter Team B (Name) : ");
          line = GetFixedString();
          teamB=line;
         }
          
          Team away = null;
           for(Team team : teamArr){
              if(team.getName().equals(line)){
                  away = team;
                  //Check If The Two Teams Played Against Echother Twice Or Not
               if (!(team.MatchOk(home))) {
                   System.out.println(ANSI_RED +"\nSorry But "+home.getName() + " and " + away.getName() +  " Can not Play more than 2 times\n"+ANSI_RESET);
                   return;
               }
              }
          } 
           if (away == null) {
              System.out.println(ANSI_RED +"Team Isnt Existed"+ANSI_RESET);
              return;
          }else{
                while (!away.isWeHaveGoalkeeper()) {
                  System.out.println(ANSI_RED +"You Dont Have Goalkeeper Please Add one !!"+ANSI_RESET);
                  away.addGoalKeeper();
              }
           }
           
           System.out.print("Enter  Team A goals : ");
         
           int homeGoals = GetFixedInt();
            
         
        
         
         
         System.out.print("Enter Team B goals : ");
          
           int awayGoals = GetFixedInt();                
            
         
         Match match = new Match();//New Object Of Match Class
         match.setDate(date);
         match.setStadium(Stadium);
         match.setTeamA(home);
         match.setTeamB(away);
          match.setTeamAScore(homeGoals);
         match.setTeamBScore(awayGoals);
         Match.Id=(Match.Id+1);//Add 1 To Match Id
         match.setId2(Match.Id);
         matches.add(match);//Add Match To The ArrayList Of Matches
         //After That Change Scores In Ech Team
         home.setScoredGoalsCount(home.getScoredGoalsCount()+homeGoals);
         away.setScoredGoalsCount(away.getScoredGoalsCount()+awayGoals);
         home.setRecievedGoalsCount(home.getReceivedGoalsCount()+awayGoals);
         away.setRecievedGoalsCount(away.getReceivedGoalsCount()+homeGoals);
         home.setGoalReceivedGoals(home.getGoalReceivedGoals()+awayGoals);
         away.setGoalReceivedGoals(away.getGoalReceivedGoals()+homeGoals);
         home.setMatches(home.getMatches()+1);
         away.setMatches(away.getMatches()+1);
         
         if (homeGoals > awayGoals) {            
             home.setPoints(home.getPoints()+3);
             home.setWinMatchesCount(home.getWinMatchesCount()+1);
             away.setLostMatchesCount(away.getLostMatchesCount()+1);
             
         }
         
         else if (homeGoals < awayGoals) {            
             away.setPoints(away.getPoints()+3);
             away.setWinMatchesCount(away.getWinMatchesCount()+1);
             home.setLostMatchesCount(home.getLostMatchesCount()+1);
         }
         
         else {
             home.setPoints(home.getPoints()+1);
             away.setPoints(away.getPoints()+1);
             home.setDrawMatchesCount(home.getDrawMatchesCount()+1);
             away.setDrawMatchesCount(away.getDrawMatchesCount()+1);
         }   
       
          Scanner scn2=new Scanner(System.in);
         for(Team team : teamArr){
              if(team.getName().equals(teamA)){
                  if (homeGoals>0) {
             int myGoals=homeGoals;
                //Add Scores To The Player Whose Scored It In HashMap that Contains The Match As A key And The Scored Goals As A Value 
             while (myGoals>0) {
                 System.out.println("");
                 team.prinPlayers();
                 System.out.println("");
                 System.out.print("The Player (Name) Whose Scored Goals In Team A : ");
                 String PlayerName=GetFixedString();
                 System.out.print("His (T-Shirt Number) : ");
                 int PlayerNumber=GetFixedInt();
                 if (team.PlayerIsExisted(PlayerName, PlayerNumber)) {
                     
                 System.out.print("How Much Goals " + PlayerName + " Scored ( Avilabele "+ myGoals + " Goals ) : ");
                 int GoalsEnterd=GetFixedInt();
                 while (myGoals-GoalsEnterd<0) {                     
                     System.out.println(ANSI_RED +"Sorry But The Team Only Scored " + myGoals + " Goals"+ANSI_RESET);
                      System.out.print("How Much Goals " + PlayerName + " Scored ( Avilabele "+ myGoals + " Goals ) : ");
                     GoalsEnterd=GetFixedInt();
                     
                     
                 }
                 
                 myGoals-=GoalsEnterd;
                  
                      Player Newplayer =null;
           Player Oldplayer =  (Player) team.getPlayer(PlayerName,PlayerNumber);
           Newplayer = Oldplayer;
           
           Newplayer.setScore(GoalsEnterd+Newplayer.getScore());//Function In Player Class To Add His Score And Match To The Hsh Map
         
                     Newplayer.ChangePlayerScores(match, GoalsEnterd);
                    team.ChangePlayerData(Oldplayer, Newplayer);
                 }else{
                 System.out.println(ANSI_RED +"The Player Isnt Existed"+ANSI_RESET);
 
                 }
 
                 
             }
             
             
           
             
         }//Function To Add The Other Team As Akey In HashMap In Team Class Wit How Many Times We Played Against
                  home.addToGoalKeeperReceivedGoals(awayGoals);
                  home.teamWePlayedAgainst(away); 
                  
                   team  = home;
                  
            
                  
              }
                
          }  
                   
              for(Team team : teamArr){
              if(team.getName().equals(teamB)){
                   if (awayGoals>0) {
             int myGoals=awayGoals;
                   
             while (myGoals>0) {
                 System.out.println("");
              team.prinPlayers();
                 System.out.println("");
                 System.out.print("The Player (Name) Whose Scored Goals in Team B : " );
                 String PlayerName=GetFixedString();
                 System.out.print("His (T-Shirt Number) : ");
                 int PlayerNumber=GetFixedInt();
                 if (team.PlayerIsExisted(PlayerName, PlayerNumber)) {
                     
                 System.out.print("How Much Goals " + PlayerName + " Scored ( Avilabele "+ myGoals + " Goals ) : ");
                 int GoalsEnterd=GetFixedInt();
                 while (myGoals-GoalsEnterd<0) {                     
                     System.out.println(ANSI_RED +"Sorry But The Team Only Scored " + myGoals + " Goals"+ANSI_RESET);
                      System.out.print("How Much Goals " + PlayerName + " Scored ( Avilabele "+ myGoals + " Goals ) : ");
                     GoalsEnterd=GetFixedInt();
                       
                 }
                 
                   myGoals-=GoalsEnterd;
                     
                      Player Newplayer =null;
           Player Oldplayer =  (Player) team.getPlayer(PlayerName,PlayerNumber);
           Newplayer = Oldplayer;
          
           Newplayer.setScore(GoalsEnterd+Newplayer.getScore());
           
         
                     Newplayer.ChangePlayerScores(match, GoalsEnterd);
                    team.ChangePlayerData(Oldplayer, Newplayer);
                 }else{
                 System.out.println(ANSI_RED +"The Player Isnt Existed"+ANSI_RESET);
 
                 }
 
                 
             }
             
             
            
         }
                  away.addToGoalKeeperReceivedGoals(homeGoals);
                  away.teamWePlayedAgainst(home);
                    
                   team  = away ;
                  
                   
             
              }
                
          }       
                   
           
           
       
              System.out.println(ANSI_GREEN + "Match has been Held Successfully ^-^" +ANSI_RESET);
              
                   
    } 
       
     
     
     
     
       //Function To Display All Team Matches     
       void displayTeamMatches(String teamName){
     
                       if (CheckExistTeam(teamName, 0, false)) {
                          
                           Team team = (Team) GetTeam(teamName); 
                         
                           
                           getTeamMatches(team);
               
                      
                       }else{
                           
                           System.out.println("Team Is Not Existed");
                           
                       }
           
       }
       
        
       

public boolean isThisDateCorrect(String dateToValidate, String dateFromat){
		
		if(dateToValidate == null){
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
		
		try {
			
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			
		
		} catch (ParseException e) {
			
			
			return false;
		}
		
		return true;
	}
       
       //Update Match Using His Id
      void UpdateMatch(int MatchId){
          
            System.out.println("\n\n--------------------------------------\n\n");
            System.out.println("\n Change Match Date----(Click 1)");
            System.out.println("\n Change Match Stadium-(Click 2)");
            System.out.println("\n Main Menue-----------(Click 0)");
           
           
           System.out.print("\nyour Choice : ");
            int command = GetFixedInt();
        
            switch(command) {
                case 1 :
                   
                    ChangeMatchDate(MatchId);
                    break;
                  case 2 :
                       ChangeMatchStadium(MatchId);
                    
                  
                    break;
                 
                  case 0 :
                 
                  return;
                  
               
            default:
            System.out.println("\nWrong Input\n");
            break;
        }
        
           
           
           UpdateMatch(MatchId);
                
                
                



          
       }  
     
     //Check If Match Existed Or No Using His Id
      boolean CorrectMatchId(int MatchId){
           
            for (Match m : matches) {
           
           
            if (m.getId2()==MatchId ) {
                
                return true;



            }
       }   
           
           
           return false;
           
       }
     
      //Get Team Matches That Played 
       void getTeamMatches(Object team){
           
           
            int x = 1;
           for (Match m : matches) {
           
           
            if (m.getTeamA().equals(team) || m.getTeamB().equals(team) ) {
              System.out.println("\n--------------------------------------\n\n");
               System.out.println("\n    Match " + x);
               System.out.println("\nMatch Id : " + m.getId2());
               System.out.println("Match Date : " + m.getDate());
               System.out.println("Match Stadium : " + m.getStadium());
               System.out.println("\n   Scores  \n" );
                 x++;
                System.out.println(m.getTeamA().getName()+ "  " + m.getTeamAScore() + " : "+ m.getTeamBScore()+ " "+ m.getTeamB().getName());
                }
       }   
           
           
           
           
           
       }
       
       //Get Every Match In The Array With Details
       void GetAllMatches(){
           
           int x = 1;
           for (Match m : matches) {
               System.out.println("\n\n--------------------------------------\n\n");
                System.out.println("\n    Match " + x);
               System.out.println("\nMatch Id : " + m.getId2());
               System.out.println("Match Date : " + m.getDate());
                System.out.println("Match Stadium : " + m.getStadium());
                 System.out.println("\n   Scores  \n" );

                System.out.println(m.getTeamA().getName()+ "  " + m.getTeamAScore() + " : "+ m.getTeamBScore()+ " "+ m.getTeamB().getName());
                
                System.out.println("\n");
                x++;

       }   
           
           
           
       }
       
       
       
       
        
       
       //Get Match Using Data
       
    void GetMatch() {
        
        System.out.print("Enter year: ");
        int Y=GetFixedInt();
       
          System.out.print("Enter Month: ");
          int M=GetFixedInt();
          
          while (M>12||M<1) {    
              System.out.print(ANSI_RED + "Unknown Month " + ANSI_RESET);
              System.out.print("Enter Month: ");
           M=GetFixedInt();
            
        }
         //Array Of Months And We Made Index 0 Empty Because There Is No Manths (0) Because Months Start From (1)
         String[] months = {
            "",
             "January", "February", "March",
             "April", "May", "June",
             "July", "August", "September",
             "October", "November", "December"  
         };
         
         //Array Contains Dayes In Every Month
         int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
         //If Month Is March Then We Have To Check If It Is A Leap Year Or Not
         if (M == 2 && isLeapYear(Y))
             days[M] = 29;
         
         System.out.println("    " + months[M] + " " + Y);
         System.out.println("S  M  Tu  W  Th  F  S");//Print Weak Dayes
         
         int d = day(M, 1, Y);
         String space = "";
         //Display The Calendar
         for (int i = 0; i < d; i++)
             System.out.print("   ");
         for (int i = 1; i <= days[M]; i++) {
             if (i<10)
                 System.out.print(i +"  ");
             else 
                 System.out.print(i+" ");
             if (((i + d) % 7 == 0) || (i == days[M]))
                 System.out.println();
         }
         
         System.out.print("Enter day: ");
        int D=GetFixedInt();
       if (D == 0 || days[M] < D) {
           System.out.println("You have t enter day in month");
           return;
       }  
       
       Calendar cal = Calendar.getInstance();
       cal.set(Y, M-1, D);
       for (Match m : matches) {
           Calendar cal2 = Calendar.getInstance();
           cal2.setTime(m.getDate());
           //Get Every Match Happend At The Same Data
            if (cal.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)&& cal.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
                System.out.println("\n\n--------------------------------------\n\n");
               System.out.println("\nMatch Id : " + m.getId2());
               System.out.println("Match Date : " + m.getDate());
                System.out.println("Match Stadium : " + m.getStadium());
                 System.out.println("\n   Scores  \n" );

                System.out.println(m.getTeamA().getName()+ "  " + m.getTeamAScore() + " : "+ m.getTeamBScore()+ " "+ m.getTeamB().getName());
                
                System.out.println("\n");
                
            }
       }   
    } 
    
    public int day(int M, int D, int Y) {
        int y = Y - (14 - M) / 12;
        int x = y + y/4 - y/100 + y/400;
        int m = M + 12 * ((14-M) / 12) - 2;
        int d = (D + x + (31*m)/12) % 7;
        return d;
    }
    
    public boolean isLeapYear(int year) {
        
        if ((year % 4 ==0) && (year % 100 !=0 )) return true;
        if (year % 400 == 0) return true;
        return false;  
    }
    
    //Check If Match Existed Using The Id
    boolean CheckMatchExistance(int MatchId){
           
           
            for (Match m : matches) {
           
           
            if (m.getId2()==MatchId ) {
                
                
                return true;
                
            }
           
           
       }
            return false;
       }
    
      
      //Change Match Date
     void ChangeMatchDate(int MatchId) {
       System.out.println("Enter Yhe New date (format dd-MM-yyyy): ");
        String line = GetFixedString();
        
       
         
         while(!isThisDateCorrect(line,"dd-MM-yyyy")){
               System.out.println(ANSI_RED+"You have to enter date in format dd-MM-yyyy"+ANSI_RESET);
                System.out.println("Enter date (format dd-MM-yyyy): ");
                 line = GetFixedString();
              
          }
        Date date;
        try {
            date = new SimpleDateFormat("dd-MM-yyyy").parse(line);
        } catch (ParseException ex) {
            System.out.println(ANSI_RED +"You have to enter date in format dd-MM-yyyy"+ANSI_RESET);
            return;
        }
       for (Match m : matches) {
           if (m.getId2()==MatchId) {
               
               m.setDate(date);
          System.out.println(ANSI_GREEN +"Match Date Has been Changed Successfully ^-^"+ANSI_RESET); 
               
           }
           
         
       }
       }   
         
           // ChangeMatchStadium 
      void ChangeMatchStadium(int MatchId) {
        System.out.println("Enter The New Stadium Name : ");
        String NewStadium = GetFixedString();
      
       for (Match m : matches) {
           if (m.getId2()==MatchId) {
               
               m.setStadium(NewStadium);
      System.out.println(ANSI_GREEN +"Match Stadium Has been Changed Successfully ^-^"+ANSI_RESET);           
           }
           
           
           
         
       }
       }   
         
         
      
      
    //------------------------------------------------------------------------------------------   
       
         
         
         
         
         
    } 
    
    