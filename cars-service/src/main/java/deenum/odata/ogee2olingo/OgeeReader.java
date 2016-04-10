package deenum.odata.ogee2olingo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;  
import org.jdom2.Element;  
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;  


public class OgeeReader {
	Namespace xmi = Namespace.getNamespace("xmi", "http://www.omg.org/XMI");
	Namespace xsi = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
	Namespace al = Namespace.getNamespace("al", "http://eclipse.org/graphiti/mm/algorithms");
	Namespace odata = Namespace.getNamespace("odata", "http://odata/1.0");
	Namespace pi = Namespace.getNamespace("pi", "http://eclipse.org/graphiti/mm/pictograms");
	
	
	static List<Element> getElements(Document docOgee, String xpath) {
		XPathFactory xpfac = XPathFactory.instance();
		List<Element> elements = new ArrayList<Element>();
		XPathExpression<Element> xp = xpfac.compile(xpath, Filters.element());
		for (Element element : xp.evaluate(docOgee)) {
			elements.add(element);
		}
		return elements;
	}
	
	public static Document documentFromFile(String path) throws JDOMException, IOException {		
		SAXBuilder saxBuilder = new SAXBuilder();  
		Document doc = saxBuilder.build(new File(path)); 
        return doc;
	}
	
	static String xmiToXpath(String xmi) {
		// /1/@schemata[namespace='cars']/@associations.0
		
		String xpath = "";
		
		String[] aTags = xmi.split("@");
		for(String tag : aTags) {
			
			if(!tag.startsWith("/")) {
				String[] point = tag.split("\\.");
				if(point.length==2) {
					int pos = Integer.parseInt(point[1].replaceAll("/", ""));
					pos++;
					
					tag = point[0]+"["+pos+"]";
					if(point[1].endsWith("/")) {
						tag +="/";
					}
					//tag += "]";					
					xpath += tag;
				} else {
					xpath += tag.replaceAll("\\[", "[@");
				}		
			}
		}
		
		xpath = "//"+xpath;
		return xpath;
	}
	
