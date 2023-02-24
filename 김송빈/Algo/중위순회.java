package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;

public class 중위순회 {

	static int n;
	static String []val;
	static boolean []visited;
	static List<Integer>order;
	
	
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1;t<=10;t++) {
			n=Integer.parseInt(br.readLine());

			val=new String[n+1];
			for(int i=1;i<=n;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				st.nextToken();
				val[i]=st.nextToken();
				
			}
			
			System.out.print("#"+t+" ");
			dfs(1);
			System.out.println();
		}
		
	}
	
	static void dfs(int cnt) {
		if(cnt>n) return;
		dfs(2*cnt);
		System.out.print(val[cnt]);
		dfs(2*cnt+1);
	}
}
