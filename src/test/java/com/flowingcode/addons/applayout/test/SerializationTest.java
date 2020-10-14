package com.flowingcode.addons.applayout.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

import com.flowingcode.addons.applayout.AppLayout;
import com.flowingcode.addons.applayout.MenuItem;

public class SerializationTest {

	private void testSerializationOf(Object obj) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
			oos.writeObject(obj);
		}
		try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()))) {
			obj.getClass().cast(in.readObject());
		}
	}

	@Test
	public void testSerialization() throws ClassNotFoundException, IOException {
		AppLayout appLayout = new AppLayout("");
		appLayout.setMenuItems(new MenuItem("Item", () -> {}));
		testSerializationOf(appLayout);
	}

}