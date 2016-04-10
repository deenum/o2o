package deenum.odata.ogee2olingo;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeSchema {
	private String namespace = null;

	public String getNamespace() {
		return /* DEBUG.nullWarning */(namespace);
	}

	OgeeSchema setNamespace(String namespace) {
		this.namespace = namespace; 
		return /* DEBUG.nullWarning */(this);
	}
	
	
	private OgeeEntities entities = new OgeeEntities();
	public OgeeEntities entities() {
		return /* DEBUG.nullWarning */(entities);
	}
	
	private OgeeAssociations associations = new OgeeAssociations();
	public OgeeAssociations associations() {
		return /* DEBUG.nullWarning */(associations);
	}
	
	private OgeeContainers containers = new OgeeContainers();
	public OgeeContainers containers() {
		return /* DEBUG.nullWarning */(containers);
	}
}
