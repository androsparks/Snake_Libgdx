package com.bosowski.snake;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AndroidLauncher extends AndroidApplication {
	private static final String TAG = AndroidLauncher.class.getName();
	private AdView adView;

	private final int SHOW_ADS = 1;
	private final int HIDE_ADS = 0;

//	Handler handler = new Handler(){
//		@Override
//		public void handleMessage(Message msg){
//			switch (msg.what){
//				case SHOW_ADS:
//					adView.setVisibility(View.VISIBLE);
//					break;
//				case HIDE_ADS:
//					adView.setVisibility(View.GONE);
//					break;
//			}
//		}
//	};

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//initialize(new Snake(), config);
		RelativeLayout layout = new RelativeLayout(this);


		View gameView = initializeForView(new Snake(), config);
		layout.addView(gameView);

		adView = new AdView(this);

//		adView.setAdListener(new AdListener() {
//			@Override
//			public void onAdLoaded(){
//				Log.i(TAG, "Ad Loaded...");
//			}
//		});

		adView.setAdSize(AdSize.BANNER);
		//adView.setAdUnitId("ca-app-pub-5725047638356457/4737731682");//real
		adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");//test


		AdRequest.Builder builder = new AdRequest.Builder();
		//builder.addTestDevice("9176508D2900CB5A4B3B15AE8C66A559");
//		builder.addTestDevice("EFE5C7C92495D3AD2ECF653187E8121E");
		RelativeLayout.LayoutParams adParams = new RelativeLayout.LayoutParams(
			RelativeLayout.LayoutParams.WRAP_CONTENT,
			RelativeLayout.LayoutParams.WRAP_CONTENT
		);


		setContentView(layout);
		MobileAds.initialize(this, "ca-app-pub-5725047638356457~3531202046");

		layout.addView(adView, adParams);
		adView.loadAd(builder.build());
	}
}
