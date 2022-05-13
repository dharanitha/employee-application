import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

abstract class EmployeeJDBC extends DateofJoin{
	public static String employeeID;
	public static String getEmployeeId() {
		return employeeID;
	}
	public static void setEmployeeId(String employeeId) {
		employeeID=employeeId;
	}
	public static boolean EmployeeId(String employeeId){
        try{
            if(employeeId.length()!=7 || (!(employeeId.substring(0,3).equals("Ace")))){
                throw new Exception();
            }
            try{
            	for(int i=3;i<7;i++){
            		if(!Character.isDigit(employeeId.charAt(i))){
            			throw new Exception();
            		}
            	}
                if(!present(employeeId)){
                	setEmployeeId(employeeId);
                	return true;
                }else {
                	System.out.println("Same id should not repeated.Enter different id:");
                }
                return false;
            }
            catch(Exception e){
            	System.out.println("The Employee id should begins with Ace followed by four digits.Do not include any special characters.");
            	return false;
            }
        }
        catch(Exception e){
        	System.out.println("The Employee id should begins with Ace followed by four digits.Do not include any special characters.");
        	return false;
        }
    }
	public static String employeeId(){
		Scanner scanner = new Scanner(System.in);
    	while(true) {
    		System.out.println("New Employee Id:");
    		String employeeId = scanner.next();
    		if(EmployeeId(employeeId)) {
    			return employeeId;
    		}
            System.out.println("The Employee id should begins with Ace followed by four digits.Do not include any special characters.");
    	}
    }
	public static void updateEmployeeId(String employeeOldId) {
     	 String employeeNewId = employeeId();
     	 try {
      		Class.forName("com.mysql.cj.jdbc.Driver");
      		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
      		PreparedStatement prestatement = null;
  			String query="update employeejdbc set Employeeid =? where EmployeeId=? or EmployeeEmailid=?";
  			prestatement=connection.prepareStatement(query);
  			prestatement.setString(1,employeeNewId);
  			prestatement.setString(2, employeeOldId);
  			prestatement.setString(3, employeeOldId);
  		}
      	catch(Exception e){
      		System.out.println(e);
      	}
    }
}
class EmployeeName extends EmployeeJDBC{
	public static String employeeName;
	public static String getEmployeeName() {
		return employeeName;
	}
	public static void setEmployeeName(String name) {
		employeeName=name;
	}
	public static boolean EmployeeName(String Name){
		 if(Pattern.matches("[a-zA-Z ]{3,40}",Name)){
			 return true;
		 }
		 return false;
	}
	public static String employeeName(){
		 Scanner scanner=new Scanner(System.in);
		 while(true){
			 System.out.println("New Employee Name:");
			 String EmployeeName=scanner.nextLine();
			 if(EmployeeName(EmployeeName)){
				 return EmployeeName;
			 }
			 System.out.println("The Employee name should contain only alphabets and do not include special characters or numeric values.");
		 }
	}
	public static void updateName(String employeeOldName) {
		String employeeNewName=employeeName();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
      		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
      		PreparedStatement prestatement = null;
  			String query="update employeejdbc"+"set EmployeeName =? where EmployeeId=? or EmployeeEmailid=?";
  			prestatement=connection.prepareStatement(query);
  			prestatement.setString(1,employeeNewName);
  			prestatement.setString(2, employeeOldName);
  			prestatement.setString(3, employeeOldName);
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static int choice() {
    	Scanner scanner =  new Scanner(System.in);
    	int choice=scanner.nextInt();
    	return choice;
    }
    public static String recordDelete() {
    	Scanner scanner =  new Scanner(System.in);
    	String record = scanner.next();
    	return record;
    }
}
class DateofBirth extends EmployeeEmailId{
	public static String employeeDob;
	public static String getEmployeeDob() {
		return employeeDob;
	}
	public static void setEmployeeDob(String employeeDOB) {
		employeeDob=employeeDOB;
	}
	public static void dateTable(String dobTable) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
      		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
      		PreparedStatement prestatement = null;
  			String query="select EmployeeDob from employeejdbc where EmployeeId=? or EmployeeEmailid=?";
  			prestatement=connection.prepareStatement(query);
  			prestatement.setString(1,dobTable);
  			prestatement.setString(2, dobTable);
  			ResultSet result=prestatement.executeQuery();
  			while(result.next()) {
  				setEmployeeDob(result.getString(1));
  			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public static boolean validDob(String dob) {
		boolean function = false;
        if (Dateofbirth(dob)){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(dob);
                function = true;
            }
            catch (Exception e) {
                function = false;
            }
        }
        return function;
	}
	static boolean Dateofbirth(String date) {
        boolean flag = false;
        DateTimeFormatter dateformat=DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now=LocalDateTime.now();
        int currentDate=Integer.parseInt(dateformat.format(now));
        if (date.matches(date)) {
            int length=date.length();
            int currentyear=Integer.parseInt(date.substring(length-4,length));
            if(currentyear<=currentDate-20){
                setEmployeeDob(date);
                flag = true;
            }
        }
        return flag;
    }
	public static String employeeDob(){
        Scanner scanner = new Scanner(System.in);
        while(true) {
        	System.out.println("New Employee Date of Birth:");
        	String date = scanner.next();
        	if(validDob(date)==true){
        		return date;
        	}
        	else{
        		System.out.println("Enter the valid dateformat dd/MM/yyyy and age must be between 18 to 60.");
        	}
        }
    }
	public static void updateDob(String employeeOldDob) {
		String employeeNewDob=employeeDob();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
      		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
      		PreparedStatement prestatement = null;
  			String query="update employeejdbc set EmployeeDob =? where EmployeeId=? or EmployeeEmailid=?";
  			prestatement=connection.prepareStatement(query);
  			prestatement.setString(1,employeeNewDob);
  			prestatement.setString(2, employeeOldDob);
  			prestatement.setString(3, employeeOldDob);
  			prestatement.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
class DateofJoin extends DateofBirth{
	public static String employeeDoj;
	public static String getEmployeeDoj() {
		return employeeDoj;
	}
	public static void setEmployeeDoj(String doj) {
		employeeDoj=doj;
	}
    public static boolean validDoj(String date){
    	boolean function = false;
        if (dateofjoin(date)){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                function = true;
            }
            catch (Exception excp) {
                function = false;
            }
        }
        return function;
    }
    
    //method overloading
    public static boolean validDate(String date,String doj)
    {
        boolean function = false;
        dateTable(doj);
        if (dateofjoin(date)){
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                function = true;
            }
            catch (Exception excp) {
                function = false;
            }
        }
        return function;
    }
    static boolean dateofjoin(String date) {
        String pattern = "(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[0-2])\\/([0-9]{4})";
        boolean flag = false;
        DateTimeFormatter dtf=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now=LocalDateTime.now();
        String date1=dtf.format(now);
        String[] s1=date1.split("/");
        String[] s2=date.split("/");
        String[] s3=getEmployeeDob().split("/");
        int currentdate=Integer.parseInt(s1[2]+s1[1]+s1[0]);
        int inputdate=Integer.parseInt(s2[2]+s2[1]+s2[0]);
        int birthdate=Integer.parseInt(s3[2]+s3[1]+s3[0]);
        if (date.matches(pattern) ) {
            if(inputdate>=birthdate+180000 && inputdate<=birthdate+600000 && inputdate<=currentdate)
            	flag = true;
            	setEmployeeDoj(date);
        }
        return flag;
    }
    public static String dateofJoin(String doj) {
    	Scanner scanner = new Scanner(System.in);
    	while(true) {
    		System.out.println("New Employee Date of Joining");
    		String date = scanner.next();
    		if(validDate(date,doj)) {
    			return date;
    		}
            System.out.println("Enter the valid dateformat dd/MM/yyyy and no future dates.");
    	}
    }
    public static void updateDoj(String employeeOldDoj) {
      	 String employeeNewDoj = dateofJoin(employeeOldDoj); 
      	 try {
       		Class.forName("com.mysql.cj.jdbc.Driver");
       		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
       		PreparedStatement prestatement = null;
       		String query="update employeejdbc set EmployeeDoj =? where EmployeeId=? or EmployeeEmailid=?";
   			prestatement=connection.prepareStatement(query);
   			prestatement.setString(1,employeeNewDoj);
   			prestatement.setString(2, employeeOldDoj);
   			prestatement.setString(3, employeeOldDoj);
   			prestatement.executeUpdate();
   		}
       	catch(Exception e){
       		System.out.println(e);
       	}
       }
}
class EmployeeEmailId{
	public static String employeeEmailid;
	public static String getEmployeeEmailId() {
		return employeeEmailid;
	}
	public static void setEmployeeEmailId(String employeeEmailId) {
		employeeEmailid=employeeEmailId;
	}
	public static boolean Emailid(String email){
		String emailRegex = "^[a-zA-Z_+&-]+(?:\\."+"[a-zA-Z_+&-]+)*@" +"(?:[a-zA-Z-]+\\.)+[a-z" +"A-Z]{2,7}$";
		Pattern pattern = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		else if(pattern.matcher(email).matches()) {
			if(!present(email))
				setEmployeeEmailId(email);
			return true;
		}
		System.out.println("Same id should not repeated.Enter different id:");
		return false;
	}
	public static boolean present(String emailId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
      		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
      		PreparedStatement prestatement = null;
  			String query="select EmployeeId from employeejdbc where EmployeeId=? or EmployeeEmailid=?";
  			prestatement=connection.prepareStatement(query);
  			prestatement.setString(1,emailId);
  			prestatement.setString(2, emailId);
  			ResultSet result=prestatement.executeQuery();
  			return result.isBeforeFirst();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
	public static String emailId(){
	    Scanner scanner=new Scanner(System.in);
	    while(true){
	        System.out.println("New Email Id: ");
		    String email =scanner.next();
		    if(Emailid(email)){
		    	return email;
		    }
		    else
			    System.out.println("Enter the valid email id like username@domainname.com and Domain name should contain only alphabets.");
	    }
	}
	public static void updateEmailid(String employeeOldEmailid) {
		String employeeNewEmailid=emailId();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
      		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
      		PreparedStatement prestatement = null;
  			String query="update employeejdbc set EmployeeEmailid =? where EmployeeId=? or EmployeeEmailid=?";
  			prestatement=connection.prepareStatement(query);
  			prestatement.setString(1,employeeNewEmailid);
  			prestatement.setString(2, employeeOldEmailid);
  			prestatement.setString(3, employeeOldEmailid);
  			prestatement.executeUpdate();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
class EmployeeDisplay extends EmployeeName{
	public static boolean emptyTable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
      		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
      		PreparedStatement prestatement = null;
      		Statement statement=connection.createStatement();
  			ResultSet result=statement.executeQuery("select * from employeejdbc");
  			if(result.next()==false) {
  				return false;
  			}
  			else {
  				return true;
  			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return true;
	}
	public static void add() {
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("Employee Name:");
			String employeeName=scanner.nextLine();
			if(EmployeeName(employeeName)) {
				setEmployeeName(employeeName);
				break;
			}
			else {
				System.out.println("The Employee name should contain only alphabets and do not include special characters or numeric values.");
			}
		}
		while(true) {
			System.out.println("Employee Id:");
			String employeeId=scanner.next();
			if(EmployeeId(employeeId)) {
				break;
			}
		}
		while(true) {
			System.out.println("Employee Date of Birth:");
			String dob=scanner.next();
			if(validDob(dob)==true) {
				break;
			}
			else {
				System.out.println("Enter the valid dateformat dd/MM/yyyy and age must be between 18 to 60.");
			}
		}
		while(true) {
			System.out.println("Employee Date of join:");
			String doj=scanner.next();
			if(validDoj(doj)==true) {
				break;
			}
			else {
				System.out.println("Enter the valid dateformat dd/MM/yyyy and age must be between 18 to 60.");
			}
		}
		while(true) {
			System.out.println("Employee EmailId:");
			String employeeEmailid=scanner.next();
			if(Emailid(employeeEmailid)) {
				break;
			}
			else {
				System.out.println("Enter the valid email id like username@domainname.com and Domain name should contain only alphabets.");
			}
		}
	}
	public static void updateAll(String updateElement) {
		Scanner scanner=new Scanner(System.in);
        System.out.println("1.Employee Name");
        System.out.println("2.Employee Id");
        System.out.println("3.Employee Date of Birth");
        System.out.println("4.Employee Date of Join");
        System.out.println("5.Employee Email Id");
        while(true){
        	int value=scanner.nextInt();
            switch (value){
                case 1:
                	updateName(updateElement);
                    return;
                case 2:
                	updateEmployeeId(updateElement);
                    return;
                case 3:
                    updateDob(updateElement);
                    return;
                case 4:
                    updateDoj(updateElement);
                    return;
                case 5:
                    updateEmailid(updateElement);
                    return;
                default:
                    System.out.println("Entered number should be between 1 to 5.");
            }
        }
	}
	public static void update(){
		Scanner scanner=new Scanner(System.in);
		while(true){
			System.out.println("1.Update the data using EmployeeId");
			System.out.println("2.Update the data using EmailId");
	        int choose=scanner.nextInt();
	        switch(choose){
	        case 1:
	        	while(true){
	        		System.out.println("Employee Id:");
	        		String oldEmployeeId=scanner.next();
	        		if(present(oldEmployeeId)) {
	        			updateAll(oldEmployeeId);
	        			break;
	        		}
	        		else{
	        			System.out.println("The Employee id should begins with Ace followed by four digits.Do not include any special characters.");
	        		}
	        	}
	        	return;	        
	        	case 2:
	        		while(true){
	        			System.out.println("Employee EmailId:");
	        			String oldEmailId=scanner.next();
	        			if(present(oldEmailId)) {
	        				updateAll(oldEmailId);
	        				break;
	        			}
	        			else{
	        				System.out.println("Enter the valid email id like username@domainname.com and Domain name should contain only alphabets.");
	        			}
	        		}
	        		return;
	        		default:
	        			System.out.println("The input should either be 1 or 2.");
	        }
		}
	}
	public void display() {
		Scanner scanner=new Scanner(System.in);
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/java_sql","root","Aspire@123");
			PreparedStatement preparestatement = null;
			Statement statement=connection.createStatement();
			while(true) {
				System.out.println("Enter the number");
				System.out.println("1.Adding the Employee Details ");
				System.out.println("2.Deleting the Employee Details ");
				System.out.println("3.Updating the Employee Details");
				System.out.println("4.Display the Employee Details ");
				System.out.println("5.Exit");
				int choice = choice();
				switch(choice){
				case 1:
					System.out.println("How many employees should add in the application:");
	            	int addnumber=scanner.nextInt();
	            	try {
	            		for(int index=1;index<=addnumber;index++) {
	            			add();
	    					String sql = "INSERT INTO employeejdbc(EmployeeId,EmployeeName,EmployeeDob,EmployeeDoj,EmployeeEmailid) VALUES (?,?,?,?,?)";
	    					preparestatement = connection.prepareStatement(sql);
	    					preparestatement.setString(1, getEmployeeId());
	    					preparestatement.setString(2, getEmployeeName());
	    					preparestatement.setString(3, getEmployeeDob());
	    					preparestatement.setString(4, getEmployeeDoj());
	    					preparestatement.setString(5, getEmployeeEmailId());
	    					preparestatement.executeUpdate();
	    					Thread.sleep(1000);
	            		}
	            	}catch(Exception e) {
	            		System.out.println(e);
	            	}
	            	break;
				case 2:
					String delete;
					while(true) {
						System.out.println("Enter delete details:");
						delete=recordDelete();
						if(present(delete)){
							break;
						}
						System.out.println("No such element exits in the database");
					}
					String sqlQuery="DELETE FROM employeejdbc WHERE EmployeeId=(?)";
					preparestatement = connection.prepareStatement(sqlQuery);
					preparestatement.setString(1, delete);
					preparestatement.executeUpdate();
					break;
				case 3:
					update();
					break;
				case 4:
					String query="SELECT EmployeeId,EmployeeName,EmployeeDob,EmployeeDoj,EmployeeEmailid FROM employeejdbc";
					ResultSet resultSet=statement.executeQuery(query);
					while(resultSet.next()) {
						System.out.println("!!!Employee Full Details!!!");
						System.out.println(resultSet.getString(1));
						System.out.println(resultSet.getString(2));
						System.out.println(resultSet.getString(3));
						System.out.println(resultSet.getString(4));
						System.out.println(resultSet.getString(5));
					}
					break;
				case 5:
					System.out.println("Exited!!!");
					return;
				}
			}
		}catch(Exception e) {
				System.out.println(e);
		}
	}
}
