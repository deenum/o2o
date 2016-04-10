package deenum.odata.ogee2olingo;

import java.util.HashMap;
import java.util.Iterator;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeEntities implements Iterable<OgeeEntity> {
	HashMap<String, OgeeEntity> entities = new HashMap<String, OgeeEntity>();
	
	public OgeeEntities() {};
	
	OgeeEntities addEntity(OgeeEntity entity) {
		entities.put(entity.getName(), entity); 
		return /* DEBUG.nullWarning */(this);
	}
	
	public OgeeEntity getEntity(String name) {
		if(entities.containsKey(name)) {
			return /* DEBUG.nullWarning */(entities.get(name));
		}
		System.out.println("return null");
		return /* DEBUG.nullWarning */(null);
	}
	

	@Override
	public Iterator<OgeeEntity> iterator() {
		return /* DEBUG.nullWarning */(entities.values().iterator());
	}

	
}
