package com.imotion.dslam.bom.data.objectify;

import com.googlecode.objectify.annotation.EntitySubclass;
import com.googlecode.objectify.annotation.Index;
import com.imotion.dslam.bom.DSLAMBOIComputedDailyTransitData;

@EntitySubclass(index=true, name="ComputedDailyTransit")
public class DSLAMBOComputedDailyTransitDataObjectify extends DSLAMBOComputedTransitDataObjectify implements DSLAMBOIComputedDailyTransitData {

	private static final long serialVersionUID = -2998958116143853158L;

	public DSLAMBOComputedDailyTransitDataObjectify() {}
	
	@Index
	private 	int		partialIncomingTransit;
	@Index
	private 	int		partialTotalTransit;
	@Index
	private 	int		partialPassingTransit;
	@Index
	private 	int		lastWeekEnteringTransit;
	@Index
	private 	int		lastWeekTotalTransit;
	@Index
	private 	int		lastWeekPassingTransit;
	@Index
	private 	int		lastMonthEnteringTransit;
	@Index
	private 	int		lastMonthTotalTransit;
	@Index
	private 	int		lastMonthPassingTransit;
	@Index
	private 	int		lastYearEnteringTransit;
	@Index
	private 	int		lastYearTotalTransit;
	@Index
	private 	int		lastYearPassingTransit;
	@Index
	private 	long	accumulatedIncomingTransit;
	@Index
	private 	long	accumulatedTotalTransit;
	@Index
	private 	long	accumulatedPassingTransit;
	@Index
	private		int		weatherConditionCode;
	@Index
	private		String 	weatherSummary;
	@Index
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
	public void setAccumulatedIncomingTransit(long accumulatedEnteringTransit) {
		this.accumulatedIncomingTransit = accumulatedEnteringTransit;
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
