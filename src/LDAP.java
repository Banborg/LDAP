import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.SearchScope;

public class LDAP {

	public static void main(String[] args) {
		final int SCOPE_SUB;


		//Adresse du serveur sur lequel se trouve l'annuaire LDAP
		String serverIP = "localhost";
		//Port du serveur sur lequel se trouve l'annuaire LDAP
		String serverPort = "2349";
		//Login de connexion à l'annuaire LDAP : Le login doit être sous forme de "distinguished name"
		//ce qui signifie qu'il dois être affiché sous la forme de son arborescence LDAP
		String serverLogin = "CN= Directory Manager"; 
		// "cn=ldap://dc=localhost,dc=localdomain:27752"
		//Mot de passe de connexion à l'annuaire LDAP
		String serverPass = "password";

		//On remplit un tableau avec les parametres d'environnement et de connexion au LDAP
		Hashtable environnement = new Hashtable();
		environnement.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		environnement.put(Context.PROVIDER_URL, "ldap://"+serverIP+":"+serverPort+"/");
		environnement.put(Context.SECURITY_AUTHENTICATION, "simple");
		environnement.put(Context.SECURITY_PRINCIPAL, serverLogin);
		environnement.put(Context.SECURITY_CREDENTIALS, serverPass);
		try {
			//On appelle le contexte à partir de l'environnement
			DirContext contexte = new InitialDirContext(environnement);
//			SearchResult sr = new SearchResult(environnement);
			//Si ça ne plante pas c'est que la connexion est faite
			System.out.println("Connexion au serveur : SUCCES");
			try {
				
				Attributes attrs = contexte.getAttributes("uid=vbruno, ou=professor,ou=people,dc=localdomain");
				System.out.println("Recuperation de dupont : SUCCES");
				System.out.println(attrs.get("givenname"));

			} catch (NamingException e) {
				System.out.println("Recuperation de dupont : ECHEC");
				e.printStackTrace();
			}
		} catch (NamingException e) {
			System.out.println("Connexion au serveur : ECHEC");
			e.printStackTrace();
		}
		LDAP ldap = new LDAP();

	}
	public static String retrieveinfoProf(DirContext contexte, String user) {
		try {
			
			Attributes attrs = contexte.getAttributes("uid="+user+",ou=Professor, ou=people,dc=localdomain");
			System.out.println("Recuperation du user : SUCCES");
			System.out.println(attrs.get("cn"));
			Attribute result = attrs.get("cn");

		} catch (NamingException e) {
			System.out.println("Recuperation du user : ECHEC");
			e.printStackTrace();
		}
		return ;
	}
	public static LDAPConnection getConnection() throws LDAPException {
	    // host, port, username and password
	    return new LDAPConnection("com.example.local", 389, "Administrator@com.example.local", "admin");
	}
//    
//	public static List<SearchResultEntry> getResults(LDAPConnection connection, String baseDN, String filter) throws LDAPSearchException {
//	    SearchResult searchResult;
//
//	    if (connection.isConnected()) {
//	        searchResult = connection.search(baseDN, SearchScope.ONE, filter);
//
//	        return searchResult.getSearchEntries();
//	    }
//
//	    return null;
//	}
//	
//	String baseDN = "DC=com,DC=example,DC=local";
//	String filter = "(&(|(objectClass=organizationalUnit)(objectClass=container)))";
//
//	LDAPConnection connection = getConnection();        
//	List<SearchResultEntry> results = getResults(connection, baseDN, filter);
}
