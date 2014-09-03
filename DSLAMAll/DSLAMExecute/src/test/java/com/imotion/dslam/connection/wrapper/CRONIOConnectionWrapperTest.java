package com.imotion.dslam.connection.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.imotion.dslam.conn.CRONIOConnectionImpl;
import com.imotion.dslam.conn.CRONIOConnectionUncheckedException;
import com.imotion.dslam.conn.CRONIOIExecutionData;

public class CRONIOConnectionWrapperTest {

	public static void main(String[] args) throws IOException {
		CRONIOConnectionImpl connection = new CRONIOConnectionImpl(1, 1, getNodeData(), null);
		connection.openConnection();
		try {
			CRONIOIExecutionData responseData = connection.executeCommand("echo hola");
			System.out.println(responseData.getSourceCommand());
			System.out.println(responseData.getResponse());
			System.out.println(responseData.getPrompt());
		} catch (NullPointerException | CRONIOConnectionUncheckedException e) {
			e.printStackTrace();
		}
		connection.closeConnection();
	}

	private static CRONIOBOINode getNodeData() {
		CRONIOBOIMachineProperties machineProperties = new CRONIOBOMachineProperties();
		machineProperties.setUsername("admin");
		machineProperties.setPassword("admin");
		machineProperties.setTimeout(3000);
		machineProperties.setProtocolType(CRONIOBOIMachineProperties.PROTOCOL_TYPE_TELNET);
		machineProperties.setPromptRegEx("\\s>\\s");

		CRONIOBOINode node = new CRONIOBONode();
		node.setNodeId(34l);
		node.setNodeIp("192.168.1.1");
		node.setMachineProperties(machineProperties);

		return node;
	}

}
