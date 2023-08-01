package com.perliexpress.Timbrado.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignatureMethod;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.keyinfo.X509IssuerSerial;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


//Comentarios Daniel 28 Junio 2023

public class FirmarXML 
{ 
	
public String generateXMLDigitalSignature(String xml) throws TransformerException, ParserConfigurationException, SAXException, IOException, XPathExpressionException, CertificateException, GeneralSecurityException, MarshalException, XMLSignatureException {
        //Obtenga el objeto del documento XML
     
	/*
	 * //Extraer archivos .cer y .key PRODUCCION
	 * 
	 * File cer = new File("C:\\SelloMysuite\\00001000000502828869.cer");
	 * 
	 * File key = new
	 * File("C:\\SelloMysuite\\CSD_CDMX_PEX910515FV0_20200121_093448.key");
	 */ 
	
         DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
         dbfac.setNamespaceAware(true);
         Document doc = dbfac.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
          
         File cer = new File("\\\\servarchivos\\Sistemas_MariaJose\\2023\\escritorio\\SelloMysuite\\JES900109Q90_TEST_CSD_30001000000500003444.cer");
 		 File key = new File("\\\\servarchivos\\Sistemas_MariaJose\\2023\\escritorio\\SelloMysuite\\JES900109Q90_TEST_CSD_30001000000500003444.key");
        //produccion
 		 // PrivateKey llavePrivada = getPrivateKey(key,"Tacuba16");
        
 		 //pruebas
 		
 		PrivateKey llavePrivada = getPrivateKey(key,"12345678a");
 		 
         //Crear XML Signature Factory
        XMLSignatureFactory xmlSigFactory = XMLSignatureFactory.getInstance("DOM");
        DOMSignContext domSignCtx = new DOMSignContext(llavePrivada, doc.getDocumentElement());
       
       
        X509Certificate x509Certificado = X509Certificate(cer);
        
          
        Reference ref = null;
        SignedInfo si = null;
        XMLSignature signature=null;
        try {
            
             ref = xmlSigFactory.newReference("", xmlSigFactory.newDigestMethod(DigestMethod.SHA1, null),
                   Collections.singletonList(xmlSigFactory.newTransform(Transform.ENVELOPED,
                   (TransformParameterSpec) null)), null, null);
           
             si  = xmlSigFactory.newSignedInfo
                   (xmlSigFactory.newCanonicalizationMethod
                   (CanonicalizationMethod.INCLUSIVE,
                   (C14NMethodParameterSpec) null),
                   xmlSigFactory.newSignatureMethod(SignatureMethod.RSA_SHA1, null),
                   Collections.singletonList(ref));
         
         //Crear una nueva firma XML    
         KeyInfoFactory kif = xmlSigFactory.getKeyInfoFactory();
         X509IssuerSerial issuerSerial = kif.newX509IssuerSerial(x509Certificado.getIssuerDN().getName(), x509Certificado.getSerialNumber());
         List x509Content = new ArrayList();
         x509Content.add(issuerSerial);
         x509Content.add(x509Certificado);
         X509Data xd = kif.newX509Data(x509Content);
         KeyInfo ki = kif.newKeyInfo(Collections.singletonList(xd));

        signature = xmlSigFactory.newXMLSignature(si, ki);
   
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (InvalidAlgorithmParameterException ex) {
            ex.printStackTrace();
        }
     
        
        try {
            //Firma el documento
            signature.sign(domSignCtx);
        } catch (MarshalException ex) {
            ex.printStackTrace();
        } catch (XMLSignatureException ex) {
            ex.printStackTrace();
        }
      
        
         //Crear String del arbol xml
        DOMSource domSource = new DOMSource(doc);
        return certificadoXml(domSource);
    }
    





    //Metodo para crear la firma con el .key 
     private PrivateKey getPrivateKey(final File keyFile, final String password) throws GeneralSecurityException, IOException
    {
        FileInputStream in = new FileInputStream(keyFile);
        org.apache.commons.ssl.PKCS8Key pkcs8 = new org.apache.commons.ssl.PKCS8Key(in,password.toCharArray());
        
        byte[] decrypted = pkcs8.getDecryptedBytes();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decrypted);
        PrivateKey pk = null;
        
        if(pkcs8.isDSA()){
            pk = KeyFactory.getInstance("DSA").generatePrivate(spec);
        }else if(pkcs8.isRSA()){
            pk = KeyFactory.getInstance("RSA").generatePrivate(spec);
        }
        
        pk = pkcs8.getPrivateKey();
        return pk;
    }
    
     //Metodo para crear el certificado con el .cer 
      private X509Certificate X509Certificate(final File certificateFile) throws CertificateException,IOException
    {
        FileInputStream is = null;
        try
        {
            is = new FileInputStream(certificateFile);
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            return (X509Certificate) cf.generateCertificate(is);
        } finally
        {
            if(is != null)
            {
                is.close();
            }
        }
    }


     public String certificadoXml(DOMSource domSource) throws TransformerException, SAXException, IOException, ParserConfigurationException, MarshalException, XMLSignatureException {
   	    
    	StringWriter writer = new StringWriter();
        StreamResult result = new StreamResult(writer);
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.transform(domSource, result);
        String resultadoXML = writer.toString();
       
      //Quitamos saltos de linea
        String XML = resultadoXML.replaceAll("\\R", "");
        
      //Convertir base 64
        byte[] encode = org.apache.commons.codec.binary.Base64.encodeBase64(XML.getBytes(StandardCharsets.UTF_8));
        String encodeXml =  new String(encode, StandardCharsets.UTF_8);
        
     
        return encodeXml;
      
    }
     
     
    
     
     
}
