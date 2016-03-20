package com.test.liferay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.model.PortletPreferences;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class TestPortlet
 */
public class TestPortlet extends MVCPortlet {
	public void addEntry(ActionRequest request, ActionResponse response) {
		 try {

		       javax.portlet.PortletPreferences prefs = request.getPreferences();

		       String[] guestbookEntries = prefs.getValues("guestbook-entries",
		          new String[1]);

		       ArrayList<String> entries = new ArrayList<String>();

		       if (guestbookEntries != null) {

		         entries = new ArrayList<String>(Arrays.asList(prefs.getValues(
		              "guestbook-entries", new String[1])));

		       }

		       String userName = ParamUtil.getString(request, "name");
		       String message = ParamUtil.getString(request, "message");
		       String entry = userName + "^" + message;

		       entries.add(entry);

		       String[] array = entries.toArray(new String[entries.size()]);

		       prefs.setValues("guestbook-entries", array);

		       try {

		         prefs.store();

		       } catch (IOException ex) {

		         Logger.getLogger(TestPortlet.class.getName()).log(
		              Level.SEVERE, null, ex);

		       } catch (ValidatorException ex) {

		         Logger.getLogger(TestPortlet.class.getName()).log(
		              Level.SEVERE, null, ex);

		       }

		    } catch (ReadOnlyException ex) {

		       Logger.getLogger(TestPortlet.class.getName()).log(
		          Level.SEVERE, null, ex);

		    }

		
	}

}
