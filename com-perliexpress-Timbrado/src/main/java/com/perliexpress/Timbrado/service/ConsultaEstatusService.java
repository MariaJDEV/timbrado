package com.perliexpress.Timbrado.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;

import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.XMLSignatureException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import com.perliexpress.Timbrado.model.RequestTimbrado;
import com.perliexpress.Timbrado.model.ResponseTimbrado;

public interface ConsultaEstatusService {
	ResponseTimbrado peticionConsultarEstatus(RequestTimbrado request)throws UnsupportedEncodingException, XPathExpressionException, CertificateException, TransformerException, ParserConfigurationException, SAXException, IOException, GeneralSecurityException, MarshalException, XMLSignatureException;
}
