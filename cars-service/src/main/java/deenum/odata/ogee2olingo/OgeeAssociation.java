package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeAssociation {
	String name = null;

	OgeeEnd From = new OgeeEnd(); 
	OgeeEnd To = new OgeeEnd();
	
	public String getName() {
		return /* DEBUG.nullWarning */(name);
	}
	
	public OgeeEnd getEndByName(String name) {
		if(From.getName().equals(name)) {
			return From;
		} else if(To.getName().equals(name)) {
			return To;
		}
		return /* DEBUG.nullWarning */(null);
	}

	public OgeeAssociation setName(String name) {
		this.name = name; 
		return /* DEBUG.nullWarning */(this);
	}

	public OgeeEnd getFrom() {
		return From;
	}

	OgeeAssociation setFrom(OgeeEnd from) {
		From = from; 
		return /* DEBUG.nullWarning */(this);
	}

	public OgeeEnd getTo() {
		return To;
	}

	OgeeAssociation setTo(OgeeEnd to) {
		To = to; 
		return /* DEBUG.nullWarning */(this);
	}
	
	
}
