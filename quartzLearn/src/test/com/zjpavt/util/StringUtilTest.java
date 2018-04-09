package test.com.zjpavt.util; 

import com.zjpavt.util.StringUtil;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After; 

/** 
* StringUtil Tester. 
* 
* @author zyc 
* @since <pre>04/04/2018</pre> 
* @version 1.0 
*/ 
public class StringUtilTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: isNullOrEmpty(String... str) 
* 
*/ 
@Test
public void testIsNullOrEmpty() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: commandStr2Int(String command) 
* 
*/ 
@Test
public void testCommandStr2Int() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: commandInt2String(int[] command) 
* 
*/ 
@Test
public void testCommandInt2String() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: decodeHex(char[] data) 
* 
*/ 
@Test
public void testDecodeHex() throws Exception {
    char[] hexChar = {'8','0'};

    byte[] result = StringUtil.decodeHex(hexChar);
    for(byte bit : result){
        System.out.println(String.valueOf(bit));
        System.out.println(String.valueOf(bit >> 1));
    }
    assert(result[0] == 17);
}


/** 
* 
* Method: toDigit(char ch, int index)
* 
*/ 
@Test
public void testToDigit() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = StringUtil.getClass().getMethod("toDigit", char.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
