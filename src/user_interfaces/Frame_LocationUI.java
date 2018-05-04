package user_interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
/**
 * UI Frame for the Location Table that the user uses 
 * to interact with the DBMS
 * @author adam Standke
 *
 */
public class Frame_LocationUI extends Application implements Frame, ActionListener
{
	//data model used to link resultset columns used in constructing jtable
	@SuppressWarnings("serial")
	private DefaultTableModel tablemodel = new DefaultTableModel() {
		//annoymous class that overides method that now allows tabel cells to not be editable
		@Override	
		public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
				
			}
	};
	private JTable table; 
	private JScrollPane scrollPane;
	private JPanel resultsPanel; 
	private JFrame frame;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnView;
	private JTextField lidTextField;
	private JTextField exitTextField;
	private JTextField sizeTextField;
	private JTextField typeTextField;
	private JTextField directTextField; 
	private JTextField lidUpdateTextField;
	private JTextField exitUpdateTextField;
	private JTextField sizeUpdateTextField;
	private JTextField typeUpdateTextField;
	private JTextField directUpdateTextField;
	private JTextField whereUpdateTextField;
	private JTextField whereDeleteTextField;
	private JTextField selectViewTextField;
	private int width, height;

	/**
	 * Creates the application.
	 * @throws SQLException 
	 */
	public Frame_LocationUI() 
	{
		setFrameSize();
		buildFrame();
		frame.setVisible(true);
		frame.pack();
		
	}

	/**
	 * set frame size to a 
	 * certain height and width
	 */
	@Override
	public void setFrameSize()
	{
		this.width = getFrameWidth();
		this.height = getFrameHeight();
		
	}
	
	/**
	 * Method that builds the actual user 
	 * interface for the Location table that interacts
	 * with the DBMS
	 */
	@Override
	public void buildFrame() 
	{
		//main graphical container that will contain various JComponents
		frame = new JFrame();
		frame.setBounds(0, 0, width, height);//sets size of frame
		frame.setTitle("Location Interface");//sets title of frame
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);//closes frame when pressed
		frame.getContentPane().setLayout(new BorderLayout());//gets the content pane and adds a layout manager to it
		//main panel that holds all of the other panels
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, width, height);
		mainPanel.setLayout(new GridLayout(5,0));
		frame.add(mainPanel);
		//panel that holds the insert portion of UI
		JPanel insertPanel = new JPanel();
		insertPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(insertPanel);
		insertPanel.setLayout(new GridLayout(4,4)); 
				
		//displays the Instructions for the UI
		JPopupMenu  p1stats = new JPopupMenu();
		String dataTypes = "DATA Types:\nL_ID(PK) INT(4); Exit_ID INT(4);\n"+
		"Size DECIMAL(10,2); Type VARCHAR(50);\nDirection VARCHAR(50)";
		JTextArea  stat1 = new JTextArea(dataTypes); 
		stat1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		stat1.setWrapStyleWord(true);
		stat1.setLineWrap(false);
		stat1.setEditable(false);
		String insert = "INSERT Insrutctions:\nTo insert into LOCATION all boxes have to be "
				+ "filled.\n Fill boxes with the "
				+ "data types as described above.\n Just type in the value(s) with no COMMAS, "
				+ "APOSTROPHES, or "
				+ "SEMICOLONS!!!\n After doing so hit the insert button to create values in table."; 
		JTextArea  stat2 = new JTextArea(insert);
		stat2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		stat2.setWrapStyleWord(true);
		stat2.setLineWrap(false);
		stat2.setEditable(false);
		String update = "UPDATE Instructions:\nYou can update either one or all attributes in LOCATION by "
				+ "filling the boxes with the data types as described above.\n Just type in the value(s) "
				+ "with no COMMAS, APOSTROPHES,or SEMICOLONS!!!\n Make sure the WHERE "
				+ "text field is filled like <column_name>=<value>.\nAgain do not add a SEMICOLON, COMMA or APOSTROPHE to the end "
				+ "of the where clause.\nThen hit the update button to update values in table."; 
		JTextArea  stat3 = new JTextArea(update);
		stat3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		stat3.setWrapStyleWord(true);
		stat3.setLineWrap(false);
		stat3.setEditable(false);
		String delete ="DELETE Instructions:\nTo delete a row in LOCATION just use the WHERE text field "
				+ "and write a statement such as <column_name>=<value>.\n Again do not add a SEMICOLON,"
				+ "COMMA, or APOSTROPE to the end of the where clause.\n Then hit the delete button to "
				+ "delete a row from the table."; 
		JTextArea  stat4 = new JTextArea(delete);
		stat4.setBorder(BorderFactory.createLineBorder(Color.black));
		stat4.setWrapStyleWord(true);
		stat4.setLineWrap(false);
		stat4.setEditable(false);
		String query = "QUERY Instructions:\nTo do a SQL Query on the LOCATION TABLE the form must be of "
				+ "the following:\n SELECT<attribute list> FROM LOCATION WHERE <condition>.\nTo simply display "
				+ "the rows of the LOCATION TABLE type the following"
				+ " SELECT * FROM LOCATION.\n Again do not add a SEMICOLON, COMMA, or APOSTROPHE to the end of "
				+ "the Query.\n Then hit the view button. The resusts of the Query will be shown in the table"
				+ "below.";
		JTextArea  stat5 = new JTextArea(query);
		stat5.setBorder(BorderFactory.createLineBorder(Color.black));
		stat5.setWrapStyleWord(true);
		stat5.setLineWrap(false);
		stat5.setEditable(false);
		p1stats.add(stat1); p1stats.add(stat2); p1stats.add(stat3);
		p1stats.add(stat4); p1stats.add(stat5);
		
		
		
		//insert heading
		JLabel lblInsert = new JLabel("INSERT");
		lblInsert.add(p1stats);
		lblInsert.setFont(new Font("Serif", Font.BOLD, 30));
		lblInsert.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		insertPanel.add(lblInsert);
		insertPanel.add(new JLabel(""));
		insertPanel.add(new JLabel(""));
		
		
		JLabel lblInst = new JLabel("Instructions");
		lblInst.setFont(new Font("Serif", Font.BOLD, 30));
		lblInst.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		//mouseListener to determine when to display the popup of the table's Instructions
		lblInst.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				p1stats.show(lblInst, arg0.getX(), arg0.getY());	
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				p1stats.setVisible(false);	
			}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}});
		insertPanel.add(lblInst);
		
		
		
		
				//creates the rest of the insertion labels and text fields for the UI
				JLabel lblarid = new JLabel("L_ID:");
				lblarid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lblarid);
				lidTextField = new JTextField();
				insertPanel.add(lidTextField);
				lidTextField.setColumns(10);
				JLabel lblgid = new JLabel("Exit_ID:");
				lblgid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lblgid);
				exitTextField = new JTextField();
				exitTextField.setColumns(10);
				insertPanel.add(exitTextField);
				JLabel lblamount = new JLabel("Size:");
				lblamount.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lblamount);
				sizeTextField = new JTextField();
				sizeTextField.setColumns(10);
				insertPanel.add(sizeTextField);
				JLabel lbllocation = new JLabel("Type:");
				lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lbllocation);
				typeTextField = new JTextField();
				typeTextField.setColumns(10);
				insertPanel.add(typeTextField);
				JLabel lblimage = new JLabel("Direction:");
				lblimage.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lblimage);
				directTextField = new JTextField();
				directTextField.setColumns(10);
				insertPanel.add(directTextField);
				insertPanel.add(new JLabel(""));
				btnInsert = new JButton("INSERT");
				btnInsert.addActionListener(this);
				insertPanel.add(btnInsert);
		//panel that holds the update portion of UI
		JPanel updatePanel = new JPanel();
		updatePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(updatePanel);
		updatePanel.setLayout(new GridLayout(5,4));
				//heading for update section of UI
				JLabel lblUpdate = new JLabel("UPDATE");
				lblUpdate.setFont(new Font("Serif", Font.BOLD, 30));
				lblUpdate.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
				updatePanel.add(lblUpdate);
				//dummy spacing values
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				//creates the rest of the update labels and text fields for the UI 
				JLabel lblupdateArid = new JLabel("L_ID:");
				lblupdateArid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateArid);
				lidUpdateTextField = new JTextField();
				lidUpdateTextField.setColumns(10);
				updatePanel.add(lidUpdateTextField);
				JLabel lblupdateGid= new JLabel("Exit_ID:");
				lblupdateGid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateGid);
				exitUpdateTextField = new JTextField();
				exitUpdateTextField.setColumns(10);
				updatePanel.add(exitUpdateTextField);
				JLabel lblupdateprotection = new JLabel("Size:");
				lblupdateprotection.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateprotection);
				sizeUpdateTextField = new JTextField();
				sizeUpdateTextField.setColumns(10);
				updatePanel.add(sizeUpdateTextField);
				JLabel lblupdatelocation = new JLabel("Type:");
				lblupdatelocation.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdatelocation);
				typeUpdateTextField = new JTextField();
				typeUpdateTextField.setColumns(10);
				updatePanel.add(typeUpdateTextField);
				JLabel lblupdateimage = new JLabel("Direction:");
				lblupdateimage.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateimage);
				directUpdateTextField = new JTextField();
				directUpdateTextField.setColumns(10);
				updatePanel.add(directUpdateTextField);
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				JLabel lblupdateWhere = new JLabel("WHERE");
				lblupdateWhere.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateWhere);
				whereUpdateTextField = new JTextField();
				whereUpdateTextField.setColumns(10);
				updatePanel.add(whereUpdateTextField);
				updatePanel.add(new JLabel(""));
				btnUpdate = new JButton("UPDATE");
				btnUpdate.addActionListener(this);
				updatePanel.add(btnUpdate);
		//panel that holds the delete portion of UI
		JPanel deletePanel = new JPanel();
		deletePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(deletePanel);
		deletePanel.setLayout(new GridLayout(1,1));
				//panel that contains all of the other components
				JPanel leftDeletePanel= new JPanel(new BorderLayout()); 
				deletePanel.add(leftDeletePanel); 
				JLabel lblDelete = new JLabel("DELETE");
				lblDelete.setFont(new Font("Serif", Font.BOLD, 30));
				lblDelete.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)); 
				leftDeletePanel.add(lblDelete, BorderLayout.NORTH);
				JLabel lbldeleteWhere = new JLabel("WHERE");
				lbldeleteWhere.setFont(new Font("Tahoma", Font.PLAIN, 15));
				whereDeleteTextField = new JTextField();
				whereDeleteTextField.setColumns(10);
				whereDeleteTextField.setFont(new Font("Tahoma", Font.BOLD, 30));
				lbldeleteWhere.add(whereDeleteTextField); 
				leftDeletePanel.add(lbldeleteWhere, BorderLayout.WEST);
				leftDeletePanel.add(whereDeleteTextField, BorderLayout.CENTER); 
				leftDeletePanel.add(new JLabel(" "), BorderLayout.SOUTH);
				btnDelete = new JButton("DELETE");
				btnDelete.addActionListener(this);
				leftDeletePanel.add(btnDelete, BorderLayout.EAST);
		//panel that holds the query portion of the UI
		JPanel viewPanel = new JPanel();
		viewPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(viewPanel);
		viewPanel.setLayout(new GridLayout(1,1));
				//panel that holds all of the other components
				JPanel leftViewPanel = new JPanel(new BorderLayout());
				viewPanel.add(leftViewPanel); 
				JLabel lblView = new JLabel("VIEW");
				lblView.setFont(new Font("Serif", Font.BOLD, 30));
				lblView.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)); 
				leftViewPanel.add(lblView, BorderLayout.NORTH); 
				JLabel lblviewSelect = new JLabel("SELECT");
				lblviewSelect.setFont(new Font("Tahoma", Font.PLAIN, 15));
				leftViewPanel.add(lblviewSelect, BorderLayout.WEST);
				selectViewTextField = new JTextField();
				selectViewTextField.setColumns(10);
				selectViewTextField.setFont(new Font("Tahoma", Font.BOLD, 30));
				leftViewPanel.add(selectViewTextField, BorderLayout.CENTER); 
				leftViewPanel.add(new JLabel(" "), BorderLayout.SOUTH);
				btnView = new JButton("VIEW"); 
				btnView.addActionListener(this);
				leftViewPanel.add(btnView, BorderLayout.EAST);
		//panel that will hold the Jtable for displaying the DBMS table after Query
		resultsPanel = new JPanel();
		resultsPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(resultsPanel);
		resultsPanel.setLayout(new BorderLayout());
				JLabel lblResults = new JLabel("RESULTS");
				lblResults.setFont(new Font("Serif", Font.BOLD, 30));
				lblResults.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)); 
				resultsPanel.add(lblResults, BorderLayout.NORTH);
				//creating of the table with a certain data table model for linking columns
				table = new JTable(tablemodel); 
				//method that disables tabel editing
				table.setEnabled(false);
				//method that disables column movement
				table.getTableHeader().setReorderingAllowed(false);
				//method that disables user resizing of table
				table.getTableHeader().setResizingAllowed(false);
				//adds table to scrollpane compoenent;will have scroll bar
				scrollPane = new JScrollPane(table);
				resultsPanel.add(scrollPane, BorderLayout.CENTER);
	
	}
	
	/**
	 * Actions performed when buttons are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//event triggered if btnInsert object is pressed
		if(e.getSource() == btnInsert)
		{
			//checks to see if any of the insert values are empty(ie., cannot have Null values)
			if(lidTextField.getText().equals("") || exitTextField.getText().equals("") ||
					sizeTextField.getText().equals("") || typeTextField.getText().equals("")||
					directTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				//if the fields are not empty insertion of values into DBMS will be done
				try {  
					PreparedStatement stmt = conn.prepareStatement("insert into LOCATION values(?,?,?,?,?)");
					String arid=lidTextField.getText();
					stmt.setInt(1, Integer.parseInt(arid));
					String gid=exitTextField.getText(); 
					stmt.setInt(2, Integer.parseInt(gid));
					String protection2 = sizeTextField.getText(); 
					stmt.setDouble(3,Double.parseDouble(protection2)); 
					stmt.setString(4, typeTextField.getText());
					stmt.setString(5, directTextField.getText());
					stmt.executeUpdate();
					JOptionPane.showMessageDialog(null, "Insert Successful");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "SQL  Insert Error "+e1.getMessage());
				} 
				
				
			}
				
			
		}
		else if(e.getSource() == btnUpdate)//event triggered when btnUpdate object is triggered
		{
			//checks to see if all of the update values are empty
			if(lidUpdateTextField.getText().equals("") && exitUpdateTextField.getText().equals("") &&
					sizeUpdateTextField.getText().equals("") && typeUpdateTextField.getText().equals("")&&
					directUpdateTextField.getText().equals("") )
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				//if the some of fields are not empty updating of DBMS will be done
				try {
					Statement stmt = conn.createStatement(); 
					String update = "UPDATE LOCATION SET "; 			
					if (!lidUpdateTextField.getText().equals(""))
					{
						String arid=lidUpdateTextField.getText();
						update+="L_ID = "+ arid +" ";
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!exitUpdateTextField.getText().equals(""))
					{
						update = "UPDATE LOCATION SET "; 	
						String gid=exitUpdateTextField.getText();
						update+="Exit_ID = "+ gid +" "; 
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!sizeUpdateTextField.getText().equals(""))
					{
						update = "UPDATE LOCATION SET ";
						String protection=sizeUpdateTextField.getText();
						update+="Size = "+ protection +" ";
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!typeUpdateTextField.getText().equals(""))
					{
						update = "UPDATE LOCATION SET ";
						String armor=typeUpdateTextField.getText(); 
						update+="Type = '"+ armor +"' "; 
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!directUpdateTextField.getText().equals(""))
					{
						update = "UPDATE LOCATION SET ";
						String image=directUpdateTextField.getText();
						update+="Direction = '"+ image +"' ";
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					JOptionPane.showMessageDialog(null, "Update Successful");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "SQL  Update Error "+e1.getMessage());
				} 
				
				
				
			}
		
		}
		else if(e.getSource() == btnDelete)//event triggered when btnDelete object is triggered
		{
			//checks to see if deletefield is emepty
			if(whereDeleteTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Field is Empty");
			}
			else
			{
				//if field is not empty deletion of row will be done
				try {
					Statement stmt = conn.createStatement(); 
					String delete = "DELETE FROM LOCATION ";
					delete+="WHERE "+ whereDeleteTextField.getText()+ "";
					stmt.executeUpdate(delete);
					JOptionPane.showMessageDialog(null, "Update Successful");
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "SQL  Delete Error "+ e1.getMessage());
				}
				
			}
			
		}
		else if(e.getSource() == btnView)//event triggered when btnView object is triggered
		{
			//checks to see if there was a query
			if(selectViewTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Field is Empty");
			}
			else
			{
				//clears the components in the results panel to repaint panel with results table
				resultsPanel.removeAll();
				//dynamic vector that points to a dynamic vector that stores Strings  
				Vector<String> columnNames = new Vector<String>(); 
				//dynamic vector that points to a dynamic vector containing vectors that store objects
				Vector<Vector<Object>> data = new Vector<Vector<Object>>(); 
				
				//if field is not empty query on DBMS will be done
				try {
					Statement stmt = conn.createStatement(); 
					String query=selectViewTextField.getText();
					//executes query and object that points to first row in table is returned
					ResultSet rs =stmt.executeQuery(query);
					//object that contains information on how the data is formated in the columns of resultset
					ResultSetMetaData md = rs.getMetaData(); 
					int columns = md.getColumnCount();//returns the number of columns in resultset
					//for loop that gets each of the column names of the result set and adds it to string vector
					for (int i=1; i<=columns; i++)
					{
						columnNames.add(md.getColumnName(i)); 
					}
					//while loop that traveres each row of the resultset
					while(rs.next())
					{
						Vector<Object> row = new Vector<Object>(); 
						for (int i=1; i<=columns; i++)
						{
							//gets each of the column values from the result set
							row.add(rs.getObject(i));
							//adds it to the object vector
						}
						data.add(row);//each object vector is added to another vector
					}
					//repainting the results panel with a jtable
					JLabel lblResults = new JLabel("RESULTS");
					lblResults.setFont(new Font("Serif", Font.BOLD, 30));
					lblResults.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED)); 
					resultsPanel.add(lblResults, BorderLayout.NORTH);
					table = new JTable(tablemodel); 
					scrollPane = new JScrollPane(table);
					//now the data model is given column objects and strings from the resultset to build table
					tablemodel.setDataVector(data, columnNames);
					resultsPanel.add(scrollPane, BorderLayout.CENTER);
					resultsPanel.revalidate();
					resultsPanel.repaint();
					
		
					
				} catch (SQLException e1) {
					
					JOptionPane.showMessageDialog(null, "SQL Query Error "+ e1.getMessage());
				}
				
			}
		
		}
	}
	
	
	
	/**
	 * provides a way to get the frame within this class
	 */
	@Override
	public JFrame getFrame()
	{
		return frame;
	}
	
	
	
	

}


