package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeEntity {
	String name = null;

	public String getName() {
		return /* DEBUG.nullWarning */(name);
	}

	public OgeeEntity setName(String name) {
		this.name = name; 
		return /* DEBUG.nullWarning */(this);
	}
	
	//assocs
	private OgeeProperties properties = new OgeeProperties();
	public OgeeProperties properties() {
		return /* DEBUG.nullWarning */(properties);
	}
	
	private OgeeNavigationProperties propertiesNavigation = new OgeeNavigationProperties();
	public OgeeNavigationProperties navigationProperties() {
		return /* DEBUG.nullWarning */(propertiesNavigation);
	}
	
}
