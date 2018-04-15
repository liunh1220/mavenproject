package com.example.ssdb;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.nutz.ssdb4j.SSDBs;
import org.nutz.ssdb4j.spi.Response;
import org.nutz.ssdb4j.spi.SSDB;

public class SsdbTest {
	@Test
	public void ssdbTest() throws IOException{
		Response resp = new Response();
		byte[] b;
		SSDB ssdb = SSDBs.simple("192.168.181.130",8888, 6000);
		
		//resp = ssdb.flushdb("");
		ssdb.set("a", "123");
		resp = ssdb.keys(0, 2, 10);
		System.out.println(resp.toString());

		ssdb.close();

	}

}
