import java.util.*;
import java.io.*;
import java.awt.Point;

class Solution
{
	static int N; // board의 한 변의 길이
	static List<Point> cores; // 코어의 좌표 저장
	static int minDist; // 전선의 길이 합 : min이 되어야 함
	static int connectedCore; // 연결 가능한 코어의 수 : max가 되어야 함
	
	public static void main(String args[]) throws Exception
	{
//		System.setIn(new FileInputStream("src/sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T;
		T=Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			// 초기화 : 매 테케마다 초기화 해줘야 함 주의
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int[][] board = new int[N][N]; 
			for(int n=0; n<N; n++) { // board값 입력 받기
				st = new StringTokenizer(br.readLine());
				for(int k=0; k<N; k++) {
					board[n][k] = Integer.parseInt(st.nextToken());
				}
			}
			cores = new ArrayList<>();
			minDist = 100_000_000; // 대충 max값 잡음
			connectedCore = 0;
			
			// 구현
			/**
			 * 1. core 위치를 파악 -> 위치 리스트에 저장해둔다
			 * 2-1. 벽에 붙어있는 core들은 무조건 벽쪽 -> core 위치 리스트에서 제거한다
			 * 2-2. 벽에 안붙어있는 core들은 '4방향 + 연결x'하는 선택지 모두 탐색함 (DFS)
			 * 		- 진행방향에 다른 core가 있거나 다른 전선이 있으면 -> 진행x
			 * 		- 비어있으면 -> 다음 core 검사 (재귀 호출)
			 * 3. 모두 검사한 후, min_dist 갱신
			 * */
			saveCores(board); // 1번 수행 && 2-1번 동시에 수행
			connectCores(board, 0, 0, 0); // 2-2번 수행, DFS 기저 조건으로 3번 동시에 수행

			System.out.println("#" + test_case + " " + minDist);
		}
	}
	
	/** core의 위치를 리스트에 저장함 (단, 벽에 붙어있는 core는 무조건 전선 길이 0이므로 제외함 */
	static void saveCores(int[][] board) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(board[i][j] == 0) continue;
				if(checkNearbyWall(j, i)) continue; // 벽에 붙어있는 것
				cores.add(new Point(j, i)); // Point(x, y)
			}
		}
	}
	
	static int[] px = {-1, 0, 1, 0};
	static int[] py = {0, -1, 0, 1};
	
	/** 4방향+연결 안할 때 전선이 있는지 검사하고, 연결가능하면 연결함. 모든 core를 검사하면 최소 전선 길이를 저장*/
	static void connectCores(int[][] board, int curIdx, int accumDist, int connectedCores) {
		// 기저 조건
		if(curIdx == cores.size()) {
			// 전선 연결한 core수가 최대 연결코어수와 같을 때 -> 전선 연결 길이가 최소이면 갱신
			if(connectedCores == Solution.connectedCore && minDist > accumDist) {
				Solution.connectedCore = connectedCores;
				minDist = accumDist;
			}
			// 전선 연결한 core수가 최대 연결코어수보다 클 때 -> 무조건 전선 연결 길이 갱신
			if(connectedCores > Solution.connectedCore){	
				Solution.connectedCore = connectedCores;
				minDist = accumDist;
			}
			return;
		}
		Point point = cores.get(curIdx);
		// 4방향 전선이 있는지 없는지 검사 -> 진행할 수 있으면 다음 idx로 진행
		for(int d=0; d<4; d++) {
			int[][] tmpBoard = new int[N][N];
			copyArr(board, tmpBoard);
			
			int dist = canConnect(tmpBoard, point, d);
			if(dist >= 0) {
				connectCores(tmpBoard, curIdx+1, accumDist + dist, connectedCores + 1);
			}
		}
		// 현재 core를 연결하지 않고 다음 idx 진행
		connectCores(board, curIdx+1, accumDist, connectedCores);
	}
	
	/** core를 전선으로 연결할 수 있는지 확인함.
	 * core를 연결할 수 있으면 : 전선길이를 return, 
	 * core를 연결할 수 없으면: -1 return */
	static int canConnect(int[][] board, Point point, int dir) { 
		int x = point.x, y = point.y;
		int count = 0;
		while(true) {
			x += px[dir];
			y += py[dir];
			if(isOOB(x, y)) break;
			if(board[y][x] > 0) return -1;
			board[y][x] = 2;
			count++;
		}
		return count;
	}

	/** DFS 내부에서 board 배열을 백업할 때 사용 */
	static void copyArr(int[][] originArr, int[][] newArr) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				newArr[i][j] = originArr[i][j];
			}
		}
	}
	
	/** core가 벽에 붙어있는 코어인지 검사 */
	static boolean checkNearbyWall(int x, int y) {
		if(x == 0 || x == N-1 || y == 0 || y == N-1) return true;
		return false;
	}
	
	/** is out of boundary 체크 */
	static boolean isOOB(int x, int y) { 
		return x < 0 || x >= N || y < 0 || y >= N;
	}
}