package com.example.bubblelayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	private BubbleLayout bubbleLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bubbleLayout = (BubbleLayout) findViewById(R.id.bubble);

		findViewById(R.id.start).setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){

				bubbleLayout.onAdd();
			}
		});
	}


}
