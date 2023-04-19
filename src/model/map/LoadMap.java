package model.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class LoadMap {
	protected int vertexs;
	protected int[][] matrix;

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

	public static void main(String[] args) {
		LoadMap test = new LoadMap();
		test.loadMatrix("./resources/map/Map_23x23.txt");
		test.printMatrix();
	}
}
