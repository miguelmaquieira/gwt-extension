package com.imotion.dslam.connection.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.imotion.dslam.conn.CRONIOConnectionImpl;
import com.imotion.dslam.conn.CRONIOIExecutionData;

public class CRONIOConnectionWrapperTest {

	public static void main(String[] args) throws IOException {
		
		CRONIOConnectionImpl connection = new CRONIOConnectionImpl(1, getNodeData(), null);
		connection.openConnection();
		
		CRONIOIExecutionData responseData = connection.executeCommand("ls");
		System.out.println(responseData.getSourceCommand());
		System.out.println(responseData.getResponse());
		System.out.println(responseData.getPrompt());
		
		connection.closeConnection();
	}
	
	private static CRONIOBOINode getNodeData() {
		CRONIOBOIMachineProperties machineProperties = new CRONIOBOMachineProperties();
		machineProperties.setUsername("gaelhosteneo");
		machineProperties.setPassword(".o7ws5mb");
		machineProperties.setTimeout(3000);
		machineProperties.setProtocolType(CRONIOBOIMachineProperties.PROTOCOL_TYPE_SSH);
		machineProperties.setPromptRegEx("\\S+@\\S+:~\\$\\s");
		
		CRONIOBOINode node = new CRONIOBONode();
		node.setNodeId(34l);
		node.setNodeIp("127.0.0.1");
		node.setMachineProperties(machineProperties);
		
		return node;
	}
	
}
