package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 공통조상 {

	static class Node{
		int leftc,rightc,parent;
		Node(){
			this.leftc=0;
			this.rightc=0;
			this.parent=0;
			
		}
	}
	
	
	static boolean []visited;
	static Node []node;
	static int size;
	public static void main(String[] args)throws Exception {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
		int tc=Integer.parseInt(br.readLine());
		
		for(int t=1;t<=tc;t++) {
			StringTokenizer st=new StringTokenizer(br.readLine()); 
			int v=Integer.parseInt(st.nextToken());
			int e=Integer.parseInt(st.nextToken());
			int n1=Integer.parseInt(st.nextToken());
			int n2=Integer.parseInt(st.nextToken());
			
			visited=new boolean[v+1];
			node=new Node[v+1];
			
			for(int i=0;i<=v;i++) {
				node[i]=new Node();
			}
			
			st=new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				int parent=Integer.parseInt(st.nextToken());
				int child=Integer.parseInt(st.nextToken());
				
				if(node[parent].leftc==0) {
					node[parent].leftc=child;
				}else node[parent].rightc=child;
				node[child].parent=parent;
			}
			
			int answer=1;
			while(true) {
				if(n1!=1) {
					if(visited[node[n1].parent]) {
						answer=node[n1].parent;
						break;
					}
					visited[node[n1].parent]=true;
					n1=node[n1].parent;
					
				}
				
	
				if(n2!=1) {
					if(visited[node[n2].parent]) {
						answer=node[n2].parent;
						break;
					}
					
					visited[node[n2].parent]=true;
					n2=node[n2].parent;
					
				}
			}
			size=0;
			get(node[answer]);
			System.out.println("#"+t+" "+answer+" "+size);
		}

	}
	static void get(Node nd) {
		size++;
		if(nd.leftc!=0)get(node[nd.leftc]);
		if(nd.rightc!=0)get(node[nd.rightc]);
	}

}
