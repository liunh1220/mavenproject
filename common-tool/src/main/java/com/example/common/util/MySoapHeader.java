package com.example.common.util;

import org.codehaus.xfire.MessageContext;
import org.codehaus.xfire.handler.AbstractHandler;
import org.jdom.Element;
import org.jdom.Namespace;

/**
 * Class description goes here.
 * 
 * @version
 * @author ren.zj
 */
public class MySoapHeader extends AbstractHandler {

	private String Uname;
	private String Password;
	private String url;

	public MySoapHeader(String uname, String password,String url) {
		this.Uname = uname;
		this.Password = password;
		this.url = url;
	}

	@Override	
	public void invoke(MessageContext context) throws Exception {
		final Namespace namespace = Namespace.getNamespace("test",url);
		Element header = new Element("header", namespace);
		Element authToken = new Element("MySoapHeader", namespace);// Token是名字
		Element token1 = new Element("Uname", namespace);

		token1.addContent(Uname);// token是值，密钥
		Element token2 = new Element("Password", namespace);
		token2.addContent(Password);
		authToken.addContent(token1);
		authToken.addContent(token2);
		header.addContent(authToken);
		context.getCurrentMessage().setHeader(header);
//		XMLOutputter outputter = null;
//		Format format = Format.getCompactFormat();
//		format.setEncoding("GB2312");
//		format.setIndent("    ");
//		outputter = new XMLOutputter(format);
//
//		outputter.output(header, new FileOutputStream("C:\\a.xml"));
	}
	
}
