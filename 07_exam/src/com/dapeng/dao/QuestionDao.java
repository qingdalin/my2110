package com.dapeng.dao;

import com.dapeng.entity.Question;
import com.dapeng.entity.Users;
import com.dapeng.util.DBUtil;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {
    private DBUtil util = new DBUtil();
    //试题添加方法
    public int add(Question question,HttpServletRequest request){
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            //获取连接
            conn = util.getConnection(request);
            String sql = "insert into question(title,optionA,optionB,optionC,optionD,answer) values(?,?,?,?,?,?)";
            //获取预编译的数据库对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            //执行sql语句
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            util.close(request,conn,ps,null);
        }
        return result;
    }
    //试题查询的方法
    public List questionFindAll(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明一个集合，放查询到的用户信息
        List<Question> questionList = new ArrayList<>();
        try {
            //获取连接
            conn = util.getConnection();
            String sql = "select * from question";
            //获取预编译对象
            ps = conn.prepareStatement(sql);
            //执行sql语句
            rs = ps.executeQuery();
            //查询结果集
            while (rs.next()){
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                //将数据封装为user对象
                Question question = new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(conn,ps,rs);
        }
        return questionList;
    }
    //试题删除的方法
    public int deleteQuestion(String questionId){
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            //获取连接
            conn = util.getConnection();
            String sql = "delete from question where questionId = ?";
            //获取预编译的对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setInt(1,Integer.valueOf(questionId));
            //执行sql语句
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            util.close(conn,ps,null);
        }
        return result;
    }
    //根据编号查询试题信息
    public Question questionFindById(String questionId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Question question = null;
        try {
            //获取连接
            conn = util.getConnection();
            String sql = "select * from question where questionId = ?";
            //获取预编译对象
            ps = conn.prepareStatement(sql);
            //占位符赋值
            ps.setInt(1,Integer.valueOf(questionId));
            //执行sql语句
            rs = ps.executeQuery();
            //查询结果集
            while (rs.next()){
                Integer questionId1 = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                //将数据封装为 question对象
                question = new Question(questionId1,title,optionA,optionB,optionC,optionD,answer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(conn,ps,rs);
        }
        return question;
    }
    //更新试题方法
    public int questionUpdate(Question question,HttpServletRequest request){
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            //获取连接
            conn = util.getConnection(request);
            String sql = "update question set title = ?,optionA = ?,optionB = ?,optionC = ?,optionD = ?,answer = ? where questionId = ?";
            //获取预编译的数据库对象
            ps = conn.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,question.getTitle());
            ps.setString(2,question.getOptionA());
            ps.setString(3,question.getOptionB());
            ps.setString(4,question.getOptionC());
            ps.setString(5,question.getOptionD());
            ps.setString(6,question.getAnswer());
            ps.setInt(7,question.getQuestionId());
            //执行sql语句
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            util.close(request,conn,ps,null);
        }
        return result;
    }
    //随机生成四道题目
    public List findRand(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明一个集合，放查询到的用户信息
        List<Question> questionList = new ArrayList<>();
        try {
            //获取连接
            conn = util.getConnection();
            String sql = "select * from question order by rand() limit 0,4";
            //获取预编译对象
            ps = conn.prepareStatement(sql);
            //执行sql语句
            rs = ps.executeQuery();
            //查询结果集
            while (rs.next()){
                Integer questionId = rs.getInt("questionId");
                String title = rs.getString("title");
                String optionA = rs.getString("optionA");
                String optionB = rs.getString("optionB");
                String optionC = rs.getString("optionC");
                String optionD = rs.getString("optionD");
                String answer = rs.getString("answer");
                //将数据封装为user对象
                Question question = new Question(questionId,title,optionA,optionB,optionC,optionD,answer);
                questionList.add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            util.close(conn,ps,rs);
        }
        return questionList;
    }
}
