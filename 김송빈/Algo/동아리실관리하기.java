package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 동아리실관리하기 {

	static int [][]dp;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			String str=br.readLine();
			int n=str.length();
			
			dp=new int[n][16];
			
			int key=1<<(str.charAt(0)-'A');
			
			System.out.println(key);
			for(int i=1;i<16;i++) {
				if((i&key)!=0&&(i&1)!=0)dp[0][i]=1;
			}
			
			for(int i=1;i<n;i++) {
				int idx=1<<(str.charAt(i)-'A');
				for(int j=1;j<16;j++) {
					if(dp[i-1][j]==0)continue;
					for(int k=1;k<16;k++) {
						if((k&j)!=0&&(k&idx)!=0) {
							dp[i][k]+=dp[i-1][j];
							dp[i][k]%=1_000_000_007;
						}
					}
				}
				
			}
			
			int sum=0;
			
			for(int i=1;i<16;i++) {
				sum+=dp[n-1][i];
				sum%=1_000_000_007;
			}
			
			System.out.println("#"+t+" "+sum);
		}

	}

}
