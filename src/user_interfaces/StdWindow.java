package user_interfaces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import sendEmail.SendEmail;

/**
 * Allow us to standardize the display of a standard window within the 
 * Database application
 * @author Denny Fleagle
 *
 */
public class StdWindow extends Application
{
	private static volatile StdWindow window = null;		//an instace of this class
	private JFrame frame;									//the main jframe window 
	private int windowWidth;								//holds the preferred width of a standard window
	private int windowHeight;								//holds the preferred height of a standard window
	private JFrame pframe;
	private JPanel logo;
	private GridBagConstraints c;
	private JButton btnTable1, btnTable2, btnTable3;
	private JButton btnTable4, btnTable5, btnTable6;
	private JButton btnTable7, btnTable8, btnTable9;
	private JMenuItem miLogin, miLogOut;
	
	/**
	 * Main method for testing
	 * @param args
	 */
	public static void main(String [] args)
	{
		StdWindow w = StdWindow.buildStdWindow();
		w.setVisible(true);
	}
	/**
	 * constructor is private. We only every want a single
	 * instance of this window. All changes will be reflected
	 * in updating the panel
	 */
	private StdWindow()
	{
				
		c = new GridBagConstraints();
		
		//get the user's screen resolution
		getScreenDimensions();

		//build a jframe and add menu bar
		buildFrame();
		
		//build left side bar
		addLeftBar();
		
		//add DB logo and group 6 branding
		addBranding();
		
		//set the frame to visible
		setVisible(true);
		
	}
	
