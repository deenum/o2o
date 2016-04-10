package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeNavigationProperty {
	String name = null;
	OgeeAssociation relationship = null;
	OgeeEnd fromRole = null;
	OgeeEnd toRole = null;
	
	
	
	public OgeeEnd getFromRole() {
		return /* DEBUG.nullWarning */(fromRole);
	}

	public OgeeNavigationProperty setFromRole(OgeeEnd fromRole) {
		this.fromRole = fromRole; 
		return /* DEBUG.nullWarning */(this);
	}

	public OgeeEnd getToRole() {
		return /* DEBUG.nullWarning */(toRole);
	}

	public OgeeNavigationProperty setToRole(OgeeEnd toRole) {
		this.toRole = toRole; 
		return /* DEBUG.nullWarning */(this);
	}

	public OgeeAssociation getRelationship() {
		return /* DEBUG.nullWarning */(relationship);
	}

	public OgeeNavigationProperty setRelationship(OgeeAssociation relationship) {
		this.relationship = relationship; 
		return /* DEBUG.nullWarning */(this);
	}

	public String getName() {
		return /* DEBUG.nullWarning */(name);
	}

	OgeeNavigationProperty setName(String name) {
		this.name = name; 
		return /* DEBUG.nullWarning */(this);
	}
	
		
}
