package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeAssociationSet {
	OgeeEndRole roleFrom = new OgeeEndRole();
	OgeeEndRole roleTo = new OgeeEndRole();
	String name = null;
	OgeeAssociation association = null;
	
	public String getName() {
		return /* DEBUG.nullWarning */(name);
	}
	
	

	public OgeeAssociation getAssociation() {
		return /* DEBUG.nullWarning */(association);
	}



	OgeeAssociationSet setAssociation(OgeeAssociation association) {
		this.association = association; 
		return /* DEBUG.nullWarning */(this);
	}



	OgeeAssociationSet setName(String name) {
		this.name = name; 
		return /* DEBUG.nullWarning */(this); 
	}
	public OgeeEndRole getRoleFrom() {
		return /* DEBUG.nullWarning */(roleFrom);
	}
	public OgeeEndRole getRoleTo() {
		return /* DEBUG.nullWarning */(roleTo);
	}
	
	
}