	public OgeeSchemas createSchema(String schemaClasspath) {
		  try {
		  OgeeReader reader;
			reader = new OgeeReader();
			OgeeSchemas schemas = new OgeeSchemas();
			reader.loadSchema(schemas, new SAXBuilder().build(getClass().getResource(schemaClasspath).getFile()));
			return schemas;
			
		  } catch(Exception e) {
			  //throw new ODataException(e.getMessage());
		  }
		  return null;
	  }
	
	
	public void loadSchema(OgeeSchemas mSchemas, Document docOgee) throws Exception {
		//schemas lesen...
		String xpathSchemas = "//schemata";
		List<Element> elements = OgeeReader.getElements(docOgee, xpathSchemas);
		for(Element eDiagram : elements) {
			//interpretieren...
			String namespace = eDiagram.getAttribute("namespace").getValue();
			
			//xpath des aktuellen Elements merken...
			String xpathSchema = xpathSchemas+"[@namespace='"+namespace+"']";
			
			//model ergaenzen...
			OgeeSchema mSchema = new OgeeSchema().setNamespace(namespace);
			if(namespace.startsWith("Org.OData")) { continue; } else {
				mSchemas.addSchema(mSchema);
			}
			
			
			
			
			//entities lesen...
			String xpathEntities = xpathSchema+"/entityTypes";
			List<Element> eEntities = OgeeReader.getElements(docOgee, xpathEntities);
			for(Element eEntity : eEntities) {
				//interpretieren...
				String entityName = eEntity.getAttribute("name").getValue();
				
				//xpath des aktuellen Elements merken...
				String xpathEntity = xpathEntities+"[@name='"+entityName+"']";
				
				//model ergaenzen...
				OgeeEntity mEntity = new OgeeEntity().setName(entityName);
				OgeeEntities mEntities = mSchema.entities();
				mEntities.addEntity(mEntity);
				
				//properties lesen...
				String xpathPropertiesWildcard = xpathEntity+"/*";
				List<Element> eProperties = OgeeReader.getElements(docOgee, xpathPropertiesWildcard);
				for(Element eProperty : eProperties) {
					if(eProperty.getName().matches("properties|keys")) {
						
						//interpretieren...
						boolean key = eProperty.getName().equals("keys");
						String name = eProperty.getAttribute("name").getValue();
						boolean nullable = true;
						
						//TODO: doesnt work
						if(eProperty.getName().equals("properties")) {
							nullable = eProperty.getAttribute("nullable").getValue().equals("true");
						}
						
						//xpath des aktuellen Elements merken...
						String xpathProperty = xpathEntity+"/"+eProperty.getName()+"[@name='"+name+"']";
						
						//model ergaenzen...
						OgeeProperty mProperty = new OgeeProperty()
								.setKey(key)
								.setName(name)
								.setNullable(nullable)
						;
						mEntity.properties().addProperty(mProperty);
						
						
						
						//type interpretieren...
						//TODO currently minimal implementation! Complex not implemented yet!
						String xpathType = xpathProperty+"/type";
						List<Element> eTypes = OgeeReader.getElements(docOgee, xpathType);
						for(Element eType : eTypes) {
							//interpretieren...
							String type = eType.getAttribute("type", xsi).getValue();
							
							//model ergaenzen...
							mProperty.setType(type);
						}	
					}	
				}
			}
			
			
			
			//assocs lesen...
			String xpathAssociations = xpathSchema+"/associations";
			List<Element> eAssociations = OgeeReader.getElements(docOgee, xpathAssociations);
			for(Element eAssociation : eAssociations) {
				//interpretieren...
				String name = eAssociation.getAttribute("name").getValue();
				
				//xpath des aktuellen Elements merken...
				String xpathAssociation = xpathAssociations+"[@name='"+name+"']";
				
				//model ergaenzen...
				OgeeAssociation association = new OgeeAssociation();
				association.setName(name);
				mSchema.associations().addAssociation(association);
				
				//ends lesen...
				String xpathEnds = xpathAssociation+"/ends";
				List<Element> eEnds= OgeeReader.getElements(docOgee, xpathEnds);
				for(Element eEnd : eEnds) {
					//interpretieren...
					String endName = eEnd.getAttribute("name").getValue();
					String multiplicity = eEnd.getAttribute("multiplicity").getValue();
					String typeName = OgeeReader.getElements(docOgee, xmiToXpath(eEnd.getAttributeValue("type"))).get(0).getAttributeValue("name");
					
					//xpath des aktuellen Elements merken...
					String xpathEnd = xpathEnds+"[@name='"+endName+"']";
					
					//model ergaenzen...
					OgeeEnd currentEnd = null;
					if(endName.startsWith("To_")) {
						currentEnd = association.getTo();
					} else if(endName.startsWith("From_")) {
						currentEnd = association.getFrom();
					}
					currentEnd.setType(mSchema.entities().getEntity(typeName));
					currentEnd.setName(endName);
					currentEnd.setMultiplicity(multiplicity);
				}
			}
			
			
			//entities lesen (zweites mal, diesmal nur fuer navigationproerties)...
			//String xpathEntities = xpathSchema+"/entityTypes";
			//List<Element> eEntities = OgeeReader.getElements(docOgee, xpathEntities);
			for(Element eEntity : eEntities) {
				//interpretieren...
				String entityName = eEntity.getAttribute("name").getValue();
				
				//xpath des aktuellen Elements merken...
				String xpathEntity = xpathEntities+"[@name='"+entityName+"']";
				
				//model ergaenzen...
				OgeeEntity mEntity = mSchema.entities().getEntity(entityName);
								
				//navigationproperties lesen...
				String xpathNavigationProperties = xpathEntity+"/navigationProperties";
				List<Element> eNavigationProperties = OgeeReader.getElements(docOgee, xpathNavigationProperties);
				for(Element eNavigationProperty : eNavigationProperties) {
						
						//interpretieren...
						String name = eNavigationProperty.getAttribute("name").getValue();
						String relationshipName = OgeeReader.getElements(docOgee, xmiToXpath(eNavigationProperty.getAttributeValue("relationship"))).get(0).getAttributeValue("name");
						String fromRoleName = OgeeReader.getElements(docOgee, xmiToXpath(eNavigationProperty.getAttributeValue("fromRole"))).get(0).getAttributeValue("name");
						String toRoleName = OgeeReader.getElements(docOgee, xmiToXpath(eNavigationProperty.getAttributeValue("toRole"))).get(0).getAttributeValue("name");
						
						OgeeAssociation relationship = mSchema.associations().getAssociation(relationshipName);
						
						OgeeEnd endFrom = relationship.getFrom();
						OgeeEnd endTo = relationship.getTo();
						
						System.out.println(mEntity.getName()+" => "+name+" "+relationshipName+" "+fromRoleName+" "+toRoleName);
						
						//xpath des aktuellen Elements merken...
						String xpathNavigationProperty = xpathNavigationProperties+"/"+eNavigationProperty.getName()+"[@name='"+name+"']";
						
						//model ergaenzen...
						OgeeNavigationProperty mProperty = new OgeeNavigationProperty()
								.setName(name)
								.setRelationship(relationship)
								.setFromRole(fromRoleName.equals(endFrom.getName()) ? endFrom : endTo)
								.setToRole    (toRoleName.equals(endFrom.getName()) ? endFrom : endTo)
						;
						mEntity.navigationProperties().addNavigationProperty(mProperty);	
					}	
				}
			
			
			
			//containers lesen...
			String xpathContainers = xpathSchema+"/containers";
			List<Element> eContainers = OgeeReader.getElements(docOgee, xpathContainers);
			for(Element eContainer : eContainers) {
				//interpretieren...
				String name = eContainer.getAttribute("name").getValue();
				
				//xpath des aktuellen Elements merken...
				String xpathContainer = xpathContainers+"[@name='"+name+"']";
				
				//model ergaenzen...
				OgeeContainer mContainer = new OgeeContainer()
						.setName(name)
				;
				mSchema.containers().addContainer(mContainer);
				
				//entitysets lesen...
				String xpathEntitySets = xpathContainer+"/entitySets";
				List<Element> eEntitySets = OgeeReader.getElements(docOgee, xpathEntitySets);
				for(Element eEntitySet : eEntitySets) {
					//interpretieren...
					String setName = eEntitySet.getAttribute("name").getValue();
					String entityTypeName = OgeeReader.getElements(docOgee, xmiToXpath(eEntitySet.getAttributeValue("type"))).get(0).getAttributeValue("name");
					OgeeEntity type = mSchema.entities().getEntity(entityTypeName);
					
					//xpath des aktuellen Elements merken...
					String xpathEntitySet = xpathEntitySets+"[@name='"+name+"']";
					
					//model ergaenzen...
					OgeeEntitySet mEntitySet = new OgeeEntitySet()
							.setName(setName)
							.setType(type)
					;
										
					mContainer.entitySets().addEntitySet(mEntitySet);
					
					
				}
				
				//assocsets lesen...
				String xpathAssociationSets = xpathContainer+"/associationSets ";
				List<Element> eAssociationSets = OgeeReader.getElements(docOgee, xpathAssociationSets);
				for(Element eAssociationSet : eAssociationSets) {
					//interpretieren...
					String setName = eAssociationSet.getAttribute("name").getValue();
					String associationName = OgeeReader.getElements(docOgee, xmiToXpath(eAssociationSet.getAttributeValue("association"))).get(0).getAttributeValue("name");
					OgeeAssociation association = mSchema.associations().getAssociation(associationName);
					
					//xpath des aktuellen Elements merken...
					String xpathAssociationSet = xpathAssociationSets+"[@name='"+setName+"']";
					
					//model ergaenzen...
					OgeeAssociationSet mAssociationSet = new OgeeAssociationSet()
							.setName(setName)
							.setAssociation(association)
					;					
					mContainer.associationSets().addAssociationSet(mAssociationSet);
					
					//ends lesen...
					String xpathEnds = xpathAssociationSet+"/ends";
					List<Element> eEnds= OgeeReader.getElements(docOgee, xpathEnds);
					for(Element eEnd : eEnds) {
						//interpretieren...
						String endName = OgeeReader.getElements(docOgee, xmiToXpath(eEnd.getAttributeValue("role"))).get(0).getAttributeValue("name");
						OgeeEnd end = mSchema.associations().getAssociation(associationName).getEndByName(endName);
						
						String entitySetName = OgeeReader.getElements(docOgee, xmiToXpath(eEnd.getAttributeValue("entitySet"))).get(0).getAttributeValue("name");
						OgeeEntitySet entitySet = mSchema.containers().getContainer(name).entitySets().getEntitySet(entitySetName);
						
						//model ergaenzen...
						OgeeEndRole currentEndRole = null;
						if(endName.startsWith("To_")) {
							currentEndRole = mAssociationSet.getRoleTo();
						} else if(endName.startsWith("From_")) {
							currentEndRole = mAssociationSet.getRoleFrom();
						}
						currentEndRole.setEntitySet(entitySet);
						currentEndRole.setRole(end);
					}
				}
			}
		}
		
	}
	
	public void xpath() {
		
	}
	
	
}
