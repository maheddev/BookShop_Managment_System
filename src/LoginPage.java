import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class LoginPage extends JFrame {
    private final JTextField user;
    private final JPasswordField pass;

    Box b;
    public LoginPage(){
        super("Login Page");
        setLayout(new BorderLayout());
        b=Box.createVerticalBox();
        JLabel titlel = new JLabel("Techno Book Store!");
        titlel.setForeground(Color.RED);
        titlel.setHorizontalAlignment(JTextField.CENTER);
        titlel.setFont(new Font("Monospace",Font.BOLD,30));
        titlel.setBackground(Color.ORANGE);
        add(titlel,BorderLayout.NORTH);
        JPanel pl = new JPanel();
        Box v1 = Box.createHorizontalBox();
        JLabel n = new JLabel("Enter Username");
        n.setForeground(Color.blue);
        n.setFont(new Font("Arial",Font.BOLD,20));
        n.setBorder(new EmptyBorder(0,-1,0,40));
        v1.add(n);
        user= new JTextField(20);
        v1.add(user);
        b.add(v1);
        Box v = Box.createHorizontalBox();
        JLabel p =new JLabel("Enter Password");
        p.setBorder(new EmptyBorder(0,-1,0,42));
        p.setForeground(Color.BLUE);
        p.setFont(new Font("Arial",Font.BOLD,20));
        v.add(p);
        pass= new JPasswordField(20);
        v.add(pass);
        JLabel statusBar = new JLabel();
        statusBar.setForeground(Color.RED);
        statusBar.setHorizontalAlignment(JTextField.CENTER);
        b.add(v);
        b.add(statusBar);
        JButton login = new JButton("Login");
        pl.add(b);
        add(pl,BorderLayout.CENTER);
        add(login,BorderLayout.SOUTH);
        login.addActionListener(
                e -> {

                    boolean check = true;
                    String password = pass.getText();

                    try{
                        Scanner file = new Scanner (Paths.get("password.txt"));
                        while(file.hasNextLine()){
                            String entry = file.nextLine();
                            String[] array = entry.split(",");

                            if(array[0].equalsIgnoreCase(user.getText()) && array[1].equals(password)){
                                MainFrame mainFrame = new MainFrame();
                                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                mainFrame.setSize(500,500);
                                mainFrame.setVisible(true);
                                dispose();
                                check = false;
                                break;
                            }
                        }
                        if(check){
                            statusBar.setText("Admin Not Found");
                        }
                    }
                    catch(IOException io){
                        statusBar.setText("File Not Found");
                    }
                }
        );
    }
}
