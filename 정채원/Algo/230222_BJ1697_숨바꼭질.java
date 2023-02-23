// https://www.acmicpc.net/problem/1697
import java.util.*;
import java.io.*;


public class Main {
	static int N;
	static int K;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		/**
		 * -1 => 0이하이면 안됨
		 * +1 => K초과이면 안됨
		 * *2 => 2*K이상이면 안됨 (-1로 이동해도 K번이동해야 함. => 그냥 +1로 이동하는게 낫다.)
		 * 
		 * queue에 현재 위치로부터 -1, +1, *2만큼 이동한 걸 넣어줌
		 * 현재 위치에 도착하면 끝
		 */
		System.out.println(bfs());
		
	}
	static int bfs() {
		Queue<Integer> que = new ArrayDeque<>();
		que.add(N);
		int count = -1; // 이동한 시간(초)
		int[] visit = new int[200_001]; // 이미 방문한 곳은 다시 queue에 넣지 않음
		while(!que.isEmpty()) {
			int lim = que.size();
			count ++;
			while(lim-- > 0) {
				Integer cur = que.remove();
				if(cur == K) {
					return count;
				}
				if(cur-1 >= 0 && visit[cur-1] == 0) {visit[cur-1] = 1; que.add(cur-1);}
				if(cur+1 <= K && visit[cur+1] == 0) {visit[cur+1] = 1; que.add(cur+1);}
				if(cur*2 < K*2 && visit[cur<<1] == 0) {visit[cur<<1] = 1; que.add(cur*2);}
			}
		}
		return -1;
	}
}
