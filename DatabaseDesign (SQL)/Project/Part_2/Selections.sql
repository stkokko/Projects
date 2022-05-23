

--(a)
select payMethod, count(bookCode) as totalReservations
	from Payment, Booking
	where Payment.payCode = Booking.payCode
		group by payMethod

--(b)
select staffSurname, staffName, count(bookCode) as totalReservations
	from Staff, Booking
	where Staff.staffNo = Booking.staffNo 
		group by staffSurname, staffName
		having count(bookCode) >= All (select count(bookCode) 
									       from Staff, Booking 
										   where Staff.staffNo = Booking.staffNo 
										       group by staffSurname, staffName)
		
--(c)
select count(distinct R.bookCode) as totalReservations
	from Rental as R, Emplacement, Category 
	where R.campCode = Emplacement.campCode and R.empNo = Emplacement.empNo and
	      Emplacement.catCode = Category.catCode and Category.catCode = 'A' and 
	      Category.catCode = All(select Category.catCode 
	                                    from Rental, Emplacement, Category 
							            where R.bookCode = bookCode and Rental.campCode = Emplacement.campCode and 
							                  Rental.empNo = Emplacement.empNo and Emplacement.catCode = Category.catCode)
	
--(d)
select custSurname, custName, count(bookCode) as totalReservations
	from Customer, Booking
	where Customer.custCode = Booking.custCode and bookDt between '2000-01-01' and '2000-12-31'
		group by custSurname, custName                                                          
		order by custSurname                                                  		
			
--(e)
select campName, sum(unitCost*(DATEDIFF(day, startDt, endDt)+1)*noPers) as totalRevenue
	from Rental, Emplacement, Category, Camping
	where Rental.campCode = Emplacement.campCode and
		  Rental.empNo = Emplacement.empNo and
		  Emplacement.catCode = Category.catCode and
		  Emplacement.campCode = Camping.campCode
		group by campName
		
		
		
		
create index indx_booking on Booking(bookDt) include(custCode)	
create index indx_rental on Rental(campCode,empNo) include(endDt,noPers)