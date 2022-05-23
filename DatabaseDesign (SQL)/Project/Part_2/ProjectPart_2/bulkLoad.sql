SET DATEFORMAT dmy; 
BULK INSERT mainTable
 FROM 'C:\Users\hp\Desktop\ΣΧΕΔΙΑΜΣΟΣ ΒΑΣΕΩΝ ΔΕΔΟΜΕΝΩΝ\Εργασιες\Εργασια\ΜΕΡΟΣ_Β\GeneratedData.txt'
WITH (FIRSTROW = 2,FIELDTERMINATOR= ',', ROWTERMINATOR = '\n');