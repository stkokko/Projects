

insert into Staff (staffNo, staffName, staffSurname)
	select distinct staffNo, staffName, staffSurname
		from MainTable

insert into Customer (custCode, custName, custSurname, custPhone)
	select distinct custCode, custName, custSurname, custPhone
		from MainTable

insert into Camping (campCode, campName, numOfEmp)
	select distinct campCode, campName, numOfEmp
		from MainTable

insert into Category (catCode, areaM2, unitCost)
	select distinct catCode, areaM2, unitCost
		from MainTable

insert into Emplacement (campCode, empNo, catCode)
	select distinct campCode, empNo, catCode
		from MainTable

insert into Payment (payCode, payMethod)
	select distinct payCode, payMethod
		from MainTable

insert into Booking (bookCode, bookDt, payCode, custCode, staffNo)
	select distinct bookCode, bookDt, payCode, custCode, staffNo
		from MainTable

insert into Rental (bookCode, campCode, empNo, startDt, endDt, noPers)
	select distinct bookCode, campCode, empNo, startDt, endDt, noPers
		from MainTable