	/**
	 * build the JFrame that will be the standard window for the application 
	 */
	public void buildFrame()
	{
		//build the main frame
		frame = new JFrame("CSC371 Project2 Group6");
		frame.getContentPane().setLayout(new GridBagLayout());
		frame.setBounds(0, 0, windowWidth, windowHeight);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setResizable(true);
		
		//change the icon of the Jframe window
		try
		{	
			Image img = ImageIO.read(getClass().getResource("/solution_db.png"));
			frame.setIconImage(img);
		} catch (IOException e)
		{
			//do nothing image icon just could not load
		}
       
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		//create a menubar for the top of the window
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//create a menu 
		JMenu menuFile = new JMenu("File");
		menuFile.setFont(new Font("Ariel", Font.BOLD, (int)(windowHeight * .015)));
		
		//create a menu item for in the menubar 
		miLogin = new JMenuItem("Login");
		miLogin.setFont(new Font("Ariel", Font.BOLD, (int)(windowHeight * .015)));
		miLogin.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				login();

			}
		});
		
		//create a menu item for in the menubar 
		miLogOut = new JMenuItem("Log Out");
		miLogOut.setEnabled(false);
		miLogOut.setFont(new Font("Ariel", Font.BOLD, (int)(windowHeight * .015)));
		miLogOut.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				logOut();

			}
		});
		
		//create menu item for creating account
		JMenuItem miCreateAccount = new JMenuItem("Request Access");
		miCreateAccount.setFont(new Font("Ariel", Font.BOLD, (int)(windowHeight * .015)));
		miCreateAccount.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) 
			{
				new RequestAccess();

			}
		});
		
		//create menu item for contacting support
		JMenuItem miSupport = new JMenuItem("Contact Support");
		miSupport.setFont(new Font("Arial", Font.BOLD, (int)(windowHeight * .015)));
		miSupport.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				new SendEmail();
				
			}
			
		});
		menuBar.add(menuFile);
		menuFile.add(miLogin);
		menuFile.add(miLogOut);
		menuFile.add(miCreateAccount);
		menuFile.add(miSupport);
	}
	
	/**
	 * build the left part of the display which houses the table UI buttons 
	 * the user will click to access specific tables
	 */
	public void addLeftBar()
	{
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 0.1;
		c.weighty = 0.1;
		
		//header for the table sidebar
		JLabel selectTable = new JLabel("Select Table");
		selectTable.setHorizontalAlignment(SwingConstants.CENTER);
		selectTable.setFont(new Font("Serif", Font.BOLD, (int)(windowHeight * .03)));
		c.gridx = 0; 
		c.gridy = 0;
		c.insets = new Insets(0,(int)(windowWidth * .01),0,(int)(windowWidth * .25));
		frame.add(selectTable, c);

		//table button sidebar panel
		JPanel tableSelectPanel = new JPanel();
		tableSelectPanel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		c.gridx = 0; 
		c.gridy = 1;
		c.insets = new Insets(0,(int)(windowWidth * .01),(int)(windowHeight * .1),(int)(windowWidth * .25));
		c.weighty = 1;
		c.fill = GridBagConstraints.BOTH;
		frame.getContentPane().add(tableSelectPanel, c);
		tableSelectPanel.setLayout(new GridLayout(9,0,15,15));

		//table 1
		btnTable1 = new JButton("Player Table");
		tableSelectPanel.add(btnTable1);
		btnTable1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable1.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_PlayerUI playerTableUI = new Frame_PlayerUI();
					window.popup(playerTableUI.getFrame());

				}
			}
		});

		//table 2
		btnTable2 = new JButton("Creature Table");
		tableSelectPanel.add(btnTable2);
		btnTable2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable2.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_CreatureUI creature = new Frame_CreatureUI();
					window.popup(creature.getFrame());

				}
			}
		});

		//table 3
		btnTable3 = new JButton("Ability Table");
		tableSelectPanel.add(btnTable3);
		btnTable3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable3.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_AbilityUI ability = new Frame_AbilityUI();
					window.popup(ability.getFrame());

				}
			}
		});
		
		//table 4
		btnTable4 = new JButton("Manager");
		tableSelectPanel.add(btnTable4);
		btnTable4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable4.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_Manager manager = new Frame_Manager();
					window.popup(manager.getFrame());

				}
			}
		});
		
		//table 1
		btnTable5 = new JButton("Moderator");
		tableSelectPanel.add(btnTable5);
		btnTable5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable5.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_Moderator moderator = new Frame_Moderator();
					window.popup(moderator.getFrame());

				}
			}
		});

		//table 2
		btnTable6 = new JButton("Container");
		tableSelectPanel.add(btnTable6);
		btnTable6.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable6.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_Container container = new Frame_Container();
					window.popup(container.getFrame());

				}
				
			}
		});

		//table 3
		btnTable7 = new JButton("Armor");
		tableSelectPanel.add(btnTable7);
		btnTable7.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable7.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_ArmorUI playerTableUI = new Frame_ArmorUI();
					window.popup(playerTableUI.getFrame());

				}
				
			}
		});

		//table 4
		btnTable8 = new JButton("Weapon");
		tableSelectPanel.add(btnTable8);
		btnTable8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable8.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_WeaponUI playerTableUI = new Frame_WeaponUI();
					window.popup(playerTableUI.getFrame());
				}
			}
		});
		
		//table 4
		btnTable9 = new JButton("Location");
		tableSelectPanel.add(btnTable9);
		btnTable9.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e)
			{
				if(btnTable9.isEnabled())
				{
					//Sleep the timer to prevent flickering when changing between tables
					try
					{
						Thread.sleep(500);
					} catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
					//pass an instance of the Table window you want to work with to the standard window
					StdWindow window = StdWindow.buildStdWindow();
					Frame_LocationUI playerTableUI = new Frame_LocationUI();
					window.popup(playerTableUI.getFrame());
				}
			}
		});
		
		activateButtons(false);
	}
	
	public void activateButtons(boolean tof)
	{
		btnTable1.setEnabled(tof);
		btnTable2.setEnabled(tof);
		btnTable3.setEnabled(tof);
		btnTable4.setEnabled(tof);
		btnTable5.setEnabled(tof);
		btnTable6.setEnabled(tof);
		btnTable7.setEnabled(tof);
		btnTable8.setEnabled(tof);
		btnTable9.setEnabled(tof);
	}
	
	/**
	 * add branding to the UI such as a DB logo in middle of screen and 
	 * silly group 6 brand at bottom
	 */
	public void addBranding()
	{
		
		//create an image that to display in the panel
		Image img = new ImageIcon(this.getClass().getResource("/solution_db.png")).getImage();	
		Image background = img.getScaledInstance((int)(getScreenWidth()*.2), (int)(getScreenWidth()*.2),  java.awt.Image.SCALE_SMOOTH);

		//the canvas will hold the image
		JLabel canvas = new JLabel();
		canvas.setBounds(0, 0, getScreenWidth(), getScreenHeight());
		canvas.setVisible(true);
		canvas.setIcon(new ImageIcon(background));

		//create a panel to hold the DB logo
		logo = new JPanel();
		logo.setLayout(new BorderLayout());
		//logo.setBounds((int)(getScreenWidth()*.4), (int)(getScreenHeight()*.3), (int)(getScreenWidth()*.3), (int)(getScreenWidth()*.3));
		logo.add(canvas, BorderLayout.WEST);
		logo.setOpaque(true);
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.WEST;
		frame.add(logo, c);
		
	}
	
	/**
	 * get the user's screen resolution
	 */
	public void getScreenDimensions()
	{
		//set the size of the window
		windowWidth = getScreenWidth();
		windowHeight = getScreenHeight();
		
	}

	/**
	 * other classes will access this instance of the window 
	 * through the build std window method ensuring only one window
	 * is built
	 * @return
	 */
	public static StdWindow buildStdWindow()
	{
		if(window == null)
			synchronized(StdWindow.class)
			{
				if(window == null)
				{
					window = new StdWindow();
				}
			}
		return window;
		
	}
	
	/**
	 * when the user wants to login 
	 */
	public void login()
	{
		//create the jframe window
		JFrame f = new JFrame("Login");
		f.setSize(windowWidth/2, windowHeight/2);
		f.setResizable(false);
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
		//create a few constraints
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.insets = new Insets(10,10,10,10);
		c.fill = GridBagConstraints.BOTH;
		
		//create the jpanel that will hold the ui items
		JPanel p = new JPanel();
		p.setLayout(new GridBagLayout());
		
		//create the ui items
		JLabel lblUsername = new JLabel("USERNAME");
		JLabel lblPassword = new JLabel("PASSWORD");
		JTextField tfLogin = new JTextField();
		tfLogin.setFont(new Font("Courier", Font.BOLD, (int)(windowHeight * .023)));
		tfLogin.setColumns(30);
		JPasswordField tfPassword = new JPasswordField();
		tfPassword.setColumns(30);
		tfPassword.setFont(new Font("Courier", Font.BOLD, (int)(windowHeight * .023)));
		
		//create the login button and give it an action listener
		JButton btnSubmit = new JButton("LOGIN");
		f.getRootPane().setDefaultButton(btnSubmit);
		btnSubmit.addActionListener(new ActionListener() {

			/**
			 * action performed when the user clicks enter button
			 */
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//boolean to determine if logged in
				boolean loggedin = false;

				//query the user database table for the user's credentials
				try
				{
			         String selectData = new String("SELECT * FROM users");
			         Statement stmt = conn.createStatement();
			         ResultSet rs = stmt.executeQuery(selectData);
			         while (rs.next())
			         {
			        	 String userName = rs.getString("uName");
			        	 String password = rs.getString("pWord");
			        	 
			        	 //if username and password exist then log in and dispose the jframe
			        	 if(userName.equals(tfLogin.getText()) && password.equals(tfPassword.getText()))
			        	 {
			        		 activateButtons(true); 
			        		 f.dispose();
			        		 loggedin = true;
			        		 miLogin.setEnabled(false);
			        		 miLogOut.setEnabled(true);
			        	 }
			         }
			         
			         //the user's credentials do not match so warn them and do nothing
			         if(!loggedin)
			         {
			        	 tfLogin.setText("");
			        	 tfPassword.setText("");
			        	 tfLogin.requestFocus();
			        	 JOptionPane.showMessageDialog(null, "Username/password does not exist");
			         }
			         
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
			
		});
		btnSubmit.addMouseListener(new MouseAdapter() {
			/**
			 * Action for when the user clicks on the button
			 */
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e)
			{
				//boolean to determin if logged in
				boolean loggedin = false;

				//query the user database table for the user's credentials
				try
				{
			         String selectData = new String("SELECT * FROM users");
			         Statement stmt = conn.createStatement();
			         ResultSet rs = stmt.executeQuery(selectData);
			         while (rs.next())
			         {
			        	 String userName = rs.getString("uName");
			        	 String password = rs.getString("pWord");
			        	 
			        	 //if username and password exist then log in and dispose the jframe
			        	 if(userName.equals(tfLogin.getText()) && password.equals(tfPassword.getText()))
			        	 {
			        		 activateButtons(true); 
			        		 f.dispose();
			        		 loggedin = true;
			        		 miLogin.setEnabled(false);
			        		 miLogOut.setEnabled(true);
			        	 }
			         }
			         
			         //the user's credentials do not match so warn them and do nothing
			         if(!loggedin)
			         {
			        	 tfLogin.setText("");
			        	 tfPassword.setText("");
			        	 tfLogin.requestFocus();
			        	 JOptionPane.showMessageDialog(null, "Username/password does not exist");
			         }
			         
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});
		
		c.gridx = 0;
		c.gridy = 0;
		p.add(lblUsername,c);
		c.gridx = 1;
		c.gridy = 0;
		p.add(tfLogin,c);
		c.gridx = 0;
		c.gridy = 1;
		p.add(lblPassword,c);
		c.gridx = 1;
		c.gridy = 1;
		p.add(tfPassword,c);
		c.ipady = 0;
		c.anchor = GridBagConstraints.PAGE_END;
		c.insets = new Insets(0,0,10,0);
		c.gridwidth = 2;
		c.gridx = 1;
		c.gridy = 2;
		p.add(btnSubmit,c);
		
		f.add(p);
	}
	
	/**
	 * allow for users to log out of the application
	 */
	public void logOut()
	{
		activateButtons(false);
		miLogin.setEnabled(true);
	}
	
	/**
	 * method used for the insturction popup window
	 */
	public void displayInstructionPopup()
	{
		//create the jframe popup window
		JFrame f = new JFrame("Learning Module");
    	f.setSize(windowWidth/2, windowHeight/2);
    	f.setResizable(false);
    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	f.setLocationRelativeTo(null);
    	f.setVisible(true);
    	
    	String s = "Hey guys create your JFrame class in the user_interfaces package. Make sure your "
    			+ "class implements the Frame interface and extends UI_setup for sizing. Once you do "
    			+ "that just call getFrameWidth and getFrameHeight to size your frames appropriately. "
    			+ "The Frame interface will require you to implement a few methods make sure you return "
    			+ "your JFrame in the appropriate method ie. getFrame... "
    			+ "It should be that simple for the UI's once you have them build then we can link "
    			+ "them to the buttons on the left which will launch each tables UI. "
    			+ "Of course if you have questions we can talk about it..."
    			+ "If you want, click on the table buttons to the left to get an idea of where the "
    			+ "undecorated jframes will be once we create them... We are simply launching a "
    			+ "new JFrame window each time we click the button to the left.";
    	
    	//create the text area
    	JTextArea ta = new JTextArea();
    	ta.setFont(new Font("Ariel", Font.PLAIN, (int)(.025 * windowHeight)));
    	ta.setBorder(new EmptyBorder(20,20,20,20));
    	ta.setLineWrap(true);
    	ta.setWrapStyleWord(true);
    	ta.append(s);
    	
    	//create the scroll area and add the text area to it
    	JScrollPane sp = new JScrollPane(ta);
    	sp.setBorder(new EmptyBorder(20, 20, 20, 20));

    	//add the scroll area to the jframe
    	f.add(sp);
	}
	
	/**
	 * allow us to display the table interaction UI
	 * @param display the jframe window to display
	 */
	public void popup(JFrame display)
	{
		if(pframe != null)
		{
			if(pframe.isVisible())
			{
				pframe.dispose();
			}
		}
		
		pframe = display;
		pframe.setLocationRelativeTo(null);
		pframe.setVisible(true);
		
	}

	/**
	 * dynamically set the visibility of this window 
	 */
	public void setVisible(boolean bool)
	{
		frame.setVisible(bool);
	}
}
