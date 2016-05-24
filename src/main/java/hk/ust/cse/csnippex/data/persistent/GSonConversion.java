package hk.ust.cse.csnippex.data.persistent;

import hk.ust.cse.csnippex.data.Result;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.google.gson.reflect.TypeToken;

/**
 * @author vterragni
 *
 * @param <T>
 */
public class GSonConversion<T> {

	public void storeFile(HashSet<T> set, String folder) {

		storeFile(set, folder, "cs");

	}

	public void storeFile(HashSet<T> set, String folder, String fileName) {

		final Gson gson = new GsonBuilder().disableHtmlEscaping().create();
		// convert java object to JSON format,
		// and returned as JSON formatted string
		final String json = gson.toJson(set);
		final File f = new File(folder);
		int id = f.listFiles().length;
		if (id > 0)
			id--;
		try {
			final FileWriter writer = new FileWriter(folder + fileName + id
					+ ".json");
			writer.write(json);
			writer.close();
			System.out.println("writing" + folder + fileName + id + ".json");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Result> readFolder(String folderP) {

		ArrayList<Result> set = new ArrayList<Result>();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		final Gson gson = gsonBuilder.create();

		// convert java object to JSON format,
		// and returned as JSON formatted string
		final File folder = new File(folderP);

		for (final File f : folder.listFiles()) {

			if (f.isDirectory()) {
				ArrayList<Result> set1;
				set1 = readFolder(f.getAbsolutePath());
				set.addAll(set1);
			}

			if (!f.isFile() || !f.getName().endsWith(".json"))
				continue;
			try {
				// write converted json data to a file named "CountryGSON.json"
				final BufferedReader br = new BufferedReader(new FileReader(f));

				// convert the json string back to object
				ArrayList<Result> set1;
				set1 = gson.fromJson(br, new TypeToken<ArrayList<Result>>() {
				}.getType());
				set.addAll(set1);

			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
		return set;
	}

	public ArrayList<Result> readFile(String fileP) {

		ArrayList<Result> set = new ArrayList<Result>();
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		final Gson gson = gsonBuilder.create();

		// convert java object to JSON format,
		// and returned as JSON formatted string
		final File f = new File(fileP);

		if (!f.isFile() || !f.getName().endsWith(".json"))
			try {
				throw new Exception();
			} catch (final Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		try {
			// write converted json data to a file named "CountryGSON.json"
			final BufferedReader br = new BufferedReader(new FileReader(f));

			ArrayList<Result> set1;
			set1 = gson.fromJson(br, new TypeToken<ArrayList<Result>>() {
			}.getType());
			set.addAll(set1);

		} catch (final IOException e) {
			e.printStackTrace();
		}

		return set;
	}

}
