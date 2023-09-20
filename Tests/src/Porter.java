
public class Porter {
	public int getPasswordStrength(String firstName,String lastName,String password){
		int currentStrength=100;
		String pw=password;
		pw=pw.toLowerCase();
		
		
		//Implement both first and last
		String username=firstName+lastName;
		username=username.toLowerCase();
		if(pw.contains(username.toLowerCase())) {
			return 0;
		}
		//Implement eiher first and last
		if(pw.contains(firstName.toLowerCase())){
			currentStrength-=30;
		}
		if(pw.contains(lastName.toLowerCase())){
			currentStrength-=30;
		}
		//HAndling Substrings
		int isSubPresentinFirstName=isSubStringPresent( firstName,password,firstName.length(),password.length());
		if(isSubPresentinFirstName>1) {
			currentStrength-=5;
		}	
		int isSubPresentinLastName=isSubStringPresent( lastName,password,lastName.length(),password.length());
		if(isSubPresentinLastName>1) {
			currentStrength-=5;
		}
		
		char arr[]=password.toCharArray();
		//48-57 dig  65 -90 Upper 97-122 lower else special
		Boolean isPwd=true;
		for(int i=0;i<arr.length;i++) {
		   if(isDigit(arr[i]) || isUpper(arr[i]) || isLower(arr[i])  || isSpecial(arr[i])) {
			   isPwd=true;
		   }else {
			   isPwd=false;
			   break;
		   }
		   
		}
		
		
		
		return currentStrength;

}

	private boolean isLower(char c) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isDigit(char c) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isUpper(char c) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isSpecial(char c) {
		// TODO Auto-generated method stub
		return false;
	}

	private int isSubStringPresent(String firstName, String password,int m,int n) {
		
		if(m==0 || n==0) {
			return 0;
		}
		if(firstName.charAt(m-1)==password.charAt(n-1)) {
			return isSubStringPresent(firstName, password, m-1, n-1)+1;
		}else {
			return 0;
		}
		
	}

}
