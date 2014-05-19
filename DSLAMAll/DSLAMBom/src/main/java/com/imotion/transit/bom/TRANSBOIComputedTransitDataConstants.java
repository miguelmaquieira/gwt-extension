package com.imotion.transit.bom;

import java.io.Serializable;

public interface TRANSBOIComputedTransitDataConstants extends Serializable {
	
	String FREQUENZY_TYPE	= "frequenzyType";
	String TIMESTAMP		= "timestamp";

	//OTHER CONSTANTS
	int FREQUENCY_TYPE_DAILY		= 1;
	int FREQUENCY_TYPE_MONDAYS		= 2;
	int FREQUENCY_TYPE_TUESDAYS		= 3;
	int FREQUENCY_TYPE_WEDNESDAYS	= 4;
	int FREQUENCY_TYPE_THURSDAYS	= 5;
	int FREQUENCY_TYPE_FRIDAYS		= 6;
	int FREQUENCY_TYPE_SATURDAYS	= 7;
	int FREQUENCY_TYPE_SUNDAYS		= 8;
	int FREQUENCY_TYPE_WEEKLY		= 9;
	int FREQUENCY_TYPE_MONTHLY		= 10;
	int FREQUENCY_TYPE_ANNUAL		= 11;

}
