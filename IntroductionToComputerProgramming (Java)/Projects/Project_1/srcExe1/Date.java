import acm.program.*;
 public class Date extends Program {
  public void run() {
	  int n = readInt("Give the number of day: ");
	  String nameofcurrentday = FindDay(n);
	  int currentyear = readInt("Give the year: ");
	  int currentmonth = Month();
	  int currentday = DateOfDay(currentmonth,currentyear);
	  println(nameofcurrentday + " " + currentday + "/" + currentmonth + "/" +currentyear);
	  int monthforpastyear = Month();
	  int pastyear = Year(currentyear,currentmonth,monthforpastyear);
	  int dayforpastyear = DateOfDay(monthforpastyear,pastyear);
	  while(currentmonth==monthforpastyear && currentday<dayforpastyear) {
		  dayforpastyear = readInt("Give a number equal to " + currentday + " or lesser :");
	  }
	  println("The date from the past is" + " " + dayforpastyear + "/" + monthforpastyear + "/" + pastyear);
	  int leap_years;
	  if(currentyear==pastyear && LeapYear(currentyear)) {
		 leap_years = 1;
	  }else{
	     leap_years = 0;
	  }
	  int j = currentyear - pastyear;
	  for(int i=1;i<j;i++) {
		  if (LeapYear(pastyear)) leap_years++ ;
		  pastyear++ ;
      }
	  int days_since = j * 365 + leap_years;
	  String thedaywas = TheDayWas(days_since,n,nameofcurrentday);
	  println("Days since" + " " + days_since);
	  println(dayforpastyear + "/" + monthforpastyear + "/" + pastyear + " " + "was" + " " + thedaywas);
	  }
  private int Year(int currentyear,int currentmonth,int monthforpastyear) {
	  int pastyear = readInt("Give a year before or equal to" + " " + currentyear + ":");
	  while(pastyear>currentyear) {
		 pastyear = readInt("Illegal year!Give a year before" + " " + currentyear + ".");
	  }
	  while(pastyear==currentyear && currentmonth<monthforpastyear) {
		  monthforpastyear = readInt("Give a month before" + monthforpastyear + ".");
	  }
	  return pastyear;
  }
	private int Month() {
    int month = readInt("Give the month: ");
    while(true) {
	  if(month>=1 && month<=12) break;
      month = readInt("Illegal number!Give a number between 1 and 12: ");
    }
	return month;
  }	
  private boolean LeapYear(int currentyear) {
	  return (currentyear%4==0 && currentyear%100!=0) || (currentyear%400==0);
  }
  private String TheDayWas(int days_since,int n,String nameofcurrentday) {
	  switch(days_since%7) {
		  case 0: return nameofcurrentday;
		  case 1: 
		   n++;
		   return FindDay(n);
		  case 2:
		   n+=2;
		   return FindDay(n);
		  case 3:
		   n+=3;
		   return FindDay(n);
		  case 4:
		   n+=4;
		   return FindDay(n);
		  case 5:
		   n+=5;
		   return FindDay(n);
		  case 6:
		   n+=6;
		   return FindDay(n);
		  default: return "bug";
	  }
  }
  private String FindDay(int n) {
	  while(n<0) {
		n = readInt("Illegal number!Give a positive integer: ");
	  }
	 switch(n%7) {
      case 0: return "Sunday";
      case 1: return "Monday";	  
	  case 2: return "Tuesday";
      case 3: return "Wednesday";
	  case 4: return "Thursday";
	  case 5: return "Friday";
	  case 6: return "Saturday";
	  default: return "bug";
	 }
  }
  private int DateOfDay(int month,int year) {
	  int dateofday = readInt("Give the number for date of day: ");
	  switch (month) {
		  case 1: case 3: case 5: case 7: case 8: case 10: case 12:
		   while(true) {
			   if(dateofday>=1 && dateofday<=31) break;
			   dateofday = readInt("Illegal number!Give a number between 1 and 31: ");
		   }
		   break;
		  case 4: case 6: case 9: case 11:
		   while(true) {
			   if(dateofday>=1 && dateofday<=30) break;
			   dateofday = readInt("Illegal number!Give a number between 1 and 30: ");
		   }
		   break;
		  case 2:
		   if(LeapYear(year)) {
			   while(true) {
			   if(dateofday>=1 && dateofday<=29) break;
			   dateofday = readInt("Illegal number!Give a number between 1 and 29: ");
			   }
			   break;
		   }else {
				while(true) {
			   if(dateofday>=1 && dateofday<=28) break;
			   dateofday = readInt("Illegal number!Give a number between 1 and 28: ");
			}
			break;
		   }
		   default: println("Illegal number");
	  }
	  return dateofday;
  }
 }