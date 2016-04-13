package com.ding.socket;

import java.io.PrintWriter;

import java.io.InputStreamReader;

import java.io.BufferedReader;

import java.io.IOException;
import java.net.Socket;

public class TestClient {

	/**
	 * @author daniel
	 * @time 2016-4-8 ÏÂÎç3:42:02
	 * @param args
	 * @throws IOException
	 * @throws
	 */
	public static void main(String[] args) throws Exception {

		Socket socket = new Socket("127.0.0.1", 4700);

		BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));

		PrintWriter os = new PrintWriter(socket.getOutputStream());

		BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		String readLine = sin.readLine();

		while (!readLine.equals("bye")) {
			os.println(readLine);
			os.flush();
			System.out.println("Client:" + readLine);
			System.out.println("Server:" + is.readLine());
			readLine = sin.readLine();
		}
		os.close();
		is.close();
		socket.close();

	}

}
