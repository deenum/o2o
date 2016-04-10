package deenum.odata.ogee2olingo.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

import deenum.odata.ogee2olingo.OgeeAssociation;
import deenum.odata.ogee2olingo.OgeeAssociationSet;
import deenum.odata.ogee2olingo.OgeeContainer;
import deenum.odata.ogee2olingo.OgeeEnd;
import deenum.odata.ogee2olingo.OgeeEntity;
import deenum.odata.ogee2olingo.OgeeEntitySet;
import deenum.odata.ogee2olingo.OgeeNavigationProperty;
import deenum.odata.ogee2olingo.OgeeReader;
import deenum.odata.ogee2olingo.OgeeProperty;
import deenum.odata.ogee2olingo.OgeeSchema;
import deenum.odata.ogee2olingo.OgeeSchemas;

public class OgeeReaderTest {

	@Test
	public void getDocument_success() throws JDOMException, IOException, Exception {
		
			OgeeReader reader = new OgeeReader();
			
			OgeeSchemas schemas = new OgeeSchemas();
			
			reader.loadSchema(schemas, new SAXBuilder().build(getClass().getResource("/deenum/odata/ogee/models/schema.odata").getFile()));
					//OgeeReader.documentFromFile("src/main/java/deenum/odata/ogee/models/schema.odata"));
			
			OgeeSchema schemaCars = schemas.getSchema("cars");
			assertEquals("cars", schemaCars.getNamespace());
			assertEquals("cars", schemas.getDefaultNamespace());
			
			OgeeEntity entityCar = schemaCars.entities().getEntity("Car");
			assertEquals("Car", entityCar.getName());
			
			OgeeProperty propCar = entityCar.properties().getProperty("CarID");
			assertEquals("CarID", propCar.getName());
			assertEquals("odata:SimpleTypeUsage", propCar.getType());
			assertEquals(true, propCar.isKey());
			//TODO assertEquals(false, propCar.isNullable());
			
			OgeeProperty propColor = entityCar.properties().getProperty("Color");
			assertEquals("Color", propColor.getName());
			assertEquals("odata:SimpleTypeUsage", propColor.getType());
			assertEquals(false, propColor.isKey());
			//TODO assertEquals(true, propCar.isNullable());
			
			OgeeNavigationProperty propNav = entityCar.navigationProperties().getNavigationProperty("Owner");
			assertEquals("Owner", propNav.getName());
			assertEquals("OwnerCar", propNav.getRelationship().getName());
			assertEquals("To_Car",   propNav.getFromRole().getName());
			assertEquals("From_Owner", propNav.getToRole().getName());


			
			OgeeAssociation assoc = schemaCars.associations().getAssociation("OwnerCar");
			assertEquals("OwnerCar", assoc.getName());
			
			OgeeEnd endFrom = assoc.getFrom();
			assertEquals("From_Owner", endFrom.getName());
			assertEquals("Owner", endFrom.getType().getName());

			
			OgeeEnd endTo = assoc.getTo();
			assertEquals("To_Car", endTo.getName());
			
			OgeeContainer def = schemaCars.containers().getContainer("default");
			assertEquals("default", def.getName());
			
			OgeeEntitySet entitySet = def.entitySets().getEntitySet("CarSet");
			assertEquals("CarSet", entitySet.getName());
			
			OgeeEntity entitySetType = entitySet.getType();
			assertEquals("Car", entitySetType.getName());
			
			OgeeAssociationSet associationSet = schemaCars.containers().getContainer("default").associationSets().getAssociationSet("OwnerCarSet");
			assertEquals("OwnerCarSet", associationSet.getName());
			assertEquals("OwnerCar", associationSet.getAssociation().getName());
			assertEquals("From_Owner", associationSet.getRoleFrom().getRole().getName());
			assertEquals("To_Car", associationSet.getRoleTo().getRole().getName());
	
	}

}
