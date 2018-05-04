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

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

/**
 * this class will create a jframe that will interact with the player table
 * within the database
 * 
 * @author Denny Fleagle
 *
 */
public class Frame_AbilityUI extends Application implements Frame, ActionListener
{
	private JFrame frame;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnView;
	private JMenuItem miInst;
	private JTextField aidtxt;
	private JTextField timetxt;
	private JTextField ratetxt;
	private JTextField durationtxt;
	private JTextField amounttxt;
	private JTextField targettxt;
	private JTextField typetxt;
	private JTable table;
	private JTextField upaidtxt;
	private JTextField uptimetxt;
	private JTextField upratetxt;
	private JTextField updurationtxt;
	private JTextField upamounttxt;
	private JTextField uptargettxt;
	private JTextField uptypetxt;
	private JTextField upwheretxt;
	private JTextField delwheretxt;
	private JTextField viewwheretxt;

	private int width, height;

	public static void main(String []args)
	{
		new Frame_AbilityUI();
	}

	/**
	 * Create the application.
	 */
	public Frame_AbilityUI()
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
		frame = new JFrame("Ability Table");
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
						+ "\nCreate new Ability by entering all the data needed. AID must be unique. "
						+ "\nIf you enter a AID that exists you will receive an error. \n\n"
						+ "UPDATE "
						+ "\nUpdate a Ability that already exists. The WHERE AID = field must match an "
						+ "existing AID. \nFuture login must be unique you will be warned if not. Aditionally,"
						+ "if there is an item already utilizing the ability that item will \nneed to lose the "
						+ "ability first before updating it.\n\n"
						+ "DELETE"
						+ "\nEnter existing Ability ID in the WHERE AID = field. \nWarning the record will "
						+ "be deleted from the database. If the AID does \nnot exist you will recieve "
						+ "a prompt. Additionally, if the Ability is being utilized by another item you "
						+ "will need to remove the \nability from that item before deleting. \n\n"
						+ "VIEW"
						+ "\nEnter ***ALL to view all data in table (shown in RESULTS section below), otherwise "
						+ "enter Ability AID that you wish to view. \nIf no match is found you will be prompted. ";

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

		JLabel lblAID = new JLabel("AID");
		c.gridx = 0;
		c.gridy = 1;
		mainPanel.add(lblAID, c);

		JLabel lblTime = new JLabel("repetition_time");
		c.gridx = 0; 
		c.gridy = 2;
		mainPanel.add(lblTime, c);

		JLabel lblRate = new JLabel("repetition_rate");
		c.gridx = 0; 
		c.gridy = 3;
		mainPanel.add(lblRate, c);

		aidtxt = new JTextField();
		aidtxt.setColumns(20);
		c.gridx = 1;
		c.gridy = 1;
		mainPanel.add(aidtxt, c);

		timetxt = new JTextField();
		timetxt.setColumns(20);
		c.gridx = 1;
		c.gridy = 2;
		mainPanel.add(timetxt, c);

		ratetxt = new JTextField();
		ratetxt.setColumns(20);
		c.gridx = 1;
		c.gridy = 3;
		mainPanel.add(ratetxt, c);

		JLabel lblduration = new JLabel("duration");
		c.gridx = 2;
		c.gridy = 1;
		mainPanel.add(lblduration, c);

		JLabel lblamount = new JLabel("amount");
		c.gridx = 2;
		c.gridy = 2;
		mainPanel.add(lblamount, c);

		JLabel lbltargets = new JLabel("targets");
		c.gridx = 2;
		c.gridy = 3;
		mainPanel.add(lbltargets, c);

		durationtxt = new JTextField();
		durationtxt.setColumns(20);
		c.gridx = 3;
		c.gridy = 1;
		mainPanel.add(durationtxt, c);

		amounttxt = new JTextField();
		amounttxt.setColumns(20);
		c.gridx = 3;
		c.gridy = 2;
		mainPanel.add(amounttxt, c);

		targettxt = new JTextField();
		targettxt.setColumns(20);
		c.gridx = 3;
		c.gridy = 3;
		mainPanel.add(targettxt, c);

		JLabel lbltype = new JLabel("type");
		c.gridx = 4;
		c.gridy = 1;
		mainPanel.add(lbltype, c);

		typetxt = new JTextField();
		typetxt.setColumns(20);
		c.gridx = 5;
		c.gridy = 1;
		mainPanel.add(typetxt, c);

