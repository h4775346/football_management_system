/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.managment.system;

import java.util.Date;


public class Match{
  
    private Team teamA;
    private Team teamB;
    private int teamAScore;
    private int teamBScore;
    private int Id2;
    private String Stadium;
    private Date date;
    static int Id;
    
    
    public Team getTeamA() {
        return teamA;
    }

    public String getStadium() {
        return Stadium;
    }

    public void setStadium(String Stadium) {
        this.Stadium = Stadium;
    }

    public int getId2() {
        return Id2;
    }

    public void setId2(int Id2) {
        this.Id2 = Id2;
    }

    public static int getId() {
        return Id;
    }

    public static void setId(int Id) {
        Match.Id = Id;
    }
    
    public Team getTeamB() {
        return teamB;
    }
    
    public int getTeamAScore(){
        return teamAScore;
    }
    
    public int getTeamBScore(){
        return teamBScore;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setTeamA(Team teamA) {
        this.teamA = teamA;
    }
    
    public void setTeamB(Team teamB) {
        this.teamB = teamB;
    } 
    
    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }
    
    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }
     public void setDate(Date date) {
        this.date = date;
    }
   
    
}
