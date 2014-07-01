package com.imotion.cronio.backend.persistence.service.node;

import java.util.Date;
import java.util.List;

import com.imotion.dslam.backend.persistence.jpa.DSLAMBKPersistenceServiceBaseJPA;
import com.imotion.dslam.bom.CRONIOBOINode;
import com.imotion.dslam.bom.DSLAMBOIProcess;
import com.imotion.dslam.bom.DSLAMBOIVariable;
import com.imotion.dslam.bom.data.CRONIOBONode;
import com.selene.arch.base.exe.core.common.AEMFTCommonUtilsBase;
import com.selene.arch.exe.core.common.AEMFTCommonUtils;

public class CRONIOBKNodePersistenceServiceJPA extends DSLAMBKPersistenceServiceBaseJPA<CRONIOBOINode, CRONIOBONode, Long> implements CRONIOBKINodePersistenceService {

	private static final long serialVersionUID = 4679813620335280677L;

	@Override
	public CRONIOBOINode addNode(CRONIOBOINode node) {
		CRONIOBONode nodeJPA = (CRONIOBONode) node;
		DSLAMBOIProcess process = node.getProcess();
		if (process != null && !AEMFTCommonUtils.isNullLong(process.getProcessId())) {
			process = getProcessPersistence().getProcess(process.getProcessId());
			node.setProcess(process);
		}
		nodeJPA = getPersistenceModule().create(nodeJPA);
		return nodeJPA;
	}
	
	@Override
	public CRONIOBOINode updateNodeIp(Long nodeIdAsLong, String nodeIp) {
		CRONIOBONode node = getPersistenceModule().get(nodeIdAsLong);
		node.setNodeIp(nodeIp);
		node.setSavedTime(new Date());
		node = getPersistenceModule().update(node);
		return node;
	}
	
	@Override
	public CRONIOBOINode updateNodeName(Long nodeIdAsLong, String nodeName) {
		CRONIOBONode node = getPersistenceModule().get(nodeIdAsLong);
		node.setNodeName(nodeName);
		node.setSavedTime(new Date());
		node = getPersistenceModule().update(node);
		return node;
	}
	
	@Override
	public CRONIOBOINode updateNodeType(Long nodeIdAsLong, int nodeType) {
		CRONIOBONode node = getPersistenceModule().get(nodeIdAsLong);
		node.setNodeType(nodeType);
		node.setSavedTime(new Date());
		node = getPersistenceModule().update(node);
		return node;
	}
	
	@Override
	public CRONIOBOINode updateNodeVariableList(Long nodeIdAsLong, List<DSLAMBOIVariable> variableList) {
		CRONIOBONode node = getPersistenceModule().get(nodeIdAsLong);
		node.setVariableList(variableList);
		node.setSavedTime(new Date());
		node = getPersistenceModule().update(node);
		return node;
	}
	
	@Override
	public List<CRONIOBOINode> getAllNodes() {
		List<CRONIOBONode> nodeListJpa = getPersistenceModule().findAll();
		return AEMFTCommonUtilsBase.castList(nodeListJpa);
	}
	
	@Override
	public void removeNode(Long nodeIdAsLong) {
		getPersistenceModule().remove(nodeIdAsLong);
	}
	
	@Override
	public CRONIOBOINode getNode(Long nodeIdAsLong) {
		CRONIOBONode nodeJpa = getPersistenceModule().get(nodeIdAsLong);
		return nodeJpa;
	}

	/**
	 * AEMFTIHasPersistenceModule
	 */
	
	@Override
	public Class<CRONIOBONode> getPersistenceClass() {
		return CRONIOBONode.class;
	}

}
