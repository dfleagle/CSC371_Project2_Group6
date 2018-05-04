package user_interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * send an email with a java application 
 * @author Denny Fleagle
 *
 * https://www.mkyong.com/java/javamail-api-sending-email-via-gmail-smtp-example/
 *
 */
public class RequestAccess implements ActionListener
{
	private String yourEmail = "enter_your_email";			//Change to your email address   //more secure if you store it in your database and pull it here
	private String yourPassword = "enter_your_password";	//Change to your email password. //more secure if you store it in your database and pull it here
	private JFrame frame;
	private JTextArea text;
	private JTextField firstName;
	private JTextField lastName;
	private Message message;
	
	/**
	 * runner method
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//instantiate new email
		new RequestAccess();
	}
	
	/**
	 * class constructor 
	 */
	public RequestAccess()
	{          
		setupUI();
		GUI();
		emailProperties();
		
	}
	
	/**
	 * establish properties for email account
	 */
	private void emailProperties()
	{
		//provide user name and password for the account which will send message
		final String username = yourEmail;
		final String password = yourPassword;

		//establish email properties
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		//Authenticate the connection
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(username, password);
			}
		});

		try 
		{
			//Create the message, who is it coming from 
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(yourEmail));

		} catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}
		
	}

	/**
	 * actions to perform when send button is clicked
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		//if user entered to address
		if(!firstName.getText().equals(""))
		{
			//and if user entered subject
			if(!text.getText().equals(""))
			{
				
				
				try
				{
					//validate the to address as being an actual email address
					InternetAddress address = new InternetAddress("enter_a_email_to_send_to");
					address.validate();
					
					//set the delivery address
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse("enter_a_email_to_send_to"));
					
					//give the message a subject
					message.setSubject("Database Application Access");

					//write the message to be sent
					message.setText("First Name: " + firstName.getText() + "\nLast Name: " + lastName.getText()
									+ "\n\nWhy I need access: \n\t" + text.getText());

					//Transport the message to the sender
					Transport.send(message);
					
					//Close the window
					frame.dispose();
								
					//email was sent
					JOptionPane.showMessageDialog(null, "Request Sent! \n\nAllow 24-48 hours for approval");
				
				} catch(AddressException e1)
				{
					JOptionPane.showMessageDialog(null, "Email address not valid");
					frame.setVisible(true);
					firstName.setText("");
					firstName.requestFocus();
					
				} catch (MessagingException e2)
				{
					JOptionPane.showMessageDialog(null, "Sending failure\nCheck Network Connection.");
					firstName.setText("");
					lastName.setText("");
					
				}
			}
			//no subject was entered so warn the user
			else
			{
				JOptionPane.showMessageDialog(null, "Please provide a reason for access");
				lastName.requestFocus();
			}
		}
		//no target address was entered warn user
		else
		{
			JOptionPane.showMessageDialog(null, "Please enter First Name.");
		}
		
	}
	
	/**
	 * Build a graphical user interface
	 */
	private void GUI()
	{	
		//create the grid bag constraint for grid bag layout in jpanel 
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10,10,10,10);
		c.anchor = GridBagConstraints.LINE_START;
		
		//create the frame 
		frame = new JFrame("Request Access");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
	
		//create the jpanel and use gridbaglayout
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridBagLayout());
		frame.add(panel);
			
		//create a label for send address
		JLabel to = new JLabel("First Name:");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(to, c);
		
		//create a label for subject
		JLabel subject = new JLabel("Last Name:");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(subject, c);
		
		//create a label for the message
		JLabel message = new JLabel("Why Do You Want/Need Access?:");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(message, c);
		
		//create a send button
		JButton btnSend = new JButton("Request");
		btnSend.addActionListener(this);
		c.gridx = 8;
		c.gridy = 10;
		c.ipadx = 400;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,0,25,0);
		panel.add(btnSend, c);
		c.ipadx = 0;
		c.insets = new Insets(0,0,0,0);
				
		//create the to address text field 
		firstName = new JTextField();
		firstName.setColumns(30);
		c.gridx = 0;
		c.gridy = 0; 
		c.gridwidth = 10;
		c.ipady = 20;
		c.insets = new Insets(10,200,10,20);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(firstName, c);
		c.insets = new Insets(10,20,10,20);
		
		//create the subject text field 
		lastName = new JTextField();
		lastName.setColumns(30);
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(10,200,10,20);
		c.gridwidth = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(lastName, c);
		c.insets = new Insets(10,20,10,20);
		
		//create the text area that will hold the message to be sent
		text = new JTextArea();
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.5;
		c.ipadx = 1500;
		c.ipady = 500;
		c.gridwidth = 11;
		c.fill = GridBagConstraints.BOTH;
		
		//add the text area to the jscroll pane 
		JScrollPane sp = new JScrollPane(text);
		panel.add(sp, c);
		
		//pack the frame and set it's location in the center of the screen
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
	
	/**
	 * set some global defaults for the UIManager 
	 */
	private void setupUI()
	{
		UIManager.put("Button.font", new Font("Arial", Font.BOLD, 40));
		UIManager.put("TextField.font", new Font("Arial", Font.PLAIN, 30));
		UIManager.put("TextArea.font", new Font("Arial", Font.PLAIN, 40));
		Font oldLabelFont = UIManager.getFont("Label.font");
	    UIManager.put("Label.font", oldLabelFont.deriveFont(Font.ITALIC, 30));
		
	}
}