		btnInsert = new JButton("INSERT");
		btnInsert.addActionListener(this);
		btnInsert.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
		c.gridx = 6;
		c.gridy = 2;
		c.insets = new Insets(0,0,0,10);
		mainPanel.add(btnInsert, c);
		c.insets = new Insets(0,10,0,10);

		//update 
		JLabel uplblUpdate = new JLabel("UPDATE");
		uplblUpdate.setFont(new Font("Serif", Font.BOLD, (int)(height * .03)));
		c.gridx = 0;
		c.gridy = 4;
		mainPanel.add(uplblUpdate, c);

		JLabel uplblAID = new JLabel("AID");
		c.gridx = 0;
		c.gridy = 5;
		mainPanel.add(uplblAID, c);

		JLabel uplblTime = new JLabel("repetition_time");
		c.gridx = 0; 
		c.gridy = 6;
		mainPanel.add(uplblTime, c);

		JLabel uplblRate = new JLabel("repetition_rate");
		c.gridx = 0; 
		c.gridy = 7;
		mainPanel.add(uplblRate, c);

		upaidtxt = new JTextField();
		upaidtxt.setColumns(20);
		c.gridx = 1;
		c.gridy = 5;
		mainPanel.add(upaidtxt, c);

		uptimetxt = new JTextField();
		uptimetxt.setColumns(20);
		c.gridx = 1;
		c.gridy = 6;
		mainPanel.add(uptimetxt, c);

		upratetxt = new JTextField();
		upratetxt.setColumns(20);
		c.gridx = 1;
		c.gridy = 7;
		mainPanel.add(upratetxt, c);

		JLabel uplblduration = new JLabel("duration");
		c.gridx = 2;
		c.gridy = 5;
		mainPanel.add(uplblduration, c);

		JLabel uplblamount = new JLabel("amount");
		c.gridx = 2;
		c.gridy = 6;
		mainPanel.add(uplblamount, c);

		JLabel uplbltargets = new JLabel("targets");
		c.gridx = 2;
		c.gridy = 7;
		mainPanel.add(uplbltargets, c);

		updurationtxt = new JTextField();
		updurationtxt.setColumns(20);
		c.gridx = 3;
		c.gridy = 5;
		mainPanel.add(updurationtxt, c);

		upamounttxt = new JTextField();
		upamounttxt.setColumns(20);
		c.gridx = 3;
		c.gridy = 6;
		mainPanel.add(upamounttxt, c);

		uptargettxt = new JTextField();
		uptargettxt.setColumns(20);
		c.gridx = 3;
		c.gridy = 7;
		mainPanel.add(uptargettxt, c);

		JLabel uplbltype = new JLabel("type");
		c.gridx = 4;
		c.gridy = 5;
		mainPanel.add(uplbltype, c);

		uptypetxt = new JTextField();
		uptypetxt.setColumns(20);
		c.gridx = 5;
		c.gridy = 5;
		mainPanel.add(uptypetxt, c);

		JLabel upWhere = new JLabel("WHERE AID =");
		c.gridx = 4;
		c.gridy = 6;
		mainPanel.add(upWhere, c);

		upwheretxt = new JTextField();
		upwheretxt.setColumns(20);
		c.gridx = 5;
		c.gridy = 6;
		c.gridwidth = 2;
		mainPanel.add(upwheretxt, c);

		btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(this);
		btnUpdate.setPreferredSize(new Dimension((int)(width * .1),(int)(height * .05)));
		c.gridx = 6;
		c.gridy = 7;
		c.insets = new Insets(0,0,0,10);
		mainPanel.add(btnUpdate, c);
		c.insets = new Insets(0,10,0,10);
		// delete

		JLabel lblDelete = new JLabel("DELETE");
		lblDelete.setFont(new Font("Serif", Font.BOLD, (int)(height * .03)));
		c.gridx = 0; 
		c.gridy = 8;
		mainPanel.add(lblDelete, c);

		JLabel lbldeleteWhere = new JLabel("WHERE AID =");
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

