
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.google.gson.Gson;

public class UserList {

	public void totalUser(String ecole) throws NamingException {
		int port = 0;
		List<User> listusers = new ArrayList<>();
		if (ecole.toLowerCase().equals("ensiie")) {
			port = 2349;
		} else if (ecole.toLowerCase().equals("epita")) {
			port = 13076;
		} else if (ecole.toLowerCase().equals("isep")) {
			port = 2772;
		}
		System.out.println("-------------------------------------------");
		System.out.println("Informations des users de l'école :" + ecole);
		System.out.println("-------------------------------------------");
		System.out.println("-------------------------------------------");

		Properties initialProperties = new Properties();
		initialProperties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		initialProperties.put(Context.PROVIDER_URL, "ldap://localhost:" + port);
		initialProperties.put(Context.SECURITY_PRINCIPAL, "CN=Directory Manager");
		initialProperties.put(Context.SECURITY_CREDENTIALS, "password");
		DirContext context = new InitialDirContext(initialProperties);

		String searchFilter = "(objectClass=inetorgperson)";
		String[] requiredAttributes = { "uid", "givenname", "sn", "userpassword", "entrydn", "parentid",
				"modifytimestamp" };
		SearchControls controls = new SearchControls();
		controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		controls.setReturningAttributes(requiredAttributes);
		// On fait une recherche dans tout l'annuaire, on ne se situe pas dans des
		// organisations units
		//
		NamingEnumeration<?> users = context.search("dc=localdomain", searchFilter, controls);
		SearchResult searchResult = null;
		String uid = null;
		String givenname = null;
		String sn = null;
		String password = null;
		String entrydn = null;
		String parentid = null;
		String modifytimestamp;
		

		while (users.hasMore()) {
			searchResult = (SearchResult) users.next();
			Attributes attr = searchResult.getAttributes();

			uid = attr.get("uid").get(0).toString();
			sn = attr.get("sn").get(0).toString();
			givenname = attr.get("givenname").get(0).toString();
			entrydn = attr.get("entrydn").get(0).toString();
			parentid = attr.get("parentid").get(0).toString();
			password = attr.get("userpassword").get(0).toString();
			modifytimestamp = attr.get("modifytimestamp").get(0).toString();
			
			User user = new User();
			user.setEntrydn(entrydn);
			user.setGivenname(givenname);
			user.setModifytimestamp(modifytimestamp);
			user.setParentid(parentid);
			user.setPassword(password);
			user.setSn(sn);
			user.setUid(uid);
			listusers.add(user);
			
			System.out.println("Uid = " + user.getUid());
			System.out.println("Firstname = " + givenname);
			System.out.println("Lastname = " + sn);
			System.out.println("Chemin d'accès LDAP = " + entrydn);
			System.out.println("Password = " + password);
			System.out.println("Id du parent = " + parentid);
			System.out.println("modifytimestamp =" + modifytimestamp);

			System.out.println("-------------------------------------------");

		}
		String json = new Gson().toJson(listusers);
		System.out.println(json);

	}

	public static void main(String[] args) throws NamingException {
		UserList sample = new UserList();
		sample.totalUser("ensiie");
		sample.totalUser("epita");
		sample.totalUser("isep");

	}

}
