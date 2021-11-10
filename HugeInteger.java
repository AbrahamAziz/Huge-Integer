package lab1andlab2;

import java.util.*;
import java.lang.*;

/**
 * @author Abraham Aziz 
 */
public class HugeInteger {
    private int sign_of_num; // sign_of_num of the number
    private int[] array;
    private int length; 
    
    public HugeInteger(String val) throws IllegalArgumentException {
    	    	if (val == null)
    	            throw new IllegalArgumentException("Invalid input"); // gives this error if nothing is inputed
    	           	  	    	
    	    	int temp = 0; 			// goes through the array when values are being set
    	    	char [] values = val.toCharArray(); // converts the string into separate character so it is easier to handle
    	    	length = values.length;	// sets the length of the value
    	    	int all_zero = 1; 		//checks if the array has all zeros
    	    	sign_of_num = 1;  				//setting the default sign a positive
    	    	
    	    	if (values[0] == '-') {   // checks if the fists character is - 
    	    		sign_of_num = 0;  // 0 means the number is negative
    	    		length = values.length - 1;}  // length is decreased by 1 since the first variable is - 
   	    	    	    	
    	    	int Zero_test = length;
    	    	int Zero_counter = 0;
    	    	int counter =0;
    	    		while(Zero_test >= 0) { // this loop starts from the end 
    	    			if (sign_of_num == 0) { // for numbers that are negative
    	    				if(values[Zero_test] != '0') {  // confirms if all the numbers are zero
    	    					all_zero = 0; // if it is not true
    	    					break;}}
    	    			else if ((sign_of_num == 1)&&(Zero_counter >= 1)) { //for positive number, and makes sure the length is -1
        	    				if(values[Zero_test] != '0') {  // confirms if all the numbers are zero
        	    					all_zero = 0; // if it is not true
        	    					break;}	}	
        	    		Zero_counter++;
    	    			Zero_test --;
    	    		}
    	    	
    	    	if (all_zero == 1) { // if one it means that all the character in the string is a zero
    	    		array = new int[1];
    	    		sign_of_num = 1; // makes sign positive since zero cannot be -
    	    		array[0] = 0;// adds the val of zero
    	    		length = 1;
    	    		return; // leaves the method
    	    	}
    	       	    	
    	    	   //Removing leading zeros 
    	        int remove_zero = length;
    	        if(sign_of_num == 1) { // for positive numbers
    	            while (counter < remove_zero) {
    	                if(values[counter] == '0') 
    	                    length--; // decreases the length since we dont want to account for the zero
    	                else
    	                    break;
    	                counter++;
    	        }
    	        }
    	        counter = 1;
    	        if(sign_of_num == 0) { // for negitive numbers
    	            while (counter < remove_zero) {
    	                if(values[counter] == '0') 
    	                    length--; // decreases the length since we dont want to account for the zero
    	                else
    	                    break;
    	                counter++;
    	        }
    	        }
   	    	
    	    	array = new int[length]; // Initializing the array
    	        counter = remove_zero-1; // counts through the loop
    	    	int test =  remove_zero - length; // new length so the leading zero is not considered
    	    	
    	    	if (sign_of_num==1) { // input of the sign is positive 
    				while(counter >= test) {
    					//Check if each element is valid and add
    					if(values[counter] >= '0' && values[counter] <= '9') { // goes through the array to make sure the number is between 0 -9, array is flipped so it easer to handle in other function
    						array[temp] = Character.getNumericValue(values[counter]); // puts the number into the array, and converts the char into int
    						temp++;
    					}
    					// if there is something other than a digit: Invalid input
    					else {// if the input is not a number error is given
    						throw new IllegalArgumentException("Invalid input"); } 
    					counter--;
    			}
    	    	}
    			else { // assumes the number is negative
    				counter = remove_zero;
    				while(counter > test) {
    					//Check if each element is valid and add
    					if(values[counter] >= '0' && values[counter] <= '9') { // goes through the array to make sure the number is between 0 -9, array is flipped so it easer to handle in other function
    						array[temp] = Character.getNumericValue(values[counter]); // puts the number into the array, and converts the char into int
    						temp++;
    					}
    					// if there is something other than a digit: Invalid input
    					else {// if the input is not a number
    						throw new IllegalArgumentException("Invalid input"); } 
    					counter--;
    			}    		
    	    }
         }
    
 
   
    
    	    HugeInteger(int n) throws IllegalArgumentException{
    	        if (n < 1) { throw new IllegalArgumentException("Invalid Entry"); } // n cannot be less then 1
    	        length = n; // sets the length as n 
    	        int max =1; int min = 0; // set value for the range
    	        sign_of_num = (int)Math.random() * ((max - min + 1) + min );// Assigning a sign, 1 = positive and 0 = negative 			
    	        array = new int[n]; // sets the size of the array
    	        max = 9; min =1;
    	        array[0] = (int)Math.random() * ((max - min+ 1) + min) ; //	 assigning number from 1 to 9, sing the first number cannot start with zero
    	        min = 0;
    	        for(int i=n ; i==0 ; i--) {
    	        	array[i] = (int)Math.random() *( (max - min + 1) + min);//assigning number from 0 to 9	
    	        }
    	    }
    	    
