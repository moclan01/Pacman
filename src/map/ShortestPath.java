package map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class ShortestPath {
	private int vertexs;
	private int[][] matrix;
	private String pathFile;
	
	public ShortestPath(String pathFile) {
		this.pathFile = pathFile;
		loadMatrix(pathFile);
	}

	public void loadMatrix(String pathFile) {
		File file = new File(pathFile);
		try {
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			this.vertexs = Integer.valueOf(br.readLine());
			this.matrix = new int[this.vertexs][this.vertexs];
			int row = 0;
			String line = "";
			while ((line = br.readLine()) != null) {
				String[] s = line.split(" ");
				for (int i = 0; i < s.length; i++) {
					this.matrix[row][i] = Integer.valueOf(s[i]);
				}
				row++;

			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.getStackTrace();
		}

	}

	public void printMatrix() {
		System.out.print("    ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.print(i + "  ");
		}
		System.out.println();
		System.out.print("    ");
		for (int i = 0; i < matrix.length; i++) {
			System.out.print("-" + "  ");
		}
		System.out.println();
		for (int i = 0; i < matrix.length; i++) {
			System.out.print(i + "-" + "  ");
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + "  ");
			}
			System.out.println("\n");
		}
	}

	public int[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	public boolean ktLienThong(int v1, int v2) {
		// kiem tra v1 va v2 co cung 1 thanh phan lien thong hay khong
		boolean result = false;

		for (int i = 0; i < DFSStack(v1).length; i++) {
			if (DFSStack(v1)[i] == v2) {
				result = true;
			}
		}
		return result;
	}

	public int[] DFSStack(int v) {
		Stack<Integer> st = new Stack<>();
		boolean[] visited = new boolean[vertexs];
		int[] result = new int[vertexs];
		int pos = 0;
		st.push(v);
		while (!st.isEmpty()) {
			int x = st.pop();
			if (visited[x] != true) {
				visited[x] = true;
				result[pos] = x;
				pos++;
			}
			for (int i = matrix.length - 1; i >= 0; i--) {
				if (matrix[x][i] != 0 && visited[i] != true) {
					st.push(i);
				}
			}
		}
		return result;
	}

	public List<Integer> dinhKe(int v) {
		// tim danh sach cac dinh ke cua dinh v
		ArrayList<Integer> tmp = new ArrayList<>();
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[v][i] != 0) {
				tmp.add(i);
			}
		}
		return tmp;
	}

	public List<Integer> duongDiNganNhat(int v1, int v2) {
		// lam theo nguyen ly cua BFS duyet theo chieu rong

		int[] dinhBo = new int[vertexs];
		// luu lai dinh bo cua cac dinh

		boolean[] duyetDinh = new boolean[vertexs];
		// duyetDinh = visited
		ArrayList<Integer> result = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> tmp2 = new ArrayList<>();

		q.offer(v1);
		int x = q.poll();
		boolean check = true;
		// dieu kien dung cua While(true)
		if (!ktLienThong(v1, v2)) {
			throw new RuntimeException("Khong co duong di giua 2 dinh " + v1 + " va " + v2);
		} else {
			while (check) {
				for (Integer i : dinhKe(x)) {
					if (duyetDinh[i] != true) {
						duyetDinh[i] = true;
						q.offer(i);
						dinhBo[i] = x;
						if (i == v2) {
							check = false;
						}
					}
				}
				x = q.poll();
			}

		}
		int tmp = v2;
		tmp2.add(v2);
		while (tmp != v1) {
			tmp2.add(dinhBo[tmp]);
			tmp = dinhBo[tmp];
		}
		// truy xuat lai duong di tu v2 -> v1 thong qua dinh bo
		for (int j = tmp2.size() - 1; j >= 0; j--) {
			result.add(tmp2.get(j));
		}
		// dao nguoc danh sach lai v1 -> v2
		return result;
	}

	public List<Integer> dinhKe2(int v) {
		// tim danh sach cac dinh ke cua dinh v
		ArrayList<Integer> tmp = new ArrayList<>();
		for (int i = matrix.length - 1; i >= 0; i--) {
			if (matrix[v][i] != 0) {
				tmp.add(i);
			}
		}
		return tmp;
	}

	public List<Integer> duongDiNganNhat2(int v1, int v2) {
		// lam theo nguyen ly cua BFS

		int[] dinhBo = new int[vertexs];
		// luu lai dinh bo cua cac dinh

		boolean[] duyetDinh = new boolean[vertexs];
		// duyetDinh = visited
		ArrayList<Integer> result = new ArrayList<>();
		Queue<Integer> q = new LinkedList<>();
		ArrayList<Integer> tmp2 = new ArrayList<>();

		q.offer(v1);
		int x = q.poll();
		boolean check = true;
		// dieu kien dung cua While(true)
		if (!ktLienThong(v1, v2)) {
			throw new RuntimeException("Khong co duong di giua 2 dinh " + v1 + " va " + v2);
		} else {
			while (check) {
				for (Integer i : dinhKe2(x)) {
					if (duyetDinh[i] != true) {
						duyetDinh[i] = true;
						q.offer(i);
						dinhBo[i] = x;
						if (i == v2) {
							check = false;
						}
					}
				}
				x = q.poll();
			}

		}
		int tmp = v2;
		tmp2.add(v2);
		while (tmp != v1) {
			tmp2.add(dinhBo[tmp]);
			tmp = dinhBo[tmp];
		}
		// truy xuat lai duong di tu v2 -> v1 thong qua dinh bo
		for (int j = tmp2.size() - 1; j >= 0; j--) {
			result.add(tmp2.get(j));
		}
		// dao nguoc danh sach lai v1 -> v2
		return result;
	}

	public void printList(List<Integer> list) {
		for (Integer i : list) {
			System.out.print(i + " ");

		}
	}

}
