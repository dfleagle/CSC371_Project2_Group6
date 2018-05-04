package user_interfaces;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 * this class will create a jframe that will interact with the player table
 * within the database
 * 
 * @author Denny Fleagle
 *
 */
public class Frame_PlayerUI extends Application implements Frame, ActionListener
{
	private JFrame frame;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnView;
	private JMenuItem miInst;
	private JTextField loginTextField;
	private JTextField passwordTextField;
	private JTextField managerTextField;
	private JTextField moderatorTextField;
	private JTextField emailTextField;
	private JTextField loginUpdateTextField;
	private JTextField passwordUpdateTextField;
	private JTextField moderatorUpdateTextField;
	private JTextField emailUpdateTextField;
	private JTextField managerUpdateTextField;
	private JTextField whereUpdateTextField;
	private JTextField whereDeleteTextField;
	private JTextField selectViewTextField;
	private JTable table;
	private int width, height;

	/**
	 * Create the application.
	 */
	public Frame_PlayerUI()
	{
		setFrameSize();
		buildFrame();
		frame.setVisible(true);
	}
	
	public static void main(String [] args)
	{
		new Frame_PlayerUI();
	}

	/**
	 * set frame size
	 */
	@Override
	public void setFrameSize()
	{
		this.width = getFrameWidth();
		this.height = getFrameHeight();
		
	}

	/**
	 * build the frame that will be displayed
	 */
	@Override
	public void buildFrame()
	{
		//create gridbag constraints for displaying
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.insets = new Insets(0,10,0,10);
		c.weightx = 0.5;
		c.fill = GridBagConstraints.BOTH;
		
		//create the jframe 
		frame = new JFrame("Player Table");
		frame.setSize(width, height);
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridLayout());
		
		//create a menubar for the top of the window
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		//create a menu 
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Ariel", Font.BOLD, (int)(height * .015)));

		//create a menu item for in the menubar 
		miInst = new JMenuItem("Instructions");
		miInst.setFont(new Font("Ariel", Font.BOLD, (int)(height * .015)));
		miInst.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				String s = "INSERT "
						+ "\nCreate new player by entering all the data needed. Login must be unique. "
						+ "\nIf you enter a login that exists you will receive an error. \n\n"
						+ "UPDATE "
						+ "\nUpdate a player that already exists. The WHERE login = field must match an "
						+ "existing player. \nFuture login must be unique you will be warned if not. \n\n"
						+ "DELETE"
						+ "\nEnter existing player login in the WHERE login = field. \nWarning the record will "
						+ "be deleted from the database. If the player's login does \nnot exist you will recieve "
						+ "a prompt. \n\n"
						+ "VIEW"
						+ "\nEnter ***ALL to view all data in table (shown in RESULTS section below), otherwise "
						+ "enter player login that you wish to view. \nIf no match is found you will be prompted. ";
				
				JOptionPane.showMessageDialog(null, s, "Instructions", JOptionPane.OK_OPTION);

			}
		});
		
		menuFile.add(miInst);
		menuBar.add(menuFile);
		
		
		//create the main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, width, height);
		mainPanel.setLayout(new GridBagLayout());
		frame.add(mainPanel);
		
//insert
				JLabel lblInsert = new JLabel("INSERT");
				lblInsert.setFont(new Font("Serif", Font.BOLD, (int)(height * .03)));
				c.gridx = 0;
				c.gridy = 0;
				mainPanel.add(lblInsert, c);
				
				JLabel lblLogin = new JLabel("Login");
				c.gridx = 0;
				c.gridy = 1;
				mainPanel.add(lblLogin, c);
				
				JLabel lblPassword = new JLabel("Password");
				c.gridx = 0; 
				c.gridy = 2;
				mainPanel.add(lblPassword, c);
				
				JLabel lblEmail = new JLabel("Email");
				c.gridx = 0;
				c.gridy = 3;
				mainPanel.add(lblEmail, c);
				
				loginTextField = new JTextField();
				loginTextField.setColumns(30);
				c.gridx = 1;
				c.gridy = 1;
				mainPanel.add(loginTextField, c);
				
				passwordTextField = new JTextField();
				passwordTextField.setColumns(30);
				c.gridx = 1;
				c.gridy = 2;
				mainPanel.add(passwordTextField, c);
				
				emailTextField = new JTextField();
				emailTextField.setColumns(30);
				c.gridx = 1;
				c.gridy = 3;
				mainPanel.add(emailTextField, c);
				
				JLabel lblManager = new JLabel("Manager");
				c.gridx = 2;
				c.gridy = 1;
				mainPanel.add(lblManager, c);
				
				JLabel lblModerator = new JLabel("Moderator");
				c.gridx = 2;
				c.gridy = 2;
				mainPanel.add(lblModerator, c);
				
				managerTextField = new JTextField();
				managerTextField.setColumns(30);
				c.gridx = 3;
				c.gridy = 1;
				mainPanel.add(managerTextField, c);
				
				moderatorTextField = new JTextField();
				moderatorTextField.setColumns(30);
				c.gridx = 3;
				c.gridy = 2;
				mainPanel.add(moderatorTextField, c);				
				
				btnInsert = new JButton("INSERT");
				btnInsert.addActionListener(this);
				btnInsert.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
				c.gridx = 5;
				c.gridy = 2;
				c.insets = new Insets(0,0,0,10);
				mainPanel.add(btnInsert, c);
				c.insets = new Insets(0,10,0,10);
		
