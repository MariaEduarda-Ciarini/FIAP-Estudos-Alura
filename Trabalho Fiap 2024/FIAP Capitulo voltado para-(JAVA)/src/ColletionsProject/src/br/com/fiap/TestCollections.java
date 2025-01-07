package br.com.fiap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TestCollections {

	public static void main(String[] args) {

		ArrayList cart = new ArrayList();
		Double value = 999.99;
		int value2 = 1;
		int value3;

		cart.add(value);
		cart.add("Grape");
		cart.add(value2);

		System.out.println(cart.get(1));

		value3 = (int) cart.get(2);

		System.out.println(value3);

		List<String> cart = new ArrayList<String>();

		System.out.println(cart.isEmpty());

		cart.add("Apple");
		cart.add("Strawberry");
		cart.add("Apple");

		cart.set(1, "Pear");

		System.out.println(cart.contains("Apple"));
		System.out.println(cart.indexOf("Apple"));
		System.out.println(cart.get(cart.lastIndexOf("Apple")));
		System.out.println(cart.lastIndexOf("Apple"));

		cart.remove(cart.indexOf("Apple"));
		System.out.println(cart.get(0));

		cart.clear();

		System.out.println(cart.isEmpty());

		System.out.println(cart.size());

		Set<String> cesta = new HashSet<String>();

		System.out.println(cesta.isEmpty());

		cesta.add("Apple");
		cesta.add("Apple");
		cesta.add("Apple");
		System.out.println(cesta.isEmpty());

		System.out.println(cesta.size());

		System.out.println(cesta);

		Map<String, String> box = new HashMap<String, String>();
		box.put("M2225", "Shrek1");
		box.put("M2226", "Maria");
		box.put("M2227", "BurrodoSherek");

		System.out.println(box.isEmpty());
		System.out.println(box.size());
		System.out.println(box.containsKey("M2225"));
		System.out.println(box.containsValue("BurrodoSherek"));
		System.out.println(box);

	}

}
