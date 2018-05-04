package user_interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
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

/**
 * Class to create the UI for the container database
 * @author Gabriel Webbe
 *
 */
public class Frame_Container extends Application implements Frame, ActionListener
{
	//initialize our buttons
	private JFrame frame;
	private JButton btnInsert;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnView;
	//text fields for insert
	private JTextField insertCIDTextField;
	private JTextField insertVolLimTextField;
	private JTextField insertWeiLimTextField;
	private JTextField insertImageTextField;
	private JTextField insertDescTextField;
	//text fields for update
	private JTextField updateCIDTextField;
	private JTextField updateVolLimTextField;
	private JTextField updateWeiLimTextField;
	private JTextField updateImageTextField;
	private JTextField updateDescTextField;
	private JTextArea updateWhereTextArea;
	//fields for delete and view
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
	public Frame_Container()
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
	 * Method to build the frame and contained panels
	 */
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
		frame.add(mainPanel,BorderLayout.CENTER);     //adds the panel to the frame
		
		
		
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
						JOptionPane.showMessageDialog(frame, "DATATYPES: CID             - INT(4)\n"
														   + "           Volume Limit    - DECIMAL(4,2)\n"
														   + "           Weight Limit    - DECIMAL(4,2)\n"
														   + "           Image           - BLOB(20M)\n"
														   + "           Description     - TEXT(1k)\n"
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
		insertPanel.setLayout(new GridLayout(6, 5));
		
				//creating a label for title of insert command
				JLabel lblInsert = new JLabel("INSERT");
				//setting font goes (Font, type, size)
				lblInsert.setFont(new Font("Serif", Font.BOLD, 40));
				//should insert at the top of insert panel
				insertPanel.add(lblInsert);
				
				//these serve as spaces in GridLayout
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				
				
				//new label for the CID
				JLabel lblCID = new JLabel("CID:");
				lblCID.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblCID.setBounds(26, 75, 200, 50);
				insertPanel.add(lblCID);
				
				insertCIDTextField = new JTextField();
				insertCIDTextField.setBounds(160, 75, (int)(width * .3), 50);
				insertCIDTextField.setFont(font1);
				insertPanel.add(insertCIDTextField);
				insertCIDTextField.setColumns(10);
				
				//another spacing label
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				
				
				//new label for volume limit
				JLabel lblVolLim = new JLabel("Volume Limit:");
				lblVolLim.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblVolLim.setBounds(26, 110, 200, 50);
				insertPanel.add(lblVolLim);
				
				insertVolLimTextField = new JTextField();
				insertVolLimTextField.setColumns(10);
				insertVolLimTextField.setBounds(160, 150, (int)(width * .3), 50);
				insertVolLimTextField.setFont(font1);
				insertPanel.add(insertVolLimTextField);
				
				//label spacing
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));

				
				//new label for weight limit
				JLabel lblWeiLim = new JLabel("Weight Limit:");
				lblWeiLim.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblWeiLim.setBounds(26, 135,200, 50);
				insertPanel.add(lblWeiLim);
				
				insertWeiLimTextField = new JTextField();
				insertWeiLimTextField.setColumns(10);
				insertWeiLimTextField.setBounds((int)(width *.45), 75, (int)(width * .3), 50);
				insertWeiLimTextField.setFont(font1);
				insertPanel.add(insertWeiLimTextField);
				
				//label spacing
				insertPanel.add(new JLabel(""));
			
				btnInsert = new JButton("INSERT");
				btnInsert.addActionListener(this);
				btnInsert.setBounds((int)(width - (width * .1)), (int)(height * .08), 300, 50);
				insertPanel.add(btnInsert);	
				
				insertPanel.add(new JLabel(""));
			
				
				//new label for image
				JLabel lblImage = new JLabel("Image:");
				lblImage.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblImage.setBounds(26, 135,200, 50);
				insertPanel.add(lblImage);
				
				insertImageTextField = new JTextField();
				insertImageTextField.setColumns(10);
				insertImageTextField.setBounds(160, 150, (int)(width * .3), 50);
				insertImageTextField.setFont(font1);
				insertPanel.add(insertImageTextField);
				
				//label spacing
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				
				
				//new label for image
				JLabel lblDesc = new JLabel("Image:");
				lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblDesc.setBounds(26, 135,200, 50);
				insertPanel.add(lblDesc);
				
				insertDescTextField = new JTextField();
				insertDescTextField.setColumns(10);
				insertDescTextField.setBounds(160, 150, (int)(width * .3), 50);
				insertDescTextField.setFont(font1);
				insertPanel.add(insertDescTextField);
				
				//label spacing
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));
				insertPanel.add(new JLabel(""));	
				
				
		/**
		 * UPDATE PANEL
		*/
		//create our panel for the insert command
		JPanel updatePanel = new JPanel();	
		//set a border around the panel to make it look pronounced
		updatePanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		mainPanel.add(updatePanel);
		updatePanel.setLayout(new GridLayout(6, 5));
						
				//creating a label for title of insert command
				JLabel lblUpdate = new JLabel("UPDATE");
				//setting font goes (Font, type, size)
				lblUpdate.setFont(new Font("Serif", Font.BOLD, 40));
				//should insert at the top of insert panel
				updatePanel.add(lblUpdate);
				
				//these serve as spaces in GridLayout
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				
				
				//new label for the CID
				JLabel lblUCID = new JLabel("CID:");
				lblUCID.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblUCID.setBounds(26, 75, 200, 50);
				updatePanel.add(lblUCID);
				
				updateCIDTextField = new JTextField();
				updateCIDTextField.setBounds(160, 75, (int)(width * .3), 50);
				updateCIDTextField.setFont(font1);
				updatePanel.add(updateCIDTextField);
				updateCIDTextField.setColumns(10);
				
				//another spacing label
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				
				
				//new label for volume limit
				JLabel lblUVolLim = new JLabel("Volume Limit:");
				lblUVolLim.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblUVolLim.setBounds(26, 110, 200, 50);
				insertPanel.add(lblUVolLim);
				
				updateVolLimTextField = new JTextField();
				updateVolLimTextField.setColumns(10);
				updateVolLimTextField.setBounds(160, 150, (int)(width * .3), 50);
				updateVolLimTextField.setFont(font1);
				updatePanel.add(updateVolLimTextField);
				
				//label spacing
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
		
				//new label for weight limit
				JLabel lblUWeiLim = new JLabel("Weight Limit:");
				lblUWeiLim.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblUWeiLim.setBounds(26, 135,200, 50);
				updatePanel.add(lblUWeiLim);
				
				updateWeiLimTextField = new JTextField();
				updateWeiLimTextField.setColumns(10);
				updateWeiLimTextField.setBounds((int)(width *.45), 75, (int)(width * .3), 50);
				updateWeiLimTextField.setFont(font1);
				updatePanel.add(updateWeiLimTextField);
				
				//label spacing
				updatePanel.add(new JLabel(""));
			
				btnUpdate = new JButton("UPDATE");
				btnUpdate.addActionListener(this);
				btnUpdate.setBounds((int)(width - (width * .1)), (int)(height * .08), 300, 50);
				updatePanel.add(btnUpdate);		
				
				updatePanel.add(new JLabel(""));
			
				
				//new label for image
				JLabel lblUImage = new JLabel("Image:");
				lblUImage.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblUImage.setBounds(26, 135,200, 50);
				updatePanel.add(lblUImage);
				
				updateImageTextField = new JTextField();
				updateImageTextField.setColumns(10);
				updateImageTextField.setBounds(160, 150, (int)(width * .3), 50);
				updateImageTextField.setFont(font1);
				updatePanel.add(updateImageTextField);
				
				//label spacing
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				
				
				//new label for image
				JLabel lblUDesc = new JLabel("Image:");
				lblUDesc.setFont(new Font("Tahoma", Font.PLAIN, 30));
				lblUDesc.setBounds(26, 135,200, 50);
				updatePanel.add(lblUDesc);
				
				updateDescTextField = new JTextField();
				updateDescTextField.setColumns(10);
				updateDescTextField.setBounds(160, 150, (int)(width * .3), 50);
				updateDescTextField.setFont(font1);
				updatePanel.add(updateDescTextField);
				
				//label spacing
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				updatePanel.add(new JLabel(""));
				
				
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
				whereDeleteTextField.setColumns(10);
				whereDeleteTextField.setFont(font1);
				leftDeletePanel.add(whereDeleteTextField, BorderLayout.CENTER);
				
				//spacing labels
				leftDeletePanel.add(new JLabel(" "), BorderLayout.EAST);
				leftDeletePanel.add(new JLabel(" "), BorderLayout.WEST);
				leftDeletePanel.add(new JLabel(" "), BorderLayout.SOUTH);

				
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
	
	
	
	
	//----------------------------------------------------
	/**
	 * Actions performed when buttons are clicked
	 *  This includes SQL control
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnInsert)
		{
			if(insertCIDTextField.getText().equals("") || insertVolLimTextField.getText().equals("") ||
				insertWeiLimTextField.getText().equals("") || insertImageTextField.getText().equals("") ||
				insertDescTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				//utilizing PreparedStatement to insert value.
				// '?' is placeholder
				String insertData = new String("INSERT INTO CONTAINER (CID, volume_limit, weight_limit, image, description)"
						+ " VALUES (?,?,?,?,?)");
				
				try
				{
					//time to connect to database
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					//get our data
					statement.setString(1, insertCIDTextField.getText());
					statement.setString(2, insertVolLimTextField.getText());
					statement.setString(3, insertWeiLimTextField.getText());
					statement.setString(4, insertImageTextField.getText());
					statement.setString(5, insertDescTextField.getText());
					
					//execute the update and inform user
					statement.executeUpdate();
					JOptionPane.showMessageDialog(null, "Update Successful");
				} 
				catch (SQLException e1) //
				{
					JOptionPane.showMessageDialog(null, "An error has occured."
							+ "\nCID must be unique! \nPlease Retry.");
				}
				
				//clearing the text fields
				insertCIDTextField.setText("");
				insertVolLimTextField.setText("");
				insertWeiLimTextField.setText("");
				insertImageTextField.setText("");
				insertDescTextField.setText("");
				JOptionPane.showMessageDialog(null, "Update Successful");
			}
				
			
		} //button update
		else if(e.getSource() == btnUpdate)
		{
			if(updateCIDTextField.getText().equals("") || updateVolLimTextField.getText().equals("") ||
				updateWeiLimTextField.getText().equals("") || updateWhereTextArea.getText().equals("") ||
				updateImageTextField.getText().equals("") || updateDescTextField.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "One or More Fields Are Empty");
			}
			else
			{
				//using prepared statement for updating values
				//'?' is placeholder for data
				String insertData = new String("UPDATE CONTAINER SET CID = ?, volume_limit = ?,"
						+ " weight_limit, image, description = ?, WHERE CID = ?");
				
				try
				{ 
					PreparedStatement statement = conn.prepareStatement(insertData);
					//see if a matching man_login exists. If not, throw exception
					String selectdata = new String("SELECT * FROM CONTAINER");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean CIDfound = false;
					while(rs.next())
					{
						String id = rs.getString("CID");
						if(id.equals(updateWhereTextArea.getText()))
						{
							CIDfound = true;
						}

					}
					if(!CIDfound)
					{
						throw new Exception();
					}
					
					statement.setString(1, updateCIDTextField.getText());
					statement.setString(2, updateVolLimTextField.getText());
					statement.setString(3, updateWeiLimTextField.getText());
					statement.setString(4, updateImageTextField.getText());
					statement.setString(5, updateDescTextField.getText());
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
					JOptionPane.showMessageDialog(null, "CID does not exist\n\nTry Again!");
		        }
				
				//reset text fields
				updateCIDTextField.setText("");
				updateVolLimTextField.setText("");
				updateWeiLimTextField.setText("");
				updateImageTextField.setText("");
				updateDescTextField.setText("");
				updateWhereTextArea.setText("");
				JOptionPane.showMessageDialog(null, "Update Successful");
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
				String insertData = new String("DELETE FROM CONTAINER WHERE CID = ?");
				
				try
				{
					PreparedStatement statement = conn.prepareStatement(insertData);
					
					//see if there exists a login which the user is trying to update 
					String selectdata = new String("SELECT * FROM CONTAINER");
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(selectdata);
					boolean whereFound = false;
					while(rs.next())
					{
						String CID = rs.getString("CID");
						if(CID.equals(whereDeleteTextField.getText()))
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
					JOptionPane.showMessageDialog(null, "Cannot delete! \nLogin does not exist");
				}

				//reset fields
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
					String selectData = "SELECT * FROM CONTAINER WHERE CID = ?";
				
				try
				{
					PreparedStatement statement = conn.prepareStatement(selectData);
				
					if(selectViewTextField.getText().equals("***ALL"))
					{
						String select = "SELECT * FROM MANAGER";
						Statement state = conn.createStatement();
						ResultSet rs = state.executeQuery(select);
						
						resultTextArea.setText("CID \t\tVolume Limit \t\tWeight Limit"
								+ "\t\tImage \t\tDescription\n");
						rs.beforeFirst();
						
						while(rs.next())
						{
							String CID = rs.getString("CID");
				        	String vol_lim = rs.getString("volume_limit");
				        	String wei_lim = rs.getString("weight_limit");
				        	String image = rs.getString("image");
				        	String desc = rs.getString("description");
				        	resultTextArea.append(CID +"\t\t" + vol_lim+"\t\t" +wei_lim+"\t\t" +
				        			image +"\t\t" +desc+"\n");
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
							String CID = rs.getString("CID");
							if(CID.equals(selectViewTextField.getText()))
							{
								viewFound = true;
							}
						}
						if(!viewFound)
						{
							throw new Exception();
						}

						resultTextArea.setText("CID \t\tVolume Limit \t\tWeight Limit"
								+ "\t\tImage \t\tDescription\n");
						rs.beforeFirst();
						
						while (rs.next()) 
						{
							String CID = rs.getString("CID");
				        	String vol_lim = rs.getString("volume_limit");
				        	String wei_lim = rs.getString("weight_limit");
				        	String image = rs.getString("image");
				        	String desc = rs.getString("description");
				        	resultTextArea.append(CID +"\t\t" + vol_lim+"\t\t" +wei_lim+"\t\t" +
				        			image +"\t\t" +desc+"\n");
						}
					}
					
					
				} catch (SQLException e1)
				{
					JOptionPane.showMessageDialog(null, "SQL Error");
				} catch (Exception notfound)
				{
					JOptionPane.showMessageDialog(null, "CID not found\nTry Again!");
				}
				
				//reset text field
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
	
}