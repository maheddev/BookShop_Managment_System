import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    ArrayList<Integer> idCheck = new ArrayList<>();
    ArrayList<Book> book = new ArrayList<>();
    File f = new File("SavedData.txt");
    private final JTextField ID;
    private final JTextField TITLE;
    private final JTextField STOCK;
    private final JTextField PRICE;
    private final JComboBox<String> CATEGORY;
    Box b,h,h2;
    JButton Add;
    JButton update;
    JButton Search;
    JButton Sale;
    JButton Printer;
    JButton Exit;

    File file = new File("D:\\lab terminal\\data.txt");
    ObjectOutputStream o = null;
    ObjectInputStream i = null;
    public MainFrame(){
        super("Techno Books");
        setLayout(new FlowLayout());
        b = Box.createVerticalBox();
        h = Box.createHorizontalBox();
        h2 = Box.createHorizontalBox();
        b.add(new JLabel("Enter BookId"));
        ID= new JTextField(20);
        b.add(ID);
        b.add(new JLabel("Enter BookTitle"));
        TITLE= new JTextField(20);
        b.add(TITLE);
        b.add(new JLabel("Enter Stock"));
        STOCK= new JTextField(20);
        b.add(STOCK);
        b.add(new JLabel("Enter Price"));
        PRICE= new JTextField(20);
        b.add(PRICE);

        b.add(new JLabel("Choose Category"));
        String[] bookCat = {"Action and Adventure", "Classics","Comic Book","Detective and Mystery","Fantasy","Historical Fiction","Horror","Literary Fiction"};
        CATEGORY= new JComboBox<>(bookCat);
        CATEGORY.setMaximumRowCount(4);
        b.add(CATEGORY);

        Add = new JButton("Add");
        Add.setBorder(new EmptyBorder(12,12,12,12));
        h.add(Add);
        /*update = new JButton("Update");
        update.setBorder(new EmptyBorder(12,12,12,12));
        h.add(update);*/
        Search = new JButton("Search");
        Search.setBorder(new EmptyBorder(12,12,12,12));
        h.add(Search);
        Sale = new JButton("Sale");
        Sale.setBorder(new EmptyBorder(12,12,12,12));
        h.add(Sale);
        Printer = new JButton("Display Data");
        Printer.setBorder(new EmptyBorder(12,12,12,12));
        h.add(Printer);
        Exit = new JButton("Exit");
        Exit.setBorder(new EmptyBorder(12,12,12,12));
        h.add(Exit);
        h.setBorder(new EmptyBorder(20,20,20,20));
        h2.add(b);
        add(h2);
        add(h);

        InnerClass handeler = new InnerClass();
        Add.addActionListener(handeler);
        Printer.addActionListener(handeler);
        Search.addActionListener(handeler);
        Sale.addActionListener(handeler);
        Exit.addActionListener(handeler);

    }

    private class InnerClass implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
                if(file.isFile()){
                    i = new ObjectInputStream(new FileInputStream(file));
                    book = (ArrayList<Book>) i.readObject();
                    idCheck = (ArrayList<Integer>) i.readObject();
                    i.close();
                }
            }
            catch (Exception j){
            }

            if(e.getSource() == Add){
                try{
                    String Title = TITLE.getText();
                    int BookID = Integer.parseInt(ID.getText());
                    String Category = CATEGORY.getSelectedItem().toString();
                    int BookPrice = Integer.parseInt(PRICE.getText());
                    int BookStock = Integer.parseInt(STOCK.getText());

                    if(!idCheck.contains(BookID)){
                        Book b = new Book(Title,BookID,Category,BookPrice,BookStock);
                        book.add(b);
                        idCheck.add(BookID);
                        o = new ObjectOutputStream(new FileOutputStream(file));
                        o.writeObject(book);
                        o.writeObject(idCheck);
                        o.close();
                        JOptionPane.showMessageDialog(null,"Book Added");
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Book Already Exists");
                    }
                }catch(IOException k)
                {
                    JOptionPane.showMessageDialog(Add,"File didn't close correctly");
                }catch(Exception exception){
                    JOptionPane.showMessageDialog(Add,"Please Enter The Details In Correct Format");
                }
            }
            else if(e.getSource() == Search){
                boolean check = true;
                String str = JOptionPane.showInputDialog(null,"Enter BookId");
                int temp = Integer.parseInt(str);
                for(Book b:book){
                    if(temp == b.getBookID()){
                        JOptionPane.showMessageDialog(null,b.toString());
                        check = false;
                    }
                }
                if(check){
                    JOptionPane.showMessageDialog(null,"Record not Found");
                }
            }
            else if(e.getSource() == Sale){
                dispose();
                SaleBook s = new SaleBook();
                s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                s.setSize(500,500);
                s.setVisible(true);
            }
            else if(e.getSource() == Printer){
                try {
                    FileWriter fw=new FileWriter(f);
                    for(Book b : book){
                            fw.write(String.valueOf(b));
                            fw.write("\n\n");
                    }
                    fw.close();
                } catch (IOException ef) {
                    System.out.println("File already exsists");
                }
                try {
                    FileReader fr=new FileReader(f);
                    Desktop.getDesktop().open(f);
                } catch (Exception eg) {
                    System.out.println("File doesnot exsists");
                }
            }
            else if(e.getSource() == Exit){
                dispose();
                LoginPage l = new LoginPage();
                l.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                l.setSize(500,500);
                l.setVisible(true);
            }
        }
    }
}
