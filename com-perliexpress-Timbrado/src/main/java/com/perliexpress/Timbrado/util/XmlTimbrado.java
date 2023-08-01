package com.perliexpress.Timbrado.util;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.perliexpress.Timbrado.service.impl.TimbrarServiceImpl;

public class XmlTimbrado {
	String dtFechaTimbrado;
    String sSelloDigitalSAT; 
    String sCertificadoSAT;
	  public String xmlTimbre(String decodedXmlFinal,String decodedString) throws Exception
	    {
	   
		
	 
	 String xml = decodedString;
	 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  // Generador de constructor de objetos XML
	 DocumentBuilder builder = factory.newDocumentBuilder(); 
	 org.w3c.dom.Document docSellado = builder.parse(new InputSource(new StringReader(xml))); //aqui le paso al Document
	 docSellado.getDocumentElement().normalize();
	 
	 XPath xPathh = XPathFactory.newInstance().newXPath();
	 String etComprobanteh = "/TimbreFiscalDigital";
	 NodeList nodeListSellos = (NodeList) xPathh.compile(etComprobanteh).evaluate(docSellado, XPathConstants.NODESET);        
	 Element TimbreFiscalDigital = (Element) nodeListSellos.item(0); 

	 
	 String xmlF = decodedXmlFinal;
	 
	 DocumentBuilderFactory factorys = DocumentBuilderFactory.newInstance(); 
	 DocumentBuilder builders = factorys.newDocumentBuilder(); 
	 org.w3c.dom.Document xmlFin = builders.parse(new InputSource(new StringReader(xmlF)));
	 xmlFin.getDocumentElement().normalize();
	 
	
	 
	 XPath xPathhs = XPathFactory.newInstance().newXPath();
	 String etComprobantehs = "/Comprobante/Complemento";
	 NodeList nodeListTimbrado = (NodeList) xPathhs.compile(etComprobantehs).evaluate(xmlFin, XPathConstants.NODESET);        
	 Element Comprobantec = (Element) nodeListTimbrado.item(0); 
	 
	 //SI NO EXISTE LA ETIQUETA /Comprobante LA AGREGA
	
	 if(nodeListTimbrado.getLength()==0) {
		 XPath etFac = XPathFactory.newInstance().newXPath();
		 String etComprobanteFac = "/Comprobante";
		 NodeList nodeEtFact = (NodeList) etFac.compile(etComprobanteFac).evaluate(xmlFin, XPathConstants.NODESET);        
		 Element etComFac = (Element) nodeEtFact.item(0); 
		 Element etf = xmlFin.createElement("cfdi:Complemento");
		 etComFac.appendChild(etf);
		 
		 XPath xPathhs2 = XPathFactory.newInstance().newXPath();
		 String etComprobantehs2 = "/Comprobante/Complemento";
		 NodeList nodeListTimbrado2 = (NodeList) xPathhs2.compile(etComprobantehs2).evaluate(xmlFin, XPathConstants.NODESET);        
		 Element Comprobantec2 = (Element) nodeListTimbrado2.item(0); 
		 Element tfd2 = xmlFin.createElement("tfd:TimbreFiscalDigital");
		 Comprobantec2.appendChild(tfd2 );
		 // String a="http://www.w3.org/2001/XMLSchema-instance";
		    ((Element)tfd2).setAttribute("xsi:schemaLocation","http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/TimbreFiscalDigital/TimbreFiscalDigitalv11.xsd");
		    ((Element)tfd2).setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
		    ((Element)tfd2).setAttribute("xmlns:tfd","http://www.sat.gob.mx/TimbreFiscalDigital");
		    ((Element)tfd2).setAttribute("SelloSAT",TimbreFiscalDigital.getAttribute("SelloSAT"));
		    ((Element)tfd2).setAttribute("NoCertificadoSAT",TimbreFiscalDigital.getAttribute("NoCertificadoSAT"));
		    ((Element)tfd2).setAttribute("SelloCFD",TimbreFiscalDigital.getAttribute("SelloCFD"));
		    ((Element)tfd2).setAttribute("RfcProvCertif",TimbreFiscalDigital.getAttribute("RfcProvCertif"));
		    ((Element)tfd2).setAttribute("FechaTimbrado",TimbreFiscalDigital.getAttribute("FechaTimbrado"));
		    ((Element)tfd2).setAttribute("UUID",TimbreFiscalDigital.getAttribute("UUID"));
		    ((Element)tfd2).setAttribute("Version",TimbreFiscalDigital.getAttribute("Version"));
		 
	 }else {
		    Element tfd = xmlFin.createElement("tfd:TimbreFiscalDigital");
		    Comprobantec.appendChild(tfd );
		    //String a="http://www.w3.org/2001/XMLSchema-instance";
		    ((Element)tfd).setAttribute("xsi:schemaLocation","http://www.sat.gob.mx/TimbreFiscalDigital http://www.sat.gob.mx/sitio_internet/cfd/TimbreFiscalDigital/TimbreFiscalDigitalv11.xsd");
		    ((Element)tfd).setAttribute("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
		    ((Element)tfd).setAttribute("xmlns:tfd","http://www.sat.gob.mx/TimbreFiscalDigital");
		    ((Element)tfd).setAttribute("SelloSAT",TimbreFiscalDigital.getAttribute("SelloSAT"));
		    ((Element)tfd).setAttribute("NoCertificadoSAT",TimbreFiscalDigital.getAttribute("NoCertificadoSAT"));
		    ((Element)tfd).setAttribute("SelloCFD",TimbreFiscalDigital.getAttribute("SelloCFD"));
		    ((Element)tfd).setAttribute("RfcProvCertif",TimbreFiscalDigital.getAttribute("RfcProvCertif"));
		    ((Element)tfd).setAttribute("FechaTimbrado",TimbreFiscalDigital.getAttribute("FechaTimbrado"));
		    ((Element)tfd).setAttribute("UUID",TimbreFiscalDigital.getAttribute("UUID"));
		    ((Element)tfd).setAttribute("Version",TimbreFiscalDigital.getAttribute("Version"));
	 }
	 
	 
	 
	     dtFechaTimbrado=TimbreFiscalDigital.getAttribute("FechaTimbrado");
	     sSelloDigitalSAT=TimbreFiscalDigital.getAttribute("SelloSAT"); 
	     sCertificadoSAT=TimbreFiscalDigital.getAttribute("NoCertificadoSAT");
	   
	    
	     
	        DOMSource domSource = new DOMSource(xmlFin);
	        StringWriter writer = new StringWriter();
	        StreamResult result = new StreamResult(writer);
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer transformer = tf.newTransformer();
	        transformer.transform(domSource, result);
	        String resultadoXML = writer.toString();
	       
	        
	        obeterselloSat();   
	        
			return resultadoXML; 
	 
	    }
	  public HashMap<String,String> obeterselloSat(){
			String FechaTimbrado=dtFechaTimbrado;
			String SelloDigitalSAT=sSelloDigitalSAT;
			String CertificadoSAT=sCertificadoSAT;
	      HashMap<String, String> dtbl = new HashMap<String,String>();
	      dtbl.put("dtFechaTimbrado", FechaTimbrado);
	      dtbl.put("sSelloDigitalSAT", SelloDigitalSAT);
	      dtbl.put("sCertificadoSAT", CertificadoSAT);
	    	return dtbl;
	    }
	  

	
}
