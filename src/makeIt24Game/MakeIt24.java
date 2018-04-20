package makeIt24Game;

import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MakeIt24 {

	private Random random = new Random();

	private int number1;
	private int number2;
	private int number3;
	private int number4;

	public void initializeNumber() {
		number1 = random.nextInt(9);
		number2 = random.nextInt(9);
		number3 = random.nextInt(9);
		number4 = random.nextInt(9);
	}
	
	public static boolean game24Points(int[] operands) {
	    ScriptEngineManager sem = new ScriptEngineManager();
	    ScriptEngine engine = sem.getEngineByName("javascript");

	    char[] operations = new char[] { '+', '-', '*', '/' };
	    for (int i = 0; i < 4; i++) {
	        for (int j = 0; j < 4; j++) {
	            for (int k = 0; k < 4; k++) {
	                try {
	                    String exp = "" + operands[0] + operations[i] + operands[1] + operations[j]
	                            + operands[2] + operations[k] + operands[3];
	                    String res = engine.eval(exp).toString();
	                    if (Double.valueOf(res).intValue() == 24) {
	                        System.out.println(exp);
	                        return true;
	                    }
	                } catch (ScriptException e) {
	                    return false;
	                }
	            }
	        }
	    }
	    return false;
	}


	public int getNumber1() {
		return number1;
	}

	public int getNumber2() {
		return number2;
	}

	public int getNumber3() {
		return number3;
	}

	public int getNumber4() {
		return number4;
	}

}
