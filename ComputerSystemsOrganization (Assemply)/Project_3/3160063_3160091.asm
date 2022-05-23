#Authors:Stelios Kokkokyris
#        Vasilis Mauraganis
#Date:   27/12/2017

		.text
		.globl main
main:
		lw $s1, count	#$s1 is a couter
		la $a0, msg
		li $v0, 4  		#print(msg)
		syscall
		
		li $a0, 8		#2 x sizeof(int)
		li $v0, 9		
		syscall
		
		move $s0, $v0	#address of first node in memory stored in $s0
	
menu:	
		la $a0, msg0	
		li $v0, 4		#print(msg0)
		syscall

		li $v0, 5		#read(int)
		syscall
		
		move $t0, $v0	
		
		beq $t0, 0, exit
		beq $t0, 1, insert
		#beq $t0, 2, remove
		beq $t0, 3, printAsc
		#beq $t0, 4, printDesc
		j menu
		
exit:
		la $a0, msg6
		li $v0, 4		#print(msg6)
		syscall
		
		li $v0, 10
		syscall
				
insert:		addi $s1, $s1, 1
			jal Insert
			j menu
			
#remove:	jal Remove
			j menu

printAsc:	jal PrintAsc
			j menu

#printDesc:	jal PrintDesc
			j menu
		
Insert:
			sw $s1, count
			lw $t1, count
			
			la $a0, msg5
			li $v0, 4		#print(msg5)
			syscall
			
			li $v0, 5		#read(int1) insert item
			syscall
			
			bgt $t1, 1, addNewNode	#if the list is not empty go to addNewNode
			
			sw $v0, ($s0)	#store int1 at field data to first node
			sw $zero, 4($s0)	#store 0 at	field link to first node
			
			lw $t2, 0($s0)
			lw $t3, 4($s0)
			
			jr $ra
			
addNewNode: la $a0, msgCount
			li $v0, 4
			syscall
			
			la $a0, count
			li $v0, 1
			syscall
			
			blt $v0, $s0, l1	#if the insert item is greater than first item go to l1
			
			sw $v0, 8($s0)      #store new int at field data to second node
			sw $zero, 12($s0)	#store 0 at field link to second node  
			addi $t3, $s0, 8	#store to $t3 the address of second item in list
			sw $t3, 4($s0)		#link first node with second
			
			jr $ra
			
l1:			#move 8($s0), 0($s0)
			#sw $zero, 12($s0)
			#sw $v0, 0($s0)
			#la 4($s0), 4($s0), 4
			
			jr $ra
			
Remove:
PrintAsc:
			la $a0, msg7	#print(msg7)
			li $v0, 4
			syscall
			
			lw $a0, 0($s0)	#print first element
			li $v0, 1
			syscall 
			
			lw $a0, 8($s0)	#print first element
			li $v0, 1
			syscall 
			
			la $a0, CRLF	#print(CRLF)
			li $v0, 4
			syscall
			jr $ra
PrintDesc:

		.data
msg:	.asciiz "Linked list representation in ascending order.\n"
msg0:	.asciiz "\nEnter 0 to exit \n 1 for insert a new item in list\n 2 for remove a node from list\n 3 to print the list in ascending order\n 4 to print the list in descending order\n"
msg1:	.asciiz "Insert a node in list at correct position.\n"
msg2:	.asciiz "Remove a node from list.\n"
msg3:	.asciiz "Print the list in ascending order.\n"
msg4:	.asciiz "Print the list in descending order.\n"
msg5:	.asciiz "Enter a new item.\n"
msg6:	.asciiz "Exit from the program."
msg7:	.asciiz "The elements of linked list are : "
CRLF:	.asciiz "\n"
count:	.word 0
msgCount: .asciiz "Count is : "