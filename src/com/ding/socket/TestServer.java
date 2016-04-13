package com.ding.socket;

import java.io.PrintWriter;

import java.io.InputStreamReader;

import java.io.BufferedReader;

import java.net.Socket;

import java.net.ServerSocket;

public class TestServer { 
	public static void main(String[] args) throws Exception { 
		ServerSocket server = new ServerSocket(4700);
		Socket socket = server.accept();
		BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintWriter os = new PrintWriter(socket.getOutputStream());
		BufferedReader sin = new BufferedReader(new InputStreamReader(System.in)); 
		System.out.println("Client:" + is.readLine());
		String line = sin.readLine(); 
		while (!line.equals("bye")) {
			os.println(line);
			os.flush();
			System.out.println("Server:" + line); 
			System.out.println("Client:" + is.readLine());
			line = sin.readLine(); 
		} 
		os.close();
		is.close();
		socket.close();
		server.close(); 
	} 
}