    public void setarray(int n,int val){this.array[n] = val;}
    public void setsign_of_num(int val){this.sign_of_num = val;} 
    public int getLength(){return this.length;}
    public int getarray(int n){return this.array[n];}
    public int getsign_of_num(){return this.sign_of_num;}	    
    
    	    
    
    public HugeInteger add(HugeInteger h) {
    		int copy = 0; // inatailizes a copy 
    		int carry = 0; // contains the carry bit
    		int big_len = 0; // stores the length of the bigger val
    		int small_len = 0; // stores value of smaller
    		int change = 0; // used to change the size
    	
    	 	if(h.toString().equals("0")) { // since the number is zero there is nothing to add
	            return(this);
    	 		}
	        
    	 		if(this.toString().equals("0")) {  	// since the number is zero there is nothing to add          
    	 			return(h);
    	 		}	
    	 		   	 	
    	 		if(this.sign_of_num != h.getsign_of_num()) {	// subtract is done when they are not the same size
    	    	  	HugeInteger diff_sign = new HugeInteger(h.toString());
    		    	if(h.getsign_of_num()==1)  // setting the sign of the huge integer
    		    		diff_sign.setsign_of_num(0);
    		    	else
    		    		diff_sign.setsign_of_num(1);   		    	
    		    	return(this.subtract(diff_sign)); // going through the subtract function    	    	  	
    		    }  

    		    if(this.sign_of_num == h.getsign_of_num()) { // for cases where both numbers are positive or negitive 
    	    		
    		    	if(this.length > h.getLength()) { 	// checks condition on which is bigger or smaller in length	    		
    		    		big_len=this.length; small_len = h.getLength(); 		    	
    		    	}
    		    	else { // sets val as other condition less then and equal
    		    		big_len=h.getLength(); small_len = this.length;
    		    		}	
    		    }
    		    HugeInteger Add = new HugeInteger(big_len +1); // the +1 if for the  potential carry
    	  		HugeInteger AddLess = new HugeInteger(big_len); // Create new Huge interger with 1 less space
    	  		
    	  		
    		    	if(this.sign_of_num == 0) {  // sets the sign of the Add, which does not matter since the sign is the same
    	    	  		Add.setsign_of_num(0);
    		    	    AddLess.setsign_of_num(0);}
    	    	  	else {
    	    	  		Add.setsign_of_num(1);
    		    	    AddLess.setsign_of_num(1);}
    		    	
    		    	while(copy < big_len) { // goes through till the biggest length, so all number are accounted for
   		    		
    		    		if (copy<small_len) { // adds both arrays until the smaller one is all added
    		    			
    		    		   int temp = this.array[copy] + h.getarray(copy) + carry;
        		    	   if (temp<10) { // check if a carry is needed
        		    			Add.setarray(copy,temp); // Copies the t 
        		    	     	carry=0;}
        		        	else {	 // if a carry is needed	
        		    			int temp2 = temp - 10;
        		    			Add.setarray(copy,temp2); // stores the last digit of the carry
        		        		carry=1;}	
    		    			}
    		    		else {			
	    		    		if(this.length > h.getLength()) {   // checks which reaming array needs to be added to the Add with the carry		    		   			
	    		    			int addtemp = this.array[copy] + carry;
	    		    			if(addtemp<10) { // check if a carry is needed
	    		    				Add.setarray(copy,addtemp);
	    		    				carry = 0;
	    			    			}
	    		    			else {
	    		    				int addtemp2 = addtemp - 10;
	    		    				Add.setarray(copy,addtemp2);
	    			    			carry=1;
	    			    			}}
	    		    		else {// occurs when h is bigger then huge intager
	    		    			int addtemp3 = h.getarray(copy) + carry;
	    		    			if(addtemp3<10) { // check if a carry is needed
	    		    				Add.setarray(copy,addtemp3);
	    		    				carry = 0;
	    			    			}
	    		    			else { // carry is needed
	    		    				int addtemp4 = addtemp3 - 10;
	    		    				Add.setarray(copy,addtemp4);
	    			    			carry=1;
	    			    			}	
	    			    			}}
    		    		copy++; // increments by one
    		    		}
    		    	
    		    		if (carry==0) { // if there is no carry the extra array space that was added at the begaining needs to be removed    	    	  		
    	    	  		while( change <big_len) {
    	    	  			AddLess.setarray(change, Add.getarray(change));// Copies the Add to AddLess
    	    	  			change++;
    	    	  		}
    	    	  		AddLess.setsign_of_num(Add.getsign_of_num()); // copies the sign of Add
    	    	  		return AddLess;
    	    	  	}
    	    	  	Add.setarray(big_len,1);// since a carry is present, 99+1= 100.
    	    	  	return Add;
    		    }
    	    	  	
   	   	    
    public HugeInteger subtract(HugeInteger h) {     
		
		HugeInteger diff_sign = new HugeInteger(h.toString()); // huge integer where the sign needs to be changed
		String sub = ""; // hold the value when subtract occurs
		HugeInteger big_num = new HugeInteger(this.toString());
		HugeInteger small_num = new HugeInteger(h.toString());
		big_num.setsign_of_num(1); // making the signs positive
		small_num.setsign_of_num(1); // making the signs positive
		HugeInteger Buffer = big_num; // store value temporally
		
		int big_len = 0; 	// stores the length of the bigger val
		int small_len = 0;	// stores the length of the small val
		int check = big_num.compareTo(small_num); // compares the two values
		int sub_sign = this.sign_of_num;
		int borrow = 1; 
		
		if(this.length > h.getLength()) { 	// checks condition on which is bigger or smaller in length	    		
    		big_len=this.length; small_len = h.getLength(); 		    	
    	}
    	else { // sets val as other condition less then and equal
    		big_len=h.getLength(); small_len = this.length;
    		}	
			
		if(h.toString().equals("0") ) { // returns the original value if 0 is subtracted
			return(this);
		}
		
		if(h.getsign_of_num()==1) // changing the sign
			diff_sign.setsign_of_num(0);
    	else
    		diff_sign.setsign_of_num(1);
		
		
		if(this.toString().equals("0")) { // if the original value is zero, h is returned with flipped sign
			return(diff_sign);
		}
		
		if(this.sign_of_num != h.getsign_of_num()) {	// addition is done when you subtract two number with diff sign    
	    	return(this.add(diff_sign));
		}		
		
		
		if(check == 0) { // returns zero if both values are equal
    		sub += "0";
    		return new HugeInteger(sub);
    	}
			
	    if(check == -1) { // swaps the value if h is larger then this
	    	big_num = small_num;
	    	small_num = Buffer;			    	
		    sub_sign = 1; //adjust the sign due to the swap , if h is negative the result will be positive, and vice versa
		    if(h.getsign_of_num()==1)
		    	sub_sign = 0;  	}


	    	for(int i=0; i < small_len; i++) { // going through all the value of the smaller in	   	
	    		if(big_num.array[i]<small_num.array[i]) { // burrow needs to occur whens bigger num is smaller then small num
	    			while(big_num.array[i+borrow] == 0) { // checks how many borrow need to occur
	    				borrow++;}
	    			//Updating the digits until we get back to the digit that was smaller
	    			while(borrow != 0 ) {
	    				big_num.array[i+borrow]--; // makes the last borrow value a 9 
	    				big_num.array[i + borrow - 1] += 10; // does a borrow by adding 10
	    				borrow--;
	    			}
	    			borrow = 1; // Resets so a borrow can occur again
	    		}	    		
	    		sub = Integer.toString(big_num.array[i]-small_num.array[i]) + sub; // doing the subract and adding it to the string
	    	}	
	    
	      	for(int i=small_len; i<big_len;i++) { // the rest of the bigger value
	      		sub = Integer.toString(big_num.array[i]) + sub;
	      	}
	
	      	if (sub_sign == 0) { // add sign if the number needs to be negative
	      		sub = "-" + sub;
	      	}	      		      
	    	return new HugeInteger(sub);				
	}
	
    	    

