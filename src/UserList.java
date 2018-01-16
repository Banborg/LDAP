
	
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class UserList {
	
	
	
	public void totalUser(String ecole) throws NamingException
	{	
		int port = 0;
		if(ecole.toLowerCase().equals("ensiie")) {
			port = 2349;
		}
		Properties initialProperties = new Properties();
		initialProperties.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		initialProperties.put(Context.PROVIDER_URL, "ldap://localhost:"+port);
		initialProperties.put(Context.SECURITY_PRINCIPAL, "CN=Directory Manager");
		initialProperties.put(Context.SECURITY_CREDENTIALS, "password");
		DirContext  context = new InitialDirContext(initialProperties);
		
		String searchFilter="(objectClass=inetOrgPerson)";
		String[] requiredAttributes={"mail","userpassword"  };
		SearchControls controls=new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(requiredAttributes);
		NamingEnumeration<?> users=context.search("dc=localdomain", searchFilter, controls);
		SearchResult searchResult=null;
		String email=null;
		String password=null;
		while(users.hasMore())
		{
			
			searchResult=(SearchResult) users.next();
			Attributes attr=searchResult.getAttributes();
			
			email=attr.get("mail").get(0).toString();
			password=attr.get("userpassword").get(0).toString();
			System.out.println("Email = "+email);
			System.out.println("Password = "+password);
			System.out.println("-------------------------------------------");
			
		}
		
	}
		public static void main(String[] args) throws NamingException  
	{
		UserList sample = new UserList();
		sample.totalUser("ensiie");
		
	}

}
