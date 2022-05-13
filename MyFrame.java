
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;




public class MyFrame {

	private JFrame frame;
	private JTextField textField_ISBN;
	private JTextField textField_Title;
	private int row = -1;
	private String editISBN;
	private boolean isISBNAscending = true;
	private boolean isTitleAscending = true;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		MyFrame window = new MyFrame();
		window.frame.setVisible(true);	
	}

	/**
	 * Create the application.
	 */


	/**
	 * Initialize the contents of the frame.
	 */
	public MyFrame() {
        MyLinkedList<Book> linkedList = new MyLinkedList<>();
		frame = new JFrame();
        frame.setTitle("Library Admin System");
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
		textArea.setRows(5);
        Date date = new Date();
        textArea.setText("Student Name and ID: Lam Tsun Hay(19059208d)\nStudnet Name and ID: Foo Sharon(19070365d)\n"+date); 
		frame.add(textArea, BorderLayout.NORTH);
		
		Book[][]data=new Book[linkedList.size()][3];
		linkedList.toArray(data); 
        String column[] = {"ISBN", "Title", "Available"};
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(data, column);
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		frame.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 3));
		
		JLabel lblNewLabel = new JLabel("ISBN:");
		panel_1.add(lblNewLabel);
		
		textField_ISBN = new JTextField();
		panel_1.add(textField_ISBN);
		textField_ISBN.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Title:");
		panel_1.add(lblNewLabel_1);
		
		textField_Title = new JTextField();
		panel_1.add(textField_Title);
		textField_Title.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton addButton = new JButton("Add");
		panel_2.add(addButton);
		
		JButton editButton = new JButton("Edit");
		panel_2.add(editButton);
		
		JButton saveButton = new JButton("Save");
        saveButton.setEnabled(false);
		panel_2.add(saveButton);
		
		JButton delButton = new JButton("Delete");
		panel_2.add(delButton);
		
		JButton searchButton = new JButton("Search");
		panel_2.add(searchButton);

		JButton saveRecordButton = new JButton("Save record");
		panel_2.add(saveRecordButton);

		JButton getRecordButton = new JButton("Retrieve record");
		panel_2.add(getRecordButton);

		JButton moreButton = new JButton("More>>");
		panel_2.add(moreButton);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton loadDataButton = new JButton("Load Test Data");
		panel_3.add(loadDataButton);
		
		JButton displayAllButton = new JButton("Display All");
		panel_3.add(displayAllButton);
		
		JButton displayISBNButton = new JButton("Display All by ISBN");
		panel_3.add(displayISBNButton);
		
		JButton displayTitleButton = new JButton("Display All by Title");
		panel_3.add(displayTitleButton);
		
		JButton exitButton = new JButton("Exit");
		panel_3.add(exitButton);

        

        addButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    boolean isValid=true;
                    String ISBN = textField_ISBN.getText();
                    String Title = textField_Title.getText();
            
                    if (ISBN.equals("")||Title.equals("")){
                        JOptionPane.showMessageDialog(null, "Error: ISBN and Title cannot be empty.", "Message", JOptionPane.ERROR_MESSAGE);
                        isValid = false;
                    }
                    else{
                        for(int i=0;i<linkedList.size();i++){
                            if (linkedList.get(i).getISBN().equals(ISBN)){
                                JOptionPane.showMessageDialog(null, "Error: book ISBN exists in the current database.", "Message", JOptionPane.ERROR_MESSAGE);
                                isValid = false;
                                break;
                            }
                        }
                    }
                    if (isValid==true){
                        Book book = new Book();
                        book.setISBN(ISBN);
                        book.setTitle(Title);
                        book.setAvailable(true);
                        linkedList.add(book);
						textField_ISBN.setText("");
						textField_Title.setText("");
                        tableModel.addRow(new Object[]{book.getISBN(), book.getTitle(), book.isAvailable()});
                    }
                    
                }
            }
        );

		

        loadDataButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
 
					Book book1 = new Book();
					book1.setTitle("HTML How to Program");
					book1.setISBN("0131450913");
					book1.setAvailable(true);

					Book book2 = new Book();
					book2.setTitle("C++ How to Program");
					book2.setISBN("0131857576");
					book2.setAvailable(true);

					Book book3 = new Book();
					book3.setTitle("Java How to Program");
					book3.setISBN("0132222205");
					book3.setAvailable(true);
					
					boolean exist_book1=false;
					boolean exist_book2=false;
					boolean exist_book3=false;
					for(int i=0;i<linkedList.size();i++){
						if (linkedList.get(i).getISBN().equals(book1.getISBN())){
							exist_book1=true;
						}
						if (linkedList.get(i).getISBN().equals(book2.getISBN())){
							exist_book2=true;
						}
						if (linkedList.get(i).getISBN().equals(book3.getISBN())){
							exist_book3=true;
						}
					}
					if(exist_book1==false){
						linkedList.add(book1);
						tableModel.addRow(new Object[]{book1.getISBN(), book1.getTitle(), book1.isAvailable()});
					}
					else{
						JOptionPane.showMessageDialog(null, "Error: 'HTML How to Program' exist in the current database.", "Message", JOptionPane.ERROR_MESSAGE);
					}
					if(exist_book2==false){
						linkedList.add(book2);
						tableModel.addRow(new Object[]{book2.getISBN(), book2.getTitle(), book2.isAvailable()});
					}
					else{
						JOptionPane.showMessageDialog(null, "Error: 'C++ How to Program' exist in the current database.", "Message", JOptionPane.ERROR_MESSAGE);
					}
					if(exist_book3==false){
						linkedList.add(book3);
						tableModel.addRow(new Object[]{book3.getISBN(), book3.getTitle(), book3.isAvailable()});
					}
					else{
						JOptionPane.showMessageDialog(null, "Error: 'Java How to Program' exist in the current database.", "Message", JOptionPane.ERROR_MESSAGE);
					}

					textField_ISBN.setText("");
					textField_Title.setText("");
                }
            }
        );

		delButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					boolean isExist=false;
					String ISBN = textField_ISBN.getText();
					for(int i=0;i<linkedList.size();i++){
						if (linkedList.get(i).getISBN().equals(ISBN)){
							linkedList.remove(linkedList.get(i));
							tableModel.removeRow(i);
							isExist=true;
							textField_ISBN.setText("");
							textField_Title.setText("");
							break;
						}
					}
					if (isExist==false){
						JOptionPane.showMessageDialog(null, "Error: The ISBN does not exist in the current database or the database is empty.", "Message", JOptionPane.ERROR_MESSAGE);
					}
					

				}
			}
		);

		table.addMouseListener(
			new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					int row = table.rowAtPoint(evt.getPoint());
					textField_ISBN.setText(linkedList.get(row).getISBN());
					textField_Title.setText(linkedList.get(row).getTitle());
				}
			}
		);
		editButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					editISBN=textField_ISBN.getText();
					String title=textField_Title.getText();
	
					for(int i=0;i<linkedList.size();i++){
						if (linkedList.get(i).getISBN().equals(editISBN)){
							row = i;
							break;
						}
					}
			
					if (row==-1||linkedList.isEmpty()){
						JOptionPane.showMessageDialog(null, "Error: This ISBN is not existed in the current database or database is empty.", "Message", JOptionPane.ERROR_MESSAGE);
					}
					else{
						if (title.equals("")){
							textField_Title.setText(linkedList.get(row).getTitle());
						}
						addButton.setEnabled(false);
						editButton.setEnabled(false);
						saveButton.setEnabled(true);
						delButton.setEnabled(false);
						searchButton.setEnabled(false);
						moreButton.setEnabled(false);
						loadDataButton.setEnabled(false);
						displayAllButton.setEnabled(false);
						displayISBNButton.setEnabled(false);
						displayTitleButton.setEnabled(false);
						exitButton.setEnabled(false);
					}
				}
			}
		);
		saveButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					boolean isRepeat = false;
					String ISBN=textField_ISBN.getText();
					String title=textField_Title.getText();

					if (!editISBN.equals(ISBN)){
						for(int i=0;i<linkedList.size();i++){
							if (linkedList.get(i).getISBN().equals(ISBN)){
								JOptionPane.showMessageDialog(null, "Error: This ISBN is already existed in the current database.", "Message", JOptionPane.INFORMATION_MESSAGE);
								isRepeat = true;
							}
						}
					}
					if (isRepeat==false){
						linkedList.get(row).setISBN(ISBN);
						linkedList.get(row).setTitle(title);
						tableModel.setValueAt(ISBN, row, 0);
						tableModel.setValueAt(title, row, 1);

						addButton.setEnabled(true);
						editButton.setEnabled(true);
						saveButton.setEnabled(false);
						delButton.setEnabled(true);
						searchButton.setEnabled(true);
						moreButton.setEnabled(true);
						loadDataButton.setEnabled(true);
						displayAllButton.setEnabled(true);
						displayISBNButton.setEnabled(true);
						displayTitleButton.setEnabled(true);
						exitButton.setEnabled(true);

						textField_ISBN.setText("");
						textField_Title.setText("");
					}
				}
			}
		);

		searchButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String ISBN = textField_ISBN.getText();
					String title = textField_Title.getText();
					boolean isFound = false;

					for(int i=0;i<linkedList.size();i++){
						if (linkedList.get(i).getISBN().contains(ISBN)||linkedList.get(i).getISBN().contains(title)){
							isFound = true;		
							break;					
						}
					}
					if (isFound==true){
						tableModel.setRowCount(0);
						if(ISBN.equals("")){
							ISBN="NOT_FOUND";
						}
						if(title.equals("")){
							title="NOT_FOUND";
						}
						for(int i=0;i<linkedList.size();i++){
							
							if (linkedList.get(i).getISBN().contains(ISBN)||linkedList.get(i).getTitle().contains(title)){
								tableModel.addRow(new Object[]{linkedList.get(i).getISBN(), linkedList.get(i).getTitle(), linkedList.get(i).isAvailable()});						
							}
						}
						textField_ISBN.setText("");
						textField_Title.setText("");
					}
					else{
						JOptionPane.showMessageDialog(null, "Error: This ISBN is already existed in the current database.", "Message", JOptionPane.ERROR_MESSAGE);
					}	

				}
			}
		);

		displayAllButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					tableModel.setRowCount(0);

					for(int i=0;i<linkedList.size();i++){
						tableModel.addRow(new Object[]{linkedList.get(i).getISBN(), linkedList.get(i).getTitle(), linkedList.get(i).isAvailable()});
					}
				}
			}
		);
		
		displayISBNButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					MyLinkedList<Book> listTitle = new MyLinkedList<>();


					tableModel.setRowCount(0);
					if (isISBNAscending==true){
						for (int i=0;i<linkedList.size();i++){
							listTitle.add(linkedList.get(i));
							
						}
	
						for (int i=1;i<linkedList.size();i++){
							Book currentElement=linkedList.get(i);
							int k;
							for(k=i-1;k>=0&&Integer.parseInt(listTitle.get(k).getISBN())>Integer.parseInt(currentElement.getISBN());k--){
								listTitle.set(k+1,listTitle.get(k));
								
							}
							listTitle.set(k+1, currentElement);
						}
						for (int i=0;i<listTitle.size();i++){
							tableModel.addRow(new Object[]{listTitle.get(i).getISBN(), listTitle.get(i).getTitle(), listTitle.get(i).isAvailable()});
						}
						
						isISBNAscending=false;
					}
					else{
						for (int i=0;i<linkedList.size();i++){
							listTitle.add(linkedList.get(i));
							
						}
	
						for (int i=1;i<linkedList.size();i++){
							Book currentElement=linkedList.get(i);
							int k;
							for(k=i-1;k>=0&&Integer.parseInt(listTitle.get(k).getISBN())<Integer.parseInt(currentElement.getISBN());k--){
								listTitle.set(k+1,listTitle.get(k));
								
							}
							listTitle.set(k+1, currentElement);
						}
						for (int i=0;i<listTitle.size();i++){
							tableModel.addRow(new Object[]{listTitle.get(i).getISBN(), listTitle.get(i).getTitle(), listTitle.get(i).isAvailable()});
						}
						
						isISBNAscending=true;
					}
					
				}
			}
		);

		displayTitleButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					MyLinkedList<Book> listTitle = new MyLinkedList<>();


					tableModel.setRowCount(0);
					if (isTitleAscending==true){
						for (int i=0;i<linkedList.size();i++){
							listTitle.add(linkedList.get(i));
							
						}
	
						for (int i=1;i<linkedList.size();i++){
							Book currentElement=linkedList.get(i);
							int k;
							for(k=i-1;k>=0&&listTitle.get(k).getTitle().compareTo(currentElement.getTitle())>0;k--){
								listTitle.set(k+1,listTitle.get(k));
								
							}
							listTitle.set(k+1, currentElement);
						}
						for (int i=0;i<listTitle.size();i++){
							tableModel.addRow(new Object[]{listTitle.get(i).getISBN(), listTitle.get(i).getTitle(), listTitle.get(i).isAvailable()});
						}
						
						isTitleAscending=false;
					}
					else{
						for (int i=0;i<linkedList.size();i++){
							listTitle.add(linkedList.get(i));
							
						}
	
						for (int i=1;i<linkedList.size();i++){
							Book currentElement=linkedList.get(i);
							int k;
							for(k=i-1;k>=0&&listTitle.get(k).getTitle().compareTo(currentElement.getTitle())<0;k--){
								listTitle.set(k+1,listTitle.get(k));
								
							}
							listTitle.set(k+1, currentElement);
						}
						for (int i=0;i<listTitle.size();i++){
							tableModel.addRow(new Object[]{listTitle.get(i).getISBN(), listTitle.get(i).getTitle(), listTitle.get(i).isAvailable()});
						}
						
						isTitleAscending=true;
					}
					
				}
			}
		);
		
		moreButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e){
					String ISBN = textField_ISBN.getText();
					
					if (ISBN.equals("")){
						JOptionPane.showMessageDialog(null, "Error: Empty Book List", "Message", JOptionPane.ERROR_MESSAGE);
						return;
					}
					MoreFrame moreGUI = new MoreFrame();
					for(int i=0;i<linkedList.size();i++){
						if (linkedList.get(i).getISBN().equals(ISBN)){

							Book selected=linkedList.get(i);			
							moreGUI.operation(selected, linkedList, tableModel);
						}
					}
				}
			}
		);

		exitButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e){
					System.exit(0);
				}
			}
		);
		
		File myFile = new File("history.txt");
		saveRecordButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						FileWriter myWriter = new FileWriter(myFile);
						PrintWriter writer = new PrintWriter(myFile);
						writer.print("");
						writer.close();
						for(int i=0;i<linkedList.size();i++){
							myWriter.write(linkedList.get(i).getISBN()+"/"+linkedList.get(i).getTitle()+"/"+linkedList.get(i).isAvailable()+"\n");
						}
      					myWriter.close();
					} catch (IOException E) {
						System.out.println("An error occurred.");
						E.printStackTrace();
					}
				}
			}
		);
		
		getRecordButton.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					try {
						Scanner myReader = new Scanner(myFile);
						
						linkedList.clear();
						tableModel.setRowCount(0);
						while (myReader.hasNextLine()) {
							Book book = new Book();
							String[] line = myReader.nextLine().split("/");
							book.setISBN(line[0]);
							book.setTitle(line[1]);
							book.setAvailable(Boolean.parseBoolean(line[2]));
							linkedList.add(book);
						}

						for(int i=0;i<linkedList.size();i++){
							tableModel.addRow(new Object[]{linkedList.get(i).getISBN(), linkedList.get(i).getTitle(), linkedList.get(i).isAvailable()});
						}
						myReader.close();
					} catch (IOException E) {
						System.out.println("An error occurred.");
						E.printStackTrace();
					}
				}
			}
		);
	}

}

