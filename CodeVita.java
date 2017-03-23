import java.io.*;
public class CodeVita {
    public static void main(String args[]) throws IOException{
        int a[]=new int[120];
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the number");
        int no=Integer.parseInt(br.readLine());
        int temp=0;
        int abc=1;
        int k=0;
        int elements=no*(no+1)/2;
        for(int i=0;i<elements;i++){
           int T=temp+6+(abc-1)*16;
           temp=T;
           
           a[k]=temp;
           abc++;
          
            k++;
        }
        int x=0;
       for(int i=0;i<no;i++){
            for(int j=0;j<=i;j++){
            String b=String.format(" %05d ",a[x]);
            System.out.print(b);
            x++;
            }
            System.out.println(" ");
            
            
    }
    
}
}
