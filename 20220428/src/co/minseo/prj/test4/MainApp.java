package co.minseo.prj.test4;

public class MainApp {
	public static void main(String[] args) {
		String[] arr = {"010102-4","991012-1","960304-1","881012-2","040403-3"};
		
		CountPerson cp = new CountPerson();
		cp.checkPerson(arr);
		cp.printPerson();
	}

}