//update 
				JLabel lblUpdate = new JLabel("UPDATE");
				lblUpdate.setFont(new Font("Serif", Font.BOLD, (int)(height * .03)));
				c.gridx = 0;
				c.gridy = 4;
				mainPanel.add(lblUpdate, c);
				
				JLabel lblupdateLogin = new JLabel("Login");
				c.gridx = 0;
				c.gridy = 5;
				mainPanel.add(lblupdateLogin, c);
				
				JLabel lblupdatePassword = new JLabel("Password");
				c.gridx = 0; 
				c.gridy = 6;
				mainPanel.add(lblupdatePassword,c);
				
				loginUpdateTextField = new JTextField();
				loginUpdateTextField.setColumns(30);
				c.gridx = 1;
				c.gridy = 5;
				mainPanel.add(loginUpdateTextField, c);
				
				passwordUpdateTextField = new JTextField();
				passwordUpdateTextField.setColumns(30);
				c.gridx = 1; 
				c.gridy = 6;
				mainPanel.add(passwordUpdateTextField, c);
				
				JLabel lblupdateManager = new JLabel("Manager");
				c.gridx = 2;
				c.gridy = 5;
				mainPanel.add(lblupdateManager, c);
				
				JLabel lblupdateModerator = new JLabel("Moderator");
				c.gridx = 2;
				c.gridy = 6;
				mainPanel.add(lblupdateModerator, c);
				
				moderatorUpdateTextField = new JTextField();
				moderatorUpdateTextField.setColumns(30);
				c.gridx = 3;
				c.gridy = 6;
				mainPanel.add(moderatorUpdateTextField, c);
				
				managerUpdateTextField = new JTextField();
				managerUpdateTextField.setColumns(30);
				c.gridx = 3;
				c.gridy = 5;
				mainPanel.add(managerUpdateTextField, c);
				
				JLabel lblupdateEmail = new JLabel("Email");
				c.gridx = 0;
				c.gridy = 7;
				mainPanel.add(lblupdateEmail, c);
				
				JLabel lblupdateWhere = new JLabel("WHERE login =");
				c.gridx = 2; 
				c.gridy = 7;
				mainPanel.add(lblupdateWhere, c);
				
				emailUpdateTextField = new JTextField();
				c.gridx = 1;
				c.gridy = 7; 
				mainPanel.add(emailUpdateTextField, c);
				
				whereUpdateTextField = new JTextField();
				whereUpdateTextField.setColumns(30);
				c.gridx = 3;
				c.gridy = 7;
				mainPanel.add(whereUpdateTextField, c);
				
				btnUpdate = new JButton("UPDATE");
				btnUpdate.addActionListener(this);
				btnUpdate.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
				c.gridx = 5;
				c.gridy = 6; 
				c.insets = new Insets(0,0,0,10);
				mainPanel.add(btnUpdate, c);
				c.insets = new Insets(0,10,0,10);
