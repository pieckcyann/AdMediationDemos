package com.xiaoyou.admediationdemos;

import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.applovin.mediation.MaxSegment;
import com.applovin.mediation.MaxSegmentCollection;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkInitializationConfiguration;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private ExampleAppOpenManager appOpenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the initialization configuration
        AppLovinSdkInitializationConfiguration initConfig = AppLovinSdkInitializationConfiguration.builder( "«SDK-key»" )
                .setMediationProvider( AppLovinMediationProvider.MAX )
                .setSegmentCollection( MaxSegmentCollection.builder()
                        .addSegment( new MaxSegment( 849, Arrays.asList( 1, 3 ) ) )
                        .build() )
                .build();


        // Initialize the SDK with the configuration
        // 使用配置初始化 SDK
        AppLovinSdk.getInstance( this ).initialize( initConfig, new AppLovinSdk.SdkInitializationListener()
        {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration sdkConfig)
            {
                // Start loading ads
                // System.out.println("初始化成功！");

                appOpenManager = new ExampleAppOpenManager( MainActivity.this );
            }
        } );
    }
}