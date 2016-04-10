package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeProperty {
	boolean key = false;
	boolean nullable = true;
	String name = null;
	String type = null;

	public String getName() {
		return /* DEBUG.nullWarning */(name);
	}

	OgeeProperty setName(String name) {
		this.name = name; 
		return /* DEBUG.nullWarning */(this);
	}
	
	public String getType() {
		return /* DEBUG.nullWarning */(type);
	}

	OgeeProperty setType(String type) {
		this.type = type; 
		return /* DEBUG.nullWarning */(this);
	}

	public boolean isKey() {
		return /* DEBUG.nullWarning */(key);
	}

	OgeeProperty setKey(boolean key) {
		this.key = key; 
		return /* DEBUG.nullWarning */(this);
	}

	public boolean isNullable() {
		return /* DEBUG.nullWarning */(nullable);
	}

	OgeeProperty setNullable(boolean nullable) {
		this.nullable = nullable; 
		return /* DEBUG.nullWarning */(this);
	}
	
	
	
	
}
