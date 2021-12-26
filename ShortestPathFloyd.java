package test7;

import java.util.Scanner;

public class ShortestPathFloyd {
	 
    /** 邻接矩阵 */
    private int[][] matrix;
    /** 表示正无穷 */
    private int MAX_WEIGHT = Integer.MAX_VALUE;
    /**路径矩阵*/
    private int[][] pathMatirx;
    /**前驱表*/
    private int[][] preTable;
 
    /**
     * 创建图
     */
    public void createGraph(int index) {
        matrix = new int[index][index];
        //传入无向网边值
        int[] A = {0,210,370,MAX_WEIGHT,190,MAX_WEIGHT,MAX_WEIGHT,420};
        int[] B = { 210,0,210,60,480,50,MAX_WEIGHT,250};
        int[] C = {370,210,0,MAX_WEIGHT,320,MAX_WEIGHT,MAX_WEIGHT,210};
        int[] D = {MAX_WEIGHT,60,MAX_WEIGHT,0,MAX_WEIGHT,50,MAX_WEIGHT,MAX_WEIGHT};
        int[] E = { 190,480,320,MAX_WEIGHT,0,MAX_WEIGHT,MAX_WEIGHT,150};
        int[] F = { MAX_WEIGHT,50,MAX_WEIGHT,50,MAX_WEIGHT,0,100,MAX_WEIGHT};
        int[] G = { MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,100,0,60};
        int[] H = { 420,250,210,MAX_WEIGHT,150,MAX_WEIGHT,60,0 };
        //传入顶点值
        matrix[0] = A;
        matrix[1] = B;
        matrix[2] = C;
        matrix[3] = D;
        matrix[4] = E;
        matrix[5] = F;
        matrix[6] = G;
        matrix[7] = H;
 
    }
 
    
    
    
    public void floyd(){
        //路径矩阵（D），表示顶点到顶点的最短路径权值之和的矩阵，初始时，就是图的邻接矩阵。
        pathMatirx = new int[matrix.length][matrix.length];
        //前驱表（P），P[u][n] 的值为 u到n的最短路径的前驱顶点，如果是直连，值为n。也就是初始值
        preTable = new int[matrix.length][matrix.length];
        
        //初始化D,P
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                pathMatirx[i][j] = matrix[i][j];
                preTable[i][j] = j;
            }
        }
        
        //循环 中间经过顶点
        for (int k = 0; k < matrix.length; k++) {
            //循环所有路径
            for (int m = 0; m < matrix.length; m++) {
                
                for (int n = 0; n < matrix.length; n++) {
                    
                    int mn = pathMatirx[m][n];
                    int mk = pathMatirx[m][k];
                    int kn = pathMatirx[k][n];
                    int addedPath = (mk == MAX_WEIGHT || kn == MAX_WEIGHT)? MAX_WEIGHT : mk + kn;
                    
                    if (mn > addedPath) {
                        //如果经过k顶点路径比原两点路径更短，将两点间权值设为更小的一个
                        pathMatirx[m][n] = addedPath;
                        //前驱设置为经过下标为k的顶点
                        preTable[m][n] = preTable[m][k];
                    }
                    
                }
            }
        }
    }
    
    /**
     * 打印 所有最短路径
     */
    public void print() {
        
//        for (int m = 0; m < matrix.length; m++) {
    	int u=0;//u为索要查找地点索引值
    	while(u<8) {
    		System.out.println("欢迎您使用园区导览最短路径查询系统:");
			System.out.println("0、宿舍楼3");
			System.out.println("1、宿舍楼2");
			System.out.println("2、宿舍楼1");
			System.out.println("3、运动广场");
			System.out.println("4、食堂");
			System.out.println("5、篮球场");
			System.out.println("6、凉亭");
			System.out.println("7、休闲广场");
			System.out.println("8、退出系统");
			 System.out.println("请选择输入要查询地点到各个建筑物的距离和路径：");
    		Scanner sc=new Scanner(System.in);
    		 u=sc.nextInt();
    		 //判断输入值是否<8,否则退出系统
    		 if(u<8) {
    			 u=0; 
    		 }
            for (int n = u + 1; n < matrix.length; n++) {
                
                int k = preTable[u][n];
                switch (n) {
                case 0:System.out.print("你选择地点到宿舍楼3距离:"+pathMatirx[u][n]+"m"+"       路径为"+":  ");break;
                case 1:System.out.print("你选择地点到宿舍楼2距离:"+pathMatirx[u][n]+"m"+"       路径为"+":  ");break;
                case 2:System.out.print("你选择地点到宿舍楼1距离:"+pathMatirx[u][n]+"m"+"       路径为"+":  ");break;
                case 3:System.out.print("你选择地点到运动广场距离:"+pathMatirx[u][n]+"m"+"      路径为"+":  ");break;
                case 4:System.out.print("你选择地点到食堂距离:"+pathMatirx[u][n]+"m"+"          路径为"+":  ");break;
                case 5:System.out.print("你选择地点到篮球场距离:"+pathMatirx[u][n]+"m"+"        路径为"+":  ");break;
                case 6:System.out.print("你选择地点到凉亭距离:"+pathMatirx[u][n]+"m"+"          路径为"+":  ");break;
                case 7:System.out.print("你选择地点到休闲广场距离:"+pathMatirx[u][n]+"m"+"      路径为"+":  ");break;
                }
                switch (u) {
                case 0:System.out.print("宿舍楼3");break;
                case 1:System.out.print("宿舍楼2");break;
                case 2:System.out.print("宿舍楼1");break;
                case 3:System.out.print("运动广场");break;
                case 4:System.out.print("食堂");break;
                case 5:System.out.print("篮球场");break;
                case 6:System.out.print("凉亭");break;
                case 7:System.out.print("休闲广场");break;
                } 
                while (k != n) {
                switch (k) {
                case 0:System.out.print("->"+"宿舍楼3");break;
                case 1:System.out.print("->"+"宿舍楼2");break;
                case 2:System.out.print("->"+"宿舍楼1");break;
                case 3:System.out.print("->"+"运动广场");break;
                case 4:System.out.print("->"+"食堂");break;
                case 5:System.out.print("->"+"篮球场");break;
                case 6:System.out.print("->"+"凉亭");break;
                case 7:System.out.print("->"+"休闲广场");break;
                }
                k = preTable[k][n];
                
                }
                switch (n) {
                case 0:System.out.println("->"+"宿舍楼3");break;
                case 1:System.out.println("->"+"宿舍楼2");break;
                case 2:System.out.println("->"+"宿舍楼1");break;
                case 3:System.out.println("->"+"运动广场");break;
                case 4:System.out.println("->"+"食堂");break;
                case 5:System.out.println("->"+"篮球场");break;
                case 6:System.out.println("->"+"凉亭");break;
                case 7:System.out.println("->"+"休闲广场");break;
                } 
            }
            
            System.out.println("............................................");
        }
    	System.out.println("退出成功");
        
    }
    
}