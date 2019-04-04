package top.chorg.Kernel.Database;

import top.chorg.Kernel.Server.Base.Auth.User;
import top.chorg.Support.Date;
import top.chorg.Support.DateTime;
import top.chorg.System.Global;
import top.chorg.System.Sys;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserQueryState {

    public static User UpdateUserInfo(int id) {
        try {
            PreparedStatement state = Global.database.prepareStatement(
                    "SELECT id, username, classId, email, phone, birthday, sex, " +
                            "realName, nickName, grade, regTime FROM users WHERE id=?"
            );
            state.setInt(1, id);
            return assignUserInfo(state);
        } catch (SQLException e) {
            Sys.err("DB", "Error while validating user.");
            return null;
        }

    }

    public static User validateUser(String name, String password) {
        try {
            PreparedStatement state = Global.database.prepareStatement(
                    "SELECT id, username, classId, email, phone, birthday, sex, " +
                            "realName, nickname, grade, regTime FROM users WHERE username=? AND password=?"
            );
            state.setString(1, name);
            state.setString(2, password);
            System.out.println(state.toString());
            return assignUserInfo(state);
        } catch (SQLException e) {
            Sys.err("DB", "Error while validating user.");
            return null;
        }

    }

    private static User assignUserInfo(PreparedStatement state) throws SQLException {
        var res = state.executeQuery();
        if (!res.next()) return null;
        return new User(
                res.getInt("id"),
                res.getInt("classId"),
                res.getInt("sex"),
                res.getInt("grade"),
                res.getString("username"),
                res.getString("realName"),
                res.getString("nickName"),
                res.getString("email"),
                res.getString("phone"),
                res.getString("birthday") == null ? null : new Date(res.getString("birthday")),
                new DateTime(res.getString("regTime"))
        );
    }

}