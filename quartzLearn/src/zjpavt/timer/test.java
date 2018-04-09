package zjpavt.timer;

public class test {
    public static int i=1;
    static{

        i = 20;

        //这里的i， 是不能被用作运算的， 因为本质上 i 还未被定义

    }
    public  static void main(String[] args){
        System.out.println(i);
    }

    }