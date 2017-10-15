package com.troy.diplo.game;

import java.io.*;
import java.util.HashSet;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.*;

public class TypeList<T> {

	private File file;
	private HashSet<T> items;

	public TypeList(File file, Kryo kryo) throws FileNotFoundException {
		this.file = file;
		this.items = new HashSet<T>();
		Input in = new Input(new FileInputStream(file));
		while (!in.eof()) {
			items.add((T) kryo.readClassAndObject(in));
		}
		in.close();
		System.out.println("items " + items.toString());
	}

	public void cleanUp(Kryo kryo) {
		try {
			Output out = new Output(new FileOutputStream(file));
			for(T item : items) {
				kryo.writeClassAndObject(out, item);
				
			}
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
