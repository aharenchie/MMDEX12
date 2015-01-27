package img;

public class HoughTransform {
	
	public static int OBJECT_NUM = 3;
	public static int[] pre_r = new int[OBJECT_NUM];
	public static int[] pre_c_x = new int[OBJECT_NUM];
	public static int[] pre_c_y = new int[OBJECT_NUM];
	 
	public static int[] run(int[] c,int w,int h){
		int XMAX = w;
		int YMAX = h;
		int RMAX = 30;
		
		int[] new_c = new int[w*h];
		
		int[][] diagonal = new int[YMAX][XMAX];
		  //斜線長テーブルを作成
	      for(int y=0;y<YMAX;y++)
	         for(int x=0;x<XMAX;x++)
	            diagonal[y][x]=(short)(Math.sqrt(y*y+x*x)+0.5);
		
		int[][][] counter = new int[YMAX][XMAX][RMAX];//ある円周上にいくつの点があるか	
		int dist_x,dist_y,r;//x座標、y座標ごとの距離と半径	
		int c_xmax,c_ymax,rmax,counter_max;
		c_xmax = c_ymax = rmax = counter_max = 0;
		
		 for(int y=0;y<h;y++){
			 for(int x=0;x<w;x++){
				 //黒のとき
				 if(Formatter.r(c[y*w+x]) == 0){
					 for(int c_y = 0;c_y < YMAX;c_y++){
						 dist_y = Math.abs(y-c_y); //対象のy座標と円中心y座標(仮)の距離
						 
						 if(dist_y > RMAX) continue; //円中心y座標(仮)が半径Rより大きい場合
	            			
						 for(int c_x = 0;c_x < XMAX;c_x++){
							 dist_x = Math.abs(x-c_x); //対象のx座標と円中心x座標(仮)の距離
	            				
							 r = diagonal[dist_y][dist_x];//半径を求める。
	            				
							 if(r >= RMAX) continue;
							 counter[c_y][c_x][r]++;//この円がありえる(インクリメント)
							 //new_c[w*c_y+c_x] = Formatter.rgb(255,0,0);
						 	}	
	            		}		
	            	}
	           	}
		 }
		 
		 boolean flag;
		 
		 for(int i=0;i <OBJECT_NUM;i++){	
			 
	         counter_max=0;   
	         flag = true;
	         
	         for(int c_y = 0;c_y < YMAX;c_y++){
	        	 for(int c_x = 0;c_x < XMAX;c_x++){
	        		 for(r = 0;r < RMAX;r++){
	        			 
	        			if(i == 0 && counter_max < counter[c_y][c_x][r]){
	        				counter_max = counter[c_y][c_x][r];
	        				c_xmax = c_x;
	        				c_ymax = c_y;
	        				rmax = r;		
	        			}else if(counter_max < counter[c_y][c_x][r] ){
	      
	        				for(int j = 0; j < i;j++)
	        					if(Math.abs(pre_c_x[j] - c_x) < 5 && Math.abs(pre_c_y[j] - c_y) < 5 )
	        						flag = false;
	        				
	        				if(flag){
	        				counter_max = counter[c_y][c_x][r];
	        				c_xmax = c_x;
	        				c_ymax = c_y;
	        				rmax = r;       
	        				}
	        			}
	        			
	        		 }					
	        	 }	        		 
	        }
	         
	         pre_c_x[i] = c_xmax;
	         pre_c_y[i] = c_ymax;	         
	         pre_r[i] = rmax;
	         
	        
	         System.out.println("---------------");
	         System.out.println(c_xmax);
	         System.out.println(c_ymax);
	         System.out.println(rmax);
	         System.out.println(counter_max);
	
		 }
		

	
		//new_c[w*c_ymax+c_xmax] = Formatter.rgb(255,0,0);
		
		return new_c;
	}
	
	
}