	public HugeInteger multiply(HugeInteger h) {
		if(h.toString().equals("0")|| this.toString().equals("0")) { // this check if one the the values being multiplied is zero
			return new HugeInteger("0"); // returns a zero
		}
		String answer="";
		int[]  temp = new int[this.length+h.length]; // temp value to store all the multiplied value
		
		for(int i=this.length-1;i>=0;i--) { // going through all the values that need to be multiplied
			for(int j=h.length-1;j>=0;j--) {
				temp[i+j]+=this.array[i]*h.array[j]; // adding the multiplied value to the array at the correct loclation 
			}
		}
		
        for(int i=0;i<=temp.length-1;i++){ // number stored in reverse, so this is starting from the begaining
        	 for( ;temp[i]>=10;temp[i]-=10){  // subtracts 10  eg.example if i = 23 , 2 get moved to the next number while the 3 remains 
            	temp[i+1]++; // moves the carry to the next value
            } }
		
		for(int i=temp.length-1;i>=0;i--) { // adds all the value that is in temp into a string
			answer+=temp[i];
		}
		HugeInteger finalanswer =new HugeInteger(answer);
		
		if(this.sign_of_num != h.getsign_of_num()) { // assigns a negative sign if one of the value that is being multi
			finalanswer.sign_of_num=0;
		}	
		return  finalanswer;
	}
    	    
	
    	    
