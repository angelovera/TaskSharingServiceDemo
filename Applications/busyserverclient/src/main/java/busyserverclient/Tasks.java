package busyserverclient;

import java.util.Random;
import java.io.FileWriter;

//import java.util.*;
//import java.io.*;
//import java.io.File;
//import java.util.concurrent.TimeUnit;

public class Tasks {
	public static int[][] randomMatrix(int[][] matrix) {
		Random rand = new Random();
		for (int i=0; i<matrix.length; i++) {
			for (int j=0; j<matrix.length; j++) {
				matrix[i][j]=rand.nextInt(2);
			}
		}
		return matrix;
	}
	
	public static int[][] upperTriang(int[][] matrix) {
		for (int i=0; i<matrix.length; i++) {
			for (int j=i; j<matrix.length; j++) {
				matrix[i][j]=0;
			}
		}
		return matrix;
	}
	
	public static void printMatrix(int[][] matrix) {
		for (int i=0; i<matrix.length; i++) {
			for (int j=0; j<matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public static String codeBlock() {
			String[] line = new String[10];
			String code;
			line[0] = "import java.util.concurrent.TimeUnit;\r\n";
			line[1] = "import java.util.Random;\r\n";
			line[2] = "import java.io.*;\r\n\n";
			line[3] = "public class SharedTasks {\r\n";
			line[4] = "\tpublic static void main (String[] args) {\r\n";
			line[5] = "\t\tRandom wait = new Random();\r\n";
			line[6] = "\t\tTimeUnit.SECONDS.sleep(wait);\r\n";
			line[7] = "\t\tSystem.out.print(\"This is the end of the program, thank you for waiting.\");\r\n";
			line[8] = "\t}\r\n";
			line[9] = "}\r\n";
			code = line[0]+line[1]+line[2]+line[3]+line[4]+line[5]+line[6]+line[7]+line[8]+line[9];
			return code;
	} 
	
	public static void createJavaFiles(int numberOfTasks, String name, String code){
		FileWriter[] file = new FileWriter[numberOfTasks];
		String temp = name;
		for(int i=0; i<numberOfTasks; i++) {
			name = temp+i+".java";
			code = codeBlock();
			try {
				file[i] = new FileWriter(name);
				file[i].write(code);
				file[i].close();
				System.out.println("file "+i+" created");
			}
			catch (Exception e) {
				e.getStackTrace();
			}	
		}
		return;
	}
	
	public static void main (String[] args) {
		int numberOfTasks = 10;
		int[][] dependencies = new int[numberOfTasks][numberOfTasks];;
		String code = codeBlock();
		System.out.println("Creating tasks, please wait.");
		createJavaFiles(numberOfTasks,"task",code);
		dependencies = randomMatrix(dependencies);
		dependencies = upperTriang(dependencies);
		printMatrix(dependencies);
		System.out.print("Task creation is complete, thank you for waiting.");
		}
}
	


