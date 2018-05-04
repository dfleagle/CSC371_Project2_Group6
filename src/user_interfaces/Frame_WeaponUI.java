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
 * UI Frame for the Weapon Table that the user uses 
 * to interact with the DBMS
 * @author adam Standke
 *
 */
public class Frame_WeaponUI extends Application implements Frame, ActionListener  
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
	private JTextField widTextField;
	private JTextField gidTextField;
	private JTextField aidTextField;
	private JTextField weaponTextField;
	private JTextField imageTextField; 
	private JTextField descriptionTextField; 
	private JTextField widUpdateTextField;
	private JTextField gidUpdateTextField;
	private JTextField aidUpdateTextField;
	private JTextField weaponUpdateTextField;
	private JTextField imageUpdateTextField;
	private JTextField descriptionUpdateTextField;
	private JTextField whereUpdateTextField;
	private JTextField whereDeleteTextField;
	private JTextField selectViewTextField;
	private int width, height;

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public Frame_WeaponUI() 
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
		frame.setTitle("Weapon Interface");//sets title of frame
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
		insertPanel.setLayout(new GridLayout(5,4));
		
		//displays the Instructions for the UI
		JPopupMenu  p1stats = new JPopupMenu();
		String dataTypes = "DATA Types:\nWID(PK) INT(4); GID INT(4);\n"+
		"AID INT(4); Weapon_Location VARCHAR(50);\n Image BLOB(20M);"+
		"Description TEXT(1k)";
		JTextArea  stat1 = new JTextArea(dataTypes); 
		stat1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		stat1.setWrapStyleWord(true);
		stat1.setLineWrap(false);
		stat1.setEditable(false);
		String insert = "INSERT Insrutctions:\nTo insert into WEAPON all boxes have to be "
				+ "filled.\n Fill boxes with the "
				+ "data types as described above.\n Just type in the value(s) with no COMMAS, "
				+ "APOSTROPHES, or "
				+ "SEMICOLONS!!!\n After doing so hit the insert button to create values in table."; 
		JTextArea  stat2 = new JTextArea(insert);
		stat2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		stat2.setWrapStyleWord(true);
		stat2.setLineWrap(false);
		stat2.setEditable(false);
		String update = "UPDATE Instructions:\nYou can update either one or all attributes in WEAPON by "
				+ "filling the boxes with the data types as described above.\n Just type in the value(s) "
				+ "with no COMMAS, APOSTROPHES,or SEMICOLONS!!!\n Make sure the WHERE "
				+ "text field is filled like <column_name>=<value>.\nAgain do not add a SEMICOLON, COMMA or APOSTROPHE to the end "
				+ "of the where clause.\nThen hit the update button to update values in table."; 
		JTextArea  stat3 = new JTextArea(update);
		stat3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		stat3.setWrapStyleWord(true);
		stat3.setLineWrap(false);
		stat3.setEditable(false);
		String delete ="DELETE Instructions:\nTo delete a row in WEAPON just use the WHERE text field "
				+ "and write a statement such as <column_name>=<value>.\n Again do not add a SEMICOLON,"
				+ "COMMA, or APOSTROPE to the end of the where clause.\n Then hit the delete button to "
				+ "delete a row from the table."; 
		JTextArea  stat4 = new JTextArea(delete);
		stat4.setBorder(BorderFactory.createLineBorder(Color.black));
		stat4.setWrapStyleWord(true);
		stat4.setLineWrap(false);
		stat4.setEditable(false);
		String query = "QUERY Instructions:\nTo do a SQL Query on the WEAPON TABLE the form must be of "
				+ "the following:\n SELECT<attribute list> FROM WEAPON WHERE <condition>.\nTo simply display "
				+ "the rows of the WEAPON TABLE type the following"
				+ " SELECT * FROM WEAPON.\n Again do not add a SEMICOLON, COMMA, or APOSTROPHE to the end of "
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
				JLabel lblarid = new JLabel("WID:");
				lblarid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lblarid);
				widTextField = new JTextField();
				insertPanel.add(widTextField);
				widTextField.setColumns(10);
				JLabel lblgid = new JLabel("GID:");
				lblgid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lblgid);
				gidTextField = new JTextField();
				gidTextField.setColumns(10);
				insertPanel.add(gidTextField);
				JLabel lblamount = new JLabel("AID:");
				lblamount.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lblamount);
				aidTextField = new JTextField();
				aidTextField.setColumns(10);
				insertPanel.add(aidTextField);
				JLabel lbllocation = new JLabel("Weapon Location:");
				lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lbllocation);
				weaponTextField = new JTextField();
				weaponTextField.setColumns(10);
				insertPanel.add(weaponTextField);
				JLabel lblimage = new JLabel("Image:");
				lblimage.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lblimage);
				imageTextField = new JTextField();
				imageTextField.setColumns(10);
				insertPanel.add(imageTextField);
				JLabel lbldescription = new JLabel("Description:");
				lbldescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
				insertPanel.add(lbldescription);
				descriptionTextField = new JTextField();
				descriptionTextField.setColumns(10);
				insertPanel.add(descriptionTextField);
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
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
				JLabel lblupdateArid = new JLabel("WID:");
				lblupdateArid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateArid);
				widUpdateTextField = new JTextField();
				widUpdateTextField.setColumns(10);
				updatePanel.add(widUpdateTextField);
				JLabel lblupdateGid= new JLabel("GID:");
				lblupdateGid.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateGid);
				gidUpdateTextField = new JTextField();
				gidUpdateTextField.setColumns(10);
				updatePanel.add(gidUpdateTextField);
				JLabel lblupdateprotection = new JLabel("AID:");
				lblupdateprotection.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateprotection);
				aidUpdateTextField = new JTextField();
				aidUpdateTextField.setColumns(10);
				updatePanel.add(aidUpdateTextField);
				JLabel lblupdatelocation = new JLabel("Weapon Location:");
				lblupdatelocation.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdatelocation);
				weaponUpdateTextField = new JTextField();
				weaponUpdateTextField.setColumns(10);
				updatePanel.add(weaponUpdateTextField);
				JLabel lblupdateimage = new JLabel("Image:");
				lblupdateimage.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdateimage);
				imageUpdateTextField = new JTextField();
				imageUpdateTextField.setColumns(10);
				updatePanel.add(imageUpdateTextField);
				JLabel lblupdatedescription = new JLabel("Description:");
				lblupdatedescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updatePanel.add(lblupdatedescription);
				descriptionUpdateTextField = new JTextField();
				descriptionUpdateTextField.setColumns(10);
				updatePanel.add(descriptionUpdateTextField);
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
			if(widTextField.getText().equals("") || gidTextField.getText().equals("") ||
					aidTextField.getText().equals("") || weaponTextField.getText().equals("")||
					imageTextField.getText().equals("") || descriptionTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				//if the fields are not empty insertion of values into DBMS will be done
				try {
					PreparedStatement stmt = conn.prepareStatement("insert into WEAPON values(?,?,?,?,?,?)");
					String arid=widTextField.getText();
					stmt.setInt(1, Integer.parseInt(arid));
					String gid=gidTextField.getText(); 
					stmt.setInt(2, Integer.parseInt(gid));
					String protection2 = aidTextField.getText(); 
					stmt.setInt(3, Integer.parseInt(protection2));
					stmt.setString(4, weaponTextField.getText());
					stmt.setString(5, imageTextField.getText());
					stmt.setString(6, descriptionTextField.getText());
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
			if(widUpdateTextField.getText().equals("") && gidUpdateTextField.getText().equals("") &&
					aidUpdateTextField.getText().equals("") && weaponUpdateTextField.getText().equals("")&&
					imageUpdateTextField.getText().equals("") && descriptionUpdateTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				//if the some of fields are not empty updating of DBMS will be done
				try {
					Statement stmt = conn.createStatement(); 
					String update = "UPDATE WEAPON SET "; 			
					if (!widUpdateTextField.getText().equals(""))
					{
						String arid=widUpdateTextField.getText();
						update+="WID = "+ arid +" ";
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!gidUpdateTextField.getText().equals(""))
					{
						update = "UPDATE WEAPON SET ";
						String gid=gidUpdateTextField.getText();
						update+="GID = "+ gid +" ";
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!aidUpdateTextField.getText().equals(""))
					{
						update = "UPDATE WEAPON SET ";
						String protection=aidUpdateTextField.getText();
						update+="AID = "+ protection +" "; 
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!weaponUpdateTextField.getText().equals(""))
					{
						update = "UPDATE WEAPON SET ";
						String armor=weaponUpdateTextField.getText(); 
						update+="Weapon_Location = '"+ armor +"' ";
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!imageUpdateTextField.getText().equals(""))
					{
						update = "UPDATE WEAPON SET ";
						String image=imageUpdateTextField.getText();
						update+="Image = '"+ image +"' ";
						update+="WHERE "+ whereUpdateTextField.getText()+ "";
						stmt.executeUpdate(update);
					}
					if(!descriptionUpdateTextField.getText().equals(""))
					{
						update = "UPDATE WEAPON SET ";
						String description=descriptionUpdateTextField.getText();
						update+="Description = '"+ description +"' ";
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
					String delete = "DELETE FROM WEAPON ";
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
