package br.com.kolss.nicbrainmobile.util.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

public class RestTask extends AsyncTask<HttpUriRequest, Void, String> {

	private static final String TAG = "RestTask";
	public static final String HTTP_RESPONSE = "httpResponse";

	private Context ctx = null;
	private HttpClient client = null;
	private String action = null;

	public RestTask(final Context ctx, final String action) {
		this.ctx = ctx;
		this.action = action;
		this.client = new DefaultHttpClient();
	}

	public RestTask(final Context ctx, final String action, final HttpClient client) {
		this.ctx = ctx;
		this.action = action;
		this.client = client;
	}

	@Override
	protected String doInBackground(final HttpUriRequest... params) {
		String response =  null;
		try {
			final HttpUriRequest request = params[0];
			final HttpResponse serverResponse = client.execute(request);
			final BasicResponseHandler handler = new BasicResponseHandler();
			response = handler.handleResponse(serverResponse);
		} catch (final Exception e) {
			//e.printStackTrace();
			Log.e(TAG, e.getMessage());
		}
		return response;
	}

	@Override
	protected void onPostExecute(final String result) {
		final Intent intent = new Intent(action);
		intent.putExtra(HTTP_RESPONSE, result);
		ctx.sendBroadcast(intent);
	}

}