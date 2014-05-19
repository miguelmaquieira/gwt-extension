package com.imotion.transit.bom.data;

import com.imotion.transit.bom.TRANSBOIComputedDailyTransitData;

public class TRANSBOComputedDailyTransitData extends TRANSBOComputedTransitData implements TRANSBOIComputedDailyTransitData {

	private static final long serialVersionUID = -2998958116143853158L;

	public TRANSBOComputedDailyTransitData() {}
	
	private 	int		partialIncomingTransit;
	private 	int		partialTotalTransit;
	private 	int		partialPassingTransit;
	private 	int		lastWeekEnteringTransit;
	private 	int		lastWeekTotalTransit;
	private 	int		lastWeekPassingTransit;
	private 	int		lastMonthEnteringTransit;
	private 	int		lastMonthTotalTransit;
	private 	int		lastMonthPassingTransit;
	private 	int		lastYearEnteringTransit;
	private 	int		lastYearTotalTransit;
	private 	int		lastYearPassingTransit;
	private 	long	accumulatedIncomingTransit;
	private 	long	accumulatedTotalTransit;
	private 	long	accumulatedPassingTransit;
	private		int		weatherConditionCode;
	private		String 	weatherSummary;
	private		String	weatherIconUrl;

	@Override
	public void setPartialIncomingTransit(int partialIncomingTransit) {
		this.partialIncomingTransit = partialIncomingTransit;
	}

	@Override
	public int getPartialIncomingTransit() {
		return partialIncomingTransit;
	}

	@Override
	public void setPartialTotalTransit(int partialTotalTransit) {
		this.partialTotalTransit = partialTotalTransit;
	}

	@Override
	public int getPartialTotalTransit() {
		return partialTotalTransit;
	}

	@Override
	public void setPartialPassingTransit(int partialPassingTransit) {
		this.partialPassingTransit = partialPassingTransit;
	}

	@Override
	public int getPartialPassingTransit() {
		return partialPassingTransit;
	}

	@Override
	public int getLastWeekEnteringTransit() {
		return lastWeekEnteringTransit;
	}

	@Override
	public void setLastWeekEnteringTransit(int lastWeekEnteringTransit) {
		this.lastWeekEnteringTransit = lastWeekEnteringTransit;
	}

	@Override
	public int getLastWeekTotalTransit() {
		return lastWeekTotalTransit;
	}

	@Override
	public void setLastWeekTotalTransit(int lastWeekTotalTransit) {
		this.lastWeekTotalTransit = lastWeekTotalTransit;
	}

	@Override
	public int getLastWeekPassingTransit() {
		return lastWeekPassingTransit;
	}

	@Override
	public void setLastWeekPassingTransit(int lastWeekPassingTransit) {
		this.lastWeekPassingTransit = lastWeekPassingTransit;
	}

	@Override
	public int getLastMonthEnteringTransit() {
		return lastMonthEnteringTransit;
	}

	@Override
	public void setLastMonthEnteringTransit(int lastMonthEnteringTransit) {
		this.lastMonthEnteringTransit = lastMonthEnteringTransit;
	}

	@Override
	public int getLastMonthTotalTransit() {
		return lastMonthTotalTransit;
	}

	@Override
	public void setLastMonthTotalTransit(int lastMonthTotalTransit) {
		this.lastMonthTotalTransit = lastMonthTotalTransit;
	}

	@Override
	public int getLastMonthPassingTransit() {
		return lastMonthPassingTransit;
	}

	@Override
	public void setLastMonthPassingTransit(int lastMonthPassingTransit) {
		this.lastMonthPassingTransit = lastMonthPassingTransit;
	}

	@Override
	public int getLastYearEnteringTransit() {
		return lastYearEnteringTransit;
	}

	@Override
	public void setLastYearEnteringTransit(int lastYearEnteringTransit) {
		this.lastYearEnteringTransit = lastYearEnteringTransit;
	}

	@Override
	public int getLastYearTotalTransit() {
		return lastYearTotalTransit;
	}

	@Override
	public void setLastYearTotalTransit(int lastYearTotalTransit) {
		this.lastYearTotalTransit = lastYearTotalTransit;
	}

	@Override
	public int getLastYearPassingTransit() {
		return lastYearPassingTransit;
	}

	@Override
	public void setLastYearPassingTransit(int lastYearPassingTransit) {
		this.lastYearPassingTransit = lastYearPassingTransit;
	}

	@Override
	public void setAccumulatedIncomingTransit(long accumulatedIncomingTransit) {
		this.accumulatedIncomingTransit = accumulatedIncomingTransit;
	}

	@Override
	public long getAccumulatedIncomingTransit() {
		return accumulatedIncomingTransit;
	}

	@Override
	public void setAccumulatedTotalTransit(long accumulatedTotalTransit) {
		this.accumulatedTotalTransit = accumulatedTotalTransit;
	}

	@Override
	public long getAccumulatedTotalTransit() {
		return accumulatedTotalTransit;
	}

	@Override
	public void setAccumulatedPassingTransit(long accumulatedPassingTransit) {
		this.accumulatedPassingTransit = accumulatedPassingTransit;
	}

	@Override
	public long getAccumulatedPassingTransit() {
		return accumulatedPassingTransit;
	}

	@Override
	public int getWeatherConditionCode() {
		return weatherConditionCode;
	}

	@Override
	public void setWeatherConditionCode(int weatherConditionCode) {
		this.weatherConditionCode = weatherConditionCode;
	}

	@Override
	public String getWeatherSummary() {
		return weatherSummary;
	}

	@Override
	public void setWeatherSummary(String weatherSummary) {
		this.weatherSummary = weatherSummary;
	}

	@Override
	public String getWeatherIconUrl() {
		return weatherIconUrl;
	}

	@Override
	public void setWeatherIconUrl(String weatherIconUrl) {
		this.weatherIconUrl = weatherIconUrl;
	}

}
