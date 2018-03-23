package com.mindle.cube;

public class Cube {
	public Node[][][] nodes;
	private static final String XOUT = "A";
	private static final String XIN = "B";
	private static final String YOUT = "C";
	private static final String YIN = "D";
	private static final String ZOUT = "E";
	private static final String ZIN = "F";

	class Node {
		String x = null;
		String y = null;
		String z = null;
	}

	public Cube() {
		nodes = new Node[3][3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				for (int k = 0; k < 3; k++)
					nodes[i][j][k] = new Node();

		int count = 1;
		for (int j = 0; j < 3; j++)
			for (int k = 0; k < 3; k++) {
				nodes[0][j][k].x = XIN + count;
				nodes[2][j][k].x = XOUT + count;
				count++;
			}

		count = 1;
		for (int i = 0; i < 3; i++)
			for (int k = 0; k < 3; k++) {
				nodes[i][0][k].y = YIN + count;
				nodes[i][2][k].y = YOUT + count;
				count++;
			}

		count = 1;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				nodes[i][j][0].z = ZIN + count;
				nodes[i][j][2].z = ZOUT + count;
				count++;
			}

	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			sb.append("         | ");
			for (int j = 0; j < 3; j++) {
				sb.append(nodes[i][j][2].z).append(" "); // plane 5
			}
			sb.append("|\r\n");
		}
		sb.append("         |          |\r\n");
		for (int z = 2; z >= 0; z--) {
			// plane 4
			sb.append(nodes[0][0][z].y).append(" ");
			sb.append(nodes[1][0][z].y).append(" ");
			sb.append(nodes[2][0][z].y).append(" ");
			sb.append("| ");

			// plane 1
			sb.append(nodes[2][0][z].x).append(" ");
			sb.append(nodes[2][1][z].x).append(" ");
			sb.append(nodes[2][2][z].x).append(" ");
			sb.append("| ");

			// plane 3
			sb.append(nodes[2][2][z].y).append(" ");
			sb.append(nodes[1][2][z].y).append(" ");
			sb.append(nodes[0][2][z].y).append(" ");
			sb.append("| ");

			// plane 2
			sb.append(nodes[0][2][z].x).append(" ");
			sb.append(nodes[0][1][z].x).append(" ");
			sb.append(nodes[0][0][z].x).append(" ");

			sb.append("\r\n");

		}