    	    public int compareTo(HugeInteger h) {   
    	    	int huge_sign = this.sign_of_num ;
    	    	int h_sign = h.getsign_of_num();
    	    	if (huge_sign > h_sign) { // checks the sign and depending on the sign makes a value bigger or smaller
    	    		return 1;}
    	    	
    	    	else if ((huge_sign < h_sign)) {
    	            return -1;}
    	        
    	    	if((this.length > h.getLength()) && (huge_sign == 1 && h_sign == 1)) { 	// checks condition on which is bigger or smaller in length	  for both positive number   		
    	    		return 1;} 
    	    	
		    	else if ((this.length < h.getLength()) && (huge_sign == 1 && h_sign == 1))  { // sets -1 if the 0
		    		return -1; }
    	    	
    	    	if((this.length > h.getLength()) && (huge_sign == 0 && h_sign == 0)) { 	// checks condition on which is bigger or smaller in length	    		
    	    		return -1;}
    	    	
		    	else if ((this.length < h.getLength()) && (huge_sign == 0 && h_sign == 0))  { // sets val as other condition less then and equal
		    		return 1;}	
    	    	
    	         int equal_len = this.length;
    	        
    	    	if (huge_sign == 1 && h_sign == 1) { // both positive 
    	    		for (int i = equal_len-1; i >= 0; i--) { // array stored in reverse
        	            int huge_a = this.array[i], H_a = h.getarray(i);// sets value
        	            if (huge_a > H_a) {
        	                return  1; // when huge integer is bigger then h
        	            }
        	            if (huge_a < H_a) {
        	                return  -1 ; // when huge integer is smaller then h
        	            }
    	    			}
    	    	}
    	    	else if (huge_sign == 0 && h_sign == 0) { // both number are negative 
    	    		for (int i = equal_len-1; i >= 0; i--) {  // array stored in reverse
    	    			int huge_a = this.array[i], H_a = h.getarray(i); // sets value
    	    			 if (huge_a > H_a) {
         	                return  -1; // when huge integer is bigger then h
         	            }
         	            if (huge_a < H_a) {
         	                return  1;    // when huge integer is smaller then h
         	            }        	             
        	            }
        	        	
    	    		}  
    	    			return 0; // returns zero if both are the same
        	    }
    	    	
 
    		public String toString( ) { 
    			String Final = new String();	// sets as string
    			if(sign_of_num == 0) 
    				Final = Final.concat("-");// put the - in the first array if number is negitve
    			for(int i=length-1; i>=0 ; i-- ) {  // for loop start at the last index , since the number was stored in reverse. this make the output bake to normal
    				Final = Final.concat(Integer.toString(array[i])); // converts to string, and appends it to the string
    			} 	
    			return Final; 
    		}
    	}
