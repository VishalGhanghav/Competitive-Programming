package Tests.src.com;
/*
ip:Given a string and no. of rows(n) and based on numbers
ip:String=PAHNAPLSIIGYIR and numRows = 4

n=5
p         i
a      s  i
h    l    g
n  p      y   r
a         i

op:piasihlgnpyrai
r1=7 r2=


i-1+(i-2)=2n-3










P          L       I
A     P    S    Y  R
H  A       I  G
N          I


n-1+n-2

n-2+n-3=2n-5=res

n-3+n-4=2n-7

n-res


     n-2+n-3
n-3+n-4

op:PLIAPSYRHAIGNI

r1=5 r2=3 1  r3=1 3  r4=5

pahnaplsi  n=3
p   a     I
a n p  S
h   l
r1=
 */
public class StringQuestion1 {
    public static void main(String[] args) {
        String result="PAHNAPLSIIGYIR";
        int noOfRows=4;
        System.out.println(getDiagonalString(result,noOfRows));
    }

    private static String getDiagonalString(String str,int noOfRows) {
        String res="";
        int k=0;
        for(int i=0;i<noOfRows;i++){

            if(i==0 || i==noOfRows-1 ){
                int p=2*noOfRows-3;
                char a = str.charAt(2 * noOfRows - 3);
                res+=a;
            }

        }

        return res;
    }
}
