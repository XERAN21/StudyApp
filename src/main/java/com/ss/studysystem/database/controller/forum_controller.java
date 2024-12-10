package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.Comments;
import com.ss.studysystem.Model.Forum_Comments;
import com.ss.studysystem.Model.Forums;
import com.ss.studysystem.Model.Users;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class forum_controller {
    public static boolean create_forum(Forums forum){

        String sql = "CALL create_forum(?,?,?,?,?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1,forum.getClassroom().getId());
            callableStatement.setString(2,forum.getTitle());
            callableStatement.setString(3,forum.getDescription());
            callableStatement.setInt(4,forum.getUser().getId());
            callableStatement.setTimestamp(5,Timestamp.valueOf(forum.getCreated_at()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean create_forum_comments(Forum_Comments forum_comments){
        String sql = "CALL create_forum_comments(?,?,?,?)";
        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)){

            callableStatement.setInt(1, forum_comments.getForum().getId());
            callableStatement.setInt(2,forum_comments.getUser().getId());
            callableStatement.setString(3,forum_comments.getComment_text());
            callableStatement.setString(4,forum_comments.getForum_file());

            int row_affected = callableStatement.executeUpdate();
            return  row_affected>0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    public static List<Forum_Comments> Get_forum_comments(int forum_id, int limit, int offset){
        String sql = "CALL Get_forum_comments(?,?,?)";
        List<Forum_Comments> FC_list = new ArrayList<>();

        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)){

            callableStatement.setInt(1,forum_id);
            callableStatement.setInt(2,limit);
            callableStatement.setInt(3,offset);
            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()){
                Forums forums = new Forums();
                forums.setId(resultSet.getInt(forum_id));

                Users users = new Users();
                users.setId(resultSet.getInt("user_id"));
                users.setUsername(resultSet.getString("username"));
                users.setProfile_img(resultSet.getBlob("profile_img"));

                Forum_Comments forum_comments = new Forum_Comments();
                forum_comments.setComment(resultSet.getInt("comment_id"));
                forum_comments.setForum(forums);
                forum_comments.setUser(users);

                FC_list.add(forum_comments);
            }
            return FC_list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public static boolean Edit_forum_comment(Forum_Comments forum_comments){
        String sql = "CALL Edit_forum_comment(?,?,?,?)";

        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1,forum_comments.getForum().getId());
            callableStatement.setString(2,forum_comments.getComment_text());
            callableStatement.setString(3,forum_comments.getForum_file());
            callableStatement.setTimestamp(4,Timestamp.valueOf(forum_comments.getCreated_at()));

            int row_affected = callableStatement.executeUpdate();
            return row_affected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean Delete_forum_comments(int forum_id){
        String sql = "CALL delete_forum_comments(?)";

        try(Connection con = DB_Connection.Get_Connection();
            CallableStatement callableStatement = con.prepareCall(sql)) {

            callableStatement.setInt(1,forum_id);

            int row_affected = callableStatement.executeUpdate();
            return  row_affected > 0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}











