package algorithms.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;

public class CompressTest {

	public static void main(String[] args) {
		// Create Maze
		Maze3dGenerator gn = new GrowingTreeGenerator();
		Maze3d maze = gn.generate(2,2,6,6);
//		byte[] bArr = maze.toByteArray();
		
		// save it to a file
		OutputStream out;
		try {
			out = new MyCompressorOutputStream(new FileOutputStream("1.maz"));
			out.write(maze.toByteArray());
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		InputStream in;
		try {
		in = new MyDecompressorInputStream(new FileInputStream("1.maz"));
		byte b[] = new byte[maze.toByteArray().length];
		in.read(b);
		in.close();
		
		Maze3d loaded = new Maze3d(b);
		System.out.println(loaded.equals(maze));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
