
package electricity.billing.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame implements ActionListener{
    
    
    JButton create,back;
    Choice accountType;
    JTextField meter,username,name ,password;
    SignUp(){
        
        setBounds(450,150, 700, 400); //Instead of Using SetLocation And setSize We Can use this founction which will work as Same
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel = new JPanel();  //Making Pannel in the Frame
        panel.setBounds(30,30,650,300);
        panel.setBorder(new TitledBorder(new LineBorder(new Color(173,216,230),2),"Create Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(173,216,230)));//Creating Border In the the Frame where we have Created pannel and Create account keeping at the top of the panel
        panel.setLayout(null);//Panel is Also like Frame So Making null To make our Style
        panel.setBackground(Color.WHITE);//Setting Panel BackGround To White
        panel.setForeground(new Color(34,139,34));//SetForeground give Functionality to Change Text Color
        add(panel);
        
//        Creating Label

  JLabel heading = new JLabel("Create Account As");
  heading.setBounds(100,50,140,20);//Creating To Set on the Frame
  heading.setForeground(Color.GRAY);//Making Heading Color GRAY
  heading.setFont(new Font("Tahoma", Font.BOLD, 14));//Adjusting Font Size With the Help of setFont where "14" is SIze of the Font in Bold
  panel.add(heading);//As Adding on the Panel That's Why taking "panel.add"
        
        
//  Making Choice of the Account For Customer and Admin
          
    accountType = new Choice();
    accountType.add("Admin");
    accountType.add("Customer");
    accountType.setBounds(260,50,150,20);//This is For where to place "setBounds" is used for keeping the content in the frame
    panel.add(accountType);
    
//  Making Lebel as Meter Number  "JLabel" USed for making Frame on the Frame
  JLabel lblmeter = new JLabel("Meter Number");
  lblmeter.setBounds(100,90,140,20);//Creating To Set on the Frame
  lblmeter.setForeground(Color.GRAY);//Making Heading Color GRAY
  lblmeter.setFont(new Font("Tahoma",Font.BOLD, 14));//Adjusting Font Size With the Help of setFont where "14" is SIze of the Font in Bold
  lblmeter.setVisible(false);
  panel.add(lblmeter);


//   Creating TextField for the MeterNumber
     meter = new JTextField();  
     meter.setBounds(260,90,150,20);
     meter.setVisible(false);
     panel.add(meter);
  
//for Username

 JLabel lbluaername = new JLabel("Username");
  lbluaername.setBounds(100,130,140,20);//Creating To Set on the Frame
  lbluaername.setForeground(Color.GRAY);//Making Heading Color GRAY
  lbluaername.setFont(new Font("Tahoma",Font.BOLD, 14));//Adjusting Font Size With the Help of setFont where "14" is SIze of the Font in Bold
  panel.add(lbluaername);
  
  //   Creating TextField for the Username
     username = new JTextField();  
     username.setBounds(260,130,150,20);
     panel.add(username);
     
  JLabel lblname = new JLabel("Name");
  lblname.setBounds(100,170,140,20);//Creating To Set on the Frame
  lblname.setForeground(Color.GRAY);//Making Heading Color GRAY
  lblname.setFont(new Font("Tahoma",Font.BOLD, 14));//Adjusting Font Size With the Help of setFont where "14" is SIze of the Font in Bold
  panel.add(lblname);
  
  //   Creating TextField for the Username
     name = new JTextField();  
     name.setBounds(260,170,150,20);
     panel.add(name);
     
//     Adding Focus Listener When the Account is created it is handled by all these

  meter.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {}
            
            @Override
            public void focusLost(FocusEvent fe) {
                try {
                    Conn c  = new Conn();
                    ResultSet rs = c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                    while(rs.next()) {
                        name.setText(rs.getString("name"));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
     
     
     JLabel lblpassword = new JLabel("Password");
  lblpassword.setBounds(100,210,140,20);//Creating To Set on the Frame
  lblpassword.setForeground(Color.GRAY);//Making Heading Color GRAY
  lblpassword.setFont(new Font("Tahoma",Font.BOLD, 14));//Adjusting Font Size With the Help of setFont where "14" is SIze of the Font in Bold
  panel.add(lblpassword);
  
  //   Creating TextField for the Username
     password = new JTextField();  
     password.setBounds(260,210,150,20);
     panel.add(password);
     
     
     // Here We are making itemListener
      
        accountType.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ae) {
                String user = accountType.getSelectedItem();
                if (user.equals("Customer")) {
                    lblmeter.setVisible(true);
                    meter.setVisible(true);
                    name.setEditable(false);
                } else {
                    lblmeter.setVisible(false);
                    meter.setVisible(false);
                    name.setEditable(true);
                }
            }
        });
        
     
     
     //Creating Button for Creating The Account
     create = new JButton("Create");
     create.setBackground(Color.BLACK);
     create.setForeground(Color.WHITE);
     create.setBounds(140,260,120,25);
     create.addActionListener(this);
     panel.add(create);
     
      //Creating Button for Login The Account
     back = new JButton("Back");
     back.setBackground(Color.BLACK);
     back.setForeground(Color.WHITE);
     back.setBounds(300,260,120,25);
     back.addActionListener(this);
     panel.add(back);//Adding on the panel
     
     //Setting image on the Panel 
     ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
     Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
     ImageIcon i3 = new ImageIcon(i2);
     JLabel image = new JLabel(i3);
     image.setBounds(415,30,250,250);
     panel.add(image);
     
     setVisible(true); 
        
    }
    
    
    
     public void actionPerformed(ActionEvent e){
         
         if(e.getSource() == create){
             String atype =accountType.getSelectedItem();//Getting AccountType with the help of this
             String susername =username.getText();//Getting Username
             String sname = name.getText();//Getting Text with the help of this
             String spassword = password.getText();//For Password
             String smeter = meter.getText();//Getting Meter number
             
//             Now We Got all the Values and now heating to mysql
              try{
//                  Whenever we feel that we need to connect with mysql database then we will make connection which i have created below
                  Conn c = new Conn();
                  //Creating Query
                  String query = null;
                  if(atype.equals("Admin")){
                        query ="insert into login values('"+smeter+"','"+susername+"','"+sname+"','"+spassword+"','"+atype+"')";
                  }else{
                      query = "update login set username = '"+susername+"', password = '"+spassword+"', user = '"+atype+"' where meter_no = '"+smeter+"'";
                  }
                  //Updaring these values to the mysql where c is connection , s is Statement and updation to the table
                  c.s.executeUpdate(query);
                  
//              This is used for Showing the Popup
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                  
                setVisible(false);
                new login();
                  
              } catch( Exception ae){
                  ae.printStackTrace();
              }
             
         }else if(e.getSource() == back){
             setVisible(false);
             
             new login();
         }

}
    
    
    public static void main(String[] args){
        
        new SignUp();
    }
    
}
