package sendEmail;

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
 * Class allow us to send an email with the application. Consider linking to 
 * customer support email address for bug reports.
 * @author Denny Fleagle
 *
 *
 */
public class SendEmail implements ActionListener
{
	private String yourEmail = "enter_your_email";			//Change to your email address   //more secure if you store it in your database and pull it here
	private String yourPassword = "enter_your_password";	//Change to your email password. //more secure if you store it in your database and pull it here
	private JFrame frame;
	private JTextArea text;
	private JTextField totxt;
	private JTextField subjecttxt;
	private Message message;
	
	/**
	 * runner method
	 * @param args
	 */
	public static void main(String[] args) 
	{
		//instantiate new email
		new SendEmail();
	}
	
	/**
	 * class constructor 
	 */
	public SendEmail()
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
		if(!totxt.getText().equals(""))
		{
			//and if user entered subject
			if(!text.getText().equals(""))
			{
				
				
				try
				{
					//validate the to address as being an actual email address
					InternetAddress address = new InternetAddress(yourEmail);
					address.validate();
					
					//set the delivery address
					message.setRecipients(Message.RecipientType.TO,
							InternetAddress.parse(yourEmail));
					
					//give the message a subject
					message.setSubject(subjecttxt.getText());

					//write the message to be sent
					message.setText(text.getText());

					//Transport the message to the sender
					Transport.send(message);
					
					//Close the window
					frame.dispose();
								
					//email was sent
					JOptionPane.showMessageDialog(null, "Email has been sent \nAllow 24-48 hours for reply");
				
				} catch(AddressException e1)
				{
					JOptionPane.showMessageDialog(null, "Email address not valid");
					frame.setVisible(true);
					totxt.setText("");
					totxt.requestFocus();
					
				} catch (MessagingException e2)
				{
					JOptionPane.showMessageDialog(null, "Sending failure\nCheck Network Connection.");
					totxt.setText("");
					subjecttxt.setText("");
					
				}
			}
			//no subject was entered so warn the user
			else
			{
				JOptionPane.showMessageDialog(null, "Message is empty.");
				subjecttxt.requestFocus();
			}
		}
		//no target address was entered warn user
		else
		{
			JOptionPane.showMessageDialog(null, "Destination address is empty");
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
		frame = new JFrame("Support");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		
		
		//create the jpanel and use gridbaglayout
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setLayout(new GridBagLayout());
		frame.add(panel);
			
		//create a label for send address
		JLabel to = new JLabel("Send To:");
		c.gridx = 0;
		c.gridy = 0;
		panel.add(to, c);
		
		//create a label for subject
		JLabel subject = new JLabel("Subject:");
		c.gridx = 0;
		c.gridy = 1;
		panel.add(subject, c);
		
		//create a label for the message
		JLabel message = new JLabel("Message:");
		c.gridx = 0;
		c.gridy = 2;
		panel.add(message, c);
		
		//create a send button
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(this);
		c.gridx = 8;
		c.gridy = 10;
		c.ipadx = 500;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(10,300,25,0);
		panel.add(btnSend, c);
		c.ipadx = 0;
		c.insets = new Insets(0,0,0,0);
				
		//create the to address text field 
		totxt = new JTextField();
		totxt.setColumns(30);
		totxt.setText(" Customer Support");
		totxt.setEditable(false);
		c.gridx = 1;
		c.gridy = 0; 
		c.gridwidth = 10;
		c.ipady = 20;
		c.insets = new Insets(10,20,10,20);
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(totxt, c);
		
		//create the subject text field 
		subjecttxt = new JTextField();
		subjecttxt.setColumns(30);
		subjecttxt.setText(" Database Application Help.");
		subjecttxt.setEditable(false);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		panel.add(subjecttxt, c);
		
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

