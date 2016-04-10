package deenum.odata.ogee2olingo;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.olingo.odata2.api.exception.ODataException;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeAssociations implements Iterable<OgeeAssociation> {
	HashMap<String, OgeeAssociation> associations = new HashMap<String, OgeeAssociation>();
	
	public OgeeAssociations() {};
	
	OgeeAssociations addAssociation(OgeeAssociation association) {
		associations.put(association.getName(), association); 
		return /* DEBUG.nullWarning */(this);
	}
	
	public OgeeAssociation getAssociation(String name) throws ODataException {
		if(associations.containsKey(name)) {
			return /* DEBUG.nullWarning */(associations.get(name));
		} else {
			throw new ODataException("OgeeAssociation getAssociation "+name);
		}
	}

	@Override
	public Iterator<OgeeAssociation> iterator() {
		return /* DEBUG.nullWarning */(associations.values().iterator());
	}
}
