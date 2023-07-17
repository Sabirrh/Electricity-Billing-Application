package electricity.billing.application;

import java.sql.*;


//This Class is for connecting the JDBC for DataBase Purpose
public class Conn {
    
    Connection c; //This Connection c Comes From Imported Sql Libraries
    Statement s;//Creating Statement for the connection part
    
//    Constructor
    Conn(){
//        There Are Five Steps to make connection with JDBC 

//Step-1: Making for Registraction of the Driver Class
//        Class.forNamo("com.mysql.cj.jdbc.Driver");//Class is name itself And "forNamo" is a function which help in connecting with the Mysql
        
//Step-2:Creating the Connectiom with mysql
try{
c =DriverManager.getConnection("jdbc:mysql:///ebs","root","Sabir@12");
//Step-3:Created Statement
s = c.createStatement(); //Storing All connection inside s
}catch(Exception e){
    e.printStackTrace();
} 


    }
    
    
}
