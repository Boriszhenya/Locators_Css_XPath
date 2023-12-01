package singletonTest;

import com.eurotechstudy.sdet.Singleton;
import org.testng.annotations.Test;

public class SingletonTest2 {

    @Test
    public void singletonTest2(){
        Singleton singleton = Singleton.getInstance();
        System.out.println(singleton);
    }
}
