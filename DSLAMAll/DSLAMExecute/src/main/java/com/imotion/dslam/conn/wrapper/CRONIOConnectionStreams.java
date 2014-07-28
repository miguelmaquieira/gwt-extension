package com.imotion.dslam.conn.wrapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;

public class CRONIOConnectionStreams {

	private static final int		CMD_REGEX_SIZE = 10;
	private static final char 	NEW_LINE_CHAR	= '\n';

	private PrintStream		toServer;
	private InputStream 	fromServer;

	public CRONIOConnectionStreams(InputStream responseStream, PrintStream executeStream) {
		super();
		this.toServer		= executeStream;
		this.fromServer 	= responseStream;
	}

	public String sendCommand(String cmd) throws CRONIOConnectionUncheckedException {
		sendCommandBase(cmd);
		int				init			= Math.max(0, cmd.length() - CMD_REGEX_SIZE);
		String			textToMatch 	= cmd.substring(init, cmd.length());
		List<String> 	lines			=  AEMFTCommonUtilsBase.splitByToken(textToMatch, String.valueOf(NEW_LINE_CHAR));
		textToMatch = lines.get(lines.size() - 1);
		return readUntil(textToMatch);
	}

	public void sendCommandBase(String cmd) {
		toServer.println(cmd);
		toServer.flush();
	}

	public String readUntil(String patternStr) throws CRONIOConnectionUncheckedException {
		Pattern	pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher("");
		StringBuilder sbResponseWithoutIsoControl 	= new StringBuilder();
		StringBuilder sbResponseToMatch				= new StringBuilder();
		while (!matcher.matches() && !matcher.find()) {
			try {
				char cChar = (char) fromServer.read();
				if (!Character.isISOControl(cChar) || NEW_LINE_CHAR == cChar) {
					sbResponseWithoutIsoControl.append(cChar);
					if (NEW_LINE_CHAR == cChar) {
						sbResponseToMatch.delete(0, sbResponseToMatch.length());
					} else {
						sbResponseToMatch.append(cChar);
					}
				}
				matcher = pattern.matcher(sbResponseToMatch);
			} catch (IOException e) {
				throw new CRONIOConnectionUncheckedException(e);
			}
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