// delete
		
				JLabel lblDelete = new JLabel("DELETE");
				lblDelete.setFont(new Font("Serif", Font.BOLD, (int)(height * .03)));
				c.gridx = 0; 
				c.gridy = 8;
				mainPanel.add(lblDelete, c);
				
				JLabel lbldeleteWhere = new JLabel("WHERE login =");
				c.gridx = 0; 
				c.gridy = 9;
				mainPanel.add(lbldeleteWhere, c);
				
				whereDeleteTextField = new JTextField();
				whereDeleteTextField.setColumns(77);
				c.gridx = 1;
				c.gridy = 9;
				c.gridwidth = 3;
				mainPanel.add(whereDeleteTextField, c);
				
				btnDelete = new JButton("DELETE");
				btnDelete.addActionListener(this);
				btnDelete.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
				c.gridx = 5;
				c.gridy = 9;
				c.insets = new Insets(0,0,0,10);
				mainPanel.add(btnDelete, c);
				c.insets = new Insets(0,10,0,10);

// view		
				JLabel lblView = new JLabel("VIEW");
				lblView.setFont(new Font("Serif", Font.BOLD, (int)(height * .03)));
				c.gridx = 0;
				c.gridy = 10;
				mainPanel.add(lblView, c);
				
				JLabel lblviewSelect = new JLabel("WHERE login =");
				c.gridx = 0;
				c.gridy = 11;
				mainPanel.add(lblviewSelect, c);
				
				selectViewTextField = new JTextField();
				selectViewTextField.setColumns(77);
				c.gridx = 1;
				c.gridy = 11;
				c.gridwidth = 3;
				mainPanel.add(selectViewTextField, c);
				
				btnView = new JButton("VIEW");
				btnView.addActionListener(this);
				btnView.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
				c.gridx = 5;
				c.gridy = 11;
				c.insets = new Insets(0,0,0,10);
				mainPanel.add(btnView, c);
				c.insets = new Insets(0,10,0,10);

