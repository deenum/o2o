package deenum.odata.ogee2olingo;

import java.util.HashMap;
import java.util.Iterator;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeEntitySets implements Iterable<OgeeEntitySet> {
	HashMap<String, OgeeEntitySet> entitySets = new HashMap<String, OgeeEntitySet>();
	
	public OgeeEntitySets() {};
	
	OgeeEntitySets addEntitySet(OgeeEntitySet entitySet) {
		entitySets.put(entitySet.getName(), entitySet); 
		return /* DEBUG.nullWarning */(this);
	}
	
	public OgeeEntitySet getEntitySet(String name) {
		if(entitySets.containsKey(name)) {
			return /* DEBUG.nullWarning */(entitySets.get(name));
		}
		System.out.println("return null");
		return /* DEBUG.nullWarning */(null);
	}
	
	@Override
	public Iterator<OgeeEntitySet> iterator() {
		return /* DEBUG.nullWarning */(entitySets.values().iterator());
	}
}
