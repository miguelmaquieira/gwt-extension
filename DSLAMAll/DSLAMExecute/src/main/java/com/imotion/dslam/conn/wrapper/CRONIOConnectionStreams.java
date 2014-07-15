package com.imotion.dslam.conn.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;

public class CRONIOConnectionStreams {

	private PrintStream		toServer;
	private InputStream 	fromServer;

	public CRONIOConnectionStreams(InputStream responseStream, PrintStream executeStream) {
		super();
		this.toServer		= executeStream;
		this.fromServer 	= responseStream;
	}

	public String sendCommand(String cmd) throws IOException {
		sendCommandBase(cmd);
		return readUntil(cmd);
	}

	public void sendCommandBase(String cmd) throws IOException {
		toServer.println(cmd);
		toServer.flush();
	}

	public String readUntil(String patternStr) throws IOException {
		Pattern	pattern = Pattern.compile(patternStr);
		Matcher	matcher = pattern.matcher("");

		StringBuilder sbResponseWithoutIsoControl	= new StringBuilder();
		char cChar = 0;
		//		while (fromServer.available() > 0 && !matcher.find() && !matcher.matches() && (byte) cChar != -1) {
		while (!matcher.find() && !matcher.matches() && (byte) cChar != -1) {
			cChar = (char) fromServer.read();
			if (!Character.isISOControl(cChar)) {
				sbResponseWithoutIsoControl.append(cChar);
			}
			matcher = pattern.matcher(sbResponseWithoutIsoControl.toString());
		}
		return sbResponseWithoutIsoControl.toString();
	}

	public void closeStreams() throws CRONIOConnectionUncheckedException {
		toServer.close();
		try {
			fromServer.close();
		} catch (IOException e) {
			throw new CRONIOConnectionUncheckedException(e);
		}
	}

}
