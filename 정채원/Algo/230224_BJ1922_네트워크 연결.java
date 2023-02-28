import java.io.*;

import java.util.StringTokenizer;
import java.util.*;

/**
 * 
 * - 인접리스트
 * 
 * 한 시작점 기준, 가장 짧은 가중치를 선택함
 * 해당 연결된 정점이 아직 방문하지 않았으면 -> 방문함, sum에 추가함.
 */
class Edge{
	int w, e;
	public Edge(int w, int e) {
		this.w = w; this.e = e;
	}
}
public class Main {
	static int N, M;
	static List<Edge>[] adjList; // 인접리스트
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		adjList = new ArrayList[N+1];
		for(int i=0; i<N+1; i++) adjList[i] = new ArrayList<>();
		for(int i=0; i<M; i++) {
			int a, b, c;
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			adjList[a].add(new Edge(c, b));
			adjList[b].add(new Edge(c, a));
		}
		
		System.out.println(prim());
	}
	static public int prim() {
		int sum = 0;
		int[] visit = new int[N+1];
		int cnt = 0; // 연결한 정점 수
		
		PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.w-b.w);
		pq.addAll(adjList[1]); // 아무 정점의 간선들을 넣음
		visit[1] = 1;
		
		while(!pq.isEmpty()) {
			Edge e = pq.remove();
			
			if(visit[e.e] == 1) continue;
			
			visit[e.e]= 1; // 연결함
			sum += e.w; // 간선 추가
			cnt++; // 연결함
			if(cnt == N) break;
			
			pq.addAll(adjList[e.e]);
		}
		return sum;
	}
}