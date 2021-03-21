import java.util.*;
public class Prims
{
    private static int infinite = 9999999;
    int[][]  LinkCost;
    int      num_nodes; //


    Prims(int[][] mat) {
        int i;
        int j;

        num_nodes = mat.length;
        LinkCost = new int[num_nodes][num_nodes];
        for ( i=0; i < num_nodes; i++) {
            for ( j=0; j < num_nodes; j++) {
                LinkCost[i][j] = mat[i][j];

                if ( LinkCost[i][j] == 0 ){
                    LinkCost[i][j] = infinite;
                }
            }
        }
    }
    public void Prim() {

        int x;
        int y;
        int cost = 0;

        boolean[] Reached = new boolean[num_nodes];
        for ( int k = 0; k < num_nodes; k++ ) {
            Reached[k] = false;
        }
        Reached[0] = true;






        for (int k = 1; k < num_nodes; k++) {
            x = y = 0;

            for (int i = 0; i < num_nodes; i++ )
                for (int  j = 0; j < num_nodes; j++ )
                {

                    if ( Reached[i] && !Reached[j] &&
                            LinkCost[i][j] < LinkCost[x][y] )
                    {
                        x = i;
                        y = j;
                    }
                }

            System.out.println(" Edge: (" +
                    + x + "," +
                    + y + ")" +
                    " cost = " + LinkCost[x][y]);
             cost += LinkCost[x][y];
            Reached[y] = true;



        }

        System.out.print("Subgraph in MST: (");
        for(int t = 0; t<Reached.length ;t++){
            if(t==Reached.length-1 && Reached[t]){
                System.out.print(t);
            }
            if(Reached[t] && t != Reached.length-1){
                System.out.print(t +",");
            }
        }
        System.out.print(")");
        System.out.println( );
        System.out.println("MST cost " +cost);


    }


        public static void main(String[] args) {
            int[][] conn = {{0, 7, 5, 0, 0},
                            {7, 0, 1, 4, 0},
                            {5, 1, 0, 6, 2},
                            {0, 4, 6, 0, 0},
                            {0, 0 ,2, 0, 0},
            };

            Prims q = new Prims(conn);

            q.Prim();
        }

}