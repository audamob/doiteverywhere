package com.audamob.doit.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ValidationDialog {

	public ValidationDialog(Context context, String message, String title) {

		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle(title);
		alertDialog.setMessage(message);

		alertDialog.setButton("CLOSE", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
			}
		});
		//alertDialog.setIcon(R.drawable.facemash_icon);
		//alertDialog.show();
	}
}