		JLabel lblviewSelect = new JLabel("SELECT AID =");
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
			if(aidtxt.getText().equals("") || timetxt.getText().equals("") ||
					ratetxt.getText().equals("") || durationtxt.getText().equals("") || 
					amounttxt.getText().equals("") || targettxt.getText().equals("") ||
					typetxt.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				// Using a PreparedStatement to insert a value (best option when providing values
				// from variables).
				// Use place holders '?' to mark where I am going to provide the data.
				String insertData = new String("INSERT INTO ABILITY (AID, repetition_time, repetition_rate, duration, amount, targets, type) "
						+ "VALUES (?,?,?,?,?,?,?)");

				try
				{ 
					PreparedStatement statement = conn.prepareStatement(insertData);

					statement.setString(1, aidtxt.getText());
					statement.setString(2, timetxt.getText());
					statement.setString(3, ratetxt.getText());
					statement.setString(4, durationtxt.getText());
					statement.setString(5, amounttxt.getText());
					statement.setString(6, targettxt.getText());
					statement.setString(7, typetxt.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");

				} catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(null, "SQL Exception");
				}

				//clear the text fields
				aidtxt.setText("");
				timetxt.setText("");
				ratetxt.setText("");
				durationtxt.setText("");
				amounttxt.setText("");
				targettxt.setText("");
				typetxt.setText("");

			}

		}
		else if(e.getSource() == btnUpdate)
		{
			if(upaidtxt.getText().equals("") || uptimetxt.getText().equals("") ||
					upratetxt.getText().equals("") || updurationtxt.getText().equals("") ||
					upwheretxt.getText().equals("") || upamounttxt.getText().equals("") || 
					uptargettxt.getText().equals("") || uptypetxt.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				// Using a PreparedStatement to insert a value (best option when providing values
				// from variables).
				// Use place holders '?' to mark where I am going to provide the data.
				String updateData = new String("UPDATE ABILITY SET AID = ?, duration = ?, type = ?, "
						+ "repetition_time = ?, amount = ?, repetition_rate = ?, targets = ? WHERE AID = ?");
				try
				{
					PreparedStatement statement = conn.prepareStatement(updateData);

					//see if there exists a CR_ID which the user is trying to update
					//if not throw an exception
					String selectdata = new String("SELECT * FROM ABILITY");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);

					boolean idfound = false;
					while(rs.next())
					{
						String id = rs.getString("AID");
						if(id.equals(upwheretxt.getText()))
						{
							idfound = true;
						}

					}
					if(!idfound)
					{
						throw new Exception();
					}

					statement.setString(1, upaidtxt.getText());
					statement.setString(2, updurationtxt.getText());
					statement.setString(3, uptypetxt.getText());
					statement.setString(4, uptimetxt.getText());
					statement.setString(5, upamounttxt.getText());
					statement.setString(6, upratetxt.getText());
					statement.setString(7, uptargettxt.getText());
					statement.setString(8, upwheretxt.getText());
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");

				} catch (MySQLIntegrityConstraintViolationException cannot)
				{
					JOptionPane.showMessageDialog(null, "Cannot update!\nSomething else relys on this ability");
				} catch (SQLException e1)
				{
					String s = "An error has occured.\n\nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);

				} catch (Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "AID does not exist\n\nTry Again!");
				}

				//clear the text fields
				upaidtxt.setText("");
				uptimetxt.setText("");
				upratetxt.setText("");
				updurationtxt.setText("");
				upamounttxt.setText("");
				uptargettxt.setText("");
				uptypetxt.setText("");
				upwheretxt.setText("");

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
				String deleteData = new String("DELETE FROM ABILITY WHERE AID = ?");

				try
				{
					PreparedStatement statement = conn.prepareStatement(deleteData);

					//see if there exists a login which the user is trying to update 
					//if not throw an exception 
					String selectdata = new String("SELECT * FROM ABILITY");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean found = false;
					while(rs.next())
					{
						String id = rs.getString("AID");
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
					String s = "Cannot delete ability in use by object.\n\nPlease Retry.";
					JOptionPane.showMessageDialog(null, s);
				} catch(Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "Cannot delete! \nAID does not exist");
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

				// select statement 
				String selectData = "SELECT * FROM ABILITY WHERE AID = ?";

				try
				{
					PreparedStatement statement = conn.prepareStatement(selectData);

					if(viewwheretxt.getText().equals("***ALL"))
					{
						String select = "SELECT * FROM ABILITY";
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
							String id = rs.getString("AID");
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
					JOptionPane.showMessageDialog(null, "AID not found\nTry Again!");
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
