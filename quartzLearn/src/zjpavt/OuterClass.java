package zjpavt;

public class OuterClass
{
    static{
        System.out.println("OuterClass static load.");
    }

    public OuterClass()
    {
        System.out.println("flag");
    }
    public OuterClass(String flag)
    {
        System.out.println("flag:"+flag);
    }

    class InnerClass
    {
        //private static String te = "";
        /*static{
            System.out.println("InnerClass static load.");
        }*/
        private OuterClass out = new OuterClass("inner");
    }
    static class InnerStaticClass
    {
        private static OuterClass out = new OuterClass("innerStatic");
        static{
            System.out.println("InnerStaticClass static load.");
        }
        private static void load()
        {
            System.out.println("InnerStaticClass func load().");
        }
    }
    public static OuterClass getInstatnce()
    {
        return InnerStaticClass.out;
    }
    public static void main(String[] args)
    {
        OuterClass outerClass = new OuterClass();
        System.out.println("new finished");
        InnerStaticClass.load();
        OuterClass out = InnerStaticClass.out;
        InnerClass innerClass = out.new InnerClass() ;
        //OuterClass.InnerStaticClass.out.
    }
}