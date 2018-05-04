package user_interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Frame_Moderator extends Application implements Frame, ActionListener
{

	//initialize our buttons
	private JFrame frame;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnView;
	
	//text fields for insert
	private JTextField insertModLogTextField;
	private JTextField insertEmailTextField;
	//text fields for update
	private JTextField updateModLoginTextField;
	private JTextField updateEmailTextField;
	private JTextArea updateWhereTextArea;
	
	//text fields for where and view
	private JTextField whereDeleteTextField;
	private JTextField selectViewTextField;
	//instructions
	private JMenuItem instructions;
	
	private JTextArea resultTextArea;
	private int width, height;
	Font font1 = new Font("Tahoma", Font.BOLD, 15);
	
	
	/**
	 * First, create the application.
	 * Constructor for the Manager class.
	 */
	public Frame_Moderator()
	{
		//sets the size of the frame. Will be uniform
		setFrameSize();
		
		//build the frame method
		buildFrame();
		
		//make sure the frame is visible
		frame.setVisible(true);
		
		//packing!
		frame.pack();
	}
	
	

	@Override
	public void buildFrame()
	{
		//set our frame variable
		frame = new JFrame();
				
		//set the bounds with variables set earlier
		frame.setBounds(0, 0, width, height);
		//frame.setUndecorated(true);
		//set to dispose on close
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		
		//creates a main panel to the proper size
		//this panel will be a background to other panels
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, width, height);
					
		//set to Grid layout
		mainPanel.setLayout(new GridLayout(5,1));
		frame.add(mainPanel);     //adds the panel to the frame
				

		/**
		 * INSTRUCTIONS
		 */
				//create a menu bar for the top of the window for
				//instructions
				JMenuBar menuBar = new JMenuBar();
				frame.setJMenuBar(menuBar);
				
				//create a menu 
				JMenu menuFile = new JMenu("Help");
				menuFile.setFont(font1);
				
				//create a menu item for in the menu bar 
				instructions = new JMenuItem("Instructions");
				instructions.setFont(font1);
				instructions.addActionListener(new java.awt.event.ActionListener() {
					@Override
					public void actionPerformed(java.awt.event.ActionEvent evt) 
					{
						JOptionPane.showMessageDialog(frame, "DATATYPES: Moderator Login - CHAR(10)\n"
														   + "           Email           - VARCHAR(30)\n"
								+ "\n How to Insert:\n"
								+ " All text fields must be filled with appropriate input\n (proper data"
								+ " types, no illegal characters, and the appropriate size.)\n"
								+ "Then, click the INSERT button to insert your data.\n\n"
								+ "How to Update: \n"
								+ " You can update any amount of the attributes for Manager. Simply\n"
								+ "fill the text fields with appropriate, legal data (proper data"
								+ "types, \n no illegal characters, and the appropriate size.) Make sure"
								+ "the WHERE text field is full. \n For example: 'heyBro@email.com'\n\n"
								+ "How to Delete: \n"
								+ "To delete a row of data from the table, simply ensure the WHERE\n"
								+ " text field has been given a direction. \nFor example: "
								+ " heyMan@email.com\n\n"
								+ "How to View: \n"
								+ " In order to view, the text field must be given a direction. For example:\n"
								+ " howAreYou@email.com."
								+ "This will display the row for you, in the RESULTS panel."
								+ " \n\nNOTICE: \n"
								+ "None of the text fields require semicolons or the beginnings of\n"
								+ " SQL statements. This is handled by the program.");

					}
				});

		/**
		 * INSERT PANEL
		 */
		//create our panel for the insert command
		JPanel insertPanel = new JPanel();
	
		//set a border around the panel to make it look pronounced
		insertPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(insertPanel);
		insertPanel.setLayout(new GridLayout(3,5));
	
				//creating a label for title of insert command
				JLabel lblInsert = new JLabel("INSERT");
				//setting font goes (Font, type, size)
				lblInsert.setFont(new Font("Serif", Font.BOLD, 40));
				//should insert at the top of insert panel
				//lblInsert.setBounds(26, 0, 300, 50);
				insertPanel.add(lblInsert);
				
				//these serve as spaces in GridLayout
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				
				
				//new label for the manager login
				JLabel lblModLog = new JLabel("Moderator Login:");
				lblModLog.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblModLog.setBounds(26, 75, 200, 50);
				insertPanel.add(lblModLog);
				
				insertModLogTextField = new JTextField();
				insertModLogTextField.setBounds(160, 75, (int)(width * .3), 50);
				insertModLogTextField.setFont(font1);
				insertPanel.add(insertModLogTextField);
				insertModLogTextField.setColumns(10);
				
				//another spacing label
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				
				
				//new label for mod login
				JLabel lblEmail = new JLabel("Email:");
				lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblEmail.setBounds(26, 110, 200, 50);
				insertPanel.add(lblEmail);
				
				insertModLogTextField = new JTextField();
				insertModLogTextField.setColumns(10);
				insertModLogTextField.setBounds(160, 150, (int)(width * .3), 50);
				insertModLogTextField.setFont(font1);
				insertPanel.add(insertModLogTextField);
				
				//label spacing
				insertPanel.add(new JLabel(""));
				
				btnInsert = new JButton("INSERT");
				btnInsert.addActionListener(this);
				btnInsert.setBounds((int)(width - (width * .1)), (int)(height * .08), 300, 50);
				insertPanel.add(btnInsert);
				
				insertPanel.add(new JLabel(""));
				

		/**
		 * UPDATE PANEL		
		 */
		JPanel updatePanel = new JPanel();
		updatePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(updatePanel);
		updatePanel.setLayout(new GridLayout(4,5));
		
				//creating a label for title of insert command
				JLabel lblUpdate = new JLabel("UPDATE");
				//setting font goes (Font, type, size)
				lblUpdate.setFont(new Font("Serif", Font.BOLD, 40));
				//should insert at the top of insert panel
				//lblInsert.setBounds(26, 0, 300, 50);
				updatePanel.add(lblUpdate);
				
				//these serve as spaces in GridLayout
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				
				
				//new label for the manager login
				JLabel lblModLogUpdate = new JLabel("Moderator Login:");
				lblModLogUpdate.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblModLogUpdate.setBounds(26, 75, 200, 50);
				updatePanel.add(lblModLogUpdate);
				
				updateModLoginTextField = new JTextField();
				updateModLoginTextField.setBounds(160, 75, (int)(width * .3), 50);
				updateModLoginTextField.setFont(font1);
				updatePanel.add(updateModLoginTextField);
				updateModLoginTextField.setColumns(10);//
				
				//another spacing label
				updatePanel.add(new JLabel(""));
		
				//where text area will be a text area because we can have long where clauses
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(26, 55, width-50, (int)(height * .4));
				updatePanel.add(scrollPane);
				
				updateWhereTextArea = new JTextArea();
				updateWhereTextArea.setFont(new Font("Tahoma", Font.PLAIN, 15));
				updateWhereTextArea.setWrapStyleWord(true);
				updateWhereTextArea.setLineWrap(true);
				updateWhereTextArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				updateWhereTextArea.setBorder(new EmptyBorder(20, 20, 20, 20));
				scrollPane.setViewportView(updateWhereTextArea);
				updateWhereTextArea.setEditable(true);
				
				//spacing
				updatePanel.add(new JLabel(""));
				
				
				//new label for mod login
				JLabel lblEmailUpdate = new JLabel("Email:");
				lblEmailUpdate.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblEmailUpdate.setBounds(26, 110, 200, 50);
				updatePanel.add(lblEmailUpdate);
				
				//text field for mod login
				updateEmailTextField = new JTextField();
				updateEmailTextField.setColumns(10);
				updateEmailTextField.setFont(font1);
				updatePanel.add(updateEmailTextField);
				updateEmailTextField.setBounds(160, 150, (int)(width * .3), 50);
				updatePanel.add(updateEmailTextField);
				
				//label spacing
				updatePanel.add(new JLabel(""));
				
				btnUpdate = new JButton("UPDATE");
				btnUpdate.addActionListener(this);
				btnUpdate.setBounds((int)(width - (width * .1)), (int)(height * .08), 300, 50);
				updatePanel.add(btnUpdate);
				
				updatePanel.add(new JLabel(""));//here
				
				
				
		/**
		 * DELETE PANEL
		 */
		JPanel deletePanel = new JPanel();
		deletePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(deletePanel);
		deletePanel.setLayout(new GridLayout(1,2));
		
				//creating our two panels
				JPanel leftDeletePanel = new JPanel(new BorderLayout());
				JPanel rightDeletePanel = new JPanel(new BorderLayout());
				
				//--First we add and fill the left panel
				deletePanel.add(leftDeletePanel);
				//adding the title for the delete panel
				JLabel lblDelete = new JLabel("DELETE");
				lblDelete.setFont(new Font("Serif", Font.BOLD, 40));
				lblDelete.setBounds(26, 0, 300, 50);
				leftDeletePanel.add(lblDelete, BorderLayout.NORTH);
				
				whereDeleteTextField = new JTextField();
				whereDeleteTextField.setColumns(10);     //changing shit here dowg!!!!!!!!1
				whereDeleteTextField.setFont(font1);
				leftDeletePanel.add(whereDeleteTextField, BorderLayout.CENTER);
				
				//spacing labels
				leftDeletePanel.add(new JLabel(" "), BorderLayout.EAST);
				leftDeletePanel.add(new JLabel(" "), BorderLayout.WEST);
				leftDeletePanel.add(new JLabel(" "), BorderLayout.SOUTH);
				
				
				
				//--Then we add and fill the right panel
				deletePanel.add(rightDeletePanel);
				//this button is the only thing within the right delete panel
				btnDelete = new JButton("DELETE");
				btnDelete.addActionListener(this);
				btnDelete.setBounds((int)(width - (width * .1)), (int)(height * .08), 300, 50);
				rightDeletePanel.add(btnDelete, BorderLayout.CENTER);
				
				rightDeletePanel.add(new JLabel(" "), BorderLayout.SOUTH);
				rightDeletePanel.add(new JLabel(" "), BorderLayout.NORTH);
				rightDeletePanel.add(new JLabel(" "), BorderLayout.WEST);
				rightDeletePanel.add(new JLabel(" "), BorderLayout.EAST);
				
				
		/**
		 * VIEW PANEL
		 */
		JPanel viewPanel = new JPanel();
		viewPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(viewPanel);
		viewPanel.setLayout(new GridLayout(1,2));
		
				//creating our two panels
				JPanel leftViewPanel = new JPanel(new BorderLayout());
				JPanel rightViewPanel = new JPanel(new BorderLayout());
				
				//--First we add and fill the left panel
				viewPanel.add(leftViewPanel);
				
				//adding the title for the delete panel
				JLabel lblView = new JLabel("VIEW");
				lblView.setFont(new Font("Serif", Font.BOLD, 40));
				lblView.setBounds(26, 0, 300, 50);
				leftViewPanel.add(lblView, BorderLayout.NORTH);
				
				selectViewTextField = new JTextField();
				selectViewTextField.setColumns(10);
				leftViewPanel.add(selectViewTextField, BorderLayout.CENTER);
				
				//spacing labels
				leftViewPanel.add(new JLabel(" "), BorderLayout.EAST);
				leftViewPanel.add(new JLabel(" "), BorderLayout.WEST);
				leftViewPanel.add(new JLabel(" "), BorderLayout.SOUTH);
				
				
				//--Then we add and fill the right panel
				viewPanel.add(rightViewPanel);
				//this button is the only thing within the right delete panel
				btnView = new JButton("VIEW");
				btnView.addActionListener(this);
				btnView.setBounds((int)(width - (width * .1)), (int)(height * .08), 300, 50);
				rightViewPanel.add(btnView, BorderLayout.CENTER);
				
				rightViewPanel.add(new JLabel(" "), BorderLayout.SOUTH);
				rightViewPanel.add(new JLabel(" "), BorderLayout.NORTH);
				rightViewPanel.add(new JLabel(" "), BorderLayout.WEST);
				rightViewPanel.add(new JLabel(" "), BorderLayout.EAST);
				
				
		/**
		 * RESULTS PANEL
		 */
		JPanel resultsPanel = new JPanel();
		resultsPanel.setBounds(0, (int)(height * .6), width, (int)(height * .48));
		mainPanel.add(resultsPanel);
		resultsPanel.setLayout(new GridLayout());
		
				//adding the title for the results panel
				JLabel lblResults = new JLabel("RESULTS");
				lblResults.setFont(new Font("Serif", Font.BOLD, 40));
				lblResults.setBounds(26, 0, 300, 50);
				resultsPanel.add(lblResults, BorderLayout.NORTH);
				
				
				//where text area will be a text area because we can have long where clauses
				JScrollPane scrollPaneResults = new JScrollPane();
				scrollPaneResults.setBounds(26, 55, width-50, (int)(height * .4));
				resultsPanel.add(scrollPaneResults);
				
				resultTextArea = new JTextArea();
				resultTextArea.setFont(new Font("Serif", Font.PLAIN, 15));
				resultTextArea.setWrapStyleWord(true);
				resultTextArea.setLineWrap(true);
				resultTextArea.setBorder(new EmptyBorder(20, 20, 20, 20));
				scrollPaneResults.setViewportView(resultTextArea);
				resultTextArea.setEditable(false);
	}
	
	
	
	/**
	 * Actions performed when buttons are clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnInsert)
		{
			if(insertModLogTextField.getText().equals("") || insertModLogTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				//utilizing PreparedStatement to insert value.
				// '?' is placeholder
				String insertData = new String("INSERT INTO MODERATOR (mod_login, email) VALUES (?,?)");
				
				try
				{
					//time to connect to database
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					//get our data
					statement.setString(2, insertModLogTextField.getText());
					statement.setString(3, insertEmailTextField.getText());
					
					//execute the update and inform user
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");
					
				} 
				catch (SQLException e1) //
				{
					JOptionPane.showMessageDialog(null, "An error has occured."
							+ "\nlogin must be unique! \nPlease Retry.");
				}
				
				//clearing the text fields
				insertModLogTextField.setText("");
				insertEmailTextField.setText("");
				JOptionPane.showMessageDialog(null, "Update Successful");
				
			}
				
			
		} //update button
		else if(e.getSource() == btnUpdate)
		{
			if(updateModLoginTextField.getText().equals("") || updateEmailTextField.getText().equals("") ||
					updateWhereTextArea.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				//using prepared statement for updating values
				//'?' is placeholder for data
				String insertData = new String("UPDATE MODERATOR SET mod_login = ?, email = ?"
		        		+ "WHERE mod_login = ?");
				
				try
				{
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					//see if a matching man_login exists. If not, throw exception
					String selectdata = new String("SELECT * FROM MODERATOR");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean mod_loginfound = false;
					while(rs.next())
					{
						String id = rs.getString("mod_login");
						if(id.equals(updateWhereTextArea.getText()))
						{
							mod_loginfound = true;
						}

					}
					if(!mod_loginfound)
					{
						throw new Exception();
					}
					
					statement.setString(2, updateModLoginTextField.getText());
					statement.setString(3, updateEmailTextField.getText());
					statement.setString(6, updateWhereTextArea.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");
				}
				catch(SQLException e1)
				{
					String s = "An error has occured.\n\nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
				} catch (Exception notfound)
		        {
					JOptionPane.showMessageDialog(null, "Moderator Login does not exist\n\nTry Again!");
		        }
				
				updateModLoginTextField.setText("");
				updateEmailTextField.setText("");
				updateWhereTextArea.setText("");
				JOptionPane.showMessageDialog(null, "Update Successful");
			}
				
			
		} //delete button
		else if(e.getSource() == btnDelete)
		{
			if(whereDeleteTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Field is Empty");
			}
			else
			{
				//'?' is a placeholder for data
				String insertData = new String("DELETE FROM MODERATOR WHERE mod_login = ?");
				
				try
				{
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					//see if there exists a login which the user is trying to update 
					String selectdata = new String("SELECT * FROM MODERATOR");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean whereFound = false;
					while(rs.next())
					{
						String login = rs.getString("mod_login");
						if(login.equals(whereDeleteTextField.getText()))
						{
							whereFound = true;
						}
					}
					if(!whereFound)
					{
						throw new Exception();
					}
					
					statement.setString(1, whereDeleteTextField.getText());
					JOptionPane.showMessageDialog(null, "Update Successful");
					//execute the update
					statement.executeUpdate();

				}
				catch (SQLException e1)
				{
					String s = "An error has occured.\n\nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
				} 
				catch (Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "Cannot delete! \nModerator Login does not exist");
				}

				//reset fields
				whereDeleteTextField.setText("");
			}
			
			
		}//view button
		else if(e.getSource() == btnView)
		{
			if(selectViewTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Fields is Empty");
			}
			else
			{	//'?' is a data placeholder
				String selectData = "SELECT * FROM MODERATOR WHERE mod_login = ?";
				
				try
				{
					PreparedStatement statement = conn.prepareStatement(selectData);
				
					if(selectViewTextField.getText().equals("***ALL"))
					{
						String select = "SELECT * FROM MODERATOR";
						Statement state = conn.createStatement();
						ResultSet rs = state.executeQuery(select);
						
						resultTextArea.setText("tMod_login \t\tEmail\n");
						rs.beforeFirst();
						
						while(rs.next())
						{
				        	String mod_login = rs.getString("mod_login");
				        	String email = rs.getString("email");
				        	resultTextArea.append(mod_login+"\t\t" +email+"\n");
						}
			
					}
					else
					{
						//creating and filling view panel
						statement.setString(1, selectViewTextField.getText());
						ResultSet rs = statement.executeQuery();
						
						//check if the login even exists in the database
						boolean viewFound = false;
						while(rs.next())
						{
							String modLogin = rs.getString("mod_login");
							if(modLogin.equals(selectViewTextField.getText()))
							{
								viewFound = true;
							}
						}
						if(!viewFound)
						{
							throw new Exception();
						}

						resultTextArea.setText("Mod_login \t\tEmail\n");
						rs.beforeFirst();
						
						while (rs.next()) 
						{
				        	String mod_login = rs.getString("mod_login");
				        	String email = rs.getString("email");
				        	resultTextArea.append(mod_login+"\t\t" +email+"\n");
						}
					}
					
					
				} catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(null, "SQL Error");
				} catch (Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "Login not found\nTry Again!");
				}
				
				//reset text field
				selectViewTextField.setText("");
				
				
			}
		
		}
	}
	

	/**
	 * Sets the size of the frame using methods included within
	 * the extended class UI_setup. This keeps our frame uniform
	 * with the others
	 */
	@Override
	public void setFrameSize()
	{
		this.width = getFrameWidth();
		this.height = getFrameHeight();
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