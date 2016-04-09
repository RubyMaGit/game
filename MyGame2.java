import java.util.ArrayList;
import java.util.Scanner;


public class MyGame2 {

    /**
     * @param args
     * CLASSIC 代表小动物数；SIZE代表签可容纳的元素个数；WIDTH和HEIGHT代表面板的长*宽
     */
    final static int CLASSIC= 4;
    final static int SIZE=6;
    final static int WIDTH=5;
    final static int HEIGHT=5;

    final static int[][] Data = CreateBoard(WIDTH, HEIGHT);
    final static ArrayList<Integer> list=new ArrayList<Integer>();
    static boolean mark=false;
    static int current=-1;

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        Scanner input = new Scanner(System.in);

        while(list.size() <= SIZE)
        {
            PrintBoard(Data);
            PrintList(list);

            try{

                System.out.println("Please input your select, like 0,0: ");
                String s = input.next() ;
                String[] nums = s.split(",") ;
                int row = Integer.parseInt(nums[0]) ;
                int column = Integer.parseInt(nums[1]) ;

                //如果戳取的不是最下面一行的元素，则重新戳取;如果戳取的是已经选择过的元素，也重新戳取
                if(row>0 && Data[row-1][column]!=-1)
                {
                    System.out.println("test1: The location can't be reached, please input it again: ");
                    continue;
                }
                else if(Data[row][column]==-1){
                    System.out.println("test2: The location has been selected, please input it again: ");
                    continue;
                }
                else
                {
                    System.out.println("Adding " +Data[row][column]);
                    list.add(Data[row][column]);
                    current=current+1;
                    //System.out.println("test4: The current location is ..."+current);
                    Data[row][column]=-1;
                }

            }
            catch (Exception e)
            {
                System.out.println("test3: The input is not available, please input it again! ");
            }


            //如果当前签上元素大于两个，进行比较；如果当前元素与前一个元素值相等，且mark为true，则表明最近三个元素值均相等，执行删除操作
            if(current>0){
                if(list.get(current).equals(list.get(current-1))&& mark==true)
                {
                    System.out.println("Three same items have been detected, so delete them...");

                    list.remove(current);
                    list.remove(current-1);
                    list.remove(current-2);
                    mark=false;
                    current= current-3;
                }
                //如果只是当前元素与前一个元素值相等，mark为false，则修改mark为true
                else if (list.get(current).equals(list.get(current-1))&&mark==false)
                {
                    mark=true;
                    System.out.println("test 5: Change the mark label to true");

                }
                else
                {mark =false;}
            }

            //PrintBoard(Data);
            //PrintList(list);
            //continue;
        }
        input.close();
        System.out.println("Game Over!!");

    }

    //生成board
    public static int[][] CreateBoard(int wdith, int height)
    {
        int[][] Data = new int[wdith][height];

        for (int i=0; i<wdith; i++)
        {
            for(int j=0; j<height; j++)
            {
                Data[i][j]= (int)(Math.random()*CLASSIC);
            }
        }

        return Data;
    }

    //打印Board
    public static void PrintBoard(int[][] Data)
    {

        System.out.println("*************************************" );

        for (int i=Data.length-1; i>=0; i--)
        {
            for(int j= Data[i].length-1; j>=0; j--)
            {
                System.out.print("("+i+","+j+")"+Data[i][j]+" ");
            }
            System.out.println( );
        }
        System.out.println("*************************************" );
    }


    public static void PrintList(ArrayList<Integer> list)
    {
        System.out.println("print list" );
        System.out.println("*************************************" );
        System.out.println(list);
        System.out.println("*************************************" );


    }


}
