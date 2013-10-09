package q4;

public class Testing {
	 
    public static void main(String []str){
          Base b= new Base();
          Sub s= new Sub();
         
          System.out.println(b);
          System.out.println(s);
         
          b=s;
          System.out.println(b);
          System.out.println(s);
         
   
          //System.out.println(b.i);
          System.out.println("b.j="+b.j);
          System.out.println("b.k="+b.k);
         
          System.out.println("\n\n");
          //System.out.println(s.i);
          System.out.println("s.j="+s.j);
          System.out.println("s.k="+s.k);
         
          System.out.println("\n\n");
          //System.out.println(b.m);
          System.out.println(s.m);
         
          b=s;

          System.out.println(" after b=s\n"+"b.j="+b.j+" ");
          System.out.println("b.k="+b.k);
          
          System.out.println(" after b=s\n"+"s.j="+s.j+" ");
          System.out.println("s.k="+s.k);

         
          System.out.println("\n\n");
         

          System.out.println( (   (Sub)b  ).j);
          System.out.println(    ((Sub)b  ).k);
         
          System.out.println(  ((Sub)b  ) .m);
          System.out.println(((Sub)b).m);
          //System.out.println(n.m); n isn't declared

    }
}
/*
 * 
 * First run no comments removed.
b.j=1
b.k=2



s.j=4
s.k=5



 after b=s
b.j=1 
b.k=2



4
5
 * 
 * Second run first block of comments removed.
 i=0   j=1   k=2

i=3   j=4   k=5   m=10

i=3   j=4   k=5   m=10

i=3   j=4   k=5   m=10

b.j=1
b.k=2



s.j=4
s.k=5



 after b=s
b.j=1 
b.k=2



4
5
 * 
 * Can not uncomment b.i and s.i i is private.
 * 
 * Can not uncomment b.m m only exists in Sub class
 * 
 * Third run second block of comments removed. 
i=0   j=1   k=2

i=3   j=4   k=5   m=10

i=3   j=4   k=5   m=10

i=3   j=4   k=5   m=10

b.j=1
b.k=2



s.j=4
s.k=5



10
 after b=s
b.j=1 
b.k=2



4
5
 * 
 * fourth run Third block of comments removed.
i=0   j=1   k=2

i=3   j=4   k=5   m=10

i=3   j=4   k=5   m=10

i=3   j=4   k=5   m=10

b.j=1
b.k=2



s.j=4
s.k=5



10
 after b=s
b.j=1 
b.k=2
 after b=s
s.j=4 
s.k=5



4
5
 * 
 * fifth run fourth block of comments removed.
i=0   j=1   k=2

i=3   j=4   k=5   m=10

i=3   j=4   k=5   m=10

i=3   j=4   k=5   m=10

b.j=1
b.k=2



s.j=4
s.k=5



10
 after b=s
b.j=1 
b.k=2
 after b=s
s.j=4 
s.k=5



4
5
10
10 
 */