//	results
				JLabel lblResults = new JLabel("RESULTS");
				lblResults.setFont(new Font("Serif", Font.BOLD, (int)(height * .03)));
				c.gridx = 0; 
				c.gridy = 12;
				mainPanel.add(lblResults, c);		   

			    table = new JTable(tablemodel);
			    table.getTableHeader().setFont(new Font("Serif", Font.ITALIC, (int)(height * .025)));
			    table.setFont(new Font("Serif", Font.BOLD, (int)(height * .02)));
			    table.setRowHeight(table.getRowHeight()+ (int)(height * .02));
				
				JScrollPane scrollPane = new JScrollPane(table);
				c.gridx = 0;
				c.gridy = 13;
				c.gridwidth = 7;
				c.ipady = (int)(height * .3);
				c.fill = GridBagConstraints.BOTH;
				mainPanel.add(scrollPane, c);
							
	}
	
	/**
	 * Actions performed when buttons are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnInsert)
		{
			if(loginTextField.getText().equals("") || passwordTextField.getText().equals("") ||
					managerTextField.getText().equals("") || moderatorTextField.getText().equals("") ||
					emailTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				// Using a PreparedStatement to insert a value (best option when providing values
		        // from variables).
		        // Use place holders '?' to mark where I am going to provide the data.
		        String insertData = new String("INSERT INTO PLAYER (login, password, mod_login, man_login, email) VALUES (?,?,?,?,?)");
		        try
				{
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					statement.setString(1, loginTextField.getText());
					statement.setString(2, passwordTextField.getText());
					statement.setString(3, moderatorTextField.getText());
					statement.setString(4, managerTextField.getText());
					statement.setString(5, emailTextField.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");
					
				} catch (SQLException e1)
				{
					String s = "An error has occured.\nlogin must be unique! \nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
				}
		        
		        //Clear the text fields
				loginTextField.setText("");
				passwordTextField.setText("");
				managerTextField.setText("");
				moderatorTextField.setText("");
				emailTextField.setText("");
			}

		}
		else if(e.getSource() == btnUpdate)
		{
			if(loginUpdateTextField.getText().equals("") || passwordUpdateTextField.getText().equals("") ||
					managerUpdateTextField.getText().equals("") || moderatorUpdateTextField.getText().equals("") ||
					whereUpdateTextField.getText().equals("") || emailUpdateTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				// Using a PreparedStatement to insert a value (best option when providing values
		        // from variables).
		        // Use place holders '?' to mark where I am going to provide the data.
		        String insertData = new String("UPDATE PLAYER SET login = ?, password = ?, mod_login = ?, man_login = ?, email = ?"
		        		+ "WHERE login = ?");
		        try
				{
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					//see if there exists a login which the user is trying to update 
					//if not throw an exception 
					String selectdata = new String("SELECT * FROM PLAYER");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean found = false;
					while(rs.next())
					{
						String login = rs.getString("login");
						if(login.equals(whereUpdateTextField.getText()))
						{
							found = true;
						}
					}
					if(!found)
					{
						throw new Exception();
					}
					
					statement.setString(1, loginUpdateTextField.getText());
					statement.setString(2, passwordUpdateTextField.getText());
					statement.setString(3, moderatorUpdateTextField.getText());
					statement.setString(4, managerUpdateTextField.getText());
					statement.setString(5, emailUpdateTextField.getText());
					statement.setString(6, whereUpdateTextField.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");
					
				} catch (SQLException e1)
				{
					String s = "An error has occured.\n\nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
				
				} catch (Exception notfound)
		        {
					JOptionPane.showMessageDialog(null, "Login does not exist\n\nTry Again!");
		        }
		       
		        
		        //Clear the text fields
				loginUpdateTextField.setText("");
				passwordUpdateTextField.setText("");
				managerUpdateTextField.setText("");
				moderatorUpdateTextField.setText("");
				whereUpdateTextField.setText("");
				emailUpdateTextField.setText("");
			}
		
		}
		else if(e.getSource() == btnDelete)
		{
			if(whereDeleteTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Field is Empty");
			}
			else
			{
				String insertData = new String("DELETE FROM PLAYER WHERE login = ?");

				try
				{
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					//see if there exists a login which the user is trying to update 
					//if not throw an exception 
					String selectdata = new String("SELECT * FROM PLAYER");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean found = false;
					while(rs.next())
					{
						String login = rs.getString("login");
						if(login.equals(whereDeleteTextField.getText()))
						{
							found = true;
						}
					}
					if(!found)
					{
						throw new Exception();
					}
					
					statement.setString(1, whereDeleteTextField.getText());
					JOptionPane.showMessageDialog(null, "Update Successful");
					statement.executeUpdate();

				} catch (SQLException e1)
				{
					String s = "An error has occured.\n\nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
				} catch (Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "Cannot delete! \nLogin does not exist");
				}

				whereDeleteTextField.setText("");

			}

		}
		else if(e.getSource() == btnView)
		{
			if(selectViewTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Fields is Empty");
			}
			else
			{
				
				//dynamic vector that points to a dynamic vector that stores Strings 
				Vector<String> columnNames = new Vector<String>();
				//dynamic vector that points to a dynamic vector containing vectors that store objects
				Vector<Vector<Object>> data = new Vector<Vector<Object>>(); 
				
				// select statement 
				String selectData = "SELECT * FROM PLAYER WHERE login = ?";
			
				try
				{
					PreparedStatement statement = conn.prepareStatement(selectData);

					if(selectViewTextField.getText().equals("***ALL"))
					{
						String select = "SELECT * FROM PLAYER";
						Statement state = conn.createStatement();
						ResultSet rs = state.executeQuery(select);
						
						//get the metadata and use that for creating the table model					
						ResultSetMetaData md = rs.getMetaData(); 
						int columns = md.getColumnCount(); //returns the number of columns in resultset
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
							data.add(row); //each object vector is added to another vector
						}
						
						//add the data and column names to the table model
						tablemodel.setDataVector(data, columnNames);
			
					}
					else
					{
						statement.setString(1, selectViewTextField.getText());
						ResultSet rs = statement.executeQuery();
						
						//check if the login even exists in the database
						boolean found = false;
						while(rs.next())
						{
							String login = rs.getString("login");
							if(login.equals(selectViewTextField.getText()))
							{
								found = true;
							}
						}
						if(!found)
						{
							throw new Exception();
						}
						
						//Reset the pointer back to the beginning of the result set
						rs.beforeFirst();

						//get the metadata to create the table model with 
						ResultSetMetaData md = rs.getMetaData(); 
						int columns = md.getColumnCount(); //returns the number of columns in resultset
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
							data.add(row); //each object vector is added to another vector
						}
						
						//add the data and column names to the table model
						tablemodel.setDataVector(data, columnNames);

					}
				
				} catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(null, "SQL Error");
				} catch (Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "Login not found\nTry Again!");
				}
				
				selectViewTextField.setText("");
				
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
	
	//data model used to link resultset columns used in constructing jtable
	@SuppressWarnings("serial")
	private DefaultTableModel tablemodel = new DefaultTableModel() {
		//annoymous class that overides method that now allows tabel cells to not be editable
		@Override	
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			return false;

		}
	};
}
