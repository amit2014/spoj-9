package Classical;

import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.BufferedReader;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Collections;

/**
 * TLE :'(
 * 
 * @author sagarjauhari@gmail.com
 * 
 */
class PT07Z3 {
	String PROB_NAME = "";
	String TEST_NAME = "input00.txt";
	InputScanner i_scan;
	OutputWriter o_writ;
	Boolean is_debug = false;
	long start_time = 0;
	long end_time = 0;

	public static void main(String[] args) {
		PT07Z3 program = new PT07Z3(args);
		program.begin();
		program.end();
	}

	public void begin() {
		int T = i_scan.nextInt();
		HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
		for (int cases = 0; cases < T - 1; cases++) {
			int p = i_scan.nextInt();
			int q = i_scan.nextInt();
			if (!graph.containsKey(p)) {
				graph.put(p, new ArrayList<Integer>());
			}
			graph.get(p).add(q);
		}// graph ready

		/* Assuming the 1st node is the root of the tree */
		int root = 0;
		for (int key : graph.keySet()) {
			root = key;
			break;
		}

		o_writ.printLine(getDiameterRecur(graph, root, 0) -1 );
	}

	int getDiameterRecur(HashMap<Integer, ArrayList<Integer>> graph, int root,
			int parent) {
		int diameter = 0;

		/*
		 * For each subtree, calculate the height as well as the diameter. Keep
		 * track of the largest sub-tree diameter and the 2 largest heights
		 */
		int maxSubTreeDiam = 0;
		int maxSubTreeHeight1 = 0;
		int maxSubTreeHeight2 = 0; // < maxSubTreeHeight1

		ArrayList<Integer> adjNodes = getAdjNodes(root, graph, parent);

		for (int node : adjNodes) {
			/* Get subtree diameter */
			int subTreeDiam = getDiameterRecur(graph, node, root);
			if (subTreeDiam > maxSubTreeDiam) {
				maxSubTreeDiam = subTreeDiam;
			}

			/* Get subtree height */
			int subTreeHeight = getHeightRecur(graph, node, root);

			/* Update the 2 largest heights obtained so far */
			if (subTreeHeight > maxSubTreeHeight1) {
				maxSubTreeHeight2 = maxSubTreeHeight1;
				maxSubTreeHeight1 = subTreeHeight;
			} else if (subTreeHeight > maxSubTreeHeight2) {
				maxSubTreeHeight2 = subTreeHeight;
			}
		}
		
		
		/* Now compare the sub tree diameter as well as the heights */
		diameter = Math.max(maxSubTreeDiam, maxSubTreeHeight1 + maxSubTreeHeight2 + 1);

		return diameter;
	}

	private int getHeightRecur(HashMap<Integer, ArrayList<Integer>> graph,
			int root, int parent) {
		int height = 0;
		ArrayList<Integer> adjNodes = getAdjNodes(root, graph, parent);
		for (int node : adjNodes) {
			height = Math.max(height, getHeightRecur(graph, node, root));
		}
		return height + 1;
	}

	ArrayList<Integer> getAdjNodes(int node,
			HashMap<Integer, ArrayList<Integer>> graph, int parent) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (graph.containsKey(node)) {
			list.addAll(graph.get(node));
		}
		for (int key : graph.keySet()) {
			if (graph.get(key).contains(node)) {
				list.add(key);
			}
		}
		
		if (list.contains(parent)) {
			list.remove(list.indexOf(parent));
		}
		return list;
	}

	public PT07Z3(String[] args) {
		if (args.length > 0 && args[0].equals("Test")) {
			i_scan = new InputScanner(new File(
					"/home/sagar/code_arena/Eclipse Workspace/InterviewStr/inputs/"
							+ PROB_NAME + "/" + TEST_NAME));
		} else {
			i_scan = new InputScanner(System.in);
		}
		o_writ = new OutputWriter(System.out);
		if (is_debug) {
			start_time = System.currentTimeMillis();
		}
	}

	public void end() {
		if (is_debug) {
			end_time = System.currentTimeMillis();
			o_writ.printLine("Time (milisec): " + (end_time - start_time));
		}
		o_writ.close();
	}

	class InputScanner {
		private final BufferedReader br;
		StringTokenizer tokenizer;

		public InputScanner(InputStream stream) {
			br = new BufferedReader(new InputStreamReader(stream));
		}

		public InputScanner(File file) {
			try {
				br = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			int num;
			try {
				num = Integer.parseInt(next());
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			}
			return num;
		}

		public float nextFloat() {
			float num;
			try {
				num = Float.parseFloat(next());
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			}
			return num;
		}

		public float nextLong() {
			long num;
			try {
				num = Long.parseLong(next());
			} catch (NumberFormatException e) {
				throw new RuntimeException(e);
			}
			return num;
		}

		public String nextLine() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken("\n").trim();
		}

	}

	class OutputWriter {
		private final PrintWriter pw;

		public OutputWriter(OutputStream op) {
			pw = new PrintWriter(op);
		}

		public OutputWriter(Writer writer) {
			pw = new PrintWriter(writer);
		}

		public void print(Object... objects) {
			for (int i = 0; i < objects.length; i++) {
				if (i != 0)
					pw.print(' ');
				pw.print(objects[i]);
			}
		}

		public void printLine(Object... objects) {
			print(objects);
			pw.println();
		}

		public void close() {
			pw.close();
		}
	}
}
