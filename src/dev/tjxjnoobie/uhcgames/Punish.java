package dev.tjxjnoobie.uhcgames;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by TJ on 1/5/2018.
 */
public class Punish {

    public static boolean isMuted(String uuid) {
        Integer i = Integer.valueOf(0);
        try {
            Statement error = info.techwizmatt.ServerCore.Main.sqlConnection.createStatement();
            ResultSet rs = error.executeQuery("SELECT * FROM rating WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                if (rs.getString("UUID").equals("muted")) {
                    return true;
                }else {
                    return false;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



}







