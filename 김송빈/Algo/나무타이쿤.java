package codetree;


import java.util.*;
import java.io.*;

public class 나무타이쿤 {
    static int [][]map;
    static int n,m;
    static int[][]dir={{},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1}};
    static List<int[]>list=new ArrayList<>();
    public static void main(String[] args)throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        
        map=new int[n][n];

        for(int i=0;i<n;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(i==n-1&&(j==0||j==1))list.add(new int[]{i,j});
                else if(i==n-2&&(j==0||j==1))list.add(new int[]{i,j});
            }
        }

        for(int i=0;i<m;i++){
            st=new StringTokenizer(br.readLine());
            int d=Integer.parseInt(st.nextToken());
            int p=Integer.parseInt(st.nextToken());

            cal(d,p);
        }

        int answer=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                answer+=map[i][j];
            }
        }

        System.out.println(answer);
    }

    static void cal(int d,int p){
        List<Integer>listChange=new ArrayList<>();
        int dy=dir[d][0]*p;
        int dx=dir[d][1]*p;

        for(int i=0;i<list.size();i++){
            int y=(dy+list.get(i)[0])%n;
            int x=(dx+list.get(i)[1])%n;
            
            if(y<0)y=n-Math.abs(y)%n;
            if(x<0)x=n-Math.abs(x)%n;

            list.get(i)[0]=y;
            list.get(i)[1]=x;

            map[y][x]+=1;
            
        }

         for(int i=0;i<list.size();i++){
            int cnt=0;
            int y=list.get(i)[0];
            int x=list.get(i)[1];

            for(int j=1;j<=4;j++){
                int dd=j*2;
                int ny=y+dir[dd][0];
                int nx=x+dir[dd][1];
                if(ny<n&&nx<n&&ny>=0&&nx>=0&&map[ny][nx]>=1)cnt++;
            }
            listChange.add(cnt+map[y][x]);
        }

        for(int i=0;i<list.size();i++){
            int y=list.get(i)[0];
            int x=list.get(i)[1];

            map[y][x]=listChange.get(i);
        }


        List<int[]>newList=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                int []x={i,j};
                boolean isExist=false;

                for(int k=0;k<list.size();k++){
                    if(x[0]==list.get(k)[0]&&x[1]==list.get(k)[1]){
                        isExist=true;
                        break;
                    }
                }

                if(!isExist&&map[x[0]][x[1]]>=2){
                    map[x[0]][x[1]]-=2;
                    newList.add(x);
                }
            }
        }
        list.clear();
        list.addAll(newList);
        
    }
}