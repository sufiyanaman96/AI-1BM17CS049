Algorithm
Step. 1: If Ψ1 or Ψ2 is a variable or constant, then:
	a) If Ψ1 or Ψ2 are identical, then return NIL. 
	b) Else if Ψ1is a variable, 
		a. then if Ψ1 occurs in Ψ2, then return FAILURE
		b. Else return { (Ψ2/ Ψ1)}.
	c) Else if Ψ2 is a variable, 
		a. If Ψ2 occurs in Ψ1 then return FAILURE,
		b. Else return {( Ψ1/ Ψ2)}. 
	d) Else return FAILURE. 
Step.2: If the initial Predicate symbol in Ψ1 and Ψ2 are not same, then return FAILURE.
Step. 3: IF Ψ1 and Ψ2 have a different number of arguments, then return FAILURE.
Step. 4: Set Substitution set(SUBST) to NIL. 
Step. 5: For i=1 to the number of elements in Ψ1. 
	a) Call Unify function with the ith element of Ψ1 and ith element of Ψ2, and put the result into S.
	b) If S = failure then returns Failure
	c) If S ≠ NIL then do,
		a. Apply S to the remainder of both L1 and L2.
		b. SUBST= APPEND(S, SUBST). 
Step.6: Return SUBST. 
Code--
