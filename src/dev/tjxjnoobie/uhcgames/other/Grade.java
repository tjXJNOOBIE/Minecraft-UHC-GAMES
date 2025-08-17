package dev.tjxjnoobie.uhcgames.other;

import dev.tjxjnoobie.uhcgames.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by TJ on 7/1/2017.
 */
public class Grade {

    public static boolean playerDoesntExists(String uuid) {
        try {

            ResultSet rs = MySQL.getResult("SELECT * FROM rating WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") == null;
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean playerExists(String uuid) {
        try {

            ResultSet rs = MySQL.getResult("SELECT * FROM rating WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int getGradePoints(String usernameORuuid, String colunm) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = MySQL.getSecondResult("SELECT * FROM rating WHERE "+colunm+"='" + usernameORuuid + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("ELO")) != null)) {
            }
            i = (rs.getInt("ELO"));
            while (rs.next()) {
                i = rs.getInt("ELO");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }

    public static void userExists() {

    }

    public static int getPlacementMatches(String usernameORuuid, String colunm) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = MySQL.getSecondResult("SELECT * FROM rating WHERE "+colunm+"='" + usernameORuuid + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("PLACEMENTS")) != null)) {
            }
            i = (rs.getInt("PLACEMENTS"));
            while (rs.next()) {
                i = rs.getInt("PLACEMENTS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }

    public static int getTillNextRank(String usernameORuuid, String colunm) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = MySQL.getSecondResult("SELECT * FROM rating WHERE "+colunm+"='" + usernameORuuid + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("TILLNEXTRANK")) != null)) {
            }
            i = (rs.getInt("TILLNEXTRANK"));
            while (rs.next()) {
                i = rs.getInt("TILLNEXTRANK");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }
    public static int getPoints(String usernameORuuid, String colunm) {
        Integer i = Integer.valueOf(0);
        try {

            ResultSet rs = MySQL.getSecondResult("SELECT * FROM rating WHERE "+colunm+"='" + usernameORuuid + "'");
            if ((rs.next()) && (Integer.valueOf(rs.getInt("POINTS")) != null)) {
            }
            i = (rs.getInt("POINTS"));
            while (rs.next()) {
                i = rs.getInt("POINTS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return i;
    }

    public static void createPlayer(String username, String uuid) {
        Player p = Bukkit.getPlayer(username);
        String playeruuid = p.getUniqueId().toString();
        if (playerDoesntExists(playeruuid) == true) {
            MySQL.update("INSERT INTO rating (UUID, USERNAME) VALUES ('" + uuid + "','" + username + "');");
        } else {
            if (playerExists(playeruuid) == true) {
                return;
            }
        }
    }

    public static void setGrade(String uuid, String grade) {
        MySQL.updateSG("UPDATE rating SET GRADE='" + grade + "' WHERE UUID='" + uuid + "'");

    }
    public static void setPlacements(String uuid, int placement) {
        MySQL.updateSG("UPDATE rating SET PLACEMENTS='" + placement + "' WHERE UUID='" + uuid + "'");

    }
    public static void setPoints(String uuid, int points) {
        MySQL.updateSG("UPDATE rating SET PLACEMENTS='" + points + "' WHERE UUID='" + uuid + "'");

    }
    public static void setTillNextRank(String uuid, int tillnext) {
        MySQL.updateSG("UPDATE rating SET TILLNEXTRANK='" + tillnext + "' WHERE UUID='" + uuid + "'");

    }



    public static String getGrade(String usernameORuuid, String colunm){

        String grade = "";
        if(getGradePoints(usernameORuuid,colunm) >= 100){
            grade = "§4§oF-";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 200){
            grade = "§4§oF";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 300){
            grade = "§4§oF+";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 400){
            grade = "§c§oD-";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 500){
            grade = "§c§oD";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 600){
            grade = "§c§oD+";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 700){
            grade = "§c§oC-";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 800){
            grade = "§c§oC";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 900){
            grade = "§c§oC+";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 1000){
            grade = "§e§oB-";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 1100){
            grade = "§e§oB";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 1200){
            grade = "§e§oB+";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 1300){
            grade = "§a§oA-";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 1400){
            grade = "§a§oA";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 1500){
            grade = "§a§oA+";
        }
        if(getGradePoints(usernameORuuid,colunm) >= 1600){
            grade = "§a§o§lE";
        }
        return grade;
    }

    public static String getTillNextBar(String usernameORuuid, String colunm){

        String till = "";

        if(getTillNextRank(usernameORuuid,colunm) >= 100){
            return till = "§8[§4::::::::::::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 95){
            return till = "§8[§a:::§4:::::::::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 90){
            return till = "§8[§a::::§4::::::::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 85){
            return till = "§8[§a:::::§4:::::::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 80){
            return till = "§8[§a::::::§4::::::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 75){
            return  till = "§8[§a:::::::§4:::::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 60){
            return  till = "§8[§a::::::::§4::::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 55){
            return  till = "§8[§a:::::::::§4:::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 50){
            return  till = "§8[§a::::::::::§4::::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 45){
            return   till = "§8[§a:::::::::::§4:::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 40){
            return  till = "§8[§a::::::::::::§4::::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 35){
            return   till = "§8[§a:::::::::::::§4:::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 30){
            return  till = "§8[§a::::::::::::::§4::::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 25){
            return  till = "§8[§a:::::::::::::::§4:::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 20){
            return till = "§8[§a::::::::::::::::§4::::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 15){
            return  till = "§8[§a:::::::::::::::::§4:::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 10){
            return  till = "§8[§a::::::::::::::::::§4::§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) >= 5){
            return  till = "§8[§a:::::::::::::::::::§4:§8]";
        }
        if(getTillNextRank(usernameORuuid,colunm) == 1){
            return till = "§8[§a::::::::::::::::::::§8]";
        }

        return "§4Error Getting Bar";
    }

    public void runQuit(Player p){

    }
}