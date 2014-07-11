package com.imotion.dslam.conn.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.io.IOUtils;

import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;

public class CRONIOConnectionStreams {

	private InputStream		responseStream;
	private PrintStream		executeStream;

	public CRONIOConnectionStreams(InputStream responseStream, PrintStream executeStream) {
		super();
		this.responseStream		= responseStream;
		this.executeStream		= executeStream;
	}

	public void sendCommand(String cmd) {
		executeStream.println(cmd);
		executeStream.flush();
	}

	public String getResponse() throws IOException {
		String response = IOUtils.toString(responseStream);
		return response;
	}

	public String readUntil(String pattern) {
		char lastChar = pattern.charAt(pattern.length() - 1);
		StringBuffer sb = new StringBuffer();
		try {
			char ch = (char) responseStream.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						break;
					}
				}
				ch = (char) responseStream.read();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public void closeStreams() throws CRONIOConnectionUncheckedException {
		executeStream.close();
		try {
			responseStream.close();
		} catch (IOException e) {
			throw new CRONIOConnectionUncheckedException(e);
		}
	}

}
