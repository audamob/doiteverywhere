package com.audamob.doit.view.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class AboutDialog {

	public AboutDialog(Context context) {
		// Comportement du bouton "Paramètres"
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setTitle("ABOUT US");
		alertDialog.setMessage("EVENT ORGANIZER\n" + "VERSION : 1.0 \n"
				+ "DEVELOPER : AudaSoft \n" + "CONTACT : contact@audasoft.fr");

		alertDialog.setButton("CLOSE", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				// here you can add functions
			}
		});
		//alertDialog.setIcon(R.drawable.info_ation_bar);
		//alertDialog.show();
	}
}
