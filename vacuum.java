public class VacuumCleaner {
	public static int score = 0;
	public static void main(String[] args) {
		boolean[] isDirty = new boolean[4];		
		setState(isDirty);
		System.out.println("Performance for S = 0% & P = 10%: " + runVacuum(0, .1, isDirty));
		System.out.println("Performance for S = 25% & P = 10%: " + runVacuum(.25, .1, isDirty));
		System.out.println("Performance for S = 100% & P = 10%: " + runVacuum(1, .1, isDirty));
		System.out.println("Performance for S = 0% & P = 90%: " + runVacuum(0, .9, isDirty));
		System.out.println("Performance for S = 25% & P = 90%: " + runVacuum(.25, .9, isDirty));
		System.out.println("Performance for S = 100% & P = 90%: " + runVacuum(1, .9, isDirty));
	}
	public static int runVacuum(double s, double p, boolean[] isDirty) {
		boolean isForward = true; // keeps track of vacuum's moving direction
		int square = 0;
		score = 0; 
		for(int i = 0; i < 2000; i++) {
			setState(isDirty);
			double chanceCorrect = Math.random();
			if(chanceCorrect <= p) {
				if(isDirty[square]) {	
					double didClean = Math.random();
					if(didClean <= s) {
						isDirty[square] = false;
					}
				}
				else {
					if(isForward == true && square < 3) square++;
					else if(isForward == false && square > 0) square--;
					else if(isForward == true && square == 3) {
						isForward = false;
						square --;
					} else if(isForward == false && square == 0) {
						isForward = true;
						square++;
					} else System.err.println("This isn't right.");
				}
			}
			else {
				if(isDirty[square]) {
					if(isForward == true && square < 3) square++;
					else if(isForward == false && square > 0) square--;
					else if(isForward == true && square == 3) {
						isForward = false;
						square --;
					} else if(isForward == false && square == 0) {
						isForward = true;
						square++;
					} else System.err.println("This isn't right.");
				}
				else continue;
			}
			score += getScore(isDirty);
		}
		return score;
	}	
	public static void setState(boolean[] isDirty) {
		for(int i = 0; i < isDirty.length; i++) {
			double chance = Math.random();
			if(chance <= .2) isDirty[i] = true;
			else isDirty[i] = false;
		}
	}
	
	public static int getScore(boolean[] isDirty) {
		int cleanSquares = 0;
		for(int i = 0; i < isDirty.length; i++) {
			if(isDirty[i] == false) cleanSquares++;
		}
		return cleanSquares;
	}
}
