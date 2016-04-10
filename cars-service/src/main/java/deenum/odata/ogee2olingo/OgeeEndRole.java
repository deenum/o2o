package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeEndRole {
	
	OgeeEntitySet entitySet = null;
	OgeeEnd role = null;
	public OgeeEntitySet getEntitySet() {
		return /* DEBUG.nullWarning */(entitySet);
	}
	OgeeEndRole setEntitySet(OgeeEntitySet entitySet) {
		this.entitySet = entitySet; 
		return /* DEBUG.nullWarning */(this);
	}
	public OgeeEnd getRole() {
		return /* DEBUG.nullWarning */(role);
	}
	OgeeEndRole setRole(OgeeEnd role) {
		this.role = role; 
		return /* DEBUG.nullWarning */(this);
	}
	
	
}
