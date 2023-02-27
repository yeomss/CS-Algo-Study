package swea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class 암호문3 {

	static int n,m;
	static List<Integer>list;
	public static void main(String[] args)throws Exception {
		System.setIn(new FileInputStream("res/swea_암호문_input.txt"));
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		for(int t=1;t<=10;t++) {
			
			n=Integer.parseInt(br.readLine());
			
			StringTokenizer st=new StringTokenizer(br.readLine());
			
			list=new ArrayList<>();
		
			while(st.hasMoreTokens()) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			m=Integer.parseInt(br.readLine());
			
			st=new StringTokenizer(br.readLine());
			
			while(st.hasMoreTokens()) {
				char cmd=st.nextToken().charAt(0);
				int idx,num;
				
				switch(cmd) {
				
				case 'I':
					idx=Integer.parseInt(st.nextToken());
					num=Integer.parseInt(st.nextToken());
					
					for(int i=idx;i<idx+num;i++) {
						list.add(i,Integer.parseInt(st.nextToken()));
					}
					break;
					
				case 'D':
					idx=Integer.parseInt(st.nextToken());
					num=Integer.parseInt(st.nextToken());
					
					int cnt=0;
					while(cnt<num) {
						cnt++;
						list.remove(idx-1);
					}
						
					break;
					
				case 'A':
					num=Integer.parseInt(st.nextToken());
					for(int i=0;i<num;i++) {
						list.add(Integer.parseInt(st.nextToken()));
					}
					break;
				}
				
			}
		
			System.out.print("#"+t+" ");
			for(int i=0;i<10;i++)System.out.print(list.get(i)+" ");
			System.out.println();
		}
	}

}
