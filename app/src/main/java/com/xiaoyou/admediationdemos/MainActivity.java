package com.xiaoyou.admediationdemos;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.applovin.mediation.MaxSegment;
import com.applovin.mediation.MaxSegmentCollection;
import com.applovin.sdk.AppLovinMediationProvider;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.applovin.sdk.AppLovinSdkInitializationConfiguration;
import com.xiaoyou.admediationdemos.ads.max.InterstitialAdActivity;
import com.xiaoyou.admediationdemos.data.main.ListItem;
import com.xiaoyou.admediationdemos.data.main.MainMenuItem;
import com.xiaoyou.admediationdemos.data.main.SectionHeader;
import com.xiaoyou.admediationdemos.ui.MainRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainRecyclerViewAdapter.OnMainListItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // // Create the initialization configuration
        // AppLovinSdkInitializationConfiguration initConfig = AppLovinSdkInitializationConfiguration.builder( "9uHgeBwag3NXva9MC23ToO3q11Ve59bF1uwg4qGltdGmCQ7OSByFZ_3b1ZF7krMlkHQo5gXzIokVDsvg1rwbr-" )
        //         .setMediationProvider( AppLovinMediationProvider.MAX )
        //         .setSegmentCollection( MaxSegmentCollection.builder()
        //                 .addSegment( new MaxSegment( 849, Arrays.asList( 1, 3 ) ) )
        //                 .build() )
        //         .build();


        // Create the initialization configuration
        AppLovinSdkInitializationConfiguration initConfig = AppLovinSdkInitializationConfiguration.builder( "9uHgeBwag3NXva9MC23ToO3q11Ve59bF1uwg4qGltdGmCQ7OSByFZ_3b1ZF7krMlkHQo5gXzIokVDsvg1rwbr-",this)
                .setMediationProvider( AppLovinMediationProvider.MAX )
        // Perform any additional configuration/setting changes
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

                Toast.makeText(MainActivity.this,"初始化已完成！", Toast.LENGTH_SHORT).show();

                final MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter( generateMainListItems(), MainActivity.this, MainActivity.this );
                final LinearLayoutManager manager = new LinearLayoutManager( MainActivity.this ); // 布局管理器
                final DividerItemDecoration decoration = new DividerItemDecoration( MainActivity.this, manager.getOrientation() ); // 分割线

                final RecyclerView recyclerView = findViewById( R.id.main_recycler_view );
                recyclerView.setHasFixedSize( true );
                recyclerView.setLayoutManager( manager );
                recyclerView.addItemDecoration( decoration );
                recyclerView.setItemAnimator( new DefaultItemAnimator() );
                recyclerView.setAdapter( adapter );

                System.out.println(isNetworkAvailable(MainActivity.this));

                // // Check that SDK key is present in Android Manifest
                // checkSdkKey();

            }
        } );

    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void checkSdkKey()
    {
        final String sdkKey = AppLovinSdk.getInstance( getApplicationContext() ).getSdkKey();
        if ( "4e3d8dc87fc3fb78".equalsIgnoreCase( sdkKey ) )
        {
            new AlertDialog.Builder( this )
                    .setTitle( "ERROR" )
                    .setMessage( "Please update your sdk key in the manifest file." )
                    .setCancelable( false )
                    .setNeutralButton( "OK", null )
                    .show();
        }
    }


    private List<ListItem> generateMainListItems()
    {
        final List<ListItem> items = new ArrayList<>();
        items.add( new SectionHeader( "MAX" ) );
        items.add( new MainMenuItem( "Interstitials", new Intent( this, InterstitialAdActivity.class ) ) );
        // items.add( new MainMenuItem( "App Open Ads", new Intent( this, AppOpenAdActivity.class ) ) );
        // items.add( new MainMenuItem( "Rewarded", new Intent( this, RewardedAdActivity.class ) ) );
        // items.add( new MainMenuItem( "Banners", new Intent( this, BannerAdActivity.class ) ) );
        // items.add( new MainMenuItem( "MRECs", new Intent( this, MrecAdActivity.class ) ) );
        // items.add( new MainMenuItem( "Native Ads", new Intent( this, NativeAdActivity.class ) ) );
        return items;
    }

    @Override
    public void onItemClicked(final ListItem item)
    {
        if ( item.getType() == ListItem.TYPE_AD_ITEM )
        {
            final MainMenuItem demoMenuItem = (MainMenuItem) item;
            if ( demoMenuItem.getIntent() != null )
            {
                startActivity( demoMenuItem.getIntent() );
            }
            else if ( demoMenuItem.getRunnable() != null )
            {
                demoMenuItem.getRunnable().run();
            }
        }
    }
}