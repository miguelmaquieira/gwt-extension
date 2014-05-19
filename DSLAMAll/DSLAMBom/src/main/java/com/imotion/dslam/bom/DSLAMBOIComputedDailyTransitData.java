package com.imotion.dslam.bom;


public interface DSLAMBOIComputedDailyTransitData extends DSLAMBOIComputedPartialTransitData, DSLAMBOIComputedAccumulatedTransitData {

	int		getWeatherConditionCode();

	void	setWeatherConditionCode(int weatherConditionCode);
	
	String	getWeatherSummary();

	void	setWeatherSummary(String weatherSummary);

	String	getWeatherIconUrl();

	void	setWeatherIconUrl(String weatherIconUrl);

	int getLastWeekEnteringTransit();

	void setLastWeekEnteringTransit(int lastWeekEnteringTransit);

	int getLastWeekTotalTransit();

	void setLastWeekTotalTransit(int lastWeekTotalTransit);

	int getLastWeekPassingTransit();

	void setLastWeekPassingTransit(int lastWeekPassingTransit);

	int getLastMonthEnteringTransit();

	void setLastMonthEnteringTransit(int lastMonthEnteringTransit);

	int getLastMonthTotalTransit();

	void setLastMonthTotalTransit(int lastMonthTotalTransit);

	int getLastMonthPassingTransit();

	void setLastMonthPassingTransit(int lastMonthPassingTransit);

	int getLastYearEnteringTransit();

	void setLastYearEnteringTransit(int lastYearEnteringTransit);

	int getLastYearTotalTransit();

	void setLastYearTotalTransit(int lastYearTotalTransit);

	int getLastYearPassingTransit();

	void setLastYearPassingTransit(int lastYearPassingTransit);
	
}
