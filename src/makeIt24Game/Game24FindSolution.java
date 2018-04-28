package makeIt24Game;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Game24FindSolution {

	public static void do24(int a, int b, int c, int d) {

		int[] num = { a, b, c, d };
		String[] ops = { "+", "-", "*", "/" };
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByName("js");
		
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length; j++) {
				if (i == j)
					continue;
				for (int k = 0; k < num.length; k++) {
					if (k == i || k == j)
						continue;
					int l = 1 + 2 + 3 + 0 - i - j - k;
					for (int p = 0; p < num.length; p++) {
						for (int q = 0; q < num.length; q++) {
							for (int r = 0; r < num.length; r++) {

								for (int p1o = 1; p1o < 4; p1o++) {
									for (int p1c = p1o + 1; p1c < 5; p1c++) {
										for (int iSpecial = 1; iSpecial <= 2; iSpecial++) {
											int p2o;
											int p2c;
											if (iSpecial == 1 && p1o == 1 && p1c == 2) {
												p2o = 3;
												p2c = 4;
											} else {
												p2o = 0;
												p2c = 0;
											}

											String expr;
											expr = p1o == 1 ? "(" : "";
											expr += num[i] + ops[p] + (p1o == 2 ? "(" : "");
											expr += num[j] + (p1c == 2 ? ")" : "");
											expr += ops[q] + (p1o == 3 || p2o == 3 ? "(" : "");
											expr += num[k] + (p1c == 3 ? ")" : "");
											expr += ops[r] + num[l] + (p1c == 4 || p2c == 4 ? ")" : "");
											
											     
											try {
												Object result = engine.eval(expr);
												if(String.valueOf(result).equals("24")) {
													System.out.println(expr);
													return;
												}
											} catch (ScriptException e) {
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
					}
				}

			}
		}
	}

	public static void main(String[] args) {
		do24(5, 5, 5, 1);
	}

}
