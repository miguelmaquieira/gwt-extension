package com.imotion.dslam.front.business.desktop.client.widget.execution;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.logging.client.HasWidgetsLogHandler;
import com.imotion.dslam.logger.atmosphere.base.CRONIOIClientLoggerConstants;
import com.selene.arch.base.exe.core.appli.metadata.element.AEMFTMetadataElementComposite;

public class CRONIOBusDesktopProjectExecutionJavaLogger extends CRONIOBusDesktopProjectExecutionLogger {

	private Logger 		logger;
	
	public CRONIOBusDesktopProjectExecutionJavaLogger(String loggerId) {
		super(loggerId);
		logger = Logger.getLogger(loggerId);
		HasWidgetsLogHandler loggerContainer = new HasWidgetsLogHandler(getLoggerContainer());
		logger.addHandler(loggerContainer);
	}
	
	/**
	 * PROTECTED
	 */
	@Override
	protected void addLogItem(AEMFTMetadataElementComposite logData) {
		String fulltrace = getElementController().getElementAsString(CRONIOIClientLoggerConstants.FULLTRACE, logData);
		logger.log(Level.INFO, fulltrace);
	}

}
