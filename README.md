Java_RelationalDB_Application: 
	A project build by a small team of Software Engineering and Computer Science students at Shippensburg 
	University as a database class project (CSC371). The application's function is to provide users a graphical
	interface for interacting with an established MySQL database. The application's main functions are inserting, 
	updating, deleting, and viewing data. Moreover, we found it exciting to provide a simple level of security 
	by password protecting those interactions. Furthermore, we provided classes that allow users the ability 
	to report bugs and contact technical support by utilizing java_mail/activation API. This project brought to 
	fruition a semester of relational database creation. 

	TEAM MEMBERS 
	ADAM STANDKE	(Computer Science major)
	DENNY FLEAGLE	(Software Engineering major)
	GABRIEL WEBBE	(Software Engineering major)

	
Instructions For Merging Our Project With Yours. 
	Once you have forked the project and have a copy on your local system,
	
	A) API's --- First you MUST add three (3) jar files to your build path:
		1) MySQL connector API
			http://dev.mysql.com/downloads/connector/j/5.1.html	 
		2) Java-Mail API
			http://www.java2s.com/Code/Jar/j/Downloadjavamail144jar.htm
		3) Java-activation API
			http://www.java2s.com/Code/Jar/a/Downloadactivationjar.htm

	------- Instructions for adding jar files to build path
		1) Download jar files from provided links to your local file system.
		2) Open Eclipse -> right click on project -> Build Path -> configure build path 
			. add external jar files -> find the files in your files system -> add them
		3) Apply and Close

	 B) Setting up class files for your project.
 		open database -> Database 
			change DB_LOCATION to your server address, 
			change LOGIN_NAME to your user login information
			change PASSWORD to your password

		open sendEmail -> SendEmail
			change yourEmail = < your email address >
			change yourPassword = < your email password >

		open user_interfaces -> RequestAccess
			change yourEmail = < your email address >
  			change yourPassword = < your email password >
	
	C) Running your new project
		In java project src -> runnable_class -> (open) Runner.java
			--- run this class ---


