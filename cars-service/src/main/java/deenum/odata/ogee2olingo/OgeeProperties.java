package deenum.odata.ogee2olingo;

import java.util.HashMap;
import java.util.Iterator;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeProperties implements Iterable<OgeeProperty> {
	HashMap<String, OgeeProperty> properties = new HashMap<String, OgeeProperty>();
	
	public OgeeProperties() {};
	
	OgeeProperties addProperty(OgeeProperty property) {
		properties.put(property.getName(), property); 
		return /* DEBUG.nullWarning */(this);
	}
	
	public OgeeProperty getProperty(String name) {
		if(properties.containsKey(name)) {
			return /* DEBUG.nullWarning */(properties.get(name));
		}
		System.out.println("return null");
		return /* DEBUG.nullWarning */(null);
	}

	@Override
	public Iterator<OgeeProperty> iterator() {
		return /* DEBUG.nullWarning */(properties.values().iterator());
	}
}
