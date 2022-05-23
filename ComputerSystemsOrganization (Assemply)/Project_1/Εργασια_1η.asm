# Author:      Stelios Kokkokyris
# Date:        13/11/2017
# Description: Αρχικά ελέγχουμε τα δεδομένα.Σε περίπτωση που δεν είναι έγκυρα
#		       το πρόγραμμα τερματίζει και τυπώνεται κατάλληλο μήνυμα.Διαφορε-
#			   τικά υπολογίζει τα ρέστα,τα τυπώνει και το πρόγραμμα τερματίζει.
			   
		
		#---------------------------
		# $s0 = ευρώ 
		# $s1 = λεπτά του ευρώ
		# $s2 = ρέστα
		# $t0 = αποτέλεσμα συνθήκης $s0 < 21 
		# $t1 = αποτέλεσμα συνθήκης $s1 < 100
		# $t2 = αποτέλεσμα πράξης & μεταξύ των $s0, $s1 και τη σταθερα 1
		# $t3 = αποτέλεσμα συνθήκης $s2 < 0 και τη σταθερα 2
		# $t4 = αποτέλεσμα διαίρεσης για τα ευρώ   
		# $t5 = αποτέλεσμα διαίρεσης για τα λεπτά του ευρώ
		# $t6 = 5
		# $t7 = 10
		# $t8 = 20
		# $t9 = 50
		#---------------------------
		.text
	    .globl main
		
main:

		# Pseudocode:
		# print "Enter the number of euros"
		# euros = read int 
		# print "Enter the number of cents"
		# cents = read int 
		la $a0, euros           # εμφάνισε τη συμβολοσειρά       
		li $v0, 4               # "Enter the nubmer of euros: "
		li $v0, 5               # διάβασε έναν ακέραιο euros   	        
		move $s0, $v0			# $s0 = euros	
		la $a0, cents           # εμφάνισε τη συμβολοσειρά
		li $v0, 4				# "Enter the number of cents: "
		li $v0, 5	            # διάβασε ένα ακέραιο cents	                 
		move $s1, $v0			# $s1 = cents						    	
		
		# Pseudocode:
		# print "Fee: 5 euros and 24 cents"											
		la $a0, fee             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # "Fee: 5 euros and 24 cents"	
		syscall                 # στην οθόνη
		
		# Pseudocode:
		# if euros < 21 && cents < 100 
		slti $t0, $s0, 21       # if($s0 < 21)  $t0 = 1
		slti $t1, $s1, 100      # if($s1 < 100) $t1 = 1
		and $t2, $t0, $t1       # $t2 = $t0 & $t1
		beq $t2, $zero, L1      # if($t2 == 0) go to L1
		
		# Pseudocode:
		# else
		#	euros = euros - 5
		#	cents = cents - 24
		#	change = euros + cents
		# 	if change > 0
		# 		 
		add $s0, $s0, -5        # $s0 = $s0 - 5
		add $s1, $s1, -24       # $s1 = $s1 - 24
		add $s2, $s0, $s1       # $s2 = $s0 + $s1
		bgt $s2, $zero, Result  # if($s2 > 0) go to L2
		
		# Pseudocode:
		# 	else if change == 0
		# 		print "Change = 0"
		beq $s2, $zero, L3      # if($s2 == 0) go to L3
		
		# Pseudocode:
		#	else if change < 0 
		#		print "Error! Not enough money!"
		slti $t3, $s2, 0        # if($s2 < 0) $t3 = 1
		bne $t3, $zero, L4      # if($s2 < 0) go to L4
		
	    # Pseudocode:
		# print "Error! Please try again."
L1:    	la $a0, error_message_1 # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # "Error! Please try again."
	    syscall		            #  στην οθόνη	
		j exit                  # jump to exit
		
		# Pseudocode:
		# print "Change: \n"
