package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeEntitySet {
	String name = null;
	OgeeEntity type = null;
	
	public String getName() {
		return /* DEBUG.nullWarning */(name);
	}

	OgeeEntitySet setName(String name) {
		this.name = name; 
		return /* DEBUG.nullWarning */(this);
	}

	public OgeeEntity getType() {
		return /* DEBUG.nullWarning */(type);
	}

	OgeeEntitySet setType(OgeeEntity type) {
		this.type = type; 
		return /* DEBUG.nullWarning */(this);
	}
	
	
}
