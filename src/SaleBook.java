import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class SaleBook extends JFrame {
    private final JTextField t1;
    private final JTextField t2;
    JButton but;
    JButton back;
    Box b;

    File file = new File("D:\\lab terminal\\data.txt");
    ObjectOutputStream o = null;
    ObjectInputStream i = null;
    ArrayList<Book> book = new ArrayList<>();

    public SaleBook(){
        super("Sale");
        setLayout(new FlowLayout());
        b=Box.createVerticalBox();
        b.add(new JLabel("Book ID"));
        t1 = new JTextField(10);
        b.add(t1);

        b.add(new JLabel("Book Stock"));
        t2 = new JTextField(10);
        b.add(t2);

        but = new JButton("Sale");
        b.add(but);

        back = new JButton("Back");
        b.add(back);
        add(b);

        but.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try{
                        if(file.isFile()){
                            i = new ObjectInputStream(new FileInputStream(file));
                            book = (ArrayList<Book>) i.readObject();
                            i.close();
                        }
                    }
                    catch (Exception j){
                    }
                    if(Objects.equals(t1.getText(), "") || Objects.equals(t2.getText(), "")){
                        JOptionPane.showMessageDialog(null,"ID and Stock Required");
                    }
                    else if(!Objects.equals(t1.getText(), "") || !Objects.equals(t2.getText(), "")){
                        int temp = Integer.parseInt(t1.getText());
                        int stocktemp = Integer.parseInt(t2.getText());
                        boolean ser = true;
                        for(Book b:book){
                            if(temp == b.getBookID()){
                                ser = false;
                                if(stocktemp <= b.getBookStock()){
                                    int revenue = stocktemp*b.getBookPrice();
                                    b.setBookStock(b.getBookStock()-stocktemp);
                                    String str = String.format("Sale Successful"+"\n"+"Your Bill is: Rs. "+revenue+" /-"+"\n"+"Remaining Strock: "+b.getBookStock());
                                    JOptionPane.showMessageDialog(null,str);
                                    try{
                                        o = new ObjectOutputStream(new FileOutputStream(file));
                                        o.writeObject(book);
                                        o.close();
                                    }catch (Exception t){

                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null,"Insufficient Stock. Availale: "+b.getBookStock());
                                }
                            }
                        }
                        if(ser){
                            JOptionPane.showMessageDialog(null,"Book Not Found");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Book not Found");
                    }
                }
            }
        );
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                        MainFrame m = new MainFrame();
                        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        m.setSize(500,500);
                        m.setVisible(true);
                    }
                }
        );
    }
}
