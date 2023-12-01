package singletonTest;

import com.eurotechstudy.sdet.Singleton;
import org.testng.annotations.Test;

public class SingletonTest {

    @Test
    public void singletonTest(){

        //Singleton singleton = new Singleton();

        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton.toString());
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton2.toString());
        Singleton singleton3 = Singleton.getInstance();
        System.out.println(singleton3.toString());
        Singleton singleton4 = Singleton.getInstance();
        System.out.println(singleton4.toString());
    }
}
