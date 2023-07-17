package electricity.billing.application;
import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    
    Thread t;
    Splash(){
         
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg")); //Selecting Image
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);//Scaling Image as Requirement
        ImageIcon i3 = new ImageIcon(i2); //Converting Image to ImageIcon For making Acceptable in Jframe
        JLabel image = new JLabel(i3);// Help in Adding image for display
        add(image);
        
        setVisible(true);
        
        int x=1;
        for(int i=2;i<600;i+=4,x+=1){
        setSize(i+x, i);
        setLocation(700 - ((i+x)/2), 400 -(i/2));
        }
        try{
            Thread.sleep(5);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        t = new Thread(this);
        t.start();
        
        
        setVisible(true);
        
    }
    public void run() {
    try{
        Thread.sleep(7000);
        setVisible(false);
        
        //login Frame
        new login();
    }catch(Exception e){
        e.printStackTrace();
    }
}
    
    public static void main(String[] args){
        
       new Splash();   
        
    
    }
    
    
}