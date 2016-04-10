package deenum.odata.ogee2olingo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import deenum.odata.ogee2olingo.test.DEBUG;

public class OgeeSchemas implements Iterable<OgeeSchema> {
	private Map<String, OgeeSchema> schemas = new HashMap<String, OgeeSchema>();
	private String defaultNamespace = null;
	
	public OgeeSchemas() {};
	
	OgeeSchemas addSchema(OgeeSchema schema) {
		schemas.put(schema.getNamespace(), schema); 
		return this;
	}
	
	
	
	public String getDefaultNamespace() {
		return defaultNamespace;
	}

	public OgeeSchema getSchema(String namespace) {
		if(schemas.containsKey(namespace)) {
			defaultNamespace = namespace;
			return schemas.get(namespace);
		}
		System.out.println("return null");
		return null;
	}
	
	public OgeeSchema getDefaultSchema() {
		if(schemas.containsKey(defaultNamespace)) {
			return schemas.get(defaultNamespace);
		}
		System.out.println("return null");
		return null;
	}
	
	@Override
	public Iterator<OgeeSchema> iterator() {
		return schemas.values().iterator();
	}
	
}
