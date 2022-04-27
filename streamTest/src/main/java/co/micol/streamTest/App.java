package co.micol.streamTest;

import co.micol.streamTest.exam.ReadExample;
import co.micol.streamTest.exam.ReaderExample;
import co.micol.streamTest.exam.WriteExample;

public class App 
{
    public static void main( String[] args )
    {
//        WriteExample we = new WriteExample();
//        we.run();
        
        ReadExample re = new ReadExample();
        re.run();
        
        System.out.println("=====================");
        
        ReaderExample rde = new ReaderExample();
        rde.run();
        
    }
}
