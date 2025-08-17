package dev.tjxjnoobie.uhcgames.other;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

// ** By @tjXJNOOBIE - TJ Valeintine 2016 ** 
public class Time  {



	static SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
	static Calendar calendar = Calendar.getInstance();
	public static String dateInString = calendar.getTime().toString();
	public static ParsePosition a;
	static Calendar cal = Calendar.getInstance();
	public static int year       = cal.get(Calendar.YEAR);
	static int month  = cal.get(Calendar.MONTH); // January = 0 & December = 11
	static int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH); 
	static int dayOfWeek  = cal.get(Calendar.DAY_OF_WEEK);
	static int weekOfYear = cal.get(Calendar.WEEK_OF_YEAR);
	static int weekOfMonth = cal.get(Calendar.WEEK_OF_MONTH);
	static int hour = cal.get(Calendar.HOUR);        // 12 hour clock
	static int hourOfDay = cal.get(Calendar.HOUR_OF_DAY); // 24 hour clock
	static int minute = cal.get(Calendar.MINUTE);
	static int second = cal.get(Calendar.SECOND);
	static int millsecond= cal.get(Calendar.MILLISECOND);
	static int ampm = cal.get(Calendar.AM_PM);
	static int zone = cal.get(Calendar.ZONE_OFFSET);

    public static Date date =  calendar.getTime();
    
    
	

	
	public static Calendar getCurrentTIme() {
		return cal;	
	}
	
	public static int getRawYear() {
		return year;
	}
	public static int getRawMonth() {

		return month;
	}
	
	public static int getDayOfMonth() {
		return dayOfMonth;
	}
	
	public static int getDayOfWeek() {
		return dayOfWeek;
		
	}
	public static int getWeekofYear(){
		return weekOfYear;
	}
	
	public static int getWeekOFMonth() {
		return weekOfMonth;
	}
	public static int get12HourClock() {
		return hour;
	}
	
	public static int get24HourClock() {
		return hourOfDay;
		
	}
	public static int getMinue(){
		return minute;
	}
	
	public static int getSecond() {
		return second;
	}
	
	public static int getMillsecond() {
		return millsecond;
	}
	public static Date getDate() {
		return date;
	}
	public static int getAMPM() {
		return ampm;
	}
		
	public static String getMonthLang() {
	if(getRawYear() == 0) {
		return "Jan";
	}
		
	if(getRawYear() == 1) {
		return "Feb";
	}
	if(getRawYear() == 2) {
		return "Mar";
	}
	if(getRawYear() == 3) {
		return "Apr";
	}
	if(getRawYear() == 4){
		return "May";
	}
	if(getRawYear() == 5) {
		return "Jun";
	}
	if(getRawYear() == 6) {
		return "Jul";
	}
	if(getRawYear() == 7){
		return "Aug";
	}
	if(getRawYear() == 8) {
		return "Sep";
	}
	if(getRawYear() == 9) {
		return "Oct";
	}
	if(getRawYear() == 10){
		return "Nov";
	}
	if(getRawYear() == 11) {
		return "Dec";
	}
		return "Could not retrive year";
}
	
	
	public static int getThisYear() {
		int currentyear = getRawYear() + 1;
		return currentyear;
	}
	// UNFINISHED
	
	

}
	



