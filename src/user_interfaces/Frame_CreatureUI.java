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
public class Frame_CreatureUI extends Application implements Frame, ActionListener
{
	private JFrame frame;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnView;
	private JMenuItem miInst;
	private JTextField creaturenametxt;
	private JTextField creatureidtxt;
	private JTextField lidtxt;
	private JTextField maxhptxt;
	private JTextField currenthptxt;
	private JTextField strengthtxt;
	private JTextField protecttxt;
	private JTextField staminatxt;
	private JTable table;
	private JTextField upcreaturenametxt;
	private JTextField upcreatureidtxt;
	private JTextField uplidtxt;
	private JTextField upmaxhptxt;
	private JTextField upcurrenthptxt;
	private JTextField upstrengthtxt;
	private JTextField upprotecttxt;
	private JTextField upstaminatxt;
	private JTextField upwheretxt;
	private JTextField delwheretxt;
	private JTextField viewwheretxt;
	
	private int width, height;
	
	public static void main(String []args)
	{
		new Frame_CreatureUI();
	}

	/**
	 * Create the application.
	 */
	public Frame_CreatureUI()
	{
		setFrameSize();
		buildFrame();
		frame.setVisible(true);
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
		frame = new JFrame("Creature Table");
		frame.setSize(width, height);
		frame.setUndecorated(false);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
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
						+ "\nCreate new Creature by entering all the data needed. CR_ID must be unique. "
						+ "\nIf you enter a CR_ID that exists you will receive an error. \nAdditionally, L_ID "
						+ "must match a current location id. If you don't have a specific location use 1.\n\n"
						+ "UPDATE "
						+ "\nUpdate a Creature that already exists. The WHERE CR_ID = field must match an "
						+ "existing CR_ID. \nFuture CR_ID must be unique you will be warned if not. Additionally, "
						+ "you must enter a valid location id. \nIf you don't have a specific location use 1.\n\n"
						+ "DELETE"
						+ "\nEnter existing CR_ID in the WHERE CR_ID = field. \nWarning the record will "
						+ "be deleted from the database. If the CR_ID does \nnot exist you will recieve "
						+ "a prompt. \n\n"
						+ "VIEW"
						+ "\nEnter ***ALL to view all data in table (shown in RESULTS section below), otherwise "
						+ "enter CR_ID that you wish to view. \nIf no match is found you will be prompted. ";

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
				
				JLabel lblCN = new JLabel("Creature_name");
				c.gridx = 0;
				c.gridy = 1;
				mainPanel.add(lblCN, c);
				
				JLabel lblCRID = new JLabel("CR_ID");
				c.gridx = 0; 
				c.gridy = 2;
				mainPanel.add(lblCRID, c);
				
				JLabel lblLocation = new JLabel("L_ID");
				c.gridx = 0; 
				c.gridy = 3;
				mainPanel.add(lblLocation, c);
				
				creaturenametxt = new JTextField();
				creaturenametxt.setColumns(20);
				c.gridx = 1;
				c.gridy = 1;
				mainPanel.add(creaturenametxt, c);
				
				creatureidtxt = new JTextField();
				creatureidtxt.setColumns(20);
				c.gridx = 1;
				c.gridy = 2;
				mainPanel.add(creatureidtxt, c);
				
				lidtxt = new JTextField();
				lidtxt.setColumns(20);
				c.gridx = 1;
				c.gridy = 3;
				mainPanel.add(lidtxt, c);
				
				JLabel lblMaxhp = new JLabel("max_hp");
				c.gridx = 2;
				c.gridy = 1;
				mainPanel.add(lblMaxhp, c);
				
				JLabel lblCurrenthp = new JLabel("current_hp");
				c.gridx = 2;
				c.gridy = 2;
				mainPanel.add(lblCurrenthp, c);
				
				JLabel lblStrength = new JLabel("Strength");
				c.gridx = 2;
				c.gridy = 3;
				mainPanel.add(lblStrength, c);
				
				maxhptxt = new JTextField();
				maxhptxt.setColumns(20);
				c.gridx = 3;
				c.gridy = 1;
				mainPanel.add(maxhptxt, c);
								
				currenthptxt = new JTextField();
				currenthptxt.setColumns(20);
				c.gridx = 3;
				c.gridy = 2;
				mainPanel.add(currenthptxt, c);
				
				strengthtxt = new JTextField();
				strengthtxt.setColumns(20);
				c.gridx = 3;
				c.gridy = 3;
				mainPanel.add(strengthtxt, c);
				
				JLabel lblProtection = new JLabel("Protection");
				c.gridx = 4;
				c.gridy = 1;
				mainPanel.add(lblProtection, c);
				
				JLabel lblStamina = new JLabel("Stamina");
				c.gridx = 4;
				c.gridy = 2;
				mainPanel.add(lblStamina, c);
				
				staminatxt = new JTextField();
				staminatxt.setColumns(20);
				c.gridx = 5;
				c.gridy = 2;
				mainPanel.add(staminatxt, c);
				
				protecttxt = new JTextField();
				protecttxt.setColumns(20);
				c.gridx = 5;
				c.gridy = 1;
				mainPanel.add(protecttxt, c);
				
				btnInsert = new JButton("INSERT");
				btnInsert.addActionListener(this);
				btnInsert.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
				c.gridx = 6;
				c.gridy = 2;
				c.insets = new Insets(0,0,0,10);
				mainPanel.add(btnInsert, c);
				c.insets = new Insets(0,10,0,10);
		
//update 
				JLabel uplblInsert = new JLabel("UPDATE");
				uplblInsert.setFont(new Font("Serif", Font.BOLD, (int)(height * .03)));
				c.gridx = 0;
				c.gridy = 4;
				mainPanel.add(uplblInsert, c);
				
				JLabel uplblCN = new JLabel("Creature_name");
				c.gridx = 0;
				c.gridy = 5;
				mainPanel.add(uplblCN, c);
				
				JLabel uplblCRID = new JLabel("CR_ID");
				c.gridx = 0; 
				c.gridy = 6;
				mainPanel.add(uplblCRID, c);
				
				JLabel uplblLocation = new JLabel("L_ID");
				c.gridx = 0; 
				c.gridy = 7;
				mainPanel.add(uplblLocation, c);
				
				upcreaturenametxt = new JTextField();
				upcreaturenametxt.setColumns(20);
				c.gridx = 1;
				c.gridy = 5;
				mainPanel.add(upcreaturenametxt, c);
				
				upcreatureidtxt = new JTextField();
				upcreatureidtxt.setColumns(20);
				c.gridx = 1;
				c.gridy = 6;
				mainPanel.add(upcreatureidtxt, c);
				
				uplidtxt = new JTextField();
				uplidtxt.setColumns(20);
				c.gridx = 1;
				c.gridy = 7;
				mainPanel.add(uplidtxt, c);
				
				JLabel uplblMaxhp = new JLabel("max_hp");
				c.gridx = 2;
				c.gridy = 5;
				mainPanel.add(uplblMaxhp, c);
				
				JLabel uplblCurrenthp = new JLabel("current_hp");
				c.gridx = 2;
				c.gridy = 6;
				mainPanel.add(uplblCurrenthp, c);
				
				JLabel uplblStrength = new JLabel("Strength");
				c.gridx = 2;
				c.gridy = 7;
				mainPanel.add(uplblStrength, c);
				
				upmaxhptxt = new JTextField();
				upmaxhptxt.setColumns(20);
				c.gridx = 3;
				c.gridy = 5;
				mainPanel.add(upmaxhptxt, c);
				
				upcurrenthptxt = new JTextField();
				upcurrenthptxt.setColumns(20);
				c.gridx = 3;
				c.gridy = 6;
				mainPanel.add(upcurrenthptxt, c);
				
				upstrengthtxt = new JTextField();
				upstrengthtxt.setColumns(20);
				c.gridx = 3;
				c.gridy = 7;
				mainPanel.add(upstrengthtxt, c);
				
				JLabel uplblProtection = new JLabel("Protection");
				c.gridx = 4;
				c.gridy = 5;
				mainPanel.add(uplblProtection, c);
				
				JLabel uplblStamina = new JLabel("Stamina");
				c.gridx = 4;
				c.gridy = 6;
				mainPanel.add(uplblStamina, c);
				
				JLabel uplblWhere = new JLabel("WHERE CR_ID =");
				c.gridx = 4;
				c.gridy = 7;
				mainPanel.add(uplblWhere, c);
				
				upstaminatxt = new JTextField();
				upstaminatxt.setColumns(20);
				c.gridx = 5;
				c.gridy = 6;
				mainPanel.add(upstaminatxt, c);
				
				upprotecttxt = new JTextField();
				upprotecttxt.setColumns(20);
				c.gridx = 5;
				c.gridy = 5;
				mainPanel.add(upprotecttxt, c);
				
				upwheretxt = new JTextField();
				upwheretxt.setColumns(20);
				c.gridx = 5;
				c.gridy = 7;
				c.gridwidth = 2;
				mainPanel.add(upwheretxt, c);
				
				btnUpdate = new JButton("UPDATE");
				btnUpdate.addActionListener(this);
				btnUpdate.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
				c.gridx = 6;
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
				
				JLabel lbldeleteWhere = new JLabel("WHERE CR_ID =");
				c.gridx = 0; 
				c.gridy = 9;
				mainPanel.add(lbldeleteWhere, c);
				
				delwheretxt = new JTextField();
				delwheretxt.setColumns(100);
				c.gridx = 1;
				c.gridy = 9;
				c.gridwidth = 5;
				mainPanel.add(delwheretxt, c);
				
				btnDelete = new JButton("DELETE");
				btnDelete.addActionListener(this);
				btnDelete.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
				c.gridx = 6;
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
				
				JLabel lblviewSelect = new JLabel("SELECT CR_ID =");
				c.gridx = 0;
				c.gridy = 11;
				mainPanel.add(lblviewSelect, c);
				
				viewwheretxt = new JTextField();
				viewwheretxt.setColumns(100);
				c.gridx = 1;
				c.gridy = 11;
				c.gridwidth = 5;
				mainPanel.add(viewwheretxt, c);
				
				btnView = new JButton("VIEW");
				btnView.addActionListener(this);
				btnView.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
				c.gridx = 6;
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
			if(creaturenametxt.getText().equals("") || creatureidtxt.getText().equals("") ||
					currenthptxt.getText().equals("") || maxhptxt.getText().equals("") ||
					lidtxt.getText().equals("") || protecttxt.getText().equals("") ||
					strengthtxt.getText().equals("") || staminatxt.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				// Using a PreparedStatement to insert a value (best option when providing values
				// from variables).
				// Use place holders '?' to mark where I am going to provide the data.
				String insertData = new String("INSERT INTO CREATURE (creature_name, CR_ID, L_ID, max_hp, current_hp, strength, protection, stamina) "
						+ "VALUES (?,?,?,?,?,?,?,?)");
				try
				{
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					//see if there exists a CR_ID which the user is trying to update
					//if not throw an exception
					String selectdata = new String("SELECT * FROM LOCATION");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean lidfound = false;
					while(rs.next())
					{
						String lid = rs.getString("L_ID");
						
						if(lid.equals(lidtxt.getText()))
						{
							lidfound = true;
						}
					}
					if(!lidfound)
					{
						throw new NoSuchFieldException();
					}

					statement.setString(1, creaturenametxt.getText());
					statement.setString(2, creatureidtxt.getText());
					statement.setString(3, lidtxt.getText());
					statement.setString(4, maxhptxt.getText());
					statement.setString(5, currenthptxt.getText());
					statement.setString(6, strengthtxt.getText());
					statement.setString(7, protecttxt.getText());
					statement.setString(8, staminatxt.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");

				} catch (SQLException e1)
				{
					String s = "An error has occured.\nCR_ID must be unique! \nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
		
				} catch(NoSuchFieldException nolocationfound)
				{
					nolocationfound.printStackTrace();
					JOptionPane.showMessageDialog(null, "Location does not exist\n\nTry Again!");
				}

				creaturenametxt.setText("");
				creatureidtxt.setText("");
				maxhptxt.setText("");
				currenthptxt.setText("");
				lidtxt.setText("");
				strengthtxt.setText("");
				staminatxt.setText("");
				protecttxt.setText("");
			}
		}
					
		else if(e.getSource() == btnUpdate)
		{
			if(upcreaturenametxt.getText().equals("") || upcreatureidtxt.getText().equals("") ||
					upcurrenthptxt.getText().equals("") || upmaxhptxt.getText().equals("") ||
					upwheretxt.getText().equals("") || uplidtxt.getText().equals("") || 
					upstrengthtxt.getText().equals("") || upstaminatxt.getText().equals("") ||
					upprotecttxt.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				// Using a PreparedStatement to insert a value (best option when providing values
		        // from variables).
		        // Use place holders '?' to mark where I am going to provide the data.
				String updateData = new String("UPDATE CREATURE SET creature_name = ?, CR_ID = ?, L_ID = ?, "
						+ "max_hp = ?, current_hp = ?, strength = ?, protection = ?, stamina = ? WHERE CR_ID = ?");
				try
				{
					PreparedStatement statement = conn.prepareStatement(updateData);
					
					//see if there exists a CR_ID which the user is trying to update
					//if not throw an exception
					String selectdata = new String("SELECT * FROM CREATURE");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean cridfound = false;
					boolean lidfound = false;
					while(rs.next())
					{
						String id = rs.getString("CR_ID");
						if(id.equals(upwheretxt.getText()))
						{
							cridfound = true;
						}

					}
					if(!cridfound)
					{
						throw new Exception();
					}
					
					//see if there exists a CR_ID which the user is trying to update
					//if not throw an exception
					String selectLocation = new String("SELECT * FROM LOCATION");
					Statement stmt1 = conn.createStatement();
					ResultSet rs1 = stmt1.executeQuery(selectLocation);
					
					while(rs1.next())
					{
						String lid = rs1.getString("L_ID");
						
						if(lid.equals(uplidtxt.getText()))
						{
							lidfound = true;
						}
					}
					if(!lidfound)
					{
						throw new NoSuchFieldException();
					}
					
					statement.setString(1, upcreaturenametxt.getText());
					statement.setString(2, upcreatureidtxt.getText());
					statement.setString(3, uplidtxt.getText());
					statement.setString(4, upmaxhptxt.getText());
					statement.setString(5, upcurrenthptxt.getText());
					statement.setString(6, upstrengthtxt.getText());
					statement.setString(7, upprotecttxt.getText());
					statement.setString(8, upstaminatxt.getText());
					statement.setString(9, upwheretxt.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");
					
				} catch (SQLException e1)
				{
					String s = "An error has occured.\n\nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
					
				} catch(NoSuchFieldException nolocationfound)
				{
					JOptionPane.showMessageDialog(null, "Location does not exist\n\nTry Again!");
				} catch(Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "CR_ID does not exist\n\nTry Again!");
				}
				
				//clear the text fields
				upcreaturenametxt.setText("");
				upcreatureidtxt.setText("");
				upcurrenthptxt.setText("");
				upmaxhptxt.setText("");
				upwheretxt.setText("");
				upstrengthtxt.setText("");
				uplidtxt.setText("");
				upstaminatxt.setText("");
				upprotecttxt.setText("");
				
			}
		
		}
		else if(e.getSource() == btnDelete)
		{
			if(delwheretxt.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Field is Empty");
			}
			else
			{
				String deleteData = new String("DELETE FROM CREATURE WHERE CR_ID = ?");
				
				try
				{
					PreparedStatement statement = conn.prepareStatement(deleteData);
					
					//see if there exists a login which the user is trying to update 
					//if not throw an exception 
					String selectdata = new String("SELECT * FROM CREATURE");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean found = false;
					while(rs.next())
					{
						String id = rs.getString("CR_ID");
						if(id.equals(delwheretxt.getText()))
						{
							found = true;
						}
					}
					if(!found)
					{
						throw new Exception();
					}
					
					statement.setString(1, delwheretxt.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");
					
				} catch(SQLException e1)
				{
					String s = "An error has occured.\n\nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
				} catch(Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "Cannot delete! \nCR_ID does not exist");
				}
				
				delwheretxt.setText("");
				
			}
			
		}
		else if(e.getSource() == btnView)
		{
			if(viewwheretxt.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Fields is Empty");
			}
			else
			{	
				//dynamic vector that points to a dynamic vector that stores Strings 
				Vector<String> columnNames = new Vector<String>();
				//dynamic vector that points to a dynamic vector containing vectors that store objects
				Vector<Vector<Object>> data = new Vector<Vector<Object>>(); 
				
			    //select statement
				String selectData = "SELECT * FROM CREATURE WHERE CR_ID = ?";
				
				try
				{
					PreparedStatement statement = conn.prepareStatement(selectData);
					
					if(viewwheretxt.getText().equals("***ALL"))
					{
						String select = "SELECT * FROM CREATURE";
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
						statement.setString(1, viewwheretxt.getText());
						ResultSet rs = statement.executeQuery();
						
						//check if the login even exists in the database
						boolean found = false;
						while(rs.next())
						{
							String id = rs.getString("CR_ID");
							if(id.equals(viewwheretxt.getText()))
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
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "SQL Error");
				} catch (Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "CR_ID not found\nTry Again!");
				}
				
				viewwheretxt.setText("");
					
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
