package deenum.odata.ogee2olingo;

import java.util.HashMap;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeContainer {
	String name = null;
	OgeeEntitySets entitySets = new OgeeEntitySets();
	OgeeAssociationSets associationSets = new OgeeAssociationSets();
	
	public String getName() {
		return /* DEBUG.nullWarning */(name);
	}

	OgeeContainer setName(String name) {
		this.name = name; 
		return /* DEBUG.nullWarning */(this);
	}

	public OgeeEntitySets entitySets() {
		return /* DEBUG.nullWarning */(entitySets);
	}

	public OgeeAssociationSets associationSets() {
		return /* DEBUG.nullWarning */(associationSets);
	}
	
	
	
	
	
}
