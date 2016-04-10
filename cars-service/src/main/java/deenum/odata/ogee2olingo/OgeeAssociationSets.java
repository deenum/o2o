package deenum.odata.ogee2olingo;

import java.util.HashMap;
import java.util.Iterator;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeAssociationSets implements Iterable<OgeeAssociationSet> {
	HashMap<String, OgeeAssociationSet> associationSets = new HashMap<String, OgeeAssociationSet>();
	
	public OgeeAssociationSets() {};
	
	OgeeAssociationSets addAssociationSet(OgeeAssociationSet associationSet) {
		associationSets.put(associationSet.getName(), associationSet); 
		return /* DEBUG.nullWarning */(this);
	}
	
	public OgeeAssociationSet getAssociationSet(String name) {
		if(associationSets.containsKey(name)) {
			return /* DEBUG.nullWarning */(associationSets.get(name));
		}
		System.out.println("return null");
		return /* DEBUG.nullWarning */(null);
	}
	
	@Override
	public Iterator<OgeeAssociationSet> iterator() {
		return /* DEBUG.nullWarning */(associationSets.values().iterator());
	}

}
