import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.awt.Graphics;


import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.*;


public class MoreFrame {

	private JFrame frame;
    JTextArea textArea_top;
    JButton borrowButton;
    JButton returnButton;
    JButton reserveButton;
    JButton queueButton;
    JTextArea textArea_bottom;
    boolean isAvailable;
    Book bookData;
    Image image;
    JPanel bigPanel;

	/**
	 * Launch the application.
	 */
	public MoreFrame() {
        	
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 545);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
		
		textArea_top = new JTextArea();
		textArea_top.setRows(5);
		frame.add(textArea_top, BorderLayout.NORTH);
        textArea_top.setEditable(false);
		
        bigPanel = new JPanel();

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		borrowButton = new JButton("Borrow");
		subPanel.add(borrowButton);
		
		returnButton = new JButton("Return");
		subPanel.add(returnButton);
		
		reserveButton = new JButton("Reserve");
		subPanel.add(reserveButton);
		
		queueButton = new JButton("Waiting Queue");
		subPanel.add(queueButton);

        bigPanel.add(subPanel);
        
        frame.add(bigPanel, BorderLayout.CENTER);

		textArea_bottom = new JTextArea();
		frame.add(textArea_bottom, BorderLayout.SOUTH);
        textArea_bottom.setEditable(false);
	}

    public void operation(Book book, MyLinkedList<Book> linkedList, DefaultTableModel tableModel){
        bookData = book;
        String ISBN = book.getISBN();
        String title = book.getTitle();
        

        isAvailable = book.isAvailable();
        frame.setTitle(title);
        textArea_top.setText("ISBN:"+ISBN+"\nTitle:"+title+"\nAvailable:"+isAvailable);
        
        if (book.getTitle().equals("C++ How to Program")){
            ImageIcon icon = new ImageIcon("C++.jpg");
            image = icon.getImage();
        }
        else if (book.getTitle().equals("HTML How to Program")){
            ImageIcon icon = new ImageIcon("HTML.jpg");
            image = icon.getImage();
        }
        else if (book.getTitle().equals("Java How to Program")){
            ImageIcon icon = new ImageIcon("Java.jpg");
            image = icon.getImage();
        }
        class MyPanel extends JPanel {
        
            public Dimension getPreferredSize() {
                return new Dimension(325,325);
            }
        
            public void paintComponent(Graphics g) {
                super.paintComponent(g);       
        
                g.drawImage(image, 0, 0, 325, 325, null);
            }  
        }

        bigPanel.add(new MyPanel());

        if (isAvailable==true){
            returnButton.setEnabled(false);
            reserveButton.setEnabled(false);
            queueButton.setEnabled(false);
        }
        else{
            borrowButton.setEnabled(false);
        }

        borrowButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){  
                    borrowButton.setEnabled(false);
                    returnButton.setEnabled(true);
                    reserveButton.setEnabled(true);
                    queueButton.setEnabled(true);
                    textArea_bottom.setText("The book is borrowed.");
                    book.setAvailable(false);

                    isAvailable = book.isAvailable();
                    textArea_top.setText("ISBN:"+ISBN+"\nTitle:"+title+"\nAvailable:"+isAvailable);
                }
            }
        );

        reserveButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){  

                    String inputName = JOptionPane.showInputDialog("What's your name?");
                    if (inputName.equals("")){
                        JOptionPane.showMessageDialog(null, "Error: Empty input name", "Message", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        textArea_bottom.setText("The book is reserved by "+inputName+".");
                        book.getReservedQueue().enqueue(inputName);
                    }
                }
            }
        );

        queueButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    if(book.getReservedQueue().getList().isEmpty()){
                        JOptionPane.showMessageDialog(null, "Error: Empty waiting queue", "Message", JOptionPane.ERROR_MESSAGE);
                    }  
                    else{
                        String listString = "The waiting queue:\n";
                        for(int i=0;i<book.getReservedQueue().getSize();i++){
                            listString+=book.getReservedQueue().getList().get(i)+"\n";
                        }
                        textArea_bottom.setText(listString);
                    }
                    
                }
            }
        );

        returnButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){

                    String listString = "The book is returned.\n";
                    
                    if(book.getReservedQueue().getList().isEmpty()){
                        borrowButton.setEnabled(true);
                        returnButton.setEnabled(false);
                        reserveButton.setEnabled(false);
                        queueButton.setEnabled(false);
                        book.setAvailable(true);
                        isAvailable=true;
                        book.setAvailable(isAvailable);
                        textArea_top.setText("ISBN:"+ISBN+"\nTitle:"+title+"\nAvailable:"+isAvailable);
                    }
                    else{
                        listString+="The book is now borrowed by "+book.getReservedQueue().dequeue()+".";
                    }
                    textArea_bottom.setText(listString);
                }
            }
        );

        frame.addWindowListener(
            new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    tableModel.setRowCount(0);
                    for(int i=0;i<linkedList.size();i++){
						tableModel.addRow(new Object[]{linkedList.get(i).getISBN(), linkedList.get(i).getTitle(), linkedList.get(i).isAvailable()});
					}
                }
            }
        );
    }

}

