package com.example.common.util.request;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.protocol.ProtocolSocketFactory;


public class HTTPSSecureProtocolSocketFactory implements ProtocolSocketFactory {
	private SSLContext sslcontext = null; 
	
	private SSLContext createSSLContext() {  
        SSLContext sslcontext = null;  
        try {  
            sslcontext = SSLContext.getInstance("SSL");  
            sslcontext.init(null,  
                    new TrustManager[] { new TrustAnyTrustManager() },  
                    new java.security.SecureRandom());  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        }  
        return sslcontext;  
    }  
    
    private SSLContext getSSLContext() {  
        if (null == this.sslcontext) {  
            this.sslcontext = createSSLContext();  
        }  
        return this.sslcontext;  
    }
    
	@Override
    public Socket createSocket(String host, int port) throws IOException,  
            UnknownHostException {  
        return getSSLContext().getSocketFactory().createSocket(host, port);  
    }  
	@Override
    public Socket createSocket(String host, int port, InetAddress clientHost,  
            int clientPort) throws IOException, UnknownHostException {  
        return getSSLContext().getSocketFactory().createSocket(host, port,  
                clientHost, clientPort);  
    }  
	@Override
    public Socket createSocket(String host, int port, InetAddress localAddress,  
            int localPort, HttpConnectionParams params) throws IOException,  
            UnknownHostException, ConnectTimeoutException {  
        if (params == null) {  
            throw new IllegalArgumentException("Parameters may not be null");  
        }  
        int timeout = params.getConnectionTimeout();  
        SocketFactory socketfactory = getSSLContext().getSocketFactory();  
        if (timeout == 0) {  
            return socketfactory.createSocket(host, port, localAddress,  
                    localPort);  
        } else {  
            Socket socket = socketfactory.createSocket();  
            SocketAddress localaddr = new InetSocketAddress(localAddress,  
                    localPort);  
            SocketAddress remoteaddr = new InetSocketAddress(host, port);  
            socket.bind(localaddr);  
            socket.connect(remoteaddr, timeout);  
            return socket;  
        }  
    }   
    
    private static class TrustAnyTrustManager implements X509TrustManager {  

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
			throws java.security.cert.CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
			throws java.security.cert.CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[]{};
		}
    }

}
