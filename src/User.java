
public class User {

	private String uid, givenname, sn, password, entrydn, parentid, modifytimestamp;

	public User() {
		super();
	}

	public User(String uid, String givenname, String sn, String password, String entrydn, String parentid,
			String modifytimestamp) {
		super();
		this.uid = uid;
		this.givenname = givenname;
		this.sn = sn;
		this.password = password;
		this.entrydn = entrydn;
		this.parentid = parentid;
		this.modifytimestamp = modifytimestamp;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getGivenname() {
		return givenname;
	}

	public void setGivenname(String givenname) {
		this.givenname = givenname;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEntrydn() {
		return entrydn;
	}

	public void setEntrydn(String entrydn) {
		this.entrydn = entrydn;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getModifytimestamp() {
		return modifytimestamp;
	}

	public void setModifytimestamp(String modifytimestamp) {
		this.modifytimestamp = modifytimestamp;
	}

}
