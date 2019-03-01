package com.luoran.zzbird.wxfwhUtils;

import java.io.InputStream;
import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class GzhUtils {
	/**
	 * 将token、timestamp、nonce三个参数进行字典序排序
	 * 
	 * @param token     前台传过来的token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String sort(String token, String timestamp, String nonce) {
		String[] strArray = { token, timestamp, nonce };
		Arrays.sort(strArray);
		StringBuilder sb = new StringBuilder();
		for (String str : strArray) {
			sb.append(str);
		}

		return sb.toString();
	}

	/**
	 * 将三个参数字符串拼接成一个字符串进行sha1加密
	 * 
	 * @param str
	 * @return
	 */
	public static String sha1(String str) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			digest.update(str.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 解析微信发来的请求(xml)
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();
		// 从request中取得输入流
		InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			// 读取输入流
			StringBuffer out = new StringBuffer();
			byte[] b = new byte[4096];
			for (int n; (n = inputStream.read(b)) != -1;) {
				out.append(new String(b, 0, n));
			}
			String xml = out.toString();
			StringReader sr = new StringReader(xml);
			InputSource is = new InputSource(sr);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			XPathFactory pathFactory = XPathFactory.newInstance();
			XPath xpath = pathFactory.newXPath();
			//XPathExpression pathExpression = xpath.compile("xml/");
			NodeList nodes = (NodeList) xpath.evaluate("xml",doc, XPathConstants.NODESET);
			NodeList childNodes = nodes.item(0).getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				String nodeName = childNodes.item(i).getNodeName();
				String textContent = childNodes.item(i).getTextContent();
				if(nodeName != null && !"#text".equals(nodeName)){
					map.put(nodeName, textContent);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

//		SAXReader reader = new SAXReader();
//		Document document = reader.read(inputStream);
//		// 得到xml根元素
//		Element root = document.getRootElement();
//		// 得到根元素的所有子节点
//		List<Element> elementList = root.elements();
//		// 遍历所有子节点
//		for (Element e : elementList)
//			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;
		return map;
	}
}
