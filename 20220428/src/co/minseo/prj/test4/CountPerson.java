package co.minseo.prj.test4;

public class CountPerson {
	public int gender;
	public int countM = 0;
	public int countF = 0;

	public void checkPerson(String[] ssnArr) {
		for (int i = 0; i < ssnArr.length; i++) {
			gender = Integer.parseInt(ssnArr[i].substring(ssnArr[i].length() - 1));
			if (gender == 1 || gender == 3) {
				countM++;
			} else if (gender == 2 || gender == 4) {
				countF++;
			}
		}
	}

	public void printPerson() {
		System.out.println("남 " + countM + " 여 " + countF);
	}

}
