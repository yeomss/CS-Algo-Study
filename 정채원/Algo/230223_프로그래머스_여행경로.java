import java.util.*;

class Solution {
    /**
    - Map< 공항, List< 항공권 index >> 인접리스트
    - int[ 항공권 index ] visit
    */
    
    static String[][] tickets;
    static int ticketCount;
    
    static Map< String, List< Integer >> adj; // 인접리스트
    static int[] visit; // dfs용
    
    private int cmp(String[] a, String[] b){
        if(a[0].equals(b[0])){
            return a[1].compareTo(b[1]);
        }
        return a[0].compareTo(b[0]);
    }
    public String[] solution(String[][] tickets) {
        // init
        Arrays.sort(tickets, (a, b) -> cmp(a, b)); // 알파벳 순서 정렬
        this.tickets = tickets;
        ticketCount = tickets.length;
        adj = new HashMap<>();
        visit = new int[ticketCount+1];
        
        // 인접 리스트 생성
        makeAdj(tickets);
        // System.out.println(adj.toString());
        
        // 경로 찾기
        route.addLast("ICN");
        dfs("ICN", 0);
        
        String[] answer = new String[ticketCount+1];
        for(int i=0; i<ticketCount+1; i++){
            answer[i] = route.removeFirst();
        }
        return answer;
    }
    
    public void makeAdj(String[][] tickets){
        for(int i=0; i<tickets.length; i++){
            if(adj.containsKey(tickets[i][0])){
                adj.get(tickets[i][0]).add(i); // list에 추가
            } else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                adj.put(tickets[i][0], list);
            }
        }
    }
    
    /**
    - 시작점 
    
    * 종료 조건 : 모두 방문했는지 (count가 ticketCount와 같은지)
    * 현재 공항에서 연결된 곳 -> visit안한 항공권 있는지 확인 -> 있으면 visit체크하고 dfs
    */
    static Deque<String> route = new ArrayDeque<>();
    public boolean dfs(String curAir, int visitCount){
        if(visitCount == ticketCount){
            return true;
        }
        
        if(!adj.containsKey(curAir)) return false; // 더 갈 수 있는 ticket이 없는 경우 // 이거 없어서 런탐에러ㅜㅠ
        
        for(int nxtTicketIdx : adj.get(curAir)){
            if(visit[nxtTicketIdx] == 1) continue;
            
            String[] ticket = tickets[nxtTicketIdx];
            String destAir = ticket[1];
            // System.out.println(destAir);
            
            visit[nxtTicketIdx] = 1;
            route.addLast(destAir);
            if(dfs(destAir, visitCount + 1)) return true;
            visit[nxtTicketIdx] = 0;
            route.removeLast();
        }
        return false;
    }
}