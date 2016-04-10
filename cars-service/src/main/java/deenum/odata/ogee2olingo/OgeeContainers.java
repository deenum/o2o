package deenum.odata.ogee2olingo;

import java.util.HashMap;
import java.util.Iterator;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeContainers implements Iterable<OgeeContainer>{
	HashMap<String, OgeeContainer> containers = new HashMap<String, OgeeContainer>();
	private String defaultContainer = null;
	
	public OgeeContainers() {};
	
	OgeeContainers addContainer(OgeeContainer container) {
		containers.put(container.getName(), container); 
		defaultContainer = container.getName(); 
		return /* DEBUG.nullWarning */(this);
	}
	
	public OgeeContainer getContainer(String name) {
		if(containers.containsKey(name)) {
			return /* DEBUG.nullWarning */(containers.get(name));
		}
		System.out.println("return null");
		return /* DEBUG.nullWarning */(null);
	}
	
	public OgeeContainer getDefaultContainer() {
		if(containers.containsKey(defaultContainer)) {
			return containers.get(defaultContainer);
		}
		System.out.println("return null");
		return /* DEBUG.nullWarning */(null);
	}
	

	@Override
	public Iterator<OgeeContainer> iterator() {
		return containers.values().iterator();
	}
}