		sb.append("         |          |\r\n");
		for (int i = 2; i >= 0; i--) {
			sb.append("         | ");
			for (int j = 0; j < 3; j++) {
				sb.append(nodes[i][j][0].z).append(" "); // plane 6
			}
			sb.append("|\r\n");
		}
		return sb.toString();
	}

	public void rotateX(int layor, boolean clockwise) {
		String tmpA = nodes[layor][0][2].z;
		String tmpB = nodes[layor][1][2].z;
		String tmpC = nodes[layor][2][2].z;
		if (clockwise) {
			nodes[layor][0][2].z = nodes[layor][0][0].y;
			nodes[layor][1][2].z = nodes[layor][0][1].y;
			nodes[layor][2][2].z = nodes[layor][0][2].y;

			nodes[layor][0][0].y = nodes[layor][2][0].z;
			nodes[layor][0][1].y = nodes[layor][1][0].z;
			nodes[layor][0][2].y = nodes[layor][0][0].z;

			nodes[layor][2][0].z = nodes[layor][2][2].y;
			nodes[layor][1][0].z = nodes[layor][2][1].y;
			nodes[layor][0][0].z = nodes[layor][2][0].y;

			nodes[layor][2][2].y = tmpA;
			nodes[layor][2][1].y = tmpB;
			nodes[layor][2][0].y = tmpC;

			if (layor != 1) {
				String tmpD = nodes[layor][0][2].x;
				String tmpF = nodes[layor][1][2].x;
				nodes[layor][0][2].x = nodes[layor][0][0].x;
				nodes[layor][0][0].x = nodes[layor][2][0].x;
				nodes[layor][2][0].x = nodes[layor][2][2].x;
				nodes[layor][2][2].x = tmpD;
				nodes[layor][1][2].x = nodes[layor][0][1].x;
				nodes[layor][0][1].x = nodes[layor][1][0].x;
				nodes[layor][1][0].x = nodes[layor][2][1].x;
				nodes[layor][2][1].x = tmpF;
			}
		} else {
			nodes[layor][0][2].z = nodes[layor][2][2].y;
			nodes[layor][1][2].z = nodes[layor][2][1].y;
			nodes[layor][2][2].z = nodes[layor][2][0].y;

			nodes[layor][2][2].y = nodes[layor][2][0].z;
			nodes[layor][2][1].y = nodes[layor][1][0].z;
			nodes[layor][2][0].y = nodes[layor][0][0].z;

			nodes[layor][2][0].z = nodes[layor][0][0].y;
			nodes[layor][1][0].z = nodes[layor][0][1].y;
			nodes[layor][0][0].z = nodes[layor][0][2].y;

			nodes[layor][0][0].y = tmpA;
			nodes[layor][0][1].y = tmpB;
			nodes[layor][0][2].y = tmpC;

			if (layor != 1) {
				String tmpD = nodes[layor][0][2].x;
				String tmpF = nodes[layor][1][2].x;
				nodes[layor][0][2].x = nodes[layor][2][2].x;
				nodes[layor][2][2].x = nodes[layor][2][0].x;
				nodes[layor][2][0].x = nodes[layor][0][0].x;
				nodes[layor][0][0].x = tmpD;
				nodes[layor][1][2].x = nodes[layor][2][1].x;
				nodes[layor][2][1].x = nodes[layor][1][0].x;
				nodes[layor][1][0].x = nodes[layor][0][1].x;
				nodes[layor][0][1].x = tmpF;
			}
		}
	}

	public void rotateY(int layor, boolean clockwise) {
		String tmpA = nodes[2][layor][2].z;
		String tmpB = nodes[1][layor][2].z;
		String tmpC = nodes[0][layor][2].z;
		if (clockwise) {
			nodes[2][layor][2].z = nodes[2][layor][0].x;
			nodes[1][layor][2].z = nodes[2][layor][1].x;
			nodes[0][layor][2].z = nodes[2][layor][2].x;

			nodes[2][layor][0].x = nodes[0][layor][0].z;
			nodes[2][layor][1].x = nodes[1][layor][0].z;
			nodes[2][layor][2].x = nodes[2][layor][0].z;

			nodes[0][layor][0].z = nodes[0][layor][2].x;
			nodes[1][layor][0].z = nodes[0][layor][1].x;
			nodes[2][layor][0].z = nodes[0][layor][0].x;

			nodes[0][layor][2].x = tmpA;
			nodes[0][layor][1].x = tmpB;
			nodes[0][layor][0].x = tmpC;

			if (layor != 1) {
				String tmpD = nodes[2][layor][2].y;
				String tmpF = nodes[1][layor][2].y;
				nodes[2][layor][2].y = nodes[2][layor][0].y;
				nodes[2][layor][0].y = nodes[0][layor][0].y;
				nodes[0][layor][0].y = nodes[0][layor][2].y;
				nodes[0][layor][2].y = tmpD;
				nodes[1][layor][2].y = nodes[2][layor][1].y;
				nodes[2][layor][1].y = nodes[1][layor][0].y;
				nodes[1][layor][0].y = nodes[0][layor][1].y;
				nodes[0][layor][1].y = tmpF;
			}
		} else {
			nodes[2][layor][2].z = nodes[0][layor][2].x;
			nodes[1][layor][2].z = nodes[0][layor][1].x;
			nodes[0][layor][2].z = nodes[0][layor][0].x;

			nodes[0][layor][2].x = nodes[0][layor][0].z;
			nodes[0][layor][1].x = nodes[1][layor][0].z;
			nodes[0][layor][0].x = nodes[2][layor][0].z;

			nodes[0][layor][0].z = nodes[2][layor][0].x;
			nodes[1][layor][0].z = nodes[2][layor][1].x;
			nodes[2][layor][0].z = nodes[2][layor][2].x;

			nodes[2][layor][0].x = tmpA;
			nodes[2][layor][1].x = tmpB;
			nodes[2][layor][2].x = tmpC;

			if (layor != 1) {
				String tmpD = nodes[2][layor][2].y;
				String tmpF = nodes[1][layor][2].y;
				nodes[2][layor][2].y = nodes[0][layor][2].y;
				nodes[0][layor][2].y = nodes[0][layor][0].y;
				nodes[0][layor][0].y = nodes[2][layor][0].y;
				nodes[2][layor][0].y = tmpD;
				nodes[1][layor][2].y = nodes[0][layor][1].y;
				nodes[0][layor][1].y = nodes[1][layor][0].y;
				nodes[1][layor][0].y = nodes[2][layor][1].y;
				nodes[2][layor][1].y = tmpF;
			}
		}
	}

	public void rotateZ(int layor, boolean clockwise) {
		String tmpA = nodes[0][0][layor].x;
		String tmpB = nodes[0][1][layor].x;
		String tmpC = nodes[0][2][layor].x;
		if (clockwise) {
			nodes[0][0][layor].x = nodes[2][0][layor].y;
			nodes[0][1][layor].x = nodes[1][0][layor].y;
			nodes[0][2][layor].x = nodes[0][0][layor].y;

			nodes[2][0][layor].y = nodes[2][2][layor].x;
			nodes[1][0][layor].y = nodes[2][1][layor].x;
			nodes[0][0][layor].y = nodes[2][0][layor].x;

			nodes[2][2][layor].x = nodes[0][2][layor].y;
			nodes[2][1][layor].x = nodes[1][2][layor].y;
			nodes[2][0][layor].x = nodes[2][2][layor].y;

			nodes[0][2][layor].y = tmpA;
			nodes[1][2][layor].y = tmpB;
			nodes[2][2][layor].y = tmpC;

			if (layor != 1) {
				String tmpD = nodes[0][0][layor].z;
				String tmpF = nodes[0][1][layor].z;
				nodes[0][0][layor].z = nodes[2][0][layor].z;
				nodes[2][0][layor].z = nodes[2][2][layor].z;
				nodes[2][2][layor].z = nodes[0][2][layor].z;
				nodes[0][2][layor].z = tmpD;
				nodes[0][1][layor].z = nodes[1][0][layor].z;
				nodes[1][0][layor].z = nodes[2][1][layor].z;
				nodes[2][1][layor].z = nodes[1][2][layor].z;
				nodes[1][2][layor].z = tmpF;
			}
		} else {
			nodes[0][0][layor].x = nodes[0][2][layor].y;
			nodes[0][1][layor].x = nodes[1][2][layor].y;
			nodes[0][2][layor].x = nodes[2][2][layor].y;

			nodes[0][2][layor].y = nodes[2][2][layor].x;
			nodes[1][2][layor].y = nodes[2][1][layor].x;
			nodes[2][2][layor].y = nodes[2][0][layor].x;

			nodes[2][2][layor].x = nodes[2][0][layor].y;
			nodes[2][1][layor].x = nodes[1][0][layor].y;
			nodes[2][0][layor].x = nodes[0][0][layor].y;

			nodes[2][0][layor].y = tmpA;
			nodes[1][0][layor].y = tmpB;
			nodes[0][0][layor].y = tmpC;

			if (layor != 1) {
				String tmpD = nodes[0][0][layor].z;
				String tmpF = nodes[0][1][layor].z;
				nodes[0][0][layor].z = nodes[0][2][layor].z;
				nodes[0][2][layor].z = nodes[2][2][layor].z;
				nodes[2][2][layor].z = nodes[2][0][layor].z;
				nodes[2][0][layor].z = tmpD;
				nodes[0][1][layor].z = nodes[1][2][layor].z;
				nodes[1][2][layor].z = nodes[2][1][layor].z;
				nodes[2][1][layor].z = nodes[1][0][layor].z;
				nodes[1][0][layor].z = tmpF;

			}
		}
	}

	public static void main(String[] args) {
		Cube cube = new Cube();
		System.out.println("初始化魔方：");
		System.out.println(cube);
		System.out.println("测试x旋转：");
		cube.rotateX(2, true);
		System.out.println(cube);
		cube.rotateX(2, false);
		System.out.println(cube);
		System.out.println("测试y旋转：");
		cube.rotateY(2, true);
		System.out.println(cube);
		cube.rotateY(2, false);
		System.out.println(cube);
		System.out.println("测试z旋转：");
		cube.rotateZ(2, true);
		System.out.println(cube);
		cube.rotateZ(2, false);
		System.out.println(cube);

	}

}
