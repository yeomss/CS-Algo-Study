import java.io.*;
import java.util.*;

public class Main {
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		// 대각선, 가로, 세로 둘 수 없음
		// 재귀하면서 y좌표 하나씩 늘려감
		// x좌표 방문 안한 곳을 체크
		// 방문 안한 x좌표가 대각선이 아닌지 확인
		int[] xvisit = new int[N];
		Arrays.fill(xvisit, -1);
		
		int sum = 0;
		for(int i=0; i<N; i++) {
			xvisit[i] = 0; // y좌표 저장
			sum += nqueen(xvisit, i, 0); // x좌표, y좌표
			xvisit[i] = -1;
		}
		System.out.println(sum);
	}
	static int nqueen(int[] xvisit, int x, int y) { // x, y좌표에 퀸을 뒀음
		if(y == N-1) return 1;
		// 다음에 퀸을 둘 곳을 찾음
		// 1. 지금까지 둔 퀸과 같은 x를 띄면 안됨
		// 2. 지금까지 둔 퀸과 같은 대각선에 있으면 안됨
		int sum = 0;
		for(int nx=0; nx<N; nx++) {
			if(xvisit[nx] > -1) continue; // 1
			if(isDiag(xvisit, nx, y+1)) continue; // 2
			xvisit[nx] = y+1;
			sum += nqueen(xvisit, nx, y+1);
			xvisit[nx] = -1;
		}
		return sum;
	}
	static boolean isDiag(int[] xvisit, int x, int y) {
		for(int ax=0; ax<N; ax++) {
			if(xvisit[ax] == -1) continue; // 아직 할당되지 않은
			if(ax == x) continue;
			if(Math.abs(ax-x) == Math.abs(xvisit[ax]-y)) return true; // 대각선
		}
		return false;
	}
}