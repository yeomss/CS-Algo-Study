import java.util.*;
import java.awt.Point;

class Solution {
    static final int MAX_N = 50*2;
    static int[][] board;
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        /**
        1. board에 rectangle 범위만큼 1로 채운다
            - rectangle 좌표를 2배로 늘려서 입력하는게 핵심 => 내부 경계선 좌표가 바로 이어지는 문제를 방지하기 위해
        2. rectangle의 경계선 부분을 2로 채운다 (경계선인지 확인할 땐 대각선도 확인해야함)
        3. 2만 이동할 수 있는 경로 : cY,cX -> item 으로 가는 최소 경로 구함
        */
        init();
        // board에 rectangle 범위만큼 1로 채움
        for(int i=0; i<rectangle.length; i++){
            fillRec(new Point(rectangle[i][0]<<1,rectangle[i][1]<<1), new Point(rectangle[i][2]<<1, rectangle[i][3]<<1));
        }
        // rectangle 경계선 부분 2로 채움 (start 좌표는 사각형 중 아무거나 준다)
        fillBoundary(new Point(rectangle[0][0]<<1,rectangle[0][1]<<1));
        Point character = new Point(characterX<<1, characterY<<1);
        Point item = new Point(itemX<<1, itemY<<1);
        // 최단경로 찾음
        return findRoad(character, item)/2; // 좌표 2배로 해서 검색했으므로, 경로 이동도 2배가 되었음. /2를 해줘야 함
    }
    public void init(){
        board = new int[MAX_N+2][MAX_N+2];
    }
    public void fillRec(Point lb, Point rt){ // board에 사각형 넓이만큼 채움
        for(int y=lb.y; y<=rt.y; y++){
            for(int x=lb.x; x<=rt.x; x++){
                board[y][x] = 1;
            }
        }
    }
    static int[] px = {-1, 0, 1, 0};
    static int[] py = {0, -1, 0, 1};
    static int[] p8x = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] p8y = {0, -1, 0, 1, -1, 1, -1, 1};
    
    public void fillBoundary(Point start){ // bfs
        Queue<Point> que = new ArrayDeque<>();
        int[][] visit = new int[MAX_N+2][MAX_N+2];
        que.add(start);
        visit[start.y][start.x] = 1;

        while(!que.isEmpty()){
            Point cur = que.remove();
            if(isBoundary(cur)) board[cur.y][cur.x] = 2; // 사각형 경계선이면 2로 채움
            for(int d=0; d<4; d++){
                int nx = cur.x + px[d];
                int ny = cur.y + py[d];
                if(nx < 0 || nx >= MAX_N+2 || ny < 0 || ny >=MAX_N+2 
                   || visit[ny][nx] == 1 || board[ny][nx] != 1) continue; // 이미 방문한 곳이나, 직사각형 내부가 아니면 패스
                que.add(new Point(nx, ny));
                visit[ny][nx] = 1;
            }
        }
    }
    
    private boolean isBoundary(Point p){ // 사각형 경계선인지 확인 : 8방 탐색
        for(int d=0; d<8; d++){
            int nx = p.x + p8x[d];
            int ny = p.y + p8y[d];
            if(nx < 0 || nx >= MAX_N+2 || ny < 0 || ny >= MAX_N+2) continue;
            if(board[ny][nx] == 0) return true;
        }
        return false;
    }
    
    public int findRoad(Point cp, Point ip){ // 최단 거리 찾기
        Queue<Point> que = new ArrayDeque<>();
        int[][] visit = new int[MAX_N+2][MAX_N+2];
        que.add(cp);
        visit[cp.y][cp.x] = 1;
        
        int count = -1;
        while(!que.isEmpty()){
            int lim = que.size();
            count++; // 이동 횟수 카운트
            while(lim-- > 0){
                Point cur = que.remove();
                if(cur.equals(ip)) return count; // 아이템에 도착했으면 return
                for(int d=0; d<4; d++){
                    int nx = cur.x + px[d];
                    int ny = cur.y + py[d];
                    if(nx < 0 || nx >= MAX_N+2 || ny < 0 || ny >= MAX_N+2 
                       || visit[ny][nx] == 1 || board[ny][nx] != 2) continue; // 이동 경로(2)가 아니면 패스
                    que.add(new Point(nx, ny));
                    visit[ny][nx] = 1;
                }
            }
        }
        return -1; // 도착 못하는 경우는 주어지지 않으므로 dummy값
    }
}