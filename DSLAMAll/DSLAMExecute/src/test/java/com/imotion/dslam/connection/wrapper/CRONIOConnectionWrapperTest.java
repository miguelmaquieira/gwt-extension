package com.imotion.dslam.connection.wrapper;

import java.io.IOException;

import com.imotion.dslam.bom.CRONIOBOIMachineProperties;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.data.CRONIOBOMachineProperties;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.imotion.dslam.conn.wrapper.CRONIOConnectionWrapperSSH;

public class CRONIOConnectionWrapperTest {

	public static void main(String[] args) throws IOException {
		CRONIOConnectionWrapperSSH connectionSSH = new CRONIOConnectionWrapperSSH();
		connectionSSH.connect(getNodeData());
		connectionSSH.readResponseUntil("~\\$");
		connectionSSH.sendCommand("ls");
		
		System.out.println(connectionSSH.readResponseUntil("~$"));
	}
	
	private static CRONIOBOINode getNodeData() {
		CRONIOBOIMachineProperties machineProperties = new CRONIOBOMachineProperties();
		machineProperties.setUsername("gaelhosteneo");
		machineProperties.setPassword(".o7ws5mb");
		machineProperties.setTimeout(3000);
		
		CRONIOBOINode node = new CRONIOBONode();
		node.setNodeIp("127.0.0.1");
		node.setMachineProperties(machineProperties);
		
		return node;
	}
	
}
