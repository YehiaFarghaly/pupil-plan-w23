import java.util.*;

import java.io.*;
import java.text.ParseException;

public class Main {

	static ArrayList<Pair>[] adjList;
	static boolean[] vis;


	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		int n = sc.nextInt();
		int m = sc.nextInt();
		adjList = new ArrayList[n];
		for(int i=0; i<n; i++) adjList[i] = new ArrayList<>();
		for(int i=0; i<m; i++) {
			int a = sc.nextInt()-1;
			int b = sc.nextInt()-1;
			int c = sc.nextInt();
			adjList[a].add(new Pair(b, c));
			adjList[b].add(new Pair(a, c));
		}
		
		int q = sc.nextInt();
		while(q-- > 0) {
			int u = sc.nextInt()-1;
			
			int v = sc.nextInt()-1;
			
			int ctr = 0;
			for(int i=1; i<=m; i++) {
				vis = new boolean[n];
				dfs(v, i);
				if(vis[u]) ctr++;
			}
			pw.println(ctr);
		}
		
		pw.flush();
	}
	
	public static void dfs(int node, int color) {
		vis[node] = true;
		for(Pair p : adjList[node]) {
			if(!vis[p.x] && p.y == color) dfs(p.x, color);
		}
	}

	static class Pair implements Comparable<Pair> {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Pair o) {
			return x == o.x && y == o.y;
		}

		@Override
		public int compareTo(Main.Pair o) {
			// TODO Auto-generated method stub
			return o.y - y;
		}

		public String toString() {

			return "(" + x + "," + y + ")";
		}

	}

	static Scanner sc = new Scanner(System.in);
	static PrintWriter pw = new PrintWriter(System.out);

	static class Scanner {
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s) {
			br = new BufferedReader(new InputStreamReader(s));
		}

		public Scanner(FileReader r) {
			br = new BufferedReader(r);
		}

		public String next() throws IOException {
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {
			return Integer.parseInt(next());
		}

		public long nextLong() throws IOException {
			return Long.parseLong(next());
		}

		public String nextLine() throws IOException {
			return br.readLine();
		}

		public double nextDouble() throws IOException {
			String x = next();
			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if (x.charAt(0) == '-') {
				neg = true;
				start++;
			}
			for (int i = start; i < x.length(); i++)
				if (x.charAt(i) == '.') {
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				} else {
					sb.append(x.charAt(i));
					if (dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg ? -1 : 1);
		}

		public long[] nextlongArray(int n) throws IOException {
			long[] a = new long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}

		public Long[] nextLongArray(int n) throws IOException {
			Long[] a = new Long[n];
			for (int i = 0; i < n; i++)
				a[i] = nextLong();
			return a;
		}

		public int[] nextIntArray(int n) throws IOException {
			int[] a = new int[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}

		public Integer[] nextIntegerArray(int n) throws IOException {
			Integer[] a = new Integer[n];
			for (int i = 0; i < n; i++)
				a[i] = nextInt();
			return a;
		}

		public boolean ready() throws IOException {
			return br.ready();
		}

	}

	public static void display(char[][] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[i].length; j++) {
				System.out.print(a[i][j]);
			}
			System.out.println();

		}
	}
}
