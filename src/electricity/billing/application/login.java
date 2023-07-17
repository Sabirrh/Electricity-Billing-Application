
package electricity.billing.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
 


//Here I have Implemented ActionListener Which is used for the Listening the activity whenever we click on the button
public class login extends JFrame implements ActionListener {
    
//    Declaring Variavles globally
    JButton login,cancel,signup;
    Choice logginin;
    JTextField username, password;
    login(){
        
        
        super("Login Page");//Setting Login Page Name and Super must be the first Content inside cinstructor
        getContentPane().setBackground(Color.white);//getCOntentPane will give access to the full frame
        setLayout(null);//it makes default layout to the null for making own layout
        
        JLabel lblusername = new JLabel("Username");//JLabel used to show the content on the frame whatever we will write there
        lblusername.setBounds(300,20,100,20);//setBounds function works only when the Layout Function is null ,it will take four argument where two are for the height and width and remaining two values are length and width of its own value
        add(lblusername);
        
        username = new JTextField(); //JTextField help in making the input box for the form
        username.setBounds(400,20,150,20);
        add(username);
        
        JLabel lblpassword = new JLabel("Password");//JLabel used to show the content on the frame whatever we will write there
        lblpassword.setBounds(300,60,100,20);//setBounds function works only when the Layout Function is null ,it will take four argument where two are for the height and width and remaining two values are length and width of its own value
        add(lblpassword);
        
        password = new JTextField(); //JTextField help in making the input box for the form
        password.setBounds(400,60,150,20);
        add(password);
        
        
        JLabel logininas = new JLabel("Login in as");//JLabel used to show the content on the frame whatever we will write there
        logininas.setBounds(300,100,100,20);//setBounds function works only when the Layout Function is null ,it will take four argument where two are for the height and width and remaining two values are length and width of its own value
        add(logininas);
        
        logginin = new Choice(); //JTextField help in making the input box for the form
        logginin.add("Admin");
        logginin.add("Customer");
        logginin.setBounds(400,100,150,20);
        add(logginin);
        
        //Attaching Pic to the login button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        //Login Button
        login = new JButton("Login", new ImageIcon(i2));//JButton is Used To Create Button
        login.setBounds(330 ,160,100,20);
        login.addActionListener(this);//This will give Functionality of knowing that the button is clicked or not
        add(login);
        
        //Attaching Pic to The Cancel Button
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        //Cancel Button
        cancel = new JButton("Cancel",new ImageIcon(i4));
        cancel.setBounds(450,160,100,20);
        cancel.addActionListener(this);//Whenever the button is Clicked I will get to know the button is clicked 
        add(cancel);
        
        //Attaching Pic to The Registration Button
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
       //Registration Button
        signup = new JButton("Sign Up",new ImageIcon(i6));
        signup.setBounds(380,200,100,20);
        signup.addActionListener(this);//Whenever the button is Clicked I will get to know the button is clicked 
        add(signup);
        
        
//    Attaching Main Image for the Page i.e. Man Image
      ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
      Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
      ImageIcon i9 = new ImageIcon(i8);
      JLabel image = new JLabel(i9);
      image.setBounds(0 ,0 ,250 ,250);
      add(image);
      
        
        
        setSize(640,300);
        setLocation(400,200);
        setVisible(true);
           
    }
//    Overriding the Method
    
//    As we have Three button if any button will get clicked then we cannot find which button is clicked 
    //so to know that we have "ActionEvent" which will give the Source that which button is 
    public void actionPerformed(ActionEvent ae){
//      AND Whenever the button is get Clicked this Function will Perform the tasks and With the help of the addActionListener this function will get to know the Button is Clicked  
        if(ae.getSource() == login){
            String susername = username.getText();
            String spassword = password.getText();
            String user = logginin.getSelectedItem();
            
            try{
                Conn c = new Conn();
                String query = "Select * from login where username ='"+susername+"' and password = '"+spassword+"' and user ='"+user+"' ";
                ResultSet rs = c.s.executeQuery(query);
                //Creating condition for login
                if(rs.next()){
                    String meter = rs.getString("meter_no");
                    setVisible(false);
                    new Project(user,meter);
                }else{
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                    username.setText("");
                    password.setText("");
                }
                
                
            }catch(Exception e){
              e.printStackTrace();
            }
            
        }else if(ae.getSource() == cancel){
            setVisible(false);
        }else if(ae.getSource() == signup){
            setVisible(false);
            
            new SignUp();
        }


    }
    
    
    public static void main(String[] args){
        new login();
    }
    
}
