package com.ss.studysystem.database.controller;

import com.ss.studysystem.Model.*;
import com.ss.studysystem.database.connection.DB_Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class answer_controller {

    public static boolean createAnswer(Answers answers) {
        String sql = "CALL create_answer(?,?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {

            callableStatement.setInt(1, answers.getQuestion().getId());
            callableStatement.setString(2, answers.getAnswer_text());

            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;


        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

//    public static List<String> getCorrectAnswers(int questionId) {
//        String sql = "CALL get_correct_answers(?)";
//        List<String> correctAnswers = new ArrayList<>();
//        try (Connection connection = DB_Connection.Get_Connection();
//             CallableStatement callableStatement = connection.prepareCall(sql)) {
//
//            callableStatement.setInt(1, questionId);
//
//            ResultSet resultSet = callableStatement.executeQuery();
//
//            while (resultSet.next()) {
//                correctAnswers.add(resultSet.getString("answer_text"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//        return correctAnswers;
//
//    }

    public static List<Answers> getGNQA(int testId, int questionId, int answerId, Test_Questions test_questions) {
        String sql = "CALL Get_gnqa(?,?,?,?,?)";
        List<Answers> gnqaList = new ArrayList<>();
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, testId);
            callableStatement.setInt(2,questionId);
            callableStatement.setInt(3,answerId);
            callableStatement.setInt(4,test_questions.getTest().getTest());
            callableStatement.setInt(5,test_questions.getQuestion().getId());

            ResultSet resultSet = callableStatement.executeQuery();

            while (resultSet.next()) {
                Answers answer = new Answers();

                Questions questions = new Questions();
                questions.setQuestion_text(resultSet.getString("question_text"));
                questions.setQuestion_img(resultSet.getBlob("question_img"));

                answer.setQuestion(questions);

                Test test = new Test();
                test.setTest(testId);

                test_questions.setTest(test);
                test_questions.setQuestion(questions);

                answer.setAnswer_text(resultSet.getString("answer_text"));
                answer.setIs_correct(resultSet.getBoolean("is_correct"));
                gnqaList.add(answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gnqaList;
    }


    public static List<Student_Answers> getStudentAnswers(int responseId) {
        String sql = "CALL get_student_answers(?)";
        List<Student_Answers> studentAnswersList = new ArrayList<>();
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, responseId);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Answers answers = new Answers();
                answers.setAnswer(resultSet.getInt("answer_id"));

                Questions questions = new Questions();
                questions.setId(resultSet.getInt("question_id"));

                Test test = new Test();
                test.setTest(resultSet.getInt("answer_text"));


                Student_Answers studentAnswer = new Student_Answers();
                studentAnswer.setResponse(resultSet.getInt("response_id"));
                studentAnswer.setQuestion(questions);
                studentAnswer.setAnswer(answers);
                studentAnswer.setTest(test);
                studentAnswer.setIs_correct(resultSet.getBoolean("is correct"));
                studentAnswer.setAttempted_at(resultSet.getTimestamp("attempted_at").toLocalDateTime());
                studentAnswersList.add(studentAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentAnswersList;
    }

    public static boolean updateAnswer(int answerId, int questionId, String answerText, boolean isCorrect) {
        String sql = "CALL update_answers(?, ?, ?, ?)";
        try (Connection connection = DB_Connection.Get_Connection();
             CallableStatement callableStatement = connection.prepareCall(sql)) {
            callableStatement.setInt(1, answerId);
            callableStatement.setInt(2, questionId);
            callableStatement.setString(3, answerText);
            callableStatement.setBoolean(4, isCorrect);
            int rowsAffected = callableStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (
                SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.setTest(1);

        Questions questions = new Questions();
        questions.setId(1);

        Answers answers = new Answers();
        answers.setAnswer(4);
        answers.setQuestion(questions);

        Test_Questions testQuestions = new Test_Questions();
        testQuestions.setTest(test);
        testQuestions.setQuestion(questions);

        List<Answers> gnqaList = getGNQA(1,1,1,testQuestions);

        for (Answers answer : gnqaList) {
            System.out.println("Question: " + answer.getQuestion().getQuestion_text());
            System.out.println("Answer: " + answer.getAnswer_text());
            System.out.println("Is Correct: " + answer.getIs_correct());
            System.out.println("Question Image: " + answer.getQuestion().getQuestion_img());
            System.out.println();
        }
    }

}


