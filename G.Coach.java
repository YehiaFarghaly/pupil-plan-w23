import java.util.*;

import java.io.*;
import java.text.ParseException;

public class Main {

	static ArrayList<Integer>[] adjList;
	static boolean[] vis;
	static int n, m;
	static int twoCounter;
	static int oneCounter;
	static int idx;
	static ArrayList<ArrayList<Integer>> teams;

	public static void main(String[] args) throws IOException, InterruptedException, ParseException {
		n = sc.nextInt();
		m = sc.nextInt();

		vis = new boolean[n];
		teams = new ArrayList<>();
		adjList = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adjList[i] = new ArrayList();
		}

		for (int i = 0; i < m; i++) {
			int u = sc.nextInt() - 1;
			int v = sc.nextInt() - 1;
			adjList[u].add(v);
			adjList[v].add(u);
		}

		if (!valid())
			pw.println(-1);
		else {

			for (int i = 0; i < teams.size(); i++) {
				ArrayList<Integer> tmp = teams.get(i);
				if (tmp.size() == 3) {
					pw.println(tmp.get(0) + " " + tmp.get(1) + " " + tmp.get(2));
					teams.remove(i--);
				} else if (tmp.size() == 2) {
					pw.println(tmp.get(0) + " " + tmp.get(1) + " ");
					teams.remove(i--);
					for (int j = 0; j < teams.size(); j++) {
						if (teams.get(j).size() == 1) {
							pw.println(teams.get(j).get(0));
							teams.remove(j);
							i = Math.max(i - 1, -1);
							break;
						}
					}
				}

			}
      // Case of remaining 1 students, put each three together in a team
			for (int i = 0; i < teams.size() - 2; i += 3) {
				pw.println(teams.get(i).get(0) + " " + teams.get(i + 1).get(0) + " " + teams.get(i + 2).get(0));
			}
		}

		pw.flush();
	}

	public static boolean valid() {
		idx = 0;
		for (int i = 0; i < n; i++) {
			if (!vis[i]) {
				teams.add(new ArrayList<>());
				teams.get(idx).add(i + 1);
				dfs(i);
				idx++;
			}
		}

		for (int i = 0; i < teams.size(); i++) {
			int s = teams.get(i).size();
			if (s > 3)
				return false;
			else if (s == 2)
				twoCounter++;
			else if (s == 1)
				oneCounter++;
		}
		if (twoCounter > oneCounter)
			return false;

		return true;
	}

	public static void dfs(int node) {
		vis[node] = true;
		for (int i : adjList[node])
			if (!vis[i]) {
				teams.get(idx).add(i + 1);
				dfs(i);
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
