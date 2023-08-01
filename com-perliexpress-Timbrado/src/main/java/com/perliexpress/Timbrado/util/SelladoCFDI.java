package com.perliexpress.Timbrado.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.ssl.PKCS8Key;
import org.apache.xerces.impl.dv.util.Base64;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class SelladoCFDI {
	String sSelloDigitalCFDI;
	String sCadenaOriginal;
	String sCertificadoDigital;

	public String crearComprobante(String xml) throws Exception {

		/*
		 * //Extraer archivos .cer y .key PRODUCCION
		 * 
		 * File cer = new File("C:\\SelloMysuite\\00001000000502828869.cer");
		 * 
		 * File key = new
		 * File("C:\\SelloMysuite\\CSD_CDMX_PEX910515FV0_20200121_093448.key");
		 */

		// Extraer archivos .cer y .key PRUEBAS
		File cer = new File("\\\\servarchivos\\Sistemas_MariaJose\\2023\\escritorio\\SelloMysuite\\JES900109Q90_TEST_CSD_30001000000500003444.cer");
		File key = new File("\\\\servarchivos\\Sistemas_MariaJose\\2023\\escritorio\\SelloMysuite\\JES900109Q90_TEST_CSD_30001000000500003444.key");

		// Agregar certificado y no. de certificado al comprobante, por medio del
		// archivo .cer del contribuyente.
		X509Certificate x509Certificado = getX509Certificate(cer);
		String certificado = getCertificadoBase64(x509Certificado);
		String noCertificado = getNoCertificado(x509Certificado);

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); // Generador de constructor de objetos
																				// XML
		DocumentBuilder builder = factory.newDocumentBuilder();

		org.w3c.dom.Document document = builder.parse(new InputSource(new StringReader(xml))); // aqui le paso al
																								// Document
		document.getDocumentElement().normalize();

		XPath xPath = XPathFactory.newInstance().newXPath();
		// La ruta del elemento que deseamos, para este omitir el prefijo cfdi:
		String etComprobante = "/Comprobante";
		// Obtenemos todos los nodos que empatan con la ruta que indicamos
		NodeList nodeLisComprobante = (NodeList) xPath.compile(etComprobante).evaluate(document,
				XPathConstants.NODESET);
		// Obtenemos el primer elemento de esa lista
		Element Comprobante = (Element) nodeLisComprobante.item(0);

		// Agrega certificado y noCertificado al xml
		((Element) Comprobante).setAttribute("NoCertificado", noCertificado);
		((Element) Comprobante).setAttribute("Certificado", certificado);

		// Agrega emisor en ambiente de pruebas para Mysuite desde PrliWeb para postman
				// no se utiliza
				String etEmisor = "/Comprobante/Emisor";
				NodeList nodeLisEmisor = (NodeList) xPath.compile(etEmisor).evaluate(document, XPathConstants.NODESET);
				Element Comprobante2 = (Element) nodeLisEmisor.item(0);
				System.out.println("Comprobante2: " + Comprobante2);
				((Element) Comprobante2).setAttribute("Nombre", "JIMENEZ ESTRADA SALAS A A");
				((Element) Comprobante2).setAttribute("Rfc", "JES900109Q90");
				((Element) Comprobante).setAttribute("LugarExpedicion", "01030");

				
				String etReceptor = "/Comprobante/Receptor";
				NodeList nodeLisReceptor = (NodeList) xPath.compile(etReceptor).evaluate(document, XPathConstants.NODESET);
				Element Comprobante5 = (Element) nodeLisReceptor.item(0);
				System.out.println("Comprobante2: " + Comprobante5);
				((Element) Comprobante5).setAttribute("Nombre", "JIMENEZ ESTRADA SALAS A A");
				((Element) Comprobante5).setAttribute("Rfc", "JES900109Q90");
				((Element) Comprobante5).setAttribute("DomicilioFiscalReceptor", "01030");
			
				// Para Rep Pruebas
				String etUbicacion = "/Comprobante/Complemento/CartaPorte/Ubicaciones/Ubicacion";
				NodeList nodeLisUbicacion = (NodeList) xPath.compile(etUbicacion).evaluate(document, XPathConstants.NODESET);
				Element Comprobante3 = (Element) nodeLisUbicacion.item(0);
				System.out.println("Comprobante3: " + Comprobante3);
				((Element) Comprobante3).setAttribute("RFCRemitenteDestinatario", "JES900109Q90");

				
				String etTiposFigura = "/Comprobante/Complemento/CartaPorte/FiguraTransporte/TiposFigura";
				NodeList nodeLisOper = (NodeList) xPath.compile(etTiposFigura).evaluate(document, XPathConstants.NODESET);
				Element Comprobante4 = (Element) nodeLisOper.item(0);
				System.out.println("Comprobante4: " + Comprobante4);
				((Element) Comprobante4).setAttribute("RFCFigura", "XAMA620210DQ5");
				((Element) Comprobante4).setAttribute("NombreFigura", "ALBA XKARAJAM MENDEZ");
				// Aqui termina
		  
		DOMSource domSource = new DOMSource(document);
		StringWriter writer = new StringWriter();
		StreamResult result = new StreamResult(writer);
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.transform(domSource, result);
		String resultadoXML = writer.toString();

		// System.out.println("resultadoXML A: "+resultadoXML);

		String cadxml = resultadoXML;
		String cadenaoriginal = "";
		PrivateKey llavePrivada = null;
		String selloDigital = "";

		try {
			cadenaoriginal = generarCadenaOriginal(cadxml);

		} catch (TransformerException ex) {
			Logger.getLogger(SelladoCFDI.class.getName()).log(Level.SEVERE, null, ex);
		}

		// Utilizar el archivo .key del contribuyente, ademas de la contraseña correspondiente
		// produccion
		// llavePrivada = getPrivateKey(key,"Tacuba16");
		// pruebas
		llavePrivada = getPrivateKey(key, "12345678a");

		// Asignar el sello digital como texto
		selloDigital = generarSelloDigital(llavePrivada, cadenaoriginal);
		// System.out.println("selloDigital: "+selloDigital);

		DocumentBuilderFactory factoryg = DocumentBuilderFactory.newInstance(); // un factory
		DocumentBuilder builderg = factoryg.newDocumentBuilder(); // el documento
		org.w3c.dom.Document XmlSellado = builderg.parse(new InputSource(new StringReader(resultadoXML))); // aqui le
																											// paso al
																											// Document
		XmlSellado.getDocumentElement().normalize();

		XPath xPathh = XPathFactory.newInstance().newXPath();
		// La ruta del elemento que deseamos
		String etComprobanteh = "/Comprobante";
		NodeList nodeListSellos = (NodeList) xPathh.compile(etComprobanteh).evaluate(XmlSellado,
				XPathConstants.NODESET);
		Element Comprobantec = (Element) nodeListSellos.item(0);
		// Agregamos el sello al xml
		((Element) Comprobantec).setAttribute("Sello", selloDigital);

		sSelloDigitalCFDI = selloDigital;
		sCadenaOriginal = cadenaoriginal;
		sCertificadoDigital = noCertificado;

		obetersello();
		DOMSource domSources = new DOMSource(XmlSellado);
		return base64EncodeXml(domSources);

	}

	public HashMap<String, String> obetersello() {
		String SelloDigitalCFDI = sSelloDigitalCFDI;
		String CadenaOriginal = sCadenaOriginal;
		String CertificadoDigital = sCertificadoDigital;
		HashMap<String, String> datostbl = new HashMap<String, String>();
		datostbl.put("sSelloDigitalCFDI", SelloDigitalCFDI);
		datostbl.put("sCadenaOriginal", CadenaOriginal);
		datostbl.put("sCertificadoDigital", CertificadoDigital);
		return datostbl;
	}

	public String base64EncodeXml(DOMSource domSources) throws TransformerException {
		StringWriter writers = new StringWriter();
		StreamResult results = new StreamResult(writers);
		TransformerFactory tfs = TransformerFactory.newInstance();
		Transformer transformers = tfs.newTransformer();
		transformers.transform(domSources, results);
		String XmlSellados = writers.toString();

		byte[] encode = org.apache.commons.codec.binary.Base64
				.encodeBase64(XmlSellados.getBytes(StandardCharsets.UTF_8));
		String encodeXml = new String(encode, StandardCharsets.UTF_8);

		return encodeXml;

	}

	// Metodos de sellado

	private X509Certificate getX509Certificate(final File certificateFile) throws CertificateException, IOException {
		FileInputStream is = null;
		try {
			is = new FileInputStream(certificateFile);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			return (X509Certificate) cf.generateCertificate(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	private String getCertificadoBase64(final X509Certificate cert) throws CertificateEncodingException {
		return new String(Base64.encode(cert.getEncoded()));

	}

	private String getNoCertificado(final X509Certificate cert) {
		BigInteger serial = cert.getSerialNumber();
		byte[] sArr = serial.toByteArray();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < sArr.length; i++) {
			buffer.append((char) sArr[i]);
		}
		return buffer.toString();
	}

	private PrivateKey getPrivateKey(final File keyFile, final String password)
			throws GeneralSecurityException, IOException {
		FileInputStream in = new FileInputStream(keyFile);
		PKCS8Key pkcs8 = new PKCS8Key(in, password.toCharArray());

		byte[] decrypted = pkcs8.getDecryptedBytes();
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decrypted);
		PrivateKey pk = null;

		if (pkcs8.isDSA()) {
			pk = KeyFactory.getInstance("DSA").generatePrivate(spec);
		} else if (pkcs8.isRSA()) {
			pk = KeyFactory.getInstance("RSA").generatePrivate(spec);
		}

		pk = pkcs8.getPrivateKey();
		return pk;
	}

	private String generarSelloDigital(final PrivateKey key, final String cadenaOriginal)
			throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {

		Signature sing = Signature.getInstance("SHA256withRSA");
		sing.initSign(key, new SecureRandom());

		try {
			sing.update(cadenaOriginal.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(SelladoCFDI.class.getName()).log(Level.SEVERE, null, ex);
		}

		byte[] signature = sing.sign();
		return new String(Base64.encode(signature));

	}

	private String generarCadenaOriginal(final String xml) throws TransformerException {
		// StreamSource streamSource = new
		// StreamSource("C:/SelloMysuite/cadenaoriginal_4_0.xslt");
		StreamSource streamSource = new StreamSource("\\\\servarchivos\\Sistemas_MariaJose\\2023\\escritorio\\SelloMysuite\\cadenaoriginal_4_0.xslt");
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer xlsTransformer = transformerFactory.newTransformer(streamSource);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		xlsTransformer.transform(new StreamSource(new StringReader(xml)), new StreamResult(output));

		String resultado = "";

		try {
			resultado = output.toString("UTF-8");

		} catch (UnsupportedEncodingException ex) {
			Logger.getLogger(SelladoCFDI.class.getName()).log(Level.SEVERE, null, ex);
		}
		return resultado;

	}

}