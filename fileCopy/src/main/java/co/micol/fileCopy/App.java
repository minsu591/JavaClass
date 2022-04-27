package co.micol.fileCopy;

import co.micol.fileCopy.prog.FileCopyProg;
import co.micol.fileCopy.prog.Practice;

public class App 
{
    public static void main( String[] args )
    {
//    	Practice pc = new Practice();
//    	pc.inputStreamRun();
//    	pc.outputStreamRun();
//    	System.out.println("======");
//    	pc.readRun();
    	
    	FileCopyProg fcp = new FileCopyProg();
    	fcp.run();
    }
}
