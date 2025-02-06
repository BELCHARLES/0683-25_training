package com.Z_tasks.filehandling.task;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

import com.exception.InvalidArgumentException;
import com.generalutils.GeneralUtils;

public class TimeTask {
	
	public Instant getInstant(){
		return Instant.now();
	}	
	
	public ZonedDateTime getZonedInstant() {
		return ZonedDateTime.now();
	}
	
	public OffsetDateTime getOffsetInstant() {
		return OffsetDateTime.now();
	}
	
//	 public LocalDateTime getLocalDateTime() {
//		 return LocalDateTime.now();
//	 }
	 
	 public long getCurrentMillisUsingSystem() {
		 return System.currentTimeMillis();
	 }
	 
	 public long getCurrentMillisUsingInstant() {
		 return getInstant().toEpochMilli();
	 }
	 
	 public long getElapsedMillisUsingSysNano() {
		 return System.nanoTime()/1_000_000;
	 }
	 
	 public Set<String> getAvailableZoneIds(){
		 return ZoneId.getAvailableZoneIds();
	 }
	 
	 public ZoneId toZoneId(String zoneId)throws InvalidArgumentException{
		 GeneralUtils.checkObjArgIsNull(zoneId);
		 return ZoneId.of(zoneId);
	 }
	 
	 
	 public ZonedDateTime getZonedDateTime(ZoneId zoneId)throws InvalidArgumentException {
		 GeneralUtils.checkObjArgIsNull(zoneId);
		 return ZonedDateTime.now(zoneId);
	 }
	 
	 public Duration getDurationBetween(ZonedDateTime zone1,ZonedDateTime zone2) throws InvalidArgumentException{
		 GeneralUtils.checkObjArgIsNull(zone1);
		 GeneralUtils.checkObjArgIsNull(zone2);
		 return Duration.between(zone1, zone2).abs();
	 }
	 
	 public long getHours(Duration duration)throws InvalidArgumentException{
		 GeneralUtils.checkObjArgIsNull(duration);
		 return duration.toHours();
	 }
	 
	 public long getMinutes(Duration duration)throws InvalidArgumentException{
		 GeneralUtils.checkObjArgIsNull(duration);
		 return duration.toMinutes() % 60;
	 }
	 
	 public long getSeconds(Duration duration)throws InvalidArgumentException{
		 GeneralUtils.checkObjArgIsNull(duration);
		 return duration.getSeconds() % 60;
	 }
	 
//	 public LocalDate toLocalDate(ZonedDateTime zone) throws InvalidArgumentException{
//		 GeneralUtils.checkObjArgIsNull(zone);
//		 return zone.toLocalDate();
//	 }
//	 
//	 public Period getPeriodBetween(LocalDate date1,LocalDate date2) throws InvalidArgumentException{
//		 GeneralUtils.checkObjArgIsNull(date1);
//		 GeneralUtils.checkObjArgIsNull(date2);
//		 return Period.between(date1, date2);
//	 }
	 
	 private Instant ofEpochMilli(long millis) {
		 return Instant.ofEpochMilli(millis);
	 }
	 
	 private ZonedDateTime getZonedDateTime(long millis,ZoneId zoneId)throws InvalidArgumentException{
		 GeneralUtils.checkObjArgIsNull(zoneId);
		 Instant instant = ofEpochMilli(millis);
		 return instant.atZone(zoneId);
	 }
	 
	 public DayOfWeek getDayForCurrentTime(long millis,String zone)throws InvalidArgumentException {
		 ZoneId zoneId = toZoneId(zone);
		 ZonedDateTime dateTime = getZonedDateTime(millis,zoneId);
		 return dateTime.getDayOfWeek();
	 }

	 public Month getMonthForCurrentTime(long millis,String zone)throws InvalidArgumentException {
		 ZoneId zoneId = toZoneId(zone);
		 ZonedDateTime dateTime = getZonedDateTime(millis,zoneId);
		 return dateTime.getMonth();
	 }
	 
	 public int getYearForCurrentTime(long millis,String zone)throws InvalidArgumentException {
		 ZoneId zoneId = toZoneId(zone);
		 ZonedDateTime dateTime = getZonedDateTime(millis,zoneId);
		 return dateTime.getYear();
	 }


}
