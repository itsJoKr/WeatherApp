package com.example.slaven.weatherapp.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.slaven.weatherapp.R;
import com.example.slaven.weatherapp.data.forecast.Forecast;
import com.example.slaven.weatherapp.data.forecast.List;
import com.example.slaven.weatherapp.data.forecast.Weather;
import com.example.slaven.weatherapp.data.weather.CurrentWeather;
import com.example.slaven.weatherapp.network.LocationApi;
import com.example.slaven.weatherapp.ui.detail.DetailActivity;
import com.example.slaven.weatherapp.ui.main.adapter.AdapterActivityComm;
import com.example.slaven.weatherapp.ui.main.recycler.CustomRecyclerView;
import com.example.slaven.weatherapp.ui.main.recycler.RecyclerScrollComm;
import com.example.slaven.weatherapp.util.Condition;
import com.example.slaven.weatherapp.util.DayNightUtil;
import com.example.slaven.weatherapp.anim.FadeAnimator;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends Activity implements MainView, AdapterActivityComm, RecyclerScrollComm {

    public static final int COLLAPSED = 2;
    public static final int EXPANDED = 1;

    MainPresenter presenter;

    int mActionBarSize;
    int toolbarState = EXPANDED;

    @Bind(R.id.img_main_weather_icon)
            ImageView imgWeatherMain;
    @Bind(R.id.txt_main_temp)
            TextView txtTempMain;
    @Bind(R.id.txt_toolbar_title)
            TextView txtToolbarTitle;
    @Bind(R.id.card_rec_view)
            CardView cardViewRecycler;
    @Bind(R.id.toolbarLayout)
            AppBarLayout appBarLayout;
    @Bind(R.id.coordinator_layout)
            CoordinatorLayout coordinatorLayout;
    @Bind(R.id.img_toolbar_second)
            View toolbarImageView;
    @Bind(R.id.txt_toolbar_temp)
            View toolbarTextTemp;

    CustomRecyclerView recyclerView;
    ForecastAdapter forecastAdapter;
    private RelativeLayout background;
    private Bundle currentWeatherBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mActionBarSize = getActionBarSize();
        presenter = new MainPresenterImpl(this);

        // Show splash screen
        toolbarImageView.setVisibility(View.INVISIBLE);
        toolbarTextTemp.setVisibility(View.INVISIBLE);
        background = (RelativeLayout) findViewById(R.id.main_relative_layout);
        background.setBackgroundDrawable(getResources().getDrawable(R.drawable.spash_white));
        cardViewRecycler.setVisibility(View.GONE);
        appBarLayout.setVisibility(View.GONE);

        // Recycler view setup
        recyclerView = (CustomRecyclerView) cardViewRecycler.findViewById(R.id.rec_view);
        forecastAdapter = new ForecastAdapter(this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(forecastAdapter);
        recyclerView.registerOnScrollListener(this);

        // presenter.downloadData();
        // Data download starts automatically when LocationApi gets Location
        new LocationApi().getLastKnownLocation(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showCurrentWeather(CurrentWeather currentWeather) {
        // Remove splash screen
        background.setBackgroundDrawable(getResources().getDrawable(R.drawable.splash_default));
        cardViewRecycler.setVisibility(View.VISIBLE);
        appBarLayout.setVisibility(View.VISIBLE);
        txtToolbarTitle.setText(currentWeather.getName() + "  ");

        // Data to show in toolbar
        String cityName = currentWeather.getName();
        String currentTemp = (int) currentWeather.getMain().getTemp() + 0.5 + "°C";
        Weather showWeather = currentWeather.getWeather().get(0);

        // Bundle (current weather) data to send to detail activity
        currentWeatherBundle = new Bundle();
        currentWeatherBundle.putString("cityName", cityName);
        currentWeatherBundle.putString("currentTemp", currentTemp);
        currentWeatherBundle.putSerializable("weather", showWeather);

        // Show weather
        ImageView toolbarWeatherIcon = (ImageView) toolbarImageView;
        toolbarWeatherIcon.setImageResource(Condition.getToolbarWeatherIcon(showWeather.getId(), DayNightUtil.isDayIcon(showWeather.getIcon())));

        TextView txtTemperature = (TextView) toolbarTextTemp;
        txtTemperature.setText("  " +(int)(currentWeather.getMain().getTemp() + 0.5) + "°C");
        imgWeatherMain.setImageResource(Condition.getMainImgId(showWeather.getId(), DayNightUtil.isDayIcon(showWeather.getIcon())));
        txtTempMain.setText((int)(currentWeather.getMain().getTemp() + 0.5) + "°C");
    }

    @Override
    public void showForecast(Forecast forecast) {
        forecastAdapter.setForecastData(forecast);
    }

    @Override
    public void showError(String error) {
        TextView txtError = (TextView) findViewById(R.id.txt_error);
        txtError.setText(error);
        txtError.setVisibility(View.VISIBLE);
    }


    @Override
    public void onHolderClick(List detailData, String cityName) {
        // Launch detail activity
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle dataBundle = new Bundle();
        dataBundle.putSerializable("detail", detailData);
        dataBundle.putSerializable("name", cityName);
        intent.putExtra("bundle", dataBundle);
        intent.putExtra("currentWeather", currentWeatherBundle);

        this.startActivity(intent);
    }


    /** Methods for collapsable toolbar
     *  When recycler view is done scrolling we determine whether or not toolbar changed it's state (expanded or collapsed)
     *  and we fade in/out toolbar icons appropriately
     */

    public int getActionBarSize(){
        final TypedArray styledAttributes = getApplicationContext().getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return actionBarSize;
    }

    @Override
    public void onRecycleScrolled(int state) {
        Log.d("USER", "STATE: " + state + ", expanded: " + coordinatorLayout.isPointInChildBounds(imgWeatherMain, 2 * mActionBarSize, 2 * mActionBarSize));
        if (state == RecyclerView.SCROLL_STATE_IDLE){
            if (coordinatorLayout.isPointInChildBounds(imgWeatherMain, 2 * mActionBarSize, 2 * mActionBarSize)){
                //toolbar expanded
                if (toolbarState != EXPANDED){
                    toolbarState = EXPANDED;
                    onToolbarStateChanged();
                }
            }
            else {
                //toolbar collapsed
                if (toolbarState!=COLLAPSED){
                    toolbarState = COLLAPSED;
                    onToolbarStateChanged();
                }
            }
        }
    }

    private void onToolbarStateChanged(){
        if (toolbarState == EXPANDED){
            FadeAnimator.fadeOutAndHideView(toolbarImageView);
            FadeAnimator.fadeOutAndHideView(toolbarTextTemp);
        }
        if (toolbarState == COLLAPSED){
            FadeAnimator.fadeInView(toolbarImageView);
            FadeAnimator.fadeInView(toolbarTextTemp);
        }
    }
}
