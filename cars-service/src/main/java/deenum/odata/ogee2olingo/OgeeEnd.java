package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeEnd {
	String name = null;
	String multiplicity = null;
	OgeeEntity type = null;
	
	
	
	public OgeeEntity getType() {
		return /* DEBUG.nullWarning */(type);
	}

	public OgeeEnd setType(OgeeEntity type) {
		this.type = type; 
		return /* DEBUG.nullWarning */(this);
	}

	public String getName() {
		return /* DEBUG.nullWarning */(name);
	}

	OgeeEnd setName(String name) {
		this.name = name; 
		return /* DEBUG.nullWarning */(this);
	}
	
	public String getMultiplicity() {
		return /* DEBUG.nullWarning */(multiplicity);
	}

	OgeeEnd setMultiplicity(String multiplicity) {
		this.multiplicity = multiplicity; 
		return this;
	}
	
	public String generateRoleName() {
		return name+"_"+type.getName();
	}
	
}
