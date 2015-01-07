/*
 * Copyright (C) 2012 http://emuneee.com/blog/apps/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.emuneee.nctrafficcams.tasks;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.emuneee.nctrafficcams.api.Camera;
import com.emuneee.nctrafficcams.ui.Constants;
import com.emuneee.nctrafficcams.ui.activities.DetailActivity;
import com.emuneee.nctrafficcams.ui.adapters.RetrievableCameraAdapter;

/**
 * Loads the detail activity while passing some data to it
 *
 * @author Evan
 *
 */
public class LoadDetailActivity extends
		AsyncTask<Void, Void, ArrayList<Camera>> {
	private RetrievableCameraAdapter mAdapter;
	private Activity mActivity;
	private int mPosition;

	public LoadDetailActivity(RetrievableCameraAdapter adapter, Activity activity,
			int position) {
		mAdapter = adapter;
		mActivity = activity;
		mPosition = position;
	}

	@Override
	protected ArrayList<Camera> doInBackground(Void... params) {
		return mAdapter.getCameras();
	}

	@Override
	protected void onPostExecute(ArrayList<Camera> cameras) {
		if (cameras != null) {
			Intent intent = new Intent(mActivity, DetailActivity.class);
			intent.putParcelableArrayListExtra(Constants.BUNDLE_CAMERAS,
					cameras);
			intent.putExtra(Constants.BUNDLE_CURRENT_INDEX, mPosition);
			mActivity.startActivity(intent);
		}
	}
}
