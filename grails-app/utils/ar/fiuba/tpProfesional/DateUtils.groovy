package ar.fiuba.tpProfesional;

import org.apache.commons.lang.StringUtils
import org.apache.commons.validator.routines.DateValidator

class DateUtils {

	public static final String DD_MM_YYYY = "dd/MM/yyyy"
	
	static boolean isDate(String date, String pattern){
		if (StringUtils.isNotEmpty(date)){
			return DateValidator.getInstance().isValid(date, pattern)
		}else{
			return false
		}
	}
	
	static Date stringToDate(String date, String pattern) {
		String[] parsePatterns = new String[1]
		parsePatterns[0] = pattern
		return org.apache.commons.lang.time.DateUtils.parseDate(date, parsePatterns)
	}
	
	static String dateToString(Date date, String pattern) {
		return date.format(pattern)
	}
	
	static boolean dateEqualsWithoutTime(Date date1, Date date2) {
		return org.apache.commons.lang.time.DateUtils.isSameDay(date1, date2)
	}
	
}
