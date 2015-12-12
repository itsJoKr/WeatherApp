package com.example.slaven.weatherapp.ui.detail;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.slaven.weatherapp.R;
import com.example.slaven.weatherapp.data.forecast.List;
import com.example.slaven.weatherapp.data.forecast.Weather;
import com.example.slaven.weatherapp.util.Condition;
import com.example.slaven.weatherapp.util.DayNightUtil;
import com.example.slaven.weatherapp.util.WindDirection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @Bind(R.id.img_detail_weather_icon)
    ImageView imgWeatherMain;
    @Bind(R.id.txt_detail_temp)
    TextView txtTempMain;
    @Bind(R.id.img_detail_wind)
    ImageView imgWindMain;
    @Bind(R.id.txt_detail_wind)
    TextView txtWindMain;
    @Bind(R.id.img_detail_rain)
    ImageView imgRainMain;
    @Bind(R.id.txt_detail_rain)
    TextView txtRainMain;
    @Bind(R.id.img_detail_press)
    ImageView imgPressureMain;
    @Bind(R.id.txt_detail_press)
    TextView txtPressureMain;
    @Bind(R.id.txt_detail_condition)
    TextView txtMainCondition;

    //Toolbar
    @Bind(R.id.txt_toolbar_title_detail)
    TextView toolbarTitle;
    @Bind(R.id.img_toolbar_second_detail)
    ImageView toolbarWeatherIcon;
    @Bind(R.id.txt_toolbar_temp_detail)
    TextView toolbarTemp;
    @Bind(R.id.app_bar_detail)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();

        // Detail weather bundle
        Bundle dataBundle = intent.getBundleExtra("bundle");
        String cityName = dataBundle.getString("name");
        List detailData = (List) dataBundle.getSerializable("detail");

        // Current weather bundle
        Bundle currentWeatherBundle = intent.getBundleExtra("currentWeather");
        String currentTemp = currentWeatherBundle.getString("currentTemp");
        Weather currentWeather = (Weather) currentWeatherBundle.getSerializable("weather");

        // Show toolbar current weather
        toolbarTitle.setText(cityName + "  ");
        toolbarTemp.setText(" " + currentTemp);
        toolbarWeatherIcon.setImageResource(Condition.getToolbarWeatherIcon(currentWeather.getId(), DayNightUtil.isDayIcon(currentWeather.getIcon())));

        // Back button
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        // Show detailed selected weather
        Weather weatherToShow = detailData.getWeather().get(0);
        imgWeatherMain.setImageResource(Condition.getMainImgId(weatherToShow.getId(), DayNightUtil.isDayIcon(weatherToShow.getIcon())));
        txtMainCondition.setText(weatherToShow.getDescription());
        txtTempMain.setText(detailData.getMain().getTemp() + "°C");
        txtPressureMain.setText(detailData.getMain().getPressure() + " kPa");
        txtWindMain.setText(detailData.getWind().getSpeed() + "kph " + WindDirection.getWindDirection(detailData.getWind().getDeg()));

        if (detailData.getRain()!=null){
            txtRainMain.setText(detailData.getRain().getRainPer3h() + " mm/3h");
        }
        else {
            txtRainMain.setText("No rain");
        }

        imgWindMain.setImageResource(R.drawable.wind);
        imgRainMain.setImageResource(R.drawable.water);
        imgPressureMain.setImageResource(R.drawable.pres);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
}