L2:     la $a0, change_1        # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # "Change: "
	    syscall		            #  στην οθόνη	
		la $a0, CRLF            # άλλαξε τη γραμμή της            
	    li $v0, 4               # οθόνης
	    syscall    
		
		# Pseudocode:
		# $t2 = 1
		# $t3 = 2
		# $t6 = 5
		# $t7 = 10
		# $t8 = 20
		# $t9 = 50
		lw $t2, 1               # load 1  to $t2
		lw $t3, 2               # load 2  to $t3
		lw $t6, 5               # load 5  to $t6              
		lw $t7, 10              # load 10 to $t7
		lw $t8, 20              # load 20 to $t8
		lw $t9, 50              # load 50 to $t9
		
		# Pseudocode:
		# e = euros / 10
		# euros = euros % 10
		# print e
		div $t4, $s0, 10        # $t4 = $s0 / 10                             
		rem $s0, $s0, 10        # $s0 = $s0 % 10                             
		move $a0, $t4           # εμφάνισε το
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t7           # εμφάνισε το ($t7=10)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_euros       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " euros "
	    syscall		      		# στην οθόνη
        la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# e = euros / 5
		# euros = euros % 5
		# print e
		div $t4, $s0, 5         # $t4 = $s0 / 5
		rem $s0, $s0, 5         # $s0 = $s0 % 5
		move $a0, $t4           # εμφάνισε το
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t6           # εμφάνισε το ($t6=5)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_euros       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " euros "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# e = euros / 2
		# euros = euros % 2
		# print e
		div $t4, $s0, 2         # $t4 = $s0 / 2
		rem $s0, $s0, 2         # $s0 = $s0 % 2
		move $a0, $t4           # εμφάνισε το 
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t3           # εμφάνισε το ($t3=2)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_euros       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " euros "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# e = euros / 1
		# print e
		div $t4, $s0, 1         # $t4 = $s0 / 1
	    move $a0, $t4           # εμφάνισε το 
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t2           # εμφάνισε το ($t2=1)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_euros      # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " euros "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# c = cents / 50
		# cents = cents % 50
		# print c
		div $t5, $s1, 50        # $t5 = $s1 / 50
		rem $s1, $s1, 50        # $s1 = $s1 % 50
		move $a0, $t5           # εμφάνισε το 
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t9           # εμφάνισε το ($t9=50)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_cents       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " cents "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# c = cents / 20
		# cents = cents % 20
		# print c
		div $t5, $s1, 20        # $t5 = $s1 / 20
		rem $s1, $s1, 20        # $s1 = $s1 % 20
		move $a0, $t5           # εμφάνισε το
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t8           # εμφάνισε το ($t8=20)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_cents       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " cents "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# c = cents / 10
		# cents = cents % 10
		# print c
		div $t5, $s1, 10        # $t5 = $s1 / 10
		rem $s1, $s1, 10        # $s1 = $s1 % 10
		move $a0, $t5           # εμφάνισε το 
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t7           # εμφάνισε το ($t7=10)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_cents       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " cents "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# c = cents / 5
		# cents = cents % 5
		# print c
		div $t5, $s1, 5         # $t5 = $s1 / 5 
		rem $s1, $s1, 5         # $s1 = $s1 % 5
		move $a0, $t5           # εμφάνισε το 
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t6           # εμφάνισε το ($t6=5)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_cents       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " cents "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# c = cents / 2
		# cents = cents % 2
		# print c
		div $t5, $s1, 2         # $t5 = $s1 / 2
		rem $s1, $s1, 2         # $s1 = $s1 % 2
		move $a0, $t5           # εμφάνισε το 
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t3           # εμφάνισε το ($t3=2)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_cents       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " cents "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		
		# Pseudocode:
		# c = cents / 1
		# print c
		div $t5, $s1, 1         # $t5 = $s1 / 1
		move $a0, $t5           # εμφάνισε το
		li $v0, 1               # αποτέλεσμα στην οθόνη
		syscall
		la $a0, str             # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " x "
	    syscall		      		# στην οθόνη
        move $a0, $t2           # εμφάνισε το ($t2=1)
	    li $v0, 1               # αποτέλεσμα στην οθόνη
	    syscall		
        la $a0, str_cents       # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # " cents "
	    syscall		      		# στην οθόνη
		la $a0, CRLF            # άλλαξε τη γραμμή της
	    li $v0, 4               # οθόνης
	    syscall 
		j exit                  # jump to exit
		
		# Pseudocode:
		# print "Change = 0"
L3:		la $a0, change_2        # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # "Change = 0"
	    syscall		            #  στην οθόνη	
		j exit                  # jump to exit
		
		# Pseudocode:
		# print "Error! Not enough money!"
L4:	    la $a0, error_message_2 # εμφάνισε τη συμβολοσειρά 
	    li $v0, 4               # "Error! Not enough money!"
	    syscall		            #  στην οθόνη	
		j exit                  # jump to exit
	
exit:   li $v0, 10              # βγες από το πρόγραμμα
	    syscall
		
		             .data
str:                 .asciiz " x "                          #
str_euros:           .asciiz " euros "                      #
str_cents:           .asciiz " cents "                      #
euros:				 .asciiz "Enter the number of euros: "	# οδηγίες ορισμού	 
cents:               .asciiz "Enter the number of cents: "  # συμβολοσειρών
error_message_1:     .asciiz "Error! Please try again."     #
change_1:            .asciiz "Change: "                     # 
change_2:            .asciiz "Change = 0"                   #
error_message_2:     .asciiz "Error! Not enough money!"     #
CRLF:                .asciiz "\n"  	                        #
