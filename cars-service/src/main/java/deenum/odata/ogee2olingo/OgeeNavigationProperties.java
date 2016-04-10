package deenum.odata.ogee2olingo;

import java.util.HashMap;
import java.util.Iterator;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeNavigationProperties implements Iterable<OgeeNavigationProperty> {
	HashMap<String, OgeeNavigationProperty> propertiesNavigation = new HashMap<String, OgeeNavigationProperty>();
	
	public OgeeNavigationProperties() {};
	
	OgeeNavigationProperties addNavigationProperty(OgeeNavigationProperty property) {
		propertiesNavigation.put(property.getName(), property); 
		return /* DEBUG.nullWarning */(this);
	}
	
	public OgeeNavigationProperty getNavigationProperty(String name) {
		if(propertiesNavigation.containsKey(name)) {
			return /* DEBUG.nullWarning */(propertiesNavigation.get(name));
		}
		System.out.println("return null");
		return /* DEBUG.nullWarning */(null);
	}

	@Override
	public Iterator<OgeeNavigationProperty> iterator() {
		return /* DEBUG.nullWarning */(propertiesNavigation.values().iterator());
	}
}
