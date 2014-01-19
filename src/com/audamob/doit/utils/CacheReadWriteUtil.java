package com.audamob.doit.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.audamob.doit.model.User;

import android.content.Context;

public class CacheReadWriteUtil {

	
	public static User restoreAccount(Context context) throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream(context.getCacheDir()
				.getAbsolutePath() + "/account");
		ObjectInputStream in = new ObjectInputStream(fin);
		User object = (User) in.readObject();
		in.close();
		return object;
	}

	public static void saveAccount(User s,Context context) throws IOException {
		FileOutputStream fout = new FileOutputStream(context.getCacheDir()
				.getAbsolutePath() + "/account");
		ObjectOutputStream out = new ObjectOutputStream(fout);

		out.writeObject(s);
		out.close();
	}

}
